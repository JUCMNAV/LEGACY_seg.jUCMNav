/**
 * 
 */
package seg.jUCMNav.importexport;

import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import seg.jUCMNav.extensionpoints.IURNExport;
import urn.URNspec;

/**
 * This class export the URN model into a GRL catalog 
 * 
 * @author Jean-François Roy
 *
 */
public class ExportGRLCatalog implements IURNExport {

    private FileOutputStream fos;
    
    //String to write XML 
    public static final String SOT = "<"; //$NON-NLS-1$
    public static final String EOT = ">\r\n"; //$NON-NLS-1$
    public static final String QUOTE = "\""; //$NON-NLS-1$
    public static final String SPACE = " "; //$NON-NLS-1$
    public static final String TAB = "    "; //$NON-NLS-1$
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNExport#export(urn.URNspec, java.io.FileOutputStream)
     */
    public void export(URNspec urn, FileOutputStream fos) throws InvocationTargetException {
        //not used
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNExport#export(urn.URNspec, java.lang.String)
     */
    public void export(URNspec urn, String filename) throws InvocationTargetException {
        try {
            fos = new FileOutputStream(filename);
            writeHeader(urn);
            writeElementDefList(urn);
            writeLinkDefList(urn);
            writeFooter();
        }catch (Exception e) {
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
    
    private void writeElementDefList(URNspec urn) throws IOException{
        //Starting tag
        write(TAB);
        write(SOT);
        write("element-def");
        write(EOT);
        
        //Create an xml element for each of the intentionalElement
        for(Iterator it=urn.getGrlspec().getIntElements().iterator(); it.hasNext();){
            IntentionalElement element = (IntentionalElement)it.next();
            write(TAB);
            write(TAB);
            write(SOT);
            write("intentional-element");
            writeAttribute("id", element.getId());
            writeAttribute("name", element.getName());
            writeAttribute("description", element.getDescription());
            writeAttribute("type", element.getType().getName());
            writeAttribute("decompositiontype", element.getDecompositionType().getName());
            write("/");
            write(EOT);
        }

        //End tag
        write(TAB);
        write(SOT);
        write("/element-def");
        write(EOT);

    }
    
    private void writeLinkDefList(URNspec urn) throws IOException{
        //Starting tag
        write(TAB);
        write(SOT);
        write("link-def");
        write(EOT);
        
        //Write an element for each decomposition, contribution and dependency
        for (Iterator it=urn.getGrlspec().getLinks().iterator(); it.hasNext();){
            ElementLink link = (ElementLink)it.next();
            write(TAB);
            write(TAB);
            write(SOT);
            if (link instanceof Decomposition){
                write("decomposition");
                writeAttribute("name", link.getName());
                writeAttribute("description", link.getDescription());
                writeAttribute("srcid", link.getSrc().getId());
                writeAttribute("destid", link.getDest().getId());
            } else if (link instanceof Dependency){
                write("dependency");
                writeAttribute("name", link.getName());
                writeAttribute("description", link.getDescription());
                writeAttribute("dependeeid", link.getDest().getId());
                writeAttribute("dependerid", link.getSrc().getId());
            } else if (link instanceof Contribution){
                write("contribution");
                writeAttribute("name", link.getName());
                writeAttribute("description", link.getDescription());
                writeAttribute("srcid", link.getSrc().getId());
                writeAttribute("destid", link.getDest().getId());
                writeAttribute("contributiontype", ((Contribution)link).getContribution().getName());
                if (((Contribution)link).isCorrelation()){
                    writeAttribute("correlation", "true");
                } else{
                    writeAttribute("correlation", "false");
                }
            }
            write("/");
            write(EOT);
        }
        
        //End tag
        write(TAB);
        write(SOT);
        write("/link-def");
        write(EOT);
    }
    /**
     * Write the string to the file output stream.
     * 
     * @param s
     *            the string to write
     * @throws IOException
     */
    public void write(String s) throws IOException {
        if (s != null && s.length() > 0) {
            fos.write(s.getBytes());
        }
    }
    
    private void writeAttribute(String name, String description)throws IOException{
        write(SPACE);
        write(name);
        write("=" + QUOTE);
        write(description);
        write(QUOTE);
    }
    
    private void writeHeader(URNspec urn) throws IOException{
        write("<?xml version='1.0' encoding='ISO-8859-1'?>\r\n");
        
        //write("<!DOCTYPE grl-catalog LOCAL \"../seg/jUCMNav/importexport/grlcatalog.dtd\">\r\n");
        
        //Write the root tag
        write(SOT);
        write("grl-catalog");
        writeAttribute("catalog-name", urn.getName());
        writeAttribute("description", urn.getDescription());
        writeAttribute("author", urn.getAuthor());
        write(EOT);
    }
    
    private void writeFooter()throws IOException{
        write(SOT);
        write("/grl-catalog");
        write(EOT);
    }
}
