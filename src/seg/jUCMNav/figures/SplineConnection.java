/*
 * Created on 2005-03-02
 *
 */
package seg.jUCMNav.figures;

import org.eclipse.draw2d.PolylineConnection;

import seg.jUCMNav.model.ucm.Link;

/**
 * Created 2005-03-02
 * 
 * @author Etienne Tremblay
 */
public class SplineConnection extends PolylineConnection {
	private Link link;

	/**
	 * @return Returns the link.
	 */
	public Link getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(Link link) {
		this.link = link;
	}
}
