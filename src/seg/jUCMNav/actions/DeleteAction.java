package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;

import fm.Feature;
import grl.IntentionalElementRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.ConnectionLabelEditPart;
import seg.jUCMNav.model.commands.delete.DeleteUselessStartNCEndCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import ucm.map.UCMmap;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import seg.jUCMNav.actions.SelectionHelper;
/**
 * DeleteAction overridden from framework to delete small paths created after a mass deletion.
 * 
 * @author jkealey
 * 
 */
public class DeleteAction extends org.eclipse.gef.ui.actions.DeleteAction {

    /**
     * Constructs a <code>DeleteAction</code> using the specified part.
     * 
     * @param part
     *            The part for this action
     */
    public DeleteAction(IWorkbenchPart part) {
        super(part);
        setLazyEnablementCalculation(false);
    }

    protected boolean calculateEnabled() {
        Command cmd = createDeleteCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		else {
			SelectionHelper sh = new SelectionHelper(getSelectedObjects());
			switch (sh.getSelectionType()) {
			case SelectionHelper.INTENTIONALELEMENT:
			case SelectionHelper.INTENTIONALELEMENTREF:
				Feature felement = null;
				if (sh.getURNmodelElement() instanceof Feature) {
					felement = (Feature) sh.getURNmodelElement();
				} else if (sh.getURNmodelElement() instanceof IntentionalElementRef
						&& ((IntentionalElementRef) sh.getURNmodelElement())
								.getDef() instanceof Feature) {

					felement = (Feature) ((IntentionalElementRef) sh
							.getURNmodelElement()).getDef();
				}
				if (felement != null) {
					String metaValue = MetadataHelper.getMetaData(felement, "CoURN"); //$NON-NLS-1$
					if (metaValue != null && metaValue.equalsIgnoreCase("root feature") && felement.getRefs().size() <= 1) //$NON-NLS-1$
						return false;
				}
			}
			return cmd.canExecute();
		}
     
    }
    
    public List getSelectedObjectsForDeletion() {
        List list = getSelectedObjects();
        Vector result = new Vector();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object o = (Object) iterator.next();
           
            if (!(o instanceof ConnectionLabelEditPart)) {
            	System.out.println("The selected objects for delection is "+ o.toString());
                result.add(o);
            }
        }
        return result;

    }

    /**
     * Performs the delete action on the selected objects.
     */
    public void run() {
        URNspec urn = ((UCMNavMultiPageEditor) getWorkbenchPart()).getModel();
        Command cmd = createDeleteSmallPaths();
        List objects = getSelectedObjectsForDeletion();
        if (objects.size() > 0) {
            boolean result = true;
            if (containsScenario(objects)) {
                result = MessageDialog.openConfirm(getWorkbenchPart().getSite().getShell(),
                        Messages.getString("DeleteAction_0"), Messages.getString("DeleteAction_1")); //$NON-NLS-1$ //$NON-NLS-2$
            }

            if (result) {
                if (cmd == null || !cmd.canExecute())
                    execute(createDeleteCommand(objects));
                else
                    execute(createDeleteCommand(objects).chain(cmd));
            }
        } else {
            if (cmd != null && cmd.canExecute())
                execute(cmd);
        }
    }

    private boolean containsScenario(List selected) {
        for (Iterator i = selected.iterator(); i.hasNext();) {
            Object part = (Object) i.next();
            if (part instanceof EditPart) {
                if (((EditPart) part).getModel() instanceof ScenarioDef) {
                    return ((ScenarioDef) ((EditPart) part).getModel()).getParentScenarios().size() > 0;
                }
            }
        }

        return false;
    }

    /**
     * @return a new {@link DeleteUselessStartNCEndCommand}.
     */
    private Command createDeleteSmallPaths() {
        UrnEditor editor = ((UCMNavMultiPageEditor) getWorkbenchPart()).getCurrentPage();
        if (editor == null || !(editor.getModel() instanceof UCMmap))
            return null;
        UCMmap map = (UCMmap) editor.getModel();
        return new DeleteUselessStartNCEndCommand(map, editor.getGraphicalViewer().getEditPartRegistry());
    }
}
