package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

import ucmscenarios.impl.UcmscenariosPackageImpl;
import org.etsi.mts.tdl.*;
import org.etsi.mts.tdl.impl.*;
import org.etsi.mts.tdl.Package;

/**
 * This class is used to load and save the tdl model from the file system.
 * 
 * @see Package
 * @see EmfModelManager
 * @author Patrice Boulet
 */
public class TdlModelManager extends EmfModelManager {

    /**
     * Creates a new Package.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new Package
     */
    public Package createPackage(File path) {
        createResource(path);

        Package tdlPackage = org.etsi.mts.tdl.TdlFactory.eINSTANCE.createPackage();

        resource.getContents().add(tdlPackage);
        return tdlPackage;
    }

    /**
     * Creates a new Package.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param tdlPackage
     *            the initial contents of the file
     * @return a new Package
     */
    public Package createPackage(File path, Package tdlPackage) {
        createResource(path);
        resource.getContents().add(tdlPackage);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tdlPackage;
    }

    /**
     * Creates a new Package.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new Package
     */
    public Package createPackage(IPath path) {
        createResource(path);

        Package tdlPackage = org.etsi.mts.tdl.TdlFactory.eINSTANCE.createPackage();

        resource.getContents().add(tdlPackage);
        return tdlPackage;
    }

    /**
     * Creates a new Package.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param tdlPackage
     *            the initial contents of the file
     * @return a new Package
     */
    public Package createPackage(IPath path, Package tdlPackage) {
        createResource(path);
        resource.getContents().add(tdlPackage);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tdlPackage;
    }

    /**
     * Returns the Package serialization filename.
     */
    protected String getFileExtension() {
        return "tdl"; //$NON-NLS-1$

    }

    /**
     * Gets the top level model elements.
     * 
     * @return top level model elements
     */
    public Package getModel() {
        if (null == model) {
            EList l = resource.getContents();
            Iterator i = l.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof Package)
                    model = (Package) o;
            }

        }
        return (Package) model;
    }

    /**
     * Initialize EMF
     */
    protected void init() {
        // Initialize the ucm package
        UcmscenariosPackageImpl.init();

    }

}