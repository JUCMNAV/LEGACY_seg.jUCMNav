package seg.jUCMNav.editparts.concernsTreeEditparts;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.editparts.treeEditparts.ConcernTreeEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import ucm.map.UCMmap;
import urncore.Concern;
import urncore.IURNDiagram;

/**
 * TreeEditPart for a Concern in the Concern Outline
 * 
 * @author gunterm
 */
public class ConcernsConcernTreeEditPart extends ConcernTreeEditPart {

    /**
     * @param model
     *            represents a concern
     */
    public ConcernsConcernTreeEditPart(Concern model) {
        super(model);
    }

    /**
     * @return list of diagrams to which the concern is assigned (alphabetically sorted, maps first, grl graphs second)
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        ArrayList listGrl = new ArrayList();
        for (Iterator iter = getConcern().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram element = (IURNDiagram) iter.next();
            if (element instanceof UCMmap)
                list.add(element);
            if (element instanceof GRLGraph)
                listGrl.add(element);
        }
        Collections.sort(list, new DelegatingElementComparator());
        Collections.sort(listGrl, new DelegatingElementComparator());
        list.addAll(listGrl);
        return list;
    }

    /**
     * @return the concern being represented
     */
    protected Concern getConcern() {
        return ((Concern) getModel());
    }

}