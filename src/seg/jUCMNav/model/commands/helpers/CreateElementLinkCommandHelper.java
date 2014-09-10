package seg.jUCMNav.model.commands.helpers;

import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.Messages;
import urn.URNspec;

public class CreateElementLinkCommandHelper extends CompoundCommand {

    IntentionalElement src, dest;
    ElementLink link;

    /**
     * 
     */
    public CreateElementLinkCommandHelper(URNspec urn, IntentionalElement source, ElementLink link, String position) {
        this.src = source;
        this.link = link;

        if (link instanceof Dependency) {
            append(new AddDependencyElementLinkCommandHelper(urn, source, (Dependency) link, position));
        } else {
            append(new AddStandardElementLinkCommandHelper(urn, source, link, position));
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
        for (Iterator iter = getCommandList().iterator(); iter.hasNext();) {
            Command cmd = (Command) iter.next();
            if (cmd instanceof AddStandardElementLinkCommandHelper) {
                ((AddStandardElementLinkCommandHelper) cmd).setTarget(target);
            } else if (cmd instanceof AddDependencyElementLinkCommandHelper) {
                ((AddDependencyElementLinkCommandHelper) cmd).setTarget(target);
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
                    if( srcRef.getDiagram() != null && destRef.getDiagram() != null){
                        if (srcRef.getDiagram().equals(destRef.getDiagram())) {
                            append(new AddLinkRefCommandHelper(destRef.getDiagram(), srcRef, destRef, link));
                        }
                    }
                }
            }
        }
    }

}
