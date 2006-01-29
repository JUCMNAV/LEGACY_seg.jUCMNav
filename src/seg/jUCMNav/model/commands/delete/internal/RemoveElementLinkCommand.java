/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.ElementLink;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * @author Jean-François Roy
 *
 */
public class RemoveElementLinkCommand extends Command implements JUCMNavCommand {

    ElementLink link;
    URNspec urn;
    IntentionalElement src, dest;
    
    /**
     * 
     */
    public RemoveElementLinkCommand(ElementLink link) {
        this.link = link;
        urn = link.getGrlspec().getUrnspec();
        setLabel("RemoveElementLinkCommand");
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        dest = link.getDest();
        src = link.getSrc();
        redo();
    }
    
    public ElementLink getElementLink(){
        return link;
    }
    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getGrlspec().getLinks().remove(link);
        
        link.setDest(null);
        link.setSrc(null);

        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null && urn != null: "Pre something is null"; //$NON-NLS-1$

        assert urn.getGrlspec().getLinks().contains(link) : "Pre urn contain link"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null && urn != null: "Post something is null"; //$NON-NLS-1$

        assert !urn.getGrlspec().getLinks().contains(link) : "Post urn contain link"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        link.setDest(dest);
        link.setSrc(src);
    }
}
