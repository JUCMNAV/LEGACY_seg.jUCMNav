/**
 * 
 */
package seg.jUCMNav.actions;
import grl.GRLGraph;
import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCommand;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCompleteCommand;
import seg.jUCMNav.model.commands.create.ShowNonLeafElementsInSeparateDiagramsCommand;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing linked element(s) in all level.
 * 
 * @author rouzbahan
 * 
 */
public class ShowNonLeafElementsInSeparateDiagramsAction extends URNSelectionAction
{
    public static final String SHOWNONLEAFELEMENTSINSEPARATEDIAGRAMS = "seg.jUCMNav.ShowNonLeafElementsInSeparateDiagramsAction"; //$NON-NLS-1$
    
    private URNspec urnspec;
    private GRLGraph grlGraph;
    
    public ShowNonLeafElementsInSeparateDiagramsAction(IWorkbenchPart part)
    {
        super(part);        
        setId(SHOWNONLEAFELEMENTSINSEPARATEDIAGRAMS);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    @SuppressWarnings("static-access")
    protected boolean calculateEnabled() 
    {
    	
        List objects = getSelectedObjects();
  
        if (objects.size() != 1)
            return false;
  
        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        grlGraph = sel.getGrlgraph();
 
        
        if (sel.getSelectionType() == sel.INTENTIONALELEMENTREF ||
        		sel.getSelectionType() == sel.URNSPEC ||
        			sel.getSelectionType() == sel.GRLGRAPH) {
            return true;
        } else
            return false;
    }
    
    public boolean isEnabled(){
    	return true;
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() {
        return new ShowNonLeafElementsInSeparateDiagramsCommand(urnspec, grlGraph, getEditor(), getCommandStack());
    }
}
