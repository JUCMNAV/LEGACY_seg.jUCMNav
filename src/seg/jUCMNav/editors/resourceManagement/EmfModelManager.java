package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.util.CoreResourceFactoryImpl;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.model.util.URNReferencerChecker;
import urn.URNspec;

/**
 * This class is used to load and save the model from the file system.
 * 
 * This class is inspired from the IBM RedBook Eclipse Development using the Graphical Editing Framework and the Eclipse Modeling Framework
 * 
 * TODO: remove some of the code duplication; the IPath calls are for files inside the currently workspace and the ones that use File are for import/export
 * outside the workspace.
 * 
 * @author Etienne Tremblay, jkealey
 */
public abstract class EmfModelManager {

    /**
     * Gives access to the top level model element contained in the resource.
     */
    protected EObject model = null;
    /**
     * In EMF, a resource provides the way to have access to the model content.
     */
    protected Resource resource = null;
    /**
     * The extension of the CORE file with which a concern-oriented jucm file is synchronized.
     */    
    public static String CORE_FILE_EXTENSION = "core";

    /**
     * Creates a resource to contain the model. The resource file does not exist yet.
     * 
     * @param path
     *            path to the newly created resource
     * @return resource to contain the model
     */
    protected Resource createResource(File path) {
        ResourceSet resSet = getResourceSet();
        resource = resSet.createResource(URI.createFileURI(path.toString()));
        return resource;
    }

    /**
     * Creates a resource to contain the model. The resource file does not exist yet.
     * 
     * @param path
     *            path to the newly created resource
     * @return resource to contain the model
     */
    protected Resource createResource(IPath path) {
        ResourceSet resSet = getResourceSet();
        resource = resSet.createResource(URI.createPlatformResourceURI(path.toString(), true));
        return resource;
    }

    /**
     * The file extension.
     * 
     * @return a string representing the file extension of our EMF serialization.
     */
    protected abstract String getFileExtension();

    /**
     * Returns the resource containing the model. Uses lazy initialization.
     * 
     * @param path
     *            path to the requested resource
     * @return resource containing the model
     */
    public Resource getResource(File path) {
        if (resource == null) {

            ResourceSet resSet = getResourceSet();
            // ,true added as the other createPlatformResourceURI is deprecated
            resource = resSet.getResource(URI.createFileURI(path.toString()), true);
        }
        return resource;
    }

    /**
     * Returns the resource containing the model. Uses lazy initialization.
     * 
     * @param path
     *            path to the requested resource
     * @return resource containing the model
     */
    public Resource getResource(IPath path) {
        if (resource == null) {

            ResourceSet resSet = getResourceSet();
            // ,true added as the other createPlatformResourceURI is deprecated
            resource = resSet.getResource(URI.createPlatformResourceURI(path.toString(), true), true); // ,true added
        }
        return resource;
    }

    /**
     * Returns the resource set.
     * 
     * @return the resource set
     */
    protected ResourceSet getResourceSet() {       
        init();
        // Register the XMI resource factory for the .ucm extension
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map m = reg.getExtensionToFactoryMap();
        m.put(getFileExtension(), new XMIResourceFactoryImpl());
        m.put(CORE_FILE_EXTENSION , new CoreResourceFactoryImpl());
        // Obtain a new resource set
        return new ResourceSetImpl();
    }

    /**
     * Initializes packages before we can use EMF classes.
     * 
     */
    protected abstract void init();

    /**
     * Loads the content of the model from the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public void load(File path) throws IOException {
        getResource(path);
        Map options = new HashMap();

        options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
        resource.load(options);
    }

    /**
     * Loads the content of the model from the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public void load(IPath path) throws IOException {
        getResource(path);
        Map options = new HashMap();

        options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
        resource.load(options);
    }

    /**
     * reloads the content of the model from the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public void reload(File path) throws IOException {
        getResource(path).unload();
        load(path);
    }

    /**
     * reloads the content of the model from the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public URNspec reload(IPath path) throws IOException {
        getResource(path).unload();
        load(path);
        EcoreUtil.resolveAll( getResource(path).getResourceSet());
        EObject model = null;
		EList l = resource.getContents();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (o instanceof URNspec)
                model = (URNspec) o;
        }
        ResourceSet resourceSet = getResource(path).getResourceSet();
        for(Iterator it = resourceSet.getResources().iterator(); it.hasNext();){
        	 EList list = ((Resource)it.next()).getContents();
        	 Iterator iterator = list.iterator();
        	 while( iterator.hasNext()){
        		 Object o = i.next();
        		 if( o instanceof COREConcern && model instanceof URNspec)
        			 ((URNspec) model).getUrndef().getConcerns().set( 0, (COREConcern)o );
        	 }
        }
        
        if (model == null)return null;
        // clean the loaded file to make sure its semantics are valid.
        URNNamingHelper.sanitizeURNspec((URNspec) model);
        URNReferencerChecker.sanitizeReferences((URNspec) model);
        return (URNspec)model;
        
    }

    /**
     * Saves the content of the model to the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public void save(File path) throws IOException {
        getResource(path);
        Map options = new HashMap();
        options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
        // latin 1
        options.put(XMLResource.OPTION_ENCODING, "ISO-8859-1"); //$NON-NLS-1$

        resource.save(options);
    }
    
    /**
     * Load the core concern model from the file
     * if not exist, create it 
     * @param path
     *            path to the requested resource
     */
    public EObject loadModel(String file)
    {
    	
    	EList<Resource> resources = getResourceSet().getResources();
    	
    	Resource resource = getResourceSet().getResource( URI.createFileURI( new File(file).getAbsolutePath()), true);
    	return resource.getContents().get(0);
    	
    }
    public void saveModel(EObject object, String file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Supplied file is invalid: " + file);
        }

        // Create a resource
        Resource resource = getResourceSet().createResource(URI.createFileURI(file));

        // Add the resources to the resource to be saved.
        resource.getContents().add(object);

        // Now save the content.
        resource.save(Collections.EMPTY_MAP);
        
    }

    /**
     * Saves the content of the model to the file.
     * 
     * @param path
     *            path to the requested resource
     */
    public void save(IPath path) throws IOException {
        getResource(path);
        Map options = new HashMap();
        options.put(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE);
        // latin 1
        options.put(XMLResource.OPTION_ENCODING, "ISO-8859-1"); //$NON-NLS-1$

        resource.save(options);
    }

}
