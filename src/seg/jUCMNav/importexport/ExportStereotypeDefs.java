package seg.jUCMNav.importexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Vector;

import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;

public class ExportStereotypeDefs implements IURNExport {
    
    private FileOutputStream fos = null;
    public static final String END_LINE = "\r\n"; //$NON-NLS-1$ // need Windows or RFC 4180 CRLF format for new lines

    @Override
    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        // not used
    }

    @Override
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
        try {
            fos = new FileOutputStream(filename);

            writeStereotypeDefinitions(urn, fos);
            
        } catch (Exception e) {
            throw new InvocationTargetException(e);
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

    /**
     * Write the string to the file output stream.
     * 
     * @param s
     *            the string to write
     * @throws IOException
     */
    private void write(String s) throws IOException {
        if (s != null && s.length() > 0) {
            fos.write(s.getBytes());
        }
    }
    
    protected void writeStereotypeDefinitions(URNspec urn, FileOutputStream stream) throws IOException {
        Vector<String> defs = MetadataHelper.getAllMetaData(urn, "StereotypeDef"); //$NON-NLS-1$
        
        for (String value : defs) {
            write(value);
            write (END_LINE);
        }
    }
}
