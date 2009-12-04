/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.LinkRef;
import grl.LinkRefBendpoint;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Command to add a newBendpoint to the linkref in GRL
 * 
 * @author Jean-François Roy
 * 
 */
public class AddLinkRefBendpointCommand extends Command implements JUCMNavCommand {

    LinkRef linkref;
    int x, y;
    int index; // Index where to insert the new bendpoint in the linkref to bendpoint association
    LinkRefBendpoint bendpoint;

    /**
     * Constructor
     */
    public AddLinkRefBendpointCommand(LinkRef link, int index) {
        this.linkref = link;
        this.index = index;
        x = 0;
        y = 0;
        setLabel(Messages.getString("AddLinkRefBendpointCommand.addLinkRefBendpoint")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        bendpoint = (LinkRefBendpoint) ModelCreationFactory.getNewObject(linkref.getDiagram().getUrndefinition().getUrnspec(), LinkRefBendpoint.class);
        bendpoint.setX(x);
        bendpoint.setY(y);
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        linkref.getBendpoints().add(index, bendpoint);
        testPostConditions();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert linkref != null : "pre LinkRef"; //$NON-NLS-1$
        assert bendpoint != null : "pre newBendpoint"; //$NON-NLS-1$
        assert index <= linkref.getBendpoints().size() : "pre index"; //$NON-NLS-1$
        assert bendpoint.getLinkref() != linkref : "pre newBendpoint linkref"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert linkref != null : "post LinkRef"; //$NON-NLS-1$
        assert bendpoint != null : "post newBendpoint"; //$NON-NLS-1$
        assert index < linkref.getBendpoints().size() : "post index"; //$NON-NLS-1$
        assert bendpoint.getLinkref() == linkref : "post newBendpoint linkref"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        linkref.getBendpoints().remove(bendpoint);

        testPreConditions();
    }
}
