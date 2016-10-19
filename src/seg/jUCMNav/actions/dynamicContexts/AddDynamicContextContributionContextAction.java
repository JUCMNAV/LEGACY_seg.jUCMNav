package seg.jUCMNav.actions.dynamicContexts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextLabelTreeEditPart;
import seg.jUCMNav.views.wizards.dynamicContexts.AddDynamicContextContributionContext;
import urn.dyncontext.DynamicContext;

/**
 * Adds a contribution context to a dynamic context.
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextContributionContextAction extends URNSelectionAction {
	

	public static final String ADDCONTEXTCONTRIBUTIONCONTEXT = "seg.jUCMNav.AddDynamicContextContributionContext"; //$NON-NLS-1$

    protected DynamicContext dyn;
    protected ContributionContext contriContext;

    /**
     * @param part
     */
    public AddDynamicContextContributionContextAction(IWorkbenchPart part) {
        super(part);
        setId(ADDCONTEXTCONTRIBUTIONCONTEXT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucmscen16.gif")); //$NON-NLS-1$
    }

    /**
     * Can add a contribution context if some are left that can be added without causing recursive loops.
     */
    protected boolean calculateEnabled() {
        initDynamicContext();
        int size = 0;
        if (dyn != null){
	        List contriContextGroups = dyn.getUrnspec().getGrlspec().getContributionGroups();
	        
	        if (contriContextGroups != null && contriContextGroups.size() > 0){
		        for (int i=0; i < contriContextGroups.size(); i++){
		        	if (((ContributionContextGroup)contriContextGroups.get(i)).getContribs().size() > 0){
		        		size++;
		        	}
		        }
	        }
        }
        return dyn != null && size > 0 && dyn.getContributionContext() == null;
    }

    /**
     * Allows us to include a contribution context inside a dynamic context even if we have selected an element in the tree 
     * which is not associated to a model element (the multiple folders that contain the children of a dynamic context).
     * 
     */
    protected void initDynamicContext() {
        List list = getSelectedObjects();
        ArrayList list2 = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object object = iter.next();

            if (object instanceof DynamicContextLabelTreeEditPart)
                list2.add(((DynamicContextLabelTreeEditPart) object).getParent());
            else
                list2.add(object);
        }
        SelectionHelper sel = new SelectionHelper(list2);
        switch (sel.getSelectionType()) {
        case SelectionHelper.DYNAMICCONTEXT:
            dyn = sel.getDynamicContext();
            break;
        default:
            dyn = null;
        }
    }

    /**
     * Opens the add contribution context in dynamic context wizard.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        AddDynamicContextContributionContext wizard = new AddDynamicContextContributionContext();

        StructuredSelection selection = new StructuredSelection(dyn);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
