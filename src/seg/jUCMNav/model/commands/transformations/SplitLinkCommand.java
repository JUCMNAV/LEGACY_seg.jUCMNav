package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.Connect;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;

/**
 * This command splits a link and inserts a passed PathNode on the target location.
 * 
 * @author Etienne Tremblay
 */
public class SplitLinkCommand extends Command implements JUCMNavCommand, ICreateElementCommand {

    // indicates whether or not the command is to be aborted because it is in a compound command

    private boolean aborted = false;

    // The UCM diagram
    private UCMmap diagram;

    // The old link where we are inserting
    private NodeConnection oldLink;

    // The new node we are inserting
    private PathNode node;

    // The node before the new node
    private PathNode previousNode;

    // The node following the new node
    private PathNode nextNode;

    // The two new links for the new node
    private NodeConnection newLink;

    // the location
    private int x, y;

    private int oldIndex;

    // does the responsibility definition already exist? (don't remove it on undo)
    private boolean bDefAlreadyExists;

    private Responsibility existingDef;

    private Condition outgoingCondition;

    private URNspec urn;

    public Condition getOutgoingCondition() {
        return outgoingCondition;
    }

    public void setOutgoingCondition(Condition outgoingCondition) {
        this.outgoingCondition = outgoingCondition;
    }

    /**
     * @param pg
     *            the pathgraph containing the elements
     * @param pn
     *            the pathnode to add
     * @param link
     *            the nodeconnection to split
     * @param x
     *            insertion location
     * @param y
     *            insertion location
     */
    public SplitLinkCommand(UCMmap pg, PathNode pn, NodeConnection link, int x, int y) {
        this.diagram = pg;
        this.node = pn;
        this.oldLink = link;
        this.x = x;
        this.y = y;
        this.outgoingCondition = null;

        this.urn = diagram.getUrndefinition().getUrnspec();

        setLabel(Messages.getString("SplitLinkCommand.insertNodeOnPath")); //$NON-NLS-1$
    }

    public SplitLinkCommand(UCMmap pg, PathNode pn, NodeConnection link, NodeConnection newLink, int x, int y) {
        this.diagram = pg;
        this.node = pn;
        this.oldLink = link;
        this.x = x;
        this.y = y;
        this.outgoingCondition = null;

        this.urn = diagram.getUrndefinition().getUrnspec();
        this.newLink = newLink;

        setLabel(Messages.getString("SplitLinkCommand.insertNodeOnPath")); //$NON-NLS-1$
    }

    /**
     * @param pg
     *            the pathgraph containing the elements
     * @param pn
     *            the pathnode to add
     * @param link
     *            the nodeconnection to split
     * @param x
     *            insertion location
     * @param y
     *            insertion location
     * @param outgoingCondition
     *            the condition on the outgoing node
     * 
     */
    public SplitLinkCommand(UCMmap pg, PathNode pn, NodeConnection link, int x, int y, Condition outgoingCondition) {
        this.diagram = pg;
        this.node = pn;
        this.oldLink = link;
        this.x = x;
        this.y = y;
        this.outgoingCondition = outgoingCondition;
        setLabel(Messages.getString("SplitLinkCommand.insertNodeOnPath")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        previousNode = (PathNode) oldLink.getSource();
        nextNode = (PathNode) oldLink.getTarget();
        if (previousNode == null || nextNode == null || previousNode.getDiagram() == null || nextNode.getDiagram() == null) {
            aborted = true;
            return;
        }

        if (newLink == null)
            newLink = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        if (outgoingCondition == null && (node instanceof OrFork || node instanceof WaitingPlace || node instanceof Timer || node instanceof FailurePoint)) {
            outgoingCondition = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            // blocking path.
            if (previousNode instanceof Timer)
                outgoingCondition.setExpression("false"); //$NON-NLS-1$
        }

        if (outgoingCondition != null)
            newLink.setCondition(outgoingCondition);

        node.getSucc().add(0, newLink);

        node.setX(x);
        node.setY(y);

        if (node instanceof RespRef) {
            existingDef = ((RespRef) node).getRespDef();
            bDefAlreadyExists = diagram.getUrndefinition().getResponsibilities().contains(existingDef);
        }

        oldIndex = nextNode.getPred().indexOf(oldLink);

        redo();
    }

    /**
     * @return Link not connected to a Connect
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return !(oldLink.getSource() instanceof Connect || oldLink.getTarget() instanceof Connect);
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();
        /*
         * Before ... (previousNode)---[oldLink]---(nextNode)---...
         * 
         * After ... (previousNode)---[oldLink]---(node)---[newLink]---(nextNode)---...
         */

        // relink
        oldLink.setTarget(node);
        // may break connection order, which impacts Connects
        // newLink.setTarget(nextNode);
        nextNode.getPred().add(oldIndex, newLink);

        // add to model
        diagram.getNodes().add(node);
        diagram.getConnections().add(newLink);

        if (node instanceof RespRef && !bDefAlreadyExists) {
            URNspec urn = diagram.getUrndefinition().getUrnspec();
            urn.getUrndef().getResponsibilities().add(((RespRef) node).getRespDef());
        } else if (node instanceof RespRef && bDefAlreadyExists) {
            ((RespRef) node).setRespDef(existingDef);
        }

        // bind to parent
        node.setContRef(ParentFinder.findParent(diagram, x, y));

        // transfer bindings from at the ending of the old link onto the new one.
        newLink.getInBindings().addAll(oldLink.getInBindings());
        oldLink.getInBindings().clear();

        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        // unbind from parent
        node.setContRef(null);

        // remove from model
        diagram.getConnections().remove(newLink);
        diagram.getNodes().remove(node);

        // unlink
        newLink.setTarget(null);
        // may break connection order, which impacts Connects
        // oldLink.setTarget(nextNode);
        nextNode.getPred().add(oldIndex, oldLink);

        if (node instanceof RespRef && !bDefAlreadyExists) {
            URNspec urn = diagram.getUrndefinition().getUrnspec();
            urn.getUrndef().getResponsibilities().remove(((RespRef) node).getRespDef());
        } else if (node instanceof RespRef && bDefAlreadyExists) {
            ((RespRef) node).setRespDef(null);
        }

        // transfer bindings from at the ending of the new link back onto the old one.
        oldLink.getInBindings().addAll(newLink.getInBindings());
        newLink.getInBindings().clear();

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
    public UCMmap getDiagram() {
        return diagram;
    }

    /**
     * @param diagram
     *            The diagram to set.
     */
    public void setDiagram(UCMmap diagram) {
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

    /**
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

        assert diagram.getNodes().contains(previousNode) : "pre graph contains previousNode"; //$NON-NLS-1$
        assert diagram.getNodes().contains(nextNode) : "pre graph contains nextNode"; //$NON-NLS-1$
        assert !diagram.getNodes().contains(node) : "pre graph doesn't contain node"; //$NON-NLS-1$
        assert diagram.getConnections().contains(oldLink) : "pre graph contains oldLink"; //$NON-NLS-1$
        assert !diagram.getConnections().contains(newLink) : "pre graph doesn't contain newLink"; //$NON-NLS-1$

        assert previousNode.getSucc().contains(oldLink) : "pre link1"; //$NON-NLS-1$
        assert oldLink.getTarget() == nextNode : "pre link2"; //$NON-NLS-1$

        assert node.getPred().size() == 0 && node.getSucc().size() == 1 : "post node 0 in 1 out"; //$NON-NLS-1$

    }

    /**
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

        assert diagram.getNodes().contains(previousNode) : "post graph contains previousNode"; //$NON-NLS-1$
        assert diagram.getNodes().contains(nextNode) : "post graph contains nextNode"; //$NON-NLS-1$
        assert diagram.getNodes().contains(node) : "post graph contains node"; //$NON-NLS-1$
        assert diagram.getConnections().contains(oldLink) : "post graph contains oldLink"; //$NON-NLS-1$
        assert diagram.getConnections().contains(newLink) : "post graph contains newLink"; //$NON-NLS-1$

        assert previousNode.getSucc().contains(oldLink) : "post link1"; //$NON-NLS-1$
        assert oldLink.getTarget() == node : "post link2"; //$NON-NLS-1$
        assert node.getSucc().get(0) == newLink : "post link3"; //$NON-NLS-1$
        assert newLink.getTarget() == nextNode : "post link4"; //$NON-NLS-1$

        assert node.getPred().size() == 1 && node.getSucc().size() == 1 : "post node 1 in 1 out"; //$NON-NLS-1$

    }

    public NodeConnection getNewLink() {
        return newLink;
    }

    public Object getNewModelElement() {
        return node;
    }
}