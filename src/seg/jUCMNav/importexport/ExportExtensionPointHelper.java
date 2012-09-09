package seg.jUCMNav.importexport;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Helps access information from the seg.jUCMNav.UseCaseMapExport and seg.jUCMNav.URNExport extension points.
 * 
 * @author jkealey
 */

public abstract class ExportExtensionPointHelper {

    /**
     * 
     * @return an array of extensions for an extension point
     */
    protected static IExtension[] getExportExtensions(String sExtensionPoint) {
        IExtensionRegistry reg = Platform.getExtensionRegistry();
        IExtensionPoint ep = reg.getExtensionPoint(sExtensionPoint);
        IExtension[] extensions = ep.getExtensions();
        return extensions;
    }

    /**
     * 
     * @return an ArrayList of IConfigurationElements extracted from the extensions
     */
    protected static ArrayList getExportConfigurationElements(String sExtensionPoint) {
        IExtension[] extensions = getExportExtensions(sExtensionPoint);
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
    protected static IConfigurationElement getExportConfigurationElement(String sExtensionPoint, String id) {
        ArrayList elements = getExportConfigurationElements(sExtensionPoint);
        for (Iterator iter = elements.iterator(); iter.hasNext();) {
            IConfigurationElement element = (IConfigurationElement) iter.next();
            if (element.getAttribute("id").equalsIgnoreCase(id)) //$NON-NLS-1$
                return element;
        }
        return null;
    }

    /**
     * 
     * @return an array of displayable names representing the configuration elements.
     */
    public static String[] getExportLabels(String sExtensionPoint) {
        ArrayList confElems = getExportConfigurationElements(sExtensionPoint);
        ArrayList labels = new ArrayList();
        for (Iterator iter = confElems.iterator(); iter.hasNext();) {
            IConfigurationElement element = (IConfigurationElement) iter.next();
            labels.add(element.getAttribute("name")); //$NON-NLS-1$
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
    public static String getExporterFromLabelIndex(String sExtensionPoint, int index) {
        ArrayList confElems = getExportConfigurationElements(sExtensionPoint);
        ArrayList labels = new ArrayList();
        for (Iterator iter = confElems.iterator(); iter.hasNext();) {
            IConfigurationElement element = (IConfigurationElement) iter.next();
            labels.add(element.getAttribute("name")); //$NON-NLS-1$
        }            
        if (index >= labels.size())
          index = 0;
      
        return ((IConfigurationElement) getExportConfigurationElements(sExtensionPoint).get(index)).getAttribute("id"); //$NON-NLS-1$
    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return the filename extension to be appended
     */
    public static String getFilenameExtension(String sExtensionPoint, String id) {
        IConfigurationElement elem = getExportConfigurationElement(sExtensionPoint, id);
        return elem.getAttribute("extension"); //$NON-NLS-1$
    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return true if import should be done in selected file, false if not
     */
    public static boolean isImportInSelectedFile(String sExtensionPoint, String id) {
        IConfigurationElement elem = getExportConfigurationElement(sExtensionPoint, id);
        String attrib = elem.getAttribute("importInSelectedFile"); //$NON-NLS-1$
        return ((attrib != null) && attrib.equalsIgnoreCase("true")); //$NON-NLS-1$

    }

    /**
     * 
     * @param id
     *            the exporter's unique id
     * @return true if a stream should be used, false if a filename should be used.
     */
    public static boolean isUseStream(String sExtensionPoint, String id) {
        IConfigurationElement elem = getExportConfigurationElement(sExtensionPoint, id);
        String attrib = elem.getAttribute("useStream"); //$NON-NLS-1$
        return ((attrib != null) && attrib.equalsIgnoreCase("true")); //$NON-NLS-1$

    }
}
