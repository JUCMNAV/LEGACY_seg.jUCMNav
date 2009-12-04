package seg.jUCMNav.editparts;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.ICellEditorValidator;

import seg.jUCMNav.editpolicies.directEditPolicy.AutocompleteTextCellEditor;
import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.figures.GrlNodeFigure;

/**
 * Generic Editpart for GrlNode
 * 
 * @author Jean-François Roy
 * 
 */
public abstract class GrlNodeEditPart extends ModelElementEditPart {

    // for direct edit
    protected DirectEditManager manager;

    /**
     * For direct edit, verify location.
     * 
     * @param requestLoc
     * @return true if the figure contains the point
     */
    private boolean directEditHitTest(Point requestLoc) {
        GrlNodeFigure figure = (GrlNodeFigure) getFigure();
        figure.translateToRelative(requestLoc);
        if (figure.containsPoint(requestLoc))
            return true;
        return false;
    }

    /**
     * @param value
     *            the name change during an edit
     */
    public void handleNameChange(String value) {
        GrlNodeFigure tableFigure = (GrlNodeFigure) getFigure();
        tableFigure.setVisible(false);
        refreshVisuals();
    }

    /**
     * Opens the direct edit manager.
     * 
     */
    protected void performDirectEdit() {
        if (manager == null) {
            GrlNodeFigure figure = (GrlNodeFigure) getFigure();

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return ""; //$NON-NLS-1$
                }
            };

            manager = new ExtendedDirectEditManager(this, AutocompleteTextCellEditor.class, new LabelCellEditorLocator(figure), figure, validator);
        }
        manager.show();
    }

    /**
     * Show direct edit on element on double click, f2 or delay.
     */
    public void performRequest(Request request) {
        if (request.getType() == RequestConstants.REQ_DIRECT_EDIT || request.getType() == RequestConstants.REQ_OPEN) {
            if (request instanceof DirectEditRequest && !directEditHitTest(((DirectEditRequest) request).getLocation().getCopy()))
                return;
            performDirectEdit();
        }
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the element value)
     */
    public void revertNameChange() {
        GrlNodeFigure tableFigure = (GrlNodeFigure) getFigure();
        tableFigure.setVisible(true);
        refreshVisuals();
    }

}
