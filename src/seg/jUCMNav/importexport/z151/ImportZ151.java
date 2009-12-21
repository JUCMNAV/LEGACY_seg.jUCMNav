package seg.jUCMNav.importexport.z151;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.importexport.z151.generated.URNspec;
import seg.jUCMNav.importexport.z151.unmarshal.EObjectImplUMHandler;
import seg.jUCMNav.importexport.z151.unmarshal.URNspecUMHandler;

public class ImportZ151 implements IURNImport {

    private Vector autolayoutDiagrams;

    public urn.URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams) throws InvocationTargetException {
        urn.URNspec urn = null;
        return importURN(fis, urn, autolayoutDiagrams);
    }
    public urn.URNspec importURN(FileInputStream fis, urn.URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException {
        EObjectImplUMHandler mh = new URNspecUMHandler();
        try {
            JAXBContext context = JAXBContext.newInstance(URNspec.class);
            Unmarshaller um = context.createUnmarshaller();
            // Unmarshal XML contents of the file into Java object instance.
            JAXBElement<seg.jUCMNav.importexport.z151.generated.URNspec> specFromFile = (JAXBElement<seg.jUCMNav.importexport.z151.generated.URNspec>) um.unmarshal(fis);
            urn = (urn.URNspec) mh.handle(specFromFile.getValue(), null, true);
            mh.resetUrnSpec();
            return urn;
        } catch (JAXBException jbe) {
            System.err.println(jbe.getMessage());
            mh.resetUrnSpec();
        } catch (Exception jbe) {
            System.err.println(jbe.getMessage());
            mh.resetUrnSpec();
        }
        return null;
    }


    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.lang.String)
     */
    public urn.URNspec importURN(String filename, Vector autolayoutDiagrams) throws InvocationTargetException {
        //not used
        return null;
    }
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.lang.String)
     */
    public urn.URNspec importURN(String filename, urn.URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException {
        // not used
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            return importURN(fis, autolayoutDiagrams);
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        } finally {
            // close the stream
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
