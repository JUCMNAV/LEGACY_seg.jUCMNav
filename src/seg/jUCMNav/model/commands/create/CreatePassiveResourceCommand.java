/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.PassiveResource;
import urn.URNspec;
import urncore.Component;

/**
 * This command adds a passive resource
 * 
 * @author jack
 * 
 */
public class CreatePassiveResourceCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private PassiveResource passiveResource;
    private Component component;
    private String name;
    private String multiplicity;
    private String schedPolicy;

    /**
     * @param name
     * @param component
     * @param schedPolicy
     * @param multiplicity
     * 
     */
    public CreatePassiveResourceCommand(URNspec urn, String name, Component component, String multiplicity, String schedPolicy) {
        this.urn = urn;
        this.component = component;
        this.name = name;
        this.multiplicity = multiplicity;
        this.schedPolicy = schedPolicy;
        setLabel(Messages.getString("CreatePassiveResourceCommand.CreatePassiveResource")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (urn != null); // passive resources need not be bound to components
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        passiveResource = (PassiveResource) ModelCreationFactory.getNewObject(urn, PassiveResource.class, 0, null);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getResources().add(passiveResource);
        if (component != null) {
            component.setResource(passiveResource);
            passiveResource.setComponent(component);
        }
        passiveResource.setName(name);
        passiveResource.setMultiplicity(multiplicity);
        passiveResource.setSchedPolicy(schedPolicy);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(passiveResource) : "post passiveResource in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(passiveResource) : "pre passiveResource not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        passiveResource.setName(null);
        passiveResource.setMultiplicity(null);
        passiveResource.setSchedPolicy(null);
        if (passiveResource.getComponent() != null) {
            passiveResource.setComponent(null);
            component.setResource(null);
        }
        urn.getUcmspec().getResources().remove(passiveResource);
        testPreConditions();
    }

    public PassiveResource getVar() {
        return passiveResource;
    }
}
