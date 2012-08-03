/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.kpimodel.KPIConversion;
import grl.kpimodel.QualitativeMappings;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command adds a KPI conversion.
 * 
 * 
 * @author jkealey
 * 
 */
public class CreateKPIConversionCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private KPIConversion conv;
    private Class type;

    /**
     * 
     */
    public CreateKPIConversionCommand(URNspec urn, Class type) {
        this.urn = urn;
        this.type = type;
        if (type == QualitativeMappings.class)
            setLabel("Create Qualitative Mapping");
        else
            setLabel("Create KPI Conversion"); // unknown.
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && type != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (conv == null) {
            conv = (KPIConversion) ModelCreationFactory.getNewObject(urn, type);
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getGrlspec().getKPIConversion().add(conv);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getGrlspec() != null && conv != null && type != null : "post not null"; //$NON-NLS-1$
        assert urn.getGrlspec().getKPIConversion().contains(conv) : "post conversion not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getGrlspec() != null && conv != null && type != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getGrlspec().getKPIConversion().contains(conv) : "pre conversion not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getKPIConversion().remove(conv);
        testPreConditions();
    }

    public KPIConversion getKPIConversion() {
        return conv;
    }

    public void setKPIConversion(KPIConversion conv) {
        this.conv = conv;
    }

}
