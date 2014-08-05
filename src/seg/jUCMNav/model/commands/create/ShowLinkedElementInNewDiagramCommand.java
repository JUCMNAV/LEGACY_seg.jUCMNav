package seg.jUCMNav.model.commands.create;

import java.lang.reflect.InvocationTargetException;

import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.DelegatingCommandStack;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.importexport.ExportLayoutDOT;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.wizards.AutoLayoutWizard;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNDiagram;
import urncore.Metadata;


/**
 * Shows the linked element(s)(level 1) associated with a URN model element in a new GRL diagram.
 * 
 * @author pboul037
 */

public class ShowLinkedElementInNewDiagramCommand extends Command implements JUCMNavCommand, IGlobalStackCommand
{
    private URNspec urnspec;
    private GRLmodelElement grlelem;
    private GRLGraph grlGraph;
    private GRLGraph newGraph;
    private IntentionalElementRef objRef;
    private EObject eObj;
    private IntentionalElement elemRef;
    private IURNDiagram diagramOfElement;
    private boolean redoTime = false;
    private CommandStack cmdStack;
    private IntentionalElementRef newRef;
    private UCMNavMultiPageEditor editor;
    private CreateGrlGraphCommand graphCmd;
    
    
    private boolean refreshTabsBypass; // true if we want to repeat the command a few times and refresh the tabs of the editor only after the last command
    

    public ShowLinkedElementInNewDiagramCommand(URNspec spec, EObject obj, IntentionalElementRef ref, UCMNavMultiPageEditor editor, CommandStack cmdStack) 
    {
    	
      
        this.editor = editor;
        urnspec = spec;
        eObj = obj;
        objRef = ref;
        elemRef = ref.getDef();
        diagramOfElement = objRef.getDiagram();
        grlGraph = (GRLGraph) objRef.getDiagram();
        this.refreshTabsBypass = refreshTabsBypass;
        this.cmdStack = cmdStack;
        
        if (obj instanceof GRLLinkableElement) 
        {
            if (obj instanceof IntentionalElement)
                grlelem = (GRLLinkableElement) obj;                                                         
        }
        
        setLabel(Messages.getString("ActionRegistryManager.ShowLinkedElementInNewDiagram")); //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        if ( urnspec != null && grlelem != null )
            return true;
        else
        	return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute(){ 

    	// Create a new GRLGraph in the urnspec
        graphCmd = new CreateGrlGraphCommand(urnspec);
        
        if (graphCmd.canExecute()) {
            DisplayPreferences.getInstance().setShowGRLS(true);
        }
        
        if (graphCmd.canExecute()) {
        	
        	// Creates the new GRL diagram
            newGraph = graphCmd.getDiagram();

            // Identifies the new GRL digram
            String objName = "";
            
            for ( Object currentObj : elemRef.getMetadata()){	
            	Metadata currentMeta = (Metadata) currentObj;

            	if( currentMeta.getName().compareTo("LegislationSection") == 0){
            		objName = currentMeta.getValue();
            	}
            }

            // if there's is no LegislationSection
            if( objName.isEmpty()){
            	for ( Object currentObj : elemRef.getMetadata()){
            		Metadata currentMeta = (Metadata) currentObj;
            	
            		if( currentMeta.getName().compareTo("AltName") == 0){
            			objName = currentMeta.getValue();
            		}
            	}
            	if( objName.isEmpty()){
            			objName = elemRef.getName();
            	}
            }
            
            newRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
            newRef.setDef(elemRef);
            
            newGraph.setName(objName); //$NON-NLS-1$
           
            cmdStack.execute(graphCmd);

            }

        redo();
        
           
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() 
    {
     
    	testPreConditions();
       
    	if(redoTime){
    		if(graphCmd.canExecute())
    			cmdStack.execute(graphCmd);
    	}
    	
    	redoTime = true;
    	
        
        AddIntentionalElementRefCommand elementCmd = new AddIntentionalElementRefCommand(newGraph, newRef);
        if (elementCmd.canExecute()) 
            elementCmd.execute();
        
        ShowLinkedElementCommand oneLevelCmd = new ShowLinkedElementCommand(urnspec, eObj, newRef);
        if ( oneLevelCmd.canExecute()){
        	oneLevelCmd.execute();
        }
        
        CompoundCommand autoLayoutCmd = null;
        // performs Autolayout on the diagram
        try{
        	autoLayoutCmd = doAutolayout();
        }catch (InvocationTargetException e){
        	e.printStackTrace();
        }
        autoLayoutCmd.execute();
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() 
    {
        assert grlelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
        assert objRef != null : "post no objRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "post no diagram to modify"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() 
    {
        assert grlelem != null : "pre no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$
        assert objRef != null : "pre no objRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "pre no diagram to modify"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() 
    {
        testPostConditions();
        cmdStack.undo();
        testPreConditions();
    }

	public IURNDiagram getAffectedDiagram() {
		return (IURNDiagram) newGraph;
	}  
	
	/*
	 * Performs Autolayout on the newly created diagram
	 */
	private CompoundCommand doAutolayout() throws InvocationTargetException{
		
		UrnEditor urneditor = (UrnEditor) editor.getEditor(editor.getActivePage());
		AutoLayoutWizard wizard = new AutoLayoutWizard(urneditor, (IURNDiagram) newGraph);
 
		wizard.addIntentionalElemRefDimensions();
	    String initial = ExportLayoutDOT.convertURNToDot((IURNDiagram) newGraph);
	    String positioned = wizard.autoLayoutDotString(initial);
	    
	    CompoundCommand cmdResult = null;
	    
	    try{
	    	cmdResult = AutoLayoutWizard.repositionLayout(newGraph, positioned);
	    }catch (Exception e){
	    	e.printStackTrace();
	    }
	    
	    return cmdResult;
	}

}
