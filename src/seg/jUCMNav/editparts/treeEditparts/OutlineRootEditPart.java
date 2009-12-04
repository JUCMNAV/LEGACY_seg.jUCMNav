package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

/**
 * This class is simply here because the root of our tree (URNspec) doesn't appear in the outline.
 * 
 * @author jkealey
 * 
 */
public class OutlineRootEditPart extends UrnAbstractTreeEditPart {

    public OutlineRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();
        l.add(((UCMNavMultiPageEditor) getModel()).getModel());
        return l;
    }

    public URNspec getURNSpec() {
        return (URNspec) getModelChildren().get(0);
    }

}