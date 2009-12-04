package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Encapsulates load/save of the scenario traversal properties.
 * 
 * @author jkealey
 * 
 */
public class ScenarioTraversalPreferences {

    public final static int DEFAULT_MAXHITCOUNT = 1000;
    public final static boolean DEFAULT_ISPATIENTONPRECONDITIONS = true;
    public final static boolean DEFAULT_DETERMINISTIC = true;
    public final static boolean DEFAULT_INTEGRATESTRATEGYVARIABLES = false;

    public final static String PREF_MAXHITCOUNT = "seg.jUCMNav.ScenarioTraversal.MaxHitCount"; //$NON-NLS-1$
    public final static String PREF_ISPATIENTONPRECONDITIONS = "seg.jUCMNav.ScenarioTraversal.IsPatientOnPreconditions"; //$NON-NLS-1$
    public final static String PREF_ISDETERMINISTIC = "seg.jUCMNav.ScenarioTraversal.Deterministic"; // $NON-NLS-1$ //$NON-NLS-1$
    public final static String PREF_INTEGRATESTRATEGYVARIABLES = "seg.jUCMNav.ScenarioExport.IntegrateStrategyVariables"; // $NON-NLS-1$ //$NON-NLS-1$

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
        getPreferenceStore().setDefault(ScenarioTraversalPreferences.PREF_MAXHITCOUNT, ScenarioTraversalPreferences.DEFAULT_MAXHITCOUNT);
        getPreferenceStore().setDefault(ScenarioTraversalPreferences.PREF_ISPATIENTONPRECONDITIONS,
                ScenarioTraversalPreferences.DEFAULT_ISPATIENTONPRECONDITIONS);
        getPreferenceStore().setDefault(ScenarioTraversalPreferences.PREF_ISDETERMINISTIC, ScenarioTraversalPreferences.DEFAULT_DETERMINISTIC);
        getPreferenceStore().setDefault(ScenarioTraversalPreferences.PREF_INTEGRATESTRATEGYVARIABLES,
                ScenarioTraversalPreferences.DEFAULT_INTEGRATESTRATEGYVARIABLES);
    }

    /**
     * 
     * @return the number of times an element can be hit before we declare it blocked.
     */
    public static int getMaxHitCount() {
        return getPreferenceStore().getInt(PREF_MAXHITCOUNT);
    }

    /**
     * 
     * @return should we block and wait on false pre-conditions?
     */
    public static boolean getIsPatientOnPreconditions() {
        return getPreferenceStore().getBoolean(PREF_ISPATIENTONPRECONDITIONS);
    }

    /**
     * 
     * @return is the algorithm deterministic
     */
    public static boolean getIsDeterministic() {
        return getPreferenceStore().getBoolean(PREF_ISDETERMINISTIC);
    }

    /**
     * 
     * @return should we integrate strategy intentional elements as scenario variables
     */
    public static boolean getShouldIntegrateStrategyVariables() {
        return getPreferenceStore().getBoolean(PREF_INTEGRATESTRATEGYVARIABLES);
    }

    /**
     * 
     * @param count
     *            the width parameter to give dot
     */
    public static void setMaxHitCount(int count) {
        getPreferenceStore().setValue(PREF_MAXHITCOUNT, count);
    }

    /**
     * 
     * @param b
     *            should we block and wait on false pre-conditions?
     */
    public static void setIsPatientOnPreconditions(boolean b) {
        getPreferenceStore().setValue(PREF_ISPATIENTONPRECONDITIONS, b);
    }

    /**
     * 
     * @param b
     *            is the algorithm deterministic
     */
    public static void setIsDeterministic(boolean b) {
        getPreferenceStore().setValue(PREF_ISDETERMINISTIC, b);
    }

    /**
     * 
     * @param b
     *            should we integrate strategy intentional elements as variables for scenarios?
     */
    public static void setShouldIntegrateStrategyVariables(boolean b) {
        getPreferenceStore().setValue(PREF_INTEGRATESTRATEGYVARIABLES, b);
    }

}
