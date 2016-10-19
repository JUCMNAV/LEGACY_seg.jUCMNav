package seg.jUCMNav.views.wizards.dynamicContexts;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urn.dyncontext.DynamicContext;

/**
 * The page actually containing the code for including a contribution context. 
 * 
 * @author aprajita
 */
public class AddDynamicContextContributionContextPage extends WizardPage {
	

	private ISelection selection;
    private DynamicContext parent;
    private ContributionContext contriContext;
    private List contriContexts;

    /**
     * The selection contains a dynamic context definition for which we want to include a contribution context. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public AddDynamicContextContributionContextPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("AddDynamicContextContributionContextPage.AddDynamicContextContributionContext")); //$NON-NLS-1$
        setDescription(Messages.getString("AddDynamicContextContributionContextPage.PleaseChooseDynamicContextContributionContext")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.dynamiccontext_adddynamiccontextcontributioncontext"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddDynamicContextContributionContextPage.PleaseChooseDynamicContextContributionContext")); //$NON-NLS-1$

        initialize();

        contriContexts = new List(container, SWT.MULTI | SWT.BORDER | SWT.SCROLL_LINE);
        contriContexts.setItems(getPossibleChildren());

        GridData gd = new GridData(GridData.FILL_BOTH);
        contriContexts.setLayoutData(gd);
        contriContexts.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                dialogChanged();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        dialogChanged();
        setControl(container);

    }
    
    /**
     * 
     * @return all possible contribution contexts
     */
    private String[] getPossibleChildren() {
    	java.util.List contriContextGroups = null;
    	if (parent != null)
    		contriContextGroups = parent.getUrnspec().getGrlspec().getContributionGroups();
    	java.util.List possibleChildren = null;
    	
    	if (contriContextGroups != null) {
	    	for (int i = 0; i < contriContextGroups.size(); i++){
	    		if (((ContributionContextGroup) contriContextGroups.get(i)).getContribs().size() > 0) {
	    			if (i == 0)
	    				possibleChildren = ((ContributionContextGroup) contriContextGroups.get(i)).getContribs();
	    			else
	    				possibleChildren.addAll(((ContributionContextGroup) contriContextGroups.get(i)).getContribs());
	    		}
	    			
	    	}
    	}
        
    	ArrayList childrenStrings = new ArrayList();
        for (Iterator iter = possibleChildren.iterator(); iter.hasNext();) {
            ContributionContext possibleChild = (ContributionContext) iter.next();
            childrenStrings.add(URNNamingHelper.getName((ContributionContextGroup)possibleChild.getGroups().get(0))
                    + Messages.getString("IncludeDynamicContextPage.GroupDynamicContextSeperator") + URNNamingHelper.getName(possibleChild)); //$NON-NLS-1$
        }

        Object[] o = childrenStrings.toArray();
        String[] strings = new String[o.length];
        for (int i = 0; i < o.length; i++) {
            strings[i] = o[i].toString();
        }
        return strings;
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof DynamicContext) {
                parent = (DynamicContext) obj;
            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {
    	java.util.List contriContextGroups = null;
    	if (parent != null)
    		contriContextGroups = parent.getUrnspec().getGrlspec().getContributionGroups();
    	java.util.List possibleChildren = null;
    	
    	if (contriContextGroups != null) {
    		for (int i = 0; i < contriContextGroups.size(); i++){
	    		if (((ContributionContextGroup) contriContextGroups.get(i)).getContribs().size() > 0) {
	    			if (i == 0)
	    				possibleChildren = ((ContributionContextGroup) contriContextGroups.get(i)).getContribs();
	    			else
	    				possibleChildren.addAll(((ContributionContextGroup) contriContextGroups.get(i)).getContribs());
	    		}
	    			
	    	}
    	}
        if (contriContexts.getSelectionIndex() < 0)
            updateStatus(Messages.getString("AddDynamicContextContributionContextPage.SelectDynamicContextContributionContext")); //$NON-NLS-1$
        else {
            contriContext = null;
            for (int i = 0; i < contriContexts.getSelectionIndices().length; i++) {
                int index = contriContexts.getSelectionIndices()[i];
                contriContext = (ContributionContext) possibleChildren.get(index);
            }
            updateStatus(null);
        }
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

    }

    /**
     * Returns the child contribution context that has been selected.
     * 
     * @return the children
     */
    public ContributionContext getChildContributionContext() {
        return contriContext;
    }

    /**
     * Returns the parent scenario that was initially given
     * 
     * @return the child
     */
    public DynamicContext getParentDynamicContext() {
        return parent;
    }
}
