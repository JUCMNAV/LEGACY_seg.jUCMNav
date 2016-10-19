package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.RootEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.UrnAbstractTreeEditPart;

/**
 * This class is simply here because the root of our dynamic context is already used.
 * 
 * @author aprajita
 * 
 */
public class DynamicContextRootEditPart extends UrnAbstractTreeEditPart {
	
	 /**
     * 
     * @param editor
     */
    public DynamicContextRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();

        l.add(((UCMNavMultiPageEditor) getModel()).getModel());
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getTimepointGroups());
        
        return l;
    }

    /**
     * @see org.eclipse.gef.EditPart#getRoot()
     */
    public RootEditPart getRoot() {
        if (getParent() == null)
            return null;
        else
            return getParent().getRoot();
    }

}
