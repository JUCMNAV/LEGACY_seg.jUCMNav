/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.internal.DisconnectGRLNodeCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNDiagram;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import java.util.*;

/**
 * @author Rouzbahan
 *
 */
public class ShowLinkedElementAlternativeSubNodesCommand extends Command implements JUCMNavCommand {
    private URNspec urnspec;
    private GRLmodelElement grlelem;
    private GRLGraph grlGraph;
    private IntentionalElementRef objRef;
    private IURNDiagram diagramOfElement;
    private int level;
    
    private IntentionalElement chosenIntentionalElement; // Selected IntentionalElement        
    private List<IntentionalElement> IntentionalElementList; // List of the IntentionalElements in the grl Graph    
    private List<IntentionalElementRef> currentIntentionalElementRefList; // List of the refs of IntentionalElements in the grl Graph
    private List<IntentionalElementRef> addedIntentionalElementRefList; // List of the added IntentionalElements refs into the grlGraph
    private List<IntentionalElementRef> initialIntentionalElementRefList;
    private List<IntentionalElementRef> allAddedIntentionalElementRefList;
    private List<IntentionalElement> allAddedIntentionalElementList;
    private List<IntentionalElement> allLogicallyConnectedElementList;    
    private List<GRLLinkableElement> srcGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the source for it
    private List<GRLLinkableElement> destGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the destination for it
    private List<IntentionalElement> consideredIntentionalElementList;    
    private ArrayList<IntentionalElement> LogicallyConnectedElementList;
    private ArrayList<IntentionalElement> inGraphLogicalElementList;
    private ArrayList<IntentionalElement> missingConnectedIntentionalElementList;    
    //private List<ElementLink> linksSourceList;
    private List<ElementLink> linksDestinationList; 
    
    public ShowLinkedElementAlternativeSubNodesCommand(URNspec spec, EObject obj, IntentionalElementRef ref, String numOfLevel) {
        int counter = 0;      
        urnspec = spec;
        objRef = ref;
        diagramOfElement = objRef.getDiagram();
        grlGraph = (GRLGraph) objRef.getDiagram();
        level = Integer.parseInt(numOfLevel);                
        if (obj instanceof GRLLinkableElement) {
            if (obj instanceof IntentionalElement) {  
                grlelem = (GRLLinkableElement) obj;
                IntentionalElementList = new ArrayList<IntentionalElement>(urnspec.getGrlspec().getIntElements());
                currentIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());                
                for (IntentionalElement IE : IntentionalElementList)
                    if (IE.equals(grlelem))
                        chosenIntentionalElement = (IntentionalElement) IE;                             
            }                                                              
        }
        
        setLabel(Messages.getString("ActionRegistryManager.ShowLinkedElement")); //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        boolean exacutable = false;        
        if ( urnspec != null && grlelem != null && chosenIntentionalElement != null )
            exacutable = true;
        
        return exacutable;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();   
    } 
    
    public void redo() {
        testPreConditions();
        
        int size, previousNumberOfNodes, newNumberOfNodes = 1;
        boolean Done = false;
        IntentionalElementRef ieRef;
        initialIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
        
        srcGRLLinkableElementList = new ArrayList<GRLLinkableElement>();
        destGRLLinkableElementList = new ArrayList<GRLLinkableElement>();
        LogicallyConnectedElementList = new ArrayList<IntentionalElement>();
        allLogicallyConnectedElementList = new ArrayList<IntentionalElement>();
        consideredIntentionalElementList = new ArrayList<IntentionalElement>();
        consideredIntentionalElementList.add(chosenIntentionalElement);
        allAddedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
        allAddedIntentionalElementList = new ArrayList<IntentionalElement>();
        
        for (int i = 1; i <= level; i++) {
            size = consideredIntentionalElementList.size();
            previousNumberOfNodes = grlGraph.getNodes().size();
            for (int j = 0; j < size; j++) {
                //linksSourceList = new ArrayList<ElementLink>(consideredIntentionalElementList.get(j).getLinksSrc());
                linksDestinationList = new ArrayList<ElementLink>(consideredIntentionalElementList.get(j).getLinksDest());
                
                LogicallyConnectedElementList = new ArrayList<IntentionalElement>();
                //for (ElementLink EL : linksSourceList)
                    //LogicallyConnectedElementList.add((IntentionalElement)EL.getDest());                      
                for (ElementLink EL : linksDestinationList)
                    LogicallyConnectedElementList.add((IntentionalElement)EL.getSrc());
                
                //LogicallyConnectedElementList.remove(chosenIntentionalElement);                
                LogicallyConnectedElementList.remove(consideredIntentionalElementList.get(j));
                currentIntentionalElementRefList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes()); // Finding all refs in the graph at the begining of any redo                
                inGraphLogicalElementList = new ArrayList<IntentionalElement>(); // Finding the definition of all the refs in the graph
                for (IntentionalElementRef IER : currentIntentionalElementRefList)
                    inGraphLogicalElementList.add(IER.getDef());          
                
                inGraphLogicalElementList.remove(chosenIntentionalElement);// Removing the selected node from the inGraphLogicalElementList
                missingConnectedIntentionalElementList = new ArrayList<IntentionalElement>(); // Finding and adding missing connected Intentional Elements to the list below
                for (IntentionalElement IE : LogicallyConnectedElementList)
                    if (!inGraphLogicalElementList.contains(IE))
                        missingConnectedIntentionalElementList.add(IE);
                
                //LogicallyConnectedElementList.remove(consideredIntentionalElementList.get(j));
                
                // Creating refs for the missingConnectedInentionalElementList and adding them to graph and also to the list of addedIntentionalElementRefList
                addedIntentionalElementRefList = new ArrayList<IntentionalElementRef>();
                for (IntentionalElement IE : missingConnectedIntentionalElementList) {
                    ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
                    ieRef.setDef( (IntentionalElement) IE);
                    addedIntentionalElementRefList.add(ieRef);
                    AddIntentionalElementRefCommand cmd = new AddIntentionalElementRefCommand(grlGraph, ieRef);
                    cmd.execute();
                }                
                
                for (IntentionalElementRef IER : addedIntentionalElementRefList) { // Adding all the missing links between all the nodes and all the added nodes in the graph
                    CreateAllLinkRefCommand cmd = new CreateAllLinkRefCommand(IER);
                    cmd.execute();
                }
                
                allLogicallyConnectedElementList.addAll(LogicallyConnectedElementList);
                allAddedIntentionalElementRefList.addAll(addedIntentionalElementRefList);
                for (IntentionalElementRef IER : allAddedIntentionalElementRefList)
                    allAddedIntentionalElementList.add(IER.getDef());                
            } // end of consideredINtentionalElement for
            
            newNumberOfNodes = grlGraph.getNodes().size(); // If no other nodes left to add to graph
            if (newNumberOfNodes == previousNumberOfNodes)
                if (allLogicallyConnectedElementList.size() == 0)
                    break;
            
            consideredIntentionalElementList.clear();
            consideredIntentionalElementList.addAll(allLogicallyConnectedElementList);
            System.out.println(consideredIntentionalElementList.size());
            allLogicallyConnectedElementList.clear();
            allAddedIntentionalElementRefList.clear();
            allAddedIntentionalElementList.clear();            
        } // end of levels' for
        
        testPostConditions();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert grlelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
        assert objRef != null : "post no objRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "post no diagram to modify"; //$NON-NLS-1$
        assert chosenIntentionalElement != null : "post no Intentional Element to modify"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert grlelem != null : "pre no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$
        assert objRef != null : "pre no objRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "pre no diagram to modify"; //$NON-NLS-1$
        assert chosenIntentionalElement != null : "pre no Intentional Element to modify"; //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        List<IntentionalElementRef> listOfIntentionalElementRefs = new ArrayList<IntentionalElementRef>(objRef.getDiagram().getNodes());
        listOfIntentionalElementRefs.removeAll(initialIntentionalElementRefList);        
        for (int i = 0; i < listOfIntentionalElementRefs.size(); i++){
            DeleteAllLinkRefCommand comm1 = new DeleteAllLinkRefCommand(listOfIntentionalElementRefs.get(i));
            comm1.execute();        
            DisconnectGRLNodeCommand comm2 = new DisconnectGRLNodeCommand(listOfIntentionalElementRefs.get(i));
            comm2.execute();        
            RemoveURNmodelElementCommand comm3 = new RemoveURNmodelElementCommand(listOfIntentionalElementRefs.get(i));
            comm3.execute();
        }
        
        testPreConditions();
    }
}
