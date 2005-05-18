/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import ucm.UCMspec;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Responsibility;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TreeEditPartFactory implements EditPartFactory {
	
	protected Map root;

	/**
	 * 
	 */
	private TreeEditPartFactory() {
		super();
	}
	
	public TreeEditPartFactory(Map root){
		this.root = root;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof URNspec)
			return new URNspecTreeEditPart(model);
		else if(model instanceof UCMspec)
			return new UCMspecTreeEditPart(model);
		else if(model instanceof Map)
			return new MapTreeEditPart((Map)model);
		else if(model instanceof PathNode)
			return new PathNodeTreeEditPart(model, root.getPathGraph());
		else if(model instanceof RespRef)
			return new PathNodeTreeEditPart(model, root.getPathGraph());
		else if(model instanceof ComponentRef)
			return new ComponentRefTreeEditPart(model);
		else if(model instanceof String)
			return new LabelTreeEditPart(model, (URNspec)root.eContainer().eContainer());
		else if(model instanceof ComponentElement)
			return new ComponentTreeEditPart(model);
		else if(model instanceof Responsibility)
			return new UcmModelElementTreeEditPart(model);
		else
			return null;
	}

}
