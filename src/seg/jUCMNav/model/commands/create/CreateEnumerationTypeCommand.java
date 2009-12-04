/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.EnumerationType;
import urn.URNspec;

/**
 * This command adds an enumeration type.
 * 
 * @author jkealey
 * 
 */
public class CreateEnumerationTypeCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private String name;
    private String values;
    private EnumerationType enumerationType;

    public void setEnumerationType(EnumerationType enumerationType) {
        this.enumerationType = enumerationType;
        if (enumerationType != null) {
            this.name = enumerationType.getName();
            this.values = enumerationType.getValues();
        } else
            this.values = Messages.getString("CreateEnumerationTypeCommand.DEFAULT"); //$NON-NLS-1$
    }

    /**
     * 
     */
    public CreateEnumerationTypeCommand(URNspec urn, String name, String values) {
        this.urn = urn;
        this.name = name;
        this.values = values;
        setLabel(Messages.getString("CreateEnumerationTypeCommand.CreateEnumerationType")); //$NON-NLS-1$
    }

    /**
     * 
     */
    public CreateEnumerationTypeCommand(URNspec urn) {
        this.urn = urn;
        this.values = Messages.getString("CreateEnumerationTypeCommand.DEFAULT"); //$NON-NLS-1$
        setLabel(Messages.getString("CreateEnumerationTypeCommand.CreateEnumerationType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (enumerationType == null) {
            enumerationType = (EnumerationType) ModelCreationFactory.getNewObject(urn, EnumerationType.class);
        }
        if (name != null)
            enumerationType.setName(name);
        enumerationType.setValues(values);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getEnumerationTypes().add(enumerationType);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUcmspec() != null && enumerationType != null : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getEnumerationTypes().contains(enumerationType) : "post enumerationType not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUcmspec() != null && enumerationType != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getEnumerationTypes().contains(enumerationType) : "pre enumerationType not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUcmspec().getEnumerationTypes().remove(enumerationType);
        testPreConditions();
    }

    public EnumerationType getEnumerationType() {
        return enumerationType;
    }
}
