package seg.jUCMNav.importexport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import seg.jUCMNav.extensionpoints.IURNExport;
import urn.URNspec;

/**
 * Export DXL Script to be run in Telelogic DOORS
 * 
 * @author jkealey
 * 
 */
public class ExportDXL implements IURNExport {

    public void export(URNspec urn, FileOutputStream fos) {
        try {
            fos.write("not yet implemented\nthis is a test".getBytes()); //$NON-NLS-1$
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void export(URNspec urn, String filename) {
        // this method body is simply used for testing purposes. classes that extend the export extension point should pick either via filename or via file
        // output stream, not both.
        FileOutputStream fos=null;
        try {

            fos = new FileOutputStream(filename);
            export(urn, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // close the stream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public boolean useStream() {
        return true;
    }

}
