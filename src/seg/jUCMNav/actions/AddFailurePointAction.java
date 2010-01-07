package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.FailurePoint;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * This action is used to create a Failure Point path node
 * 
 * @author damyot
 * 
 */

public class AddFailurePointAction extends AddResponsibilityAction {

    public static final String ADDFAILUREPOINT = "seg.jUCMNav.AddFailurePoint"; //$NON-NLS-1$

    public AddFailurePointAction(IWorkbenchPart part) {
        super(part);
        setId(ADDFAILUREPOINT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/FailurePoint16.gif")); //$NON-NLS-1$
    }

    /**
     * @param urn
     * @return the PathNode to be inserted.
     */
    protected PathNode getNewPathNode(URNspec urn) {
        return (PathNode) ModelCreationFactory.getNewObject(urn, FailurePoint.class);
    }

}
