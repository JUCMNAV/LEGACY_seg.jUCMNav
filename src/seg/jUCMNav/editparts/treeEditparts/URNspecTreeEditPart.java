package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import urn.URNspec;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class URNspecTreeEditPart extends UcmModelElementTreeEditPart {

	/**
	 * @param model
	 */
	public URNspecTreeEditPart(Object model) {
		super(model);
	}

	protected List getModelChildren() {
		ArrayList list = new ArrayList();
		list.add(getURNspec().getUcmspec());
		return list;
	}

	/**
	 * @return
	 */
	private URNspec getURNspec() {
		return (URNspec)getModel();
	}

	protected String getText() {
		return getURNspec().getName();
	}
}
