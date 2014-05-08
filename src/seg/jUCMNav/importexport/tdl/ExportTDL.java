package seg.jUCMNav.importexport.tdl;

import java.util.ListIterator;

import org.eclipse.emf.common.util.EList;
import org.etsi.mts.tdl.*;
import org.etsi.mts.tdl.impl.*;
import org.etsi.mts.tdl.util.*;

import seg.jUCMNav.importexport.msc.*;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.Component;




/*
 * TODO : Add a description here.
 */

public class ExportTDL{
		
	
	 protected String oldFilename;
	 protected String newFilename;
	
	/*
	 * An instance of ExportMSC class
	 * simply to access it's methods. 
	 */
	
	private ExportMSC exportMsc = new ExportMSC();
	private TdlFactory f = TdlFactory.eINSTANCE;
	
	public void export(ScenarioSpec scenarioSpec){
		
		System.out.println("Plugin execution started \n");
		
		EList compList = scenarioSpec.getComponents();
		ListIterator<Component> compListIt = compList.listIterator();
		
		while(compListIt.hasNext()){
		Component currentUCMComp = compListIt.next();
		System.out.println(currentUCMComp.getName());
		
		ComponentType currentTdlCompType = f.createComponentType();
		currentTdlCompType.setName(currentUCMComp.getName());
		System.out.println("The name of the tdl ComponentType is " + currentTdlCompType.getName());
		
		}
		
		System.out.println("\nPlugin execution terminated ");
	}
	
}


