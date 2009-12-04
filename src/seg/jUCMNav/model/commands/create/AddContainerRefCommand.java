package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.UCMmodelElement;

/**
 * This command adds a ContainerRef and its corresponding definition to the model. Its definition must not be referenced by any other classes.
 * 
 * Note: this command does not bind a component to its parent. use the SetConstraintComponentRef command instead.
 * 
 * @author jkealey
 * 
 */
public class AddContainerRefCommand extends Command implements JUCMNavCommand, ICreateElementCommand {

    // the component reference
    private IURNContainerRef compRef;

    // the diagram it has to be added to.
    private IURNDiagram diagram;

    // does the definition already exist.
    private boolean bDefAlreadyExists;
    private IURNContainer existingDef;

    /**
     * 
     * @param m
     *            The diagram to which to add the ComponentRef
     * @param cr
     *            The ComponentRef
     */
    public AddContainerRefCommand(IURNDiagram m, IURNContainerRef cr) {
        this.diagram = m;
        this.compRef = cr;
        if (compRef instanceof UCMmodelElement) {
            setLabel(Messages.getString("AddContainerRefCommand.createComp")); //$NON-NLS-1$
        } else {
            setLabel(Messages.getString("AddContainerRefCommand.createActor")); //$NON-NLS-1$        
        }
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        existingDef = compRef.getContDef();

        if (compRef instanceof UCMmodelElement) {
            bDefAlreadyExists = diagram.getUrndefinition().getUrnspec().getUrndef().getComponents().contains(compRef.getContDef());
        } else if (compRef instanceof GRLmodelElement) {
            bDefAlreadyExists = diagram.getUrndefinition().getUrnspec().getGrlspec().getActors().contains(existingDef);
        }

        redo();
    }

    /**
     * @return Returns the comp.
     */
    public IURNContainerRef getComp() {
        return compRef;
    }

    /**
     * @return Returns the diagram.
     */
    public IURNDiagram getMap() {
        return diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        URNspec urnspec = diagram.getUrndefinition().getUrnspec();
        // add the component definition to the model
        if (!bDefAlreadyExists && compRef instanceof UCMmodelElement) {
            urnspec.getUrndef().getComponents().add(compRef.getContDef());
        } else if (!bDefAlreadyExists && compRef instanceof GRLmodelElement) {
            urnspec.getGrlspec().getActors().add(compRef.getContDef());
        } else if (bDefAlreadyExists)
            compRef.setContDef(existingDef);

        // add the component reference to the model
        diagram.getContRefs().add(compRef);

        testPostConditions();
    }

    /**
     * @param comp
     *            The comp to set.
     */
    public void setComp(IURNContainerRef comp) {
        this.compRef = comp;
    }

    /**
     * @param map
     *            The diagram to set.
     */
    public void setMap(IURNDiagram map) {
        this.diagram = map;
    }

    /**
     * Make sure the model is in a consistent state for undo().
     * 
     */
    public void testPostConditions() {
        assert compRef != null : "post compRef"; //$NON-NLS-1$
        assert compRef.getContDef() != null : "post compDef"; //$NON-NLS-1$
        assert diagram != null : "post diagram"; //$NON-NLS-1$

        assert diagram.getContRefs().contains(compRef) : "post compRef in diagram"; //$NON-NLS-1$
        if (compRef instanceof UCMmodelElement) {
            assert diagram.getUrndefinition().getUrnspec().getUrndef().getComponents().contains(compRef.getContDef()) : "post compDef in model"; //$NON-NLS-1$
        } else if (compRef instanceof GRLmodelElement) {
            assert diagram.getUrndefinition().getUrnspec().getGrlspec().getActors().contains(compRef.getContDef()) : "post compDef in model"; //$NON-NLS-1$
        }
    }

    /**
     * Make sure the model is in a consistent state for redo().
     * 
     */
    public void testPreConditions() {
        assert compRef != null : "pre compRef"; //$NON-NLS-1$
        assert bDefAlreadyExists || compRef.getContDef() != null : "pre compDef"; //$NON-NLS-1$
        assert diagram != null : "pre Map"; //$NON-NLS-1$

        assert !diagram.getContRefs().contains(compRef) : "pre compRef not in diagram"; //$NON-NLS-1$

        // make sure this is a new component definition.
        // if not, our undo() will remove it, breaking code.
        if (compRef instanceof UCMmodelElement) {
            assert bDefAlreadyExists || !diagram.getUrndefinition().getUrnspec().getUrndef().getComponents().contains(compRef.getContDef()) : "pre compDef not in model"; //$NON-NLS-1$
        } else if (compRef instanceof GRLmodelElement) {
            assert bDefAlreadyExists || !diagram.getUrndefinition().getUrnspec().getGrlspec().getActors().contains(compRef.getContDef()) : "pre compDef not in model"; //$NON-NLS-1$
        }
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the component reference from the model
        diagram.getContRefs().remove(compRef);

        URNspec urnspec = diagram.getUrndefinition().getUrnspec();
        if (!bDefAlreadyExists && compRef instanceof UCMmodelElement) {
            // remove the component definition from the model
            urnspec.getUrndef().getComponents().remove(compRef.getContDef());
        } else if (!bDefAlreadyExists && compRef instanceof GRLmodelElement) {
            urnspec.getGrlspec().getActors().remove(compRef.getContDef());
        } else if (bDefAlreadyExists)
            compRef.setContDef(null);

        testPreConditions();
    }

    public Object getNewModelElement() {
        return compRef;
    }

}