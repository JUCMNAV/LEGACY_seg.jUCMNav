/**
 * 
 */
package seg.jUCMNav.model.commands.transformations;

import grl.Belief;
import grl.BeliefLink;
import grl.GRLGraph;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddBeliefLinkCommand;
import urn.URNspec;

/**
 * This command create a belief, add a link between the belief and the intentionalElementRef and move the belief near the element
 * 
 * @author Jean-François Roy
 * 
 */
public class AddBeliefToIntentionalElementRefCommand extends CompoundCommand {

    IntentionalElementRef ref;
    URNspec urn;

    /**
     * Constructor
     */
    public AddBeliefToIntentionalElementRefCommand(IntentionalElementRef ref) {
        this.ref = ref;
        urn = ref.getDiagram().getUrndefinition().getUrnspec();
        setLabel(Messages.getString("AddBeliefToIntentionalElementRefCommand.addBelief")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    public void execute() {
        // delay until execution
        build();
        super.execute();
    }

    private void build() {
        Belief belief = (Belief) ModelCreationFactory.getNewObject(urn, Belief.class);
        BeliefLink link = (BeliefLink) ModelCreationFactory.getNewObject(urn, BeliefLink.class);

        add(new AddBeliefCommand((GRLGraph) ref.getDiagram(), belief));
        AddBeliefLinkCommand linkcmd = new AddBeliefLinkCommand((GRLGraph) ref.getDiagram(), belief, link);
        linkcmd.setTarget(ref);
        add(linkcmd);

        add(new SetConstraintCommand(belief, ref.getX() - 150, ref.getY()));
    }
}
