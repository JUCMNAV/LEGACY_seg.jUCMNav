package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;

/**
 * Adds a GRL graph to the current URNspec. 
 * @author Jean-François Roy
 *
 */
public class AddGrlGraphAction extends URNSelectionAction {

    public static final String ADDGRLGRAPH = "seg.jUCMNav.AddGrlGraph"; //$NON-NLS-1$
    
    public AddGrlGraphAction(IWorkbenchPart part){
        super(part);
        setId(ADDGRLGRAPH);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grl16.gif")); //$NON-NLS-1$
    }
    
    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null;
    }
    
    /**
     * @return a {@link CreateGrlGraphCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateGrlGraphCommand create = new CreateGrlGraphCommand(sel.getUrnspec());

        return create;
    }
}
