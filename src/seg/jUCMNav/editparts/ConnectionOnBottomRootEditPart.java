package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Our root edit part; this class mainly changes the order of the layers of the root edit part.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class ConnectionOnBottomRootEditPart extends ScalableFreeformRootEditPart {

    public static final String COMPONENT_LAYER = "COMPONENT"; //$NON-NLS-1$

    // bad design but used to simplify some stub binding code. 
    private UCMNavMultiPageEditor multiPageEditor;

    // what is the current view mode for this editor. 
    private int mode = 0;

	/**
	 * 
	 * @param editor the multi page editor
	 */
    public ConnectionOnBottomRootEditPart(UCMNavMultiPageEditor editor) {
        super();
        multiPageEditor = editor;
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
     * @return Returns the MultiPageEditor. This is bad design but used to simplify some stub binding code. 
     */
    public UCMNavMultiPageEditor getMultiPageEditor() {
        return multiPageEditor;
    }

	/**
	 * Overridden to allow access from export image wizard. 
	 */
    public LayeredPane getScaledLayers() {
        return super.getScaledLayers();
    }

    /**
     * See UCMActionBarContributor's use of ModeComboContributionItem.
     * 
     * 0: normal show everything mode
     * 
     * 1: print mode 1: remove empty points
     * 
     * 2: print mode 2: remove empty points + stub labels
     * 
     * @return number of current mode
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * @param mode the new mode to be used; refreshes the editor. 
     */
    public void setMode(int mode) {
        this.mode = mode;
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            MapAndPathGraphEditPart element = (MapAndPathGraphEditPart) iter.next();
            element.refreshVisuals();
        }
    }
}