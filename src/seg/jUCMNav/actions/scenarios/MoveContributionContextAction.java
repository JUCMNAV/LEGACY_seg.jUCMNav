package seg.jUCMNav.actions.scenarios;

import grl.ContributionContext;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextLabelTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextTreeEditPart;
import seg.jUCMNav.model.commands.transformations.ReorderContributionContextChildrenCommand;

/**
 * Lets a user reorder the strategy inherited elements around.
 * 
 * @author jkealey
 */
public class MoveContributionContextAction extends URNSelectionAction {

    public static final String MOVEUPACTION = "seg.jUCMNav.MOVEUPACTION_CONTRIBUTIONCONTEXT"; //$NON-NLS-1$
    public static final String MOVEDOWNACTION = "seg.jUCMNav.MOVEDOWNACTION_CONTRIBUTIONCONTEXT"; //$NON-NLS-1$

    protected ContributionContext contrib;
    protected EObject obj;
    protected boolean isMoveUp;

    /**
     * @param part
     *            jUCMNav
     * @param isMoveUp
     *            if true, is the move up action, otherwise the move down action.
     */
    public MoveContributionContextAction(IWorkbenchPart part, boolean isMoveUp) {
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
     * Is true if we have a non-inherited included contribution context
     */
    protected boolean calculateEnabled() {
        initContributionContext();
        List list = getSelectedObjects();

        return contrib != null
                && obj != null
                && list.size() > 0
                && (list.get(0) instanceof ContributionContextTreeEditPart && !((ContributionContextTreeEditPart) list.get(0)).isInherited());
    }

    /**
     * Figures out what we want to move.
     * 
     * The parent contribution context is saved to contrib and the object to be moved is saved to obj.
     * 
     */
    protected void initContributionContext() {
        List list = getSelectedObjects();
        EditPart part = null;
        obj = null;
        if (list.size() > 0 && list.get(0) instanceof EditPart && ((EditPart) list.get(0)).getModel() instanceof EObject) {
            part = (EditPart) list.get(0);
            obj = (EObject) part.getModel();
            if (!(obj instanceof ContributionContext && part.getParent() instanceof ContributionContextLabelTreeEditPart))
                obj = null;
            else if (part.getParent() != null && part.getParent().getParent() != null) {
                contrib = (ContributionContext) part.getParent().getParent().getModel();
            }
            if (contrib == null || obj == null)
                return;

            if (this.isMoveUp) {
                if (obj instanceof ContributionContext) {
                    ContributionContext child = (ContributionContext) obj;
                    if (contrib.getIncludedContexts().indexOf(child) <= 0)
                        obj = null;
                } else
                    obj = null;
            } else {
                if (obj instanceof ContributionContext) {
                    ContributionContext child = (ContributionContext) obj;
                    if (contrib.getIncludedContexts().indexOf(child) == contrib.getIncludedContexts().size() - 1)
                        obj = null;
                } else
                    obj = null;
            }

        }
    }

    /**
     * Creates a {@link seg.jUCMNav.model.commands.transformations.ReorderContributionContextChildrenCommand} to re-order the selected object.
     */
    protected Command getCommand() {
        ReorderContributionContextChildrenCommand move = new ReorderContributionContextChildrenCommand(contrib, obj, isMoveUp);
        return move;
    }

}