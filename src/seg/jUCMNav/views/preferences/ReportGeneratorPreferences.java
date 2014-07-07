package seg.jUCMNav.views.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * General preferences page for the Report Generator.
 * 
 * @author Alain Dessureault
 * 
 */
public class ReportGeneratorPreferences {

    public final static double DEFAULT_REPORT_HEIGHT = 11;
    public final static String DEFAULT_REPORT_ORIENTATION = "TB"; //$NON-NLS-1$
    // TODO change default color
    public final static String DEFAULT_REPORT_COLOR = "white"; //$NON-NLS-1$
    public final static double DEFAULT_REPORT_WIDTH = 8.5;
    public final static int DEFAULT_NUMBER_CSV_COLUMNS = 7;
    public final static String PREF_REPORT_HEIGHT = "seg.jUCMNav.ReportPreference.Height"; //$NON-NLS-1$
    public final static String PREF_REPORT_ORIENTATION = "seg.jUCMNav.ReportPreference.Orientation"; //$NON-NLS-1$
    //public final static String PREF_REPORT_COLOR = "seg.jUCMNav.ReportPreference.Color";//$NON-NLS-1$
    public final static String PREF_REPORT_WIDTH = "seg.jUCMNav.ReportPreference.Width"; //$NON-NLS-1$
    public final static String PREF_NUMBER_CSV_COLUMNS = "seg.jUCMNav.ReportPreference.NumberCSV_Columns"; //$NON-NLS-1$

    public final static boolean DEFAULT_UCM_SHOW_UCM_DIAGRAMS = true;
    public final static boolean DEFAULT_GRL_SHOW_GRL_DIAGRAMS = true;
    
    public final static boolean DEFAULT_UCM_SHOW_DESC = true;
    public final static boolean DEFAULT_UCM_SHOW_RESPONSIBILITY = true;
    public final static boolean DEFAULT_UCM_SHOW_STUB = true;
    public final static boolean DEFAULT_UCM_SHOW_OR_FORK = true;
    // public final static boolean DEFAULT_UCM_SHOW_AND_FORK = true;
    public final static boolean DEFAULT_UCM_SHOW_START_POINT = true;
    public final static boolean DEFAULT_UCM_SHOW_END_POINT = true;
    public final static boolean DEFAULT_GRL_SHOW_INTENTIONAL_ELEMENTS = true;
    public final static boolean DEFAULT_GRL_SHOW_BELIEFS = true;
    public final static boolean DEFAULT_GRL_SHOW_URN_LINKS = true;

    public final static boolean DEFAULT_GRL_SHOW_EVALS = true;
    public final static boolean DEFAULT_KPI_SHOW_EVALS = false;
    public final static boolean DEFAULT_GRL_SHOW_EVAL_STRATEGY_TREND = false;
    public final static int DEFAULT_GRL_EVAL_STRATEGY_TREND = 3;
    public final static boolean DEFAULT_SHOW_SCENARIO_INFO = true; 
    public final static boolean DEFAULT_SHOW_SCENARIO_EXEC = true;

    public final static String PREF_UCM_SHOW_UCM_DIAGRAMS = "seg.jUCMNav.UCMReportPreference.UCMSHOWUCMDIAGRAMS"; //$NON-NLS-1$
    public final static String PREF_GRL_SHOW_GRL_DIAGRAMS = "seg.jUCMNav.UCMReportPreference.GRLSHOWGRLDIAGRAMS"; //$NON-NLS-1$
    
    public final static String PREF_UCM_SHOW_DESC = "seg.jUCMNav.UCMReportPreference.UCMSHOWDESC"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_RESPONSIBILITY = "seg.jUCMNav.UCMReportPreference.UCMSHOWRESPONSIBILITY"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_STUB = "seg.jUCMNav.UCMReportPreference.UCMSHOWSTUB"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_OR_FORK = "seg.jUCMNav.UCMReportPreference.OrFork"; //$NON-NLS-1$
    // public final static String PREF_UCMSHOWANDFORK = "seg.jUCMNav.UCMReportPreference.AndFork"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_START_POINT = "seg.jUCMNav.UCMReportPreference.StartPoint"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_END_POINT = "seg.jUCMNav.UCMReportPreference.EndPoint"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_SCENARIO_INFO = "seg.jUCMNav.UCMReportPreference.UCMSHOWSCENARIOINFO"; //$NON-NLS-1$
    public final static String PREF_UCM_SHOW_SCENARIO_EXEC = "seg.jUCMNav.UCMReportPreference.UCMSHOWSCENARIOEXEC"; //$NON-NLS-1$
    public final static String PREF_GRL_SHOW_INTENTIONAL_ELEMENTS = "seg.jUCMNav.UCMReportPreference.IntentionalElements"; //$NON-NLS-1$
    public final static String PREF_GRL_SHOW_BELIEFS = "seg.jUCMNav.UCMReportPreference.Beliefs"; //$NON-NLS-1$
    public final static String PREF_SHOW_URN_LINKS = "seg.jUCMNav.UCMReportPreference.URNLinks"; //$NON-NLS-1$
    
    public final static String PREF_KPI_SHOW_EVALS = "seg.jUCMNav.UCMReportPreference.KPIsShowEvals";
    public final static String PREF_GRL_SHOW_EVALS = "seg.jUCMNav.UCMReportPreference.GRLShowEvals"; //$NON-NLS-1$
    public final static String PREF_GRL_SHOW_EVAL_STRATEGY_TREND = "seg.jUCMNav.UCMReportPreference.GRLShowEvalStratefyTrend"; //$NON-NLS-1$
    public final static String PREF_GRL_EVAL_STRATEGY_TREND = "seg.jUCMNav.UCMReportPreference.GRLEvalStrategyTrend"; //$NON-NLS-1$


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
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORT_ORIENTATION, ReportGeneratorPreferences.DEFAULT_REPORT_ORIENTATION);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORT_WIDTH, ReportGeneratorPreferences.DEFAULT_REPORT_WIDTH);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORT_HEIGHT, ReportGeneratorPreferences.DEFAULT_REPORT_HEIGHT);
        //getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_REPORT_COLOR, ReportGeneratorPreferences.DEFAULT_REPORT_COLOR);

        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_UCM_DIAGRAMS, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_UCM_DIAGRAMS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_SHOW_GRL_DIAGRAMS, ReportGeneratorPreferences.DEFAULT_GRL_SHOW_GRL_DIAGRAMS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_DESC, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_DESC);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_RESPONSIBILITY, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_RESPONSIBILITY);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_STUB, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_STUB);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_OR_FORK, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_OR_FORK);
        // getPreferenceStore().setDefault( ReportGeneratorPreferences.PREF_UCM_SHOW_AND_FORK, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_AND_FORK );
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_START_POINT, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_START_POINT);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_END_POINT, ReportGeneratorPreferences.DEFAULT_UCM_SHOW_END_POINT);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_SCENARIO_INFO, ReportGeneratorPreferences.DEFAULT_SHOW_SCENARIO_INFO);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_UCM_SHOW_SCENARIO_EXEC, ReportGeneratorPreferences.DEFAULT_SHOW_SCENARIO_EXEC);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_SHOW_INTENTIONAL_ELEMENTS,
                ReportGeneratorPreferences.DEFAULT_GRL_SHOW_INTENTIONAL_ELEMENTS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_SHOW_BELIEFS, ReportGeneratorPreferences.DEFAULT_GRL_SHOW_BELIEFS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_SHOW_URN_LINKS, ReportGeneratorPreferences.DEFAULT_GRL_SHOW_URN_LINKS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_SHOW_EVALS, ReportGeneratorPreferences.DEFAULT_GRL_SHOW_EVALS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_KPI_SHOW_EVALS, ReportGeneratorPreferences.DEFAULT_KPI_SHOW_EVALS);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_SHOW_EVAL_STRATEGY_TREND, ReportGeneratorPreferences.DEFAULT_GRL_SHOW_EVAL_STRATEGY_TREND);
        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_GRL_EVAL_STRATEGY_TREND, ReportGeneratorPreferences.DEFAULT_GRL_EVAL_STRATEGY_TREND);

        getPreferenceStore().setDefault(ReportGeneratorPreferences.PREF_NUMBER_CSV_COLUMNS, ReportGeneratorPreferences.DEFAULT_NUMBER_CSV_COLUMNS);
    }

    /**
     * 
     * @return the height parameter to give dot
     */
    public static String getHeight() {
    	if (getPreferenceStore().getString(PREF_REPORT_HEIGHT).equals("") || Double.parseDouble(getPreferenceStore().getString(PREF_REPORT_HEIGHT)) < 3.0){ //$NON-NLS-1$
    		return "3"; //return 3 if blank or too small (0 would cause an error)
    		
    	}else{
    	
    		return getPreferenceStore().getString(PREF_REPORT_HEIGHT);
    	}	
        
    }

    /**
     * 
     * @return the orientation (TB, LR)
     */
    public static String getOrientation() {
        return getPreferenceStore().getString(PREF_REPORT_ORIENTATION);
    }

    /**
     * 
     * @return the color of the report background in the PDF
     */
    //public static String getReportColor() {
    //    return getPreferenceStore().getString(PREF_REPORT_COLOR);
    //}

    /**
     * 
     * @return the width parameter to give dot
     */
    public static String getWidth() {
    	if (getPreferenceStore().getString(PREF_REPORT_WIDTH).equals("") || Double.parseDouble(getPreferenceStore().getString(PREF_REPORT_WIDTH)) < 3.0){ //$NON-NLS-1$
    		return "3"; //return 3 if blank or too small (0 would cause an error)
    	}else{
    		return getPreferenceStore().getString(PREF_REPORT_WIDTH);
    	}	
    	

    }

    public static String getNumberCSV_Columns() {
    	if (getPreferenceStore().getString(PREF_NUMBER_CSV_COLUMNS).equals("") || Integer.parseInt(getPreferenceStore().getString(PREF_NUMBER_CSV_COLUMNS)) < 5 || Integer.parseInt(getPreferenceStore().getString(PREF_NUMBER_CSV_COLUMNS)) > 12){ //$NON-NLS-1$
    		return "7"; //return 0 if blank
    	}else{
    		return getPreferenceStore().getString(PREF_NUMBER_CSV_COLUMNS);
    	}	
           	
    }
    /**
     * 
     * @param height
     *            the height parameter to give to report
     */
    public static void setHeight(String height) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(height);

            s = Double.toString(d);        
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }

        getPreferenceStore().setValue(PREF_REPORT_HEIGHT, s);
    }

    public static void setNumberCSVcolumns(String numberCSVcolumns) {
        String s;
        // want to make sure it is convertible.
        try {
            int i = Integer.parseInt(numberCSVcolumns);
            if( i < 5 || i > 12 ) {
            	s = "7"; //$NON-NLS-1$
            } else {
            	s = Integer.toString(i);
            }
        } catch (Exception e) {
            s = "7"; //$NON-NLS-1$
        }

        getPreferenceStore().setValue(PREF_NUMBER_CSV_COLUMNS, s);
    }

    /**
     * 
     * @param str
     *            the orientation (TB, LR)
     */
    public static void setOrientation(String str) {
        getPreferenceStore().setValue(PREF_REPORT_ORIENTATION, str);
    }

    /**
     * 
     * @param color
     *            the color of the report background in the PDF
     */
    //public static void setReportColor(String color) {
    //    getPreferenceStore().setValue(PREF_REPORT_COLOR, color);
    //}

    /**
     * 
     * @param width
     *            the width parameter to give dot
     */
    public static void setWidth(String width) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(width);
            	s = Double.toString(d);
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }

        getPreferenceStore().setValue(PREF_REPORT_WIDTH, s);
    }

    /**
     * 
     * @return the value of the show ucm diagrams preference
     */
    public static boolean getUCMSHOWUCMDIAGRAMS() {
    	return getPreferenceStore().getBoolean(PREF_UCM_SHOW_UCM_DIAGRAMS);
    }
    
    /**
     * 
     * @return the value of the show grl diagrams preference
     */
    public static boolean getGRLSHOWGRLDIAGRAMS() {
    	return getPreferenceStore().getBoolean(PREF_GRL_SHOW_GRL_DIAGRAMS);
    }
    
    /**
     * 
     * @return the show ucm description
     */
    public static boolean getUCMSHOWDESC() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_DESC);
    }

    /**
     * 
     * @return the show responsibility node
     */
    public static boolean getUCMSHOWRESPONSIBILITY() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_RESPONSIBILITY);
    }

    /**
     * 
     * @return the show stub node
     */
    public static boolean getUCMSHOWSTUB() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_STUB);
    }

    /**
     * 
     * @return the show OrFork node
     */
    public static boolean getUCMSHOWORFORK() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_OR_FORK);
    }

    /**
     * 
     * @return the show AndFork node
     */
    // public static boolean getAndForkOrder() {
    // return getPreferenceStore().getBoolean(PREF_UCM_SHOW_AND_FORK);
    // }

    /**
     * 
     * @return the show StartPoint node
     */
    public static boolean getUCMSHOWSTARTPOINT() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_START_POINT);
    }

    /**
     * 
     * @return the show EndPoint node
     */
    public static boolean getUCMSHOWENDPOINT() {
        return getPreferenceStore().getBoolean(PREF_UCM_SHOW_END_POINT);
    }
    
    /**
     * 
     * @return the value of the ucm show scenario info preference
     */
    public static boolean getUCMSHOWSCENARIOINFO() {
    	return getPreferenceStore().getBoolean(PREF_UCM_SHOW_SCENARIO_INFO);
    }
    
    /**
     * 
     * @return the value of the ucm show scenario execution info preference
     */
    public static boolean getUCMSHOWSCENARIOEXEC() {
    	return getPreferenceStore().getBoolean(PREF_UCM_SHOW_SCENARIO_EXEC);
    }

    
    public static boolean getGRLShowIntentionalElements() {
        return getPreferenceStore().getBoolean(PREF_GRL_SHOW_INTENTIONAL_ELEMENTS);
    }

    public static boolean getGRLShowBeliefs() {
        return getPreferenceStore().getBoolean(PREF_GRL_SHOW_BELIEFS);
    }

    public static boolean getShowURNLinks() {
        return getPreferenceStore().getBoolean(PREF_SHOW_URN_LINKS);
    }

    /**
     * 
     * @return the value of the show grl show evals preference
     */
    public static boolean getShowGRLShowEvals() {
    	return getPreferenceStore().getBoolean(PREF_GRL_SHOW_EVALS);
    }
    
    /**
     * 
     * @return the value of the show kpi show evals preference
     */
    public static boolean getShowKpiShowEvals() {
    	return getPreferenceStore().getBoolean(PREF_KPI_SHOW_EVALS);
    }
    /**
     * 
     * @return the value of the show grl eval strategy trend preference
     */
    public static boolean getShowGRLEvalStrategyTrend() {
    	return getPreferenceStore().getBoolean(PREF_GRL_SHOW_EVAL_STRATEGY_TREND);
    }
    
    /**
     * 
     * @return the value of the grl eval strategy trend preference
     */
    public static String getGRLEvalStrategyTrend() {
    	if (getPreferenceStore().getString(PREF_GRL_EVAL_STRATEGY_TREND).equals("")){ //$NON-NLS-1$
    		return Integer.toString(DEFAULT_GRL_EVAL_STRATEGY_TREND);//return default if blank
    		
    	}else{
    	
    	return getPreferenceStore().getString(PREF_GRL_EVAL_STRATEGY_TREND);
    	}
    }
    
    /**
     * 
     * @param bool
     *            show or not the description node type
     */
    public static void setUCMSHOWDESC(boolean bool) {
        getPreferenceStore().setValue(PREF_UCM_SHOW_DESC, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the show ucm diagrams node type
     */
    public static void setUCMSHOWUCMDIAGRAMS(boolean bool) {
    	getPreferenceStore().setValue(PREF_UCM_SHOW_UCM_DIAGRAMS, bool);
    }
    
    /**
     * 
     * @param bool
     *            show or not the show grl diagrams node type
     */
    public static void setGRLSHOWGRLDIAGRAMS(boolean bool) {
    	getPreferenceStore().setValue(PREF_GRL_SHOW_GRL_DIAGRAMS, bool);
    }
    
    /**
     * 
     * @param bool
     *            show or not the responsibility node type
     */
    public static void setUCMSHOWRESPONSIBILITY(boolean bool) {
        getPreferenceStore().setValue(PREF_UCM_SHOW_RESPONSIBILITY, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the stub node type
     */
    public static void setUCMSHOWSTUB(boolean bool) {
        getPreferenceStore().setValue(PREF_UCM_SHOW_STUB, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the OrFork node type
     */
    public static void setOrFork(boolean bool) {
        getPreferenceStore().setValue(PREF_UCM_SHOW_OR_FORK, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the AndFork node type
     */
    // public static void setAndFork(boolean bool) {
    // getPreferenceStore().setValue(PREF_UCM_SHOW_AND_FORK, bool);
    // }

    /**
     * 
     * @param bool
     *            show or not the StartPoint node type
     */
    public static void setStartPoint(boolean bool) {
        getPreferenceStore().setValue(PREF_UCM_SHOW_START_POINT, bool);
    }

    /**
     * 
     * @param bool
     *            show or not the EndPoint node type
     */
     public static void setEndPoint(boolean bool) {
    	 getPreferenceStore().setValue(PREF_UCM_SHOW_END_POINT, bool);
     }

     /**
      * 
      * @param bool
      *            show or not the UCMSHOWSCENARIOINFO preference
      */
      public static void setUCMSHOWSCENARIOINFO(boolean bool) {
     	 getPreferenceStore().setValue(PREF_UCM_SHOW_SCENARIO_INFO, bool);
      }
      
      /**
       * 
       * @param bool
       *            show or not the UCMSHOWSCENARIOEXEC preference
       */
       public static void setUCMSHOWSCENARIOEXEC(boolean bool) {
      	 getPreferenceStore().setValue(PREF_UCM_SHOW_SCENARIO_EXEC, bool);
       }
    
      /**
       * 
       * @param bool
       *            show or not the GRL Show Evals preference
       */
      public static void setShowGRLEvals(boolean bool) {
      	getPreferenceStore().setValue(PREF_GRL_SHOW_EVALS, bool);
      }
      
    /**
     * 
     * @param bool
     *            show or not the GRL Show Eval Strategy Trend preference
     */
    public static void setShowGRLEvalStrategyTrend(boolean bool) {
    	getPreferenceStore().setValue(PREF_GRL_SHOW_EVAL_STRATEGY_TREND, bool);
    }
    
    /**
     * 
     * @param evalStrategyTrend
     *            set the number of strategy evaluations to use for the evaluation strategy trend of the intentional elements
     */
    public static void setGRLEvalStrategyTrend(String evalStrategyTrend) {
    	String s;
        // want to make sure it is convertible.

        try {
            int i = Integer.parseInt(evalStrategyTrend);
            if( i < 0) {
            	s = "0"; //$NON-NLS-1$
            } else {
            	
            	s = Integer.toString(i);
            	
            }
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }


        getPreferenceStore().setValue(PREF_GRL_EVAL_STRATEGY_TREND, s);
    
    	}
}
