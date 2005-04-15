package seg.jUCMNav.model.commands;

import ucm.map.ComponentRef;
import ucm.map.Map;
import urn.URNspec;

/**
 * This command adds a ComponentRef and its corresponding definition to the model. Its definition must not be referenced by any other classes.
 * 
 * @author jkealey
 *  
 */
public class AddComponentRefCommand extends JUCMNavCommand {

    // the component reference
    private ComponentRef compRef;

    // the map it has to be added to.
    private Map map;

    /**
     * 
     * @param m
     *            The map to which to add the ComponentRef
     * @param cr
     *            The ComponentRef
     */
    public AddComponentRefCommand(Map m, ComponentRef cr) {
        this.map = m;
        this.compRef = cr;
        setLabel("Create Component");
    }

    public void execute() {
        redo();
    }

    /**
     * @return Returns the comp.
     */
    public ComponentRef getComp() {
        return compRef;
    }

    /**
     * @return Returns the map.
     */
    public Map getMap() {
        return map;
    }

    public void redo() {
        testPreConditions();

        // add the component definition to the model
        URNspec urnspec = (URNspec) map.eContainer().eContainer();
        urnspec.getUrndef().getComponents().add(compRef.getCompDef());

        // add the component reference to the model
        map.getCompRefs().add(compRef);

        testPostConditions();
    }

    /**
     * @param comp
     *            The comp to set.
     */
    public void setComp(ComponentRef comp) {
        this.compRef = comp;
    }

    /**
     * @param map
     *            The map to set.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Make sure the model is in a consistent state for undo().
     *  
     */
    public void testPostConditions() {
        assert compRef != null : "post compRef";
        assert compRef.getCompDef() != null : "post compDef";
        assert map != null : "post map";

        assert map.getCompRefs().contains(compRef): "post compRef in map";
        assert ((URNspec) map.eContainer().eContainer()).getUrndef().getComponents().contains(compRef.getCompDef()) : "post compDef in model";
    }

    /**
     * Make sure the model is in a consistent state for redo().
     *  
     */
    public void testPreConditions() {
        assert compRef != null : "pre compRef";
        assert compRef.getCompDef() != null : "pre compDef";
        assert map != null : "pre Map";

        assert !map.getCompRefs().contains(compRef): "pre compRef not in map";

        // make sure this is a new component definition.
        // if not, our undo() will remove it, breaking code.
        assert !((URNspec) map.eContainer().eContainer()).getUrndef().getComponents().contains(compRef.getCompDef()) : "pre compDef not in model";
    }

    public void undo() {
        testPostConditions();

        // remove the component reference from the model
        map.getCompRefs().remove(compRef);

        // remove the component definition from the model
        URNspec urnspec = (URNspec) map.eContainer().eContainer();
        urnspec.getUrndef().getComponents().remove(compRef.getCompDef());

        testPreConditions();
    }

}