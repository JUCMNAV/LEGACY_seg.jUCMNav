/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.ExportGRLCatalog;
import seg.jUCMNav.importexport.ImportGRLCatalog;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNContainer;

/**
 * @author Rouzbahan
 *
 */
public class ShowEvaluationIntentionalElementV1Command extends Command implements JUCMNavCommand
{
    private URNspec urnspec;
    private GRLGraph grlGraph;
    private EvaluationStrategy strategy;
    private int value;
    private ImportGRLCatalog IGC;
    private ExportGRLCatalog EGC;
    private URNspec newUrn;
    private GRLGraph newGraph;
    
    private List<Evaluation> evals; // list of the evaluations of the strategy
    private List<IntentionalElement> nodesInStrategy; // nodes that are considered in the strategy
    private List<IntentionalElement> contributorsNodesToSelectedNodes; // list of the contributors of the selected node
    private ArrayList<EvaluationStrategy> urnspecEvaluationStrategyList; // list of all the existing strategies in the graph
    private ArrayList<IntentionalElement> desiredIntentionalElementList;
    private ArrayList<IntentionalElementRef> existingIntentionalElementRefsList;
    private ArrayList<IntentionalElement> existingIntentionalElementList;
    private ArrayList<IntentionalElementRef> addedIntentionlElementRefinNewGraph;
    
    private List<GRLLinkableElement> srcGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the source for it
    private List<GRLLinkableElement> destGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the destination for it
    private List<IntentionalElement> leafNodes;
    private List<IntentionalElementRef> leafNodesRefs;
    private List<IntentionalElementRef> startingNodes;
    private List<IntentionalElementRef> consideringNodes;
    private ArrayList<IntentionalElement> LogicallyConnectedElementList;
    private List<ElementLink> linksSourceList;
    private List<ElementLink> linksDestinationList;
    
  
    public ShowEvaluationIntentionalElementV1Command(URNspec spec, EObject obj, EvaluationStrategy es, String v) 
    {
        urnspec = spec;
      
        if (obj instanceof GRLmodelElement) 
        {
            this.grlGraph = (GRLGraph) obj;
            this.strategy = es;
            this.value = Integer.parseInt(v);
            urnspecEvaluationStrategyList = new ArrayList<EvaluationStrategy>(urnspec.getGrlspec().getStrategies());
            existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
            existingIntentionalElementList = new ArrayList<IntentionalElement>();
            
            for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                existingIntentionalElementList.add(IER.getDef());
            
            if (urnspecEvaluationStrategyList.size() != 0)
                System.out.println("\n\nEvaluationSterategyList is not empty");
          
            System.out.println("In Command constructor for ShowEvaluationIntentionalElementV1Command!!!");
          
            setLabel(Messages.getString("ActionRegistryManager.ShowEvaluationIntentionalElement")); //$NON-NLS-1$
        }
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean executable = false;
        System.out.println("urnspecEvaluationStrategyList size is : " + urnspecEvaluationStrategyList.size());
        
        if (urnspec != null && grlGraph != null && urnspecEvaluationStrategyList.size() != 0) {
            executable = true;
            System.out.println("urnspecEvaluationStrategyList size is : " + urnspecEvaluationStrategyList.size());            
        }
        
        return executable;
    }
    
    /*
    **
    * @see org.eclipse.gef.commands.Command#execute()
    */
    public void execute() {   
        evals = new ArrayList<Evaluation>(strategy.getEvaluations()); // list of evaluation in the strategy
        System.out.println("size of evals is : " + evals.size());
        nodesInStrategy = new ArrayList<IntentionalElement>(); // list of nodes in the strategy that are qualified
        for (Evaluation e : evals) // obtaining nodes
            if (e.getEvaluation() >= value)
                nodesInStrategy.add(e.getIntElement()); // adding qualified nodes to the list
        System.out.println("size of nodesInStrategy is : " + nodesInStrategy.size());
        
        leafNodes = new ArrayList<IntentionalElement>();
        leafNodesRefs = new ArrayList<IntentionalElementRef>();
        for (IntentionalElementRef IER : existingIntentionalElementRefsList) {
            IntentionalElement ie = IER.getDef();
            if (ie.getLinksDest().size() == 0) {
                leafNodes.add(ie);
                leafNodesRefs.add(IER);
            }
        }
        System.out.println("size of leafNodes is : " + leafNodes.size());
        System.out.println("size of leafNodesRefs is : " + leafNodesRefs.size());
        
        startingNodes = new ArrayList<IntentionalElementRef>(); // finding starting nodes at the bottom of the graph for expanding
        for (IntentionalElement IE : nodesInStrategy)
            if (leafNodes.contains(IE))
                startingNodes.add(leafNodesRefs.get(leafNodes.indexOf(IE)));
        System.out.println("size of startingNodes is : " + startingNodes.size());
            
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
        IntentionalElement intentionalElement;
        int j = 0;
        
        // creating a new graph
        newUrn = ModelCreationFactory.getNewURNspec(false, false);
        cmd = new CreateGrlGraphCommand(newUrn);
        newGraph = ((CreateGrlGraphCommand) cmd).getDiagram();
        cmd.execute();
        // adding the very first set of element to the new graph
        addedIntentionlElementRefinNewGraph = new ArrayList<IntentionalElementRef>();
        for (int i = 0; i < startingNodes.size(); i++) { // first we add the refs that are in the consideringNodes list
            ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, 
                startingNodes.get(i).getDef().getType().getValue(), startingNodes.get(i).getDef());
            addedIntentionlElementRefinNewGraph.add(ieRef);
            cmd = new AddIntentionalElementRefCommand(newGraph, ieRef);
            cmd.execute();
        }
        
        // calculation of the connected element to the first set of added nodes FAIL FAIL FAIL FAIL
        consideringNodes = new ArrayList<IntentionalElementRef>();
        for (int i = 0; i < startingNodes.size(); i++) {
            intentionalElement = startingNodes.get(i).getDef();
            linksSourceList = new ArrayList<ElementLink>(intentionalElement.getLinksSrc());
        }
        while (j < 1) {
            for (int i = 0; i < consideringNodes.size(); i++) { // first we add the refs that are in the consideringNodes list
                ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, 
                    consideringNodes.get(i).getDef().getType().getValue(), consideringNodes.get(i).getDef());
                addedIntentionlElementRefinNewGraph.add(ieRef);
                cmd = new AddIntentionalElementRefCommand(newGraph, ieRef);
                cmd.execute();
            }
            j++;
            // now we calculate the connected element to the last set of added nodes
        }
        
        
        
        /*
        for (IntentionalElement ie : nodesInStrategy) {
            linksSourceList = new ArrayList<ElementLink>(ie.getLinksSrc());
            for (ElementLink EL : linksSourceList)
                LogicallyConnectedElementList.add((IntentionalElement)EL.getDest());
        }
        
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
        */
        /*
        // exporting and importing grl file
        HashMap mapDiagrams = (HashMap)((IURNContainer) grlGraph);
        IGC = new ImportGRLCatalog();
        EGC = new ExportGRLCatalog();
        newUrn = ModelCreationFactory.getNewURNspec(false, false);
        try
        {
          EGC.export(newUrn, mapDiagrams, "file.grl");
        }
        catch (InvocationTargetException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        Vector autolayoutDiagrams = null;
        try
        {
          IGC.importURN("file.grl", newUrn, autolayoutDiagrams);
        }
        catch (InvocationTargetException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        */
        System.out.println("redo is done!!!");
        System.out.println("The size of nodes in new graph is : " + newGraph.getNodes().size());
        
        testPostConditions();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
    }
  
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$       
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
