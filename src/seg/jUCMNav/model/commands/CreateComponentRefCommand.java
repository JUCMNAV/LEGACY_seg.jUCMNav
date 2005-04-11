package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.ComponentRef;
import ucm.map.Map;
import urn.URNspec;
import urncore.ComponentElement;

/**
 * This Command creates a new instance of a ComponentRef.
 * For now, automatically creates a new component definition. 
 * Do we need to check if there are other references to this component before undoing it?
 * 
 * @author jkealey
 *  
 */
public class CreateComponentRefCommand extends Command {

    private static final String CreateCommand_Label = "CreateComponentRefCommand";
    private ComponentRef comp;
    private ComponentElement compdef;
    private Point location;
    private int width;
    private int height;
    private Map map;
    public static final int DEFAULT_WIDTH = 100;
    public static final int DEFAULT_HEIGHT = 100;

    /**
     * must have set comp and compdef prior to calling this method
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
      	       
        if (location != null) {
            comp.setX(location.x);
            comp.setY(location.y);
        }

        if (width > 0)
            comp.setWidth(width);
        else
            comp.setWidth(DEFAULT_WIDTH);

        if (height > 0)
            comp.setHeight(height);
        else
            comp.setHeight(DEFAULT_HEIGHT);

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        
        // add the component definition to the model
        URNspec urnspec = (URNspec) map.eContainer().eContainer();
        urnspec.getUrndef().getComponents().add(compdef);
        
        // add the component reference to the model
        map.getCompRefs().add(comp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {

        // remove the component reference from the model
        map.getCompRefs().remove(comp);        
        
        // remove the component definition from the model
        // should we check to see if there are any other references to this component?
        URNspec urnspec = (URNspec) map.eContainer().eContainer();
        urnspec.getUrndef().getComponents().remove(comp.getCompDef());
        

    }

    /**
     * @return Returns the location.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location
     *            The location to set.
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return Returns the comp.
     */
    public ComponentRef getComp() {
        return comp;
    }

    /**
     * @param comp
     *            The comp to set.
     */
    public void setComp(ComponentRef comp) {
        this.comp = comp;
    }

    /**
     * @return Returns the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height
     *            The height to set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return Returns the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width
     *            The width to set.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return Returns the map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map
     *            The map to set.
     */
    public void setMap(Map map) {
        this.map = map;
    }
    /**
     * @return Returns the compdef.
     */
    public ComponentElement getCompdef() {
        return compdef;
    }
    /**
     * @param compdef The compdef to set.
     */
    public void setCompdef(ComponentElement compdef) {
        this.compdef = compdef;
    }
}