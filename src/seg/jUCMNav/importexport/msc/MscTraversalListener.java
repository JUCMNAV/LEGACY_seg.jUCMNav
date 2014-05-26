package seg.jUCMNav.importexport.msc;

import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.importexport.scenariosTools.ExportScenariosTraversalListener;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.delete.DeleteScenarioCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategiesGroupCommand;
import seg.jUCMNav.model.commands.delete.DeleteStrategyCommand;
import seg.jUCMNav.model.commands.transformations.MakeWellFormedCommand;
import seg.jUCMNav.scenarios.algorithmInterfaces.ITraversalListener;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;


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


public class MscTraversalListener extends ExportScenariosTraversalListener implements ITraversalListener {

	
    /**
     * Create a new traversal listener. Will set the stage for the listener. Creates a new blank URNspec.
     * 
     * @param originalFilename
     *            the original filename
     * @param newFilename
     *            the new filename
     * @param whenToSave
     *            "0" is the final ucmscenarios model instance, "1" means before making it well-formed, "2" means after making it well-formed.
     */
    public MscTraversalListener(String originalFilename, String newFilename, String whenToSave) {
        super(originalFilename, newFilename, whenToSave);
    }
    
	private  void saveModel() {
	    File path = new File(filename);
	
	    if (whenToSave.equals("1")) { //$NON-NLS-1$
	        try {
	            UrnModelManager manager = new UrnModelManager();
	
	            // IPath path = ((FileEditorInput)
	            // PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput()).getFile()
	            // .getFullPath().removeLastSegments(1).append("__msctemp_" + currentSrcScenario.getName() + ".jucm");
	
	            manager.createURNspec(path, urnspec);
	            manager.save(path);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return;
	    }
	
	    cs.execute(new MakeWellFormedCommand(urnspec));
	
	    if (whenToSave.equals("2")) { //$NON-NLS-1$
	        try {
	            UrnModelManager manager = new UrnModelManager();
	
	            // IPath path = ((FileEditorInput)
	            // PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput()).getFile()
	            // .getFullPath().removeLastSegments(1).append("__msctemp2_" + currentSrcScenario.getName() + ".jucm");
	
	            manager.createURNspec(path, urnspec);
	            manager.save(path);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return;
	    }
	
	    // IPath path = ((FileEditorInput) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput()).getFile()
	    // .getFullPath().removeLastSegments(1).append("__msctemp_" + currentSrcScenario.getName() + ".jucmscenarios");
	
	    // MscGenerator gen = new MscGenerator(urnspec);
	
	    ScenarioGenerator gen = new ScenarioGenerator(urnspec);
	    gen.save(path);
	
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
