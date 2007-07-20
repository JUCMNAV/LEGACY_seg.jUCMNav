/**
 * 
 */
package seg.jUCMNav.actions.kpi;

import grl.kpimodel.IndicatorGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateIndicatorGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;

/**
 * Creates a new Indicator Group.
 * 
 * @author pchen
 * 
 */
public class AddIndicatorGroupAction extends URNSelectionAction {

    public static final String ADDINDICATORGROUP = "Add Indicator Group"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddIndicatorGroupAction(IWorkbenchPart part) {
        super(part);

        setId(ADDINDICATORGROUP);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/folder16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getGRLspec() != null;
    }

    /**
     * Returns a new {@link CreateStrategiesGroupCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        IndicatorGroup group = (IndicatorGroup) ModelCreationFactory.getNewObject(sel.getUrnspec(), IndicatorGroup.class);
        CreateIndicatorGroupCommand create = new CreateIndicatorGroupCommand(sel.getUrnspec(), group);

        return create;
    }
}
