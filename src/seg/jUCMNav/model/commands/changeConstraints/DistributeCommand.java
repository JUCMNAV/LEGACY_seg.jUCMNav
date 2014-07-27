package seg.jUCMNav.model.commands.changeConstraints;

import grl.IntentionalElementRef;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gef.NodeEditPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
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
public class DistributeCommand extends AlignDistributeCommand {

    private HashMap<String, Integer> newCoordinates;
    private int newPositionElemDimension;
    private int spacingDistance;
    private HashMap<URNmodelElement, Integer> elemsRadius;
    
    /**
     * 
     * @param node
     *            The SpecificationNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     */
    public DistributeCommand(List sel, int selType, String moveType) {
        

    	setLabel(Messages.getString("SetConstraintCommand.Distribute")); //$NON-NLS-1$
        
    	this.sel = sel;
    	this.selType = selType;
    	this.moveType = moveType;
    	newCoordinates = new HashMap<String, Integer>();
    	elemsRadius = new HashMap<URNmodelElement, Integer>();
    	
    	if( moveType.compareTo("seg.jUCMNav.DistributeVertically") == 0 ){
    		verticalMove = true;
    		axis = "y";
    	}
  	
    	
    	if (Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
    			Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
    		
    		coordinatesValuesContainer = new HashMap<IURNContainerRef, HashMap <String, Integer>>() ;
    		newCoordinates = findBoundsCoordinatecontainer();
        	getOldCoordinates();
        	spacingDistance = getSpacingDistance();
        	getElementsRadius();
    		
    	}else{
    		
    		coordinatesValues = new HashMap <String, HashMap <String, Integer>>();
    		newCoordinates = findBoundsCoordinate();
    		getOldCoordinates();
    	  	spacingDistance = getSpacingDistance();
    	  	getElementsRadius();
    	}

    	addMoveCommand();

    }

	private void getElementsRadius() {
		if(verticalMove){
		
			if (Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
	    			Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
				
				for(IURNContainerRef container : (List<IURNContainerRef>)sel){
					elemsRadius.put((URNmodelElement)container, container.getHeight()/2);
				}	
				
			}else{
				for (IURNNode node : (List<IURNNode>)sel){
					elemsRadius.put((URNmodelElement)node, 
							(Integer.valueOf(MetadataHelper.getMetaData( (URNmodelElement)node, "_height"))/2));
				}
			}
						
		}else{
			
			if (Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
	    			Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
				
				for(IURNContainerRef container : (List<IURNContainerRef>)sel){
					elemsRadius.put((URNmodelElement)container, container.getWidth()/2);
				}	
				
			}else{
				for (IURNNode node : (List<IURNNode>)sel){
					elemsRadius.put((URNmodelElement)node, 
							(Integer.valueOf(MetadataHelper.getMetaData( (URNmodelElement)node, "_width"))/2));
				}
			}
		}
		
	}

	private int getSpacingDistance() {
			
		int result;
		
		result = (newCoordinates.get("UpperBound") - newCoordinates.get("LowerBound"))/(sel.size()-1) ;
		
		return result;
	}

	private void addMoveCommand() {
		
		int currentCoordinateDistance = newCoordinates.get("LowerBound");
		
		if(verticalMove){
			if ( Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
					Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
				// adds the move command to be executed on the Command stack after this command
		    	for(IURNContainerRef currentContainer : (List<IURNContainerRef>)sel){
					
		    		if( ((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(0).getId() && 
		    				((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(sel.size()-1).getId()){
			    		int calculatedDistance = currentCoordinateDistance + spacingDistance - elemsRadius.get((URNmodelElement)currentContainer);
			    		
			    		add( new SetConstraintBoundContainerRefCompoundCommand(currentContainer, currentContainer.getX(), 
								 calculatedDistance , currentContainer.getWidth(),
									currentContainer.getHeight(), true));
			    		
			    		currentCoordinateDistance = currentCoordinateDistance + spacingDistance;
		    	}
		    		}	
				
			}else{
				// adds the move command to be executed on the Command stack after this command
		    	for(IURNNode currentNode : (List<IURNNode>)sel){
		    		
		    		if( ((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(0).getId() && 
		    				((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(sel.size()-1).getId()){
		    		
		    			int calculatedDistance = currentCoordinateDistance + spacingDistance - elemsRadius.get((URNmodelElement)currentNode);
		    		
		    			add( new SetConstraintGrlNodeCommand(currentNode, currentNode.getX(),
							calculatedDistance, true));
				
		    			currentCoordinateDistance = currentCoordinateDistance + spacingDistance;
		    		}
		    	}
			}
			
		}else{
			
			if ( Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
					Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
				// adds the move command to be executed on the Command stack after this command
		    	for(IURNContainerRef currentContainer : (List<IURNContainerRef>)sel){
		    		if( ((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(0).getId() && 
		    				((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(sel.size()-1).getId()){
			    		int calculatedDistance = currentCoordinateDistance + spacingDistance - elemsRadius.get((URNmodelElement)currentContainer);
			    		
			    		add( new SetConstraintBoundContainerRefCompoundCommand(currentContainer, calculatedDistance, 
								 currentContainer.getY() , currentContainer.getWidth(),
									currentContainer.getHeight(), true));
			    		
			    		currentCoordinateDistance = currentCoordinateDistance + spacingDistance;
		    		}
		    		}	
				
			}else{
				// adds the move command to be executed on the Command stack after this command
		    	for(IURNNode currentNode : (List<IURNNode>)sel){
		    		if( ((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(0).getId() && 
		    				((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(sel.size()-1).getId()){
			    		int calculatedDistance = currentCoordinateDistance + spacingDistance - elemsRadius.get((URNmodelElement)currentNode);
			    		
						add( new SetConstraintGrlNodeCommand(currentNode, calculatedDistance,
								currentNode.getY(), true));
					
						currentCoordinateDistance = currentCoordinateDistance + spacingDistance;
		    		}
		    	}
			}
		}
	}
    
    private HashMap<String, Integer> findBoundsCoordinate(){
       
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
    	
    		// sorts the elements in ascending order compared with their y value field
	    	Collections.sort(sel, new Comparator<IURNNode>() {
	    	    public int compare(IURNNode nodeA, IURNNode nodeB) {
	    	    	if ( verticalMove)
	    	    		return Integer.valueOf(nodeA.getY()).compareTo(nodeB.getY());
	    	    	else
	    	    		return Integer.valueOf(nodeA.getX()).compareTo(nodeB.getX());
	    	    }
	    	});

    	// assigns a new coordinate for the URNmodelElement depending on the moveType variable
    		if( verticalMove){
    			result.put("LowerBound", ( ((IURNNode)sel.get(0)).getY()) + 
    					Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_height" ))/2);
    			result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getY()) + 
    					Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get((sel.size()-1))), "_height" ))/2);
    		}else{
    			result.put("LowerBound", ( ((IURNNode)sel.get(0)).getX())  + 
    					Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_width" ))/2);
    			
    			result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getX()) +
    					Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get((sel.size()-1))), "_width" ))/2);
    		}
    	
    	return result;
    }
    
    private HashMap<String, Integer> findBoundsCoordinatecontainer(){
        
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
	
    		// sorts the elements in ascending order compared with their coordinate value field
	    	Collections.sort(sel, new Comparator<IURNContainerRef>() {
	    	    public int compare(IURNContainerRef containerA, IURNContainerRef containerB) {
	    	        if( verticalMove)
	    	        	return Integer.valueOf(containerA.getY()).compareTo(containerB.getY());
	    	        else
	    	        	return Integer.valueOf(containerA.getX()).compareTo(containerB.getX());
	    	    }
	    	});
    	
	    List<IURNContainerRef> selTemp = (List<IURNContainerRef>) sel;
    	
    	// assigns a new coordinate for the URNmodelElement depending on the moveType variable
 		if( verticalMove){
			result.put("LowerBound", (selTemp.get(0).getY()) + 
					selTemp.get(0).getHeight()/2 );
			result.put("UpperBound", (selTemp.get((selTemp.size()-1)).getY()) + 
					selTemp.get((selTemp.size()-1)).getHeight()/2);
		}else{
			result.put("LowerBound", ( (selTemp.get(0)).getX()) + 
					selTemp.get(0).getWidth()/2);
			result.put("UpperBound", ( (selTemp.get((sel.size()-1))).getX()) + 
					selTemp.get((sel.size()-1)).getWidth()/2 );
		}
    	
    	return result;
    }
    
}