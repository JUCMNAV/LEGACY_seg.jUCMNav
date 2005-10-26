package seg.jUCMNav.editparts.treeEditparts;

import grl.GRLGraph;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import ucm.map.ComponentRef;
import ucm.map.UCMmap;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Responsibility;

/**
 * The EditPartFactory associated with the outline treeview.
 * 
 * @author TremblaE
 *  
 */
public class TreeEditPartFactory implements EditPartFactory {

    // the urn spec being edited.
    protected URNspec urn;

    /**
     * @param urn
     *            the urnspec to display
     */
    public TreeEditPartFactory(URNspec urn) {
        this.urn = urn;
    }

    /**
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor)
            return new OutlineRootEditPart((UCMNavMultiPageEditor) model);
        else if (model instanceof URNspec)
            return new URNspecTreeEditPart((URNspec)model);
        else if (model instanceof UCMmap)
            return new MapTreeEditPart((UCMmap) model);
        else if (model instanceof ComponentRef)
            return new ComponentRefTreeEditPart((ComponentRef) model);
        else if (model instanceof RespRef)
            return new RespRefTreeEditPart((RespRef) model);
        else if (model instanceof Stub)
            return new StubTreeEditPart((Stub) model);
        else if (model instanceof PathNode)
            return new PathNodeTreeEditPart((PathNode) model);
        else if (model instanceof String)
            return new LabelTreeEditPart(model, urn);
        else if (model instanceof ComponentElement)
            return new ComponentTreeEditPart((ComponentElement) model);
        else if (model instanceof Responsibility)
            return new ResponsibilityTreeEditPart((Responsibility) model);
        else if (model instanceof GRLGraph)
            return new UcmModelElementTreeEditPart((GRLGraph)model);
        else
            return null;
    }

}