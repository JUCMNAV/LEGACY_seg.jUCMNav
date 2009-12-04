package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.LayeredPane;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Our root edit part; this class mainly changes the order of the layers of the root edit part.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class UCMConnectionOnBottomRootEditPart extends URNRootEditPart {

    /**
     * 
     * @param editor
     *            the multi page editor
     */
    public UCMConnectionOnBottomRootEditPart(UCMNavMultiPageEditor editor) {
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
        layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
        layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
        return layeredPane;
    }

    /**
     * @param mode
     *            the new mode to be used; refreshes the editor. 0: normal show everything mode
     * 
     *            1: print mode 1: remove empty points
     * 
     *            2: print mode 2: remove empty points + stub labels
     */
    public void setMode(int mode) {
        this.mode = mode;
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            element.refreshVisuals();
        }
    }
}