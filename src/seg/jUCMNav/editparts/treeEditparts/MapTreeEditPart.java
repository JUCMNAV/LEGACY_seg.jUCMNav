/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import seg.jUCMNav.editpolicies.layout.MapAndPathGraphXYLayoutEditPolicy;
import ucm.map.Map;
import ucm.map.PathGraph;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#installEditPolicy(java.lang.Object, org.eclipse.gef.EditPolicy)
	 */
	public void installEditPolicy(Object key, EditPolicy editPolicy) {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new MapAndPathGraphXYLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#getChildren()
	 */
	public List getModelChildren() {
		ArrayList list = new ArrayList();
		Map map = getMap();
		PathGraph graph = map.getPathGraph();
		list.addAll(graph.getPathNodes());
		list.addAll(map.getCompRefs());
		return list;
	}

	/**
	 * @return
	 */
	private Map getMap() {
		return ((Map)getModel());
	}
}
