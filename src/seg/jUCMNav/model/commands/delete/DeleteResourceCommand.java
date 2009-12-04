/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.Demand;
import ucm.performance.DeviceKind;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urn.URNspec;
import urncore.Component;

/**
 * This command deletes resource (one of the three)
 * 
 * @author jack
 * 
 */
public class DeleteResourceCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private GeneralResource genRes;
    private PassiveResource passiveResource;
    private ProcessingResource processingResource;
    private ExternalOperation externalOperation;
    private String name;
    private Component component;
    private Component[] components;
    private String opTime;
    private DeviceKind deviceKind;
    private String description;
    private Demand[] demands;
    private IWorkbenchPage workbenchPage;
    private CommandStack cs;
    private CompoundCommand command;
    private List perfMeasures;
    private List disconnectedPM;

    /**
     * Deleting one of the three (3) types of resource
     * 
     * @param workbenchPage
     * @param urn
     * @param genRes
     */
    public DeleteResourceCommand(IWorkbenchPage workbenchPage, URNspec urn, GeneralResource genRes) {
        this.workbenchPage = workbenchPage;
        this.urn = urn;
        this.genRes = genRes;
        if (genRes instanceof PassiveResource) {
            passiveResource = (PassiveResource) genRes;
        } else if (genRes instanceof ProcessingResource) {
            processingResource = (ProcessingResource) genRes;
        } else if (genRes instanceof ExternalOperation) {
            externalOperation = (ExternalOperation) genRes;
        }

        setLabel(Messages.getString("DeleteResourceCommand.DeleteResource")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        return (urn != null) && (urn.getUcmspec().getResources().contains(genRes)); // and exists resource...
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
        // urn.getUcmspec().getResources().remove(genRes);
    }

    public void redo() {
        // disconnect PerfMeasures
        disconnectedPM = new ArrayList();
        if (genRes instanceof PassiveResource) {
            redoPassive();
        } else if (genRes instanceof ProcessingResource) {
            redoProcessing();
        } else if (genRes instanceof ExternalOperation) {
            redoExternal();
        }
    }

    public void undo() {
        if (genRes instanceof PassiveResource) {
            undoPassive();
        } else if (genRes instanceof ProcessingResource) {
            undoProcessing();
        } else if (genRes instanceof ExternalOperation) {
            undoExternal();
        }
    }

    public void deleteDemands() {
        demands = new Demand[externalOperation.getDemands().size()];
        int i = 0;
        cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        command = new CompoundCommand();
        for (Iterator dem = externalOperation.getDemands().iterator(); dem.hasNext();) {
            Demand aDemand = (Demand) dem.next();
            demands[i] = aDemand;
            i++;
            DeleteDemandCommand deleteCmd = new DeleteDemandCommand(urn, (ExternalOperation) externalOperation, aDemand, aDemand.getResponsibility());
            command.add(deleteCmd);
        }
        // use a command to be undoable.
        if (command.canExecute())
            cs.execute(command);
    }

    public void undeleteDemands() {
        if (command.canUndo())
            cs.undo();
    }

    public void redoPassive() {
        testPreConditions();
        name = passiveResource.getName();
        component = (Component) passiveResource.getComponent();
        if (component != null) {
            passiveResource.setComponent(null);
            component.setResource(null);
        }
        // passiveResource.setName(null);
        urn.getUcmspec().getResources().remove(passiveResource);
        testPostConditions();
    }

    public void undoPassive() {
        testPostConditions();
        urn.getUcmspec().getResources().add(passiveResource);
        if (component != null) {
            component.setResource(passiveResource);
            passiveResource.setComponent(component);
        }
        passiveResource.setName(name);
        testPreConditions();
    }

    /**
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        if (genRes instanceof PassiveResource) {
            testPreConditionsPassive();
        } else if (genRes instanceof ProcessingResource) {
            testPreConditionsProcessing();
        } else if (genRes instanceof ExternalOperation) {
            testPreConditionsExternal();
        }
    }

    public void testPostConditions() {
        if (genRes instanceof PassiveResource) {
            testPostConditionsPassive();
        } else if (genRes instanceof ProcessingResource) {
            testPostConditionsProcessing();
        } else if (genRes instanceof ExternalOperation) {
            testPostConditionsExternal();
        }
    }

    public void testPreConditionsPassive() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "pre null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(passiveResource) : "pre passiveResource not in model"; //$NON-NLS-1$
    }

    public void testPostConditionsPassive() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "post null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(passiveResource) : "post passiveResource in model"; //$NON-NLS-1$
    }

    public void testPreConditionsProcessing() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "pre null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(processingResource) : "pre processingResource not in model"; //$NON-NLS-1$
    }

    public void testPostConditionsProcessing() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "post null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(processingResource) : "post passiveResource in model"; //$NON-NLS-1$
    }

    public void testPreConditionsExternal() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "pre null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOperation) : "pre externalOperation not in model"; //$NON-NLS-1$
    }

    public void testPostConditionsExternal() {
        assert (urn != null) && (urn.getUcmspec() != null) : "post null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(externalOperation) : "post externalOperation in model"; //$NON-NLS-1$
    }

    public void redoProcessing() {
        testPreConditions();
        components = new Component[processingResource.getComponents().size()];
        deviceKind = processingResource.getKind();
        name = processingResource.getName();
        opTime = processingResource.getOpTime();
        // get a copy
        int i = 0;
        for (Iterator comp = processingResource.getComponents().iterator(); comp.hasNext();) {
            Component acomp = (Component) comp.next();
            components[i] = acomp;
            i++;
        }
        // remove from owner
        for (int j = 0; j < components.length; j++) {
            Component acomp = components[j];
            processingResource.getComponents().remove(acomp);
        }
        // acomp.setHost(null);
        urn.getUcmspec().getResources().remove(processingResource);
        // processingResource.setKind(null);
        // processingResource.setOpTime(0.0);
        // processingResource.setName(null);
        testPostConditions();
    }

    public void undoProcessing() {
        testPostConditions();
        urn.getUcmspec().getResources().add(processingResource);
        for (int i = 0; i < components.length; i++) {
            Component acomp = components[i];
            acomp.setHost(processingResource);
            processingResource.getComponents().add(acomp);
        }
        processingResource.setKind(deviceKind);
        processingResource.setOpTime(opTime);
        processingResource.setName(name);
        testPreConditions();
    }

    public void redoExternal() {
        testPreConditions();
        deleteDemands();
        name = externalOperation.getName();
        opTime = externalOperation.getOpTime();
        description = externalOperation.getDescription();
        // externalOperation.setOpTime(0.0);
        // externalOperation.setDescription(null);
        // externalOperation.setName(null);
        urn.getUcmspec().getResources().remove(externalOperation);
        testPostConditions();
    }

    public void undoExternal() {
        testPostConditions();
        urn.getUcmspec().getResources().add(externalOperation);
        externalOperation.setName(name);
        externalOperation.setDescription(description);
        externalOperation.setOpTime(opTime);
        undeleteDemands();
        testPreConditions();
    }

}
