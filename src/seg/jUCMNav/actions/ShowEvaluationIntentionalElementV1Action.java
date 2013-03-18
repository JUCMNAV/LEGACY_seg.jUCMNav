/**
 * 
 */
package seg.jUCMNav.actions;

//import grl.Evaluation;
import java.util.List;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
//import grl.IntentionalElement;
import grl.IntentionalElementRef;

//import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.commands.create.ShowEvaluationIntentionalElementV1Command;
//import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.DisplayPreferences;
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
        List objects = getSelectedObjects();
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
            //System.out.println("element in action is : " + element );
            return true;
        } else 
            return false;              
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() {
        Command cmd_temp;
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ShowEvaluationIntentionalElementDialog dialog = new ShowEvaluationIntentionalElementDialog(shell);
        String value = dialog.open(Messages.getString("ShowEvaluationIntentionalElement.WindowEnterNew"), //$NON-NLS-1$ 
            Messages.getString("ShowEvaluationIntentionalElement.TextEnterNew"), //$NON-NLS-1$  
            ""); //$NON-NLS-1$        
       if (value != null && value.length() > 0) {
            System.out.println("in the action's if");
            // a test
            Display.getDefault().syncExec(new Runnable() { 
                @Override
                public void run() {
            
            Command cmd = new CreateGrlGraphCommand(urnspec);
            System.out.println("No Fail is happened");
            ((CreateGrlGraphCommand)cmd).setIndex(urnspec.getUrndef().getSpecDiagrams().indexOf(element) + 1 );
            System.out.println("grlGraph index is : " + urnspec.getUrndef().getSpecDiagrams().indexOf(element) +
               " and, index of the new grapg is : " + urnspec.getUrndef().getSpecDiagrams().indexOf(element) + 1);
            if (cmd.canExecute()) {
                System.out.println("can be executed!");
                DisplayPreferences.getInstance().setShowGRLS(true);
            }
            
            System.out.println("No Fail is happened");
            newGraph = ((CreateGrlGraphCommand) cmd).getDiagram();
            cmd.execute();
            
               } // end of run
            });
            // end of a test
            cmd_temp = new ShowEvaluationIntentionalElementV1Command(urnspec, element, strategy, value, newGraph);
            return cmd_temp;
        } else
            return null;          
    }
}
