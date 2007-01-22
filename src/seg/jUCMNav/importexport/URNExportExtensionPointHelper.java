package seg.jUCMNav.importexport;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

import seg.jUCMNav.extensionpoints.IURNExport;

/**
 * Helps access information from the seg.jUCMNav.URNExport extension point.
 * 
 * @author jkealey
 */
public class URNExportExtensionPointHelper extends ExportExtensionPointHelper {

    protected static final String sExtensionPoint = "seg.jUCMNav.URNExport"; //$NON-NLS-1$

    /* Facade for ExportExtensionPointHelper */
    protected static IConfigurationElement getExportConfigurationElement(String id) {
        return ExportExtensionPointHelper.getExportConfigurationElement(sExtensionPoint, id);
    }

    /* Facade for ExportExtensionPointHelper */
    protected static ArrayList getExportConfigurationElements() {
        return ExportExtensionPointHelper.getExportConfigurationElements(sExtensionPoint);
    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return an instance of the exporter defined in the class attribute
     */
    public static IURNExport getExporter(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        IURNExport exporter = null;
        try {
            exporter = (IURNExport) elem.createExecutableExtension("class"); //$NON-NLS-1$
        } catch (CoreException e) {
            // ignore
            e.printStackTrace();
        }
        return exporter;
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
    public static String[] getExportLabels() {
        return ExportExtensionPointHelper.getExportLabels(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String getFilenameExtension(String id) {
        return ExportExtensionPointHelper.getFilenameExtension(sExtensionPoint, id);

    }

    /* Facade for ExportExtensionPointHelper */
    public static boolean isUseStream(String id) {
        return ExportExtensionPointHelper.isUseStream(sExtensionPoint, id);
    }

    /**
     * Returns the id of the UCM exporter to be run on all maps after the URN export.
     * 
     * @param id
     *            the urn exporter's unique id
     * @return the ucm exporter's unique id
     */

    public static String getPostExporter(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        String postExporter = elem.getAttribute("postExporter"); //$NON-NLS-1$
        if (postExporter == null)
            postExporter = ""; //$NON-NLS-1$
        return postExporter;
    }

}
