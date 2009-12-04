package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.scenario.EnumerationType;

/**
 * Changes the name/values of an enumeration type.
 * 
 * @author jkealey
 */
public class ChangeEnumerationTypeCommand extends Command implements JUCMNavCommand {
    private EnumerationType elem;
    private String name, oldName, values, oldValues;

    public ChangeEnumerationTypeCommand(EnumerationType elem, String newName, Vector newValues) {
        this.elem = elem;
        this.name = newName;
        String tmp = ""; //$NON-NLS-1$
        for (Iterator iter = newValues.iterator(); iter.hasNext();) {
            String str = (String) iter.next();
            tmp += "," + str; //$NON-NLS-1$
        }

        if (tmp.length() > 0)
            this.values = tmp.substring(1);
        else
            this.values = tmp;

        setLabel(Messages.getString("ChangeEnumerationTypeCommand.ChangeEnumerationType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldName = elem.getName();
        oldValues = elem.getValues();
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        elem.setName(name);
        elem.setValues(values);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert elem != null : "post no element!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elem != null : "pre no element!"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        elem.setName(oldName);
        elem.setValues(oldValues);

        testPreConditions();
    }

    public EnumerationType getElem() {
        return elem;
    }

    public void setElem(EnumerationType elem) {
        this.elem = elem;
    }

}