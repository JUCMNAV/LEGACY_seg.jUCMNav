/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.IntentionalElement;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Command that create a kpiModelLink between a KPIInformationElement and an intentional element
 * 
 * @author pchen
 * 
 */
public class AddStandardKPIModelLinkCommand extends Command implements JUCMNavCommand {

    private KPIInformationElement src;
    private IntentionalElement dest;
    private URNspec urnspec;
    private KPIModelLink link;

    /**
     * 
     */
    public AddStandardKPIModelLinkCommand(URNspec urn, KPIInformationElement source, KPIModelLink link) {

        this.urnspec = urn;
        this.link = link;
        this.src = source;

        setLabel(Messages.getString("AddStandardKPIModelLinkCommand.addKPIModelLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // disallow source -> source connections
        if (src.equals(dest)) {
            return false;
        }
        if (!(dest instanceof Indicator)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // Set the source and destination
        src.getKpiModelLinksSrc().add(link);
        ((Indicator) dest).getKpiModelLinksDest().add(link);

        urnspec.getGrlspec().getKpiModelLinks().add(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPostConditions();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.dest = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre link"; //$NON-NLS-1$
        assert urnspec != null : "pre urn spec"; //$NON-NLS-1$
        assert src != null : "pre src"; //$NON-NLS-1$
        assert dest != null : "pre dest"; //$NON-NLS-1$

        assert !urnspec.getGrlspec().getKpiModelLinks().contains(link) : "pre link in spec"; //$NON-NLS-1$
        assert !src.getKpiModelLinksSrc().contains(link) : "pre link in source"; //$NON-NLS-1$
        assert !((Indicator) dest).getKpiModelLinksDest().contains(link) : "pre link in destination"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link"; //$NON-NLS-1$
        assert urnspec != null : "post urn spec"; //$NON-NLS-1$
        assert src != null : "post src"; //$NON-NLS-1$
        assert dest != null : "post dest"; //$NON-NLS-1$

        assert urnspec.getGrlspec().getKpiModelLinks().contains(link) : "post link in spec"; //$NON-NLS-1$
        assert src.getKpiModelLinksSrc().contains(link) : "post link in source"; //$NON-NLS-1$
        assert ((Indicator) dest).getKpiModelLinksDest().contains(link) : "post link in destination"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the source
        src.getKpiModelLinksSrc().remove(link);
        ((Indicator) dest).getKpiModelLinksDest().remove(link);

        urnspec.getGrlspec().getKpiModelLinks().remove(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPreConditions();
    }
}
