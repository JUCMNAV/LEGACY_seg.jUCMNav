/*
 * Created on 2005-03-02
 *
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.PolylineConnection;

import ucm.map.NodeConnection;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class SplineConnection extends PolylineConnection {
	private NodeConnection link;

	/**
	 * @return Returns the link.
	 */
	public NodeConnection getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(NodeConnection link) {
		this.link = link;
	}
}
