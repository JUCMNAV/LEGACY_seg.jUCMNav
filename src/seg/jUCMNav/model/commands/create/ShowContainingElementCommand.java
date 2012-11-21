package seg.jUCMNav.model.commands.create;

import grl.Actor;
import grl.ActorRef;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.internal.DisconnectGRLNodeCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urn.URNspec;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * @author Rouzbahan
 *
 */
public class ShowContainingElementCommand extends Command implements JUCMNavCommand {
    private URNspec urnspec;
    private URNmodelElement urnelem;
    private Actor grlelem;
    private ActorRef grlelemRef;
    private GRLGraph grlGraph;
    
    private List<GRLNode> currentGRLNodeList;
    private List<IntentionalElementRef> currentIntentionalElementRefList; // List of the refs of IntentionalElements in the grl Graph
    private List<IntentionalElementRef> actorTotalIERefList;
    private List<IntentionalElementRef> actorContainingIERefList;
    private List<IntentionalElement> actorContainingIEList;
    private List<IntentionalElementRef> actorMissingIERefList;
    private List<IntentionalElement> actorMissingIEList;
    private List<IntentionalElementRef> neededIERefList;    
    private List<GRLLinkableElement> srcGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the source for it
    private List<IntentionalElement> srcIEList;
    private List<GRLLinkableElement> destGRLLinkableElementList; // List of the GRLLinkable(IntentionalElement) Elements that the selected element is the destination for it
    private List<IntentionalElement> destIEList;
    private List<GRLLinkableElement> currentIntentionalElementList; // List of existing IntentionalElement in the grl Graph    
    private List<ElementLink> linksSourceList;
    private List<ElementLink> linksDestinationList;    
    private List<IntentionalElement> missingIEList;
    private List<IntentionalElementRef> addedIERefsList;    
    private final static int nodeMove = 10;
    
    public ShowContainingElementCommand(URNspec spec, EObject obj, ActorRef objRef) {
        urnspec = spec;        
        if (obj instanceof GRLLinkableElement) {
            this.urnelem = (URNmodelElement) obj;
            this.grlelem = (Actor) obj;
            this.grlelemRef = (ActorRef) objRef;
            this.grlGraph = (GRLGraph) objRef.getDiagram();             
            setLabel(Messages.getString("ActionRegistryManager.ShowContainingElement")); //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        List<ActorRef> arefsList = new ArrayList<ActorRef>(grlelem.getContRefs());
        List<IntentionalElementRef> ierefsList = new ArrayList<IntentionalElementRef>();
        List<IntentionalElement> ieList = new ArrayList<IntentionalElement>();        
        for (ActorRef AR : arefsList)
            ierefsList.addAll(AR.getNodes());        
        for (IntentionalElementRef IER : ierefsList)
            ieList.add(IER.getDef());
        
        List<IntentionalElementRef> existingIERefsList = new ArrayList<IntentionalElementRef>(grlelemRef.getNodes());
        List<IntentionalElement> existingIEList = new ArrayList<IntentionalElement>();
        for (IntentionalElementRef IER : existingIERefsList)
            existingIEList.add(IER.getDef());
        
        List <IntentionalElement> listOfIE = new ArrayList<IntentionalElement>();
        List <IntentionalElementRef> listOfIER = new ArrayList<IntentionalElementRef>();
        listOfIER = grlGraph.getNodes();
        for (IntentionalElementRef IER : listOfIER)
            listOfIE.add(IER.getDef());
        
        missingIEList = new ArrayList<IntentionalElement>();
        missingIEList.addAll(ieList);
        missingIEList.removeAll(existingIEList);
        missingIEList.removeAll(listOfIE);
        
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        int X_offset, Y_offset;
        IntentionalElementRef ieRef = null;
        List<IntentionalElement> tempAddedIEList = new ArrayList<IntentionalElement>();
        addedIERefsList = new ArrayList<IntentionalElementRef>();
        if (!missingIEList.isEmpty()) {
            for (IntentionalElement IE : missingIEList) {
                if (!tempAddedIEList.contains(IE)) {
                    tempAddedIEList.add(IE);
                    ieRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urnspec, IntentionalElementRef.class);
                    ieRef.setDef( (IntentionalElement) IE);            
                    addedIERefsList.add(ieRef);            
                    AddIntentionalElementRefCommand comm1 = new AddIntentionalElementRefCommand(grlGraph, ieRef);
                    comm1.execute();            
                    CreateAllLinkRefCommand comm2 = new CreateAllLinkRefCommand(ieRef);
                    comm2.execute();
                }
            }
                        
            grlelemRef.getNodes().addAll(addedIERefsList);
            X_offset = grlelemRef.getX();
            Y_offset = grlelemRef.getY();                                  
            for (int i = 0; i < grlelemRef.getNodes().size(); i++) {
                MoveNodeCommand comm = new MoveNodeCommand((IURNNode)grlelemRef.getNodes().get(i), X_offset += nodeMove, Y_offset += nodeMove);
                comm.execute();
            }
            
            missingIEList.clear();
        }
            
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urnelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlelem != null : "post no grlelem specification to modify!"; //$NON-NLS-1$
        assert grlelemRef != null : "post no grlelemRef specification to modify!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urnelem != null : "pre no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlelem != null : "pre no grlelem specification to modify!"; //$NON-NLS-1$
        assert grlelemRef != null : "pre no grlelemRef specification to modify!"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        missingIEList = new ArrayList<IntentionalElement>();        
        for (IntentionalElementRef IER : addedIERefsList)
            missingIEList.add(IER.getDef());
        
        for (int i = 0; i < addedIERefsList.size(); i++) {
            DeleteAllLinkRefCommand comm1 = new DeleteAllLinkRefCommand(addedIERefsList.get(i));
            comm1.execute();        
            DisconnectGRLNodeCommand comm2 = new DisconnectGRLNodeCommand(addedIERefsList.get(i));
            comm2.execute();        
            RemoveURNmodelElementCommand comm3 = new RemoveURNmodelElementCommand(addedIERefsList.get(i));
            comm3.execute();
        }
        
        testPreConditions();
    }
}
