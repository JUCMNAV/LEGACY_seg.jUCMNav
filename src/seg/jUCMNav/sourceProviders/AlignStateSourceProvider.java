package seg.jUCMNav.sourceProviders;

import grl.ActorRef;
import grl.IntentionalElementRef;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import seg.jUCMNav.model.util.MetadataHelper;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.IURNNode;
import urncore.URNmodelElement;

/*
 * Default Handler for seg.jUCMNav.AlignCommand.
 * 
 * @author Patrice Boulet
 */

public class AlignStateSourceProvider extends AbstractSourceProvider {
	
	public final static String SELECTION_STATE = "seg.jUCMNav.selectionState";
	public final static String ENOUGH_SELECTED = "seg.jUCMNav.enoughSelected";	
	public final static String SELECTION_TYPE = "seg.jUCMNav.AlignSelectionType";
	
	private final static String SEL_VALID = "valid"; 
    private final static String SEL_NOTVALID = "notValid"; 
    private final static String NOT_ENOUGH_SELECTED = "notEnough";
    private final static String ENOUGH_SELECTED_2 = "greaterThan1";
    private final static String ENOUGH_SELECTED_3 = "greaterThan2";
	
	 	private static final String SELTYPE_INTENTIONALELEMENT = "1";
	 	private static final String SELTYPE_ACTOR = "2";
		private static final String SELTYPE_COMPONENT = "3";
		private static final String SELTYPE_PATHNODE = "4";
		    
		private URNmodelElement element;
	    private IntentionalElementRef elementRef;
	    private URNspec urnspec;
	    private String selType;
	    private String alignType;
	    private List wholeSelection;
	    
	    private List<IntentionalElementRef> intElemList;
	    private List<ActorRef> actorList;
	    private List<ComponentRef> componentList;
	    private List<PathNode> pathNodeList;
	    private List<String> notEmptyTypes;
	    private int num;
	
	    boolean isSelectionValid;
	
	public AlignStateSourceProvider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Map <String, String> getCurrentState() {
		
		Map<String, String> currentState = new HashMap<String, String>(1);
		String state = isSelectionValid?SEL_VALID:SEL_NOTVALID;
		currentState.put(SELECTION_STATE, state);
		
		if( selType != null){
			String typeState = selType;
			currentState.put(SELECTION_TYPE, typeState);
		}
		
		

		return currentState;
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] {SELECTION_STATE, SELECTION_TYPE};
	}

	/*
	 * Sets the SELECTION_STATE to valid or not valid 
	 * if there has been a changed in the state.
	 * 
	 * @param isSelectionValid
	 * 		true if the current selection is valid and false otherwise
	 */
	
	public void setSelectionValid( boolean isSelectionValid){
		
		if( this.isSelectionValid == isSelectionValid){
			return; // no change
			}
		this.isSelectionValid = isSelectionValid;
		String state = isSelectionValid?SEL_VALID:SEL_NOTVALID;
		fireSourceChanged(ISources.WORKBENCH, SELECTION_STATE, state);
	}
	
	/*
	 * Sets the ENOUGH_SELECTED to the right string
	 * if there has been a changed in the state.
	 * 
	 * @param num
	 * 		0 if less than 1 elem selected, 1 if more than 1 elem selected
	 * and 2 if more than 2 elem selected
	 */
	
	public void setEnoughSelected(int num){
		
		String state = null;
		
		if( num == this.num){
			return; // no change
		}else if ( num == 0){
			state = NOT_ENOUGH_SELECTED;
		}else if( num == 1){
			state = ENOUGH_SELECTED_2;
		}else if ( num  == 2){
			state = ENOUGH_SELECTED_3;
		}
		
		this.num = num;
		fireSourceChanged(ISources.WORKBENCH, ENOUGH_SELECTED, state);
	}
	
	/*
	 * Sets the SELECTION_TYPE to the right type 
	 * if there has been a changed in the state.
	 * 
	 * @param selType
	 * 		either 1, 2, 3 or 4 (see line 45 for def)
	 */
	
	public void setSelectionType( String selType ){
		
		fireSourceChanged(ISources.WORKBENCH, SELECTION_TYPE, selType);
	}

	/*
	 * Evaluates if the selection is valid.
	 * 
	 * Criterias:
	 * 		1.  At least two objects must be selected
	 * 		2.  Only 1 type of IntentionalElement, Actor, Component
	 * 			or PathNode must be selected.
	 */
	
	public boolean isSelectionValid(){
		
		intElemList = new LinkedList<IntentionalElementRef>();
        actorList = new LinkedList<ActorRef>();
        componentList = new LinkedList<ComponentRef>();
        pathNodeList = new LinkedList<PathNode>();
        notEmptyTypes = new LinkedList<String>();
        boolean result = false;
        
        try{

        List objects = ((IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).toList();


        
        for( Object obj :  objects){
        	
        	if ( obj instanceof IntentionalElementEditPart){ 
        		IntentionalElementEditPart intElemEditPart = (IntentionalElementEditPart) obj;  
        		addDimensions(intElemEditPart);
        		intElemList.add(((IntentionalElementRef)intElemEditPart.getModel()));
        		if(intElemList.size() == 1)
        			notEmptyTypes.add("intElem");
        	}else if ( obj instanceof ActorRefEditPart ){
        		ActorRefEditPart actorRefEditPart = (ActorRefEditPart) obj;
        		actorList.add((ActorRef)actorRefEditPart.getModel());
        		if(actorList.size() == 1)
        			notEmptyTypes.add("actor");
        	}else if ( obj instanceof ComponentRefEditPart ){
        		ComponentRefEditPart componentRefEditPart = (ComponentRefEditPart) obj;
        		componentList.add((ComponentRef)componentRefEditPart.getModel());
        		if(componentList.size() == 1)
        			notEmptyTypes.add("component");
        	}else if ( obj instanceof RespRefEditPart ||
        					obj instanceof PathNodeEditPart ||
        						obj instanceof StubEditPart){
        		PathNodeEditPart pathNodeEditPart = (PathNodeEditPart) obj;
        		pathNodeList.add((PathNode)pathNodeEditPart.getModel());
        		if(pathNodeList.size() == 1)
        			notEmptyTypes.add("pathNode");
        	}else {
        		// do nothing for now
        	}
        }
        
        	if ( notEmptyTypes.size() == 0 ){
        		result = false;
        	}else if ( notEmptyTypes.size() == 1 ){
        		if( notEmptyTypes.get(0).compareTo("intElem") == 0 ){
        			selType = SELTYPE_INTENTIONALELEMENT;
            		wholeSelection = intElemList;
            		result = true;
        		}else if(notEmptyTypes.get(0).compareTo("actor") == 0 ){
        			selType = SELTYPE_ACTOR;
            		wholeSelection = actorList;
            		result = true;
              	}else if(notEmptyTypes.get(0).compareTo("component") == 0 ){
        			selType = SELTYPE_COMPONENT;
            		wholeSelection = componentList;
            		result = true;
              	}else if(notEmptyTypes.get(0).compareTo("pathNode") == 0 ){
        			selType = SELTYPE_PATHNODE;
            		wholeSelection = pathNodeList;
            		result = true;
            	}else {
              		// do nothing for now
              	}
        	}
        	if (wholeSelection != null){
        		
        		if( wholeSelection.size() < 2){
        			result = false;
        			setEnoughSelected(0);
        		}
        		
        		if ( wholeSelection.size() == 2 ){
        			setEnoughSelected(1);
        		}
        		
        		if( wholeSelection.size() > 2)
        			setEnoughSelected(2);
        	}
        	
        }catch(Exception e){
        	// nothing to solve; exception only threw when running all test suite
        }
        
      	return result;
	}
	
	/*
	 * Updates the state every time the current
	 * selection has been changed.
	 */
	
	public void updateStateSelectionChanged() {
		if(isSelectionValid()){
			setSelectionValid(true);
			setSelectionType(selType);
		}else 
			setSelectionValid(false);
		}
	
	public List getFilterdSelection(){
		return wholeSelection;
	}
	
	private void addDimensions(NodeEditPart nodeEditPart) {

			
			int height = nodeEditPart.getFigure().getBounds().height;
			int width = nodeEditPart.getFigure().getBounds().width;
			
			IURNNode node = (IURNNode) nodeEditPart.getModel();
			
			MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(),
					(URNmodelElement)node, "_height", String.valueOf(height));
			MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(),
					(URNmodelElement)node, "_width", String.valueOf(width));
			}
	
	
}
