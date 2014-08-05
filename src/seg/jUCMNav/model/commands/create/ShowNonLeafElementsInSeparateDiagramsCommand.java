/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.GRLGraph;
import grl.IntentionalElementRef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Collections;
import java.util.Comparator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.Metadata;

/**
 * Creates a new GRL diagram for all non lead intentional elements in the present GRL diagram.
 * 
 * @author pboul037
 *
 */
public class ShowNonLeafElementsInSeparateDiagramsCommand extends Command implements JUCMNavCommand, IGlobalStackCommand
{
    private URNspec urnspec;
    private GRLGraph grlGraph; 
    private CommandStack cmdStack;
    private List<IntentionalElementRef> currentIntentionalElementRefList; // List of the refs of IntentionalElements in the grl Graph
    private Stack<IntentionalElementRef> leafElementRefStack; // List of the refs of IntentionalElements in the grl Graph
    private UCMNavMultiPageEditor editor;
    private Stack<ShowLinkedElementInNewDiagramCommand> secondaryCmdStack; // contains the a command stack for this command
    private HashMap<IntentionalElementRef, String> newDiagramNames; // name of the diagram that will be generated ( if the case ) for this intentional element ref
  
    
    public ShowNonLeafElementsInSeparateDiagramsCommand(URNspec urnspec, GRLGraph grlGraph, UCMNavMultiPageEditor editor, CommandStack cmdStack) 
    {
      
        this.urnspec = urnspec;
        this.grlGraph = grlGraph;            
        this.editor = editor;
        this.cmdStack = cmdStack;
        secondaryCmdStack = new Stack<ShowLinkedElementInNewDiagramCommand>();
        currentIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());                                 

        
        setLabel(Messages.getString("ActionRegistryManager.ShowNonLeafElementsInSeparateDiagrams")); //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean exacutable = false;
        
        if ( urnspec != null  ) {
           exacutable = true;
        }
        
        return exacutable;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
    	
    	newDiagramNames = new HashMap<IntentionalElementRef, String>();
    	leafElementRefStack = new Stack<IntentionalElementRef>();
    	
    	// makes a list of the IntentionalElementRef that are leaves
    	for( IntentionalElementRef currentRef : currentIntentionalElementRefList){		
    			if( currentRef.getPred().size() == 0 ||
    					(currentRef.getDef().getType().getName().compareTo("Ressource") == 0 ) ){
    				leafElementRefStack.push(currentRef);
    			}
    		}
    	
    	// remove the leaf elements from <b> currentIntentionalElementRefList </b>
    	while( !leafElementRefStack.isEmpty()){
    		currentIntentionalElementRefList.remove(leafElementRefStack.pop());
    	}
    	
        // Identifies the new GRL digram
    	String objName = "";
    	
        for( IntentionalElementRef currentRef : currentIntentionalElementRefList){
	        for ( Object currentObj : currentRef.getMetadata()){	
	        	Metadata currentMeta = (Metadata) currentObj;
	
	        	if( currentMeta.getName().compareTo("LegislationSection") == 0){
	        		objName = currentMeta.getValue();
	        	}
	        }
	
	        // if there's is no LegislationSection
	        if( objName.isEmpty()){
	        	for ( Object currentObj : currentRef.getMetadata()){
	        		Metadata currentMeta = (Metadata) currentObj;
	        	
	        		if( currentMeta.getName().compareTo("AltName") == 0){
	        			objName = currentMeta.getValue();
	        		}
	        	}
	        	if( objName.isEmpty()){
        			objName = ((IntentionalElementRef)currentRef).getDef().getName();
	        	}
	        }
        newDiagramNames.put(currentRef, objName);
        
        }
        
        // sorts the diagrams that will be created alphabetically with the names attributed above
    	Collections.sort(currentIntentionalElementRefList, new Comparator<IntentionalElementRef>() {
    	    public int compare(IntentionalElementRef ref1, IntentionalElementRef ref2) {
    	        return newDiagramNames.get(ref1).compareTo(newDiagramNames.get(ref2));
    	    }
    	});
        
    	
        redo();   
    } 
    
    public void redo() 
    {
        testPreConditions();

    	for( IntentionalElementRef currentRef : currentIntentionalElementRefList){
    				ShowLinkedElementInNewDiagramCommand cmd = new ShowLinkedElementInNewDiagramCommand(urnspec, currentRef.getDef(), currentRef, editor, cmdStack);
    				if(cmd.canExecute()){
    					cmd.execute();
    					secondaryCmdStack.push(cmd);
    				}
    		}
    	
        
        testPostConditions();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() 
    {
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
    }
  
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() 
    {
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() 
    {
        testPostConditions();
        
        while(!secondaryCmdStack.isEmpty()){
        	if(secondaryCmdStack.peek().canUndo()){
        		secondaryCmdStack.peek().undo();
        	}
        	secondaryCmdStack.pop();
        }
        
        testPreConditions();
    }

	@Override
	public IURNDiagram getAffectedDiagram() {
		return (IURNDiagram)grlGraph;
	}
}
