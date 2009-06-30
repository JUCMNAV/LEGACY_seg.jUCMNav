package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * Encapsulates load/save of the strategy evaluation properties. 
 * 
 * @author jkealey, sghanava
 * 
 */
public class StrategyEvaluationPreferences {

    public final static int DEFAULT_TOLERANCE = 10;
    public final static boolean DEFAULT_EVALFILLED = true;
    
    public final static int QUANTITATIVE_ALGORITHM = 0;
    public final static int QUALITATIVE_ALGORITHM = 1;
    public final static int MIXED_ALGORITHM = 2;
    public final static int ROY_2006_GRL_ALGORITHM = 3;
    
    public final static int DEFAULT_GRL_ALGORITHM = 0; //this should always be 0
    public final static int NUM_ALGORITHMS = 4;
    

    public static final String PREF_ALGORITHM = "PREF_ALGORITHM"; //$NON-NLS-1$    
    public static final String PREF_TOLERANCE = "PREF_TOLERANCE"; //$NON-NLS-1$
    public static final String PREF_EVALFILLED = "PREF_EVALFILLED"; //$NON-NLS-1$

    
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
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_ALGORITHM, StrategyEvaluationPreferences.DEFAULT_GRL_ALGORITHM+"");    	 //$NON-NLS-1$
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_TOLERANCE, StrategyEvaluationPreferences.DEFAULT_TOLERANCE);
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_EVALFILLED, StrategyEvaluationPreferences.DEFAULT_EVALFILLED);
    }

    /**
     * 
     * @return the grl evaluation algorithm tolerance  
     */
    public static int getTolerance() {
        return getPreferenceStore().getInt(PREF_TOLERANCE);
    }

    /**
     * 
     * @return should we fill evaluated elements?
     */
    public static boolean getFillElements() {
        return getPreferenceStore().getBoolean(PREF_EVALFILLED);
    }

    /**
     * 
     * @return the grl strategy evaluation algorithm
     */
    public static String getAlgorithm() {
        return getPreferenceStore().getString(PREF_ALGORITHM);
    }

    /**
     * 
     * @param tolerance
     *            the grl evaluation algorithm tolerance
     */
    public static void setTolerance(int tolerance) {
        getPreferenceStore().setValue(PREF_TOLERANCE, tolerance);
    }

    /**
     * 
     * @param b
     *            should we fill evaluated elements?
     */
    public static void setFillElements(boolean b) {
        getPreferenceStore().setValue(PREF_EVALFILLED, b);
    }
    
    /**
     * 
     * @param b
     *            set the GRL evaluation strategy
     */
    public static void setAlgorithm(String b) {
        getPreferenceStore().setValue(PREF_ALGORITHM, b);
    }
    

}
