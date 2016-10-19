package seg.jUCMNav.actions.dynamicContexts;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextLabelTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextTreeEditPart;
import seg.jUCMNav.model.commands.transformations.ReorderDynamicContextChildrenCommand;
import urn.dyncontext.DynamicContext;

/**
 * Lets a user reorder the dynamic context's inherited elements around.
 * 
 * @author aprajita
 */
public class MoveDynamicContextAction extends URNSelectionAction {
	
	public static final String MOVEUPACTION = "seg.jUCMNav.MOVEUPACTION_DYNAMICCONTEXTS"; //$NON-NLS-1$
    public static final String MOVEDOWNACTION = "seg.jUCMNav.MOVEDOWNACTION_DYNAMICCONTEXTS"; //$NON-NLS-1$

    protected DynamicContext dynContext;
    protected EObject obj;
    protected boolean isMoveUp;

    /**
     * @param part
     *            jUCMNav
     * @param isMoveUp
     *            if true, is the move up action, otherwise the move down action.
     */
    public MoveDynamicContextAction(IWorkbenchPart part, boolean isMoveUp) {
        super(part);
        this.isMoveUp = isMoveUp;

        if (this.isMoveUp) {
            setId(MOVEUPACTION);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/move_up.gif")); //$NON-NLS-1$
        } else {
            setId(MOVEDOWNACTION);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/move_down.gif")); //$NON-NLS-1$
        }

    }

    /**
     * Is true if we have a non-inherited included dynamic context
     */
    protected boolean calculateEnabled() {
        initDynamicContext();
        List list = getSelectedObjects();

        return dynContext != null
                && obj != null
                && list.size() > 0
                && (list.get(0) instanceof DynamicContextTreeEditPart && !((DynamicContextTreeEditPart) list.get(0)).isInherited());
    }

    /**
     * Figures out what we want to move.
     * 
     * The parent dynamic context is saved to dynamic context and the object to be moved is saved to obj.
     * 
     */
    protected void initDynamicContext() {
        List list = getSelectedObjects();
        EditPart part = null;
        obj = null;
        if (list.size() > 0 && list.get(0) instanceof EditPart && ((EditPart) list.get(0)).getModel() instanceof EObject) {
            part = (EditPart) list.get(0);
            obj = (EObject) part.getModel();
            if (!(obj instanceof DynamicContext && part.getParent() instanceof DynamicContextLabelTreeEditPart))
                obj = null;
            else if (part.getParent() != null && part.getParent().getParent() != null) {
                dynContext = (DynamicContext) part.getParent().getParent().getModel();
            }
            if (dynContext == null || obj == null)
                return;

            if (this.isMoveUp) {
                if (obj instanceof DynamicContext) {
                    DynamicContext child = (DynamicContext) obj;
                    if (dynContext.getIncludedContexts().indexOf(child) <= 0)
                        obj = null;
                } else
                    obj = null;
            } else {
                if (obj instanceof DynamicContext) {
                    DynamicContext child = (DynamicContext) obj;
                    if (dynContext.getIncludedContexts().indexOf(child) == dynContext.getIncludedContexts().size() - 1)
                        obj = null;
                } else
                    obj = null;
            }

        }
    }

    /**
     * Creates a {@link seg.jUCMNav.model.commands.transformations.ReorderDynamicContextChildrenCommand} to re-order the selected object.
     */
    protected Command getCommand() {
        ReorderDynamicContextChildrenCommand move = new ReorderDynamicContextChildrenCommand(dynContext, obj, isMoveUp);
        return move;
    }

}
