package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

import ucmscenarios.ScenarioSpec;
import ucmscenarios.impl.UcmscenariosPackageImpl;

/**
 * This class is used to load and save the scenario model from the file system.
 * 
 * @see ScenarioSpec
 * @see EmfModelManager
 * @author Jason Kealey
 */
public class UcmScenariosModelManager extends EmfModelManager {

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(File path) {
        createResource(path);

        ScenarioSpec spec = ucmscenarios.UcmscenariosFactory.eINSTANCE.createScenarioSpec();

        resource.getContents().add(spec);
        return spec;
    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param spec
     *            the initial contents of the file
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(File path, ScenarioSpec spec) {
        createResource(path);
        resource.getContents().add(spec);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spec;
    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(IPath path) {
        createResource(path);

        ScenarioSpec spec = ucmscenarios.UcmscenariosFactory.eINSTANCE.createScenarioSpec();

        resource.getContents().add(spec);
        return spec;
    }

    /**
     * Creates a new ScenarioSpec.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param spec
     *            the initial contents of the file
     * @return a new ScenarioSpec
     */
    public ScenarioSpec createScenarioSpec(IPath path, ScenarioSpec spec) {
        createResource(path);
        resource.getContents().add(spec);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spec;
    }

    /**
     * Returns the ScenarioSpec serialization filename.
     */
    protected String getFileExtension() {
        return "jucmscenarios"; //$NON-NLS-1$

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
        return (ScenarioSpec) model;
    }

    /**
     * Initialize EMF
     */
    protected void init() {
        // Initialize the ucm package
        UcmscenariosPackageImpl.init();

    }

}