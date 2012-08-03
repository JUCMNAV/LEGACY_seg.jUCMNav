package seg.jUCMNav.actions.scenarios;

import grl.kpimodel.QualitativeMappings;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.create.CreateQualitativeMappingCommand;

/**
 * Creates a qualitative mapping in the mapping list. 
 * 
 * @author jkealey
 * 
 */
public class AddQualitativeMappingAction extends URNSelectionAction {

    public static final String ADDQUALITATIVEMAPPING = "Add Qualitative Mapping"; // $NON-NLS-1$ 

    /**
     * 
     * Creates a qualitative mapping in the mapping list.
     * 
     * @param part
     */
    public AddQualitativeMappingAction(IWorkbenchPart part) {
        super(part);
        setId(ADDQUALITATIVEMAPPING);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/plus.gif")); //$NON-NLS-1$
    }

    /**
     * We need to be a KPIConversionListTreeEditPart. (Can't use a model element because ELists don't have listeners)
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (!(sel.getUrnspec() != null && sel.getGrlspec() != null && getSelectedObjects().size() == 1))
            return false;

        return getSelectedObjects().get(0) instanceof EditPart && ((EditPart)getSelectedObjects().get(0)).getModel() instanceof QualitativeMappings;
    }

    /**
     * @return a {@link seg.jUCMNav.model.commands.create.CreateKPIConversionCommand}.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        QualitativeMappings mappings = (QualitativeMappings)((EditPart)getSelectedObjects().get(0)).getModel(); 
        CreateQualitativeMappingCommand create = new CreateQualitativeMappingCommand(sel.getUrnspec(), mappings);
        return create;
    }

}
