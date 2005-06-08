package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urn.URNspec;

/**
 * This command splits a link and inserts a passed PathNode (EmptyPoint or Responsibility) on the target location.
 * 
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class SplitLinkCommand extends Command implements JUCMNavCommand {

    //  The UCM diagram
    private PathGraph diagram;

    //  The old link where we are inserting
    private NodeConnection oldLink;

    //  The new node we are inserting
    private PathNode node;
    //  The node before the new node
    private PathNode previousNode;
    // The node following the new node
    private PathNode nextNode;
    //  The two new links for the new node
    private NodeConnection newLink;

    private int x, y;

    public SplitLinkCommand(PathGraph pg, PathNode pn, NodeConnection link, int x, int y) {
        this.diagram = pg;
        this.node = pn;
        this.oldLink = link;
        this.x = x;
        this.y = y;

        setLabel(Messages.getString("SplitLinkCommand.insertNodeOnPath")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        previousNode = oldLink.getSource();
        nextNode = oldLink.getTarget();
        URNspec urn = diagram.getMap().getUcmspec().getUrnspec();
        newLink = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        node.getSucc().add(0, newLink);

        node.setX(x);
        node.setY(y);
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        /*
         * Before ... (previousNode)---[oldLink]---(nextNode)---...
         * 
         * After ... (previousNode)---[oldLink]---(node)---[newLink]---(nextNode)---...
         */

        // relink
        oldLink.setTarget(node);
        newLink.setTarget(nextNode);

        // add to model
        diagram.getPathNodes().add(node);
        diagram.getNodeConnections().add(newLink);

        if (node instanceof RespRef) {
            URNspec urn = diagram.getMap().getUcmspec().getUrnspec();
            urn.getUrndef().getResponsibilities().add(((RespRef) node).getRespDef());
        }

        // bind to parent
        node.setCompRef(ParentFinder.findParent(diagram.getMap(), x, y));

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // unbind from parent
        node.setCompRef(null);

        // remove from model
        diagram.getNodeConnections().remove(newLink);
        diagram.getPathNodes().remove(node);

        // unlink
        oldLink.setTarget(nextNode);
        newLink.setTarget(null);

        if (node instanceof RespRef) {
            URNspec urn = diagram.getMap().getUcmspec().getUrnspec();
            urn.getUrndef().getResponsibilities().remove(((RespRef) node).getRespDef());
        }

        testPreConditions();
    }

    /**
     * @return Returns the oldLink.
     */
    public NodeConnection getOldLink() {
        return oldLink;
    }

    /**
     * @param oldLink
     *            The oldLink to set.
     */
    public void setOldLink(NodeConnection oldLink) {
        this.oldLink = oldLink;
    }

    /**
     * @return Returns the diagram.
     */
    public PathGraph getDiagram() {
        return diagram;
    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(PathGraph diagram) {
        this.diagram = diagram;
    }

    /**
     * @return Returns the node.
     */
    public PathNode getNode() {
        return node;
    }

    /**
     * @param node
     *            The node to set.
     */
    public void setNode(PathNode node) {
        this.node = node;
    }

    /**
     * @return Returns the x.
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     *            The x to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return Returns the y.
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     *            The y to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert diagram != null : "pre diagram"; //$NON-NLS-1$
        assert node != null : "pre node"; //$NON-NLS-1$
        assert previousNode != null : "pre previous node"; //$NON-NLS-1$
        assert nextNode != null : "pre next node"; //$NON-NLS-1$
        assert newLink != null : "pre newLink1"; //$NON-NLS-1$
        assert oldLink != null : "pre oldLink"; //$NON-NLS-1$

        assert node.getX() == x && node.getY() == y : "pre node position"; //$NON-NLS-1$

        assert diagram.getPathNodes().contains(previousNode) : "pre graph contains previousNode"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(nextNode) : "pre graph contains nextNode"; //$NON-NLS-1$
        assert !diagram.getPathNodes().contains(node) : "pre graph doesn't contain node"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(oldLink) : "pre graph contains oldLink"; //$NON-NLS-1$
        assert !diagram.getNodeConnections().contains(newLink) : "pre graph doesn't contain newLink"; //$NON-NLS-1$

        assert previousNode.getSucc().contains(oldLink) : "pre link1"; //$NON-NLS-1$
        assert oldLink.getTarget() == nextNode : "pre link2"; //$NON-NLS-1$

        assert node.getPred().size() == 0 && node.getSucc().size() == 1 : "post node 0 in 1 out"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert diagram != null : "post diagram"; //$NON-NLS-1$
        assert node != null : "post node"; //$NON-NLS-1$
        assert previousNode != null : " postvious node"; //$NON-NLS-1$
        assert nextNode != null : "post next node"; //$NON-NLS-1$
        assert newLink != null : "post newLink1"; //$NON-NLS-1$
        assert oldLink != null : "post oldLink"; //$NON-NLS-1$

        assert node.getX() == x && node.getY() == y : "post node position"; //$NON-NLS-1$

        assert diagram.getPathNodes().contains(previousNode) : "post graph contains previousNode"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(nextNode) : "post graph contains nextNode"; //$NON-NLS-1$
        assert diagram.getPathNodes().contains(node) : "post graph contains node"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(oldLink) : "post graph contains oldLink"; //$NON-NLS-1$
        assert diagram.getNodeConnections().contains(newLink) : "post graph contains newLink"; //$NON-NLS-1$

        assert previousNode.getSucc().contains(oldLink) : "post link1"; //$NON-NLS-1$
        assert oldLink.getTarget() == node : "post link2"; //$NON-NLS-1$
        assert node.getSucc().get(0) == newLink : "post link3"; //$NON-NLS-1$
        assert newLink.getTarget() == nextNode : "post link4"; //$NON-NLS-1$

        assert node.getPred().size() == 1 && node.getSucc().size() == 1 : "post node 1 in 1 out"; //$NON-NLS-1$

    }
}