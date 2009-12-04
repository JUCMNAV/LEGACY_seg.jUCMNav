package seg.jUCMNav.importexport;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

import seg.jUCMNav.extensionpoints.IURNImport;

/**
 * Helps access information from the seg.jUCMNav.URNImport extension point.
 * 
 * We chose to extend the existing export extension point infrastructure, even though the method names don't make much sense in the context of importing.
 * 
 * @author jkealey
 */
public class URNImportExtensionPointHelper extends ExportExtensionPointHelper {

    protected static final String sExtensionPoint = "seg.jUCMNav.URNImport"; //$NON-NLS-1$

    /* Facade for ExportExtensionPointHelper */
    protected static IConfigurationElement getExportConfigurationElement(String id) {
        return ExportExtensionPointHelper.getExportConfigurationElement(sExtensionPoint, id);
    }

    /* Facade for ExportExtensionPointHelper */
    protected static ArrayList getExportConfigurationElements() {
        return ExportExtensionPointHelper.getExportConfigurationElements(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String getExporterFromLabelIndex(int index) {
        return ExportExtensionPointHelper.getExporterFromLabelIndex(sExtensionPoint, index);
    }

    /* Facade for ExportExtensionPointHelper */
    protected static IExtension[] getExportExtensions() {
        return ExportExtensionPointHelper.getExportExtensions(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String getFilenameExtension(String id) {
        return ExportExtensionPointHelper.getFilenameExtension(sExtensionPoint, id);
    }

    /**
     * 
     * @param id
     *            the importer's unique id
     * @return an instance of the exporter defined in the class attribute
     */
    public static IURNImport getImporter(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        IURNImport importer = null;
        try {
            importer = (IURNImport) elem.createExecutableExtension("class"); //$NON-NLS-1$
        } catch (CoreException e) {
            // ignore
            e.printStackTrace();
        }
        return importer;
    }

    /* Facade for ExportExtensionPointHelper */
    public static String[] getImportLabels() {
        return ExportExtensionPointHelper.getExportLabels(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    public static boolean isUseStream(String id) {
        return ExportExtensionPointHelper.isUseStream(sExtensionPoint, id);
    }

    /* Facade for ExportExtensionPointHelper */
    public static boolean isImportInSelectedFile(String id) {
        return ExportExtensionPointHelper.isImportInSelectedFile(sExtensionPoint, id);
    }
}
