/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urn.URNspec;

/**
 * This command create the decomposition, contribution or dependency and all the linkref in the graph where the source and destination element ref are.
 * 
 * @author Jean-François Roy
 * 
 */
public class CreateElementLinkCommand extends CompoundCommand {

    IntentionalElement src, dest;
    ElementLink link;

    /**
     * 
     */
    public CreateElementLinkCommand(URNspec urn, IntentionalElement source, ElementLink link) {
        this.src = source;
        this.link = link;

        if (link instanceof Dependency) {
            add(new AddDependencyElementLinkCommand(urn, source, (Dependency) link));
        } else {
            add(new AddStandardElementLinkCommand(urn, source, link));
        }
        setLabel(Messages.getString("CreateElementLinkCommand.createElementLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (connectionExist()) {
            return false;
        }
        return super.canExecute();
    }

    /**
     * Verify if a connection already exist between the source and destination intentional element
     * 
     * @return true if a connection exist, false if not
     */
    private boolean connectionExist() {
        Iterator iter;
        EList list;
        if (src != null && dest != null && !src.equals(dest)) {
            list = src.getLinksSrc();
            for (iter = list.iterator(); iter.hasNext();) {
                ElementLink cont = (ElementLink) iter.next();
                if (cont.getDest() == dest) {
                    return true;
                }
            }
            list = src.getLinksDest();
            for (iter = list.iterator(); iter.hasNext();) {
                ElementLink cont = (ElementLink) iter.next();
                if (cont.getSrc() == dest) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * When ready to execute the command, add the AddLinkRefCommand to the CompoundCommand
     */
    public void execute() {
        addLinkRefCommand();
        super.execute();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.dest = target;
        for (Iterator iter = getCommands().iterator(); iter.hasNext();) {
            Command cmd = (Command) iter.next();
            if (cmd instanceof AddStandardElementLinkCommand) {
                ((AddStandardElementLinkCommand) cmd).setTarget(target);
            } else if (cmd instanceof AddDependencyElementLinkCommand) {
                ((AddDependencyElementLinkCommand) cmd).setTarget(target);
            }
        }
    }

    /*
     * Find the pair of intentionalElementRef in the same GRLGraph corresponding to the source and destination intentional element and add command to add
     * LinkRef
     */
    private void addLinkRefCommand() {

        if (canExecute()) {
            for (Iterator iter = src.getRefs().iterator(); iter.hasNext();) {
                IntentionalElementRef srcRef = (IntentionalElementRef) iter.next();
                for (Iterator destiter = dest.getRefs().iterator(); destiter.hasNext();) {
                    IntentionalElementRef destRef = (IntentionalElementRef) destiter.next();
                    if (srcRef.getDiagram().equals(destRef.getDiagram())) {
                        add(new AddLinkRefCommand(destRef.getDiagram(), srcRef, destRef, link));
                    }
                }
            }
        }
    }

}
