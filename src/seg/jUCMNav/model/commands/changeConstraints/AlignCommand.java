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
public class AlignCommand extends AlignDistributeCommand {

    private int newCoordinate;
    private int newPositionElemDimension;
    private boolean containerSel;
    private String alignModelId;

    
    /**
     * 
     * @param node
     *            The SpecificationNode to move
     * @param x
     *            the new X
     * @param y
     *            the new Y
     */
    public AlignCommand(List sel, int selType, String moveType) {
        

    	setLabel(Messages.getString("SetConstraintCommand.Align")); //$NON-NLS-1$
        
    	this.sel = sel;
    	this.selType = selType;
    	this.moveType = moveType;
    	containerSel = false;
    	alignModelId = "notValidId";
    	
    	if( moveType.compareTo("seg.jUCMNav.AlignBottom") == 0 ||
    			moveType.compareTo("seg.jUCMNav.AlignTop") == 0 ||
    					moveType.compareTo("seg.jUCMNav.AlignMiddle") == 0 ){
    		verticalMove = true;
    		axis = "y";
    	}
    	
       	if (Integer.valueOf(selType).compareTo(SELTYPE_ACTOR) == 0 ||
    			Integer.valueOf(selType).compareTo(SELTYPE_COMPONENT) == 0){
       		containerSel = true;
       		}
  	
    	
    	if (containerSel){
    		
    		coordinatesValuesContainer = new HashMap<IURNContainerRef, HashMap <String, Integer>>() ;
    		newCoordinate = findCoordinatecontainer();
        	getOldCoordinates();
        	calculateNewXYcoordinatesContainer();
    		
    	}else{
    		
    		coordinatesValues = new HashMap <String, HashMap <String, Integer>>();
    		newCoordinate = findCoordinate();
        	getOldCoordinates();
        	calculateNewCoordinates();
    	}

    	addMoveCommand();

    }

	private void addMoveCommand() {
		
		if ( containerSel ){
			// adds the move command to be executed on the Command stack after this command
	    	for(IURNContainerRef currentContainer : (List<IURNContainerRef>)sel){
				add( new SetConstraintBoundContainerRefCompoundCommand(currentContainer, coordinatesValuesContainer.get(currentContainer).get("x"), 
						coordinatesValuesContainer.get(currentContainer).get("y"), currentContainer.getWidth(),
							currentContainer.getHeight(), true));
	    		}	
			
		}else{
			// adds the move command to be executed on the Command stack after this command
	    	for(IURNNode currentNode : (List<IURNNode>)sel){
				add( new SetConstraintGrlNodeCommand(currentNode, coordinatesValues.get( ((URNmodelElement)currentNode).getId()).get("x"),
						coordinatesValues.get( ((URNmodelElement)currentNode).getId()).get("y"), true));
			}
		}
	}
    
    private int findCoordinate(){
       
    	int result = -1;
    	
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
    	if( moveType.compareTo("seg.jUCMNav.AlignTop") == 0 || moveType.compareTo("seg.jUCMNav.AlignLeft") == 0){
    		alignModelId = ((URNmodelElement)sel.get(0)).getId();
    		
    		if( verticalMove){
    			result = ((IURNNode)sel.get(0)).getY();
    		}else{
    			result = ((IURNNode)sel.get(0)).getX();
    		}
    	}else if( moveType.compareTo("seg.jUCMNav.AlignMiddle") == 0 || moveType.compareTo("seg.jUCMNav.AlignCenter") == 0){
    		if( verticalMove){
    			result = ((IURNNode)sel.get(0)).getY() + 
    					( (((IURNNode)sel.get(sel.size()-1)).getY() -
    						((IURNNode)sel.get(0)).getY() )/2);
    		}else{
    			result = ((IURNNode)sel.get(0)).getX() + 
    					( (((IURNNode)sel.get(sel.size()-1)).getX() -
    						((IURNNode)sel.get(0)).getX() )/2);
    		}
    	}else if( moveType.compareTo("seg.jUCMNav.AlignBottom") == 0 || moveType.compareTo("seg.jUCMNav.AlignRight") == 0){
    		alignModelId = ((URNmodelElement)sel.get(sel.size()-1)).getId();
    		
    		if (verticalMove)
    			result = ((IURNNode)sel.get(sel.size()-1)).getY();
    		else
    			result = ((IURNNode)sel.get(sel.size()-1)).getX();
    	}
    	
    	return result;
    }
    
    private int findCoordinatecontainer(){
        
    	int result = -1;
	
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
    	if( moveType.compareTo("seg.jUCMNav.AlignTop") == 0 || moveType.compareTo("seg.jUCMNav.AlignLeft") == 0){
    		alignModelId = ((URNmodelElement)sel.get(0)).getId();
    		
    		if (verticalMove)
    			result = selTemp.get(0).getY();
    		else
    			result = selTemp.get(0).getX();
    	}else if( moveType.compareTo("seg.jUCMNav.AlignMiddle") == 0 || moveType.compareTo("seg.jUCMNav.AlignCenter") == 0){
    		if( verticalMove){
    			result = selTemp.get(0).getY() + 
    					(( ((selTemp.get(selTemp.size()-1)).getY() + selTemp.get(0).getHeight()) -
    						(selTemp.get(0).getY())) /2);
    		}else{
    			result = selTemp.get(0).getX() + 
    					(( ((selTemp.get(selTemp.size()-1)).getX() + selTemp.get(0).getWidth()) -
    						(selTemp.get(0).getX())) /2);
    		}
    	}else if( moveType.compareTo("seg.jUCMNav.AlignBottom") == 0 || moveType.compareTo("seg.jUCMNav.AlignRight") == 0){
    		alignModelId = ((URNmodelElement)sel.get(sel.size()-1)).getId();
    		
    		if (verticalMove)
    			result = selTemp.get(selTemp.size()-1).getY();
    		else
    			result = selTemp.get(selTemp.size()-1).getX();
    	}
    	
    	return result;
    }
    
    private void calculateNewCoordinates(){
    	
    	// finds the height/width of the URNmodelElement for which the coordinate equals the newCoordinate variable
		for(URNmodelElement currentElem : (List<URNmodelElement>) sel){	
			String currentMeta;
			
			if ( verticalMove){
				currentMeta = MetadataHelper.getMetaData(currentElem, "_height");	
			}else{
				currentMeta = MetadataHelper.getMetaData(currentElem, "_width");
			}
				if ( currentElem.getId().compareTo(alignModelId) == 0){	
					if ( currentMeta != null){
						newPositionElemDimension = Integer.valueOf(currentMeta);
					}else{
						newPositionElemDimension = 0;
					}
				}
		}
		
		
		for(URNmodelElement currentElem : (List<URNmodelElement>) sel){			

				// the height/width of the URNmodelElement in this iteration
				int dimensionValue;
				String currentMeta;
				int tempNewCoordinate;
		
				if (verticalMove){
					currentMeta = MetadataHelper.getMetaData(currentElem, "_height");
				}else{
					currentMeta = MetadataHelper.getMetaData(currentElem, "_width");
				}
				if ( currentMeta != null){
					dimensionValue = Integer.valueOf(Integer.valueOf(currentMeta));
				}else{
					dimensionValue = 0;
				}
					// sets the new coordinate for each URNmodelElement differently depending on the moveType
					
					if ( currentElem.getId().compareTo(alignModelId) != 0){	
						
				    	if( moveType.compareTo("seg.jUCMNav.AlignTop") == 0 || moveType.compareTo("seg.jUCMNav.AlignLeft") == 0){
				    			coordinatesValues.get(currentElem.getId()).put(axis, newCoordinate);
				    	}else if( moveType.compareTo("seg.jUCMNav.AlignMiddle") == 0 || moveType.compareTo("seg.jUCMNav.AlignCenter") == 0){
				    			coordinatesValues.get(currentElem.getId()).put(axis, newCoordinate - dimensionValue/2);
				    	}else if( moveType.compareTo("seg.jUCMNav.AlignBottom") == 0 || moveType.compareTo("seg.jUCMNav.AlignRight") == 0){
				    			coordinatesValues.get(currentElem.getId()).put(axis, newCoordinate - (dimensionValue - newPositionElemDimension));
				    	}
					}
	
    }
    }
 
	private void calculateNewXYcoordinatesContainer(){	 
	
		
    	// finds the height/width of the URNmodelElement for which the Y coordinate equals the newY variable
		for(IURNContainerRef currentRef : (List<IURNContainerRef>) sel){	
			if( verticalMove){
					if ( ((URNmodelElement)currentRef).getId().compareTo(alignModelId) == 0){	
						newPositionElemDimension = currentRef.getHeight();
					}
			}else{
				if ( ((URNmodelElement)currentRef).getId().compareTo(alignModelId) == 0){	
					newPositionElemDimension = currentRef.getWidth();
				}
			}
		}
    	
		for(IURNContainerRef currentRef : (List<IURNContainerRef>) sel){			
			
				// the height of the URNmodelElement in this iteration
				
				int dimensionValue; 
				int tempNewCoordinate;
				
				if ( verticalMove){
					dimensionValue = currentRef.getHeight();
				}else{
					dimensionValue = currentRef.getWidth();
				}
				
				// sets the new coordinate for each URNmodelElement differently depending on the moveType
				if ( ((URNmodelElement)currentRef).getId().compareTo(alignModelId) != 0){	
						
				    	if( moveType.compareTo("seg.jUCMNav.AlignTop") == 0 || moveType.compareTo("seg.jUCMNav.AlignLeft") == 0){
				    			coordinatesValuesContainer.get(currentRef).put(axis, newCoordinate);
				    	}else if( moveType.compareTo("seg.jUCMNav.AlignMiddle") == 0 || moveType.compareTo("seg.jUCMNav.AlignCenter") == 0){
				    			coordinatesValuesContainer.get(currentRef).put(axis, newCoordinate - dimensionValue/2);
				    	}else if( moveType.compareTo("seg.jUCMNav.AlignBottom") == 0 || moveType.compareTo("seg.jUCMNav.AlignRight") == 0){
				    			coordinatesValuesContainer.get(currentRef).put(axis, newCoordinate - (dimensionValue - newPositionElemDimension));
				    	}
					}

				}
		
		
    }
}