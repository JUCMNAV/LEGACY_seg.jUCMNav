package seg.jUCMNav.strategies;

import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.util.FeatureUtil;
import seg.jUCMNav.strategies.util.ReusedElementUtil;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import urncore.URNmodelElement;

/**
 * This class implements the evaluation algorithm for Feature Models.
 * 
 * @author Yanji Liu, Yukun Su, gunterm
 * 
 */
public class FeatureModelStrategyAlgorithm extends FormulaBasedGRLStrategyAlgorithm {

    public static final String METADATA_WARNING = "_userSetEvaluationWarning"; //$NON-NLS-1$
    public static final String METADATA_AUTO_SELECTED = "_autoSelected"; //$NON-NLS-1$
    
    // Vector of warnings happened during the featureModel strategy executions
    public static Vector<StrategyEvaluationWarning> warnings;
    private EvaluationStrategy strategy;
    
    public void init(EvaluationStrategy strategy, HashMap evaluations) {
    	super.init(strategy, evaluations);
    	this.strategy = strategy;
    	setupReusedElementsEvaluations();
    	evalReady.addAll(0, ReusedElementUtil.getReusedElements(strategy.getGrlspec()));
    }
    
    /*
     * (non-Javadoc)
     * @return the warnings of featureModel strategy executions
     */
    public static Vector<StrategyEvaluationWarning> getWarnings(){
    	if(warnings==null)
    		warnings= new Vector<StrategyEvaluationWarning>();
    	return warnings;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
    	
    	//get Aggregate Contribution for element
    	getAggregation(element);
        Evaluation eval = (Evaluation) evaluations.get(element);
    	// the quickReturn result is not null when the element does not have any incoming links, has a user-defined value, or has a KPI conversion
    	// contrary to the formula-based algorithm, do not return this result immediately, but compare it against the calculated result from the incoming links
        Integer quickReturn = preGetEvaluation(element, eval);
        // set quickReturn result to 100 if feature is auto selected
        if (StrategyEvaluationPreferences.getAutoSelectMandatoryFeatures() &&
        		element instanceof Feature && MetadataHelper.getMetaDataObj(element, FeatureModelStrategyAlgorithm.METADATA_AUTO_SELECTED) != null)
        	quickReturn = FEATURE_SELECTED;
        // if there are incoming links, calculate the result from the incoming links
        int result = 0;
        boolean incomingLinks = false;
        if (!ReusedElementUtil.isReusedElement(strategy.getGrlspec(), element) && element.getLinksDest().size() > 0) {
        	incomingLinks = true;
            int decompositionValue = -10000;
            int dependencyValue = 10000;
            int[] contributionValues = new int[100];
            int contribArrayIt = 0;
            
            // for feature model: automatic setting of contribution values of mandatory and optional links
            int mandatoryLinksNumber = 0;
            if (element instanceof Feature) {
                mandatoryLinksNumber = FeatureUtil.getNumberOfMandatoryDestLinks((Feature) element);
            }
            // the sum of the contributions of mandatory links is always 100
            int remainingMandatoryContribution = 100;
                       
            // return the list of elementlink
            Iterator it = element.getLinksDest().iterator();
            while (it.hasNext()) {
                ElementLink link = (ElementLink) it.next();
                if (link instanceof Decomposition) {
                    decompositionValue = evaluateDecomposition(element, decompositionValue, it, link);
                } else if (link instanceof Dependency) {
                    dependencyValue = evaluateDependency(dependencyValue, link);
                } else if (link instanceof Contribution) {
                    Contribution contrib = (Contribution) link;
                    
                    // update contribution values of mandatory and optional links
                    if (element instanceof Feature && (link instanceof MandatoryFMLink || link instanceof OptionalFMLink || ReusedElementUtil.isReuseLink(link))) {
                    	int quantitativeContrib;
                    	if (ReusedElementUtil.isReuseLink(link)) {
                    		quantitativeContrib = 100;
                    	} else if (mandatoryLinksNumber == 0)
                    		// the element contains only optional links --> each optional link's contribution is 100
                    		quantitativeContrib = 100;
                    	else {
                    		// the element contains mixed optional & mandatory links or only mandatory links
                    		// the mandatory links split up evenly the remainingMandatoryContribution (rounding errors are tolerated)
                    		if (link instanceof MandatoryFMLink) {
                    			if (mandatoryLinksNumber == 1)                			
                        			// this is the last link 
                    				quantitativeContrib = remainingMandatoryContribution;
                    			else {
                    				// all other links before the last one 
                    				quantitativeContrib = remainingMandatoryContribution/mandatoryLinksNumber;
                    				remainingMandatoryContribution -= quantitativeContrib;
                    				// only count down the number of links if greater than one, therefore the lowest this number gets is 1 and
                    				// the earlier check whether the number of links is 0 is still correct for each loop iteration
                        			mandatoryLinksNumber--;                    			
                        		}
                    		} else
                    			// in the mixed case, an optional link's contribution is 0
                    			quantitativeContrib = 0;                		
                    	}
                    	contrib.setQuantitativeContribution(quantitativeContrib);
                    }

                    contribArrayIt = evaluateContribution(contributionValues, contribArrayIt, link, contrib);
                }
            }
            result = ensureEvaluationWithinRange(result, decompositionValue, dependencyValue, contributionValues, contribArrayIt);
            result = postGetEvaluation(element, eval, result);
        }
        
        // compare the results
        if (quickReturn != null && incomingLinks) {
        	if (result != quickReturn.intValue()) {
        		// the user set evaluation does not match the incoming links --> add warning
       			MetadataHelper.addMetaData(element.getGrlspec().getUrnspec(), element, METADATA_WARNING, 
       					Integer.toString(quickReturn) + " != " + Integer.toString(result));
        	} else {
        		//user set evaluation is equal to evaluated value --> make sure warning does not exist
        		MetadataHelper.removeMetaData(element, METADATA_WARNING);
        	}
        } else {
        	// nothing to compare --> make sure warning does not exist
    		MetadataHelper.removeMetaData(element, METADATA_WARNING);
        }
        
        if (quickReturn != null)
        	// in any case keep the overridden evaluation value instead of the result from the incoming links
        	return quickReturn.intValue();
        else
        	return result;
    }
    
    public void clearAllAutoSelectedFeatures(EvaluationStrategy strategy) {
    	Iterator it = strategy.getGrlspec().getIntElements().iterator();
    	while (it.hasNext()) {
    		IntentionalElement elem = (IntentionalElement) it.next();
    		if (elem instanceof Feature)
    			MetadataHelper.removeMetaData(elem, METADATA_AUTO_SELECTED);
    	}
    	
    	for (IntentionalElement intElem : ReusedElementUtil.getReusedElements(strategy.getGrlspec())) {
    		if (intElem instanceof Feature) {
    			MetadataHelper.removeMetaData(intElem, METADATA_AUTO_SELECTED);
    		}
    	}
    }


	public void autoSelectAllMandatoryFeatures(EvaluationStrategy strategy) {
		// timestamp for auto selection
		String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmssSSS").format(Calendar.getInstance().getTime());
		// this implementation does not assume that there is only one root feature and
		// it also does not assume that a feature only has one parent
		List<Feature> rootFeatures = FeatureUtil.getRootFeatures(strategy.getGrlspec());
		
		List<Feature> candidates = new ArrayList<Feature>();
		Iterator it = rootFeatures.iterator();
		while (it.hasNext()) {
			Feature root = (Feature) it.next();
			autoSelectChildren(root, candidates, timeStamp, strategy);
		}
		while (!candidates.isEmpty()) {
			Feature parent = candidates.remove(0);
			autoSelectChildren(parent, candidates, timeStamp, strategy);
		}
	}

	private void autoSelectChildren(Feature parent, List<Feature> candidates, String timeStamp, EvaluationStrategy strategy) {
		// A reuse feature is a leaf
		if ( ReusedElementUtil.isReusedElement(strategy.getGrlspec(), parent) ) {
			return;
		}

		Iterator it = parent.getLinksDest().iterator();

		// all children of a parent (either root feature or selected feature) are auto selected 
		// as long as the link between the child and the parent is mandatory or an AND decomposition
		while (it.hasNext()) {
			ElementLink link = (ElementLink) it.next();
			IntentionalElement child = (IntentionalElement) link.getSrc();

			// auto selection only applies to features
			if (child instanceof Feature) {
				if (link instanceof MandatoryFMLink || (link instanceof Decomposition && parent.getDecompositionType() == DecompositionType.AND_LITERAL) 
						|| ReusedElementUtil.isReuseLink(link)) {
					// the child feature is auto selectable --> add metadata tag to this element
					MetadataHelper.addMetaData(strategy.getGrlspec().getUrnspec(), child, METADATA_AUTO_SELECTED, timeStamp);
					// add child to list of features that will need to have their children examined for auto selection
					candidates.add((Feature) child);
				} else if (FeatureUtil.checkSelectionStatus((Feature) child, true)) {
					
					// the child feature is not auto selectable --> add child to list of features that will need to have their children examined for auto selection but only if the child is selected
					candidates.add((Feature) child);
				}
			}
			
		}
	}
    
    public IntentionalElement nextNode() {
        if (ReusedElementUtil.isReusedElement(strategy.getGrlspec(), evalReady.firstElement())) {
        	IntentionalElement intElem = (IntentionalElement) evalReady.remove(0);
        	for (IntentionalElement reusingElement : ReusedElementUtil.getReusingElements(strategy.getGrlspec(), intElem)) {
	        	addToEvalReadyIfCovered(reusingElement);
        	}
        	return intElem;
        } else {
        	return super.nextNode();
        }
    }

    private void setupReusedElementsEvaluations() {
        for ( IntentionalElement intElem : ReusedElementUtil.getReusedElements(strategy.getGrlspec())) {
    		if ( !evaluations.containsKey(intElem) || (evaluations.get(intElem) == null) ) {
    			Evaluation evaluation = (Evaluation)  ModelCreationFactory.getNewObject( strategy.getGrlspec().getUrnspec(), Evaluation.class );
            	evaluations.put(intElem, evaluation);
    		}
        }
    }
    
    /**
     * Clears the warnings associated to this file and replaces them with those supplied in the vector.
     * 
     * @param warnings
     *            a vector of {@link StrategyEvaluationWarning}s to be pushed to the problems view.
     */
    public static void refreshProblemsView() {
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            IFile resource = ((FileEditorInput) editor.getEditorInput()).getFile();
            try {

                IMarker[] existingMarkers = resource.findMarkers("seg.jUCMNav.evaluationproblem", true, 3);  //$NON-NLS-1$
                for (int i = 0; i < existingMarkers.length; i++) {
                    IMarker marker = existingMarkers[i];
                    marker.delete();
                }
            } catch (CoreException ex) {
                System.out.println(ex);
            }

            if (getWarnings().size() > 0) {

                for (Iterator iter = warnings.iterator(); iter.hasNext();) {
                	StrategyEvaluationWarning o = (StrategyEvaluationWarning) iter.next();

                    try {
                        IMarker marker = resource.createMarker("seg.jUCMNav.evaluationproblem");  //$NON-NLS-1$
                        marker.setAttribute(IMarker.SEVERITY, o.getSeverity());   //$NON-NLS-1$
                        marker.setAttribute(IMarker.MESSAGE, o.toString());       //$NON-NLS-1$
                        if (o.getLocation() instanceof URNmodelElement) {
                            URNmodelElement elem = (URNmodelElement) o.getLocation();
                            marker.setAttribute(IMarker.LOCATION, URNNamingHelper.getName(elem)); //$NON-NLS-1$
                            if (o.getLocation() instanceof IntentionalElementRef){ 
                            	marker.setAttribute("EObject", ((IntentionalElementRef)o.getLocation()).getId()); //$NON-NLS-1$
                            }else{
                                marker.setAttribute("EObject", ((URNmodelElement) o.getLocation()).getId()); //$NON-NLS-1$
                            }
                        } else if (o.getLocation() != null) {
                            marker.setAttribute(IMarker.LOCATION, o.getLocation().toString());  //$NON-NLS-1$
                        }

                        
                        resource.findMarkers("seg.jUCMNav.WarningMarker", true, 1); //$NON-NLS-1$
                    } catch (CoreException ex) {
                        // System.out.println(ex);
                    }

                }
                // throw new TraversalException(b.toString());

            }
        }
    }
    


    

}
