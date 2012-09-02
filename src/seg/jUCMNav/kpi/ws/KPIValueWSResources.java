package seg.jUCMNav.kpi.ws;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.kpimodel.Indicator;

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.kpi.KPIValueResources;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.KPIMonitoringPreferences;

/**
 * @author pchen
 * 
 */
public class KPIValueWSResources implements KPIValueResources {

    public KPIValueWSResources() {
    }

    public void requestKPIValues(List evalObjects) {
        try {
            if (evalObjects != null && evalObjects.size() > 0) {
                // For testing = "http://localhost:9080/KPIValueWebServiceBeanService/KPIValueWebServiceBean"
                String wsAddress = KPIMonitoringPreferences.getWebServiceAddress();
                KPIValueWebServiceBean port = new KPIValueWebServiceBeanProxy(wsAddress).getKPIValueWebServiceBean();

                List kpiEntities = new ArrayList();
                for (int i = 0; i < evalObjects.size(); i++) {
                    KpiEntity kpiEntity = new KpiEntity();
                    EvaluationStrategy strategy = ((Evaluation) evalObjects.get(i)).getStrategies();
                    IntentionalElement intElem = ((Evaluation) evalObjects.get(i)).getIntElement();
                    if (!(intElem instanceof Indicator)) {
                        continue;
                    }

                    kpiEntity.setStrategyName(strategy.getName());
                    kpiEntity.setIndicatorName(intElem.getName());
                    kpiEntities.add(kpiEntity);
                }

                KpiEntity[] result = new KpiEntity[0];
                try {
                    result = port.retrieveKPIValues((KpiEntity[]) kpiEntities.toArray(new KpiEntity[0]));
                } catch (Throwable th) {
                    System.out.println("The connection is not available or the monitoring services are not running."); //$NON-NLS-1$
                }

                EvaluationStrategyManager strategyManager = EvaluationStrategyManager.getInstance();
                for (int i = 0; i < result.length; i++) {
                    // System.out.println("Result" + i + ": " + ((KpiEntity) result[i]).getIndicatorName() + " - " + ((KpiEntity) result[i]).getKpiValue());
                    
                    /*KPIEvalValueSet kpiEvalValueSet = strategyManager.getActiveKPIEvalValueSet(elem);
                    kpiEvalValueSet.setEvaluationValue(Double.parseDouble(((KpiEntity) result[i]).getKpiValue()));*/
                    IntentionalElement elem = ((Evaluation) evalObjects.get(i)).getIntElement();
                    strategyManager.setActiveKPIEvaluationValue(elem, Double.parseDouble(((KpiEntity) result[i]).getKpiValue()));
                    strategyManager.calculateIndicatorEvalLevel((Evaluation) evalObjects.get(i));
                }

                strategyManager.calculateEvaluation();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
