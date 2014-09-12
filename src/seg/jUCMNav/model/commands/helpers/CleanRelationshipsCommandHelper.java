package seg.jUCMNav.model.commands.helpers;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urncore.Concern;
import grl.ActorRef;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIInformationElementRef;

public class CleanRelationshipsCommandHelper extends CompoundCommand {
    
    private EObject element;
    
    /*
    * @param ref
    *            the GRLNode to be cleaned.
    */
   public CleanRelationshipsCommandHelper(GRLNode ref) {
       this.element = ref;

   }
   
   private void build(GRLNode ref) {
       if (ref.getContRef() != null) {
           append(new ContainerRefUnbindChildCommandHelper(ref.getContRef(), ref));
       }
   }
   
   /**
    * Returns true even if no commands exist.
    */
   public boolean canExecute() {

           return true;

   }

   /**
    * Returns true even if no commands exist.
    */
   public boolean canUndo() {
       return true;
   }

   /**
    * Builds command as late as possible.
    */
   public void execute() {
       build();
       super.execute();
   }

   /**
    * redirects to the appropriate build method.
    */
   private void build() {

        if (element instanceof IntentionalElementRef)
           build((IntentionalElementRef) element);
       
   }

}
