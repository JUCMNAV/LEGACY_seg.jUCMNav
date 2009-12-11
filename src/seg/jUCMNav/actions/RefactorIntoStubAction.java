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
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urncore.URNmodelElement;

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
                    PathNode pn = ((PathNode) part.getModel());
                    originalMap = (UCMmap) pn.getDiagram();
                    selection.add(pn);
                    
                    // add what is bound to connect.
                    if (pn instanceof Connect) {
                        Connect connect = (Connect) pn;
                        addConnectedElements(selection, connect);
                    } else {
                        addIndirectlyConnectedElements(selection, pn);
                    }
                }
            } else if (object instanceof ComponentRefEditPart) {
                ComponentRefEditPart part = (ComponentRefEditPart) object;
                selection.add(part.getModel());
            }
        }
        return canExec;
    }
    
    private void addIndirectlyConnectedElements(Vector selectedModelElements, PathNode pn) {
        if (pn instanceof StartPoint || pn instanceof WaitingPlace) {
            for (Iterator iterator2 = pn.getPred().iterator(); iterator2.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator2.next();
                if (nc.getSource() instanceof Connect)
                    addConnectedElements(selectedModelElements, (Connect) nc.getSource());
            }
        } else if (pn instanceof EndPoint || pn instanceof EmptyPoint) {
            for (Iterator iterator2 = pn.getSucc().iterator(); iterator2.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator2.next();
                if (nc.getTarget() instanceof Connect)
                    addConnectedElements(selectedModelElements, (Connect) nc.getTarget());
            }
        }
    }

    private void addConnectedElements(Vector selectedModelElements, Connect connect) {
        if (!selectedModelElements.contains(connect))
            selectedModelElements.add(connect);

        for (Iterator iterator2 = connect.getPred().iterator(); iterator2.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator2.next();
            if (nc.getSource() instanceof URNmodelElement && !selectedModelElements.contains(nc.getSource())) {
                URNmodelElement element2 = (URNmodelElement) nc.getSource();
                selectedModelElements.add(element2);
            }
        }

        for (Iterator iterator2 = connect.getSucc().iterator(); iterator2.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator2.next();
            if (nc.getTarget() instanceof URNmodelElement && !selectedModelElements.contains(nc.getTarget())) {
                URNmodelElement element2 = (URNmodelElement) nc.getTarget();
                selectedModelElements.add(element2);
            }
        }
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