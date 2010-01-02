package seg.jUCMNav.importexport.z151;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.importexport.z151.generated.ObjectFactory;
import seg.jUCMNav.importexport.z151.generated.URNspec;
import seg.jUCMNav.importexport.z151.marshal.MHandler;
import seg.jUCMNav.importexport.z151.marshal.URNspecMHandler;

public class ExportZ151 implements IURNExport {

    private FileOutputStream fos = null;

    /**
     * Handles IURNExport and invokes the other export method by converting the filename to a FileOutputStream.
     * @see ExportZ151#export(URNspec, HashMap, FileOutputStream)
     */
    public void export(urn.URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
    	try {
        	fos = new FileOutputStream(filename);
            export(urn, mapDiagrams, fos);
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

    public void export(urn.URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {

    	MHandler mh = null;
    	// Marchal the URN spec to XML...
		try {
	    	mh = new URNspecMHandler();
	    	URNspec urnZ = null;
	    	urnZ = (URNspec) mh.handle(urn, null, true);
	    	JAXBContext context = JAXBContext.newInstance(URNspec.class);
	    	seg.jUCMNav.importexport.z151.generated.ObjectFactory of = new ObjectFactory();
	    	JAXBElement<URNspec> spec  = of.createURNspec(urnZ);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(spec, fos);
			mh.resetUrnSpec();
		} catch (JAXBException jbe) {
			System.err.println(jbe.getMessage());
			mh.resetUrnSpec();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			mh.resetUrnSpec();
		}
    }
}