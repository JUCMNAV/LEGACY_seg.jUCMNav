package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * Adds a GRL graph to the current URNspec.
 * 
 * @author Jean-François Roy
 * 
 */
public class AddGrlGraphAction extends URNSelectionAction {

    public static final String ADDGRLGRAPH = "seg.jUCMNav.AddGrlGraph"; //$NON-NLS-1$

    public AddGrlGraphAction(IWorkbenchPart part) {
        super(part);
        setId(ADDGRLGRAPH);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/grl16.gif")); //$NON-NLS-1$
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
     * @return a {@link CreateGrlGraphCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateGrlGraphCommand create = new CreateGrlGraphCommand(sel.getUrnspec());

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
