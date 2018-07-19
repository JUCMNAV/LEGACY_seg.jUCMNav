package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.*;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urn.URNspec;
import asd.*;

/**
 * Adds an or-fork. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @see SafePathChecker
 * @author jpdaigle, jkealey
 */
public class AddToolAction extends URNSelectionAction {
    public static final String ADDTOOL = "seg.jUCMNav.AddTool"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddToolAction(IWorkbenchPart part) {
        super(part);
        setId(ADDTOOL);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrFork16.gif")); //$NON-NLS-1$
    }

    /**
     * @return true if have selected elements that can be forked, and when forked will not cause illegal loops.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
       switch (sel.getSelectionType()) {
        case SelectionHelper.ASDIAGRAM:
           return true;
      //  case SelectionHelper.EMPTYPOINT:
      //      return true;
      //  case SelectionHelper.STARTPOINT_EMPTYPOINT:
      //      return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getEmptypoint());
       // case SelectionHelper.STARTPOINT_DIRECTIONARROW:
        //    return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getDirectionarrow());
       // case SelectionHelper.STARTPOINT_NODECONNECTION:
         //   return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getNodeconnection());
      
            
        default: 
       return false;
       }
    }

    
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm= new AddToolCommand(sel.getUrnspec(), getNewTool(sel.getUrnspec()), sel.getASDiagram()) ;
        return comm;

      
    }
    
    protected Tool getNewTool(URNspec urn) {
        return (Tool) ModelCreationFactory.getNewObject(urn, Tool.class);
    }

    
   
}