package seg.jUCMNav.editors;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

/**
 * Created 2005-03-24
 * 
 * This class only change the order of the layers of the root edit part.
 * 
 * @author Etienne Tremblay
 */
public class ConnectionOnBottomRootEditPart extends ScalableFreeformRootEditPart {

    /**
     * Overwrite this function and add the connection layer before the primary layer. This will make the nodes display on top of the connections.
     */
    protected LayeredPane createPrintableLayers() {
        FreeformLayeredPane layeredPane = new FreeformLayeredPane();

        layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
        layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
        return layeredPane;
    }
}