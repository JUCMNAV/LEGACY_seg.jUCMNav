package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.Map;
import ucm.map.PathNode;

/**
 * Created on 16-May-2005
 * 
 * @author jkealey
 *  
 */
public class TrimEmptyNodeCommand extends CompoundCommand implements JUCMNavCommand {

    private Vector toRemove;
    
    public TrimEmptyNodeCommand(Map map) {
        /*
         * Stack pathStack = new Stack();
         * 
         * for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) { PathNode pn = (PathNode) iter.next();
         * 
         * if (pn instanceof StartPoint && pn.getPred().size()==0) { pathStack.add(pn.getSucc()); } }
         * 
         * while (pathStack.size()>0) { NodeConnection nc = (NodeConnection) pathStack.pop(); PathNode target = nc.getTarget();
         * 
         * for (Iterator iter = target.getSucc().iterator(); iter.hasNext();) { pathStack.push(iter.next()); } }
         */

        toRemove = new Vector();
        
        for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
            ComponentRef compRef = (ComponentRef) iter.next();
            for (Iterator iter2 = compRef.getPathNodes().iterator(); iter2.hasNext();) {
                PathNode pn = (PathNode) iter2.next();
                if (pn instanceof EmptyPoint) {
                    deleteIfPossible(pn);
                }
            }
        }

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof EmptyPoint) {
                deleteIfPossible(pn);
            }
        }

    }

    /**
     * @param pn
     */
    private void deleteIfPossible(PathNode pn) {
        if (!toRemove.contains(pn) && pn.getLabel() == null && pn.getName().equals(URNNamingHelper.getPrefix(EmptyPoint.class))) {
            add(new DeleteNodeCommand(pn));
            toRemove.add(pn);
/*
            if (pn.getCompRef() == null) {
                add(new DeleteNodeCommand(pn));
                toRemove.add(pn);
            } else if (pn.getSucc().size() == 1 && pn.getPred().size() == 1) {
                PathNode pred = ((NodeConnection) pn.getPred().get(0)).getSource();
                PathNode succ = ((NodeConnection) pn.getSucc().get(0)).getTarget();
                if ((!toRemove.contains(pred) && pred.getCompRef() == pn.getCompRef()) || (!toRemove.contains(succ) && succ.getCompRef() == pn.getCompRef())) {
                    add(new DeleteNodeCommand(pn));
                    toRemove.add(pn);
                }

            }*/
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // TODO Auto-generated method stub

    }

}