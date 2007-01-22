package seg.jUCMNav.importexport;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;

/**
 * Helps access information from the seg.jUCMNav.UseCaseMapExport extension point.
 * 
 * @author jkealey
 */
public class UCMExportExtensionPointHelper extends ExportExtensionPointHelper {

    protected static final String sExtensionPoint = "seg.jUCMNav.UseCaseMapExport"; //$NON-NLS-1$

    /* Facade for ExportExtensionPointHelper */
    protected static IExtension[] getExportExtensions() {
        return ExportExtensionPointHelper.getExportExtensions(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    protected static ArrayList getExportConfigurationElements() {
        return ExportExtensionPointHelper.getExportConfigurationElements(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    protected static IConfigurationElement getExportConfigurationElement(String id) {
        return ExportExtensionPointHelper.getExportConfigurationElement(sExtensionPoint, id);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String[] getExportLabels() {
        return ExportExtensionPointHelper.getExportLabels(sExtensionPoint);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String getExporterFromLabelIndex(int index) {
        return ExportExtensionPointHelper.getExporterFromLabelIndex(sExtensionPoint, index);
    }

    /* Facade for ExportExtensionPointHelper */
    public static String getFilenameExtension(String id) {
        return ExportExtensionPointHelper.getFilenameExtension(sExtensionPoint, id);

    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return an instance of the exporter defined in the class attribute
     */
    public static IUseCaseMapExport getExporter(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        IUseCaseMapExport exporter = null;
        try {
            exporter = (IUseCaseMapExport) elem.createExecutableExtension("class"); //$NON-NLS-1$
        } catch (CoreException e) {
            // ignore
            e.printStackTrace();
        }
        return exporter;
    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return the export mode
     */
    public static String getMode(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        return elem.getAttribute("mode"); //$NON-NLS-1$
    }

    /* Facade for ExportExtensionPointHelper */
    public static boolean isUseStream(String id) {
        return ExportExtensionPointHelper.isUseStream(sExtensionPoint, id);
    }

}
