/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.MapFactory;

/**
 * @author Etienne Tremblay
 *  
 */
public class CreateComponentRefCommand extends Command {

    private static final String CreateCommand_Label = "CreateComponentRefCommand";

    private ComponentRef comp;

    private Point location;

    private int width;

    private int height;

    private Map map;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        MapFactory factory = MapFactory.eINSTANCE;
        comp = factory.createComponentRef();
        
        if (location != null) {
            comp.setX(location.x);
            comp.setY(location.y);
        }

        if (width > 0)
            comp.setWidth(width);
        else
            comp.setWidth(100);

        if (height > 0)
            comp.setHeight(height);
        else
            comp.setHeight(100);



        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        map.getCompRefs().add(comp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        map.getCompRefs().remove(comp);
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
}