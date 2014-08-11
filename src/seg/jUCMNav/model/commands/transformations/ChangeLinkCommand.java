package seg.jUCMNav.model.commands.transformations;

import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;

import ca.mcgill.sel.core.COREFeatureRelationshipType;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import urncore.IURNConnection;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;
import urncore.Metadata;

/**
 * This command is used to align URNmodelElements.
 * 
 * @author Patrice Boulet
 * 
 */
public class ChangeLinkCommand extends Command implements JUCMNavCommand {

	private Feature feature = null;
	private COREFeatureRelationshipType newRelationship;
    private COREFeatureRelationshipType oldRelationship;
	
    /**
     * 
     */
    public ChangeLinkCommand(COREFeatureRelationshipType relationship, IntentionalElementRef elemRef) {
    	
    	this.feature = (Feature)elemRef.getDef();
    	this.newRelationship = relationship;
    	
    	if ( feature.getLinksSrc().get(0) instanceof MandatoryFMLink){
    		this.oldRelationship = COREFeatureRelationshipType.MANDATORY;
    	}else if ( feature.getLinksSrc().get(0) instanceof OptionalFMLink ){
    		this.oldRelationship = COREFeatureRelationshipType.OPTIONAL;
    	}else if ( feature.getLinksSrc().get(0) instanceof Decomposition ){
    		IntentionalElement intElem = (IntentionalElement)((ElementLink)feature.getLinksSrc().get(0)).getDest();
    		int decompType = intElem.getDecompositionType().getValue();
    	
    		if ( decompType == 1 ) /* OR */ {
    			this.oldRelationship = COREFeatureRelationshipType.OR;
    		}else if( decompType == 2) /* XOR */ {
    			this.oldRelationship = COREFeatureRelationshipType.XOR;
    		}	
 
    	}
    	setLabel(Messages.getString("ChangeLinkCommand.ChangeLink")); //$NON-NLS-1$
    }
    
    @Override
    public void execute(){
    	redo();
    }
    
    public void redo(){
    	feature.changeLink(newRelationship);
    }
    
    public void undo(){
    	feature.changeLink(oldRelationship);
    }
    
	@Override
	public void testPreConditions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPostConditions() {
		// TODO Auto-generated method stub
		
	}

}