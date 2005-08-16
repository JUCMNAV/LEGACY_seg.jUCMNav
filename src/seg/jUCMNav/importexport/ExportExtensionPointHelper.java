package seg.jUCMNav.importexport;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;

/**
 * Helps access information from the seg.jUCMNav.UseCaseMapExport extension point.
 * 
 * @author jkealey
 */
public class ExportExtensionPointHelper {

    /**
     * 
     * @return an array of extensions for the seg.jUCMNav.UseCaseMapExport extensin point.
     */
    private static IExtension[] getExportExtensions() {
        IExtensionRegistry reg = Platform.getExtensionRegistry();
        IExtensionPoint ep = reg.getExtensionPoint("seg.jUCMNav.UseCaseMapExport");
        IExtension[] extensions = ep.getExtensions();
        return extensions;
    }

    /**
     * 
     * @return an ArrayList of IConfigurationElements extracted from the extensions
     */
    private static ArrayList getExportConfigurationElements() {
        IExtension[] extensions = getExportExtensions();
        ArrayList confElems = new ArrayList();

        for (int i = 0; i < extensions.length; i++) {
            IExtension ext = extensions[i];
            IConfigurationElement[] ce = ext.getConfigurationElements();
            for (int j = 0; j < ce.length; j++) {
                confElems.add(ce[j]);
            }

        }
        return confElems;
    }

    /**
     * 
     * @param id
     *            the unique id for the specific exporter
     * @return the associated exporter
     */
    private static IConfigurationElement getExportConfigurationElement(String id) {
        ArrayList elements = getExportConfigurationElements();
        for (Iterator iter = elements.iterator(); iter.hasNext();) {
            IConfigurationElement element = (IConfigurationElement) iter.next();
            if (element.getAttribute("id").equalsIgnoreCase(id))
                return element;
        }
        return null;
    }

    /**
     * 
     * @return an array of displayable names representing the configuration elements.
     */
    public static String[] getExportLabels() {
        ArrayList confElems = getExportConfigurationElements();
        ArrayList labels = new ArrayList();
        for (Iterator iter = confElems.iterator(); iter.hasNext();) {
            IConfigurationElement element = (IConfigurationElement) iter.next();
            labels.add(element.getAttribute("name"));
        }
        // we can't guarantee the uniqueness of the label so we wouldn't be able to return back to the unique ID if we sorted
        // we will have to rely on the UI to sort it if necessary.
        // Collections.sort(labels);
        String[] strings = new String[labels.size()];
        for (int i = 0; i < labels.size(); i++)
            strings[i] = labels.get(i).toString();

        return strings;
    }

    /**
     * 
     * @param index
     *            the index in getExportLabels()
     * @return the exporter's unique id
     */
    public static String getExporterFromLabelIndex(int index) {
        return ((IConfigurationElement) getExportConfigurationElements().get(index)).getAttribute("id");
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
            exporter = (IUseCaseMapExport) elem.createExecutableExtension("class");
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
        return elem.getAttribute("mode");
    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return the filename extension to be appended
     */
    public static String getFilenameExtension(String id) {
        IConfigurationElement elem = getExportConfigurationElement(id);
        return elem.getAttribute("extension");
    }

}
