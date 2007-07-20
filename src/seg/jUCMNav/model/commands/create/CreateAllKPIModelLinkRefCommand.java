/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;

/**
 * This command is used to create all KPIModelLinkRef from an KPIInformationElementRef to an IntentionalElementRef that are not already in the GRLGraph. Use
 * when modifying the definition of a KPIInformationElementRef or IntentionalElementRef or when using action to recreate deleted KPIModelLinkRef.
 * 
 * This command could have performance problem for large GRLGraph (but should not be call a lot)
 * 
 * @author pchen
 * 
 */
public class CreateAllKPIModelLinkRefCommand extends CompoundCommand {

    /**
     * @param element
     *            The KPIInformationElementRef
     */
    public CreateAllKPIModelLinkRefCommand(KPIInformationElementRef element) {
        setLabel(Messages.getString("CreateAllKPIModelLinkRefCommand.createAllKPIModelLinkRefs")); //$NON-NLS-1$

        GRLGraph graph = (GRLGraph) element.getDiagram();
        init(element, graph);
    }

    /**
     * @param element
     *            The KPIInformationElementRef
     */
    public CreateAllKPIModelLinkRefCommand(IntentionalElementRef element) {
        setLabel(Messages.getString("CreateAllKPIModelLinkRefCommand.createAllKPIModelLinkRefs")); //$NON-NLS-1$

        GRLGraph graph = (GRLGraph) element.getDiagram();
        init(element, graph);
    }

    /**
     * Used when creating from outline.
     * 
     * @param graph
     * @param element
     */
    public CreateAllKPIModelLinkRefCommand(GRLGraph graph, KPIInformationElementRef element) {
        setLabel(Messages.getString("CreateAllKPIModelLinkRefCommand.createAllKPIModelLinkRefs")); //$NON-NLS-1$

        init(element, graph);
    }

    /**
     * Used when creating from outline.
     * 
     * @param graph
     * @param element
     */
    public CreateAllKPIModelLinkRefCommand(GRLGraph graph, IntentionalElementRef element) {
        setLabel(Messages.getString("CreateAllKPIModelLinkRefCommand.createAllKPIModelLinkRefs")); //$NON-NLS-1$

        init(element, graph);
    }

    private void init(KPIInformationElementRef element, GRLGraph graph) {
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode grlnode = (GRLNode) iter.next();
            if (grlnode instanceof IntentionalElementRef) {
                IntentionalElementRef current = (IntentionalElementRef) grlnode;

                // Verify that no KPIModelLinkRef exist between the two elements
                boolean exist = false;
                for (int i = 0; i < current.getPred().size(); i++) {
                    if (element.getSucc().contains(current.getPred().get(i))) {
                        exist = true;
                    }
                }

                if (!exist) {
                    createAddKPIModelLinkRefCommand(graph, element, current);
                }

            }
        }
    }

    private void init(IntentionalElementRef element, GRLGraph graph) {
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode grlnode = (GRLNode) iter.next();
            if (grlnode instanceof KPIInformationElementRef) {
                KPIInformationElementRef current = (KPIInformationElementRef) grlnode;

                // Verify that no KPIModelLinkRef exist between the two elements
                boolean exist = false;
                for (int i = 0; i < current.getSucc().size(); i++) {
                    if (element.getPred().contains(current.getSucc().get(i))) {
                        exist = true;
                    }
                }

                if (!exist) {
                    createAddKPIModelLinkRefCommand(graph, current, element);
                }

            }
        }
    }

    /**
     * @param graph
     * @param element
     * @param current
     */
    private void createAddKPIModelLinkRefCommand(GRLGraph graph, KPIInformationElementRef element, IntentionalElementRef current) {
        Indicator indicator = null;
        if (current.getDef().getType() == IntentionalElementType.INDICATOR_LITERAL) {
            indicator = (Indicator) current.getDef();

            for (int i = 0; i < element.getDef().getKpiModelLinksSrc().size(); i++) {
                if (indicator.getKpiModelLinksDest().contains(element.getDef().getKpiModelLinksSrc().get(i))) {
                    add(new AddKPIModelLinkRefCommand(graph, element, current, (KPIModelLink) element.getDef().getKpiModelLinksSrc().get(i)));
                }
            }
        }
    }

}
