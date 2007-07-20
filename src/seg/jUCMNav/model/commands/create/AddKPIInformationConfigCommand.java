/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command link a KPIInformationConfig to it KPIInformationElement and Strategy
 * 
 * @author pchen
 * 
 */
public class AddKPIInformationConfigCommand extends Command implements JUCMNavCommand {

    private KPIInformationConfig kpiInformationConfig;
    private KPIInformationElement element;
    private EvaluationStrategy strategy;

    /**
     * Constructor
     */
    public AddKPIInformationConfigCommand(KPIInformationConfig config, KPIInformationElement elem, EvaluationStrategy strategy) {
        this.kpiInformationConfig = config;
        this.element = elem;
        this.strategy = strategy;

        setLabel(Messages.getString("AddKPIInformationConfigCommand.addKPIInformationConfig")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ((kpiInformationConfig != null && element != null && strategy != null) && (kpiInformationConfig.getKpiInfoElement() == null)
                && (kpiInformationConfig.getStrategies() == null)) {
            return true;
        }
        return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        kpiInformationConfig.setKpiInfoElement(element);
        strategy.getKpiInfoConfig().add(kpiInformationConfig);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert kpiInformationConfig != null && element != null && strategy != null : "pre null"; //$NON-NLS-1$
        assert kpiInformationConfig.getKpiInfoElement() != element && kpiInformationConfig.getStrategies() != strategy : "pre link set!"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert kpiInformationConfig != null && element != null && strategy != null : "post null"; //$NON-NLS-1$
        assert !(kpiInformationConfig.getKpiInfoElement() != element && kpiInformationConfig.getStrategies() != strategy) : "post link set"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        strategy.getKpiInfoConfig().remove(kpiInformationConfig);
        kpiInformationConfig.setKpiInfoElement(null);
        testPreConditions();
    }
}
