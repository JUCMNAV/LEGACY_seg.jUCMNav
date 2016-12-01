package seg.jUCMNav.actions.features;

import org.eclipse.ui.IWorkbenchPart;

import fm.Feature;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.transformations.UnreexposeFeatureCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.util.FeatureUtil;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
/**
 * This action is used to un-reexpose one feature (remove the metadata of reexpose from strategy).
 * 
 * @author jiaying, gunterm
 * 
 */
public class UnreexposeFeatureAction extends URNSelectionAction {

	public static final String UNREEXPOSEFEATURE = Messages.getString("UnreexposeFeatureAction.UnreexposeFeature");  //$NON-NLS-1$
	
	
	public UnreexposeFeatureAction(IWorkbenchPart part) {
		super(part);
		setId(UNREEXPOSEFEATURE);
		setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/delete16.gif")); //$NON-NLS-1$
		
	}
	
	/* this method is used to check the ability of unreexpose action. only reexposed feature and not ReusedElement
	 * will enable this action
	 * */
	public boolean calculateEnabled(){
		
		if( getSelectedObjects().size()!= 1 )
			return false;
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
		if( (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) && (sel.getIntentionalElementRef().getDef()) instanceof Feature){
			// don't have consider if this IntentionalElement is ReusedElement, since only !ReusedElement will be reexposed
			IntentionalElementRef selection = sel.getIntentionalElementRef();
			Feature currFeature = (Feature) selection.getDef();
			if( strategy != null ){
				Evaluation eval = EvaluationStrategyManager.getInstance().getEvaluationObject( currFeature);
				if( FeatureUtil.isReexposed(currFeature)){
					return true;
				}
			}
			
		}
		
		return false;
		
		
	}
	
	public void run(){
		EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
		IntentionalElementRef intElemRef = new SelectionHelper(getSelectedObjects()).getIntentionalElementRef();
		
		execute(new UnreexposeFeatureCommand( intElemRef, strategy));
	}

}
