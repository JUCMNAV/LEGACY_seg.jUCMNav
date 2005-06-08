package seg.jUCMNav.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.StubBindingsDialog;
import ucm.map.Stub;

/**
 * Created 2005-06-05
 * 
 * @author Etienne Tremblay
 */
public class EditStubPluginsAction extends SelectionAction {
	
	public static final String EDITSTUBPLUGINS = "EditStubPluginsAction"; //$NON-NLS-1$
	
	private Stub stub;

	/**
	 * @param part
	 */
	public EditStubPluginsAction(IWorkbenchPart part) {
		super(part);
		setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STUB:
        	stub = sel.getStub();
        	return true;
        }
		return false;
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
    	Shell shell = new Shell();
		StubBindingsDialog d = new StubBindingsDialog(shell, getCommandStack());
		d.open(stub);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#getId()
     */
    public String getId() {
        return EDITSTUBPLUGINS;
    }

}
