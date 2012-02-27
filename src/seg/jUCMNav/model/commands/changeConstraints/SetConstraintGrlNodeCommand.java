package seg.jUCMNav.model.commands.changeConstraints;

import grl.LinkRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urncore.IURNConnection;
import urncore.IURNNode;

/**
 * This command is used to resize/move Nodes. Is a compound command because might move connected elements.
 * 
 * @author jkealey
 * 
 */
public class SetConstraintGrlNodeCommand extends CompoundCommand {
    protected IURNNode node;
    protected int x, y;
    protected boolean multipleNodeMoved;
    /**
     * 
     * @param node
     *            The SpecificationNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     * @param multipleNodeMoved
     *            Multiple nodes are moved in the UI, handle moving bendpoints differently.
     */
    public SetConstraintGrlNodeCommand(IURNNode node, int x, int y, boolean multipleNodeMoved) {
        this.node = node;
        this.x = x;
        this.y = y;
        this.multipleNodeMoved = multipleNodeMoved;
        
        setLabel(Messages.getString("SetConstraintCommand.setNodeConstraints")); //$NON-NLS-1$

        add(new SetConstraintCommand(node, x, y));
        
        addChildCommands();
    }
    
    private void addChildCommands() {
        List commands = new ArrayList();
        
        // For each connections reaching out of this node, move the bendpoints to reflect the node movement.
        // and add the commands to the compound command.
        commands.addAll(getMoveBendpointCommandForConnections(node.getPred(), false));
        commands.addAll(getMoveBendpointCommandForConnections(node.getSucc(), true));
        
        for (Iterator i = commands.iterator(); i.hasNext();)
            add((Command) i.next());
    }

    protected List getMoveBendpointCommandForConnections(EList list, boolean isNext) {
        List commands = new ArrayList();
        for (Iterator i = list.iterator(); i.hasNext();) {
            IURNConnection type = (IURNConnection) i.next();

            if (type instanceof LinkRef)
                commands.addAll(getMoveBendpointCommands((LinkRef) type, isNext));
        }
        return commands;
    }

    protected List getMoveBendpointCommands(LinkRef ref, boolean isNext) {
        int deltaX, deltaY;
        IURNNode targetNode = isNext ? ref.getSource() : ref.getTarget();

        deltaX = x - targetNode.getX();
        deltaY = y - targetNode.getY();

        return MoveLinkRefBendpointCommand.getMoveLinkRefBendpointCommand(ref, deltaX, deltaY, multipleNodeMoved, isNext);
    }
}