package seg.jUCMNav.views.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Class used to initialize default preference values.
 * 
 * @author jkealey
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    /**
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public void initializeDefaultPreferences() {
        IPreferenceStore store = JUCMNavPlugin.getDefault().getPreferenceStore();
        PreferenceConverter.setDefault(store, GeneralPreferencePage.PREF_STUBLABELCOLOR, new RGB(150, 0, 150));
        PreferenceConverter.setDefault(store, GeneralPreferencePage.PREF_CONDITIONLABELCOLOR, new RGB(100, 100, 100));
        PreferenceConverter.setDefault(store, GeneralPreferencePage.PREF_LINKREFLABELCOLOR, new RGB(150,150,150));

        JUCMNavPlugin.getDefault().getPreferenceStore().setDefault(GeneralPreferencePage.PREF_AUTHOR, System.getProperty("user.name")); //$NON-NLS-1$

        // done elsewhere
        AutoLayoutPreferences.createPreferences();

    }

}
