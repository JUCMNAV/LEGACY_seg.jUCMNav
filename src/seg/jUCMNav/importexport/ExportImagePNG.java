package seg.jUCMNav.importexport;

import org.eclipse.swt.SWT;

/**
 * Exports as JPEG file. For Eclipse 3.3 and above.
 * 
 * @author damyot
 * 
 */
public class ExportImagePNG extends ExportImage {

    /**
     * Returns SWT.IMAGE_JPEG
     * 
     * @see seg.jUCMNav.importexport.ExportImage#getType()
     */
    public int getType() {
        return SWT.IMAGE_PNG;
    }

}
