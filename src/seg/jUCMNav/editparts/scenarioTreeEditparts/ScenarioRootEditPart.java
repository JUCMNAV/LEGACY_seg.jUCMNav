package seg.jUCMNav.editparts.scenarioTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * This class is simply here because the root of our scenario (GRLspec)
 * 
 * @author jfroy
 *  
 */
public class ScenarioRootEditPart extends AbstractTreeEditPart {

    public ScenarioRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec());
        return l;
    }

}