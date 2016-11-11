package seg.jUCMNav.actions.dynamicContexts;

import java.util.Iterator;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.ChangeTreeEditPart;
import seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeCustomDialog;
import seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeEditor;

/**
 * Manages Change.
 * 
 * @author aprajita
 * 
 */
public class ManageChangeAction extends URNSelectionAction {
	
	public static final String MANAGECHANGEACTION = "seg.jUCMNav.ManageChangeAction"; //$NON-NLS-1$


    /**
     * Opens the Manage Change editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public ManageChangeAction(IWorkbenchPart part) {
        super(part);
        
        setId(MANAGECHANGEACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Change.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected an element capable of adding Change.
     */
    protected boolean calculateEnabled() {
    	
    	int countElt = 0;
    	int countChange = 0;
    	for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof LinkRefEditPart) && !(obj instanceof IntentionalElementEditPart) &&
            		!(obj instanceof ActorRefEditPart) && !(obj instanceof ChangeTreeEditPart))
                return false;
            else if ((obj instanceof LinkRefEditPart) || (obj instanceof IntentionalElementEditPart) ||
            		(obj instanceof ActorRefEditPart))
                countElt += 1;
            else if (obj instanceof ChangeTreeEditPart)
            	countChange += 1;
        }
    	
    	//If more than one IntentionalElement/Link/Actor are selected, then this 
    	//action should be disabled.
        if (countElt > 1 || countChange > 1) 
        	return false;
        
        return true;
    }

    /**
     * Opens the Manage Change editor.
     * 
     * @see seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeEditor
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
    	SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ManageChangeEditor wizard = new ManageChangeEditor(sel.getUrnspec());
        StructuredSelection selection = new StructuredSelection(sel.getSelection());
        wizard.init(PlatformUI.getWorkbench(), selection);
        ManageChangeCustomDialog dialog = new ManageChangeCustomDialog(shell, wizard);
        dialog.open();

    }
   

}
