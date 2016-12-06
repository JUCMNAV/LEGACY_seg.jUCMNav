/*****************************************************************************************
 * MXPARSER LICENSE
 * Copyright 2010-2015 MARIUSZ GROMADA. All rights reserved. 
 * You may use this software under the condition of Simplified BSD License. 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 1) Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 * 2) Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *    
 * THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL MARIUSZ GROMADA 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * The views and conclusions contained in the software and documentation are those of the 
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of MARIUSZ GROMADA.
 *******************************************************************************************/
package seg.jUCMNav.strategies;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.ContributionRange;
import grl.ContributionType;
import grl.DecompositionType;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationRange;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.ImpactModel;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.QualitativeLabel;
import grl.StrategiesGroup;
import grl.impl.GRLspecImpl;
import grl.impl.GrlFactoryImpl;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPINewEvalValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.management.loading.PrivateClassLoader;
import javax.swing.plaf.multi.MultiViewportUI;

import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import fm.Feature;
import fm.FeatureModel;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.editparts.kpiTreeEditparts.KPIRootEditPart;
import seg.jUCMNav.editparts.kpiViewEditparts.AbstractKPIViewEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;
import seg.jUCMNav.model.commands.create.AddKPIInformationConfigCommand;
import seg.jUCMNav.model.commands.delete.DeleteEvaluationCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.StrategyEvaluationRangeHelper;
import seg.jUCMNav.strategies.util.FeatureUtil;
import seg.jUCMNav.views.UCMPerspectiveFactory;
import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.property.LinkRefPropertySource;
import seg.jUCMNav.views.strategies.StrategiesView;
import seg.jUCMNav.views.wizards.dynamicContexts.ManageChangeEditorPage;
import ucmscenarios.impl.InstanceImpl;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.NumericChange;
import urn.dyncontext.PropertyChange;
import urn.dyncontext.QuadraticChange;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;
import urncore.GRLmodelElement;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * This class is a singleton responsible to manage the current strategy. It does the evaluation calculation for IntentionalElement, create the Evaluation and
 * return the value of the evaluation given an IntentionalElement for the current strategy.
 * 
 * @author Jean-Francois Roy, pchen, sghanava
 * 
 */
public class EvaluationStrategyManager {

    /**
     * Metadata name used to store run-time GRL numerical evaluations
     */
    public static final String METADATA_NUMEVAL = "_numEval"; //$NON-NLS-1$
    /**
     * Metadata name used to store run-time GRL qualitative evaluations
     */
    public static final String METADATA_QUALEVAL = "_qualEval"; //$NON-NLS-1$

    /**
     * Metadata name used to store the run-time evaluations when a range is executed.
     */
    public static final String METADATA_RANGEVALUES = "_rangeNumEvals"; //$NON-NLS-1$
    
    /**
     * Metadata name used to store original quantitative importance of an intentional element
     */
    public static final String METADATA_ORIGIMP = "_origImp"; //$NON-NLS-1$
    
    /**
     * Metadata name used to store original quantitative evaluation of an intentional element
     */
    public static final String METADATA_ORIGEVAL = "_origEval"; //$NON-NLS-1$
    
    /**
     * Metadata name used to store original quantitative contribution of an intentional element
     */
    public static final String METADATA_ORIGCONTRIB = "_origContrib"; //$NON-NLS-1$
    
    /**
     * Metadata name used to store original Decomposition Type of an intentional element
     */
    public static final String METADATA_ORIGDECOMPTYPE = "_origDecompType"; //$NON-NLS-1$
    
    /**
     * Metadata name used to store deactivation status of an element
     */
    public static final String METADATA_DEACTSTATUS = "_deactStatus"; //$NON-NLS-1$

    /**
     * Metadata name used to store the run-time reexposed Feature(IntentionalElement).
     */
    public static final String REEXPOSE_RUNTIMEMATADATA = "CoURN";//$NON-NLS-1$
    
    /**
     * Metadata value mapped to name of CoURN for reexposed Feature.
     */
    public static final String REEXPOSE_RUNTIMEMATADATAVAlUES = "_reexpose"; //$NON-NLS-1$
    
    private static HashMap<UCMNavMultiPageEditor, EvaluationStrategyManager> strategyManagerInstances = null;
    // just in case we're actually accessing it via some non UI thread during export and/or before the app loads.
    private static EvaluationStrategyManager noEditorStrategyManagerInstance = null;
    private static int minRange = -100; // for 0..100 range feature

    private boolean canRefresh;
    private boolean differenceMode = false;

    private UCMNavMultiPageEditor multieditor;
    private ScrollingGraphicalViewer kpiViewer;
    private TreeViewer kpiListViewer;
    private HashMap evaluations = new HashMap(); // HashMap to keep link between IntentionalElement and the Evaluation for a particular strategy
    private HashMap<IntentionalElement, Evaluation> comparisonEvaluations = null; // HashMap to keep link between IntentionalElement and the Evaluation for the
                                                                                  // first strategy in difference mode
    private HashMap<Actor, Integer> currentActorEvaluations = new HashMap<Actor, Integer>(); // HashMap to store current Actor evaluations
    private HashMap<Actor, Integer> comparisonActorEvaluations = null; // HashMap to store Actor evaluations for the first strategy in difference mode
    private EvaluationStrategy strategy, strategy1 = null, strategy2 = null; // strategy1, strategy2 used in difference mode
    private ContributionContext contributionContext = null;
    private DynamicContext dynContext = null;
    private Timepoint tp = null;
    private IGRLStrategyAlgorithm algo;
    private HashMap kpiInformationConfigs = new HashMap();
    private EvaluationStrategy oldStrategy = null;
   
    public static synchronized EvaluationStrategyManager getInstance(UCMNavMultiPageEditor multieditor, boolean canRefresh) {

        if (strategyManagerInstances == null) {
            strategyManagerInstances = new HashMap<UCMNavMultiPageEditor, EvaluationStrategyManager>();
        }

        EvaluationStrategyManager soleInstance = null;

        if (multieditor == null) {
       		if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
       				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
       				&& PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
       			multieditor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
       		}        		
        }

        if (multieditor != null) {
            if (strategyManagerInstances.get(multieditor) == null)
                strategyManagerInstances.put(multieditor, new EvaluationStrategyManager());
            soleInstance = strategyManagerInstances.get(multieditor);
        } else {
            // fallback for non-UI threads (not even sure if this exists, but
            // better be safe than sorry
            if (noEditorStrategyManagerInstance == null)
                noEditorStrategyManagerInstance = new EvaluationStrategyManager();
            soleInstance = noEditorStrategyManagerInstance;
        }

        soleInstance.canRefresh = canRefresh;

        // cancel difference mode in all other managers

        return soleInstance;
    }

    public static synchronized EvaluationStrategyManager getInstance(UCMNavMultiPageEditor multieditor) {
        return getInstance(multieditor, true);
    }

    public static synchronized EvaluationStrategyManager getInstance(boolean canRefresh) {
        return getInstance(null, canRefresh);
    }

    public static synchronized EvaluationStrategyManager getInstance() {
        return getInstance(null, true);
    }

    public static void releaseEnvironment(UCMNavMultiPageEditor editor) {
        if (editor != null && strategyManagerInstances.containsKey(editor)) {
            strategyManagerInstances.remove(editor);
        }
    }

    /**
     * 
     */
    private EvaluationStrategyManager() {

    }

    public synchronized void calculateEvaluations(URNspec urn, EvaluationRange range) {
        if (strategy == null || range == null || range.getStep() == 0 || (range.getEnd() - range.getStart()) * range.getStep() < 0)
            return;

        // determines whether -100 or 0 should be used as a minimum scale.
        minRange = -100 * (StrategyEvaluationRangeHelper.getCurrentRange(urn) ? 0 : 1);

        HashMap results = new HashMap();
        // long before = System.currentTimeMillis();

        clearCalculationValues(urn);

        for (int i = range.getStart(); (range.getEnd() >= range.getStart() && i <= range.getEnd())
                || (range.getEnd() < range.getStart() && i >= range.getEnd()); i += range.getStep()) {
            range.getEval().setEvaluation(i);
            calculateEvaluationExecute();

            recordCalculationValues(urn, results, i);
        }

        // long after = System.currentTimeMillis();
        //        System.out.println("Time spent: " + (after - before) + " milliseconds"); //$NON-NLS-1$ //$NON-NLS-2$

        refreshDiagrams();
    }

    private void clearCalculationValues(URNspec urn) {
        for (Iterator iterator = urn.getGrlspec().getIntElements().iterator(); iterator.hasNext();) {
            IntentionalElement ie = (IntentionalElement) iterator.next();
            MetadataHelper.removeMetaData(ie, METADATA_RANGEVALUES);
            MetadataHelper.addMetaData(urn, ie, METADATA_RANGEVALUES, ""); //$NON-NLS-1$
        }
    }

    public synchronized void calculateEvaluations(URNspec urn, ContributionRange range) {
        if (strategy == null || range == null || range.getStep() == 0 || (range.getEnd() - range.getStart()) * range.getStep() < 0)
            return;

        HashMap results = new HashMap();
        // long before = System.currentTimeMillis();

        clearCalculationValues(urn);

        for (int i = range.getStart(); (range.getEnd() >= range.getStart() && i <= range.getEnd())
                || (range.getEnd() < range.getStart() && i >= range.getEnd()); i += range.getStep()) {
            range.getChange().setNewQuantitativeContribution(i);
            LinkRefPropertySource.syncElementLinkQualitativeContribution(range.getChange().getContribution(), i);
            calculateEvaluationExecute();

            recordCalculationValues(urn, results, i);
        }

        // long after = System.currentTimeMillis();
        //        System.out.println("Time spent: " + (after - before) + " milliseconds"); //$NON-NLS-1$ //$NON-NLS-2$

        refreshDiagrams();
    }

    private void recordCalculationValues(URNspec urn, HashMap results, int i) {
        for (Iterator iterator = evaluations.keySet().iterator(); iterator.hasNext();) {
            IntentionalElement ie = (IntentionalElement) iterator.next();
            EvaluationRange r = (EvaluationRange) results.get(ie);
            if (r == null) {
                r = (EvaluationRange) ModelCreationFactory.getNewObject(urn, EvaluationRange.class);
                r.setEnd(minRange);
                r.setStart(100); // so that is overridden with better values below.
            }
            results.put(ie, r);

            Evaluation ev = (Evaluation) evaluations.get(ie);

            // if we have found a larger range, change it.
            if (r.getStart() > ev.getEvaluation()) {
                r.setStart(ev.getEvaluation());
            }
            if (r.getEnd() < ev.getEvaluation()) {
                r.setEnd(ev.getEvaluation());
            }

            if (ev.getStrategies() == null) // only do it for temporarily created ranges - do not override existing ones.
            {
                ev.setEvalRange(r);
            }
            results.put(ie, r);

            String val = MetadataHelper.getMetaData(ie, METADATA_RANGEVALUES);
            if (val == null)
                val = ""; //$NON-NLS-1$
            MetadataHelper.addMetaData(urn, ie, METADATA_RANGEVALUES, val + i + "=" + ev.getEvaluation() + ";"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    public synchronized void calculateEvaluation() {
        if (strategy == null) {
            return;
        }
        // long before = System.currentTimeMillis();

        calculateEvaluationExecute();

        // long after = System.currentTimeMillis();
        //        System.out.println("Time spent: " + (after - before) + " milliseconds"); //$NON-NLS-1$ //$NON-NLS-2$

        refreshDiagrams();
        FeatureModelStrategyAlgorithm.refreshProblemsView();
        // refresh problem views of the EvaluationStrategy
    }

    private void calculateEvaluationExecute() {
        setupEvaluationAlgorithm();

        algo.init(strategy, evaluations);
        if (algo.isConstraintSolverAlgorithm()) {
            processConstraintSolverAlgorithm();
        } else {
            processNonConstraintSolverAlgorithm();
            //if it is Feature Model algorithm , set MetaData of reexpose to the reexposed element
            if( algo instanceof FeatureModelStrategyAlgorithm && strategy != null){
            	URNspec urn = null;
            	String reexposeIDs =  MetadataHelper.getMetaData(strategy, REEXPOSE_RUNTIMEMATADATA);
            	if( strategy.getGrlspec()!= null && strategy.getGrlspec().getUrnspec()!= null)
            		 urn = strategy.getGrlspec().getUrnspec();
            	clearAllRuntimeReexposeMetadata( urn );
            	if( reexposeIDs != null){
            		 String[] reexposeArray = reexposeIDs.substring( reexposeIDs.indexOf(":") +1).split(",");
            	     List<String> reexposeFeatureList = Arrays.asList(reexposeArray);
            	     for(Iterator itr = urn.getGrlspec().getIntElements().iterator(); itr.hasNext();){
            			  IntentionalElement elem = (IntentionalElement) itr.next();
            			  if (elem instanceof Feature){
            				  
            				  Metadata runtimeEvalObj = MetadataHelper.getMetaDataObj(elem, METADATA_NUMEVAL);
            				  if( reexposeFeatureList.contains( elem.getId()) && runtimeEvalObj !=null &&
            						  Integer.valueOf(runtimeEvalObj.getValue()) == 0 ){
            					  MetadataHelper.addMetaData(urn, elem, REEXPOSE_RUNTIMEMATADATA, REEXPOSE_RUNTIMEMATADATAVAlUES);
            				  }
            				    
            			  }
            		  }
            	 } 
            	
            }
        }
    }
    
    // before it starts to execute applying the runtime metadata reexpose to IntentionalElement
    public static void clearAllRuntimeReexposeMetadata(URNspec urn) {
		// TODO Auto-generated method stub
		LinkedList<Feature> list = new LinkedList<Feature>();
		for(Iterator itr = urn.getGrlspec().getIntElements().iterator(); itr.hasNext();){
			 IntentionalElement intElem = (IntentionalElement)itr.next();
			 if( intElem instanceof Feature && FeatureUtil.isReexposed(intElem)){
				 
				  MetadataHelper.removeMetaData(intElem, REEXPOSE_RUNTIMEMATADATA );
				 
			 }
			 
		}
	}

	private synchronized void refreshDiagrams() {
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

    private void processNonConstraintSolverAlgorithm() {
        // if Feature Model evaluation, remove all auto select metadata (done regardless of whether the 
    	// "Auto Select" option is selected or not to ensure that all metadata is removed in both cases)
        if (algo instanceof FeatureModelStrategyAlgorithm)
        	((FeatureModelStrategyAlgorithm) algo).clearAllAutoSelectedFeatures(strategy);
        	
        // evaluate for the first time (this is all that is needed for all algorithms except for the Feature Model evaluation)
        evaluateModel();
        
        // if Feature Model evaluation and auto selection is set, execute auto selection of mandatory features, then evaluate again 
        if (algo instanceof FeatureModelStrategyAlgorithm && StrategyEvaluationPreferences.getAutoSelectMandatoryFeatures()) {
        	((FeatureModelStrategyAlgorithm) algo).autoSelectAllMandatoryFeatures(strategy);
        	algo.init(strategy, evaluations);
        	evaluateModel();
        	refreshDiagrams();
        }
    }

	private void evaluateModel() {
		while (algo.hasNextNode()) {
            IntentionalElement element = algo.nextNode();
            
            if (algo instanceof TimedGRLStrategyAlgorithm) {
	            //Check if the element is deactivated
	            boolean deactivated = false;
	            deactivated = isIgnored(element);
	    		
	    		//If the element is deactivated, skip it
	    		if (deactivated)
	    			continue;
            }
            Evaluation eval = (Evaluation) evaluations.get(element);
            int val = algo.getEvaluation(element);
            eval.setEvaluation(val);
            syncIntentionalElementQualitativeEvaluation(eval, val);
            setEvaluationMetadata(element, eval);
        }
	}

    private void processConstraintSolverAlgorithm() {
        Hao2011Algorithm hao2011Algorithm = (Hao2011Algorithm) algo;
        hao2011Algorithm.calculate();
        while (hao2011Algorithm.hasNextNode()) {
            IntentionalElement element = hao2011Algorithm.nextNode();
            Evaluation eval = (Evaluation) evaluations.get(element);
            int val = eval.getEvaluation();
            syncIntentionalElementQualitativeEvaluation(eval, val);
            // setEvaluationMetadata(element, eval);
            setEvaluationMetadata(element, val, eval.getQualitativeEvaluation().toString());
        }
    }

    private void setEvaluationMetadata(IntentionalElement element, int val, String qualEvalAsString) {
        String numEvalAsString = Integer.toString(val);
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(element, METADATA_NUMEVAL);
        if (metaNumerical != null) {
            // Run-time metadata already exist for this element
            metaNumerical.setValue(numEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = element.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, element, METADATA_NUMEVAL, numEvalAsString);
        }

        Metadata metaQuantitative = MetadataHelper.getMetaDataObj(element, METADATA_QUALEVAL);
        if (metaQuantitative != null) {
            // Run-time metadata already exist for this element
            metaQuantitative.setValue(qualEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = element.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, element, METADATA_QUALEVAL, qualEvalAsString);
        }
    }

    public synchronized int getActorEvaluation(Actor actor) {
    	boolean ignored = false;
    	int actorEval = 0;
    	if (algo instanceof TimedGRLStrategyAlgorithm) {
			Metadata metaDeactStatus = MetadataHelper.getMetaDataObj(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
			if (metaDeactStatus != null) {
				String deactStatus = MetadataHelper.getMetaData(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
				if (deactStatus.equalsIgnoreCase("true"))
					ignored = true;
			}
    	}
		if (ignored) 
			actorEval = 0;
		else
			actorEval = algo.getActorEvaluation(actor);
        setEvaluationMetadata(actor, actorEval);
        currentActorEvaluations.put(actor, new Integer(actorEval));
        return actorEval;
    }

    public synchronized int getDisplayActorEvaluation(Actor actor) {
    	
    	int currentEval = getActorEvaluation(actor);

        if (differenceMode && strategy1 != null && strategy2 != null && strategy == strategy2 && strategy2 != strategy1 && comparisonActorEvaluations != null
                && comparisonActorEvaluations.containsKey(actor)) {
            // determine if difference mode is valid and contains a previous evaluation for element
            int firstEval = comparisonActorEvaluations.get(actor);
            return (currentEval - firstEval);
        } else {
            return currentEval;
        }
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
            return Evaluation.EVALUATION_UNDEFINED;

    }

    /**
     * This method returns the evaluation strategy algorithm if it has been prepared, if not it will set it up
     * 
     * @return the evaluation strategy algorithm
     * 
     */
    public synchronized IGRLStrategyAlgorithm getEvaluationAlgorithm() {
        if (algo == null)
            setupEvaluationAlgorithm();
        return algo;
    }

    /**
     * Prepares the value from the properties preference page and creates the algorithm accordingly
     * 
     */
    public synchronized void setupEvaluationAlgorithm() {
    	String algoChoice = StrategyEvaluationPreferences.getAlgorithm();
    	URNspec urn = null;
    	if (strategy != null)
    		urn = strategy.getGrlspec().getUrnspec();
        
        if ((StrategyEvaluationPreferences.MIXED_ALGORITHM + "").equals(algoChoice)){ //$NON-NLS-1$
        	algo = new MixedGRLStrategyAlgorithm();
        }
        else if ((StrategyEvaluationPreferences.QUANTITATIVE_ALGORITHM + "").equals(algoChoice)) //$NON-NLS-1$
            algo = new QuantitativeGRLStrategyAlgorithm();
        else if ((StrategyEvaluationPreferences.QUALITATIVE_ALGORITHM + "").equals(algoChoice)){ //$NON-NLS-1$
            algo = new QualitativeGRLStrategyAlgorithm();
        }
        else if ((StrategyEvaluationPreferences.FORMULA_BASED_ALGORITHM + "").equals(algoChoice)){ //$NON-NLS-1$
            algo = new FormulaBasedGRLStrategyAlgorithm();
            
            //Aggregate metadata not needed for this algorithm
            MetadataHelper.cleanAggregateMetadata(urn);
        }
        else if ((StrategyEvaluationPreferences.CONDITIONAL_GRL_ALGORITHM + "").equals(algoChoice)){ //$NON-NLS-1$
            algo = new ConditionalBasedGRLStrategyAlgorithm();
            
            //Aggregate metadata not needed for this algorithm
            MetadataHelper.cleanAggregateMetadata(urn);
        }
        else if ((StrategyEvaluationPreferences.CONSTRAINT_SOLVER_ALGORITHM + "").equals(algoChoice)){ //$NON-NLS-1$
            algo = new Hao2011Algorithm();
            
            //Aggregate metadata not needed for this algorithm
            MetadataHelper.cleanAggregateMetadata(urn);
        }
        else if ((StrategyEvaluationPreferences.FEATURE_MODEL_ALGORITHM + "").equals(algoChoice)) //$NON-NLS-1$
            algo = new FeatureModelStrategyAlgorithm();
        //New Algorithm added for Timed GRL
        else if ((StrategyEvaluationPreferences.TIMED_GRL_ALGORITHM + "").equals(algoChoice)) //$NON-NLS-1$
            algo = new TimedGRLStrategyAlgorithm();
        else
            algo = new QuantitativeGRLStrategyAlgorithm(); // New default, just in case...

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

    public synchronized Evaluation getDisplayEvaluationObject(IntentionalElement elem) {

        if (differenceMode && strategy1 != null && strategy2 != null && strategy == strategy2 && strategy2 != strategy1 && comparisonEvaluations != null
                && comparisonEvaluations.containsKey(elem)) {
            // determine if difference mode is valid and contains a previous evaluation for element

            Evaluation diffEval = (Evaluation) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);

            int firstValue = comparisonEvaluations.get(elem).getEvaluation();
            int secondValue = ((Evaluation) evaluations.get(elem)).getEvaluation();

            diffEval.setEvaluation(secondValue - firstValue);
            return diffEval;
        } else {
            return getEvaluationObject(elem);
        }
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

    public synchronized boolean isIgnored(IntentionalElement elem) {
        if (this.hasSpecificMetadata(elem)) {
            return true;
        } else if (elem.getType() == IntentionalElementType.INDICATOR_LITERAL) {
            for (Iterator iter = elem.getLinksSrc().iterator(); iter.hasNext();) {
                ElementLink el = (ElementLink) iter.next();
                if (el.getDest() instanceof IntentionalElement) {
                    if (this.hasSpecificMetadata((IntentionalElement) el.getDest()))
                        return true;
                }
            }
        }
        return false;
    }

    private synchronized boolean hasSpecificMetadata(IntentionalElement elem) {
        if (elem.getMetadata().size() > 0) {
            for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
                Metadata md = (Metadata) iter.next();
                if (md.getName().equalsIgnoreCase("ST_Legal")) { //$NON-NLS-1$
                    if (md.getValue().equalsIgnoreCase("No")) //$NON-NLS-1$
                        return true;
                } else if (md.getName().equals(Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode"))) { //$NON-NLS-1$
                    return true;
                } else if (md.getName().equalsIgnoreCase("_DEACTSTATUS") && md.getValue().equalsIgnoreCase("true")) {
                	return true;
                }
            }
        }
        Iterator iter = elem.getRefs().iterator();
        while (iter.hasNext()) {
            
        	// Parse through the node bound to this Intentional Element
            IntentionalElementRef ref = (IntentionalElementRef) iter.next();
            
            //If the actor in which the intentional element is contained is deactivated, then deactivate the element as well
	        if (ref.getContRef() != null && ref.getContRef() instanceof ActorRef) {
	        	Actor actor = (Actor) ((ActorRef) ref.getContRef()).getContDef();
	        	
	        	//If this actor or any of its parent is ignored
	        	if (isActorIgnored(actor))
	        		return true;
	        }
	        		
        }
        return false;

    }

    public synchronized boolean isConditionResource(IntentionalElement elem) {
        if (elem.getType() == IntentionalElementType.RESSOURCE_LITERAL) {
            for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
                Metadata md = (Metadata) iter.next();
                if (md.getName().equalsIgnoreCase("ST_CONDITIONTYPE")) { //$NON-NLS-1$
                    return true;
                }
            }
        }
        return false;
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
        if (strategy != null) {
            evaluations.remove(element);
            if (eval != null)
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

    protected synchronized HashMap getRecursiveEvaluations(EvaluationStrategy strategy, HashMap v) {
        for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
            EvaluationStrategy ev = (EvaluationStrategy) iterator.next();
            getRecursiveEvaluations(ev, v);
        }
        for (Iterator iterator = strategy.getEvaluations().iterator(); iterator.hasNext();) {
            Evaluation ev = (Evaluation) iterator.next();
            Evaluation past = (Evaluation) v.get(ev.getIntElement());
            v.put(ev.getIntElement(), ev);
        }
        return v;
    }

    protected synchronized HashMap getRecursiveKPIInformationConfig(EvaluationStrategy strategy, HashMap v) {
        for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
            EvaluationStrategy ev = (EvaluationStrategy) iterator.next();
            getRecursiveKPIInformationConfig(ev, v);
        }
        for (Iterator iterator = strategy.getKpiInfoConfig().iterator(); iterator.hasNext();) {
            KPIInformationConfig ev = (KPIInformationConfig) iterator.next();
            v.put(ev.getKpiInfoElement(), ev);
        }
        return v;
    }

    public synchronized void setStrategy(EvaluationStrategy strategy3) {
        this.strategy = strategy3;
        
        // Create a new hash map for the evaluation of this strategy
        evaluations = new HashMap();

        // Create a new hash map for the KPIInformationConfig this strategy
        kpiInformationConfigs = new HashMap();
        if (multieditor != null) {
        	URNspec urn = multieditor.getModel();
        
	        //Restore any value changed due to TimedGRL Evaluation and clean the related metadata
	        restoreOriginalValue(urn.getGrlspec());
	        MetadataHelper.cleanTimedGRLMetadata(urn);
        }
        
        if (strategy != null) {
        	
        	oldStrategy = strategy; 
        	
            // Go through all the intentionalElement and create a new Evaluation object if no one exist for this strategy
            GRLspec grl = strategy.getGrlspec();
            
            MetadataHelper.cleanRunTimeMetadata(grl.getUrnspec());
            HashMap recursiveEvaluations = new HashMap();
            getRecursiveEvaluations(strategy, recursiveEvaluations);

            // Associate elements involved in an evaluation but not present in the grl
            // (reused elements) to the evaluations hash map
            for (Object intElement : recursiveEvaluations.keySet()) {
                if ( !grl.getIntElements().contains(intElement) ) {
                    evaluations.put(intElement, recursiveEvaluations.get(intElement));
                }
            }
            
            Iterator it = grl.getIntElements().iterator();
            while (it.hasNext()) {
                IntentionalElement elem = (IntentionalElement) it.next();
                // Verify if an evaluation exist for this strategy. This could create performance problem!!!!
                Evaluation eval = (Evaluation) recursiveEvaluations.get(elem);
                if (eval == null) {
                    eval = (Evaluation) ModelCreationFactory.getNewObject(grl.getUrnspec(), Evaluation.class);
                }
                evaluations.put(elem, eval);
            }          
            
            // Go through all the KPIInformationElement and create a new KPIInformationConfig object if no one exist for this strategy
            grl = strategy.getGrlspec();
            HashMap recursiveKPIInformationConfig = new HashMap();
            getRecursiveKPIInformationConfig(strategy, recursiveKPIInformationConfig);
            it = grl.getKpiInformationElements().iterator();
            while (it.hasNext()) {
                KPIInformationElement elem = (KPIInformationElement) it.next();
                KPIInformationConfig config = (KPIInformationConfig) recursiveKPIInformationConfig.get(elem);
                if (config == null) {
                    config = (KPIInformationConfig) ModelCreationFactory.getNewObject(grl.getUrnspec(), KPIInformationConfig.class);
                }
                kpiInformationConfigs.put(elem, config);
            }

            for (Iterator iterator = evaluations.values().iterator(); iterator.hasNext();) {
                Evaluation ev = (Evaluation) iterator.next();
                KPIEvalValueSet set = ev.getKpiEvalValueSet();
                // if we have something change, force a recompute.
                if (ev.getKpiNewEvalValue() != null
                        || (set != null && (set.getTargetValue() != 0 || set.getThresholdValue() != 0 || set.getWorstValue() != 0
                                || set.getEvaluationValue() != 0 || !"".equals(set.getUnit()))))
                    calculateIndicatorEvalLevel(ev);

            }
            
            //Preprocessing for Timed GRL
            if ((StrategyEvaluationPreferences.TIMED_GRL_ALGORITHM + "").equals(StrategyEvaluationPreferences.getAlgorithm()) 
            		&& dynContext != null && tp != null) {
            	grl = preProcessTimedGRL(grl, dynContext, tp);
            }
            
            calculateEvaluation();
          //  if(FeatureModelStrategyAlgorithm.getWarnings().size()>0){
          //      FeatureModelStrategyAlgorithm.refreshProblemsView();
          //  }
            
        }else{
        	FeatureModelStrategyAlgorithm.clearProblemViews();
        }

        // Refresh the kpi list view
        if (kpiListViewer != null) {
            EditPart ep = kpiListViewer.getContents();
            if (ep instanceof KPIRootEditPart) {
                ((KPIRootEditPart) ep).refreshIndicatorTreeEditPart(ep);
            }
        }
    }

    public synchronized ContributionContext getContributionContext() {
        return contributionContext;
    }

    public synchronized void setContributionContext(ContributionContext context) {
        this.contributionContext = context;
        setStrategy(strategy);
    }
    
    //new
    public synchronized DynamicContext getDynamicContext() {
        return dynContext;
    }
    
    public synchronized void setDynamicContext(DynamicContext context) {
        this.dynContext = context;
        setStrategy(strategy);
    }
    
    public synchronized Timepoint getTimepoint() {
        return tp;
    }
    
    public synchronized void setTimepoint(Timepoint tp) {
        this.tp = tp;
    }

    /**
     * Sets the quantitative importance of an intentional element
     * 
     * @param element
     *            the element to apply the new value to
     * @param value
     *            the new quantitative importance value
     * 
     */
    public synchronized void setIntentionalElementQuantitativeImportance(IntentionalElement element, int value) {
        // The importance should be between 0 and 100
        if (value <= 100 && value >= 0) {

            // Change the value in the evaluation
            if (value != element.getImportanceQuantitative()) {
                element.setImportanceQuantitative(value);
                syncIntentionalElementQuantitativeImportance(element, value);
            }

            // do we have a command for this?
            calculateEvaluation();
        }
    }

    /**
     * Synchronize the qualitative importance to the quantitative importance
     * 
     * @param element
     *            intentional element model for the intentional element being synchronized
     * @param value
     *            quantitative value for the intentional element
     * 
     */
    private void syncIntentionalElementQuantitativeImportance(IntentionalElement element, int value) {
        ImportanceType label = element.getImportance();

        ImportanceType toSet = getQualitativeImportanceForQuantitativeValue(value);

        if (!label.equals(toSet))
            element.setImportance(toSet);

    }

    public static ImportanceType getQualitativeImportanceForQuantitativeValue(int value) {
        if (value >= 66 && value <= 100)
            return ImportanceType.HIGH_LITERAL;
        else if (value >= 33 && value < 66)
            return ImportanceType.MEDIUM_LITERAL;
        else if (value > 0 && value < 33)
            return ImportanceType.LOW_LITERAL;
        else if (value == 0)
            return ImportanceType.NONE_LITERAL;
        return null;
    }

    /**
     * Sets the quantitative importance of an actor
     * 
     * @param element
     *            the element to apply the new value to
     * @param value
     *            the new quantitative importance value
     * 
     */
    public synchronized void setActorQuantitativeImportance(Actor element, int value) {
        // The importance should be between 0 and 100
        if (value <= 100 && value >= 0) {

            // Change the value in the evaluation
            if (value != element.getImportanceQuantitative()) {
                element.setImportanceQuantitative(value);
                syncActorQuantitativeImportance(element, value);
            }

            // do we have a command for this?
            calculateEvaluation();
        }
    }

    /**
     * Synchronize the qualitative importance to the quantitative importance
     * 
     * @param element
     *            actor being synchronized
     * @param value
     *            quantitative value for the actor
     * 
     */
    private void syncActorQuantitativeImportance(Actor element, int value) {
        ImportanceType label = element.getImportance();

        ImportanceType toSet = getQualitativeImportanceForQuantitativeValue(value);

        if (!label.equals(toSet))
            element.setImportance(toSet);
    }

    /**
     * Sets the qualitative importance
     * 
     * @param element
     *            the element to which to apply the new value
     * @param value
     *            the new qualitative importance value
     * 
     */
    public synchronized void setIntentionalElementQualitativeImportance(IntentionalElement element, ImportanceType value) {
        // Change the value in the evaluation
        if (value != element.getImportance()) {
            element.setImportance(value);
            syncIntentionalElementQualitativeImportance(element);

        }
        // do we have a command for this?
        calculateEvaluation();
    }

    /**
     * Synchronizes the quantitative evaluation to the qualitative evaluation
     * 
     * @param element
     *            the intentional element being synchronized
     * 
     */
    private void syncIntentionalElementQualitativeImportance(IntentionalElement element) {
        String type = element.getImportance().getName();
        int importance = element.getImportanceQuantitative();
        int value = mapImportanceToValue(type, importance);
        if (value >= 0)
            element.setImportanceQuantitative(value);

    }

    protected int mapImportanceToValue(String type, int importance) {
        if (ImportanceType.HIGH_LITERAL.getName().equals(type) && (importance < 66))
            return 100;
        else if (ImportanceType.MEDIUM_LITERAL.getName().equals(type) && (importance < 33 || importance >= 66))
            return 50;
        else if (ImportanceType.LOW_LITERAL.getName().equals(type) && (importance == 0 || importance >= 33))
            return 25;
        else if (ImportanceType.NONE_LITERAL.getName().equals(type) && (importance > 0))
            return 0;
        return -1;
    }

    /**
     * Sets the qualitative importance
     * 
     * @param element
     *            the element to which to apply the new value
     * @param value
     *            the new qualitative importance value
     * 
     */
    public synchronized void setActorQualitativeImportance(Actor element, ImportanceType value) {
        // Change the value in the evaluation
        if (value != element.getImportance()) {
            element.setImportance(value);
            syncActorQualitativeImportance(element);

        }
        // do we have a command for this?
        calculateEvaluation();
    }

    /**
     * Synchronizes the quantitative evaluation to the qualitative evaluation
     * 
     * @param element
     *            the intentional element being synchronized
     * 
     */
    private void syncActorQualitativeImportance(Actor element) {
        String type = element.getImportance().getName();
        int importance = element.getImportanceQuantitative();
        int value = mapImportanceToValue(type, importance);
        if (value >= 0)
            element.setImportanceQuantitative(value);
    }

    /**
     * Sets the quantitative evaluation
     * 
     * @param element
     *            the element to apply the new value to
     * @param value
     *            the new quantitative evaluation value
     * @param delete
     *            Are we removing this evaluation or adding it?
     * 
     */
    protected synchronized void setIntentionalElementEvaluation(IntentionalElement element, int value, boolean delete) {
        // The evaluation could only be between 100 and minRange (0 or -100, depending on the scale). Do nothing if it is not the case
        if (value <= IGRLStrategyAlgorithm.SATISFICED && value >= IGRLStrategyAlgorithm.UNDECIDED) {
            Evaluation eval = (Evaluation) evaluations.get(element);
            // Change the value in the evaluation
            if (value != eval.getEvaluation()) {
                eval.setEvaluation(value);
                syncIntentionalElementQualitativeEvaluation(eval, value);
                setEvaluationMetadata(element, eval);
            }
            if (!delete) {
                // If it is a new Evaluation entered by the user, link it with the strategy and intentionalElement
                AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
                execute(cmd);
            } else {
                DeleteEvaluationCommand cmd = new DeleteEvaluationCommand(eval);
                execute(cmd);
            }

            calculateEvaluation();
        }
    }

    /**
     * Sets the quantitative evaluation
     * 
     * @param element
     *            the element to apply the new value to
     * @param value
     *            the new quantitative evaluation value
     */
    public synchronized void setIntentionalElementEvaluation(IntentionalElement element, int value) {
        setIntentionalElementEvaluation(element, value, false);
    }

    /**
     * Remove a quantitative evaluation
     * 
     * @param element
     *            the element to remove the evaluation
     * @param oldValue
     *            the value that was there before
     */
    public synchronized void removeIntentionalElementEvaluation(IntentionalElement element, int oldValue) {
        setIntentionalElementEvaluation(element, oldValue, true);
    }

    private void execute(Command cmd) {
        if (cmd.canExecute()) {
            // bug 717 original code
            cmd.execute();

            /*
             * proposed enh - partial solution only. see comments in bug. if (multieditor==null )// won't be able to undo cmd.execute(); else
             * multieditor.getDelegatingCommandStack().execute(cmd);
             */
        }
    }

    /**
     * Synchronize the qualitative evaluation to the quantitative evaluation
     * 
     * @param eval
     *            evaluation model for the intentional element being synchronized
     * @param value
     *            quantitative value for the intentional element
     * 
     */
    public void syncIntentionalElementQualitativeEvaluation(Evaluation eval, int value) {
        QualitativeLabel label = eval.getQualitativeEvaluation();

        URNspec urn = null;
        if (eval.getIntElement() != null && eval.getIntElement().getGrlspec() != null)
            urn = eval.getIntElement().getGrlspec().getUrnspec();
        else if (multieditor != null)
            urn = multieditor.getModel();

        QualitativeLabel toSet = getQualitativeEvaluationForQuantitativeValue(urn, value);

        if (!label.equals(toSet))
            eval.setQualitativeEvaluation(toSet);

    }

    public static QualitativeLabel getQualitativeEvaluationForQuantitativeValue(URNspec urn, int value) {
        value = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable(urn, value);

        if (value == IGRLStrategyAlgorithm.SATISFICED)
            return QualitativeLabel.SATISFIED_LITERAL;
        else if (value > IGRLStrategyAlgorithm.NONE && value < IGRLStrategyAlgorithm.SATISFICED)
            return QualitativeLabel.WEAKLY_SATISFIED_LITERAL;
        else if (value > IGRLStrategyAlgorithm.DENIED && value < IGRLStrategyAlgorithm.NONE)
            return QualitativeLabel.WEAKLY_DENIED_LITERAL;
        else if (value == IGRLStrategyAlgorithm.DENIED)
            return QualitativeLabel.DENIED_LITERAL;
        else if (value == IGRLStrategyAlgorithm.NONE)
            return QualitativeLabel.NONE_LITERAL;
        else if (value == IGRLStrategyAlgorithm.CONFLICT)
            return QualitativeLabel.CONFLICT_LITERAL;
        else if (value == IGRLStrategyAlgorithm.UNDECIDED)
            return QualitativeLabel.UNKNOWN_LITERAL;
        return null;
    }

    /**
     * Sets the qualitative evaluation
     * 
     * @param element
     *            the element to which to apply the new value
     * @param value
     *            the new qualitative evaluation value
     * 
     */
    public synchronized void setIntentionalElementQualitativeEvaluation(IntentionalElement element, QualitativeLabel value) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        // Change the value in the evaluation
        if (value != eval.getQualitativeEvaluation()) {
            eval.setQualitativeEvaluation(value);
            syncIntentionalElementEvaluation(eval);
            setEvaluationMetadata(element, eval);
        }
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        execute(cmd);

        calculateEvaluation();
    }

    /**
     * Synchronizes the quantitative evaluation to the qualitative evaluation
     * 
     * @param eval
     *            evaluation model for the intentional element being synchronized
     * 
     */
    private void syncIntentionalElementEvaluation(Evaluation eval) {
        QualitativeLabel qualitative = eval.getQualitativeEvaluation();

        URNspec urn = null;
        if (eval.getIntElement() != null && eval.getIntElement().getGrlspec() != null)
            urn = eval.getIntElement().getGrlspec().getUrnspec();
        else if (multieditor != null)
            urn = multieditor.getModel();

        int evaluation = eval.getEvaluation();
        int result = getQuantitativeValueForQualitativeEvaluation(qualitative, urn, evaluation);
        eval.setEvaluation(result);
    }

    public static int getQuantitativeValueForQualitativeEvaluation(QualitativeLabel qualitative, URNspec urn, int evaluation) {
        String type = qualitative.getName();
        evaluation = StrategyEvaluationPreferences.getEquivalentValueInFullRangeIfApplicable(urn, evaluation);

        int result = 0;
        if (QualitativeLabel.SATISFIED_LITERAL.getName().equals(type) && evaluation < IGRLStrategyAlgorithm.SATISFICED)
            result = IGRLStrategyAlgorithm.SATISFICED;
        else if (QualitativeLabel.WEAKLY_SATISFIED_LITERAL.getName().equals(type)
                && (evaluation <= IGRLStrategyAlgorithm.NONE || evaluation == IGRLStrategyAlgorithm.SATISFICED))
            result = IGRLStrategyAlgorithm.WSATISFICED;
        else if (QualitativeLabel.WEAKLY_DENIED_LITERAL.getName().equals(type)
                && (evaluation >= IGRLStrategyAlgorithm.NONE || evaluation == IGRLStrategyAlgorithm.DENIED))
            result = IGRLStrategyAlgorithm.WDENIED;
        else if (QualitativeLabel.DENIED_LITERAL.getName().equals(type) && evaluation != IGRLStrategyAlgorithm.DENIED)
            result = IGRLStrategyAlgorithm.DENIED;
        else if (QualitativeLabel.NONE_LITERAL.getName().equals(type) && evaluation != IGRLStrategyAlgorithm.NONE)
            result = IGRLStrategyAlgorithm.NONE;
        else if (QualitativeLabel.CONFLICT_LITERAL.getName().equals(type) && evaluation != IGRLStrategyAlgorithm.CONFLICT)
            result = IGRLStrategyAlgorithm.CONFLICT;
        else if (QualitativeLabel.UNKNOWN_LITERAL.getName().equals(type) && evaluation != IGRLStrategyAlgorithm.UNDECIDED)
            result = IGRLStrategyAlgorithm.UNDECIDED;

        result = StrategyEvaluationPreferences.getEquivalentValueIn0To100RangeIfApplicable(urn, result);
        return result;
    }

    /*
     * KPI management methods.
     */

    public synchronized void setLevelOfDimension(KPIInformationElement element, String value) {
        if (value != null) {
            KPIInformationConfig config = (KPIInformationConfig) kpiInformationConfigs.get(element);
            // Change the value in the KPIInformationConfig
            if (!value.equals(config.getLevelOfDimension())) {
                config.setLevelOfDimension(value);
            }
            // If it is a new KPIInformationConfig enter by the user, link it with the strategy and KPIInformationElement
            AddKPIInformationConfigCommand cmd = new AddKPIInformationConfigCommand(config, element, strategy);
            execute(cmd);

            // In this version, KPI Information Element is not involved in the evaluation
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
            execute(cmd);

            // In this version, KPI Information Element is not involved in the evaluation
            // calculateEvaluation();
        }
    }

    public synchronized void setKPIEvalValueSet(IntentionalElement element, EStructuralFeature feature, double value) {
        // TODO: For some reason, when the Properties view is maximized, "evaluations" becomes empty!
        Evaluation eval = (Evaluation) evaluations.get(element);

        // KPIEvalValueSet kpiEval = getActiveKPIEvalValueSet(element);
        Vector sets = new Vector();
        getRecursiveKPIEvalValueSetList(strategy, sets);

        // modify all applicable sets.
        for (Iterator iterator = sets.iterator(); iterator.hasNext();) {
            KPIEvalValueSet kpiEval = (KPIEvalValueSet) iterator.next();

            if (feature.getName().equals("targetValue")) { //$NON-NLS-1$
                kpiEval.setTargetValue(value);
            } else if (feature.getName().equals("thresholdValue")) { //$NON-NLS-1$
                kpiEval.setThresholdValue(value);
            } else if (feature.getName().equals("worstValue")) { //$NON-NLS-1$
                kpiEval.setWorstValue(value);
            } else if (feature.getName().equals("evaluationValue")) { //$NON-NLS-1$
                // kpiEval.setEvaluationValue(value);
                setActiveKPIEvaluationValue(element, value);
            }

        }
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        execute(cmd);

        calculateIndicatorEvalLevel(eval);
        calculateEvaluation();
    }

    public synchronized void resetKPIEvalValueSet(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = getActiveKPIEvalValueSet(element);
        double defaultValue = 0.0;

        kpiEval.setTargetValue(defaultValue);
        kpiEval.setThresholdValue(defaultValue);
        kpiEval.setWorstValue(defaultValue);
        // kpiEval.setEvaluationValue(defaultValue);
        setActiveKPIEvaluationValue(element, defaultValue);

        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        execute(cmd);

        calculateIndicatorEvalLevel(eval);
        calculateEvaluation();
    }

    public synchronized void setKPIEvalValueSetString(IntentionalElement element, EStructuralFeature feature, String value) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = getActiveKPIEvalValueSet(element);

        if (feature.getName().equals("unit")) { //$NON-NLS-1$
            kpiEval.setUnit(value);
        } else if (feature.getName().equals("qualitativeEvaluationValue")) { //$NON-NLS-1$
            kpiEval.setQualitativeEvaluationValue(value);
        }
        
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
        execute(cmd);

        calculateIndicatorEvalLevel(eval);
        calculateEvaluation();        
    }
    
    public synchronized void setKPIEvalValueSetKPIConversion(IntentionalElement element, EStructuralFeature feature, KPIConversion conv) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        KPIEvalValueSet kpiEval = getActiveKPIEvalValueSet(element);
        kpiEval.setKpiConv(conv);
        
        if (kpiEval.getEval()==null) {
            kpiEval.setEval(eval);
            
            // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
            AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
            execute(cmd);
        }
    }
    
    public synchronized int calculateIndicatorEvalLevel(Evaluation eval) {
        KPIEvalValueSet kpiEval = getActiveKPIEvalValueSet(eval.getIntElement());
        double newValue = getActiveKPIValue(eval.getIntElement());
        double evalLevel;

        if (kpiEval.getTargetValue() < kpiEval.getWorstValue()) {
            if (newValue <= kpiEval.getTargetValue()) {
                evalLevel = 100;
            } else if (newValue >= kpiEval.getWorstValue()) {
                evalLevel = -100;
            } else if (newValue < kpiEval.getThresholdValue()) {
                evalLevel = Math.abs(newValue - kpiEval.getThresholdValue()) / Math.abs(kpiEval.getTargetValue() - kpiEval.getThresholdValue()) * 100;
            } else {
                evalLevel = Math.abs(newValue - kpiEval.getThresholdValue()) / Math.abs(kpiEval.getThresholdValue() - kpiEval.getWorstValue()) * -100;
            }
        } else {
            if (newValue >= kpiEval.getTargetValue()) {
                evalLevel = 100;
            } else if (newValue <= kpiEval.getWorstValue()) {
                evalLevel = -100;
            } else if (newValue >= kpiEval.getThresholdValue()) {
                evalLevel = Math.abs(newValue - kpiEval.getThresholdValue()) / Math.abs(kpiEval.getTargetValue() - kpiEval.getThresholdValue()) * 100;
            } else {
                evalLevel = Math.abs(newValue - kpiEval.getThresholdValue()) / Math.abs(kpiEval.getThresholdValue() - kpiEval.getWorstValue()) * -100;
            }
        }

        if (minRange == 0) // Convert to new 0..100 range
            evalLevel = evalLevel / 2 + 50;

        eval.setEvaluation((int) evalLevel);
        return (int)evalLevel;
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

    // Uses metadata attached to intentional elements and actors to store run-time evaluations.
    // Enables OCL constraints to reason about satisfaction levels for a strategy.

    /**
     * Sets _numEval and _qualEval metadata for the intentional element
     */
    private void setEvaluationMetadata(IntentionalElement element, Evaluation eval) {
        String numEvalAsString = Integer.toString(eval.getEvaluation());
        String qualEvalAsString = eval.getQualitativeEvaluation().toString();

        Metadata metaNumerical = MetadataHelper.getMetaDataObj(element, METADATA_NUMEVAL);
        if (metaNumerical != null) {
            // Run-time metadata already exist for this element
            metaNumerical.setValue(numEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = element.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, element, METADATA_NUMEVAL, numEvalAsString);
        }

        Metadata metaQuantitative = MetadataHelper.getMetaDataObj(element, METADATA_QUALEVAL);
        if (metaQuantitative != null) {
            // Run-time metadata already exist for this element
            metaQuantitative.setValue(qualEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = element.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, element, METADATA_QUALEVAL, qualEvalAsString);
        }
    }

    /**
     * Sets _numEval and _qualEval metadata for the actor
     */
    private void setEvaluationMetadata(Actor actor, int actorEval) {

        URNspec urn = null;
        if (actor.getGrlspec() != null)
            urn = actor.getGrlspec().getUrnspec();
        else if (multieditor != null)
            urn = multieditor.getModel();

        String numEvalAsString = Integer.toString(actorEval);
        if (getQualitativeEvaluationForQuantitativeValue(urn, actorEval) == null)
            return;
        String qualEvalAsString = getQualitativeEvaluationForQuantitativeValue(urn, actorEval).toString();

        Metadata metaNumerical = MetadataHelper.getMetaDataObj(actor, METADATA_NUMEVAL);
        if (metaNumerical != null) {
            // Run-time metadata already exists for this element
            metaNumerical.setValue(numEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = actor.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, actor, METADATA_NUMEVAL, numEvalAsString);
        }

        Metadata metaQuantitative = MetadataHelper.getMetaDataObj(actor, METADATA_QUALEVAL);
        if (metaQuantitative != null) {
            // Run-time metadata already exists for this element
            metaQuantitative.setValue(qualEvalAsString);
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = actor.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, actor, METADATA_QUALEVAL, qualEvalAsString);
        }
    }

    public synchronized void startDifferenceMode(EvaluationStrategy strategy) {
        StrategiesView strategiesView;

        strategy1 = strategy;
        differenceMode = true;

        comparisonEvaluations = new HashMap<IntentionalElement, Evaluation>();
        comparisonActorEvaluations = new HashMap<Actor, Integer>();

        for (Iterator iter = evaluations.keySet().iterator(); iter.hasNext();) {
            IntentionalElement ie = (IntentionalElement) iter.next();
            Evaluation eval = (Evaluation) evaluations.get(ie);
            comparisonEvaluations.put(ie, eval);
            URNspec urnSpec = ie.getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, ie, Messages.getString("EvaluationStrategyManager.Evaluation"), Integer.toString(eval.getEvaluation())); // add temporary metadata for current evaluation //$NON-NLS-1$
        }

        comparisonActorEvaluations = (HashMap<Actor, Integer>) currentActorEvaluations.clone(); // store Actor evaluations for current strategy

        if ((strategiesView = (StrategiesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .findView("seg.jUCMNav.views.StrategiesView")) == null) { //$NON-NLS-1$
            System.err.println("Strategies view not found."); //$NON-NLS-1$
            return;
        }

        IActionBars bars = strategiesView.getViewSite().getActionBars();
        bars.getStatusLineManager().setMessage(Messages.getString("EvaluationStrategyManager.StrategyDifferenceModeBaseStrategy") + strategy1.getName() + "\""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public synchronized void setComparisonStrategy(EvaluationStrategy strategy) {
        StrategiesView strategiesView;

        if (differenceMode && strategy != null && strategy1 != null && strategy != strategy1) {
            strategy2 = strategy;
            calculateEvaluation();

            if ((strategiesView = getStrategiesView()) != null) {
                strategiesView.highlightStrategies(strategy1, strategy2);
            }
        }
    }

    public synchronized void stopDifferenceMode() {
        StrategiesView strategiesView;

        strategy = strategy1;
        strategy1 = null;
        strategy2 = null;
        differenceMode = false;

        if (comparisonEvaluations != null) {
            // remove temporary metadata
            for (IntentionalElement ie : comparisonEvaluations.keySet()) {
                MetadataHelper.removeMetaData(ie, Messages.getString("EvaluationStrategyManager.Evaluation")); //$NON-NLS-1$
            }
            comparisonEvaluations = null;
        }
        comparisonActorEvaluations = null;

        calculateEvaluation();

        if ((strategiesView = getStrategiesView()) != null) {
            IActionBars bars = strategiesView.getViewSite().getActionBars();
            bars.getStatusLineManager().setMessage(""); //$NON-NLS-1$
        }
    }

    public synchronized boolean isDifferenceMode() {
        return (differenceMode);
    }

    public synchronized boolean isDifferenceMode(EvaluationStrategy strategy) {
        return (differenceMode && strategy != null && strategy != strategy1 && strategy != strategy2);
    }

    public synchronized boolean displayDifferenceMode() {

        // if( differenceMode && JUCMNavPlugin.isInDebug() ) {
        // if( strategy1 != null ) System.out.println( "displayDifferenceMode()\nstrategy1: " + strategy1.getName() );
        // if( strategy2 != null ) System.out.println( "strategy2: " + strategy2.getName() );
        // if( strategy != null ) System.out.println( "strategy: " + strategy.getName() );
        // }
        return (differenceMode && strategy1 != null && strategy2 != null && strategy == strategy2 && strategy2 != strategy1);
    }

    public synchronized StrategiesView getStrategiesView() {
        StrategiesView sv = null;

        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.StrategiesView") != null) { //$NON-NLS-1$
            sv = (StrategiesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .findViewReference("seg.jUCMNav.views.StrategiesView").getView(false); //$NON-NLS-1$
        }

        return sv;
    }

    /**
     * Returns all contribution contexts that we may include into the given parent. Will not cause any circular references.
     * 
     * @param parent
     *            the parent strategy
     * @return the list of possible children.
     */
    public static List getPossibleIncludedContributionContexts(ContributionContext parent) {
        List list = getPossibleIncludedContributionContextsNonRecursive(parent);

        ArrayList toRemove = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            ContributionContext child = (ContributionContext) iter.next();
            if (!getPossibleIncludedContributionContextsNonRecursive(child).contains(parent))
                toRemove.add(child);
        }
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            ContributionContext element = (ContributionContext) iter.next();
            if (list.contains(element))
                list.remove(element);
        }
        return list;
    }

    /**
     * Gets the list of contribution context that it would be possible to include, without recursing. Used in a context where recursion would cause an infinite
     * loop.
     * 
     * @param parent
     *            the strategy
     * @return the list of possible {@link EvaluationStrategy}
     */
    private static List getPossibleIncludedContributionContextsNonRecursive(ContributionContext parent) {
        if (parent.getGroups().size() == 0 || parent.getGrlspec() == null)
            return new ArrayList();
        URNspec urn = ((ContributionContextGroup) parent.getGroups().get(0)).getGrlspec().getUrnspec();
        List list = getAllContributionContexts(urn);

        removeIncludedContributionContexts(list, parent);
        return list;
    }

    /**
     * Returns a list of all ContributionContexts in all groups.
     * 
     * @param urn
     *            the root urnspec
     * @return the list of contribution contexts
     */
    public static List getAllContributionContexts(URNspec urn) {
        ArrayList list = new ArrayList();
        for (Iterator iter = urn.getGrlspec().getContributionGroups().iterator(); iter.hasNext();) {
            ContributionContextGroup group = (ContributionContextGroup) iter.next();

            for (Iterator iterator = group.getContribs().iterator(); iterator.hasNext();) {
                ContributionContext contrib = (ContributionContext) iterator.next();
                list.add(contrib);
            }
        }
        return list;
    }

    /**
     * Recursively removes all the included contribution contexts from the given list.
     * 
     * @param list
     *            list of ContributionContext
     * @param parent
     *            the root ContributionContext from which we remove the children. we also remove the parent from the list.
     */
    private static void removeIncludedContributionContexts(List list, ContributionContext parent) {
        for (Iterator iter = parent.getIncludedContexts().iterator(); iter.hasNext();) {
            ContributionContext child = (ContributionContext) iter.next();
            removeIncludedContributionContexts(list, child);
        }

        if (list.contains(parent))
            list.remove(parent);
    }

    /**
     * Get all the included contribution contexts (recursively)that are related to this contribution context
     * 
     * @param def
     *            the strategy
     * @return the list of {@link ContributionContext}
     */
    public static Vector getDefinedIncludedContributionContexts(ContributionContext def) {
        Vector contexts = new Vector();
        getDefinedIncludedContributionContexts(def, contexts);
        return contexts;
    }

    /**
     * Get all the included contribution contexts (recursively)that are related to this strategy
     * 
     * @param def
     *            the contribution context
     * @param contribs
     *            where to insert the found {@link ContributionContext}s
     */
    private static void getDefinedIncludedContributionContexts(ContributionContext def, Vector contribs) {
        for (Iterator iter = def.getIncludedContexts().iterator(); iter.hasNext();) {
            ContributionContext contrib = (ContributionContext) iter.next();
            getDefinedIncludedContributionContexts(contrib, contribs);
            if (!contribs.contains(contrib))
                contribs.add(contrib);
        }
    }

    /**
     * For each EvaluationManager which is an explicit child of def, we return the index inside the vector returned by getDefinedIncludedContributionContexts
     * 
     * @param def
     *            the context
     * @return the list of indexes in the getDefinedIncludedContributionContext list.
     */
    public static Vector getIndexesOfPrimaryDefinedIncludedContributionContexts(ContributionContext def) {
        Vector all = getDefinedIncludedContributionContexts(def);
        Vector indexes = new Vector();
        for (int i = 0; i < def.getIncludedContexts().size(); i++) {
            // add the index of the contribution context in this list.
            // given how we merge included contribution context (to avoid duplication), this list is non-obvious
            indexes.add(new Integer(all.indexOf(def.getIncludedContexts().get(i))));
        }
        return indexes;
    }

    /**
     * Returns all strategies that we may include into the given parent. Will not cause any circular references.
     * 
     * @param parent
     *            the parent strategy
     * @return the list of possible children.
     */
    public static List getPossibleIncludedStrategies(EvaluationStrategy parent) {
        List list = getPossibleIncludedStrategiesNonRecursive(parent);

        ArrayList toRemove = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            EvaluationStrategy child = (EvaluationStrategy) iter.next();
            if (!getPossibleIncludedStrategiesNonRecursive(child).contains(parent))
                toRemove.add(child);
        }
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            EvaluationStrategy element = (EvaluationStrategy) iter.next();
            if (list.contains(element))
                list.remove(element);
        }
        return list;
    }

    /**
     * Gets the list of strategy that it would be possible to include, without recursing. Used in a context where recursion would cause an infinite loop.
     * 
     * @param parent
     *            the strategy
     * @return the list of possible {@link EvaluationStrategy}
     */
    private static List getPossibleIncludedStrategiesNonRecursive(EvaluationStrategy parent) {
        if (parent.getGroup() == null)
            return new ArrayList();
        URNspec urn = parent.getGroup().getGrlspec().getUrnspec();
        List list = getAllStrategies(urn);

        removeIncludedStrategies(list, parent);
        return list;

    }

    /**
     * Returns a list of all EvaluationStrategies in all groups.
     * 
     * @param urn
     *            the root urnspec
     * @return the list of strategies
     */
    public static List getAllStrategies(URNspec urn) {
        minRange = -100 * (StrategyEvaluationRangeHelper.getCurrentRange(urn) ? 0 : 1);

        ArrayList list = new ArrayList();
        for (Iterator iter = urn.getGrlspec().getGroups().iterator(); iter.hasNext();) {
            StrategiesGroup group = (StrategiesGroup) iter.next();

            for (Iterator iterator = group.getStrategies().iterator(); iterator.hasNext();) {
                EvaluationStrategy strategy = (EvaluationStrategy) iterator.next();
                list.add(strategy);
            }
        }
        return list;
    }

    /**
     * Recursively removes all the included strategies from the given list.
     * 
     * @param list
     *            list of EvaluationStrategies
     * @param parent
     *            the root evaluationstrategy from which we remove the children. we also remove the parent from the list.
     */
    private static void removeIncludedStrategies(List list, EvaluationStrategy parent) {
        for (Iterator iter = parent.getIncludedStrategies().iterator(); iter.hasNext();) {
            EvaluationStrategy child = (EvaluationStrategy) iter.next();
            removeIncludedStrategies(list, child);
        }

        if (list.contains(parent))
            list.remove(parent);
    }

    /**
     * Get all the included strategies (recursively) that are related to this strategy
     * 
     * @param def
     *            the strategy
     * @return the list of {@link EvaluationStrategy}
     */
    public static Vector getDefinedIncludedStrategies(EvaluationStrategy def) {
        Vector strategies = new Vector();
        getDefinedIncludedStrategies(def, strategies);
        return strategies;
    }

    /**
     * Get all the included strategies (recursively) that are related to this strategy
     * 
     * @param def
     *            the strategy
     * @param scenarios
     *            where to insert the found {@link EvaluationStrategy}s
     */
    private static void getDefinedIncludedStrategies(EvaluationStrategy def, Vector scenarios) {
        for (Iterator iter = def.getIncludedStrategies().iterator(); iter.hasNext();) {
            EvaluationStrategy strategy = (EvaluationStrategy) iter.next();
            getDefinedIncludedStrategies(strategy, scenarios);
            if (!scenarios.contains(strategy))
                scenarios.add(strategy);
        }
    }

    /**
     * For each EvaluationManager which is an explicit child of def, we return the index inside the vector returned by getDefinedIncludedStrategies
     * 
     * @param def
     *            the strategy
     * @return the list of indexes in the getDefinedIncludedStrategies list.
     */
    public static Vector getIndexesOfPrimaryDefinedIncludedStrategies(EvaluationStrategy def) {
        Vector all = getDefinedIncludedStrategies(def);
        Vector indexes = new Vector();
        for (int i = 0; i < def.getIncludedStrategies().size(); i++) {
            // add the index of the strategy in this list.
            // given how we merge included strategy (to avoid duplication), this list is non-obvious
            indexes.add(new Integer(all.indexOf(def.getIncludedStrategies().get(i))));
        }
        return indexes;
    }

    public ContributionChange findApplicableContributionChange(Contribution contrib, boolean recurse) {
        return findApplicableContributionChange(contributionContext, contrib, recurse);
    }

    public static ContributionChange findApplicableContributionChange(ContributionContext context, Contribution contrib, boolean recurse) {
        if (context == null || contrib == null)
            return null;
        ContributionChange result = null;

        // recursively look at includes.
        if (recurse) {
            for (int i = 0; i < context.getIncludedContexts().size(); i++) {
                ContributionContext include = (ContributionContext) context.getIncludedContexts().get(i);
                ContributionChange childChange = findApplicableContributionChange(include, contrib, recurse);
                if (childChange != null)
                    result = childChange; // always apply last found, except if not found.
            }
        }

        // override with local changes, if any are found.
        for (int i = 0; i < context.getChanges().size(); i++) {
            ContributionChange change = (ContributionChange) context.getChanges().get(i);
            if (change.getContribution() == contrib)
                result = change;
        }
        return result;
    }

    public int getActiveQuantitativeContribution(Contribution contribution) {
        if (contribution != null) {
            if (contributionContext != null) {
                ContributionChange change = findApplicableContributionChange(contributionContext, contribution, true);
                if (change != null)
                    return change.getNewQuantitativeContribution();
            }
            return contribution.getQuantitativeContribution();
        } else
            return 0;

    }

    public ContributionType getActiveContribution(Contribution contribution) {
        if (contribution != null) {
            if (contributionContext != null) {
                ContributionChange change = findApplicableContributionChange(contributionContext, contribution, true);
                if (change != null)
                    return change.getNewContribution();
            }
            return contribution.getContribution();
        } else
            return ContributionType.UNKNOWN_LITERAL;

    }

    public void setActiveQuantitativeContribution(ContributionContext contributionContext, Contribution contribution, int value) {
        if (contribution != null) {
            if (contributionContext != null) {
                ContributionChange change = findApplicableContributionChange(contributionContext, contribution, false);
                if (change != null) {
                    change.setNewQuantitativeContribution(value);
                    // force a refresh of the GUI even if value does not change
                    contribution.setQuantitativeContribution(contribution.getQuantitativeContribution());
                    return;
                } else if (contribution.getGrlspec() != null) {
                    ContributionChange newChange = (ContributionChange) ModelCreationFactory.getNewObject(contribution.getGrlspec().getUrnspec(),
                            ContributionChange.class);
                    newChange.setNewQuantitativeContribution(value); // other field will be set separately by existing code.
                    newChange.setContribution(contribution);
                    newChange.setContext(contributionContext);

                    // force a refresh of the GUI even if value does not change
                    contribution.setQuantitativeContribution(contribution.getQuantitativeContribution());
                    return;
                }
            }
            contribution.setQuantitativeContribution(value);
        }
    }

    public void setActiveContribution(ContributionContext contributionContext, Contribution contribution, ContributionType type) {
        if (contribution != null) {
            if (contributionContext != null) {
                ContributionChange change = findApplicableContributionChange(contributionContext, contribution, false);
                if (change != null) {
                    change.setNewContribution(type);
                    // force a refresh of the GUI even if value does not change
                    contribution.setContribution(contribution.getContribution());

                    return;
                } else if (contribution.getGrlspec() != null) {
                    ContributionChange newChange = (ContributionChange) ModelCreationFactory.getNewObject(contribution.getGrlspec().getUrnspec(),
                            ContributionChange.class);
                    newChange.setNewContribution(type); // other field will be set separately by existing code.
                    newChange.setContext(contributionContext);
                    newChange.setContribution(contribution);

                    // force a refresh of the GUI even if value does not change
                    contribution.setContribution(contribution.getContribution());
                    return;
                }
            }
            contribution.setContribution(type);
        }
    }

    public KPIEvalValueSet getActiveKPIEvalValueSet(IntentionalElement elem) {
        KPIEvalValueSet set = getRecursiveKPIEvalValueSet(elem, strategy);
        if (set == null) { // ensure always has a value, even if the ev is temporary  
            set = (KPIEvalValueSet) ModelCreationFactory.getNewObject(null, KPIEvalValueSet.class);
        }
        return set;

    }

    public KPINewEvalValue getActiveKPINewEvalValue(IntentionalElement elem) {
        return getRecursiveKPINewEvalValue(elem, strategy);
    }

    public double getActiveKPIValue(IntentionalElement elem) {
        KPINewEvalValue val = getRecursiveKPINewEvalValue(elem, strategy);
        if (val != null)
            return val.getEvaluationValue();
        else {
            KPIEvalValueSet set = getActiveKPIEvalValueSet(elem);
            if (set != null) {
                return set.getEvaluationValue();
            }
            else {
                return (double) getEvaluation(elem);
            }
        }
    }

    public void setActiveKPIEvaluationValue(IntentionalElement elem, double value) {
        setActiveKPIEvaluationValue(elem, value, true);
    }
    public void setActiveKPIEvaluationValue(IntentionalElement elem, double value, boolean recomputeAll) {
        Evaluation eval = null;
        KPIEvalValueSet set = getActiveKPIEvalValueSet(elem);
        // if we're editing the active strategy.
        if (set != null && set.getEval()!=null) {
            eval = set.getEval();

            if (eval.getStrategies() == strategy) {
                set.setEvaluationValue(value); // change
            } else // set's evaluation is actually connected to an included strategy.
            {
                eval = EcoreUtil.copy(eval);
                eval.setIntElement(null);
                eval.setKpiEvalValueSet(null); // don't actually need this, will keep on re-using the other one.
                KPINewEvalValue override = (KPINewEvalValue) ModelCreationFactory.getNewObject(elem.getGrlspec().getUrnspec(), KPINewEvalValue.class);
                override.setEvaluationValue(value);
                eval.setKpiNewEvalValue(override);
                evaluations.put(elem, eval);
            }
        } else // set doesn't exist, create it.
        {
            eval = getEvaluationObject(elem);
            set = (KPIEvalValueSet) ModelCreationFactory.getNewObject(elem.getGrlspec().getUrnspec(), KPIEvalValueSet.class);
            set.setEvaluationValue(value); // change
            eval.setKpiEvalValueSet(set);
        }

        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        AddEvaluationCommand cmd = new AddEvaluationCommand(eval, elem, strategy);
        execute(cmd);

        calculateIndicatorEvalLevel(eval);
        if (recomputeAll)
            calculateEvaluation();
    }

    /***
     * Find the first KPIEvalValueSet you can in the list of included strategies.
     * 
     * @param strategy
     * @return
     */
    protected synchronized void getRecursiveKPIEvalValueSetList(EvaluationStrategy strategy, Vector list) {
        if (list == null)
            throw new NullPointerException();
        for (Iterator iterator = strategy.getEvaluations().iterator(); iterator.hasNext();) {
            Evaluation ev = (Evaluation) iterator.next();
            if (ev.getKpiEvalValueSet() != null) {
                list.add(ev.getKpiEvalValueSet());
            }
        }
        for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
            EvaluationStrategy ev = (EvaluationStrategy) iterator.next();
            getRecursiveKPIEvalValueSetList(ev, list);
        }
    }

    /***
     * Find the first KPIEvalValueSet you can in the list of included strategies.
     * 
     * @param strategy
     * @return
     */
    protected synchronized KPIEvalValueSet getRecursiveKPIEvalValueSet(IntentionalElement elem, EvaluationStrategy strategy) {
        for (Iterator iterator = strategy.getEvaluations().iterator(); iterator.hasNext();) {
            Evaluation ev = (Evaluation) iterator.next();
            if (ev.getKpiEvalValueSet() != null && ev.getIntElement() == elem)
                return ev.getKpiEvalValueSet();
        }
        for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
            EvaluationStrategy ev = (EvaluationStrategy) iterator.next();
            return getRecursiveKPIEvalValueSet(elem, ev);
        }
        return null;
    }

    /***
     * Find the first KPINewEvalValue you can in the list of included strategies.
     * 
     * @param strategy
     * @return
     */
    protected synchronized KPINewEvalValue getRecursiveKPINewEvalValue(IntentionalElement elem, EvaluationStrategy strategy) {
        for (Iterator iterator = strategy.getEvaluations().iterator(); iterator.hasNext();) {
            Evaluation ev = (Evaluation) iterator.next();
            if (ev.getKpiNewEvalValue() != null && ev.getIntElement() == elem)
                return ev.getKpiNewEvalValue();
        }
        for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
            EvaluationStrategy ev = (EvaluationStrategy) iterator.next();
            return getRecursiveKPINewEvalValue(elem, ev);
        }
        return null;
    }
    
    public HashMap getEvaluations()
    {
        return (HashMap) evaluations.clone();
    }
    
    /*
     * Preprocesses and updates the GRL model according to the selected Dynamic Context and Timepoint
     */
    public GRLspec preProcessTimedGRL(GRLspec grl, DynamicContext dynContext, Timepoint tp) {
    	GRLspec updatedGRLmodel = grl;
    	List<Change> changes = null;
    	List<String> affected = new ArrayList<String>();
    	changes = collectChanges(dynContext, tp, affected);
    	
    	List<Change> actorChanges = new ArrayList<Change>();
    	List<Change> intEltsChanges = new ArrayList<Change>();
    	List<Change> linkChanges = new ArrayList<Change>();
    	if (changes.size() != 0) {
	    	for (Iterator j = changes.iterator(); j.hasNext();){ 
	    		Change change = (Change) j.next();
	    		if (change.getElement() instanceof ActorRef)
	    			actorChanges.add(change);
	    		else if (change.getElement() instanceof IntentionalElement)
	    			intEltsChanges.add(change);
	    		else
	    			linkChanges.add(change);
	    	}
	    	
	    	if (actorChanges.size() != 0) {
		    	//First apply changes to Actors 
		    	for (Iterator j = actorChanges.iterator(); j.hasNext();){ 
		        	Change change = (Change) j.next();
		    		updatedGRLmodel = applyChange(updatedGRLmodel, change, tp);
		    	}
	    	}
	    	//Apply changes to Intentional Elements 
	    	if (intEltsChanges.size() != 0) {
		    	for (Iterator j = intEltsChanges.iterator(); j.hasNext();){ 
		        	Change change = (Change) j.next();
		    		updatedGRLmodel = applyChange(updatedGRLmodel, change, tp);
		    	}
	    	}
	    	
	    	//Apply changes to Links 
	    	if (linkChanges.size() != 0) {
		    	for (Iterator j = linkChanges.iterator(); j.hasNext();){ 
		        	Change change = (Change) j.next();
		    		updatedGRLmodel = applyChange(updatedGRLmodel, change, tp);
		    	}
	    	}
    	}

    	return updatedGRLmodel;
    }
    
    /*
     * Collects the list of changes applicable to the model according to the Timepoint selected 
     */
    private List<Change> collectChanges (DynamicContext dynContext, Timepoint tp, List affected) {
    	List<Change> changes = new ArrayList<Change>();
    	for (Iterator j = dynContext.getChanges().iterator(); j.hasNext();){
    		Change change = (Change) j.next();
    		if ((tp.getTimepoint().after(change.getStart()) || tp.getTimepoint().equals(change.getStart())) && tp.getTimepoint().before(change.getEnd())) {
	    		String affectedProp = change.getElement().toString();
	    		
	    		if (change instanceof DeactivationChange) {
	    			affectedProp = affectedProp + ".deactivate";
	    		} else if (change instanceof PropertyChange) {
	    			PropertyChange propChange = (PropertyChange) change;
	    			affectedProp = affectedProp + propChange.getAffectedProperty();
	    		}
	    		if (affected == null || !affected.contains(affectedProp)) {
	    			changes.add(change);
	    			affected.add(affectedProp);
	    		}
	    		
	    	} else if (tp.getTimepoint().equals(change.getEnd())) {
	    		boolean consecutiveChangeExists = false;
	    		Change consecutiveChange = null;
	    		//Check if there is a consecutive change (The change directly following takes preference)
	    		for (Iterator j2 = dynContext.getChanges().iterator(); j2.hasNext();){
	        		Change change1 = (Change) j2.next();
	        		if (change1.getStart().equals(change.getEnd()) && change1.getElement() == change.getElement()) {
	        			consecutiveChangeExists = true;
	        			consecutiveChange = change1;
	        			break;
	        		}	        		
	    		}
	    		String affectedProp = change.getElement().toString();
	    		
	    		if (change instanceof DeactivationChange) {
	    			affectedProp = affectedProp + ".deactivate";
	    		} else if (change instanceof PropertyChange) {
	    			PropertyChange propChange = (PropertyChange) change;
	    			if (consecutiveChangeExists && consecutiveChange instanceof PropertyChange && 
	    					((PropertyChange) consecutiveChange).getAffectedProperty().equals(propChange.getAffectedProperty())) {
	    				if (!(consecutiveChange instanceof LinearChange))
	    					continue;
	    				else {
	    					//In case of linear changes, we need the previous change
	    					affectedProp = affectedProp + propChange.getAffectedProperty() + "1";
	    				}
	    			} else
		    			affectedProp = affectedProp + propChange.getAffectedProperty();
	    		}
	    		if (affected == null || !affected.contains(affectedProp)) {
	    			changes.add(change);
	    			affected.add(affectedProp);
	    		}

	    		
	    	}
    	}
    	
    	// handle included contexts
		for (Iterator j1 = dynContext.getIncludedContexts().iterator(); j1.hasNext();){
			DynamicContext incDynContext = (DynamicContext) j1.next();
			changes.addAll(collectChanges(incDynContext, tp, affected));
		}

    	return changes;
    }
    
    /*
     * Updates the GRL model with updated values because of a particular change
     */
    private GRLspec applyChange(GRLspec grl, Change change, Timepoint tp) {
    	if (change instanceof DeactivationChange){
    		if (change.getElement() instanceof ActorRef)
    			MetadataHelper.addMetaData(grl.getUrnspec(), (Actor) ((ActorRef) change.getElement()).getContDef(), METADATA_DEACTSTATUS, "true");
    		else if (change.getElement() instanceof GRLmodelElement)
    			MetadataHelper.addMetaData(grl.getUrnspec(), change.getElement(), METADATA_DEACTSTATUS, "true");
    		
    	} else if (change instanceof PropertyChange) {
    		
    		if (change instanceof LinearChange) {
    			LinearChange linChange = (LinearChange) change;
    			float valueAtTp = calculateLinearChange(linChange, tp.getTimepoint());
    			grl = updateValue(grl, linChange, Math.round(valueAtTp));
    			
    		} else {
    			grl = updateValue(grl, tp, change);
    		}
    		
    	}
    	
    	return grl;
    }
    
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
    /*
     * Updates the values in GRL model acording to the type of change seleced
     */
    private GRLspec updateValue(GRLspec grl, Timepoint tp, Change change) {
    	EvaluationStrategy strategySave = null;
    	if (change instanceof NumericChange) {
    		int newVal = 0;
	    	if (change instanceof ConstantChange){
	    		ConstantChange constChange = (ConstantChange) change;
	    		newVal = constChange.getNewValue();
	    	} else if (change instanceof QuadraticChange){
	    		QuadraticChange quadChange = (QuadraticChange) change;
	    		float valueAtTp = calculateQuadraticChange(quadChange, tp.getTimepoint());
	    		newVal = Math.round(valueAtTp);
	    	} else if (change instanceof FormulaChange){
	    		FormulaChange forChange = (FormulaChange) change;
	    		float valueAtTp = calculateFormulaChange(forChange, tp.getTimepoint());
	    		newVal = Math.round(valueAtTp);
	    	}
	    	if (change.getElement() instanceof IntentionalElement) {
		    	IntentionalElement element = (IntentionalElement)change.getElement();
		    	String affProp = ((NumericChange) change).getAffectedProperty();
		    	if (affProp.equals("Quantitative Importance")) {
		    		Integer origImportance = element.getImportanceQuantitative();
		    		MetadataHelper.addMetaData(grl.getUrnspec(), element, METADATA_ORIGIMP, origImportance.toString());
		    			
		    		//Cap the importance values to "0" as minimum and "100" as maximum
		    		if (newVal < 0)
		    			newVal = 0;
		    		else if (newVal > 100)
		    			newVal = 100;
		    		element.setImportanceQuantitative(newVal);
		    	} else if (affProp.equals("Element's Evaluation")) {
		    		Evaluation eval = (Evaluation) evaluations.get(element);
		            int origEval = eval.getEvaluation();
		            MetadataHelper.addMetaData(grl.getUrnspec(), element, METADATA_ORIGEVAL, Integer.toString(origEval));
		            boolean range = StrategyEvaluationRangeHelper.getCurrentRange(grl.getUrnspec());
	    	        
	    	        //Cap the evaluation values to "-100" or "0" as minimum and "100" as maximum
		    		if (!range && newVal < -100)
		    			newVal = -100;
		    		else if (range && newVal < 0)
		    			newVal = 0;
		    		else if (newVal > 100)
		    			newVal = 100;
		    		eval.setEvaluation(newVal);
		    	} 
	    	} else if (change.getElement() instanceof Contribution) {
	    		Contribution link = (Contribution) change.getElement();
	    		if (((NumericChange) change).getAffectedProperty().equals("Quantitative Contribution")) {
	    			Contribution contrib = (Contribution) link;
	    			int origContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
	    			MetadataHelper.addMetaData(grl.getUrnspec(), link, METADATA_ORIGCONTRIB, Integer.toString(origContrib));
	    				
	    			//Cap the contribution values to "-100" as minimum and "100" as maximum
		    		if (newVal < -100)
		    			newVal = -100;
		    		else if (newVal > 100)
		    			newVal = 100;
		    		link.setQuantitativeContribution(newVal);
	    		}
	    	}    	 
	    } else if (change instanceof EnumChange) {
    		EnumChange enumChange = (EnumChange) change;
    		strategySave = strategy;
    		
    		//Make strategy null to successfully change decomposition type
    		strategy = null;
    		List<IntentionalElement> intElements = grl.getIntElements();
			IntentionalElement element = intElements.get(intElements.indexOf(change.getElement()));
			DecompositionType origDecompType = element.getDecompositionType();
			MetadataHelper.addMetaData(grl.getUrnspec(), element, METADATA_ORIGDECOMPTYPE, origDecompType.getLiteral());
			if (enumChange.getNewValue().equals("AND"))
				element.setDecompositionType(DecompositionType.AND_LITERAL);
			else if (enumChange.getNewValue().equals("OR"))
				element.setDecompositionType(DecompositionType.OR_LITERAL);
			else if (enumChange.getNewValue().equals("XOR"))
				element.setDecompositionType(DecompositionType.XOR_LITERAL);
			
			//Restore Strategy
			strategy = strategySave;
    	}
    	return grl;
    }
    
    /*
     * Solves a linear equation for a linear change
     */
    private float calculateLinearChange(LinearChange linChange, Date reqDate) {
    	float valueAtTp = 0;
    	float start = calculateInitialValue(linChange);
    	long difference = getDateDiff(linChange.getStart(),linChange.getEnd(),TimeUnit.DAYS);
		float slope = (float) ((long) (linChange.getNewValue() - start))/difference;
		long timeInDays = getDateDiff(linChange.getStart(),reqDate,TimeUnit.DAYS);
		valueAtTp = (slope * timeInDays) + start;
    	return valueAtTp;
    }
    
    /*
     * Updates the values in the GRL model if the selected change is a Linear Change
     */
    private GRLspec updateValue(GRLspec grl, LinearChange linChange, int valueAtTp) {
    	
    	if (linChange.getElement() instanceof IntentionalElement) {
    		IntentionalElement element = (IntentionalElement)linChange.getElement();
    		if (linChange.getAffectedProperty().equals("Quantitative Importance")) {
    			Metadata metaOrigImp = MetadataHelper.getMetaDataObj(element, METADATA_ORIGIMP);
    	        if (metaOrigImp == null) {
    	        	Integer origImportance = element.getImportanceQuantitative();
    				MetadataHelper.addMetaData(grl.getUrnspec(), element, METADATA_ORIGIMP, origImportance.toString());
    	        }
    	        //Cap the importance values to "0" as minimum and "100" as maximum
	    		if (valueAtTp < 0)
	    			valueAtTp = 0;
	    		else if (valueAtTp > 100)
	    			valueAtTp = 100;
    			element.setImportanceQuantitative(valueAtTp);
    		}
    		else if (linChange.getAffectedProperty().equals("Element's Evaluation")) {
    			Metadata metaOrigEval = MetadataHelper.getMetaDataObj(element, METADATA_ORIGEVAL);
    			Evaluation eval = (Evaluation) evaluations.get(element);
    	        if (metaOrigEval == null) {
	    			int origEval = eval.getEvaluation();
	            	MetadataHelper.addMetaData(grl.getUrnspec(), element, METADATA_ORIGEVAL, Integer.toString(origEval));
    	        }
    	        
    	        boolean range = StrategyEvaluationRangeHelper.getCurrentRange(grl.getUrnspec());
    	        
    	        //Cap the evaluation values to "-100" or "0" as minimum and "100" as maximum
	    		if (!range && valueAtTp < -100)
	    			valueAtTp = -100;
	    		else if (range && valueAtTp < 0)
	    			valueAtTp = 0;
	    		else if (valueAtTp > 100)
	    			valueAtTp = 100;
            	eval.setEvaluation(Math.round(valueAtTp));

    		}
    	} else if (linChange.getElement() instanceof Contribution) {
    		Contribution link = (Contribution) linChange.getElement();
    		if (linChange.getAffectedProperty().equals("Quantitative Contribution")) {
    			Metadata metaOrigContrib = MetadataHelper.getMetaDataObj(link, METADATA_ORIGCONTRIB);
    	        if (metaOrigContrib == null) {
	    			Contribution contrib = (Contribution) link;
	    			int origContrib = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
	    			MetadataHelper.addMetaData(grl.getUrnspec(), link, METADATA_ORIGCONTRIB, Integer.toString(origContrib));
    	        }
    	        //Cap the contribution values to "-100" as minimum and "100" as maximum
	    		if (valueAtTp < -100)
	    			valueAtTp = -100;
	    		else if (valueAtTp > 100)
	    			valueAtTp = 100;
    			link.setQuantitativeContribution(valueAtTp);
    		}
    	}
    	return grl;
    }
    
    /*
     * Calculates initial value of a Linear change
     */
    private float calculateInitialValue(LinearChange change) {
    	float startValue = 0;
    	boolean foundStartValue = false;
    	Change previousChange = null;
    	List availChanges = DynamicContextsUtils.getAllAvailableChanges(change.getElement(), change.getContext(), change.getContext().getUrnspec());
    	for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
    		Change nextChange = (Change) iter.next();
    		if (nextChange instanceof DeactivationChange || nextChange instanceof EnumChange)
				continue;
    		else {
    			NumericChange nChange = (NumericChange) nextChange; 
    			if (nChange.getEnd().equals(change.getStart()) && nChange.getAffectedProperty().equals(change.getAffectedProperty())){
	    			if (nChange instanceof ConstantChange){
	    				startValue = (float) ((ConstantChange) nChange).getNewValue();
	    				foundStartValue = true;
	    			} else if (nChange instanceof LinearChange) {
	    				startValue = (float) ((LinearChange) nChange).getNewValue();
	    				foundStartValue = true;
	    			} else if (nChange instanceof QuadraticChange) {
	    				startValue = calculateQuadraticChange((QuadraticChange) nChange, change.getStart());
	    				foundStartValue = true;
	    			} else if (nChange instanceof FormulaChange) {
	    				startValue = calculateFormulaChange((FormulaChange) nChange, change.getStart());
	    				foundStartValue = true;
	    			}
	    			break;		    			
	    		}
    		}
    	}
    	if (foundStartValue == false) {
    		String affProperty = change.getAffectedProperty();
    		if (change.getElement() instanceof Contribution) {
    			if (affProperty.equals("Quantitative Contribution")) {
    				Contribution contrib = (Contribution) change.getElement();
    				startValue = (float) EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
    			}
    		} else if (change.getElement() instanceof Actor) {
    			if (affProperty.equals("Count")) 
    				startValue = (float) ((Actor)change.getElement()).getCount();
    			else if (affProperty.equals("Quantitative Importance"))
    				startValue = (float) ((Actor) change.getElement()).getImportanceQuantitative();
    		} else if (change.getElement() instanceof IntentionalElement) {
    			if (affProperty.equals("Quantitative Importance"))
    				startValue = (float) ((IntentionalElement) change.getElement()).getImportanceQuantitative();
    			else if (affProperty.equals("Element's Evaluation"))
    				startValue = 0;
    		}  
    	}
    	return startValue;
    }
    
    /*
     * Solves quadratic equation for Quadratic change
     */
    private float calculateQuadraticChange(QuadraticChange quadChange, Date reqDate) {
    	float valueAtTp = 0;
    	long timeInDays = getDateDiff(quadChange.getStart(), reqDate, TimeUnit.DAYS);
    	valueAtTp = (float) ((quadChange.getQuadraticCoefficient() * timeInDays * timeInDays) + (quadChange.getLinearCoefficient() * 
    					timeInDays) + quadChange.getConstant());
    	return valueAtTp;
    }
    
    /*
     * Calculates values for Formula change
     */
    private float calculateFormulaChange(FormulaChange forChange, Date reqDate) {
    	float valueAtTp = 0;
    	long timeInDays = getDateDiff(forChange.getStart(), reqDate, TimeUnit.DAYS);
    	Argument t = new Argument("t", (float) timeInDays);
    	Expression e = new Expression(forChange.getFormula(), t);
    	valueAtTp = (float) e.calculate();
    	return valueAtTp;
    }
    
    /*
     * Restores original values in the GRL model by removing all the updates due to application of changes
     */
    private void restoreOriginalValue(GRLspec grl) {
    	EvaluationStrategy strategySave = null;
    	
    	// Restore original values for Intentional Elements
        for (Iterator iter = grl.getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement ie = (IntentionalElement) iter.next();
            Metadata metaOrigImp = MetadataHelper.getMetaDataObj(ie, METADATA_ORIGIMP);
            if (metaOrigImp != null) {
            	String origImp = MetadataHelper.getMetaData(ie, METADATA_ORIGIMP);
            	int origImpInt = Integer.parseInt(origImp);
            	ie.setImportanceQuantitative(origImpInt);
            }
            
            if (strategy != null || oldStrategy != null) {
	            Metadata metaOrigEval = MetadataHelper.getMetaDataObj(ie, METADATA_ORIGEVAL);
	            if (metaOrigEval != null) {
	            	String origEval = MetadataHelper.getMetaData(ie, METADATA_ORIGEVAL);
	            	int origEvalInt = Integer.parseInt(origEval);
	            	EvaluationStrategy thisStrategy = null;
	            	if (strategy != null)
	            		thisStrategy = strategy;
	            	else
	            		thisStrategy = oldStrategy;
	            	for (Iterator iter1 = thisStrategy.getEvaluations().iterator(); iter1.hasNext();) {
	            		Evaluation eval = (Evaluation) iter1.next();
	            		if (eval.getIntElement() == ie) {
	            			eval.setEvaluation(origEvalInt);
	            			break;
	            		}
	            	}
	            }
            }
            
            Metadata metaOrigDecomp = MetadataHelper.getMetaDataObj(ie, METADATA_ORIGDECOMPTYPE);
            if (metaOrigDecomp != null) {
            	strategySave = strategy;
            	strategy = null;
            	String origDecomp = MetadataHelper.getMetaData(ie, METADATA_ORIGDECOMPTYPE);
            	DecompositionType origDecompType = DecompositionType.get(origDecomp);
            	ie.setDecompositionType(origDecompType);
            	strategy = strategySave;
            }
            
        }
        
        // Restore original values for  contribution links
        for (Iterator iter2 = grl.getLinks().iterator(); iter2.hasNext();) {
            ElementLink elLink = (ElementLink) iter2.next();
            if (elLink instanceof Contribution) {
            	Contribution link = (Contribution)elLink;
            	Metadata metaOrigContrib = MetadataHelper.getMetaDataObj(link, METADATA_ORIGCONTRIB);
            	if (metaOrigContrib != null) {
                	String origContrib = MetadataHelper.getMetaData(link, METADATA_ORIGCONTRIB);
                	int origContribInt = Integer.parseInt(origContrib);
                	link.setQuantitativeContribution(origContribInt);
                }
            }
        }
    }
    
    //Return true if the actor or its parent is deactivated
    public boolean isActorIgnored(Actor actor) {
    	boolean ignored = false;
    	
    	//If the actor itself has deactivation metadata
    	Metadata metaDeactStatus = MetadataHelper.getMetaDataObj(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
		if (metaDeactStatus != null) {
			String deactStatus = MetadataHelper.getMetaData(actor, EvaluationStrategyManager.METADATA_DEACTSTATUS);
			if (deactStatus.equalsIgnoreCase("true"))
				return true;
		}
		
    	Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()) {
            
        	// Parse through the actor refs bound to this Actor
            ActorRef ref = (ActorRef) iter.next();
            
            //If the actor in which this actor ref is contained is deactivated, then deactivate this actor as well
	        if (ref.getParent() != null && ref.getParent() instanceof ActorRef) {
	        	Actor act = (Actor) ((ActorRef) ref.getParent()).getContDef();
	        	if (actor.getMetadata().size() > 0) {
		        	for (Iterator iter1 = actor.getMetadata().iterator(); iter1.hasNext();) {
		                Metadata md = (Metadata) iter1.next();
		                if (md.getName().equalsIgnoreCase("_DEACTSTATUS") && md.getValue().equalsIgnoreCase("true")) {
		                	return true;
		                }
		            }
		        }
	        	ignored = isActorIgnored(act);
	        }
	        		
        }
        return ignored;
    	
    }
    
}
