package seg.jUCMNav.actions.scenarios;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;

public class EditSecondaryCodeAction extends EditCodeAction {

    public static final String EDITSECONDARYCODEACTION = "seg.jUCMNav.EditSecondaryCodeAction"; //$NON-NLS-1$

    /**
     * @param part
     */
    public EditSecondaryCodeAction(IWorkbenchPart part) {
        super(part);
        setId(EDITSECONDARYCODEACTION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/condition.gif")); //$NON-NLS-1$
    }

    public boolean calculateEnabled() {
        boolean b = super.calculateEnabled();

        if (obj instanceof FailurePoint) {

            FailurePoint point = (FailurePoint) obj;
            if (point.getSucc().size() == 0) {
                obj = null;
            } else {
                NodeConnection nc = (NodeConnection) point.getSucc().get(0);
                if (nc.getCondition()!=null)
                {
                    obj = nc.getCondition();
                }
                else
                {
                    obj = null;
                }
                
            }
        } else {
            obj = null;
        }

        return obj != null;
    }
}
