package seg.jUCMNav.model.commands.delete;

import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import urncore.ComponentElement;

/**
 * Command to delete a ComponentRef. (Remove it from the model). 
 * 
 * @author jkealey
 *  
 */
public class DeleteComponentRefCommand extends Command implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeleteComponentRefCommand";
    private Vector compRefChildren;
    private Vector pathNodeChildren;
    private ComponentRef compRef;

    private Map map;
    private ComponentRef parent;
    private ComponentElement compDef;

    public DeleteComponentRefCommand(ComponentRef cr) {
        setCompRef(cr);
        setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return compRef!=null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }




    /**
     * @return Returns the compRef.
     */
    public ComponentRef getCompRef() {
        return compRef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        map.getCompRefs().remove(compRef);
        compRef.getChildren().clear();
        compRef.getPathNodes().clear();
        compRef.setParent(null);
        compRef.setCompDef(null);
        
        testPostConditions();
    }

    /**
     * @param compRef
     *            The compRef to set.
     */
    public void setCompRef(ComponentRef compRef) {
        this.compRef = compRef;
        map = (Map) compRef.eContainer();
        parent = compRef.getParent();
        compDef = compRef.getCompDef();
        
        compRefChildren = new Vector();
        compRefChildren.addAll(compRef.getChildren());
        
        pathNodeChildren = new Vector();
        pathNodeChildren.addAll(compRef.getPathNodes());
       
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // TODO Auto-generated method stub
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        map.getCompRefs().add(compRef);
        compRef.getChildren().addAll(compRefChildren);
        compRef.getPathNodes().addAll(pathNodeChildren);
        compRef.setParent(parent);
        compRef.setCompDef(compDef);
        
        testPreConditions();
    }
}