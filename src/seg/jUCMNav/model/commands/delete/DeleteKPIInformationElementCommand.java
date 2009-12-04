/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveKPIInformationElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;

/**
 * Delete an KPIInformationElement (including all the KPIModelLink associate to it)
 * 
 * @author pchen
 * 
 */
public class DeleteKPIInformationElementCommand extends CompoundCommand {

    KPIInformationElement element;

    /**
     * @param element
     *            The KPIInformationElement to delete
     */
    public DeleteKPIInformationElementCommand(KPIInformationElement element) {
        setLabel(Messages.getString("DeleteKPIInformationElementCommand.deleteKPIInformationElement")); //$NON-NLS-1$
        this.element = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
        return super.canExecute();
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

    private void deleteKPIModelLink() {
        if (element.getKpiModelLinksSrc().size() > 0) {
            for (int i = 0; i < element.getKpiModelLinksSrc().size(); i++) {
                KPIModelLink link = (KPIModelLink) element.getKpiModelLinksSrc().get(i);
                add(new DeleteKPIModelLinkCommand(link));
            }
        }
    }

    // TODO Fix delete KPIInformationConfig.
    // A modification is required in the metamodel to implement a bidirectional links between KPI Information Elements and Configs (instead of unidirectional).
    // Should access the Config to delete using this association.
    private void deleteKPIInformationConfig() {
        for (Iterator it = element.getGrlspec().getStrategies().iterator(); it.hasNext();) {
            EvaluationStrategy strategy = (EvaluationStrategy) it.next();
            for (Iterator itConfig = strategy.getKpiInfoConfig().iterator(); itConfig.hasNext();) {
                KPIInformationConfig config = (KPIInformationConfig) itConfig.next();
                if (config.getKpiInfoElement().equals(element)) {
                    add(new DeleteKPIInformationConfigCommand(config));
                }
            }
        }
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Builds a sequence of DeleteGRLNodeCommands
     * 
     */
    private void build() {
        // Verify if the definition can be delete.
        if (element.getRefs().size() == 0 || DeletePreferences.getDeleteReference(element)) {

            deleteKPIInformationConfig();
            // Delete all the KPIModelLink associate with the KPIInformationElement
            deleteKPIModelLink();
            // Add during execute delete of all the references
            for (Iterator it = element.getRefs().iterator(); it.hasNext();) {
                KPIInformationElementRef reference = (KPIInformationElementRef) it.next();
                add(new PreDeleteUrnModelElementCommand(reference));
                add(new RemoveURNmodelElementCommand(reference));
            }

            add(new RemoveKPIInformationElementCommand(element));

        }
    }
}