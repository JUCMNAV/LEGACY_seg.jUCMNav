package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import urn.URNspec;

/**
 * Encapsulates load/save of the strategy evaluation properties.
 * 
 * @author jkealey, sghanava, gunterm
 * 
 */
public class StrategyEvaluationPreferences {

    public final static int DEFAULT_TOLERANCE = 10;
    public final static boolean DEFAULT_EVALFILLED = true;
    public final static boolean DEFAULT_VISUALIZEASPOSITIVERANGE = false;
    public final static boolean DEFAULT_AUTOSELECTMANDATORYFEATURES = false;
    
    public final static int QUANTITATIVE_ALGORITHM = 0;
    public final static int QUALITATIVE_ALGORITHM = 1;
    public final static int MIXED_ALGORITHM = 2;
    public final static int FORMULA_BASED_ALGORITHM = 3;
    public final static int CONSTRAINT_SOLVER_ALGORITHM = 4;
    public final static int CONDITIONAL_GRL_ALGORITHM = 5;
    public final static int FEATURE_MODEL_ALGORITHM = 6;


    public final static int DEFAULT_GRL_ALGORITHM = 0; // this should always be 0
    public final static int NUM_ALGORITHMS = 7;

    public static final String PREF_ALGORITHM = "PREF_ALGORITHM"; //$NON-NLS-1$    
    public static final String PREF_TOLERANCE = "PREF_TOLERANCE"; //$NON-NLS-1$
    public static final String PREF_EVALFILLED = "PREF_EVALFILLED"; //$NON-NLS-1$
    public static final String PREF_VISUALIZEASPOSITIVERANGE = "PREF_VISUALIZEASPOSITIVERANGE"; //$NON-NLS-1$
    public static final String PREF_AUTOSELECTMANDATORYFEATURES = "PREF_AUTOSELECTMANDATORYFEATURES"; //$NON-NLS-1$ 
	
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
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_ALGORITHM, StrategyEvaluationPreferences.DEFAULT_GRL_ALGORITHM + ""); //$NON-NLS-1$
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_TOLERANCE, StrategyEvaluationPreferences.DEFAULT_TOLERANCE);
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_EVALFILLED, StrategyEvaluationPreferences.DEFAULT_EVALFILLED);
        getPreferenceStore().setDefault(StrategyEvaluationPreferences.PREF_VISUALIZEASPOSITIVERANGE, StrategyEvaluationPreferences.DEFAULT_VISUALIZEASPOSITIVERANGE);
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
     * @return should we auto select mandatory features?
     */
    public static boolean getAutoSelectMandatoryFeatures() {
    	return getPreferenceStore().getBoolean(PREF_AUTOSELECTMANDATORYFEATURES);
    }

    /**
     * 
     * @return should we visualize -100 to 100 as a positive range (0 to 100)?
     */
    public static boolean getVisualizeAsPositiveRange(URNspec urn) {
        if (urn == null) {
        	// this if statement was added to support the CORE interface; when jUCMNav is accessed through the CORE interface,
        	// the plugin environment is not defined which causes a null pointer exception here
        	if (COREFactory4URN.isCOREInterfaceActive())
        		return COREFactory4URN.POSITIVE_RANGE;
        	return getPreferenceStore().getBoolean(PREF_VISUALIZEASPOSITIVERANGE);
        }
        else
        {
            return StrategyEvaluationRangeHelper.getCurrentRange(urn);
        }
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

    /**
     * 
     * @param b
     *            should we visualize -100 to 100 as a positive range (0 to 100)?
     */
    public static void setVisualizeAsPositiveRange(boolean b) {
        getPreferenceStore().setValue(PREF_VISUALIZEASPOSITIVERANGE, b);
    }
    
    /**
     * If we should visualize our internal ranges [-100 to 100] as [0, 100], converts a value from the former to the latter. 
     * 
     * @param modelValue
     * @return
     */
    public static int getEquivalentValueIn0To100RangeIfApplicable(URNspec urn, int modelValue)
    {
        if (!getVisualizeAsPositiveRange(urn) || modelValue == IGRLStrategyAlgorithm.CONFLICT || modelValue == IGRLStrategyAlgorithm.UNDECIDED)
            return modelValue;
        else
            
            return (int)Math.round(((double)(modelValue + 100))/2); 
    }
    
    /**
     * If we should visualize our ranges [-100 to 100] as [0, 100], converts a value from the latter to the former. 
     * 
     * @param viewValue
     * @return
     */
    public static int getEquivalentValueInFullRangeIfApplicable(URNspec urn, int viewValue)
    {
        if (!getVisualizeAsPositiveRange(urn) || viewValue == IGRLStrategyAlgorithm.CONFLICT || viewValue == IGRLStrategyAlgorithm.UNDECIDED)
            return viewValue;
        else 
            
            return viewValue * 2 - 100; 
    }
}
