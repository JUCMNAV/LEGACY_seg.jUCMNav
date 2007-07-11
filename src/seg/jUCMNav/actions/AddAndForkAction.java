package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.AndFork;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds an and-fork. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @author jpdaigle, jkealey
 * @see SafePathChecker
 */
public class AddAndForkAction extends AddOrForkAction {

    public static final String ADDANDFORK = "seg.jUCMNav.AddAndFork"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddAndForkAction(IWorkbenchPart part) {
        super(part);
        setId(ADDANDFORK);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/AndFork16.gif")); //$NON-NLS-1$
    }

    /**
     * @param urn
     * @return an and-fork
     */
    protected PathNode getNewFork(URNspec urn) {
        return (AndFork) ModelCreationFactory.getNewObject(urn, AndFork.class);
    }

    /**
     * 
     * @return true if or fork/join, false if and fork/join
     */
    protected boolean isOrElement() {
        return false;
    }
}