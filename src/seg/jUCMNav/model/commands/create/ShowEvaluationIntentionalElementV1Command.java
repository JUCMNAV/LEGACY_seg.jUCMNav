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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.ExportGRLCatalog;
import seg.jUCMNav.importexport.ImportGRLCatalog;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLGraphCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import urn.URNspec;
import urncore.GRLmodelElement;

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
    
  
    public ShowEvaluationIntentionalElementV1Command(URNspec spec, EObject obj, EvaluationStrategy es, String v, GRLGraph g) 
    {
        urnspec = spec;
      
        if (obj instanceof GRLmodelElement) 
        {
            this.grlGraph = (GRLGraph) obj;
            this.newGraph = (GRLGraph) g;
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
        
        //ExportGRLCatalog exportGRL = new ExportGRLCatalog();
        //exportGRL.export(urnspec, null, "file");        
        //ImportGRLCatalog importGRL = new ImportGRLCatalog();
        //newUrn = ModelCreationFactory.getNewURNspec(false, false);
        //try {
            //FileInputStream grlFile = new FileInputStream("file.grl");
        //} catch (FileNotFoundException e) {
            //e.printStackTrace();
        //}
        
        //importGRL.importURN(grlFile, newUrn, autolayoutDiagrams);
        
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
        
        //Display.getDefault().syncExec(new Runnable() { 
          //@Override
          //public void run() {
        
        Command cmd;        
        IntentionalElementRef ieRef, new_ieRef;
        IntentionalElement intentionalElement;
        
        // creating a new graph
        //newUrn = ModelCreationFactory.getNewURNspec(false, false);
        /*cmd = new CreateGrlGraphCommand(urnspec);
        System.out.println("No Fail is happened");
        ((CreateGrlGraphCommand)cmd).setIndex(urnspec.getUrndef().getSpecDiagrams().indexOf(grlGraph) + 1 );
        System.out.println("grlGraph index is : " + urnspec.getUrndef().getSpecDiagrams().indexOf(grlGraph) +
           " and, index of the new grapg is : " + urnspec.getUrndef().getSpecDiagrams().indexOf(grlGraph) + 1);
        if (cmd.canExecute()) {
            System.out.println("can be executed!");
            DisplayPreferences.getInstance().setShowGRLS(true);
        }
        
        System.out.println("No Fail is happened");
        newGraph = ((CreateGrlGraphCommand) cmd).getDiagram();
        cmd.execute();*/
        
        
        
        consideringNodes = new ArrayList<IntentionalElementRef>(startingNodes); // consideringNodes = startingNodes
        addedIntentionlElementRefinNewGraph = new ArrayList<IntentionalElementRef>(); // adding the very first set of element to the new graph
        for (int i = 0; i < startingNodes.size(); i++) { // first we add the refs that are in the startingNodes list
            ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, 
                startingNodes.get(i).getDef().getType().getValue(), startingNodes.get(i).getDef());
            addedIntentionlElementRefinNewGraph.add(ieRef);
            cmd = new AddIntentionalElementRefCommand(newGraph, ieRef);
            cmd.execute();
        }
        
        while (consideringNodes.size() != 0) {
            existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(newGraph.getNodes());
            existingIntentionalElementList = new ArrayList<IntentionalElement>(); // obtaining the definition of the existing refs in the new graph
            for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                existingIntentionalElementList.add(IER.getDef());
            
            linksSourceList = new ArrayList<ElementLink>(); // finding all the outgoing links from all the starting nodes
            for (int i = 0; i < consideringNodes.size(); i++) {
                intentionalElement = consideringNodes.get(i).getDef();
                linksSourceList.addAll(intentionalElement.getLinksSrc());
            }
            
            consideringNodes = new ArrayList<IntentionalElementRef>();
            for (int i = 0; i < linksSourceList.size(); i++) { // finding all the elements on the destination side of the all outgoing links
                existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(newGraph.getNodes());
                existingIntentionalElementList = new ArrayList<IntentionalElement>(); // obtaining the definition of the existing refs in the new graph
                for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                    existingIntentionalElementList.add(IER.getDef());
                
                IntentionalElement ie = (IntentionalElement) linksSourceList.get(i).getDest();
                if (existingIntentionalElementList.contains(ie)) // a ref of the element already exists in the new graph
                    continue; 
                else { // no ref of the element exists in graph
                    ArrayList<IntentionalElementRef> ieRefList = new ArrayList<IntentionalElementRef>(ie.getRefs());
                    IntentionalElementRef ref = null;
                    for (int j = 0; j < ieRefList.size(); j++) // finding the ref that in located in the current grlGraph
                        if (((GRLGraph) ieRefList.get(j).getDiagram()).equals(grlGraph)) {
                            ref = ieRefList.get(j); // keeping the found ref in the variable ref
                            break;
                        }
                    
                    consideringNodes.add(ref); // saving all the elements on the destination side of the all links into consideringNodes if not already included
                    ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class, 
                        ref.getDef().getType().getValue(), ref.getDef());
                    addedIntentionlElementRefinNewGraph.add(ieRef);
                    cmd = new AddIntentionalElementRefCommand(newGraph, ieRef);
                    cmd.execute();
                }
            }
        }
        
        for (IntentionalElementRef IER : addedIntentionlElementRefinNewGraph) {
            CreateAllLinkRefCommand comm = new CreateAllLinkRefCommand(IER);
            comm.execute();
        }
        
        //  } // end of run
        //}); 
        
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
