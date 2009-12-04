package seg.jUCMNav.importexport;

import org.eclipse.swt.SWT;

/**
 * Exports as JPEG file. Implementation used in Eclipse 3.1 doesn't allow variable quality.
 * 
 * @author jkealey
 * 
 */
public class ExportImageJPG extends ExportImage {

    /**
     * Returns SWT.IMAGE_JPEG
     * 
     * @see seg.jUCMNav.importexport.ExportImage#getType()
     */
    public int getType() {
        return SWT.IMAGE_JPEG;
    }

}
