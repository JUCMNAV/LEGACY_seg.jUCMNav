package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import asd.Rule;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddRuleCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import urn.URNspec;

/**
 * Adds an or-fork. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @see SafePathChecker
 * @author jpdaigle, jkealey
 */
public class AddRuleAction extends URNSelectionAction {
    public static final String ADDRULE = "seg.jUCMNav.AddRule"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddRuleAction(IWorkbenchPart part) {
        super(part);
        setId(ADDRULE);
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
        Command comm= new AddRuleCommand(sel.getUrnspec(), getNewRule(sel.getUrnspec()), sel.getASDiagram()) ;
        return comm;

      
    }
    
    protected Rule getNewRule(URNspec urn) {
        return (Rule) ModelCreationFactory.getNewObject(urn, Rule.class);
    }

    
   
}