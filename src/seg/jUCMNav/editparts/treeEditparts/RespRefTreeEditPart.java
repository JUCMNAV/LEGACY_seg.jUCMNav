package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.PathGraph;
import ucm.map.RespRef;
import urncore.Responsibility;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class RespRefTreeEditPart extends PathNodeTreeEditPart {

    /**
     * @param model
     */
    public RespRefTreeEditPart(Object model, PathGraph graph) {
        super(model, graph);
    }

    public void activate() {
        super.activate();
        getRespRef().getRespDef().eAdapters().add(this);
    }

    public void deactivate() {
        super.deactivate();
        getRespRef().getRespDef().eAdapters().remove(this);
    }

    protected String getText() {
        Responsibility resp = getRespRef().getRespDef();
        return "ref " + getRespRef().getId() + ": " + resp.getName();
    }

    private RespRef getRespRef() {
        return (RespRef) getModel();
    }

    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage());
        return super.getImage();
    }
}