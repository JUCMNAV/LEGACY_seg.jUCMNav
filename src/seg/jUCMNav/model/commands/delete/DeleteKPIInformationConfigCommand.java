/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * This command delete an GRL KPIInformationConfig
 * 
 * @author pchen
 * 
 */
public class DeleteKPIInformationConfigCommand extends Command implements JUCMNavCommand {

    private KPIInformationConfig kpiInformationConfig;
    private EvaluationStrategy strategy;
    private KPIInformationElement kpiInformationElement;

    /**
     * 
     */
    public DeleteKPIInformationConfigCommand(KPIInformationConfig config) {
        this.kpiInformationConfig = config;
        setLabel(Messages.getString("DeleteKPIInformationConfigCommand.deleteKPIInformationConfig")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        strategy = kpiInformationConfig.getStrategies();
        kpiInformationElement = kpiInformationConfig.getKpiInfoElement();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // Remove the evaluation object from the EvaluationManager to calculate the new value
        EvaluationStrategyManager.getInstance().setKPIInformationConfigForElement(kpiInformationElement,
                (KPIInformationConfig) ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), KPIInformationConfig.class));

        kpiInformationConfig.setStrategies(null);
        kpiInformationConfig.setKpiInfoElement(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert kpiInformationConfig != null && strategy != null && kpiInformationElement != null : "pre something is null"; //$NON-NLS-1$
        assert strategy.getKpiInfoConfig().contains(kpiInformationConfig) : "pre evaluation in strategy"; //$NON-NLS-1$
        assert kpiInformationConfig.getKpiInfoElement().equals(kpiInformationElement) : "pre evaluation in intentional element"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert kpiInformationConfig != null && strategy != null && kpiInformationElement != null : "post something is null"; //$NON-NLS-1$
        assert !strategy.getKpiInfoConfig().contains(kpiInformationConfig) : "post evaluation in strategy"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        kpiInformationConfig.setStrategies(strategy);
        kpiInformationConfig.setKpiInfoElement(kpiInformationElement);

        // Set the kpiInformationConfig object from the EvaluationManager
        EvaluationStrategyManager.getInstance().setKPIInformationConfigForElement(kpiInformationElement, kpiInformationConfig);

        testPreConditions();
    }
}
