package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import asd.DivisionOfLabour;
import asd.Rule;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddDivisionOfLabourCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import urn.URNspec;

/**
 * Adds an or-fork. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @see SafePathChecker
 * @author jpdaigle, jkealey
 */
public class AddDivisionOfLabourAction extends URNSelectionAction {
    public static final String ADDDIVISIONOFLABOUR = "seg.jUCMNav.AddDivisionOfLabour"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddDivisionOfLabourAction(IWorkbenchPart part) {
        super(part);
        setId(ADDDIVISIONOFLABOUR);
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
        Command comm= new AddDivisionOfLabourCommand(sel.getUrnspec(), getNewDivisionOfLabour(sel.getUrnspec()), sel.getASDiagram()) ;
        return comm;

      
    }
    
    protected DivisionOfLabour getNewDivisionOfLabour(URNspec urn) {
        return (DivisionOfLabour) ModelCreationFactory.getNewObject(urn, DivisionOfLabour.class);
    }

    
   
}