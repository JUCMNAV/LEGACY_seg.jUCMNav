package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import asd.Tool;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddToolCommand;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.UCMmodelElement;

/**
 * Adds a label to a PathNode or ComponentRef.
 * 
 * @author Jordan
 */
public class ChangeToolNameAction extends URNSelectionAction {
    public static final String CHANGETOOLNAME = "seg.jUCMNav.ChangeToolName"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ChangeToolNameAction(IWorkbenchPart part) {
        super(part);
        setId(CHANGETOOLNAME);
        //setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/label.gif")); //$NON-NLS-1$
    }

    /**
     * can only add labels if none already exist.
     * 
     * @return true, if calculate enabled
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