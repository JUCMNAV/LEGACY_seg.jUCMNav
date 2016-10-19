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

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextLabelTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.views.wizards.dynamicContexts.IncludeDynamicContext;
import urn.dyncontext.DynamicContext;

/**
 * Includes a dynamic context within another dynamic context.
 * 
 * @author aprajita
 * 
 */
public class IncludeDynamicContextAction extends URNSelectionAction {
	
	public static final String INCLUDEDYNAMICCONTEXT = "seg.jUCMNav.IncludeDynamicContext"; //$NON-NLS-1$

    protected DynamicContext dyn;

    /**
     * @param part
     */
    public IncludeDynamicContextAction(IWorkbenchPart part) {
        super(part);
        setId(INCLUDEDYNAMICCONTEXT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucmscen16.gif")); //$NON-NLS-1$
    }

    /**
     * Can include a dynamic context if there are contexts left that can be added without causing recursive loops.
     */
    protected boolean calculateEnabled() {
        initDynamicContext();
        return dyn != null && DynamicContextsUtils.getPossibleIncludedDynamicContexts(dyn).size() > 0;
    }

    /**
     * Allows us to include a dynamic context inside another one even if we have selected an element in the tree which is not associated to a model element (the
     * multiple folders that contain the children of a dynamic context).
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
     * Opens the include dynamic context wizard.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IncludeDynamicContext wizard = new IncludeDynamicContext();

        StructuredSelection selection = new StructuredSelection(dyn);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
