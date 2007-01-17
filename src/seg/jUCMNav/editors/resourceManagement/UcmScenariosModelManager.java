package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import seg.jUCMNav.Messages;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.impl.UcmscenariosPackageImpl;

/**
 * This class is used to load and save the model from the file system.
 * 
 * @author Jason Kealey
 */
public class UcmScenariosModelManager  {
    /**
     * In EMF, a resource provides the way to have access to the model content.
     */
    private Resource resource = null;

    /**
     * Gives access to the top level model element contained in the resource.
     */
    private ScenarioSpec model = null;

    /**
     * Returns the resource containing the UCM scenarios. Uses lazy initialization.
     * 
     * @param path
     * @return resource containing the UCM scenarios
     */
    public Resource getResource(IPath path) {
        if (resource == null) {

            ResourceSet resSet = getResourceSet();
            resource = resSet.getResource(URI.createPlatformResourceURI(path.toString()), true);
        }
        return resource;
    }

    /**
     * Returns the resource containing the UCM scenarios. Uses lazy initialization.
     * 
     * @param path
     * @return resource containing the UCM scenarios
     */
    public Resource getResource(File path) {
        if (resource == null) {

            ResourceSet resSet = getResourceSet();
            resource = resSet.getResource(URI.createFileURI(path.toString()), true);
        }
        return resource;
    }

    
    /**
     * Creates a resource to contain the UCM scenarios. The resource file does not exist yet.
     * 
     * @param path
     * @return resource to contain the UCM scenarios
     */
    private Resource createResource(IPath path) {
        ResourceSet resSet = getResourceSet();
        resource = resSet.createResource(URI.createPlatformResourceURI(path.toString()));
        return resource;
    }

    /**
     * Returns the resource set.
     * 
     * @return the resource set
     */
    private ResourceSet getResourceSet() {
        // Initialize the ucm package
    	UcmscenariosPackageImpl.init();
        // Register the XMI resource factory for the .ucm extension
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map m = reg.getExtensionToFactoryMap();
        m.put(Messages.getString("UrnModelManager.ucmExtension") + "scenarios", new XMIResourceFactoryImpl()); //$NON-NLS-1$
        // Obtain a new resource set
        return new ResourceSetImpl();
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
     * Loads the content of the model from the file.
     * 
     * @param path
     */
    public void load(IPath path) throws IOException {
        getResource(path);
        Map options = new HashMap();
        
        options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
        resource.load(options);
    }
    

    /**
     * Loads the content of the model from the file.
     * 
     * @param path
     */
    public void load(File file) throws IOException {
        getResource(file);
        Map options = new HashMap();
        
        options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
        resource.load(options);
    }

    /**
     * reloads the content of the model from the file.
     * 
     * @param path
     */
    public void reload(IPath path) throws IOException {
        getResource(path).unload();
        load(path);
    }

    /**
     * Saves the content of the model to the file.
     * 
     * @param path
     */
    public void save(IPath path) throws IOException {
        getResource(path);
        Map options = new HashMap();
        options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
        // latin 1
        options.put(XMIResource.OPTION_ENCODING, "ISO-8859-1"); //$NON-NLS-1$
                
        resource.save(options);
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
        return model;
    }

    
}