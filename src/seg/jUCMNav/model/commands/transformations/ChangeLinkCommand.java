package seg.jUCMNav.model.commands.transformations;

import java.util.Stack;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteLinkRefCommand;
import urn.URNspec;
import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;

/**
 * This Command is used to change the link type of a Feature.
 * 
 * @author pboul037
 * 
 */
public class ChangeLinkCommand extends Command implements JUCMNavCommand {

	private Feature feature;
	private String newRelationship;
    private Stack<Command> delegateCommandStack;
    
    public static final String FEATURE_MANDATORY_RELATIONSHIP = "FeatureMandatoryRelationShip";
    public static final String FEATURE_OPTIONAL_RELATIONSHIP = "FeatureOptionalRelationShip";
    public static final String FEATURE_OR_RELATIONSHIP = "FeatureOrRelationShip";
    public static final String FEATURE_XOR_RELATIONSHIP = "FeatureXorRelationShip";
    public static final String FEATURE_AND_RELATIONSHIP = "FeatureAndRelationShip";
	
    /**
     * Constructor.
     * 
     * @param relationship
     * 		new type for the link
     * @param elemRef
     * 		the Feature at the source of the link to change
     */
    public ChangeLinkCommand(String relationship, IntentionalElementRef elemRef) {
    	
    	this.feature = (Feature)elemRef.getDef();
    	this.newRelationship = relationship;
    	delegateCommandStack = new Stack<Command>();
    	
    	
    	setLabel(Messages.getString("ChangeLinkCommand.ChangeLink")); //$NON-NLS-1$
    }
    
    @Override
    public void execute(){
    	redo();
    }
    
    @Override
    public boolean canExecute(){
    	if( feature == null || newRelationship == null){
    		return false;
    	}else{
    		return true;
    	}	
    }
    
    public void redo(){
    	
    	ElementLink oldLink = (ElementLink) feature.getLinksSrc().get(0);
		IntentionalElement element = (IntentionalElement) oldLink.getDest();
		
		String position = String.valueOf(element.getLinksDest().indexOf(oldLink));
		URNspec urn = feature.getGrlspec().getUrnspec();
		
		createNewLink(element, (IntentionalElement) feature, urn, position, newRelationship, oldLink);

    }
    
    public void undo(){
    	while( ! delegateCommandStack.isEmpty() ){
    		delegateCommandStack.pop().undo();
    	}
    }
    
	@Override
	public void testPreConditions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPostConditions() {
		// TODO Auto-generated method stub
		
	}
	
	  
	 /**
	  * Deletes the parent link if <b>oldLink</b> is not null and then creates
	  * a new <b>ElementLink</b> between two <b>IntentionalElement</b> and 
	  * sets a  new <b>DecompositionType</b> if applicable.
	  * 
	  * @param linkTarget
	  * 		target of the new link
	  * @param linkSource
	  * 		source of the new link
	  * @param urn
	  * 		current URNspec 
	  * @param link
	  * 		new link
	  * @param strPosition
	  * 		a String representation of the position 
	  * 		of the child in relation to its parent
	  * @param strRelationship
	  * 		type of the new link
	  * @param oldLink
	  * 		a reference to the parent ElementLink to delete, if ! null
	  * 
	  * @author pboul037
	  */
		public boolean createNewLink(IntentionalElement linkTarget, IntentionalElement linkSource, 
							URNspec urn, String strPosition, String strRelationship, ElementLink oldLink) {
			
			// if there's an old link to the parent to delete
			if( oldLink != null){
				DeleteLinkRefCommand deleteParentLinkCmd = new DeleteLinkRefCommand((LinkRef)oldLink.getRefs().get(0));
				if( deleteParentLinkCmd.canExecute() ){
					deleteParentLinkCmd.execute();
					delegateCommandStack.push(deleteParentLinkCmd);
				}else{
					return false;
				}
			}
			
			ElementLink link = null;
			int type = new Integer(0);
			
			if (strRelationship == ChangeLinkCommand.FEATURE_MANDATORY_RELATIONSHIP) {
				// add mandatory link between this feature and the new child feature
				link = (ElementLink) ModelCreationFactory.getNewObject(urn, MandatoryFMLink.class);								
			} else if (strRelationship == ChangeLinkCommand.FEATURE_OPTIONAL_RELATIONSHIP) {
				// add optional link between this feature and the new child feature
				link = (ElementLink) ModelCreationFactory.getNewObject(urn, OptionalFMLink.class);
			} else if (strRelationship == ChangeLinkCommand.FEATURE_XOR_RELATIONSHIP) {
				// add XOR decomposition link between this feature and the new child feature
				link = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
				type = new Integer(2);
			} else if (strRelationship == ChangeLinkCommand.FEATURE_OR_RELATIONSHIP) {
				// add OR decomposition link between this feature and the new child feature
				link = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
				type = new Integer(1);
			}
			
			CreateElementLinkCommand celCmd = new CreateElementLinkCommand(urn, linkSource, link, strPosition);
			celCmd.setTarget(linkTarget);
			if (celCmd.canExecute()){
				celCmd.execute();
				delegateCommandStack.push(celCmd);
			}else{
				return false;
			}
			
			ChangeDecompositionTypeCommand cdtCmd = new ChangeDecompositionTypeCommand((IntentionalElementRef)linkTarget.getRefs().get(0), type);
			if (cdtCmd.canExecute()){
				cdtCmd.execute();
				delegateCommandStack.push(cdtCmd);
			}else{
				return false;
			}
			
			return true;
		}

}