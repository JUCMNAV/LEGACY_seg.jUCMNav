package seg.jUCMNav.model.commands.create;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;
//import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
//import seg.jUCMNav.model.commands.delete.internal.DisconnectGRLNodeCommand;
//import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urn.URNspec;
import urncore.GRLmodelElement;

public class ShowEvaluationIntentionalElementCommand extends Command implements JUCMNavCommand
{
    private URNspec urnspec;
    private IntentionalElement grlelem;
    private IntentionalElementRef grlelemRef;
    private GRLGraph grlGraph;
    private EvaluationStrategy strategy;
    private GRLGraph newGraph;
    private int value;
    
    private List<Evaluation> evals; // list of the evaluations of the strategy
    private List<IntentionalElement> nodesInStrategy; // nodes that are considered in the strategy
    private List<IntentionalElement> contributorsNodesToSelectedNodes; // list of the contributors of the selected node
    private ArrayList<EvaluationStrategy> urnspecEvaluationStrategyList; // list of all the existing strategies in the graph
    private ArrayList<IntentionalElement> desiredIntentionalElementList;
    private ArrayList<ArrayList<IntentionalElement>> positiveEvaluationIEList;
    private ArrayList<IntentionalElementRef> existingIntentionalElementRefsList;
    private ArrayList<IntentionalElement> existingIntentionalElementList;
    private ArrayList<IntentionalElementRef> addedIntentionlElementRefinNewGraph;
  
    public ShowEvaluationIntentionalElementCommand(URNspec spec, EObject obj, IntentionalElementRef objRef, EvaluationStrategy es, String v) 
    {
        urnspec = spec;
      
        if (obj instanceof GRLmodelElement) 
        {
            this.grlelem = (IntentionalElement) obj;
            this.grlelemRef = (IntentionalElementRef) objRef;
            this.grlGraph = (GRLGraph) objRef.getDiagram();
            this.strategy = es;
            //this.newGraph = g;
            this.value = Integer.parseInt(v);
            urnspecEvaluationStrategyList = new ArrayList<EvaluationStrategy>(urnspec.getGrlspec().getStrategies());
            existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
            existingIntentionalElementList = new ArrayList<IntentionalElement>();
            
            for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                existingIntentionalElementList.add(IER.getDef());
            
            if (urnspecEvaluationStrategyList.size() != 0)
                System.out.println("\n\nEvaluationSterategyList is not empty");
          
            System.out.println("In Command constructor for ShowEvaluationIntentionalElementCommand!!!");
          
            setLabel(Messages.getString("ActionRegistryManager.ShowEvaluationIntentionalElement")); //$NON-NLS-1$
        }
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean executable = false;
        List <Evaluation> esList;
        List <IntentionalElement> ieList;
        positiveEvaluationIEList = new ArrayList<ArrayList<IntentionalElement>>();
                
        for (EvaluationStrategy ES : urnspecEvaluationStrategyList)
            positiveEvaluationIEList.add(new ArrayList<IntentionalElement>());
                       
        if (urnspec != null && grlelem != null && urnspecEvaluationStrategyList.size() != 0)
        {
            /*for (int i = 0; i < urnspecEvaluationStrategyList.size(); i++)
            {
                //positiveEvaluationIEList.set(i, new ArrayList<IntentionalElement>());
                esList = new ArrayList<Evaluation>(urnspecEvaluationStrategyList.get(i).getEvaluations());
                ieList = new ArrayList<IntentionalElement>();
                              
                for (Evaluation e : esList)
                {
                    if (e.getEvaluation() > 0)
                        positiveEvaluationIEList.get(i).add(e.getIntElement());
                }
            }*/            
                        
            executable = true;
            
            System.out.println("positiveEvaluationIEList size is : " + positiveEvaluationIEList.size());            
        }
        
        //for (int i = 0; i < positiveEvaluationIEList.size(); i++)
            //for (int j = 0; j < positiveEvaluationIEList.get(i).size(); j++)
                //System.out.println("Strategy number " + i + " " + positiveEvaluationIEList.get(i).get(j));
        
        return executable;
    }
    
    /*
    **
    * @see org.eclipse.gef.commands.Command#execute()
    */
    public void execute() 
    {
        evals = new ArrayList<Evaluation>(strategy.getEvaluations()); // list of evaluation in the strategy
        nodesInStrategy = new ArrayList<IntentionalElement>(); // list of nodes in the strategy
        for (Evaluation e : evals) // obtaining nodes
            nodesInStrategy.add(e.getIntElement()); // adding nodes to a list
        
        ArrayList<grl.ElementLink>links = new ArrayList<grl.ElementLink>(((IntentionalElement) grlelem).getLinksDest());
        contributorsNodesToSelectedNodes = new ArrayList<IntentionalElement>();
        for (grl.ElementLink l : links)
            contributorsNodesToSelectedNodes.add((IntentionalElement) l.getSrc());
        
        List<IntentionalElement> temp = new ArrayList<IntentionalElement>();
        for (IntentionalElement ie : contributorsNodesToSelectedNodes) // extracting the nodes that exist in the graph
            if (existingIntentionalElementList.contains(ie))
                temp.add(ie);
                
        contributorsNodesToSelectedNodes = new ArrayList<IntentionalElement>(temp); // saving back all the connected and existed-in-graph elements
        desiredIntentionalElementList = new ArrayList<IntentionalElement>();
        for(IntentionalElement ie : contributorsNodesToSelectedNodes)
            for (Evaluation e : evals)
                if (e.getIntElement().equals(ie))
                    if (e.getEvaluation() >= value)
                        desiredIntentionalElementList.add(ie);
        System.out.println("execution is done!!! and size is : " + desiredIntentionalElementList.size());
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
        
        Command cmd;        
        IntentionalElementRef ieRef, new_ieRef;
        addedIntentionlElementRefinNewGraph = new ArrayList<IntentionalElementRef>();
        
        // creating the graph and adding the selected element at first
        cmd = new CreateGrlGraphCommand(urnspec);
        //((CreateGrlGraphCommand)cmd).setIndex(urnspec.getUrndef().getSpecDiagrams().indexOf(grlGraph) + 1);
        DisplayPreferences.getInstance().setShowGRLS(true);
        cmd.execute();
        newGraph = ((CreateGrlGraphCommand) cmd).getDiagram();        
        ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef.setDef((IntentionalElement) grlelem);
        addedIntentionlElementRefinNewGraph.add(ieRef);
        cmd = new AddIntentionalElementRefCommand(newGraph, ieRef);
        cmd.execute();
        // adding all the desired elements to the new graph
        for (IntentionalElement ie : desiredIntentionalElementList) {
            new_ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
            new_ieRef.setDef((IntentionalElement) ie);
            addedIntentionlElementRefinNewGraph.add(new_ieRef);
            cmd = new AddIntentionalElementRefCommand(newGraph, new_ieRef);
            cmd.execute();
        }
        
        for (IntentionalElementRef IER : addedIntentionlElementRefinNewGraph) {
            CreateAllLinkRefCommand comm = new CreateAllLinkRefCommand(IER);
            comm.execute();
        }       
        
        System.out.println("redo is done!!!");
        System.out.println("The size of nodes in new graph is : " + newGraph.getNodes().size());
        
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
        assert grlelemRef != null : "post no objRef to modify"; //$NON-NLS-1$        
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
        assert grlelemRef != null : "pre no objRef to modify"; //$NON-NLS-1$        
    }
    
    public void undo() 
    {
        testPostConditions();
        
        Command cmd = new DeleteGRLGraphCommand(newGraph);
        cmd.execute();
        /*for (int i = 0; i < addedIntentionalElementRefList.size(); i++)
        {
            DeleteAllLinkRefCommand comm1 = new DeleteAllLinkRefCommand(addedIntentionalElementRefList.get(i));
            comm1.execute();
        
            DisconnectGRLNodeCommand comm2 = new DisconnectGRLNodeCommand(addedIntentionalElementRefList.get(i));
            comm2.execute();
        
            RemoveURNmodelElementCommand comm3 = new RemoveURNmodelElementCommand(addedIntentionalElementRefList.get(i));
            comm3.execute();
        }     
        
        addedIntentionalElementRefList.clear();*/
        
        testPreConditions();
    }
}
