package seg.jUCMNav.actions.features;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import fm.Feature;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.delete.DeleteEvaluationCommand;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.util.FeatureUtil;
import seg.jUCMNav.strategies.util.ReusedElementUtil;
import urn.URNspec;
import urncore.Metadata;
import seg.jUCMNav.model.commands.transformations.ReexposeFeatureCommand;;
/**
 * This action is used to reexpose one feature (add reexpose metamodel to strategy).
 * 
 * @author jiaying, gunterm
 * 
 */
public class ReexposeFeatureAction extends URNSelectionAction {

	public static final String REEXPOSEFEATURE = Messages.getString("ReexposeFeatureAction.ReexposeFeature"); //$NON-NLS-1$
	private static final String REEXPOSE_MATADATA = "CoURN"; //$NON-NLS-1$
	
	public ReexposeFeatureAction(IWorkbenchPart part) {
		super(part);
		setId(REEXPOSEFEATURE);
		setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SelectFeature.gif")); //$NON-NLS-1$
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * We need to have an intentional element reference of a feature definition selected when the ElementLink
	 * type of the feature is Decomposition(OR/XOR) or OptionalFMLink
	 */
	protected boolean calculateEnabled() {
		EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy(); 
		if ( strategy == null)
			return false;

		boolean reexposable = false;
		for (Iterator<?> iter = getSelectedObjects().iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (!(obj instanceof IntentionalElementEditPart) || 
					(obj instanceof IntentionalElementEditPart) && !(((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef() instanceof Feature)
					|| (obj instanceof IntentionalElementEditPart) && (((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef() instanceof Feature) 
						&& ReusedElementUtil.isReusedElement(strategy.getGrlspec(), ((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef()))
				return false;
			else {
				Feature selectedFeature = (Feature)((IntentionalElementRef)((IntentionalElementEditPart) obj).getModel()).getDef();
                Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(selectedFeature);
                boolean typeOfSelectedFeatureCorrect = false;
                for(ElementLink link : (EList<ElementLink>)selectedFeature.getLinksSrc()){
                	if( link instanceof Decomposition && link.getDest() instanceof Feature){
                		if(((Feature)link.getDest()).getDecompositionType()== DecompositionType.OR_LITERAL ||
                				((Feature)link.getDest()).getDecompositionType() == DecompositionType.XOR_LITERAL )
                		          	typeOfSelectedFeatureCorrect = true;
                	}
                 	if(link.getDest() instanceof Feature && link instanceof OptionalFMLink){
                 		typeOfSelectedFeatureCorrect = true;
                 	}
                }
                // if and only if this model urn is Concern-oriented
                URNspec urn = selectedFeature.getGrlspec().getUrnspec();
                
                String value = MetadataHelper.getMetaData( urn, REEXPOSE_MATADATA );
                String reexposeIDs = MetadataHelper.getMetaData(strategy, REEXPOSE_MATADATA );
//                for(String currID : reexposeIDs.substring( reexposeIDs.indexOf(":")+1).split(",")){
//                	if( currID.equals(selectedFeature.getId()))
//                		return false;
//                }
                
                String runtimeEval = MetadataHelper.getMetaData(selectedFeature, EvaluationStrategyManager.METADATA_NUMEVAL);
                Evaluation eval = EvaluationStrategyManager.getInstance().getEvaluationObject(selectedFeature);
                if( eval.getStrategies() != strategy && runtimeEval != null && Integer.valueOf(runtimeEval) == 100)
                	  return false;
                if( value != null && value.equalsIgnoreCase("true") && typeOfSelectedFeatureCorrect &&
                		 !FeatureUtil.isReexposed(selectedFeature) && !FeatureUtil.
                		 isRootFeature(selectedFeature, selectedFeature.getGrlspec())){
//                     if( (evaluation.getStrategies() == null && evaluation.getEvaluation()!=100) || (evaluation.getStrategies() == strategy && evaluation.getEvaluation() !=100) )
//                	     reexposable = true && typeOfSelectedFeatureCorrect;
                	 return true;
                }
			}
		}

		return false;
	}
 
	public void run() {
		
		EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy(); 
		Vector<IntentionalElementRef> intElementRefs = new Vector<IntentionalElementRef>();
		
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF && (sel.getIntentionalElementRef().getDef() instanceof Feature)) {
            IntentionalElementRef selection = sel.getIntentionalElementRef();
            intElementRefs.add(selection);
            URNspec currUrn = selection.getDef().getGrlspec().getUrnspec();
            String id = selection.getDef().getId();
            CompoundCommand stack = new CompoundCommand();
            
            Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject( selection.getDef());
            if( evaluation.getStrategies() == strategy && evaluation.getEvaluation() == 100){
            	stack.add(new DeleteEvaluationCommand(evaluation));
            }
            
            stack.add( new ReexposeFeatureCommand( selection, strategy, Integer.parseInt(id) ));
            execute(stack);
            intElementRefs.removeAllElements();
        }
		
	
	}
	
}
