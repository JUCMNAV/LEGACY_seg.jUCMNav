/**
 */
package fm.impl;

import fm.Feature;
import fm.FeatureModel;
import fm.FmPackage;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;
import grl.impl.GRLGraphImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;

import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.util.FeatureUtil;
import urn.URNspec;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREImpactModelElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class FeatureModelImpl extends GRLGraphImpl implements FeatureModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public COREFeature getGlobalRoot() {
		COREFactory4URN.setCOREInterfaceActive(true);
		GRLspec grl = this.getUrndefinition().getUrnspec().getGrlspec();
		List<Feature> roots = FeatureUtil.getRootFeatures(grl);
		// only returns the first of possible many roots (URN does not constrain feature models to one root)
		if (roots.isEmpty())
			return (COREFeature) COREFactory4URN.returnResult(null);
		else
			return (COREFeature) COREFactory4URN.returnResult(roots.get(0)); 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeature getLocalRoot() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EvaluationResult select(List<COREFeature> features) {
		COREFactory4URN.setCOREInterfaceActive(true);
		if (features.size() == 0)
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		Iterator<COREFeature> it = features.iterator();
		URNspec urn = null;
		Vector<IntentionalElementRef> featureRefs = new Vector<IntentionalElementRef>();
		HashMap<COREFeature, String> featuresHash = new HashMap<COREFeature, String>();
		while (it.hasNext()) {
			COREFeature feature = it.next();
			if (feature instanceof Feature) {
				if (urn == null)
					urn = ((Feature) feature).getGrlspec().getUrnspec();
				if (!((Feature) feature).getRefs().isEmpty()) {
					featureRefs.add((IntentionalElementRef) ((Feature) feature).getRefs().get(0));
					featuresHash.put(feature, "");
				}
			}
		}
		if (urn == null)
			return (EvaluationResult) COREFactory4URN.returnResult(null);

		// create a new strategy based on the list of selected features
		// TODO this creates a new strategy group each time, there should be a dedicated group for CORE
		StrategiesGroup group = (StrategiesGroup) ModelCreationFactory.getNewObject(urn, StrategiesGroup.class);
		CreateStrategiesGroupCommand csgCmd = new CreateStrategiesGroupCommand(urn, group);
		if (csgCmd.canExecute())
			csgCmd.execute();
		else
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		CreateStrategyCommand csCmd = new CreateStrategyCommand(urn, group);
		EvaluationStrategy strategy = csCmd.getStrategy();
		if (csCmd.canExecute())
			csCmd.execute();
		else 
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		// select the new strategy and set the values of the selected features
        EvaluationStrategyManager.getInstance().setStrategy(strategy);
		ChangeNumericalEvaluationCommand cneCmd = new ChangeNumericalEvaluationCommand(featureRefs, ChangeNumericalEvaluationCommand.USER_ENTRY, 100, null);
		if (cneCmd.canExecute())
			cneCmd.execute();
		else
			return (EvaluationResult) COREFactory4URN.returnResult(null);

		// execute the strategy by calling setStrategy again
		EvaluationStrategyManager.getInstance().setStrategy(strategy);

		// collect the results and prepare the EvaluationResult
		// TODO only features are done so far, impact model results still need to be done
		EvaluationResult er = new EvaluationResult();
		Iterator it2 = urn.getGrlspec().getIntElements().iterator();
		while (it2.hasNext()) {
			IntentionalElement ie = (IntentionalElement) it2.next();
			Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(ie);
			String color = IntentionalElementEditPart.determineColor(urn, ie, evaluation, false, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL);
			boolean warning = IntentionalElementEditPart.determineOverriddenWarning(ie, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL) || 
					IntentionalElementEditPart.determineOrXorWarning(ie, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL);
			if (ie instanceof COREFeature) {
				// color 96,255,96 = SELECTED unless in featuresHash, then USER_SELECTED
				// color 169,169,169 = NOT_SELECTED_NO_ACTION
				// color anything else but the above two = NOT_SELECTED
				// warning = WARNING
				SelectionStatus selectionStatus = SelectionStatus.NOT_SELECTED;
				if (warning)
					selectionStatus = SelectionStatus.WARNING;
				else if (color.equals("169,169,169"))
					selectionStatus = SelectionStatus.NOT_SELECTED_NO_ACTION;
				else if (color.equals("96,255,96")) {
					if (featuresHash.containsKey(ie))
						selectionStatus = SelectionStatus.USER_SELECTED;
					else
						selectionStatus = SelectionStatus.SELECTED;
				}
				er.featureResult.put((COREFeature) ie, selectionStatus);
			}			
		}

		// return the evaluation result
		return (EvaluationResult) COREFactory4URN.returnResult(er);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public class EvaluationResult {
		public Map<COREFeature, SelectionStatus> featureResult = new HashMap<COREFeature, SelectionStatus>();
		public Map<COREImpactModelElement, Integer> impactResult = new HashMap<COREImpactModelElement, Integer>();	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public enum SelectionStatus {
		SELECTED, NOT_SELECTED, USER_SELECTED, NOT_SELECTED_NO_ACTION, WARNING;		
	}
	
} //FeatureModelImpl
