package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.KPIConversionListTreeEditPart;
import seg.jUCMNav.model.commands.create.CreateKPIConversionCommand;

/**
 * Creates a KPI conversion to the GRL spec
 * 
 * @author jkealey
 * 
 */
public class AddKPIConversionAction extends URNSelectionAction {

    public static final String ADDKPICONVERSION = "Add KPI Conversion"; // $NON-NLS-1$ 

    protected Class type;
    /**
     * 
     * Creates a KPI conversion to the GRL spec
     * 
     * @param part
     */
    public AddKPIConversionAction(IWorkbenchPart part, Class type) {
        super(part);

        this.type = type;
        setId(ADDKPICONVERSION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/plus.gif")); //$NON-NLS-1$
    }

    /**
     * We need to be a KPIConversionListTreeEditPart. (Can't use a model element because ELists don't have listeners)
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (!(sel.getUrnspec() != null && sel.getGrlspec() != null && getSelectedObjects().size() == 1))
            return false;

        return getSelectedObjects().get(0) instanceof KPIConversionListTreeEditPart;
    }

    /**
     * @return a {@link seg.jUCMNav.model.commands.create.CreateKPIConversionCommand}.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        CreateKPIConversionCommand create = new CreateKPIConversionCommand(sel.getUrnspec(), type);
        return create;
    }

}
