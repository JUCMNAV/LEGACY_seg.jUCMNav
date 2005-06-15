package seg.jUCMNav.figures.util;

import org.eclipse.draw2d.geometry.Dimension;

import seg.jUCMNav.figures.AndForkFigure;
import seg.jUCMNav.figures.AndJoinFigure;
import seg.jUCMNav.figures.EmptyPointFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.OrForkFigure;
import seg.jUCMNav.figures.OrJoinFigure;
import seg.jUCMNav.figures.ResponsibilityFigure;
import seg.jUCMNav.figures.StartPointFigure;
import seg.jUCMNav.figures.StubFigure;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.WaitingPlace;

/**
 * @author Jordan
 * 
 * This is a utility class to obtain the default dimensions of model elements to position labels at creation. We must use this class because the editparts have
 * not been created when the label is created.
 * 
 */
public class JUCMNavFigure {
    public static Dimension getDimension(Object modelElement) {
        if (modelElement instanceof StartPoint || modelElement instanceof WaitingPlace) {
            Dimension dim = StartPointFigure.getDefaultDimension();
            //The plus 10 is to offset the coordinates in the figure
            //ellipse.setBounds(new Rectangle(4, 4, DEFAULT_WIDTH, DEFAULT_HEIGHT));
            return new Dimension(dim.width, dim.height + 10);
        } else if (modelElement instanceof EndPoint) {
            return EndPointFigure.getDefaultDimension();
        } else if (modelElement instanceof Stub) {
            return StubFigure.getDefaultDimension();
        } else if (modelElement instanceof RespRef) {
            return ResponsibilityFigure.getDefaultDimension();
        } else if (modelElement instanceof EmptyPoint) {
            return EmptyPointFigure.getDefaultDimension();
        } else if (modelElement instanceof AndFork) {
            return AndForkFigure.getDefaultDimension();
        } else if (modelElement instanceof OrFork) {
            return OrForkFigure.getDefaultDimension();
        } else if (modelElement instanceof OrJoin) {
            return OrJoinFigure.getDefaultDimension();
        } else if (modelElement instanceof AndJoin) {
            return AndJoinFigure.getDefaultDimension();
        }

        return new Dimension(0, 0);
    }
}