package seg.jUCMNav.importexport.tdl;

import ucmscenarios.UcmscenariosFactory;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.Component;
import ucmscenarios.Instance;

public class TdlUtils{

	public static void main ( String[] args){
		
		UcmscenariosFactory f = UcmscenariosFactory.eINSTANCE;
		ScenarioSpec scenarioSpec = f.createScenarioSpec();
		
		/*
		 * Creates components related to <b>scenarioSpec<\b>
		 */
		
		Component compActor = f.createComponent();
		compActor.setScenarioSpec(scenarioSpec);
		compActor.setName("Actor");
		compActor.setDescription("This is an actor");
		compActor.setId("1");
		
		Component compCashier = f.createComponent();
		compCashier.setScenarioSpec(scenarioSpec);
		compCashier.setName("Cashier");
		compCashier.setDescription("This is a cashier");
		compCashier.setId("2");
		
		/*
		 * Creates an instance related to <b>comp<\b>
		 */
		
		Instance inst = f.createInstance();
		inst.setDefinition(compActor);
		
		ExportTDL tdlModel = new ExportTDL();
		
		tdlModel.export(scenarioSpec);
	}
	}

