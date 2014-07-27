package seg.jUCMNav.actions.features;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.create.CreateFMDCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * Adds a Feature Model graph to the current URNspec.
 */
public class AddFMDAction extends URNSelectionAction {

    public static final String ADDFMD = "seg.jUCMNav.AddFMD"; //$NON-NLS-1$

    public AddFMDAction(IWorkbenchPart part) {
        super(part);
        setId(ADDFMD);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/fmd16.gif")); //$NON-NLS-1$
    }

    /**
     * If you have a URNspec or Graph selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        int type = sel.getSelectionType();

        return sel.getUrnspec() != null
                && (type == SelectionHelper.MAP || type == SelectionHelper.GRLGRAPH || (type == SelectionHelper.URNSPEC) && (sel.getSelection().size() == 1));

    }

    /**
     * @return a {@link CreateFMDCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateFMDCommand create = new CreateFMDCommand(sel.getUrnspec());

        if (sel.getUrnspec()!=null && sel.getMap()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getMap())+1);
        else if (sel.getUrnspec()!=null && sel.getGrlgraph()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getGrlgraph())+1);

        if (create.canExecute()) {
            DisplayPreferences.getInstance().setShowGRLS(true);
        }
        return create;
    }
}
