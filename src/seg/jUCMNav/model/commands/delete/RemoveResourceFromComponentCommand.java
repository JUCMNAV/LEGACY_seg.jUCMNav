package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urncore.ComponentElement;
import urncore.ComponentRegular;

public class RemoveResourceFromComponentCommand extends Command implements JUCMNavCommand {

    private ComponentElement comp;
    private GeneralResource resx;
    private int index;
    
    public RemoveResourceFromComponentCommand(ComponentElement comp, GeneralResource resx) {
        this.comp=comp;
        this.resx=resx;
    }
    
    
    public void execute() {
        if (resx instanceof ProcessingResource)
        {
            ProcessingResource resource = (ProcessingResource) resx;
            index = resource.getComponents().indexOf(comp);
        }
        redo();
    }
    public void redo() {
        testPreConditions();
        if (comp instanceof ComponentRegular && resx instanceof ProcessingResource) {
            ComponentRegular regular = (ComponentRegular) comp;
            regular.setHost(null);
        }
        else {
            comp.setResource(null);
        }
        
        testPostConditions();
    }
    
    public void undo() {
        testPostConditions();

        if (comp instanceof ComponentRegular && resx instanceof ProcessingResource) {
            ProcessingResource resource = (ProcessingResource) resx;
            ComponentRegular regular = (ComponentRegular) comp;
            //regular.setHost((ProcessingResource)resx);
            // keep index
            resource.getComponents().add(index, regular);
        }
        else {
            comp.setResource((PassiveResource)resx);
        }
        
        testPreConditions();
    }
    public void testPostConditions() {
        assert comp != null && resx!= null && (resx instanceof PassiveResource || resx instanceof ProcessingResource) : "post invalid comp/resx";
        if (comp instanceof ComponentRegular && resx instanceof ProcessingResource) {
            ComponentRegular regular = (ComponentRegular) comp;
            assert regular.getHost() == null : "post resx still bound";
        }
        else {
            assert comp.getResource() == null : "post resx still bound";
        }        
    }

    public void testPreConditions() {
        assert comp != null && resx!= null && (resx instanceof PassiveResource || resx instanceof ProcessingResource) : "post invalid comp/resx";
        
        if (comp instanceof ComponentRegular && resx instanceof ProcessingResource) {
            ComponentRegular regular = (ComponentRegular) comp;
            assert regular.getHost() == resx : "pre invalid resx link";
        }
        else {
            assert comp.getResource() == resx : "pre invalid resx link";
        }
                
    }

    
}
