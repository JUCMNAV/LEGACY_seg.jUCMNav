/*
 * Created on 2005-02-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmFactory;
import seg.jUCMNav.model.ucm.UcmPackage;
import seg.jUCMNav.model.ucm.impl.UcmPackageImpl;

/**
 * Created 2005-02-11
 * 
 * @author Etienne Tremblay
 */
public class UcmModelManager {
	/**
	 * For the purpose of the simple editor, a file can only contain a network.
	 * In EMF, a resource provides the way to have access to the model content.
	 */
	private Resource resource = null;

	/**
	 * Contains the factory associated with the model.
	 */
	private static UcmFactory networkFactory = null;

	/**
	 * Gives access to the top level network contained in the resource.
	 */
	private UcmDiagram ucm = null;

	/**
	 * Returns the resource containing the network. Uses lazy initialization.
	 * @param path
	 * @return
	 */
	public Resource getResource(IPath path) {
		if (resource == null) {
			ResourceSet resSet = getResourceSet();
			resource =
				resSet.getResource(
					URI.createPlatformResourceURI(path.toString()),
					true);
		}
		return resource;
	}

	/**
	 * Creates a resource to contain the network. The resource file does not exist yet.
	 * @param path
	 * @return
	 */
	private Resource createResource(IPath path) {
		if (resource == null) {
			ResourceSet resSet = getResourceSet();
			resource =
				resSet.createResource(
					URI.createPlatformResourceURI(path.toString()));
		}
		return resource;
	}

	/**
	 * Returns the resource set.
	 * @param 
	 * @return
	 */
	private ResourceSet getResourceSet() {
		// Initialize the network package
		UcmPackageImpl.init();
		// Register the XMI resource factory for the .network extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map m = reg.getExtensionToFactoryMap();
		m.put("net", new XMIResourceFactoryImpl());
		// Obtain a new resource set
		return new ResourceSetImpl();
	}

	/**
	 * Returns the factory associated with the model.
	 * Object creation are made through that factory.
	 * @return
	 */
	static public UcmFactory getFactory() {
		if (networkFactory == null) {
			// Access the factory (needed to create instances)
			Map registry = EPackage.Registry.INSTANCE;
			String networkURI = UcmPackage.eNS_URI;
			UcmPackage ucmPackage =
				(UcmPackage) registry.get(networkURI);
			networkFactory = ucmPackage.getUcmFactory();
		}
		return networkFactory;
	}

	/**
	 * Creates a new network.
	 * @param 
	 * @return
	 */
	public UcmDiagram createDiagram(IPath path) {
		createResource(path);
		// Create a new network model
		Map registry = EPackage.Registry.INSTANCE;
		String UcmURI = UcmPackage.eNS_URI;
		UcmPackage nPackage = (UcmPackage) registry.get(UcmURI);
		UcmFactory nFactory = nPackage.getUcmFactory();
		ucm = nFactory.createUcmDiagram();
		resource.getContents().add(ucm);
		return ucm;
	}

	/**
	 * Loads the content of the model from the file.
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
     * @param path
     */
    public void reload(IPath path) throws IOException {
        getResource(path).unload();
        load(path);
    }

	/**
	 * Saves the content of the model to the file.
	 * @param path
	 */
	public void save(IPath path) throws IOException {
		getResource(path);
		Map options = new HashMap();
		options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
		resource.save(options);
	}

	/**
	 * Gets the top level network model.
	 * @return
	 */
	public UcmDiagram getModel() {
		if (null == ucm) {
			EList l = resource.getContents();
			Iterator i = l.iterator();
			while (i.hasNext()) {
				Object o = i.next();
				if (o instanceof UcmDiagram)
					ucm = (UcmDiagram) o;
			}
		}
		return ucm;
	}
}
