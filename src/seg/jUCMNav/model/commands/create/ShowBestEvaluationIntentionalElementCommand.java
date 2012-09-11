package seg.jUCMNav.model.commands.create;

import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.internal.DisconnectGRLNodeCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNDiagram;
import urncore.Metadata;

public class ShowBestEvaluationIntentionalElementCommand extends Command implements JUCMNavCommand
{
    private URNspec urnspec;
    private IntentionalElement grlelem;
    private IntentionalElementRef grlelemRef;
    private GRLGraph grlGraph;
    private IURNDiagram diagramOfElement;
    private EvaluationStrategy strategy;
    private StrategiesGroup strategyGroup;
    
    private IntentionalElement chosenIntentionalElement; // Selected IntentionalElement
    private IntentionalElement desiredIntentionalElement; // the intentional element with highest numEval
    
    //private List<IntentionalElement> IntentionalElementList; // List of the IntentionalElements in the grl Graph
    
    private List<IntentionalElementRef> currentIntentionalElementRefList; // List of the refs of IntentionalElements in the grl Graph
    private List<IntentionalElementRef> addedIntentionalElementRefList; // List of the added IntentionalElements refs into the grlGraph
    
    private List<GRLLinkableElement> srcGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the source for it
    private List<GRLLinkableElement> destGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the destination for it
    
    private ArrayList<IntentionalElement> LogicallyConnectedElementList;
    private ArrayList<Metadata> MetadataOfMissingConnectedElementList;
    private ArrayList<IntentionalElement> inGraphLogicalElementList;
    private ArrayList<IntentionalElement> missingConnectedIntentionalElementList;
    private ArrayList<IntentionalElement> desiredMissingConnectedIntentionalElementList;
    private ArrayList<IntentionalElement> selectedStrategyIntentionalElementList;
    private ArrayList<IntentionalElement> desiredMissingConnectedSelectedStrategyIntentionalElementList; // Worst name ever!!!
    private ArrayList<EvaluationStrategy> strategyGroupList;
    private ArrayList<ArrayList<IntentionalElement>> selectedStrategyGroupIntentionalElementList;
    private ArrayList<IntentionalElement> maxEvaluationValueIntentionalElementList;
    List<IntentionalElement> visitedIEList; // Keeps IEs that have the biggest evaluation value in selected group
    private ArrayList<IntentionalElement> desiredMissingConnectedSelectedStrategyGroupIntentionalElementList; // Worst name ever!!!
    
    private List<ElementLink> linksSourceList;
    private List<ElementLink> linksDestinationList;
    
    private ArrayList<EvaluationStrategy> urnspecEvaluationStrategyList;
    private ArrayList<ArrayList<IntentionalElement>> positiveEvaluationIEList;
    private ArrayList<IntentionalElementRef> existingIntentionalElementRefsList;
    private ArrayList<IntentionalElement> existingIntentionalElementList;
  
    public ShowBestEvaluationIntentionalElementCommand(URNspec spec, EObject obj, IntentionalElementRef objRef, EvaluationStrategy es) 
    {
        urnspec = spec;
      
        if (obj instanceof GRLmodelElement) 
        {
            this.grlelem = (IntentionalElement) obj;
            this.grlelemRef = (IntentionalElementRef) objRef;
            this.grlGraph = (GRLGraph) objRef.getDiagram();
            this.diagramOfElement = objRef.getDiagram();
            this.strategy = es;
            this.strategyGroup = strategy.getGroup();
            //chosenIntentionalElement = grlelem;
            //IntentionalElementList = new ArrayList<IntentionalElement>(urnspec.getGrlspec().getIntElements());
            currentIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
            urnspecEvaluationStrategyList = new ArrayList<EvaluationStrategy>(urnspec.getGrlspec().getStrategies());
            existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
            existingIntentionalElementList = new ArrayList<IntentionalElement>();
            
            for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                existingIntentionalElementList.add(IER.getDef());
            
            setLabel(Messages.getString("ActionRegistryManager.ShowEvaluationIntentionalElement")); //$NON-NLS-1$
        }
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean executable = false;
        
        if (urnspec != null && grlelem != null && urnspecEvaluationStrategyList.size() != 0)
            executable = true;            
            
        return executable;
    }
    
    /*
    **
    * @see org.eclipse.gef.commands.Command#execute()
    */
    public void execute() 
    {
        boolean found = false;
        ArrayList <IntentionalElementRef> tempIEList;
        IntentionalElement tempIE;
        GRLLinkableElement tempGRLElement;
        List <Evaluation> esList;
        
        srcGRLLinkableElementList = new ArrayList<GRLLinkableElement>();
        destGRLLinkableElementList = new ArrayList<GRLLinkableElement>();
        LogicallyConnectedElementList = new ArrayList<IntentionalElement>();
                
        // Initializing ElementLink lists to find related intentional elements
        linksSourceList = new ArrayList<ElementLink>(grlelem.getLinksSrc());
        linksDestinationList = new ArrayList<ElementLink>(grlelem.getLinksDest());
        
        // Logically connected elements are being added to related list
        for (ElementLink EL : linksSourceList)
            LogicallyConnectedElementList.add((IntentionalElement)EL.getDest());
                
        for (ElementLink EL : linksDestinationList)
            LogicallyConnectedElementList.add((IntentionalElement)EL.getSrc());                
                      
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
        
        int Max_numEval = 0, maxIndex = 0;
        IntentionalElementRef ieRef;
        IntentionalElement ie;
                
        // Finding all refs in the graph at the begining of any redo
        currentIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
        
        // Finding the definition of all the refs in the graph
        inGraphLogicalElementList = new ArrayList<IntentionalElement>();
        for (IntentionalElementRef IER : currentIntentionalElementRefList)
            inGraphLogicalElementList.add(IER.getDef());
        
        // Removing the selected node from the inGraphLogicalElementList
        inGraphLogicalElementList.remove(grlelem);
        
        // Finding and adding missing connected Intentional Elements to the list below
        missingConnectedIntentionalElementList = new ArrayList<IntentionalElement>();
        for (IntentionalElement IE : LogicallyConnectedElementList)
            if (!inGraphLogicalElementList.contains(IE))
                missingConnectedIntentionalElementList.add(IE);
        
        // Gathering Information of Metadata of missingConnectedintentionalElement related to enumEval
        MetadataOfMissingConnectedElementList = new ArrayList<Metadata>();
        for (int i = 0; i < missingConnectedIntentionalElementList.size(); i++)
        {
            List<Metadata> mdata = missingConnectedIntentionalElementList.get(i).getMetadata();
            MetadataOfMissingConnectedElementList.add(mdata.get(0));
        }
        
        // Finding the element with max numVal
        Max_numEval = Integer.valueOf(MetadataOfMissingConnectedElementList.get(0).getValue());
        for (int i = 1; i < MetadataOfMissingConnectedElementList.size(); i++)
        {
            if (Integer.valueOf(MetadataOfMissingConnectedElementList.get(i).getValue()) > Max_numEval)
            {
                maxIndex = i;
                Max_numEval = Integer.valueOf(MetadataOfMissingConnectedElementList.get(i).getValue());
            }
        }
        
        // Creating ref in the graph
        addedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
        ie = missingConnectedIntentionalElementList.get(maxIndex);
        ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
        ieRef.setDef( (IntentionalElement) ie);
        addedIntentionalElementRefList.add(ieRef);
        AddIntentionalElementRefCommand cmd = new AddIntentionalElementRefCommand(grlGraph, ieRef);
        cmd.execute();
        
        // Adding all the missing links between all the nodes and all the added nodes in the graph
        for (IntentionalElementRef IER : addedIntentionalElementRefList)
        {
            CreateAllLinkRefCommand comm = new CreateAllLinkRefCommand(IER);
            comm.execute();
        }
        
        /*
        // Finding and adding the intentional elements that are connected and have a positive evaluation value in all of the EvaluationStrategy. v1.0
        desiredMissingConnectedIntentionalElementList = new ArrayList<IntentionalElement>();
        for (IntentionalElement IE : missingConnectedIntentionalElementList)
        {
            for (ArrayList<IntentionalElement> IEList : positiveEvaluationIEList)
                if (IEList.contains(IE))
                    desiredMissingConnectedIntentionalElementList.add(IE);
        }
        
        
        // Finding and adding the intentional elements that are connected and have a positive evaluation value in a specific EvaluationStrategy(strategy). This list does not have duplication. v2.0
        desiredMissingConnectedSelectedStrategyIntentionalElementList = new ArrayList<IntentionalElement>();
        for (IntentionalElement IE : missingConnectedIntentionalElementList)
        {
            for (IntentionalElement IEList : selectedStrategyIntentionalElementList)
                if (IEList.equals(IE))
                    desiredMissingConnectedSelectedStrategyIntentionalElementList.add(IE);
        }
        // Removing redundant intentional elements using a has set. v1.0
        HashSet tempHashSet = new HashSet();
        tempHashSet.addAll(desiredMissingConnectedIntentionalElementList);
        desiredMissingConnectedIntentionalElementList.clear();
        desiredMissingConnectedIntentionalElementList.addAll(tempHashSet);
        */
        /* It is de-activated since we have only one strategy selected. v1.0
        // Creating refs for the missingConnectedInentionalElementList and adding them to graph and also to the list of addedIntentionalElementRefList
        addedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
        for (IntentionalElement IE : desiredMissingConnectedIntentionalElementList)
        {
            ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
            ieRef.setDef( (IntentionalElement) IE);
            addedIntentionalElementRefList.add(ieRef);
            AddIntentionalElementRefCommand cmd = new AddIntentionalElementRefCommand(grlGraph, ieRef);
            cmd.execute();
        }
        
        // Adding all the missing links between all the nodes and all the added nodes in the graph
        for (IntentionalElementRef IER : addedIntentionalElementRefList)
        {
            CreateAllLinkRefCommand cmd = new CreateAllLinkRefCommand(IER);
            cmd.execute();
        }
        */
        /* It is de-activated since we have only one strategy group selected. v2.0
        // Creating refs for the desiredMissingConnectedSelectedStrategyInentionalElementList and adding them to graph and also to the list of addedIntentionalElementRefList
        addedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
        for (IntentionalElement IE : desiredMissingConnectedSelectedStrategyIntentionalElementList)
        {
            ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
            ieRef.setDef( (IntentionalElement) IE);
            addedIntentionalElementRefList.add(ieRef);
            AddIntentionalElementRefCommand cmd = new AddIntentionalElementRefCommand(grlGraph, ieRef);
            cmd.execute();
        }
        
        // Adding all the missing links between all the nodes and all the added nodes in the graph
        for (IntentionalElementRef IER : addedIntentionalElementRefList)
        {
            CreateAllLinkRefCommand cmd = new CreateAllLinkRefCommand(IER);
            cmd.execute();
        }
        */
        /* v3.0
        // Finding and adding the intentional elements that are connected and have a positive evaluation value in a EvaluationStrategy group. This list does not have duplication. v3.0
        desiredMissingConnectedSelectedStrategyGroupIntentionalElementList = new ArrayList<IntentionalElement>();
        for (IntentionalElement IE : missingConnectedIntentionalElementList)
        {
            for (IntentionalElement IEList : visitedIEList)
                if (IEList.equals(IE))
                    desiredMissingConnectedSelectedStrategyGroupIntentionalElementList.add(IE);
        }
        
        // Creating refs for the visitedIEList and adding them to graph and also to the list of addedIntentionalElementRefList. v3.0
        addedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
        for (IntentionalElement IE : desiredMissingConnectedSelectedStrategyGroupIntentionalElementList)
        {
            ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
            ieRef.setDef( (IntentionalElement) IE);
            addedIntentionalElementRefList.add(ieRef);
            AddIntentionalElementRefCommand cmd = new AddIntentionalElementRefCommand(grlGraph, ieRef);
            cmd.execute();
        }
        
        // Adding all the missing links between all the nodes and all the added nodes in the graph
        for (IntentionalElementRef IER : addedIntentionalElementRefList)
        {
            CreateAllLinkRefCommand cmd = new CreateAllLinkRefCommand(IER);
            cmd.execute();
        }
        */
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
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() 
    {
        testPostConditions();
        
        for (int i = 0; i < addedIntentionalElementRefList.size(); i++)
        {
            DeleteAllLinkRefCommand comm1 = new DeleteAllLinkRefCommand(addedIntentionalElementRefList.get(i));
            comm1.execute();
        
            DisconnectGRLNodeCommand comm2 = new DisconnectGRLNodeCommand(addedIntentionalElementRefList.get(i));
            comm2.execute();
        
            RemoveURNmodelElementCommand comm3 = new RemoveURNmodelElementCommand(addedIntentionalElementRefList.get(i));
            comm3.execute();
        }     
        
        addedIntentionalElementRefList.clear();
        
        testPreConditions();
    }
}
