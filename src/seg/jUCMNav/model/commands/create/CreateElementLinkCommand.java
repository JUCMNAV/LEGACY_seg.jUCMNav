/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Dependency;
import grl.ElementLink;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.Reuse;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.strategies.util.ReusedElementUtil;
import urn.URNspec;

/**
 * This command create the decomposition, contribution or dependency and all the linkref in the graph where the source and destination element ref are.
 * 
 * @author Jean-François Roy
 * 
 */
public class CreateElementLinkCommand extends CompoundCommand {

	GRLspec grl;
    IntentionalElement src, dest;
    IntentionalElementRef sourceRef;
    ElementLink link;

    /**
     * 
     */
    public CreateElementLinkCommand(URNspec urn, IntentionalElement source, ElementLink link, String position) {
    	this.grl = urn.getGrlspec();
    	this.src = source;
        this.sourceRef = null;
        this.link = link;

        if (link instanceof Dependency) {
            add(new AddDependencyElementLinkCommand(urn, source, (Dependency) link, position));
        } else {
            add(new AddStandardElementLinkCommand(urn, source, link, position));
        }
        setLabel(Messages.getString("CreateElementLinkCommand.createElementLink")); //$NON-NLS-1$
    }
    
    /**
     * 
     */
    public CreateElementLinkCommand(URNspec urn, IntentionalElement source, IntentionalElementRef sourceRef, ElementLink link, String position) {
    	this.grl = urn.getGrlspec();
    	this.src = source;
    	this.sourceRef = null;
    	if (link != null && link instanceof Reuse)
        	this.sourceRef = sourceRef;
        this.link = link;

        if (link instanceof Dependency) {
            add(new AddDependencyElementLinkCommand(urn, source, (Dependency) link, position));
        } else {
            add(new AddStandardElementLinkCommand(urn, source, link, position));
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
        // if we are trying to create a Reuse link, the sourceRef must be specified
        if (link!= null && link instanceof Reuse && sourceRef == null)
        	return false;
        // Links to reused elements cannot be created
        if (grl != null && dest != null && ReusedElementUtil.isReusedElement(grl, dest))
        	return false;
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
    		if (link!= null && link instanceof Reuse && sourceRef != null) {
        		// Reuse case: cannot go through all srcRefs of src to find the corresponding srcRef, because in the reuse case src is in a different file and 
    			// srcRefs in a different file (i.e., the current file) are not tracked by src. Therefore, there is not a single srcRef of src that is on the same
    			// diagram as any destRef. Therefore, sourceRef in the current file must be used to create a LinkRef!
    			for (Iterator destiter = dest.getRefs().iterator(); destiter.hasNext();) {
					IntentionalElementRef destRef = (IntentionalElementRef) destiter.next();
					if (sourceRef.getDiagram().equals(destRef.getDiagram())) {
						add(new AddLinkRefCommand(destRef.getDiagram(), sourceRef, destRef, link));
					}
				}
    		} else {
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

}
