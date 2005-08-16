package seg.jUCMNav.importexport;

import org.eclipse.swt.SWT;

/**
 * Exports as bitmap.
 * 
 * @author jkealey
 * 
 */
public class ExportImageBMP extends ExportImage {

    /**
     * Return SWT.IMAGE_BMP_RLE
     * 
     * @see seg.jUCMNav.importexport.ExportImage#getType()
     */
    public int getType() {
        return SWT.IMAGE_BMP_RLE;
    }

}
