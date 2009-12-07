package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.commands.transformations.RefactorIntoStubCommand;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * Extracts the selection into another map and includes a stub.
 * 
 * @author jkealey
 */
public class RefactorIntoStubAction extends URNSelectionAction {

    public static final String REFACTORINTOSTUB = "seg.jUCMNav.RefactorIntoStub"; //$NON-NLS-1$

    protected UCMmap originalMap;

    /**
     * @param part
     */
    public RefactorIntoStubAction(IWorkbenchPart part) {
        super(part);
        setId(REFACTORINTOSTUB);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Stub16.gif")); //$NON-NLS-1$
    }

    protected boolean calculateEnabled() {
        if (getSelectedObjects().isEmpty())
            return false;
        List parts = getSelectedObjects();

        Vector selection = new Vector();
        boolean canExec = buildSelection(parts, selection);

        return canExec;
    }

    /**
     * Can execute if you have not selected any start/end/connect.
     * 
     * @param parts
     * @param selection
     * @return
     */
    private boolean buildSelection(List parts, Vector selection) {
        boolean canExec = false;

        for (Iterator iterator = parts.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            if (object instanceof PathNodeEditPart) {
                PathNodeEditPart part = (PathNodeEditPart) object;
                if (part.getModel() == null
                /* || ((part.getModel() instanceof StartPoint) || part.getModel() instanceof EndPoint || part.getModel() instanceof Connect) */) {
                    return false;
                } else {
                    if (!(part.getModel() instanceof StartPoint || part.getModel() instanceof EndPoint || part.getModel() instanceof Connect))
                        canExec = true;
                    originalMap = (UCMmap) ((PathNode) part.getModel()).getDiagram();
                    selection.add(part.getModel());
                }
            } else if (object instanceof ComponentRefEditPart) {
                ComponentRefEditPart part = (ComponentRefEditPart) object;
                selection.add(part.getModel());
            }
        }
        return canExec;
    }

    protected Command getCommand() {

        Vector selection = new Vector();
        buildSelection(getSelectedObjects(), selection);

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return new RefactorIntoStubCommand(sel.getUrnspec(), selection);
    }

    protected void autoDirectEdit(Command cmd) {
        if (originalMap != null) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) this.getWorkbenchPart().getSite().getPage().getActiveEditor();
            editor.setActivePage(originalMap);
            originalMap = null;
        }

        super.autoDirectEdit(cmd);

    }

}