package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.Messages;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.impl.UcmscenariosPackageImpl;

/**
 * This class is used to load and save the model from the file system.
 * 
 * @author Jason Kealey
 */
public class UcmScenariosModelManager extends EmfModelManager  {
  

   
    protected String getFileExtension() {
    	return Messages.getString("UrnModelManager.ucmExtension") + "scenarios"; //$NON-NLS-1$

    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(IPath path) {
        createResource(path);

        ScenarioSpec spec = (ScenarioSpec) ucmscenarios.UcmscenariosFactory.eINSTANCE.createScenarioSpec();

        resource.getContents().add(spec);
        return spec;
    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     * @param urnspec
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(IPath path, ScenarioSpec spec) {
        createResource(path);
        resource.getContents().add(spec);
        try {
			save(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return spec;
    }
    
    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(File path) {
        createResource(path);

        ScenarioSpec spec = (ScenarioSpec) ucmscenarios.UcmscenariosFactory.eINSTANCE.createScenarioSpec();

        resource.getContents().add(spec);
        return spec;
    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     * @param urnspec
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(File path, ScenarioSpec spec) {
        createResource(path);
        resource.getContents().add(spec);
        try {
			save(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return spec;
    }

    
    
    /**
     * Gets the top level model elements.
     * 
     * @return top level model elements
     */
    public ScenarioSpec getModel() {
        if (null == model) {
            EList l = resource.getContents();
            Iterator i = l.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof ScenarioSpec)
                    model = (ScenarioSpec) o;
            }

        }
        return (ScenarioSpec)model;
    }

	protected void init() {
	    // Initialize the ucm package
	    UcmscenariosPackageImpl.init();
		
	}

    
}