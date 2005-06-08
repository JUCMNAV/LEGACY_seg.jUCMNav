/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.MapComponentEditPolicy;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import ucm.map.Map;
import ucm.map.PathGraph;

/**
 * @author TremblaE
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class MapTreeEditPart extends UcmModelElementTreeEditPart {

    /**
     * @param model
     */
    public MapTreeEditPart(Object model) {
        super(model);
    }

    public void activate() {
        super.activate();
        getMap().getPathGraph().eAdapters().add(this);
    }

    public void deactivate() {
        super.deactivate();
        getMap().getPathGraph().eAdapters().remove(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new MapComponentEditPolicy());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#getChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        Map map = getMap();
        PathGraph graph = map.getPathGraph();
        list.addAll(map.getCompRefs());
        list.addAll(graph.getPathNodes());
        Collections.sort(list, new EObjectClassNameComparator());
        return list;
    }

    /**
     * @return
     */
    private Map getMap() {
        return ((Map) getModel());
    }

    protected Image getImage() {
        if (super.getImage() == null)
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")).createImage()); //$NON-NLS-1$
        return super.getImage();
    }

}