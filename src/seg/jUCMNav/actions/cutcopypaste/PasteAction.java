package seg.jUCMNav.actions.cutcopypaste;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.cutcopypaste.PasteCommand;
import seg.jUCMNav.model.commands.delete.DeletionContext;
import ucm.map.NodeConnection;
import urn.URNspec;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;

public class PasteAction extends URNSelectionAction {
    protected UCMNavMultiPageEditor editor;

    public PasteAction(UCMNavMultiPageEditor part) {
        super(part);
        this.editor = part;
        setId(ActionFactory.PASTE.getId());
        setText(GEFMessages.PasteAction_Label);
        ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
        setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
        setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
    }

    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        EObject ep = getSelectedInsertionPoint();
        IURNDiagram targetMap = sel.getMap();
        if (targetMap == null)
            targetMap = sel.getGrlgraph();
        URNspec targetUrn = sel.getUrnspec();
        Point nodeConnectionMiddle = null;
        if (ep instanceof NodeConnection)
            nodeConnectionMiddle = sel.getNodeconnectionMiddle();

        org.eclipse.swt.graphics.Point pos = Display.getCurrent().getCursorLocation();
        if (editor.getCurrentPage() != null)
            pos = editor.getCurrentPage().getGraphicalViewer().getControl().toControl(pos);
        Point cursorPosition = new Point(pos.x, pos.y); // avoid type conflicts in lower library

        return new PasteCommand(ep, targetUrn, targetMap, nodeConnectionMiddle, cursorPosition);
    }

    protected EObject getSelectedInsertionPoint() {
        if (getSelectedObjects().size() == 1) {
            SelectionHelper sel = new SelectionHelper(getSelectedObjects());

            switch (sel.getSelectionType()) {
            case SelectionHelper.EMPTYPOINT:
                return sel.getEmptypoint();
            case SelectionHelper.DIRECTIONARROW:
                return sel.getDirectionarrow();
            case SelectionHelper.NODECONNECTION:
                return sel.getNodeconnection();
            case SelectionHelper.GRLGRAPH:
            case SelectionHelper.ACTORREF:
                return sel.getGrlgraph();
            case SelectionHelper.COMPONENTREF:
            case SelectionHelper.MAP:
                return sel.getMap();
            case SelectionHelper.SCENARIOGROUP:
                return sel.getScenarioGroup();
            case SelectionHelper.EVALUATIONGROUP:
                return sel.getStrategiesGroup();
            case SelectionHelper.CONTRIBUTIONCONTEXTGROUP:
                return sel.getContributionContextGroup();

            }

            return sel.getUrnspec();
        }

        return null;
    }

    public void run() {
        // bug 868: track existing content on diagram
        HashMap points = new HashMap();
        IURNDiagram map = null;
        if (getSelectedInsertionPoint() instanceof IURNDiagram) {
            map = (IURNDiagram) getSelectedInsertionPoint();
            for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                IURNNode node = (IURNNode) iterator.next();
                points.put(node, node);
            }
            for (Iterator iterator = map.getContRefs().iterator(); iterator.hasNext();) {
                IURNContainerRef ref = (IURNContainerRef) iterator.next();
                points.put(ref, ref);
            }
        }

        // actually execute the paste.
        try {

            DeletionContext.setPerformingPasteAction(true);
            super.run();
        } finally {
            DeletionContext.setPerformingPasteAction(false);
        }

        // bug 868: figure out what was added.
        Vector toSelect = new Vector();
        if (map != null) {
            for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                IURNNode node = (IURNNode) iterator.next();
                if (!points.containsKey(node))
                    toSelect.add(node);
            }
            for (Iterator iterator = map.getContRefs().iterator(); iterator.hasNext();) {
                IURNContainerRef ref = (IURNContainerRef) iterator.next();
                if (!points.containsKey(ref))
                    toSelect.add(ref);
            }
        }

        // bug 868: actually select what was added
        if (editor.getCurrentPage() != null) {
            GraphicalViewer viewer = editor.getCurrentPage().getGraphicalViewer();
            Map registry = viewer.getEditPartRegistry();

            Vector list = new Vector(); // edit parts.
            for (Iterator iterator = toSelect.iterator(); iterator.hasNext();) {
                EObject o = (EObject) iterator.next();
                Object ep = registry.get(o);
                if (ep != null)
                    list.add(ep);

            }
            StructuredSelection sel = new StructuredSelection(list);
            viewer.setSelection(sel);
        }

    }

}
