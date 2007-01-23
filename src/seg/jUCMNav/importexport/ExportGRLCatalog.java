package seg.jUCMNav.importexport;

import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        //not used
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNExport#export(urn.URNspec, java.lang.String)
     */
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
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
        write("element-def"); //$NON-NLS-1$
        write(EOT);
        
        //Create an xml element for each of the intentionalElement
        for(Iterator it=urn.getGrlspec().getIntElements().iterator(); it.hasNext();){
            IntentionalElement element = (IntentionalElement)it.next();
            write(TAB);
            write(TAB);
            write(SOT);
            write("intentional-element"); //$NON-NLS-1$
            writeAttribute("id", element.getId()); //$NON-NLS-1$
            writeAttribute("name", element.getName()); //$NON-NLS-1$
            writeAttribute("description", element.getDescription()); //$NON-NLS-1$
            writeAttribute("type", element.getType().getName()); //$NON-NLS-1$
            writeAttribute("decompositiontype", element.getDecompositionType().getName()); //$NON-NLS-1$
            write("/"); //$NON-NLS-1$
            write(EOT);
        }

        //End tag
        write(TAB);
        write(SOT);
        write("/element-def"); //$NON-NLS-1$
        write(EOT);

    }
    
    private void writeLinkDefList(URNspec urn) throws IOException{
        //Starting tag
        write(TAB);
        write(SOT);
        write("link-def"); //$NON-NLS-1$
        write(EOT);
        
        //Write an element for each decomposition, contribution and dependency
        for (Iterator it=urn.getGrlspec().getLinks().iterator(); it.hasNext();){
            ElementLink link = (ElementLink)it.next();
            write(TAB);
            write(TAB);
            write(SOT);
            if (link instanceof Decomposition){
                write("decomposition"); //$NON-NLS-1$
                writeAttribute("name", link.getName()); //$NON-NLS-1$
                writeAttribute("description", link.getDescription()); //$NON-NLS-1$
                writeAttribute("srcid", link.getSrc().getId()); //$NON-NLS-1$
                writeAttribute("destid", link.getDest().getId()); //$NON-NLS-1$
            } else if (link instanceof Dependency){
                write("dependency"); //$NON-NLS-1$
                writeAttribute("name", link.getName()); //$NON-NLS-1$
                writeAttribute("description", link.getDescription()); //$NON-NLS-1$
                writeAttribute("dependeeid", link.getDest().getId()); //$NON-NLS-1$
                writeAttribute("dependerid", link.getSrc().getId()); //$NON-NLS-1$
            } else if (link instanceof Contribution){
                write("contribution"); //$NON-NLS-1$
                writeAttribute("name", link.getName()); //$NON-NLS-1$
                writeAttribute("description", link.getDescription()); //$NON-NLS-1$
                writeAttribute("srcid", link.getSrc().getId()); //$NON-NLS-1$
                writeAttribute("destid", link.getDest().getId()); //$NON-NLS-1$
                writeAttribute("contributiontype", ((Contribution)link).getContribution().getName()); //$NON-NLS-1$
                if (((Contribution)link).isCorrelation()){
                    writeAttribute("correlation", "true"); //$NON-NLS-1$ //$NON-NLS-2$
                } else{
                    writeAttribute("correlation", "false"); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            write("/"); //$NON-NLS-1$
            write(EOT);
        }
        
        //End tag
        write(TAB);
        write(SOT);
        write("/link-def"); //$NON-NLS-1$
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
        write("=" + QUOTE); //$NON-NLS-1$
        write(description);
        write(QUOTE);
    }
    
    private void writeHeader(URNspec urn) throws IOException{
        write("<?xml version='1.0' encoding='ISO-8859-1'?>\r\n"); //$NON-NLS-1$
        
        //write("<!DOCTYPE grl-catalog LOCAL \"../seg/jUCMNav/importexport/grlcatalog.dtd\">\r\n");
        
        //Write the root tag
        write(SOT);
        write("grl-catalog"); //$NON-NLS-1$
        writeAttribute("catalog-name", urn.getName()); //$NON-NLS-1$
        writeAttribute("description", urn.getDescription()); //$NON-NLS-1$
        writeAttribute("author", urn.getAuthor()); //$NON-NLS-1$
        write(EOT);
    }
    
    private void writeFooter()throws IOException{
        write(SOT);
        write("/grl-catalog"); //$NON-NLS-1$
        write(EOT);
    }
}
