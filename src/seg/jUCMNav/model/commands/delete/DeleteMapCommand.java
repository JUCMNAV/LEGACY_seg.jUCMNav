package seg.jUCMNav.model.commands.delete;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Responsibility;

/**
 * Command to delete a Map. (Remove it from the model). This command only takes into consideration links between RespRefs+ComponentRefs and
 * their respective definitions. No other links are broken (stubs, etc.).
 * 
 * @author jkealey
 *  
 */
public class DeleteMapCommand extends CompoundCommand implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeleteMapCommand"; //$NON-NLS-1$

    // the map to delete
    private Map map;

    private Hashtable htReferences;

    // the URNspec in which it is contained
    private URNspec urn;

    public DeleteMapCommand(Map m) {
        setMap(m);
        setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getMap() != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = getMap().getUcmspec().getUrnspec();
        htReferences = new Hashtable();
        for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
            ComponentRef comp = (ComponentRef) iter.next();
            htReferences.put(comp, comp.getCompDef());
        }

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            if (node instanceof RespRef)
                htReferences.put(node, ((RespRef) node).getRespDef());
            
            if(node instanceof Stub){
            	Stub stub = (Stub)node;
            	List plugins = stub.getBindings();
            	for (Iterator i = plugins.iterator(); i.hasNext();) {
        			PluginBinding plugin = (PluginBinding) i.next();
        			DeletePluginCommand cmd = new DeletePluginCommand(plugin, urn);
        			add(cmd);
        		}
            }
        }
        
        List plugins = map.getParentStub();
        for (Iterator i = plugins.iterator(); i.hasNext();) {
			PluginBinding plugin = (PluginBinding) i.next();
			DeletePluginCommand cmd = new DeletePluginCommand(plugin, urn);
			add(cmd);
		}
        testPreConditions();
        
        doRedo();
        
        super.execute();
        
        testPostConditions();
    }

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		// Make sure we can undo even if we don't have any added commands
		if(getCommands().size() == 0)
			return true;
		return super.canUndo();
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        doRedo();
        
        super.redo();
        
        testPostConditions();
    }

    /**
	 * 
	 */
	private void doRedo() {
		// remove map
        urn.getUcmspec().getMaps().remove(getMap());

        // break relations
        for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
            ComponentRef comp = (ComponentRef) iter.next();
            comp.setCompDef(null);
        }

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            if (node instanceof RespRef)
                ((RespRef) node).setRespDef(null);
        }
	}

	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getMap() != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getMaps().contains(getMap()) : "post map still in model"; //$NON-NLS-1$

        // verify no more references
        for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
            ComponentRef comp = (ComponentRef) iter.next();
            assert comp.getCompDef() == null : "post compRef still references definition"; //$NON-NLS-1$
        }

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            if (node instanceof RespRef)
                assert ((RespRef) node).getRespDef() == null : "post respref still references definition"; //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        // lists could be empty but not null
        assert getMap() != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getUcmspec().getMaps().contains(getMap()) : "pre map in model"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        super.undo();

        // re-add map
        urn.getUcmspec().getMaps().add(getMap());

        // re-add references
        for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
            ComponentRef comp = (ComponentRef) iter.next();
            comp.setCompDef((ComponentElement) htReferences.get(comp));
        }

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            if (node instanceof RespRef)
                ((RespRef) node).setRespDef((Responsibility) htReferences.get(node));
        }
        testPreConditions();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}