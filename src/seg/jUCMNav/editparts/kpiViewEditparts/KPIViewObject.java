package seg.jUCMNav.editparts.kpiViewEditparts;

import grl.ElementLink;
import grl.Evaluation;
import grl.IntentionalElement;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;

import seg.jUCMNav.figures.kpi.KPIViewObjectFigure;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * The KPI view object used to store the overall information of KPI to be showed the KPI View.
 * 
 * @author pchen
 * 
 */
public class KPIViewObject {

    // Basic Indicator information
    private String id = ""; //$NON-NLS-1$
    private String name = ""; //$NON-NLS-1$
    private String description = ""; //$NON-NLS-1$

    // Indicator groups
    private String[] indicatorGroupNames = new String[0];

    // Linked intentional elements
    private String[] intentionalElementNames = new String[0];

    // Evaluation level
    private int evaluationLevel = 0;

    // KPI evaluation value set
    private double targetValue = 0.0;
    private double thresholdValue = 0.0;
    private double worstValue = 0.0;
    private double evaluationValue = 0.0;
    private String unit = ""; //$NON-NLS-1$

    // KPI information elements
    private Map kpiInformationMap = new HashMap();

    // Location information
    public final static int DEFAULT_WIDTH = 500;
    public final static int DEFAULT_HEIGHT = 250;
    public final static int SHIFT_X = 20;
    public final static int SHIFT_Y = 10;

    private int x = SHIFT_X;
    private int y = SHIFT_Y;
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public KPIViewObject(Indicator indicator) {
        id = indicator.getId();
        name = indicator.getName();
        description = indicator.getDescription() == null ? "" : indicator.getDescription(); //$NON-NLS-1$

        List indicatorGroups = indicator.getGroups();
        if (indicatorGroups != null) {
            indicatorGroupNames = new String[indicatorGroups.size()];
            for (int i = 0; i < indicatorGroups.size(); i++) {
                indicatorGroupNames[i] = ((IndicatorGroup) indicatorGroups.get(i)).getName();
            }
        }

        ElementLink[] elementLinks = (ElementLink[]) indicator.getLinksSrc().toArray(new ElementLink[0]);
        if (elementLinks != null) {
            intentionalElementNames = new String[elementLinks.length];
            for (int i = 0; i < elementLinks.length; i++) {
                // TODO: Make sure this GRLLinkableElement is an IntentionalElement
                IntentionalElement intElem = (IntentionalElement) elementLinks[i].getDest();
                if (intElem != null) {
                    String intElemName = intElem.getName();
                    intentionalElementNames[i] = intElemName;
                }
            }
        }

        EvaluationStrategyManager sm = EvaluationStrategyManager.getInstance();
        Evaluation eval = sm.getEvaluationObject(indicator);
        evaluationLevel = eval.getEvaluation();

        KPIEvalValueSet kpiEvalValueSet = sm.getActiveKPIEvalValueSet(indicator);
        targetValue = kpiEvalValueSet.getTargetValue();
        thresholdValue = kpiEvalValueSet.getThresholdValue();
        worstValue = kpiEvalValueSet.getWorstValue();
        evaluationValue =   sm.getActiveKPIValue(indicator); //kpiEvalValueSet.getEvaluationValue();
        unit = kpiEvalValueSet.getUnit() == null ? "" : kpiEvalValueSet.getUnit(); //$NON-NLS-1$

        KPIModelLink[] kpiModelLinks = (KPIModelLink[]) indicator.getKpiModelLinksDest().toArray(new KPIModelLink[0]);
        if (kpiModelLinks != null) {
            for (int i = 0; i < kpiModelLinks.length; i++) {
                KPIInformationElement kpiInfoElem = kpiModelLinks[i].getKpiInformationElementSrc();
                String kpiInfoElemName = kpiInfoElem.getName();

                String valueOfDimension = EvaluationStrategyManager.getInstance().getValueOfDimension(kpiInfoElem);
                String levelOfDimension = EvaluationStrategyManager.getInstance().getLevelOfDimension(kpiInfoElem);
                String kpiInfoSetting = (valueOfDimension == null ? "-" : valueOfDimension) + " (" + (levelOfDimension == null ? "-" : levelOfDimension) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

                kpiInformationMap.put(kpiInfoElemName, kpiInfoSetting);
            }
        }

        // calculate the preferred height
        KPIViewObjectFigure figure = new KPIViewObjectFigure(this);
        figure.setLocation(new Point(0, 0));
        figure.setupFigure();
        height = figure.getPreferredHeight();
    }

    public String getDescription() {
        return description;
    }

    public int getEvaluationLevel() {
        return evaluationLevel;
    }

    public double getEvaluationValue() {
        return evaluationValue;
    }

    public String getId() {
        return id;
    }

    public Map getKpiInformationMap() {
        return kpiInformationMap;
    }

    public String getName() {
        return name;
    }

    public String[] getIndicatorGroupNames() {
        return indicatorGroupNames;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public double getThresholdValue() {
        return thresholdValue;
    }

    public double getWorstValue() {
        return worstValue;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getUnit() {
        return unit;
    }

    public String[] getIntentionalElementNames() {
        return intentionalElementNames;
    }

}
