package seg.jUCMNav.figures;

import org.eclipse.draw2d.geometry.PointList;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * Figure for a Direction Arrow. An arrow that can be rotated.
 * 
 * @author Jordan McManus
 */
public class DirectionArrowFigure extends ResponsibilityFigure implements IRotateable {

    /**
     * Create arrow taking half the horizontal and vertical space.
     * 
     * @see seg.jUCMNav.figures.PathNodeFigure#createFigure()
     */
    protected void createFigure() {
        edge1 = new Polygon();
        enpoints1 = new PointList();

        edge2 = new Polygon();
        enpoints2 = new PointList();

        enpoints1.addPoint(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT / 4);
        enpoints1.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        enpoints2.addPoint(DEFAULT_WIDTH / 4, DEFAULT_HEIGHT - DEFAULT_HEIGHT / 4);
        enpoints2.addPoint(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);

        edge1.setLineWidth(3);
        edge1.setPoints(enpoints1);
        edge1.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        edge2.setLineWidth(3);
        edge2.setPoints(enpoints2);
        edge2.setAntialias(GeneralPreferencePage.getAntialiasingPref());

        add(edge1);
        add(edge2);
    }

}