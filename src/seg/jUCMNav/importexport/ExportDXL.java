package seg.jUCMNav.importexport;

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
            fos.write("not yet implemented\nthis is a test".getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
