package seg.jUCMNav.importexport.tdl;

import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.delete.DeleteScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategiesGroupCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategyCommand;
import seg.jUCMNav.model.commands.transformations.MakeWellFormedCommand;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.importexport.msc.*;
import seg.jUCMNav.importexport.scenariosTools.ExportScenariosTraversalListener;

/**
 * A traversal listener that will generate MSCs.
 * 
 * TODO: synchronous connects -> empty responsibility
 * 
 * TODO: filter unused definitions (resp/comp) + scenario groups.
 * 
 * TODO: and fork should have stub name if concurrency is started by endpoint.
 * 
 * @author jkealey
 * 
 */


public class TdlTraversalListener extends ExportScenariosTraversalListener implements ITraversalListener {


	
    /**
     * Create a new traversal listener. Will set the stage for the listener. Creates a new blank URNspec.
     * 
     * @param originalFilename
     *            the original filename
     * @param newFilename
     *            the new filename
     */
    public TdlTraversalListener(String originalFilename, String newFilename, String whenToSave) {
        super(originalFilename, newFilename, whenToSave);
    }

    private void saveModel() {
	    String path = filename;
	    ExportTDL exportTdl = new ExportTDL();
	    
	    ScenarioGenerator gen = new ScenarioGenerator(urnspec);
	    exportTdl.generateTdl(gen.getScenarioDocument(), path);
	}
    
	public void traversalEnded() {
	    cleanupScenarioGroups();
	
	    // TODO: get rid of unused definitions.
	
	    saveModel();
	}
	
	private void cleanupScenarioGroups() {
	    CompoundCommand compound = new CompoundCommand();
	    for (Iterator iter = urnspec.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
	        ScenarioGroup g = (ScenarioGroup) iter.next();
	
	        // if is in use, won't delete.
	        DeleteStrategiesGroupCommand cmd = new DeleteStrategiesGroupCommand(g);
	        if (cmd.canExecute())
	            compound.add(cmd);
	    }
	    cs.execute(compound);
	}
}
