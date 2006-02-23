/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

/**
 * This command is used to create all LinkRef from an intentionalElementRef to other intentionalElementRef
 * that are not already in the GRLGraph. Use when modifying the definition of an intentionalElementRef or 
 * when using action to recreate deleted LinkRef.
 * 
 * This command could have performance problem for large GRLGraph (but should not be call a lot)
 * 
 * @author Jean-François Roy
 *
 */
public class CreateAllLinkRefCommand extends CompoundCommand {

    /**
     * @param element
     *      The IntentionalElementReference
     */
    public CreateAllLinkRefCommand(IntentionalElementRef element) {
        setLabel("Create All LinkRefs");
        
        GRLGraph graph = (GRLGraph)element.getDiagram();
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();){
            GRLNode grlnode = (GRLNode)iter.next();
            if (grlnode instanceof IntentionalElementRef){
                IntentionalElementRef current = (IntentionalElementRef)grlnode;

                //Verify that no LinkRef exist between the two element
                boolean exist = false;
                for(int i=0; i<current.getSucc().size();i++){
                    if (element.getPred().contains(current.getSucc().get(i))){
                        exist = true;
                    }
                }
                for(int i=0; i<current.getPred().size();i++){
                    if (element.getSucc().contains(current.getPred().get(i))){
                        exist = true;
                    }
                }
                
                if (!exist){
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
        for (int i=0; i<element.getDef().getLinksDest().size(); i++){
            if (current.getDef().getLinksSrc().contains(
                    element.getDef().getLinksDest().get(i))){
                add(new AddLinkRefCommand(graph, current, element, (ElementLink)element.getDef().getLinksDest().get(i)));
            }
        }
        for (int i=0; i<element.getDef().getLinksSrc().size(); i++){
            if (current.getDef().getLinksDest().contains(
                    element.getDef().getLinksSrc().get(i))){
                add(new AddLinkRefCommand(graph, element, current, (ElementLink)element.getDef().getLinksSrc().get(i)));
            }
        }
    }
    
}
