package seg.jUCMNav.views.wizards.concerns;

import grl.GRLGraph;
import seg.jUCMNav.Messages;
import ucm.map.UCMmap;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * Given an object, determines the textual representation of that object for the UI
 * 
 * @author gunterm
 */
public class DisplayTextHelper {

    /**
     * @param element
     *            for which to determine its textual representation
     * @return the textual represenation for the UI
     */
    public static String getDisplayName(Object element) {
        if (element instanceof UpdatedConcern)
            return ((UpdatedConcern) element).getName() + " (" + ((UpdatedConcern) element).getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        else if (element instanceof UpdatedDiagram) {
            String type = getType(((UpdatedDiagram) element).getOriginal());
            return ((UpdatedDiagram) element).getName() + " (" + type + ((UpdatedDiagram) element).getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        } else if (element instanceof IURNDiagram) {
            String type = getType(element);
            return ((URNmodelElement) element).getName() + " (" + type + ((URNmodelElement) element).getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        } else
            return Messages.getString("DisplayTextHelper.UnknownElement"); //$NON-NLS-1$
    }

    /**
     * @param element
     *            for which to determine its type info
     * @return type info for textual representation
     */
    private static String getType(Object element) {
        String type = ""; //$NON-NLS-1$
        String delimiter = ", "; //$NON-NLS-1$
        if (element instanceof GRLGraph)
            type = Messages.getString("DisplayTextHelper.GRL") + delimiter; //$NON-NLS-1$
        else if (element instanceof UCMmap)
            type = Messages.getString("DisplayTextHelper.UCM") + delimiter; //$NON-NLS-1$
        return type;
    }

}
