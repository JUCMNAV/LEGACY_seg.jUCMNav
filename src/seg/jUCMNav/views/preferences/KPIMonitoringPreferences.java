package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Encapsulates load/save of the kpi monitoring properties.
 * 
 * @author pchen
 * 
 */
public class KPIMonitoringPreferences {
    public final static String DEFAULT_WEBSERVICEADDRESS = "http://localhost:8080/SampleService/SampleServiceBean"; //$NON-NLS-1$

    public final static String PREF_WEBSERVICEADDRESS = "seg.jUCMNav.KPIMonitoring.WebServiceAddress"; //$NON-NLS-1$

    /**
     * 
     * @return Preference store where the properties are stored.
     */
    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {
        getPreferenceStore().setDefault(KPIMonitoringPreferences.PREF_WEBSERVICEADDRESS, KPIMonitoringPreferences.DEFAULT_WEBSERVICEADDRESS);
    }

    /**
     * 
     * @return the address of kpi monitoring web service
     */
    public static String getWebServiceAddress() {
        return getPreferenceStore().getString(PREF_WEBSERVICEADDRESS);
    }

    /**
     * 
     * @param address
     *            the address of kpi monitoring web service
     */
    public static void setWebServiceAddress(String address) {
        getPreferenceStore().setValue(PREF_WEBSERVICEADDRESS, address);
    }

}
