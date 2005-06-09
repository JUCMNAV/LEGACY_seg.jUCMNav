package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.figures.StubFigure;
import seg.jUCMNav.views.property.StubPropertySource;
import ucm.UcmPackage;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.Stub;

/**
 * Created 2005-05-11
 * 
 * @author Etienne Tremblay
 */
public class StubEditPart extends PathNodeEditPart {

    private StubFigure figure;

    /**
     *  
     */
    public StubEditPart(PathNode model, PathGraph diagram) {
        super(model, diagram);
    }

    public void refreshVisuals() {
        Stub stub = (Stub) getNode();
        figure.setDynamic(stub.isDynamic());

        super.refreshVisuals();
    }

    protected IFigure createFigure() {
        Stub stub = (Stub) getModel();
        figure = new StubFigure(stub.isDynamic());
        return figure;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType() == REQ_OPEN) {
            Stub stub = (Stub) getModel();
            if (stub.getBindings().size() > 0) {
                Map map = ((PluginBinding) stub.getBindings().get(0)).getPlugin();
                if (map != null)
                    ((ConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor().setActivePage(map);
            }
        }
    }

    protected IPropertySource getPropertySource() {
        CommandStack cmdStack = getViewer().getEditDomain().getCommandStack();
        propertySource = new StubPropertySource((EObject) getModel(), cmdStack);
        return propertySource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.PathNodeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (featureId) {
        case MapPackage.STUB__PRED:
            for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshNodeConnection(nc);
            }
        case MapPackage.STUB__SUCC:
            for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshNodeConnection(nc);
            }
        }

        super.notifyChanged(notification);
    }

    /**
     * @param nc
     */
    private void refreshNodeConnection(NodeConnection nc) {
        if (getRoot().getViewer().getEditPartRegistry().get(nc) != null) {
            ((NodeConnectionEditPart) getRoot().getViewer().getEditPartRegistry().get(nc)).refreshVisuals();
        }
    }

    public void refreshInOuts() {
        for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            refreshNodeConnection(nc);
        }

        for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            refreshNodeConnection(nc);
        }
    }

}