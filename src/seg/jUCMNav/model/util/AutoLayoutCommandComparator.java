package seg.jUCMNav.model.util;

import java.util.Comparator;

import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;

/**
 * To be used to sort commands in a compound command in order to move the components before the path nodes. *
 * 
 * @author jkealey
 *  
 */
public class AutoLayoutCommandComparator implements Comparator {

    /**
     * Puts SetConstraintBoundComponentRefCompoundCommand before SetConstraintCommand
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {
        if (arg0 instanceof SetConstraintBoundComponentRefCompoundCommand && arg1 instanceof SetConstraintCommand)
            return -1;
        else if (arg1 instanceof SetConstraintBoundComponentRefCompoundCommand && arg0 instanceof SetConstraintCommand)
            return 1;
        else
            return 0;
    }

}