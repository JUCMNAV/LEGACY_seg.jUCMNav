package seg.jUCMNav.actions;

import java.util.ArrayList;
import java.util.List;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.ShowEvaluationIntentionalElementCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.ShowEvaluationIntentionalElementDialog;
import urn.URNspec;
import urncore.URNmodelElement;

public class ShowEvaluationIntentionalElementAction extends URNSelectionAction
{
    public static final String SHOWEVALUATIONINTENTIONALELEMENT = "seg.jUCMNav.ShowEvaluationIntentionalElementAction"; //$NON-NLS-1$
    
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    private EvaluationStrategy strategy;
    private GRLGraph newGraph;
    
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
    {   
        List objects = getSelectedObjects();
        List<Evaluation> evals;
        List<IntentionalElement> nodesInStrategy;
        List<grl.ElementLink> links;
        List<IntentionalElement> contributorsNodesToSelectedNodes;

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
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            evals = new ArrayList<Evaluation>(strategy.getEvaluations()); // list of evaluation in the strategy
            nodesInStrategy = new ArrayList<IntentionalElement>(); // list of nodes in the strategy
            for (Evaluation e : evals) // obtaining nodes
                nodesInStrategy.add(e.getIntElement()); // adding nodes to a list
            
            links = new ArrayList<grl.ElementLink>(((IntentionalElement) element).getLinksDest());
            contributorsNodesToSelectedNodes = new ArrayList<IntentionalElement>();
            for (grl.ElementLink l : links)
                contributorsNodesToSelectedNodes.add((IntentionalElement) l.getSrc());
            
            if (nodesInStrategy.containsAll(contributorsNodesToSelectedNodes) && contributorsNodesToSelectedNodes.size() != 0) {           
                elementRef = sel.getIntentionalElementRef();
                System.out.println("Well, right!!!");
                return true;
            } else
                return false;
        } 
        else
            return false;
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() {
        //SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        //Command cmd = new CreateGrlGraphCommand(sel.getUrnspec());
        //if (sel.getUrnspec()!=null && sel.getMap()!=null)
            //((CreateGrlGraphCommand)cmd).setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getMap())+1);
        //else if (sel.getUrnspec()!=null && sel.getGrlgraph()!=null)
            //((CreateGrlGraphCommand)cmd).setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getGrlgraph())+1);
      
        //DisplayPreferences.getInstance().setShowGRLS(true);
        //cmd.execute();
        //newGraph = ((CreateGrlGraphCommand) cmd).getDiagram();
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ShowEvaluationIntentionalElementDialog dialog = new ShowEvaluationIntentionalElementDialog(shell);
        String value = dialog.open(Messages.getString("ShowEvaluationIntentionalElement.WindowEnterNew"), //$NON-NLS-1$ 
            Messages.getString("ShowEvaluationIntentionalElement.TextEnterNew"), //$NON-NLS-1$  
            ""); //$NON-NLS-1$
        
        if (value != null && value.length() > 0) {
            System.out.println("in the action's if");
            Command cmd = new ShowEvaluationIntentionalElementCommand(urnspec, element, elementRef, strategy, value);
            return cmd;
        } else
            return null;                     
    }
}
