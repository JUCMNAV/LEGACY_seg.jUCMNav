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
    private Component component;
    private double opTime;
    private DeviceKind deviceKind;
    
    /**
     * @param component 
     * 
     */
    public CreateActiveProcessingCommand(URNspec urn, Component component, double opTime, DeviceKind deviceKind) {
        this.urn = urn;
        this.component = component;
        this.opTime = opTime;
        this.deviceKind = deviceKind;
        setLabel(Messages.getString("CreateActiveProcessingCommand.CreateActiveProcessingResource")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
    	// this requires no resources currently set to this component -> implies prior use of "delete" _js_
        return (urn != null) && (component != null) && (component.getResource() == null);
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
    	component.setHost(processingResource);
    	processingResource.getComponents().add(component);
    	processingResource.setKind(deviceKind);
    	processingResource.setOpTime(opTime);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null): "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(processingResource): "post processingResource in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null): "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(processingResource) : "pre passiveResource not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUcmspec().getResources().remove(processingResource);
        component.setHost(null);
        processingResource.getComponents().remove(component);
    	processingResource.setKind(null);
    	processingResource.setOpTime(0.0);
        testPreConditions();
    }

    public ProcessingResource getProcessingResource() {
        return processingResource;
    }
}
