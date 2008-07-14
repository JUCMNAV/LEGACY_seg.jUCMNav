package seg.jUCMNav.strategies;

import grl.Actor;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.QualitativeLabel;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.TreeViewer;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.kpiTreeEditparts.KPIRootEditPart;
import seg.jUCMNav.editparts.kpiViewEditparts.AbstractKPIViewEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationConfigCommand;

/**
 * This class is a singleton responsible to manage the current strategy. It does the evaluation calculation for IntentionalElement, create the Evaluation and
 * return the value of the evaluation given an IntentionalElement for the current strategy.
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class EvaluationStrategyManager {

    private UCMNavMultiPageEditor multieditor;
    private ScrollingGraphicalViewer kpiViewer;
    private TreeViewer kpiListViewer;

    private static EvaluationStrategyManager instance;
    private boolean canRefresh;

    public static synchronized EvaluationStrategyManager getInstance() {
        if (instance == null) {
            instance = new EvaluationStrategyManager();
        }
        instance.canRefresh = true;
        return instance;
    }

    public static synchronized EvaluationStrategyManager getInstance(boolean canRefresh) {
        if (instance == null) {
            instance = new EvaluationStrategyManager();
        }
        instance.canRefresh = canRefresh;
        return instance;
    }

    private HashMap evaluations; // HashMap to keep link between intentionalElement and the evaluation for a particular strategy
    private EvaluationStrategy strategy;
    private IGRLStrategyAlgorithm algo;

    private HashMap kpiInformationConfigs;

    /**
     * 
     */
    private EvaluationStrategyManager() {

    }

    public synchronized void calculateEvaluation() {
        if (strategy == null) {
            return;
        }
        algo = new DefaultGRLStrategyAlgorithm();

        algo.init(strategy, evaluations);

        while (algo.hasNextNode()) {
            IntentionalElement element = algo.nextNode();
            Evaluation eval = (Evaluation) evaluations.get(element);
            eval.setEvaluation(algo.getEvaluation(element));
        }

        // Refresh all the diagrams if canRefresh set to true
        if (canRefresh && multieditor != null) {
            for (int i = 0; i < multieditor.getPageCount(); i++) {
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).refreshChildren();
            }
        }

        // Refresh the kpi view if canRefresh set to true
        if (canRefresh && kpiViewer != null) {
            EditPart ep = kpiViewer.getContents();
            if (ep instanceof AbstractKPIViewEditPart) {
                ((AbstractKPIViewEditPart) ep).refreshChildren();
            }
        }

        // Refresh the kpi list view if canRefresh set to true
        if (canRefresh && kpiListViewer != null) {
            EditPart ep = kpiListViewer.getContents();
            if (ep instanceof KPIRootEditPart) {
                ((KPIRootEditPart) ep).refreshIndicatorTreeEditPart(ep);
            }
        }
    }

    public synchronized int getActorEvaluation(Actor actor) {
        return algo.getActorEvaluation(actor);
    }

    public synchronized int getEvaluation(IntentionalElement elem) {
        Evaluation temp = (Evaluation) evaluations.get(elem);
        if (temp == null && strategy != null && strategy.getGrlspec() != null && strategy.getGrlspec().getUrnspec() != null) {
            temp = (Evaluation) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
            return temp.getEvaluation();
        } else if (temp != null) {
            return temp.getEvaluation();
        } else
            return 0;

    }

    public synchronized String getLevelOfDimension(KPIInformationElement elem) {
        KPIInformationConfig temp = (KPIInformationConfig) kpiInformationConfigs.get(elem);
        if (temp == null && strategy != null && strategy.getGrlspec() != null && strategy.getGrlspec().getUrnspec() != null) {
            temp = (KPIInformationConfig) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), KPIInformationConfig.class);
            kpiInformationConfigs.put(elem, temp);
            return temp.getLevelOfDimension();
        } else if (temp != null) {
            return temp.getLevelOfDimension();
        } else
            return ""; //$NON-NLS-1$

    }

    public synchronized String getValueOfDimension(KPIInformationElement elem) {
        KPIInformationConfig temp = (KPIInformationConfig) kpiInformationConfigs.get(elem);
        if (temp == null && strategy != null && strategy.getGrlspec() != null && strategy.getGrlspec().getUrnspec() != null) {
            temp = (KPIInformationConfig) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), KPIInformationConfig.class);
            kpiInformationConfigs.put(elem, temp);
            return temp.getValueOfDimension();
        } else if (temp != null) {
            return temp.getValueOfDimension();
        } else
            return ""; //$NON-NLS-1$

    }

    public synchronized Evaluation getEvaluationObject(IntentionalElement elem) {
        Evaluation temp = (Evaluation) evaluations.get(elem);
        // if the evaluation is null, it is a new element and we need to create a new evaluation
        if (temp == null && strategy != null && strategy.getGrlspec() != null && strategy.getGrlspec().getUrnspec() != null) {
            temp = (Evaluation) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
        }
        return temp;
    }

    public synchronized KPIInformationConfig getKPIInformationConfigObject(KPIInformationElement elem) {
        KPIInformationConfig temp = (KPIInformationConfig) kpiInformationConfigs.get(elem);
        // if the KPIInformationConfig is null, it is a new element and we need to create a new KPIInformationConfig
        if (temp == null && strategy != null && strategy.getGrlspec() != null && strategy.getGrlspec().getUrnspec() != null) {
            temp = (KPIInformationConfig) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), KPIInformationConfig.class);
            kpiInformationConfigs.put(elem, temp);
        }
        return temp;
    }

    public synchronized EvaluationStrategy getEvaluationStrategy() {
        return strategy;
    }

    public synchronized void setEvaluationForElement(IntentionalElement element, Evaluation eval) {
        if (strategy != null && eval != null) {
            evaluations.remove(element);
            evaluations.put(element, eval);
            calculateEvaluation();
        }

    }

    public synchronized void setKPIInformationConfigForElement(KPIInformationElement element, KPIInformationConfig config) {
        if (config != null) {
            kpiInformationConfigs.remove(element);
            kpiInformationConfigs.put(element, config);
            calculateEvaluation();
        }

    }

    public synchronized void setStrategy(EvaluationStrategy strategy) {
        this.strategy = strategy;

        // Create a new hash map for the evaluation of this strategy
        evaluations = new HashMap();

        // Create a new hash map for the KPIInformationConfig this strategy
        kpiInformationConfigs = new HashMap();

        if (strategy != null) {
            // Go through all the intentionalElement and create a new Evaluation object if no one exist for this strategy
            GRLspec grl = strategy.getGrlspec();
            Iterator it = grl.getIntElements().iterator();
            while (it.hasNext()) {
                IntentionalElement elem = (IntentionalElement) it.next();
                // Verify if an evaluation exist for this strategy. This could create performance problem!!!!
                Iterator sc = strategy.getEvaluations().iterator();
                Evaluation eval = null;
                while (sc.hasNext() && eval == null) {
                    Evaluation temp = (Evaluation) sc.next();
                    if (temp.getIntElement() == elem) {
                        eval = temp;
                    }
                }
                if (eval == null) {
                    eval = (Evaluation) ModelCreationFactory.getNewObject(grl.getUrnspec(), Evaluation.class);
                }
                evaluations.put(elem, eval);
            }

            // Go through all the KPIInformationElement and create a new KPIInformationConfig object if no one exist for this strategy
            grl = strategy.getGrlspec();
            it = grl.getKpiInformationElements().iterator();
            while (it.hasNext()) {
                KPIInformationElement elem = (KPIInformationElement) it.next();
                // Verify if an KPIInformationConfig exist for this strategy. This could create performance problem!!!!
                Iterator sc = strategy.getKpiInfoConfig().iterator();
                KPIInformationConfig config = null;
                while (sc.hasNext() && config == null) {
                    KPIInformationConfig temp = (KPIInformationConfig) sc.next();
                    if (temp.getKpiInfoElement() == elem) {
                        config = temp;
                    }
                }
                if (config == null) {
                    config = (KPIInformationConfig) ModelCreationFactory.getNewObject(grl.getUrnspec(), KPIInformationConfig.class);
                }
                kpiInformationConfigs.put(elem, config);
            }

            calculateEvaluation();
        }

        // Refresh the kpi list view
        if (kpiListViewer != null) {
            EditPart ep = kpiListViewer.getContents();
            if (ep instanceof KPIRootEditPart) {
                ((KPIRootEditPart) ep).refreshIndicatorTreeEditPart(ep);
            }
        }
    }

    public synchronized void setIntentionalElementEvaluation(IntentionalElement element, int value) {
        // The evaluation could only be between 100 and -100. Do nothing if it is not the case
        if (value <= 100 && value >= -100) {
            Evaluation eval = (Evaluation) evaluations.get(element);
            // Change the value in the evaluation
            if (value != eval.getEvaluation()) {
                eval.setEvaluation(value);
            }
            // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
            AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
            if (cmd.canExecute()) {
                cmd.execute();
            }

            calculateEvaluation();
        }
    }

    public synchronized void setIntentionalElementQualitativeEvaluation(IntentionalElement element, QualitativeLabel value) {
    	Evaluation eval = (Evaluation) evaluations.get(element);
        // Change the value in the evaluation
        if (value != eval.getQualitativeEvaluation()) {
            eval.setQualitativeEvaluation(value);
        }
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        if (cmd.canExecute()) {
            cmd.execute();
        }

        calculateEvaluation();    	
    }
    
    public synchronized void setLevelOfDimension(KPIInformationElement element, String value) {
        if (value != null) {
            KPIInformationConfig config = (KPIInformationConfig) kpiInformationConfigs.get(element);
            // Change the value in the KPIInformationConfig
            if (!value.equals(config.getLevelOfDimension())) {
                config.setLevelOfDimension(value);
            }
            // If it is a new KPIInformationConfig enter by the user, link it with the strategy and KPIInformationElement
            AddKPIInformationConfigCommand cmd = new AddKPIInformationConfigCommand(config, element, strategy);
            if (cmd.canExecute()) {
                cmd.execute();
            }

            // In this version, KPI Information Element is not involved in the evalution
            // calculateEvaluation();
        }
    }

    public synchronized void setValueOfDimension(KPIInformationElement element, String value) {
        if (value != null) {
            KPIInformationConfig config = (KPIInformationConfig) kpiInformationConfigs.get(element);
            // Change the value in the KPIInformationConfig
            if (!value.equals(config.getValueOfDimension())) {
                config.setValueOfDimension(value);
            }
            // If it is a new KPIInformationConfig enter by the user, link it with the strategy and KPIInformationElement
            AddKPIInformationConfigCommand cmd = new AddKPIInformationConfigCommand(config, element, strategy);
            if (cmd.canExecute()) {
                cmd.execute();
            }

            // In this version, KPI Information Element is not involved in the evalution
            // calculateEvaluation();
        }
    }

    public synchronized void setKPIEvalValueSet(IntentionalElement element, EStructuralFeature feature, double value) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = eval.getKpiEvalValueSet();

        if (feature.getName().equals("targetValue")) { //$NON-NLS-1$
            kpiEval.setTargetValue(value);
        } else if (feature.getName().equals("thresholdValue")) { //$NON-NLS-1$
            kpiEval.setThresholdValue(value);
        } else if (feature.getName().equals("worstValue")) { //$NON-NLS-1$
            kpiEval.setWorstValue(value);
        } else if (feature.getName().equals("evaluationValue")) { //$NON-NLS-1$
            kpiEval.setEvaluationValue(value);
        }

        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        if (cmd.canExecute()) {
            cmd.execute();
        }

        calculateIndicatorEvalLevel(eval);
        calculateEvaluation();
    }

    public synchronized void resetKPIEvalValueSet(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = eval.getKpiEvalValueSet();
        double defaultValue = 0.0;

        kpiEval.setTargetValue(defaultValue);
        kpiEval.setThresholdValue(defaultValue);
        kpiEval.setWorstValue(defaultValue);
        kpiEval.setEvaluationValue(defaultValue);

        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        if (cmd.canExecute()) {
            cmd.execute();
        }

        calculateIndicatorEvalLevel(eval);
        calculateEvaluation();
    }

    public synchronized void setKPIEvalValueSetUnit(IntentionalElement element, EStructuralFeature feature, String value) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = eval.getKpiEvalValueSet();

        if (feature.getName().equals("unit")) { //$NON-NLS-1$
            kpiEval.setUnit(value);
        }
    }

    public synchronized void calculateIndicatorEvalLevel(Evaluation eval) {
        KPIEvalValueSet kpiEval = eval.getKpiEvalValueSet();
        double evalLevel;

        if (kpiEval.getTargetValue() < kpiEval.getWorstValue()) {
            if (kpiEval.getEvaluationValue() <= kpiEval.getTargetValue()) {
                evalLevel = 100;
            } else if (kpiEval.getEvaluationValue() >= kpiEval.getWorstValue()) {
                evalLevel = -100;
            } else if (kpiEval.getEvaluationValue() < kpiEval.getThresholdValue()) {
                evalLevel = Math.abs(kpiEval.getEvaluationValue() - kpiEval.getThresholdValue())
                        / Math.abs(kpiEval.getTargetValue() - kpiEval.getThresholdValue()) * 100;
            } else {
                evalLevel = Math.abs(kpiEval.getEvaluationValue() - kpiEval.getThresholdValue())
                        / Math.abs(kpiEval.getThresholdValue() - kpiEval.getWorstValue()) * -100;
            }
        } else {
            if (kpiEval.getEvaluationValue() >= kpiEval.getTargetValue()) {
                evalLevel = 100;
            } else if (kpiEval.getEvaluationValue() <= kpiEval.getWorstValue()) {
                evalLevel = -100;
            } else if (kpiEval.getEvaluationValue() >= kpiEval.getThresholdValue()) {
                evalLevel = Math.abs(kpiEval.getEvaluationValue() - kpiEval.getThresholdValue())
                        / Math.abs(kpiEval.getTargetValue() - kpiEval.getThresholdValue()) * 100;
            } else {
                evalLevel = Math.abs(kpiEval.getEvaluationValue() - kpiEval.getThresholdValue())
                        / Math.abs(kpiEval.getThresholdValue() - kpiEval.getWorstValue()) * -100;
            }
        }

        eval.setEvaluation((int) evalLevel);
    }

    public synchronized void setMultieditor(UCMNavMultiPageEditor multieditor) {
        this.multieditor = multieditor;
    }

    public void setKPIViewer(ScrollingGraphicalViewer viewer) {
        this.kpiViewer = viewer;
    }

    public void setKPIListViewer(TreeViewer viewer) {
        this.kpiListViewer = viewer;
    }
}
