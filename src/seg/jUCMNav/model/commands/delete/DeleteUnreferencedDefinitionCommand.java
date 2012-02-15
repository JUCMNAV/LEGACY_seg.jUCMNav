/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.Actor;
import grl.IntentionalElement;
import grl.kpimodel.KPIInformationElement;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * @author jfroy
 * 
 */
public class DeleteUnreferencedDefinitionCommand extends CompoundCommand {

    private URNspec urnspec;

    public DeleteUnreferencedDefinitionCommand(URNspec spec) {
        this.urnspec = spec;
        setLabel(Messages.getString("DeleteUnreferencedDefinitionCommand_DeleteUnreferencedDefinition")); //$NON-NLS-1$

    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Build the command when execute.
     */
    private void build() {
        // Get all the unreferenced definition
        for (Iterator it = urnspec.getUrndef().getComponents().iterator(); it.hasNext();) {
            Component component = (Component) it.next();
            if (component.getContRefs().size() == 0) {
                add(new DeleteComponentCommand(component));
            }
        }

        for (Iterator it = urnspec.getUrndef().getResponsibilities().iterator(); it.hasNext();) {
            Responsibility resp = (Responsibility) it.next();
            if (resp.getRespRefs().size() == 0  && resp.getParentBindings().size()==0 /* bug 764*/ ) {
                add(new DeleteResponsibilityCommand(resp));
            }
        }

        for (Iterator it = urnspec.getGrlspec().getActors().iterator(); it.hasNext();) {
            Actor actor = (Actor) it.next();
            if (actor.getContRefs().size() == 0) {
                add(new DeleteActorCommand(actor));
            }
        }

        for (Iterator it = urnspec.getGrlspec().getIntElements().iterator(); it.hasNext();) {
            IntentionalElement element = (IntentionalElement) it.next();
            if (element.getRefs().size() == 0) {
                add(new DeleteIntentionalElementCommand(element));
            }
        }

        // KPI element
        for (Iterator it = urnspec.getGrlspec().getKpiInformationElements().iterator(); it.hasNext();) {
            KPIInformationElement element = (KPIInformationElement) it.next();
            if (element.getRefs().size() == 0) {
                add(new DeleteKPIInformationElementCommand(element));
            }
        }
    }

}
