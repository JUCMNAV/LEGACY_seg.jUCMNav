package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urncore.Component;

public class RemoveResourceFromComponentCommand extends Command implements JUCMNavCommand {

    private Component comp;
    private GeneralResource resx;
    private int index;

    public RemoveResourceFromComponentCommand(Component comp, GeneralResource resx) {
        this.comp = comp;
        this.resx = resx;
    }

    public void execute() {
        if (resx instanceof ProcessingResource) {
            ProcessingResource resource = (ProcessingResource) resx;
            index = resource.getComponents().indexOf(comp);
        }
        redo();
    }

    public void redo() {
        testPreConditions();
        if (comp instanceof Component && resx instanceof ProcessingResource) {
            Component regular = (Component) comp;
            regular.setHost(null);
        } else {
            comp.setResource(null);
        }

        testPostConditions();
    }

    public void undo() {
        testPostConditions();

        if (comp instanceof Component && resx instanceof ProcessingResource) {
            ProcessingResource resource = (ProcessingResource) resx;
            Component regular = (Component) comp;
            // regular.setHost((ProcessingResource)resx);
            // keep index
            resource.getComponents().add(index, regular);
        } else {
            comp.setResource((PassiveResource) resx);
        }

        testPreConditions();
    }

    public void testPostConditions() {
        assert comp != null && resx != null && (resx instanceof PassiveResource || resx instanceof ProcessingResource) : "post invalid comp/resx"; //$NON-NLS-1$
        if (comp instanceof Component && resx instanceof ProcessingResource) {
            Component regular = (Component) comp;
            assert regular.getHost() == null : "post resx still bound"; //$NON-NLS-1$
        } else {
            assert comp.getResource() == null : "post resx still bound"; //$NON-NLS-1$
        }
    }

    public void testPreConditions() {
        assert comp != null && resx != null && (resx instanceof PassiveResource || resx instanceof ProcessingResource) : "post invalid comp/resx"; //$NON-NLS-1$

        if (comp instanceof Component && resx instanceof ProcessingResource) {
            Component regular = (Component) comp;
            assert regular.getHost() == resx : "pre invalid resx link"; //$NON-NLS-1$
        } else {
            assert comp.getResource() == resx : "pre invalid resx link"; //$NON-NLS-1$
        }

    }

}
