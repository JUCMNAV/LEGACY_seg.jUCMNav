package seg.jUCMNav.figures.util;

import org.eclipse.draw2d.geometry.Dimension;

import seg.jUCMNav.figures.AndForkJoinFigure;
import seg.jUCMNav.figures.EndPointFigure;
import seg.jUCMNav.figures.GrlNodeFigure;
import seg.jUCMNav.figures.PathNodeFigure;
import seg.jUCMNav.figures.StubFigure;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.Stub;
import urncore.GRLmodelElement;
import urncore.IURNNode;

/**
 * This is a utility class to obtain the default dimensions of model elements to position labels at creation. We must use this class because the editparts have
 * not been created when the label is created.
 * 
 * @author Jordan
 * 
 */
public class JUCMNavFigure {
    public static Dimension getDimension(Object modelElement) {
        if (modelElement instanceof EndPoint) {
            if (((EndPoint) modelElement).getSucc().size() == 0)
                return PathNodeFigure.getDefaultDimension();
            else
                // if connected to startpoint, avoids overlapping (figure grows anyways)
                return PathNodeFigure.getDefaultDimension().getCopy().scale(EndPointFigure.RESIZEFACTOR + 0.5);
        } else if (modelElement instanceof Stub) {
            return StubFigure.getDefaultDimension();
        } else if (modelElement instanceof AndFork || modelElement instanceof AndJoin) {
            // and fork/joins are scaled depending on the number of outs/ins
            return AndForkJoinFigure.getDefaultDimension().getCopy().scale(((PathNode) modelElement).getSucc().size());
        } else if (modelElement instanceof PathNode) {
            return PathNodeFigure.getDefaultDimension();
        } else if ((modelElement instanceof IURNNode) && (modelElement instanceof GRLmodelElement)) {
            return GrlNodeFigure.getDefaultDimension();
        }

        System.out.println("Unknown dimension in JUCMNavFigure.getDimension() for " + modelElement); //$NON-NLS-1$

        return new Dimension(0, 0);
    }
}