package seg.jUCMNav.editparts.strategyTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * This class is simply here because the root of our strategy (GRLspec) / scenario (UCMspec)
 * 
 * @author jfroy, jkealey
 *  
 */
public class StrategyRootEditPart extends AbstractTreeEditPart {

    public StrategyRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getUcmspec());
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec());
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getUcmspec().getVariables());
        return l;
    }

    /**
     * @see org.eclipse.gef.EditPart#getRoot()
     */
    public RootEditPart getRoot() {
    	if (getParent()==null)
    		return null;
    	else
    		return getParent().getRoot();
    }
}