package seg.jUCMNav.emf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ucm.UcmPackage;
import ucm.map.MapFactory;
import ucm.map.MapPackage;
import ucm.map.impl.MapPackageImpl;

/**
 * Created 2005-02-11
 * 
 * This class is used to load and save the model from the file system.
 * 
 * This class is inspired from the IBM RedBook Eclipse Development using the Graphical Editing Framework and the Eclipse Modeling Framework
 * 
 * @author Etienne Tremblay
 */
public class UcmModelManager {
    /**
     * For the purpose of the simple editor, a file can only contain a UCM. In EMF, a resource provides the way to have access to the model content.
     */
    private Resource resource = null;

    /**
     * Contains the factory associated with the model.
     */
    private static MapFactory networkFactory = null;

    /**
     * Gives access to the top level model element contained in the resource.
     */
    private ucm.map.Map ucm = null;

    /**
     * Returns the resource containing the UCM. Uses lazy initialization.
     * 
     * @param path
     * @return
     */
    public Resource getResource(IPath path) {
        if (resource == null) {
            ResourceSet resSet = getResourceSet();
            resource = resSet.getResource(URI.createPlatformResourceURI(path.toString()), true);
        }
        return resource;
    }

    /**
     * Creates a resource to contain the UCM. The resource file does not exist yet.
     * 
     * @param path
     * @return
     */
    private Resource createResource(IPath path) {
        if (resource == null) {
            ResourceSet resSet = getResourceSet();
            resource = resSet.createResource(URI.createPlatformResourceURI(path.toString()));
        }
        return resource;
    }

    /**
     * Returns the resource set.
     * 
     * @param
     * @return
     */
    private ResourceSet getResourceSet() {
        // Initialize the ucm package
        MapPackageImpl.init();
        // Register the XMI resource factory for the .network extension
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map m = reg.getExtensionToFactoryMap();
        m.put("ucm", new XMIResourceFactoryImpl());
        // Obtain a new resource set
        return new ResourceSetImpl();
    }

    /**
     * Returns the factory associated with the model. Object creation are made through that factory.
     * 
     * @return
     */
    static public MapFactory getFactory() {
        if (networkFactory == null) {
            // Access the factory (needed to create instances)
            Map registry = EPackage.Registry.INSTANCE;
            String networkURI = UcmPackage.eNS_URI;
            MapPackage ucmPackage = (MapPackage) registry.get(networkURI);
            networkFactory = ucmPackage.getMapFactory();
        }
        return networkFactory;
    }

    /**
     * Creates a new UCM.
     * 
     * @param path
     * @return
     */
    public ucm.map.Map createMap(IPath path) {
        createResource(path);
        // Create a new network model
        Map registry = EPackage.Registry.INSTANCE;
        String UcmURI = MapPackage.eNS_URI;
        MapPackage nPackage = (MapPackage) registry.get(UcmURI);
        MapFactory nFactory = nPackage.getMapFactory();
        ucm = nFactory.createMap();
        ucm.setPathGraph(nFactory.createPathGraph());
        resource.getContents().add(ucm);
        return ucm;
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
        resource.save(options);
    }

    /**
     * Gets the top level model elements.
     * 
     * @return
     */
    public ucm.map.Map getModel() {
        if (null == ucm) {
            EList l = resource.getContents();
            Iterator i = l.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof ucm.map.Map)
                    ucm = (ucm.map.Map) o;
            }
        }
        return ucm;
    }
}