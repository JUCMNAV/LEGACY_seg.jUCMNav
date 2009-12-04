package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.LayeredPane;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Implementation of the URNRootEditPart for GRL editor
 * 
 * @author Jean-François Roy
 * 
 */
public class GrlConnectionOnBottomRootEditPart extends URNRootEditPart {

    /**
     * 
     * @param editor
     *            the multi page editor
     */
    public GrlConnectionOnBottomRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Overwrite this function and add the connection layer before the primary layer. This will make the nodes display on top of the connections.
     */
    protected LayeredPane createPrintableLayers() {
        FreeformLayeredPane layeredPane = new FreeformLayeredPane();

        FreeformLayer comp = new FreeformLayer();
        comp.setLayoutManager(new FreeformLayout());

        layeredPane.add(comp, COMPONENT_LAYER);

        layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
        layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
        return layeredPane;

    }

    /**
     * Sets the mode (what is shown (all / versus filtered view)) for all children.
     * 
     * @see seg.jUCMNav.editparts.URNRootEditPart#setMode(int)
     */
    public void setMode(int mode) {
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            element.refreshVisuals();
        }
    }
}
