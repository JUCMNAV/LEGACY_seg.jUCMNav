package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * This command is used to distribute URNmodelElements.
 * 
 * @author Patrice Boulet
 * 
 */
public class DistributeCommand extends AlignDistributeCommand {

	
    private HashMap<String, Integer> newCoordinates;
    private HashMap<URNmodelElement, Integer> elemsRadius;
   
    private int newPositionElemDimension;
    private int spacingDistance;
   
    private boolean custom;
    private boolean equalGaps;
    private boolean containerMove;
    private boolean extendUpperBound;
 
    
    
    /**
     * 
     * @param sel
     *            Contains the selection to be distributed
     * @param selType
     *            Tells the type of selection we're dealing with (e.g. IntentionalElements)
     * @param moveType
     *            Tells the type of distribute move we're doing (e.g. DistributeCentersVertically)
     */
    public DistributeCommand(List sel, int selType, String moveType, boolean custom) {


    	setLabel(Messages.getString("SetConstraintCommand.Distribute")); //$NON-NLS-1$
        
    	this.sel = sel;
    	this.selType = selType;
    	this.moveType = moveType;
    	this.custom = custom;
    	
    	newCoordinates = new HashMap<String, Integer>();
    	elemsRadius = new HashMap<URNmodelElement, Integer>();
    	equalGaps = false;
    	containerMove = false;
    	extendUpperBound = false;
    	
    	/*
    	 * Sets the algorithm for a vertical move
    	 */
    	if( moveType.compareTo("seg.jUCMNav.DistributeCentersVertically") == 0 ||
    			moveType.compareTo("seg.jUCMNav.DistributeVertically") == 0){
    		verticalMove = true;
    		axis = "y";
    	}
    	
    	/*
    	 * Sets the algorithm for a distribution with equal gaps between each object of the selection
    	 */
    	if( moveType.compareTo("seg.jUCMNav.DistributeVertically") == 0 || 
    			moveType.compareTo("seg.jUCMNav.DistributeHorizontally") == 0 )
    		equalGaps = true;
    	
  	
    	/*
    	 * Sets the algorithm to distribute IURNContainers
    	 */
    	if (Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
    			Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0)
    		containerMove = true;
    	
    	
    	/*
    	 * This is the main control part of the class; decides which part of
    	 * algorithm is called.
    	 */
    	if (containerMove){
    		
    		coordinatesValuesContainer = new HashMap<IURNContainerRef, HashMap <String, Integer>>() ;
    		newCoordinates = findBoundsCoordinatecontainer();
        	getOldCoordinates();
          	getElementsRadius();
        	spacingDistance = getSpacingDistance();
    		
    	}else{
    		
    		coordinatesValues = new HashMap <String, HashMap <String, Integer>>();
    		newCoordinates = findBoundsCoordinate();
    		getOldCoordinates();
    	  	getElementsRadius();
    	  	spacingDistance = getSpacingDistance();

    	}
    	addMoveCommand();
    	
    }

    /*
     * Method that stores the radius in x/y of each element
     * of the selection.
     */
	private void getElementsRadius() {
		if(verticalMove){
		
			if (containerMove){
				
				for(IURNContainerRef container : (List<IURNContainerRef>)sel){
					elemsRadius.put((URNmodelElement)container, container.getHeight()/2);
				}	
				
			}else{
				for (IURNNode node : (List<IURNNode>)sel){
					
					if( MetadataHelper.getMetaData( (URNmodelElement)node, "_height") != null){
						elemsRadius.put((URNmodelElement)node, 
								(Integer.valueOf(MetadataHelper.getMetaData( (URNmodelElement)node, "_height"))/2));
					}else{
						elemsRadius.put((URNmodelElement)node, 0);
					}
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
					
					if( MetadataHelper.getMetaData( (URNmodelElement)node, "_width") != null){
					
						elemsRadius.put((URNmodelElement)node, 
							(Integer.valueOf(MetadataHelper.getMetaData( (URNmodelElement)node, "_width"))/2));
					}else{
						elemsRadius.put((URNmodelElement)node, 0);
					}
					
				}
			}
		}
		
	}

	/*
	 * A method that gets the distance that is going to
	 * separate the x/y centers of each element of the distribution
	 * or the equal distance that is going to separate each element
	 * of the distribution depending on the type of move.
	 */
	private int getSpacingDistance() {
			
		int result;
		
		if( equalGaps ){
			
			int sumOfAllDimensions = 0;
			int distanceBetweenBounds = 0;
			
			for ( URNmodelElement elem : (List<URNmodelElement>) sel){
				sumOfAllDimensions += (elemsRadius.get(elem)*2);
			}
			
			distanceBetweenBounds = newCoordinates.get("UpperBound") - newCoordinates.get("LowerBound");
			
			result = (distanceBetweenBounds - sumOfAllDimensions)/(sel.size()-1);
			
			/*
			 * If there's not enough space to fit all the elements of
			 * the selection without doing some overlapping, put a default
			 * value of spacing between each element of the distribution and extend
			 * the upper bound.
			 */
			if( result < 0 || custom){

				result = Integer.valueOf(GeneralPreferencePage.getDistributeSpacing());
				extendUpperBound = true;
			}
			
		}else{
			result = (newCoordinates.get("UpperBound") - newCoordinates.get("LowerBound"))/(sel.size()-1) ;
		}
		return result;
	}

	private void addMoveCommand() {
		
		/*
		 * The coordinate at which the algorithm is positioned after each iteration.
		 */
		int currentCoordinateDistance;
		
		if(equalGaps){
			currentCoordinateDistance = newCoordinates.get("LowerBound") + elemsRadius.get((URNmodelElement)sel.get(0))*2;
		}else{
			currentCoordinateDistance = newCoordinates.get("LowerBound");
		}
		
		/*
		 *  Adds the move command accordingly to the moveType and selType to be 
		 *  executed on the Command stack after this command.
		 */
		if(verticalMove){
			if ( containerMove){
				// adds the move command to be executed on the Command stack after this command
		    	for(IURNContainerRef currentContainer : (List<IURNContainerRef>)sel){
					
		    		if( ((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(0).getId() ){
			    		
		    			if (((URNmodelElement)currentContainer).getId() == ((List<URNmodelElement>)sel).get(sel.size()-1).getId() &&
		    					!extendUpperBound){
		    				break;
		    			}
		    			
		    			int calculatedDistance = findCalculatedDistance(currentCoordinateDistance, (URNmodelElement)currentContainer);
			    		add( new SetConstraintBoundContainerRefCompoundCommand(currentContainer, currentContainer.getX(), 
								 calculatedDistance , currentContainer.getWidth(), currentContainer.getHeight(), true));
			    		currentCoordinateDistance = calculateCurrentCoordinateDistance(currentCoordinateDistance, (URNmodelElement)currentContainer);
			    		
		    		}		
		    	}
				
			}else{
		    	for(IURNNode currentNode : (List<IURNNode>)sel){
		    		
		    		if( ((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(0).getId()){
		    		
		    			if (((URNmodelElement)currentNode).getId() == ((List<URNmodelElement>)sel).get(sel.size()-1).getId() &&
		    					!extendUpperBound){
		    				break;
		    			}
		    			
		    			int calculatedDistance = findCalculatedDistance(currentCoordinateDistance, (URNmodelElement)currentNode);
		    			add( new SetConstraintGrlNodeCommand(currentNode, currentNode.getX(), calculatedDistance, true));
		    			currentCoordinateDistance = calculateCurrentCoordinateDistance(currentCoordinateDistance, (URNmodelElement)currentNode);
		    		}
		    	}
			}
			
		}else{
			if (containerMove){
				
		    	for(IURNContainerRef currentContainer : (List<IURNContainerRef>)sel){
		    		
		    		if( ((URNmodelElement)currentContainer).getId() != ((List<URNmodelElement>)sel).get(0).getId()){
			    		
		    			if (((URNmodelElement)currentContainer).getId() == ((List<URNmodelElement>)sel).get(sel.size()-1).getId() &&
		    					!extendUpperBound){
		    				break;
		    			}
		    			
		    			int calculatedDistance = findCalculatedDistance(currentCoordinateDistance, (URNmodelElement)currentContainer);
			    		add( new SetConstraintBoundContainerRefCompoundCommand(currentContainer, calculatedDistance, 
								 currentContainer.getY() , currentContainer.getWidth(), currentContainer.getHeight(), true));
			    		currentCoordinateDistance = calculateCurrentCoordinateDistance(currentCoordinateDistance, (URNmodelElement)currentContainer);
		    		}
		    		}	
				
			}else{
				
		    	for(IURNNode currentNode : (List<IURNNode>)sel){
		    		if( ((URNmodelElement)currentNode).getId() != ((List<URNmodelElement>)sel).get(0).getId()){
			    		
		    			if (((URNmodelElement)currentNode).getId() == ((List<URNmodelElement>)sel).get(sel.size()-1).getId() &&
		    					!extendUpperBound){
		    				break;
		    			}
		    			
		    			int calculatedDistance = findCalculatedDistance(currentCoordinateDistance, (URNmodelElement)currentNode);
						add( new SetConstraintGrlNodeCommand(currentNode, calculatedDistance, currentNode.getY(), true));
						currentCoordinateDistance = calculateCurrentCoordinateDistance(currentCoordinateDistance, (URNmodelElement)currentNode);
					}
		    	}
			}
		}
	}

	/*
	 * Calculates the position where the previous 
	 * iteration of the algorithm left.
	 */
	private int calculateCurrentCoordinateDistance( int currentCoordinateDistance, URNmodelElement currentElem) {
		
		if( equalGaps){
			currentCoordinateDistance = currentCoordinateDistance + spacingDistance + elemsRadius.get(currentElem)*2;
		}else{
			currentCoordinateDistance = currentCoordinateDistance + spacingDistance;
		}
		
		return currentCoordinateDistance;
	}

	/*
	 * Calculates the distance at which the element
	 * of this iteration is going to be moved.
	 */
	private int findCalculatedDistance(int currentCoordinateDistance, URNmodelElement currentElem) {
		
		int calculatedDistance;
		
		if( equalGaps){
			calculatedDistance = currentCoordinateDistance + spacingDistance;
		}else{
			calculatedDistance = currentCoordinateDistance + spacingDistance - elemsRadius.get(currentElem);
		}
		
		return calculatedDistance;
	}
    
    private HashMap<String, Integer> findBoundsCoordinate(){
       
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
    	
    		/*
    		 *  Sorts the elements in ascending order compared with their y/x value field
    		 */
	    	Collections.sort(sel, new Comparator<IURNNode>() {
	    	    public int compare(IURNNode nodeA, IURNNode nodeB) {
	    	    	if ( verticalMove)
	    	    		return Integer.valueOf(nodeA.getY()).compareTo(nodeB.getY());
	    	    	else
	    	    		return Integer.valueOf(nodeA.getX()).compareTo(nodeB.getX());
	    	    }
	    	});

	    	/*
	    	 * Assigns a new coordinate for the URNmodelElement depending on the moveType variable
	    	 */
    		if( verticalMove){
    			int lowerBoundRadius, upperBoundRadius;
    			
    			if( MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_height" ) != null){
    				lowerBoundRadius = Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_height" ))/2;
    				upperBoundRadius = Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get((sel.size()-1))), "_height" ))/2;	
    			}else{
    				lowerBoundRadius = 0;
    				upperBoundRadius = 0;
    			}
    			if ( equalGaps){
    				result.put("LowerBound", ( ((IURNNode)sel.get(0)).getY()));
    				result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getY()) + upperBoundRadius*2);
    			}else{
    				result.put("LowerBound", ( ((IURNNode)sel.get(0)).getY()) + lowerBoundRadius);
    				result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getY()) + upperBoundRadius);
    			}
    			
    		}else{
    			int lowerBoundRadius, upperBoundRadius;
    			
    			if( MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_width" ) != null){
    				lowerBoundRadius = Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get(0)), "_width" ))/2;
    				upperBoundRadius = Integer.valueOf(MetadataHelper.getMetaData( ((URNmodelElement)sel.get((sel.size()-1))), "_width" ))/2;	
    			}else{
    				lowerBoundRadius = 0;
    				upperBoundRadius = 0;
    			}
    			if ( equalGaps){
    				result.put("LowerBound", ( ((IURNNode)sel.get(0)).getX()));
    				result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getX()) + upperBoundRadius*2);
    			}else{
    				result.put("LowerBound", ( ((IURNNode)sel.get(0)).getX())  + lowerBoundRadius);
    				result.put("UpperBound", ( ((IURNNode)sel.get((sel.size()-1))).getX()) + upperBoundRadius);
    			}
    		}
    	
    	return result;
    }
    
    private HashMap<String, Integer> findBoundsCoordinatecontainer(){
        
    	HashMap<String, Integer> result = new HashMap<String, Integer>();
	
    		/*
    		 *  Sorts the elements in ascending order compared with their y/x value field
    		 */
	    	Collections.sort(sel, new Comparator<IURNContainerRef>() {
	    	    public int compare(IURNContainerRef containerA, IURNContainerRef containerB) {
	    	        if( verticalMove)
	    	        	return Integer.valueOf(containerA.getY()).compareTo(containerB.getY());
	    	        else
	    	        	return Integer.valueOf(containerA.getX()).compareTo(containerB.getX());
	    	    }
	    	});
    	
	    List<IURNContainerRef> selTemp = (List<IURNContainerRef>) sel;
    	
    	/*
    	 * Assigns a new coordinate for the URNmodelElement depending on the moveType variable
    	 */
 		if( verticalMove){
			if ( equalGaps){
				result.put("LowerBound", (selTemp.get(0).getY()) );
				result.put("UpperBound", (selTemp.get((selTemp.size()-1)).getY()) + 
						selTemp.get((selTemp.size()-1)).getHeight());
			}else{
 			result.put("LowerBound", (selTemp.get(0).getY()) + 
					selTemp.get(0).getHeight()/2 );
			result.put("UpperBound", (selTemp.get((selTemp.size()-1)).getY()) + 
					selTemp.get((selTemp.size()-1)).getHeight()/2);
			}
		}else{
			if ( equalGaps){
				result.put("LowerBound", (selTemp.get(0).getX()) );
				result.put("UpperBound", (selTemp.get((selTemp.size()-1)).getX()) + 
						selTemp.get((selTemp.size()-1)).getWidth());
			}else{
			result.put("LowerBound", ( (selTemp.get(0)).getX()) + 
					selTemp.get(0).getWidth()/2);
			result.put("UpperBound", ( (selTemp.get((sel.size()-1))).getX()) + 
					selTemp.get((sel.size()-1)).getWidth()/2 );
			}
		}
    	
    	return result;
    }
    
}