package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.DeleteInBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteOutBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.DeletePluginCommand;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * Given a PathNode, NodeConnection or Map, removes anything to do with Stub-Plugin bindings.
 * 
 * Also a facade for deleting InBindings, OutBindings and PluginBindings.
 * 
 * @author jkealey
 * 
 */
public class DeleteBindingsCommand extends CompoundCommand {
    private EObject element;

    /**
     * 
     * @param binding
     *            the PluginBinding to be cleaned.
     */
    public DeleteBindingsCommand(InBinding binding) {
        this.element = binding;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned.
     */
    public DeleteBindingsCommand(UCMmap map) {
        this.element = map;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be cleaned.
     */
    public DeleteBindingsCommand(NodeConnection nc) {
        this.element = nc;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @param binding
     *            the OutBinding to be cleaned.
     */
    public DeleteBindingsCommand(OutBinding binding) {
        this.element = binding;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @param pn
     *            the PathNode to be cleaned.
     */
    public DeleteBindingsCommand(PathNode pn) {
        this.element = pn;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @param binding
     *            the PluginBinding to be cleaned.
     */
    public DeleteBindingsCommand(PluginBinding binding) {
        this.element = binding;
        setLabel(Messages.getString("DeleteBindingsCommand.deleteBinding")); //$NON-NLS-1$
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
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        assert this.element != null : "something is null"; //$NON-NLS-1$

        if (element instanceof StartPoint) {
            StartPoint point = (StartPoint) element;
            for (Iterator iter = point.getInBindings().iterator(); iter.hasNext();) {
                InBinding binding = (InBinding) iter.next();
                add(new DeleteInBindingCommand(binding));
            }

        } else if (element instanceof EndPoint) {
            EndPoint point = (EndPoint) element;
            for (Iterator iter = point.getOutBindings().iterator(); iter.hasNext();) {
                OutBinding binding = (OutBinding) iter.next();
                add(new DeleteOutBindingCommand(binding));
            }
        } else if (element instanceof Stub) {
            Stub stub = (Stub) element;
            for (Iterator iter = stub.getBindings().iterator(); iter.hasNext();) {
                PluginBinding element = (PluginBinding) iter.next();
                add(new DeletePluginCommand(element));
            }
        } else if (element instanceof NodeConnection) {
            NodeConnection connection = (NodeConnection) element;
            for (Iterator iter = connection.getInBindings().iterator(); iter.hasNext();) {
                InBinding binding = (InBinding) iter.next();
                add(new DeleteInBindingCommand(binding));
            }
            for (Iterator iter = connection.getOutBindings().iterator(); iter.hasNext();) {
                OutBinding binding = (OutBinding) iter.next();
                add(new DeleteOutBindingCommand(binding));
            }

        } else if (element instanceof UCMmap) {

            UCMmap map = (UCMmap) element;

            // break the links between this map and its containers
            for (Iterator iter = map.getParentStub().iterator(); iter.hasNext();) {
                PluginBinding element = (PluginBinding) iter.next();
                add(new DeletePluginCommand(element));
            }
            // break the links between this map and any of its plugins
            for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
                PathNode element = (PathNode) iter.next();
                if (element instanceof Stub) {
                    Stub stub = (Stub) element;
                    // for each stub in this map, remove all PluginBindings
                    for (Iterator iter2 = stub.getBindings().iterator(); iter2.hasNext();) {
                        PluginBinding elem = (PluginBinding) iter2.next();
                        add(new DeletePluginCommand(elem));
                    }
                }

            }
        } else if (element instanceof PluginBinding) {
            PluginBinding binding = (PluginBinding) element;
            add(new DeletePluginCommand(binding));
        } else if (element instanceof InBinding) {
            InBinding binding = (InBinding) element;
            add(new DeleteInBindingCommand(binding));
        } else if (element instanceof OutBinding) {
            OutBinding binding = (OutBinding) element;
            add(new DeleteOutBindingCommand(binding));
        }

        super.execute();
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

}
