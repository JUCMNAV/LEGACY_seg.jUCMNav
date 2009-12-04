package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.model.util.URNReferencerChecker;
import ucm.map.impl.MapPackageImpl;
import urn.URNspec;

/**
 * This class is used to load and save the {@link URNspec} model from the file system.
 * 
 * This class is inspired from the IBM RedBook Eclipse Development using the Graphical Editing Framework and the Eclipse Modeling Framework
 * 
 * @author Etienne Tremblay
 */
public class UrnModelManager extends EmfModelManager {
    /**
     * Creates a new URNspec.
     * 
     * @param path
     *            where should it be created
     * @return a new URNspec
     */
    public URNspec createURNspec(File path) {
        createResource(path);

        URNspec urnspec = ModelCreationFactory.getNewURNspec();

        resource.getContents().add(urnspec);
        return urnspec;
    }

    /**
     * Creates a new URNspec.
     * 
     * @param path
     *            where should it be created
     * @param urnspec
     *            the file's contents
     * @return a new URNspec
     */
    public URNspec createURNspec(File path, URNspec urnspec) {
        createResource(path);
        resource.getContents().add(urnspec);
        return urnspec;
    }

    /**
     * Creates a new URNspec.
     * 
     * @param path
     *            where should it be created
     * @return a new URNspec
     */
    public URNspec createURNspec(IPath path) {
        createResource(path);

        URNspec urnspec = ModelCreationFactory.getNewURNspec();

        resource.getContents().add(urnspec);
        return urnspec;
    }

    /**
     * Creates a new URNspec.
     * 
     * @param path
     *            where should it be created
     * @param urnspec
     *            the file's contents
     * @return a new URNspec
     */
    public URNspec createURNspec(IPath path, URNspec urnspec) {
        createResource(path);
        resource.getContents().add(urnspec);
        return urnspec;
    }

    /**
     * The file extension for jUCMNav.
     */
    protected String getFileExtension() {
        return Messages.getString("UrnModelManager.ucmExtension"); //$NON-NLS-1$
    }

    /**
     * Gets the top level model elements.
     * 
     * @return top level model elements
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

            // clean the loaded file to make sure its semantics are valid.
            URNNamingHelper.sanitizeURNspec((URNspec) model);
            URNReferencerChecker.sanitizeReferences((URNspec) model);
        }
        return (URNspec) model;
    }

    /**
     * Initialize EMF
     */
    protected void init() {
        // Initialize the ucm package
        MapPackageImpl.init();

    }

}