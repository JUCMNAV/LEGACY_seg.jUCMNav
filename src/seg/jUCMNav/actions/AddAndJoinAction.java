package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.AndJoin;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds an and-join. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @author jpdaigle, jkealey
 * @see SafePathChecker
 */
public class AddAndJoinAction extends AddOrJoinAction {
    public static final String ADDANDJOIN = "seg.jUCMNav.AddAndJoin"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddAndJoinAction(IWorkbenchPart part) {
        super(part);
        setId(ADDANDJOIN);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndJoin16.gif")); //$NON-NLS-1$
    }

    /**
     * @param urn
     * @return an AndJoin
     */
    protected PathNode getNewJoin(URNspec urn) {
        return (AndJoin) ModelCreationFactory.getNewObject(urn, AndJoin.class);
    }

    /**
     * 
     * @return true if or fork/join, false if and fork/join
     */
    protected boolean isOrElement() {
        return false;
    }
}