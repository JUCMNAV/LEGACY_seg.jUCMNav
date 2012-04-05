/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Dependency;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;

/**
 * This command is used to create all LinkRef from an intentionalElementRef to other intentionalElementRef that are not already in the GRLGraph. Use when
 * modifying the definition of an intentionalElementRef or when using action to recreate deleted LinkRef.
 * 
 * This command could have performance problem for large GRLGraph (but should not be call a lot)
 * 
 * @author Jean-François Roy, jkealey
 * 
 */
public class CreateAllLinkRefCommand extends CompoundCommand {

    protected IntentionalElementRef element;
    protected GRLGraph graph;
    protected Vector limitToTheseNodes;
    /**
     * @param element
     *            The IntentionalElementReference
     */
    public CreateAllLinkRefCommand(IntentionalElementRef element) {
        setLabel(Messages.getString("CreateAllLinkRefCommand.createAllLinkRefs")); //$NON-NLS-1$

        // GRLGraph graph = (GRLGraph)element.getDiagram();
        // init(element, graph);
        this.element = element;
        this.graph = (GRLGraph) element.getDiagram();
    }

    /**
     * Used when creating from outline.
     * 
     * @param graph
     * @param element
     */
    public CreateAllLinkRefCommand(GRLGraph graph, IntentionalElementRef element) {
        setLabel(Messages.getString("CreateAllLinkRefCommand.createAllLinkRefs")); //$NON-NLS-1$

        // init(element, graph);
        this.element = element;
        this.graph = graph;
    }

    /**
     * Used in copy paste. 
     * 
     * @param graph
     * @param element
     * @param limitToTheseNodes list of nodes for which links should be created. 
     */
    public CreateAllLinkRefCommand(GRLGraph graph, IntentionalElementRef element, Vector limitToTheseNodes) {
        setLabel(Messages.getString("CreateAllLinkRefCommand.createAllLinkRefs")); //$NON-NLS-1$

        // init(element, graph);
        this.element = element;
        this.graph = graph;
        this.limitToTheseNodes = limitToTheseNodes;
    }
    
    public boolean canExecute() {
        return true;
    }

    public boolean canUndo() {
        return true;
    }

    public void execute() {
        init(this.element, this.graph);
        super.execute();
    }

    private void init(IntentionalElementRef element, GRLGraph graph) {
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode grlnode = (GRLNode) iter.next();
            if (grlnode instanceof IntentionalElementRef && (limitToTheseNodes==null || limitToTheseNodes.contains(grlnode)) ) {
                IntentionalElementRef current = (IntentionalElementRef) grlnode;

                // Verify that no LinkRef exist between the two element
                boolean exist = false;
                for (int i = 0; i < current.getSucc().size(); i++) {
                    if (element.getPred().contains(current.getSucc().get(i))) {
                        exist = true;
                    }
                }
                for (int i = 0; i < current.getPred().size(); i++) {
                    if (element.getSucc().contains(current.getPred().get(i))) {
                        exist = true;
                    }
                }

                if (!exist) {
                    createAddLinkRefCommand(graph, element, current);
                }

            }
        }
    }

    /**
     * @param graph
     * @param element
     * @param current
     */
    private void createAddLinkRefCommand(GRLGraph graph, IntentionalElementRef element, IntentionalElementRef current) {
        for (int i = 0; i < element.getDef().getLinksDest().size(); i++) {
            if (current.getDef().getLinksSrc().contains(element.getDef().getLinksDest().get(i))) {
                if ((ElementLink) element.getDef().getLinksDest().get(i) instanceof Dependency)
                    add(new AddLinkRefCommand(graph, element, current, (ElementLink) element.getDef().getLinksDest().get(i)));
                else
                    add(new AddLinkRefCommand(graph, current, element, (ElementLink) element.getDef().getLinksDest().get(i)));
            }
        }
        for (int i = 0; i < element.getDef().getLinksSrc().size(); i++) {
            if (current.getDef().getLinksDest().contains(element.getDef().getLinksSrc().get(i))) {
                if ((ElementLink) element.getDef().getLinksSrc().get(i) instanceof Dependency)
                    add(new AddLinkRefCommand(graph, current, element, (ElementLink) element.getDef().getLinksSrc().get(i)));
                else
                    add(new AddLinkRefCommand(graph, element, current, (ElementLink) element.getDef().getLinksSrc().get(i)));
            }
        }
    }

}
