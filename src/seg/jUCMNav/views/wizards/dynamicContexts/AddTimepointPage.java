package seg.jUCMNav.views.wizards.dynamicContexts;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.TimepointGroupTreeEditPart;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urn.dyncontext.TimepointGroup;

/**
 * The page actually containing the code for adding a timepoint.
 * 
 * @author aprajita
 */
public class AddTimepointPage extends WizardPage {
	
	private ISelection selection;
    private TimepointGroup parent;
    private DateTime timepointDate;
    private Date tp;

    /**
     * The selection contains a timepoint group for which we want to add a timepoint. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public AddTimepointPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("AddTimepointPage.AddTimepoint")); //$NON-NLS-1$
        setDescription(Messages.getString("AddTimepointPage.PleaseEnterTimepoint")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.dynamiccontext_addtimepoint"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddTimepointPage.PleaseEnterTimepoint")); //$NON-NLS-1$

        initialize();

        timepointDate = new DateTime(container, SWT.DATE | SWT.DROP_DOWN | SWT.MEDIUM);
        timepointDate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        dialogChanged();
        setControl(container);

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
            if (obj instanceof TimepointGroupTreeEditPart) {
                parent = ((TimepointGroupTreeEditPart) obj).getTimepointGroup();
            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {
    	Calendar tpCal = Calendar.getInstance();
        tpCal.set(timepointDate.getYear(), timepointDate.getMonth(), timepointDate.getDay(), 0, 0, 0);
        tpCal.set(Calendar.MILLISECOND, 0);
        tp = tpCal.getTime();
        updateStatus(null);
        
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
     * Returns the date that has been selected.
     * 
     * @return the children
     */
    public Date getTimepointDate() {
        return tp;
    }

    /**
     * Returns the parent TimepointGroup that was initially given
     * 
     * @return the parent
     */
    public TimepointGroup getParentTimepointGroup() {
        return parent;
    }

}
