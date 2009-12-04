package seg.jUCMNav.importexport.reports;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.draw2d.IFigure;

import seg.jUCMNav.extensionpoints.IURNReport;
import urncore.IURNDiagram;

/**
 * Abstract base class to create reports
 * 
 * @author dessure
 * 
 */
public abstract class URNReport implements IURNReport {

    /**
     * TODO verify applicability of all methods from below Given the IFigure, save it to a file.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure pane, FileOutputStream fos) {

    }

    public void export(IFigure pane, String path) {

    }

    public void export(IURNDiagram diagram, FileOutputStream fos) throws InvocationTargetException {
        // not used.
    }

    private void export(IURNDiagram diagram, String path) throws InvocationTargetException {
        // not used.
    }

    /**
     * 
     * @return an SWT constant representing the image type.
     */
    public abstract int getType();

}
