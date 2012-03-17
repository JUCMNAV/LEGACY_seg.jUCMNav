package seg.jUCMNav.importexport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import seg.jUCMNav.extensionpoints.IURNImport;
import urn.URNspec;

public class ImportGRLStrategies implements IURNImport  {

	@Override
	public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		System.out.println("importURN(FileInputStream fis, Vector autolayoutDiagrams) called.");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URNspec importURN(String filename, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	public URNspec importURN(FileInputStream fis, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {


		System.out.println("importURN(FileInputStream fis, URNspec urn, Vector autolayoutDiagrams) called.");

		int i = 1;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String strLine;

			while( (strLine = br.readLine()) != null) {
				System.out.println ( i++ + ": " + strLine);
			}
			fis.close();
		} catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}


		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URNspec importURN(String filename, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
