package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;

/**
 * To be used to sort commands in a compound command in order to move the components before the path nodes. *
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutCommandComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Puts SetConstraintBoundContainerRefCompoundCommand before SetConstraintCommand
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        if (arg0 instanceof SetConstraintBoundContainerRefCompoundCommand)// && arg1 instanceof SetConstraintCommand)
            return -1;
        else if (arg1 instanceof SetConstraintBoundContainerRefCompoundCommand)// && arg0 instanceof SetConstraintCommand)
            return 1;
        // else if (arg0 instanceof SetConstraintBoundComponentRefCompoundCommand) {
        // SetConstraintBoundComponentRefCompoundCommand s1 = (SetConstraintBoundComponentRefCompoundCommand) arg0;
        // SetConstraintBoundComponentRefCompoundCommand s2 = (SetConstraintBoundComponentRefCompoundCommand) arg1;
        // if (isAlreadyParent(s1.getCompRef(), s2.getCompRef()))
        // return -1;
        // else if (isAlreadyParent(s2.getCompRef(), s1.getCompRef()))
        // return 1;
        // else
        // return 0;
        //
        // } else
        else
            return 0;
    }

    // private boolean isAlreadyParent(SpecificationComponentRef possibleParent, SpecificationComponentRef child) {
    // if (possibleParent == null || child == null || possibleParent == child) {
    // return false;
    // } else if (possibleParent == child.getParent())
    // return true;
    // else
    // return isAlreadyParent(possibleParent, child.getParent());
    // }
}