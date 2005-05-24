package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.Map;
import urn.URNspec;

/**
 * Created on 21-May-2005
 * 
 * @author jkealey
 *  
 */
public class CreateMapCommand extends Command implements JUCMNavCommand {
    private Map map;

    private URNspec urn;

    public CreateMapCommand(URNspec urn) {
        this.urn = urn;
        
        // must be created here for getMap() to work properly.
        map = (Map) ModelCreationFactory.getNewObject(urn, Map.class);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }
    public Map getMap() {
        return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getMaps().add(map);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUcmspec() != null && map != null : "post not null";
        assert urn.getUcmspec().getMaps().contains(map) : "post map not in model";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUcmspec() != null && map != null : "pre not null";
        assert !urn.getUcmspec().getMaps().contains(map) : "pre map not in model";
    }

    public void undo() {
        testPostConditions();
        urn.getUcmspec().getMaps().remove(map);
        testPreConditions();
    }
}