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

import grl.EvaluationStrategy;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.dyncontext.DynamicContext;

/**
 * The page actually containing the code for including a scenario. 
 * 
 * @author aprajita
 */
public class AddDynamicContextScenarioPage extends WizardPage {
	
	private ISelection selection;
    private DynamicContext parent;
    private ScenarioDef scenario;
    private List scenarios;

    /**
     * The selection contains a dynamic context definition for which we want to include a scenario. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public AddDynamicContextScenarioPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("AddDynamicContextScenarioPage.AddDynamicContextScenario")); //$NON-NLS-1$
        setDescription(Messages.getString("AddDynamicContextScenarioPage.PleaseChooseDynamicContextScenario")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.dynamiccontext_adddynamiccontextscenario"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddDynamicContextScenarioPage.PleaseChooseDynamicContextScenario")); //$NON-NLS-1$

        initialize();

        scenarios = new List(container, SWT.MULTI | SWT.BORDER | SWT.SCROLL_LINE);
        scenarios.setItems(getPossibleChildren());

        GridData gd = new GridData(GridData.FILL_BOTH);
        scenarios.setLayoutData(gd);
        scenarios.addSelectionListener(new SelectionListener() {
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
     * @return all possible scenarios that can be included
     */
    private String[] getPossibleChildren() {
    	java.util.List scenarioGroups = null;
    	if (parent != null)
    		scenarioGroups = parent.getUrnspec().getUcmspec().getScenarioGroups();
    	java.util.List possibleChildren = null;
    	
    	if (scenarioGroups != null) {
	    	for (int i = 0; i < scenarioGroups.size(); i++){
	    		if (((ScenarioGroup) scenarioGroups.get(i)).getScenarios().size() > 0) {
	    			if (i == 0)
	    				possibleChildren = ((ScenarioGroup) scenarioGroups.get(i)).getScenarios();
	    			else
	    				possibleChildren.addAll(((ScenarioGroup) scenarioGroups.get(i)).getScenarios());
	    		}
	    			
	    	}
    	}
        
    	ArrayList childrenStrings = new ArrayList();
        for (Iterator iter = possibleChildren.iterator(); iter.hasNext();) {
            ScenarioDef possibleChild = (ScenarioDef) iter.next();
            childrenStrings.add(URNNamingHelper.getName(possibleChild.getGroup())
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
    	java.util.List scenarioGroups = null;
    	if (parent != null)
    		scenarioGroups = parent.getUrnspec().getUcmspec().getScenarioGroups();
    	java.util.List possibleChildren = null;
    	
    	if (scenarioGroups != null) {
    		for (int i = 0; i < scenarioGroups.size(); i++){
	    		if (((ScenarioGroup) scenarioGroups.get(i)).getScenarios().size() > 0) {
	    			if (i == 0)
	    				possibleChildren = ((ScenarioGroup) scenarioGroups.get(i)).getScenarios();
	    			else
	    				possibleChildren.addAll(((ScenarioGroup) scenarioGroups.get(i)).getScenarios());
	    		}
	    			
	    	}
    	}
        if (scenarios.getSelectionIndex() < 0)
            updateStatus(Messages.getString("AddDynamicContextScenarioPage.SelectDynamicContextScenario")); //$NON-NLS-1$
        else {
            scenario = null;
            for (int i = 0; i < scenarios.getSelectionIndices().length; i++) {
                int index = scenarios.getSelectionIndices()[i];
                scenario = (ScenarioDef) possibleChildren.get(index);
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
     * Returns the child scenarios that has been selected.
     * 
     * @return the children
     */
    public ScenarioDef getChildScenario() {
        return scenario;
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
