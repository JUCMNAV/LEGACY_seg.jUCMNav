package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * Adds a new blank use case map in the current editor.
 * 
 * @author jkealey
 */
public class AddMapAction extends URNSelectionAction {
    public static final String ADDMAP = "seg.jUCMNav.AddMap"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddMapAction(IWorkbenchPart part) {
        super(part);
        setId(ADDMAP);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * If you have a URNspec or Map selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        int type = sel.getSelectionType();

        return sel.getUrnspec() != null
                && (type == SelectionHelper.MAP || type == SelectionHelper.GRLGRAPH || (type == SelectionHelper.URNSPEC) && (sel.getSelection().size() == 1));

    }

    /**
     * @return a {@link CreateMapCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateMapCommand create = new CreateMapCommand(sel.getUrnspec());
        if (sel.getUrnspec()!=null && sel.getMap()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getMap())+1);
        else if (sel.getUrnspec()!=null && sel.getGrlgraph()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getGrlgraph())+1);

        if (create.canExecute()) {
            DisplayPreferences.getInstance().setShowUCMS(true);
        }
        return create;
    }
}