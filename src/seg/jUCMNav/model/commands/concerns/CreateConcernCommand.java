package seg.jUCMNav.model.commands.concerns;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * CompoundCommand to add a concern, first creates the concern and then assigns the concern to all of its diagrams
 * 
 * @author gunterm
 */
public class CreateConcernCommand extends CompoundCommand {

    /**
     * @param urn
     *            URNspec to which to add the concern
     * @param name
     *            of the concern
     * @param description
     *            of the concern
     * @param diagrams
     *            IURNDiagrams of the concern
     */
    public CreateConcernCommand(URNspec urn, String name, String description, List diagrams) {
        setLabel(Messages.getString("CreateConcernCommand.CreateConcern")); //$NON-NLS-1$
        // create the concern
        InternalCreateConcernCommand cmd = new InternalCreateConcernCommand(urn, name, description);
        add(cmd);
        // assign concern to its diagrams
        for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();
            add(new AssignConcernDiagramCommand(diagram, cmd.getConcern()));
        }
    }

}