package seg.jUCMNav.model.commands.changeConstraints;

import grl.LinkRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
import urncore.IURNConnection;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * This command is used to resize/move Nodes. Is a compound command because might move connected elements.
 * 
 * @author jkealey
 * 
 */
public class SetConstraintGrlNodeCommand extends CompoundCommand {
    protected IURNNode node;
    protected int x, y, height, width;
    protected int oldX, oldY, oldHeight, oldWidth;
    protected boolean multipleNodeMoved;
    protected boolean addedSize = false;

    public SetConstraintGrlNodeCommand(IURNNode node, int x, int y, boolean multipleNodeMoved) {
        this(node, x, y, -1, -1, multipleNodeMoved);
    }

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
    public SetConstraintGrlNodeCommand(IURNNode node, int x, int y, int width, int height, boolean multipleNodeMoved) {
        this.node = node;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    protected void changeDimension(int width, int height) {
        if (width > 0 && height > 0) {
        	URNmodelElement thenode = (URNmodelElement) node;
            MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(), thenode, MetadataHelper.WIDTH, (new Integer(width)).toString()); //$NON-NLS-1$
            MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(), thenode, MetadataHelper.HEIGHT, (new Integer(height)).toString()); //$NON-NLS-1$
        }
    }

    @Override
    public void execute() {
        this.oldX = node.getX();
        this.oldY = node.getY();
        
        String _width = MetadataHelper.getMetaData((URNmodelElement) node, MetadataHelper.WIDTH); //$NON-NLS-1$
        String _height = MetadataHelper.getMetaData((URNmodelElement) node,  MetadataHelper.HEIGHT); //$NON-NLS-1$
        
        if(_width == null || _height == null)
            addedSize = true;
        
        this.oldWidth = (new Integer(_width != null ? _width : "0")).intValue(); //$NON-NLS-1$
        this.oldHeight = (new Integer(_height != null ? _height : "0")).intValue(); //$NON-NLS-1$
        
        changeDimension(width, height);

        super.execute();
    }

    @Override
    public void redo() {
        changeDimension(width, height);
        
        super.redo();
    }

    @Override
    public void undo() {
        if(!addedSize)
            changeDimension(oldWidth, oldHeight);
        else {
            MetadataHelper.removeMetaData((URNmodelElement) node,  MetadataHelper.WIDTH); //$NON-NLS-1$
            MetadataHelper.removeMetaData((URNmodelElement) node,  MetadataHelper.HEIGHT); //$NON-NLS-1$
        }

        super.undo();
    }
}