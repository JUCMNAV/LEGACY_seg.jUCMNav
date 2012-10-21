/**
 * 
 */
package seg.jUCMNav.actions;

//import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
//import grl.IntentionalElement;
import grl.IntentionalElementRef;

//import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.ShowEvaluationIntentionalElementV1Command;
//import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.ShowEvaluationIntentionalElementDialog;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * @author Rouzbahan
 *
 */
public class ShowEvaluationIntentionalElementV1Action extends URNSelectionAction
{
    public static final String SHOWEVALUATIONINTENTIONALELEMENT = "seg.jUCMNav.ShowEvaluationIntentionalElementV1Action"; //$NON-NLS-1$
    
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    private EvaluationStrategy strategy;
    private GRLGraph newGraph;
    
    public ShowEvaluationIntentionalElementV1Action(IWorkbenchPart part)
    {
        super(part);
    
        setId(SHOWEVALUATIONINTENTIONALELEMENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    protected boolean calculateEnabled() 
    {   
        /*List objects = getSelectedObjects();
        List<Evaluation> evals;
        List<IntentionalElement> nodesInStrategy;
        List<grl.ElementLink> links;
        List<IntentionalElement> contributorsNodesToSelectedNodes;
        
        //UCMNavMultiPageEditor editor = getEditor();
        //EditPartViewer viewer = editor.getCurrentPage().getGraphicalViewer();
        //if ( editor.getModel().getUrndef().getUrnspec().getGrlspec().getStrategies().isEmpty() )
            //return false;
        
        strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
        if (strategy == null)
            return false;
        
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        int type = sel.getSelectionType();
        if (type == SelectionHelper.GRLGRAPH || (type == SelectionHelper.URNSPEC) && (sel.getSelection().size() == 1) && sel.getUrnspec() != null) {
            urnspec = sel.getUrnspec();
            element = sel.getURNmodelElement();
            return true;
        } else */
            return false;              
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ShowEvaluationIntentionalElementDialog dialog = new ShowEvaluationIntentionalElementDialog(shell);
        String value = dialog.open(Messages.getString("ShowEvaluationIntentionalElement.WindowEnterNew"), //$NON-NLS-1$ 
            Messages.getString("ShowEvaluationIntentionalElement.TextEnterNew"), //$NON-NLS-1$  
            ""); //$NON-NLS-1$
        
        if (value != null && value.length() > 0) {
            System.out.println("in the action's if");
            Command cmd = new ShowEvaluationIntentionalElementV1Command(urnspec, element, strategy, value);
            return cmd;
        } else
            return null;                     
    }
}
