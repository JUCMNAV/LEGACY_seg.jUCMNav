package seg.jUCMNav.tests.Z151importexport;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import seg.jUCMNav.importexport.z151.generated.ObjectFactory;
import seg.jUCMNav.importexport.z151.generated.URNspec;
import seg.jUCMNav.importexport.z151.marshal.MHandler;
import seg.jUCMNav.importexport.z151.marshal.URNspecMHandler;
import seg.jUCMNav.importexport.z151.unmarshal.EObjectImplUMHandler;
import seg.jUCMNav.importexport.z151.unmarshal.URNspecUMHandler;

public class Z151importexportTest extends TestCase {
	static String jUCMNavHome = SkeletonClassesGenerator.getHomeDirPath();
	static String expected = "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\expected\\"; //$NON-NLS-1$
	static String actual = "\\src\\seg\\jUCMNav\\tests\\Z151importexport\\actual\\"; //$NON-NLS-1$
	
	public void testActor(){
		String Z151file = "actor.z151"; //$NON-NLS-1$
		compareTwoZ151File(Z151file);
	}
	
//	public void testAO_Via_Verde_SPL(){
//		String Z151file = "AO_Via_Verde_SPL.z151";
//		compareTwoZ151File(Z151file);
//	}

	public boolean compareTwoZ151File(String Z151file){
		String expectedPath =jUCMNavHome+expected+Z151file;
		String actualPath= jUCMNavHome+actual+Z151file;
		urn.URNspec urn = mockImport(expectedPath);
		mockExport(urn, actualPath);
		String expected =  ResolveIds(readFileAsString(expectedPath));
		String actual =  ResolveIds(readFileAsString(actualPath));
		System.out.println(expected);
		assertEquals(expected, actual);
		return true;
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
		
		// Marshal the URN spec to XML...
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
		String idPrefix = "<id>Z151_id_"; //$NON-NLS-1$
		String idClosing = "</id>"; //$NON-NLS-1$
		int count = 1;
		int startIndex = urnspec.indexOf(idPrefix);
		int endIndex;
		while (startIndex != -1) {
			endIndex = startIndex+ urnspec.substring(startIndex).indexOf(idClosing);
			String id = urnspec.substring(startIndex + "<id>".length(),endIndex); //$NON-NLS-1$
			String newId = "RESOLVED_" + count++; //$NON-NLS-1$
			urnspec = urnspec.replaceAll(id, newId);
			startIndex = urnspec.indexOf(idPrefix);
		}
		return urnspec;
	}
	
	private static String readFileAsString(String filePath) {
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f;
		try {
			f = new BufferedInputStream(new FileInputStream(filePath));
			try {
				f.read(buffer);
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					f.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(buffer);
		
	}
}
