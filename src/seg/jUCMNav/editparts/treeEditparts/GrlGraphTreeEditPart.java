package seg.jUCMNav.editparts.treeEditparts;

import fm.FeatureDiagram;
import grl.GRLGraph;
import grl.GRLNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.GRLGraphComponentEditPolicy;
import seg.jUCMNav.model.util.DelegatingElementComparator;


/**
 * Tree edit part for the GrlGraph
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class GrlGraphTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the GrlGraph
     */
    public GrlGraphTreeEditPart(GRLGraph model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new GRLGraphComponentEditPolicy());
    }

    /**
     * Returns list of actorref, beliefs and intentionalElementRef, kpiInformationElementRef sorted by type and name using EObjectClassNameComparator
     * 
     * @see org.eclipse.gef.EditPart#getChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        GRLGraph graph = getGraph();
        list.addAll(graph.getContRefs());
        Vector v = new Vector();
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode element = (GRLNode) iter.next();
            v.add(element);
        }
        list.addAll(v);

        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * 
     * @return the GRLGraph
     */
    public GRLGraph getGraph() {
        return ((GRLGraph) getModel());
    }

    /**
     * Returns an icon representing a GrlGraph.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
        	if (getGraph() instanceof FeatureDiagram)
        		// FM icon
        		setImage((JUCMNavPlugin.getImage("icons/fmd16.gif"))); //$NON-NLS-1$
        	else 
        		// GRL icon
    			setImage((JUCMNavPlugin.getImage("icons/grl16.gif"))); //$NON-NLS-1$
        }
        return super.getImage();
    }

}
