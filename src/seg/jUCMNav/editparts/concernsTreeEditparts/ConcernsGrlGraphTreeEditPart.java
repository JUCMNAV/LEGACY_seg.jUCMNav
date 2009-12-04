package seg.jUCMNav.editparts.concernsTreeEditparts;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.editparts.treeEditparts.GrlGraphTreeEditPart;

/**
 * TreeEditPart for a GRL graph in the Concern outline
 * 
 * @author gunterm
 */
public class ConcernsGrlGraphTreeEditPart extends GrlGraphTreeEditPart {

    /**
     * @param model
     *            represents a GRL graph
     */
    public ConcernsGrlGraphTreeEditPart(GRLGraph model) {
        super(model);
    }

    /**
     * @return an empty list
     * @see seg.jUCMNav.editparts.treeEditparts.GrlGraphTreeEditPart#getModelChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        // TODO CONCERNS: list the pointcut graphs
        return list;
    }

}