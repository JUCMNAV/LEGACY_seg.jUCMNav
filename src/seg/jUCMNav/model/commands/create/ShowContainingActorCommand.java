/**
 * 
 */
package seg.jUCMNav.model.commands.create;


import grl.Actor;
import grl.ActorRef;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import urn.URNspec;
import urncore.GRLmodelElement;
import urncore.IURNDiagram;
/**
 * @author Rouzbahan
 *
 */
public class ShowContainingActorCommand extends Command implements JUCMNavCommand
{
    private URNspec urnspec;
    private GRLmodelElement grlelem;
    private IntentionalElement chosenIntentionalElement;
    private IntentionalElementRef chosenIntentionalElementRef;
    private GRLGraph grlGraph;
    private IURNDiagram diagramOfElement;    
    
    private ArrayList<ActorRef> containingActorsList;
    
    private ArrayList<ActorRef> currentContainerList;
    private ArrayList<ActorRef> addedContainingActorList;
    private Actor currentContainingActor;    
    private ArrayList<ArrayList<ActorRef>> allNodesActorRefsList;
    private ArrayList<IntentionalElementRef> allNodesList;
    
    private ArrayList<IntentionalElementRef> chosenIntentionalElementAllRefs;
    private ArrayList<Actor> missingContainingActorList;
    private ArrayList<ActorRef> currentContainingActorRefs;
    private ArrayList<Actor> currentContainingActorDefs;
    
    private static int actorRef_Width = 70;
    private static int actorRef_Height = 70;
    private int actorRef_X, actorRef_Y;
    
    public ShowContainingActorCommand(URNspec spec, EObject obj, IntentionalElementRef ref)
    {
        urnspec = spec;      
        if (obj instanceof GRLLinkableElement) {
            this.grlelem = (GRLmodelElement) obj;
            this.chosenIntentionalElement = (IntentionalElement) obj;
            this.chosenIntentionalElementRef = (IntentionalElementRef) ref;
            this.grlGraph = (GRLGraph) ref.getDiagram();
            this.diagramOfElement = ref.getDiagram();            
        }
        
        setLabel(Messages.getString("ActionRegistryManager.ShowContainingActor")); //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean exacutable = false;
        
        if (urnspec != null && chosenIntentionalElementRef != null && chosenIntentionalElement != null && grlGraph != null) {
            exacutable = true;
        }
        
        return exacutable;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() 
    {   
        IntentionalElementRef ieRef;
        // creating list of all the other refs in GrlSpec of the selected element
        chosenIntentionalElementAllRefs = new ArrayList<IntentionalElementRef>(); 
        for (int i = 0; i < chosenIntentionalElement.getRefs().size(); i++) {
            if (!chosenIntentionalElement.getRefs().get(i).equals(chosenIntentionalElementRef))
                chosenIntentionalElementAllRefs.add( (IntentionalElementRef) chosenIntentionalElement.getRefs().get(i));
        }
        
        /*currentContainingActor = (ActorRef) chosenIntentionalElementRef.getContRef();
        containingActorsList = new ArrayList<ActorRef>();
        for (int i = 0; i < chosenIntentionalElement.getRefs().size(); i++) {
            ieRef = (IntentionalElementRef) chosenIntentionalElement.getRefs().get(i);
            if (ieRef.getContRef() != null)  
                containingActorsList.add((ActorRef) ieRef.getContRef());
        }*/
        // obtaining all the actorRefs related to each node in the graph. they are being kept in the same order as the order of the nodes in the allNodesList list.
        // they are being kept in allNodesActorRefsList list.
        allNodesList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
        allNodesActorRefsList = new ArrayList<ArrayList<ActorRef>>();
        //System.out.println("the size of the allNodesList is : "+allNodesList.size());
        for (int i = 0; i < allNodesList.size(); i++) {
            allNodesActorRefsList.add(i, new ArrayList<ActorRef>());
            IntentionalElement IE = ((IntentionalElementRef) allNodesList.get(i)).getDef();
            List<IntentionalElementRef> tempList = IE.getRefs();
            for (int j = 0; j < tempList.size(); j++)
                if (((ActorRef) tempList.get(j).getContRef()) != null)
                    allNodesActorRefsList.get(i).add((ActorRef) tempList.get(j).getContRef());
        }
        
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
        
        int offset = 40, maxX = -1, minX = -1, maxY = -1, minY = -1;
        ActorRef aRef; 
        Command cmd;
        
        /*ArrayList <Actor> tempActorDefs = new ArrayList<Actor>(); // a list to contain the actors' definitions that are added
        addedContainingActorList = new ArrayList<ActorRef>();
        
        // obtaining current actors in the graph
        currentContainerList = new ArrayList<ActorRef>();
        for (int i = 0; i < grlGraph.getContRefs().size(); i++) {
            currentContainerList.add((ActorRef) grlGraph.getContRefs().get(i));
        }
        
        // obtaining missing actorRefs
        missingContainingActorList = new ArrayList<ActorRef>(containingActorsList);
        missingContainingActorList.removeAll(currentContainerList);
        actorRef_X = 0;        
        actorRef_Y = offset;
        for (int i = 0; i < missingContainingActorList.size(); i++) {
            if (!tempActorDefs.contains((Actor) missingContainingActorList.get(i).getContDef())) {
                tempActorDefs.add((Actor) missingContainingActorList.get(i).getContDef());
                aRef = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
                aRef.setContDef(missingContainingActorList.get(i).getContDef());
                cmd = new AddContainerRefCommand(grlGraph, aRef);
                cmd.execute();
                //cmd = new SetConstraintContainerRefCommand(aRef, actorRef_X += offset, actorRef_Y, actorRef_Width, actorRef_Height);
                //cmd.execute();
                //md = new ContainerRefBindChildCommand(aRef);
                //cmd.execute();
                tempActorDefs.add((Actor) missingContainingActorList.get(i).getContDef());
                addedContainingActorList.add(aRef);
            }
        } */
        
        // finding actor refs that must be added and adding its defs
        missingContainingActorList = new ArrayList<Actor>();
        for (int i = 0; i < chosenIntentionalElementAllRefs.size(); i++) {
            if (chosenIntentionalElementAllRefs.get(i).getContRef() != null) // if a ref of chosen element has an containing actor
                if (!missingContainingActorList.contains( (Actor) chosenIntentionalElementAllRefs.get(i).getContRef().getContDef())) // if actor ref is not included already
                    missingContainingActorList.add( (Actor) chosenIntentionalElementAllRefs.get(i).getContRef().getContDef()); // actor ref is added
        }
        
        // finding existing actor refs in current diagram and adding their defs into a new list 
        currentContainingActorRefs = new ArrayList<ActorRef>(grlGraph.getContRefs());
        currentContainingActorDefs = new ArrayList<Actor>();
        for (int i = 0; i < currentContainingActorRefs.size(); i++) {
            if (!currentContainingActorDefs.contains(currentContainingActorRefs.get(i).getContDef()))
                currentContainingActorDefs.add( (Actor) currentContainingActorRefs.get(i).getContDef());
        }
        
        missingContainingActorList.removeAll(currentContainingActorDefs); // removing the redundant actor defs, so the rest can be added safely
        System.out.println(missingContainingActorList.size());
        addedContainingActorList = new ArrayList<ActorRef>(); // to keep track of added actor refs
        actorRef_X = 0;        
        actorRef_Y = offset;
        for (int i = 0; i < missingContainingActorList.size(); i++) {
            aRef = (ActorRef) ModelCreationFactory.getNewObject(urnspec, ActorRef.class);
            aRef.setContDef(missingContainingActorList.get(i));
                cmd = new AddContainerRefCommand(grlGraph, aRef);
                cmd.execute();
                //cmd = new SetConstraintContainerRefCommand(aRef, actorRef_X += offset, actorRef_Y, actorRef_Width, actorRef_Height);
                //cmd.execute();
                //md = new ContainerRefBindChildCommand(aRef);
                //cmd.execute();
                addedContainingActorList.add(aRef);            
        }
        
        for (int i = 0; i < addedContainingActorList.size(); i++) {
            minX = allNodesList.get(0).getX();
            maxX = allNodesList.get(0).getX();
            minY = allNodesList.get(0).getY();
            maxY = allNodesList.get(0).getY();
            for (int j = 0; j < allNodesList.size(); j++) {
                List<Actor> tempDefActList= new ArrayList<Actor>();
                for(int k = 0; k < allNodesActorRefsList.get(j).size(); k++)
                    tempDefActList.add((Actor) ((ActorRef) allNodesActorRefsList.get(j).get(k)).getContDef());                
                if (tempDefActList.contains((Actor) addedContainingActorList.get(i).getContDef()) == true) {
                    //System.out.println("the value for j = "+ j);
                    if (maxX <= allNodesList.get(j).getX())
                        maxX = allNodesList.get(j).getX();
                    if (maxY <= allNodesList.get(j).getY())
                        maxY = allNodesList.get(j).getY();
                    if (minX >= allNodesList.get(j).getX())
                        minX = allNodesList.get(j).getX();
                    if (minY >= allNodesList.get(j).getY())
                        minY = allNodesList.get(j).getY();
                }
            }
            actorRef_Height = maxY - minY + 4*offset;
            actorRef_Width = maxX - minX + 4*offset;
            actorRef_X = minX - offset;
            actorRef_Y = minY - offset;
            cmd = new SetConstraintBoundContainerRefCompoundCommand(addedContainingActorList.get(i), actorRef_X, actorRef_Y, actorRef_Width, actorRef_Height, true);
            cmd.execute();
        }        
            
        testPostConditions();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() 
    {
        //assert grlelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
        assert chosenIntentionalElementRef != null : "post no chosenIntentionalElementRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "post no diagram to modify"; //$NON-NLS-1$
        assert chosenIntentionalElement != null : "post no Intentional Element to modify"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() 
    {
        //assert grlelem != null : "pre no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$
        assert chosenIntentionalElementRef != null : "pre no chosenIntentionalElementRef to modify"; //$NON-NLS-1$
        assert diagramOfElement != null : "pre no diagram to modify"; //$NON-NLS-1$
        assert chosenIntentionalElement != null : "pre no Intentional Element to modify"; //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() 
    {
        testPostConditions();
        
        Command cmd;
        
        for (int i = 0; i < addedContainingActorList.size(); i++) {
            cmd = new RemoveURNmodelElementCommand(addedContainingActorList.get(i));
            cmd.execute();
        }
        
        addedContainingActorList.clear();
        
        testPreConditions();
    }  
}
