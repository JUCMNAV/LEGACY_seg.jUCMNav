package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.Anything;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * This action is used to create an Anything path node
 * 
 * @author damyot
 * 
 */

public class AddAnythingAction extends AddResponsibilityAction {

    public static final String ADDANYTHING = "seg.jUCMNav.AddAnything"; //$NON-NLS-1$

    public AddAnythingAction(IWorkbenchPart part) {
        super(part);
        setId(ADDANYTHING);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/anything16.gif")); //$NON-NLS-1$
    }

    /**
     * @param urn
     * @return the PathNode to be inserted.
     */
    protected PathNode getNewPathNode(URNspec urn) {
        return (PathNode) ModelCreationFactory.getNewObject(urn, Anything.class);
    }

}
