package seg.jUCMNav.actions;

import java.util.List;

import grl.EvaluationStrategy;
import grl.IntentionalElementRef;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.ShowBestEvaluationIntentionalElementCommand;
//import seg.jUCMNav.model.commands.create.ShowEvaluationIntentionalElementLevelOneCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;
import urncore.URNmodelElement;

public class ShowEvaluationIntentionalElementAction extends URNSelectionAction
{
    public static final String SHOWEVALUATIONINTENTIONALELEMENT = "seg.jUCMNav.ShowEvaluationIntentionalElementAction"; //$NON-NLS-1$
    
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    private EvaluationStrategy strategy;
  
    public ShowEvaluationIntentionalElementAction(IWorkbenchPart part)
    {
        super(part);
    
        setId(SHOWEVALUATIONINTENTIONALELEMENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    protected boolean calculateEnabled() 
    {   /*
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;
        
        UCMNavMultiPageEditor editor = getEditor();
        EditPartViewer viewer = editor.getCurrentPage().getGraphicalViewer();
                
        if ( editor.getModel().getUrndef().getUrnspec().getGrlspec().getStrategies().isEmpty() )
            return false;
        
        strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
        
        if (strategy == null)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        element = HyperlinkUtils.findURNmodelElement(sel);
        
        //System.out.println("element is in new command : "+element);
        
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF)
        {
            elementRef = sel.getIntentionalElementRef(); 
            return true;
        } 
        else*/
            return false;
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() 
    {
        return new ShowBestEvaluationIntentionalElementCommand(urnspec, element, elementRef, strategy);            
    }
}
