package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.PathNode;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;

public class AddWaitingPlaceAction extends AddResponsibilityAction {

    public static final String ADDWP = "seg.jUCMNav.AddWaitingPlace"; //$NON-NLS-1$
    public static final String ADDTIMER = "seg.jUCMNav.AddTimer"; //$NON-NLS-1$

    public AddWaitingPlaceAction(IWorkbenchPart part, String id) {
        super(part);
        setId(id);
        if (id.equals(ADDTIMER))
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Timer16.gif")); //$NON-NLS-1$
        else
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Wait16.gif")); //$NON-NLS-1$
    }

    /**
     * @param urn
     * @return the PathNode to be inserted.
     */
    protected PathNode getNewPathNode(URNspec urn) {
        if (getId().equals(ADDTIMER))
            return (PathNode) ModelCreationFactory.getNewObject(urn, Timer.class);
        else
            return (PathNode) ModelCreationFactory.getNewObject(urn, WaitingPlace.class);
    }

}
