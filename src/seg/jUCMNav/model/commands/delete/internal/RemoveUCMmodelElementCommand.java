package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.UCMmap;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.UCMmodelElement;

/**
 * Given a PathNode or ComponentRef, remove it from the map or pathgraph, the component ref binding hierarchy and disconnect it from its definition if it is a
 * responsibility.
 * 
 * @author jkealey
 * 
 */
public class RemoveUCMmodelElementCommand extends Command implements JUCMNavCommand {
    private UCMmodelElement element;
    private ComponentRef parent;
    private UCMmap map;
    private UCMmodelElement definition;
    private boolean aborted = false;

    /**
     * 
     * @param pn
     *            the PathNode to be deleted.
     */
    public RemoveUCMmodelElementCommand(PathNode pn) {
        this.element = pn;
    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be deleted.
     */
    public RemoveUCMmodelElementCommand(ComponentRef cr) {
        this.element = cr;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element instanceof PathNode) {
            PathNode node = (PathNode) element;
            aborted = node.getSpecDiagram() == null;
            if (aborted)
                return;
            map = (UCMmap)node.getSpecDiagram();
            parent = (ComponentRef)node.getCompRef();
            if (node instanceof RespRef) {
                RespRef ref = (RespRef) node;
                definition = ref.getRespDef();
            }

        } else if (element instanceof ComponentRef) {
            ComponentRef ref = (ComponentRef) element;
            map = (UCMmap)ref.getSpecDiagram();
            aborted = map == null;
            if (aborted)
                return;
            parent = (ComponentRef)ref.getParent();
            definition = (ComponentElement)ref.getCompDef();
        }
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;

        testPreConditions();

        if (element instanceof PathNode) {
            PathNode node = (PathNode) element;
            map.getNodes().remove(element);
            node.setCompRef(null);
            if (node instanceof RespRef) {
                RespRef ref = (RespRef) node;
                ref.setRespDef(null);
            }

        } else if (element instanceof ComponentRef) {
            ComponentRef ref = (ComponentRef) element;
            map.getCompRefs().remove(ref);
            ref.setParent(null);
            ref.setCompDef(null);
        }

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;

        testPostConditions();

        if (element instanceof PathNode) {
            PathNode node = (PathNode) element;
            map.getNodes().add(element);
            node.setCompRef(parent);
            if (node instanceof RespRef) {
                RespRef ref = (RespRef) node;
                ref.setRespDef((Responsibility) definition);
            }

        } else if (element instanceof ComponentRef) {
            ComponentRef ref = (ComponentRef) element;
            map.getCompRefs().add(ref);
            ref.setParent(parent);
            ref.setCompDef((ComponentElement) definition);
        }
        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null && map != null : "pre something is null";
        if (element instanceof PathNode)
            assert map.getNodes().contains(element) : "pre pathgraph contains element";
        else if (element instanceof ComponentRef)
            assert map.getCompRefs().contains(element) : "pre pathgraph contains element";

        if (definition != null) {
            if (element instanceof RespRef) {
                RespRef ref = (RespRef) element;
                assert definition == ref.getRespDef() : "pre resp def";
            } else if (element instanceof ComponentRef) {
                ComponentRef ref = (ComponentRef) element;
                assert definition == ref.getCompDef() : "pre comp def";
            }
        }

        if (parent != null) {
            if (element instanceof PathNode) {
                PathNode node = (PathNode) element;
                assert parent == node.getCompRef() : "pre pn parent";

            } else if (element instanceof ComponentRef) {
                ComponentRef ref = (ComponentRef) element;
                assert parent == ref.getParent() : "pre cr parent";
            }
        }

    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null && map != null : "post something is null";
        if (element instanceof PathNode)
            assert !map.getNodes().contains(element) : "post pathgraph contains element";
        else if (element instanceof ComponentRef)
            assert !map.getCompRefs().contains(element) : "post pathgraph contains element";
        if (definition != null) {
            if (element instanceof RespRef) {
                RespRef ref = (RespRef) element;
                assert null == ref.getRespDef() : "post resp def";
            } else if (element instanceof ComponentRef) {
                ComponentRef ref = (ComponentRef) element;
                assert null == ref.getCompDef() : "post comp def";
            }
        }

        if (parent != null) {
            if (element instanceof PathNode) {
                PathNode node = (PathNode) element;
                assert null == node.getCompRef() : "post pn parent";

            } else if (element instanceof ComponentRef) {
                ComponentRef ref = (ComponentRef) element;
                assert null == ref.getParent() : "post cr parent";
            }
        }
    }

}
