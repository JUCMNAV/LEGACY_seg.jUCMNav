package seg.jUCMNav.model.commands.transformations;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * This command splits a link and inserts a passed PathNode (EmptyPoint or Responsibility) on the target location.
 * 
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class SplitLinkCommand extends JUCMNavCommand {

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
        this.oldLink=link;
        this.x = x;
        this.y = y;

        
        setLabel("Insert a node on a path");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        previousNode = oldLink.getSource();
        nextNode = oldLink.getTarget();

        newLink = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        
        node.getSucc().add(0,newLink);

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
         * Before
         * ... (previousNode)---[oldLink]---(nextNode)---...
         *   
         * After
         * ... (previousNode)---[oldLink]---(node)---[newLink]---(nextNode)---...
         */

        // relink
        oldLink.setTarget(node);
        newLink.setTarget(nextNode);
        
        // add to model
        diagram.getPathNodes().add(node);
        diagram.getNodeConnections().add(newLink);
        
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        // remove from model
        diagram.getNodeConnections().remove(newLink);
        diagram.getPathNodes().remove(node);

        // unlink
        oldLink.setTarget(nextNode);
        newLink.setTarget(null);

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
        assert diagram!=null : "pre diagram";
        assert node!=null : "pre node";
        assert previousNode!=null : "pre previous node";
        assert nextNode!=null : "pre next node";
        assert newLink!=null : "pre newLink1";
        assert oldLink!=null : "pre oldLink";
        
        assert node.getX()==x && node.getY()==y : "pre node position";
        
        assert diagram.getPathNodes().contains(previousNode) : "pre graph contains previousNode";
        assert diagram.getPathNodes().contains(nextNode) : "pre graph contains nextNode";
        assert !diagram.getPathNodes().contains(node) : "pre graph doesn't contain node";
        assert diagram.getNodeConnections().contains(oldLink) : "pre graph contains oldLink";
        assert !diagram.getNodeConnections().contains(newLink) : "pre graph doesn't contain newLink";

        assert previousNode.getSucc().contains(oldLink) : "pre link1";
        assert oldLink.getTarget() == nextNode : "pre link2";

        assert node.getPred().size()==0 && node.getSucc().size()==1 : "post node 0 in 1 out";
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert diagram!=null : "post diagram";
        assert node!=null : "post node";
        assert previousNode!=null : " postvious node";
        assert nextNode!=null : "post next node";
        assert newLink!=null : "post newLink1";
        assert oldLink!=null : "post oldLink";
        
        assert node.getX()==x && node.getY()==y : "post node position";
        
        assert diagram.getPathNodes().contains(previousNode) : "post graph contains previousNode";
        assert diagram.getPathNodes().contains(nextNode) : "post graph contains nextNode";
        assert diagram.getPathNodes().contains(node) : "post graph contains node";
        assert diagram.getNodeConnections().contains(oldLink) : "post graph contains oldLink";
        assert diagram.getNodeConnections().contains(newLink) : "post graph contains newLink";

        assert previousNode.getSucc().contains(oldLink) : "post link1";
        assert oldLink.getTarget() == node : "post link2";
        assert node.getSucc().get(0) == newLink : "post link3";
        assert newLink.getTarget() == nextNode : "post link4";
        
        assert node.getPred().size()==1 && node.getSucc().size()==1 : "post node 1 in 1 out";
        
        

    }
}