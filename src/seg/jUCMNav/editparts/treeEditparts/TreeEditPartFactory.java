/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
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
	
	protected URNspec urn;

	/**
	 * 
	 */
	public TreeEditPartFactory(URNspec urn) {
		this.urn=urn;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof UCMNavMultiPageEditor)
		    return new OutlineRootEditPart((UCMNavMultiPageEditor)model);
	    else if(model instanceof URNspec)
			return new URNspecTreeEditPart(model);
		else if(model instanceof Map)
			return new MapTreeEditPart((Map)model);
		else if(model instanceof ComponentRef)
			return new ComponentRefTreeEditPart(model);
		else if(model instanceof RespRef)
			return new RespRefTreeEditPart(model);
		else if(model instanceof PathNode)
			return new PathNodeTreeEditPart(model);
		else if(model instanceof String)
			return new LabelTreeEditPart(model, urn);
		else if(model instanceof ComponentElement)
			return new ComponentTreeEditPart(model);
		else if(model instanceof Responsibility)
			return new ResponsibilityTreeEditPart(model);
		else
			return null;
	}

}
