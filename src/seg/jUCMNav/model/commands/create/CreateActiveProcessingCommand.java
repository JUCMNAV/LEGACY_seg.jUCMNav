/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.DeviceKind;
import ucm.performance.ProcessingResource;
import urn.URNspec;
import urncore.Component;

/**
 * This command adds an active processing resource
 * 
 * @author jack
 * 
 */
public class CreateActiveProcessingCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private ProcessingResource processingResource;
    private Component[] components;
    private String opTime;
    private DeviceKind deviceKind;
    private String name;
    private String multiplicity;
    private String schedPolicy;

    /**
     * @param name
     * @param components
     * @param schedPolicyStr
     * @param multiplicityStr
     * 
     */
    public CreateActiveProcessingCommand(URNspec urn, String name, Component[] components, String opTime, DeviceKind deviceKind, String multiplicityStr,
            String schedPolicyStr) {
        this.urn = urn;
        this.components = components;
        this.opTime = opTime;
        this.deviceKind = deviceKind;
        this.name = name;
        this.multiplicity = multiplicityStr;
        this.schedPolicy = schedPolicyStr;
        setLabel(Messages.getString("CreateActiveProcessingCommand.CreateActiveProcessingResource")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (urn != null); // components are not mandatory
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        processingResource = (ProcessingResource) ModelCreationFactory.getNewObject(urn, ProcessingResource.class, 0, null);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getResources().add(processingResource);
        processingResource.setName(name);
        for (int i = 0; i < components.length; i++) {
            Component comp = components[i];
            comp.setHost(processingResource);
            processingResource.getComponents().add(comp);
        }
        processingResource.setKind(deviceKind);
        processingResource.setOpTime(opTime);
        processingResource.setMultiplicity(multiplicity);
        processingResource.setSchedPolicy(schedPolicy);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(processingResource) : "post processingResource in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(processingResource) : "pre passiveResource not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUcmspec().getResources().remove(processingResource);
        for (int i = 0; i < components.length; i++) {
            Component comp = components[i];
            comp.setHost(null);
            processingResource.getComponents().remove(comp);
        }
        processingResource.setKind(null);
        processingResource.setOpTime("0.0"); //$NON-NLS-1$
        processingResource.setName(null);
        processingResource.setMultiplicity(null);
        processingResource.setSchedPolicy(null);
        testPreConditions();
    }

    public ProcessingResource getProcessingResource() {
        return processingResource;
    }
}
