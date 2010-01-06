package seg.jUCMNav.tests.Z151importexport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import seg.jUCMNav.importexport.z151.generated.ObjectFactory;
import seg.jUCMNav.importexport.z151.generated.URNspec;
import seg.jUCMNav.importexport.z151.marshal.MHandler;
import seg.jUCMNav.importexport.z151.marshal.URNspecMHandler;
import seg.jUCMNav.importexport.z151.unmarshal.EObjectImplUMHandler;
import seg.jUCMNav.importexport.z151.unmarshal.URNspecUMHandler;
import junit.framework.TestCase;

public class Z151importexportTest extends TestCase {
	static String jUCMNavHome = SkeletonClassesGenerator.getHomeDirPath();
	static String expected = "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\expected";
	static String actual = "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\actual";
	public void testZ151(){
		urn.URNspec urn = mockImport(jUCMNavHome+expected+"\\actor.z151");
		mockExport(urn, jUCMNavHome+actual+"\\actor.z151");
		
	}
	private urn.URNspec mockImport(String fileName) {
		EObjectImplUMHandler mh = new URNspecUMHandler();
		FileInputStream fis;
		urn.URNspec urn = null;
		try {
			fis = new FileInputStream(new File(fileName));
			JAXBContext context = JAXBContext.newInstance(URNspec.class);
			Unmarshaller um = context.createUnmarshaller();
			// Unmarshal XML contents of the file into Java object instance.
			JAXBElement<seg.jUCMNav.importexport.z151.generated.URNspec> specFromFile = (JAXBElement<seg.jUCMNav.importexport.z151.generated.URNspec>) um
					.unmarshal(fis);
			urn = (urn.URNspec) mh.handle(specFromFile.getValue(), null, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException jbe) {
			System.err.println(jbe.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			mh.resetUrnSpec();
		}
		return urn;
	}

	private void mockExport(urn.URNspec urn, String outputFile) {
		MHandler mh = null;
		FileOutputStream fos;
		
		// Marchal the URN spec to XML...
		try {
			fos = new FileOutputStream(outputFile);
			mh = new URNspecMHandler();
			URNspec urnZ = null;
			urnZ = (URNspec) mh.handle(urn, null, true);
			JAXBContext context = JAXBContext.newInstance(URNspec.class);
			seg.jUCMNav.importexport.z151.generated.ObjectFactory of = new ObjectFactory();
			JAXBElement<URNspec> spec = of.createURNspec(urnZ);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(spec, fos);
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (JAXBException jbe) {
			System.err.println(jbe.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			mh.resetUrnSpec();	
		}
	}

	private String ResolveIds(String urnspec) {
		String idPrefix = "<id>Z151_id_";
		String idClosing = "</id>";
		int count = 1;
		int startIndex = urnspec.indexOf(idPrefix);
		int endIndex = urnspec.indexOf(idClosing);
		while (startIndex != -1) {
			String id = urnspec.substring(startIndex + "<id>".length(),
					endIndex - "</id>".length());
			String newId = "RESOLVED_" + count++;
			urnspec = urnspec.replaceAll(id, newId);
			startIndex = urnspec.indexOf(idPrefix);
			endIndex = urnspec.indexOf(idClosing);
		}
		return urnspec;
	}
}
