package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElementRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;
import urncore.Metadata;

/**
 *  use this command to reset Metadata (CoURN, reexposed intElementID:ID,ID,ID) to the currently executed EvaluationStrategy
 * if one of the reexposed Feature is set to un-reexpose
 * @author jiaying
 */

public class UnreexposeFeatureCommand extends Command implements JUCMNavCommand{

	private IntentionalElementRef intElemRef;
	private EvaluationStrategy strategy;
	private String oldValue;
	private String id;
	private static final String REEXPOSE_MATADATA = "CoURN"; //$NON-NLS-1$
	private static final String REEXPOSEVALUE_MATADATA = "reexposed intElementID:"; //$NON-NLS-1$
	
	public UnreexposeFeatureCommand(IntentionalElementRef intElemRef,  EvaluationStrategy strategy){
	    this.intElemRef = intElemRef;
	    this.strategy = strategy;
	    this.id = intElemRef.getDef().getId();
	    setLabel(Messages.getString("UnreexposeFeatureAction.UnreexposeFeature")); //$NON-NLS-1$
	}
	
	@Override
	public void testPreConditions() {
		// TODO Auto-generated method stub
		Metadata reexpostedList = MetadataHelper.getMetaDataObj( strategy, REEXPOSE_MATADATA);
		Evaluation eval = EvaluationStrategyManager.getInstance().getEvaluationObject( intElemRef.getDef());
		assert intElemRef != null :"pre no IntentionalElementRef is selected!";
		assert intElemRef.getDef().getId().equals( String.valueOf(id)) : "The element is not matched!";
		assert strategy != null : "pre no strategy is selected";
		assert eval.getStrategies() != strategy || (eval.getStrategies() == strategy && eval.getEvaluation() != 100);
		assert reexpostedList != null :"pre CoURN metadata of strategy is not null!";
	}

	@Override
	public void testPostConditions() {
		// TODO Auto-generated method stub
		assert intElemRef != null :"pre no IntentionalElementRef is selected!";
		assert intElemRef.getDef().getId().equals( String.valueOf(id)) : "The element is not matched!";
		assert strategy != null : "pre no strategy is selected";
	}
	
	public void execute(){
		redo();
	}
	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
	public void redo(){
		testPreConditions();
		// remove the runtime metadata FROM the IntentionalElement
		Metadata metadataInt = MetadataHelper.getMetaDataObj( intElemRef.getDef(), REEXPOSE_MATADATA);
//		if( metadataInt != null && metadataInt.getValue().equals( EvaluationStrategyManager.REEXPOSE_RUNTIMEMATADATAVAlUES )){
//			MetadataHelper.removeMetaData(intElemRef.getDef(), REEXPOSE_MATADATA);
//		}
		Metadata metadataStrgy = MetadataHelper.getMetaDataObj( strategy, REEXPOSE_MATADATA);
		if( metadataStrgy!= null ){
			oldValue = metadataStrgy.getValue();
			
			String[] ids = getReexposesList( oldValue );
			
        	String newValue = "";  //$NON-NLS-1$
        	for(int i= 0; i < ids.length ; i++){
        		 
        		if( !this.id.equals( ids[i]) ){
        			newValue += ids[i] + ","; //$NON-NLS-1$
         		}
        		 
        	}
        	
        	if( newValue.isEmpty()){
        		MetadataHelper.removeMetaData(strategy, REEXPOSE_MATADATA);
        	}else{
        		
        		if( newValue.lastIndexOf(",") == newValue.length()-1 )
           		   newValue = newValue.substring( 0, newValue.lastIndexOf(",")); //$NON-NLS-1$
        		newValue = REEXPOSEVALUE_MATADATA + newValue;
        		metadataStrgy.setValue(newValue);
        	}
		}
		EvaluationStrategyManager.getInstance().calculateEvaluation(); 
        testPostConditions();
	}
	
	
	/*
     * (non-Javadoc)
     * @parameter value of (key,value) set of reexpose metadata
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public String[] getReexposesList(String value){
    	
    	String middle = ":";
    	String[] ids = value.substring( value.indexOf(middle) + 1).split(",");
    	return ids;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions() ;
        
        Metadata metadataStrgy = MetadataHelper.getMetaDataObj( strategy, REEXPOSE_MATADATA);
        if( metadataStrgy!= null){
        	metadataStrgy.setValue(oldValue);	
        }else{
        	
        	URNspec urn = intElemRef.getDef().getGrlspec().getUrnspec();
        	MetadataHelper.addMetaData( urn, strategy, REEXPOSE_MATADATA, oldValue);
        }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); 
        
        testPreConditions();
        
    }
    

}
