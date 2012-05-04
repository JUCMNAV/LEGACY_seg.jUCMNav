package seg.jUCMNav.actions.cutcopypaste;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.internal.GEFMessages;
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
import urncore.IURNDiagram;

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
        try {
            DeletionContext.setPerformingPasteAction(true);
            super.run();
        } finally
        {
            DeletionContext.setPerformingPasteAction(false);
        }
    }

}
