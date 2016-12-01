package seg.jUCMNav.model.commands.transformations;

import java.util.Vector;

import org.eclipse.gef.commands.Command;

import grl.EvaluationStrategy;
import grl.IntentionalElementRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm;
import urn.URNspec;
import urncore.Metadata;

/**
 *  use this command to set Metadata (CoURN, reexposed intElementID:ID,ID,ID) to the currently executed EvaluationStrategy
 * @author jiaying
 */
public class ReexposeFeatureCommand extends Command implements JUCMNavCommand{

	private IntentionalElementRef intElemRef;
	private EvaluationStrategy strategy;
	private int id;
	private String oldMetadataValue;
	private static final String REEXPOSE_MATADATA = "CoURN"; //$NON-NLS-1$
	private static final String REEXPOSEVALUE_MATADATA = "reexposed intElementID:"; //$NON-NLS-1$
	
	public ReexposeFeatureCommand(IntentionalElementRef intElementRef, EvaluationStrategy strategy, int id){
		this.intElemRef = intElementRef;
		this.strategy = strategy;
		this.id = id;
		setLabel(Messages.getString("ReexposeFeatureAction.ReexposeFeature")); //$NON-NLS-1$
		
	}
	
	/**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        Metadata reexpostedList = MetadataHelper.getMetaDataObj( strategy, REEXPOSE_MATADATA);
        
       
        // Run-time metadata already exists for this element, so directly set new value
        if (reexpostedList != null) {
        	oldMetadataValue = reexpostedList.getValue();
        	
        	String[] ids = getReexposesList(oldMetadataValue);
        	boolean addBefore = false;
        	for(int i= 0; i < ids.length ; i++){
        		 
        			 addBefore = addBefore | (this.id == Integer.parseInt(ids[i]));
        		 
        	}
        	if(!addBefore){
        		String newValue = oldMetadataValue + "," + String.valueOf(id); //$NON-NLS-1$
        		reexpostedList.setValue(newValue);	
        	}
        	
        }else {
            URNspec urnSpec = intElemRef.getDef().getGrlspec().getUrnspec();
            String newValue = REEXPOSEVALUE_MATADATA + String.valueOf(id);
            MetadataHelper.addMetaData( urnSpec, strategy, REEXPOSE_MATADATA, newValue);

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
    	
    	String middle = ":"; //$NON-NLS-1$
    	String[] ids = value.substring( value.indexOf(middle) + 1).split(","); //$NON-NLS-1$
    	return ids;
    }
    
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions() ;

        
        if( oldMetadataValue != null ){
        	Metadata metaNumerical = MetadataHelper.getMetaDataObj(strategy, REEXPOSE_MATADATA);
        	metaNumerical.setValue(oldMetadataValue);
        	
        }else{
        	// if there is no element reexposed in the Strategy, remove the MetaData
        	MetadataHelper.removeMetaData(strategy, REEXPOSE_MATADATA);
        }
       
        EvaluationStrategyManager.getInstance().calculateEvaluation(); 
        testPreConditions();
    }
    
	@Override
	public void testPreConditions() {
		// TODO Auto-generated method stub
		assert intElemRef != null :"pre no IntentionalElementRef is selected!";
		assert Integer.valueOf(intElemRef.getDef().getId()) == id : "The element is not matched!";
		assert strategy != null : "pre no strategy is selected";
		
	}

	@Override
	public void testPostConditions() {
		// TODO Auto-generated method stub
		 Metadata reexpostedList = MetadataHelper.getMetaDataObj( strategy, REEXPOSE_MATADATA);
		 assert reexpostedList != null :"post not add reexpose metadata successfully!";
		 assert intElemRef != null :"pre no IntentionalElementRef is selected!";
		 assert strategy != null : "pre no strategy is selected";
	}

}
