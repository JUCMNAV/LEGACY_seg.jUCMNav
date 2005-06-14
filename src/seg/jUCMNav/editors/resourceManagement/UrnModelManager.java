package seg.jUCMNav.editors.resourceManagement;

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
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.model.util.URNReferencerChecker;
import ucm.map.impl.MapPackageImpl;
import urn.URNspec;

/**
 * Created 2005-02-11
 * 
 * This class is used to load and save the model from the file system.
 * 
 * This class is inspired from the IBM RedBook Eclipse Development using the Graphical Editing Framework and the Eclipse Modeling Framework
 * 
 * @author Etienne Tremblay
 */
public class UrnModelManager {
    /**
     * For the purpose of the simple editor, a file can only contain a UCM. In EMF, a resource provides the way to have access to the model content.
     */
    private Resource resource = null;

    /**
     * Gives access to the top level model element contained in the resource.
     */
    private URNspec model = null;

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
        ResourceSet resSet = getResourceSet();
        resource = resSet.createResource(URI.createPlatformResourceURI(path.toString()));
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
        // Register the XMI resource factory for the .ucm extension
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map m = reg.getExtensionToFactoryMap();
        m.put(Messages.getString("UrnModelManager.ucmExtension"), new XMIResourceFactoryImpl()); //$NON-NLS-1$
        // Obtain a new resource set
        return new ResourceSetImpl();
    }

    /**
     * Creates a new URNspec.
     * 
     * @param path
     * @return
     */
    public URNspec createURNspec(IPath path) {
        createResource(path);

        URNspec urnspec = (URNspec) ModelCreationFactory.getNewURNspec();

        resource.getContents().add(urnspec);
        return urnspec;
    }

    /**
     * Creates a new URNspec.
     * 
     * @param path
     * @param urnspec
     * @return
     */
    public URNspec createURNspec(IPath path, URNspec urnspec) {
        createResource(path);
        resource.getContents().add(urnspec);
        return urnspec;
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
        // latin 1
        options.put(XMIResource.OPTION_ENCODING, "ISO-8859-1");
                
        resource.save(options);
    }

    /**
     * Gets the top level model elements.
     * 
     * @return
     */
    public URNspec getModel() {
        if (null == model) {
            EList l = resource.getContents();
            Iterator i = l.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof URNspec)
                    model = (URNspec) o;
            }

            URNNamingHelper.sanitizeURNspec(model);
            URNReferencerChecker.sanitizeReferences(model);
        }
        return model;
    }

}