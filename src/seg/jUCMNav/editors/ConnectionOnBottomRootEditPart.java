/*
 * Created on 2005-03-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editors;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

/**
 * Created 2005-03-24
 * 
 * @author Etienne Tremblay
 */
public class ConnectionOnBottomRootEditPart extends ScalableFreeformRootEditPart {
	
	
	protected LayeredPane createPrintableLayers() {
		FreeformLayeredPane layeredPane = new FreeformLayeredPane();
		layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
		layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
		return layeredPane;
	}
}
