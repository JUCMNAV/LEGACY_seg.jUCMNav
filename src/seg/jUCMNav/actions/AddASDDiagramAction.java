package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateASDiagramCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * Adds a new blank use case map in the current editor.
 * 
 * @author jkealey
 */
public class AddASDDiagramAction extends URNSelectionAction {
    public static final String ADDASDIAGRAM = "seg.jUCMNav.AddDiagram"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddASDDiagramAction(IWorkbenchPart part) {
        super(part);
        setId(ADDASDIAGRAM);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ucm16.gif")); //$NON-NLS-1$
    }

    /**
     * If you have a URNspec or Map selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        int type = sel.getSelectionType();

        return sel.getUrnspec() != null
        		// && ( type == SelectionHelper.ASDIAGRAM && (sel.getSelection().size() == 1)); 
                && (type == SelectionHelper.MAP || type == SelectionHelper.ASDIAGRAM || type == SelectionHelper.GRLGRAPH || (type == SelectionHelper.URNSPEC) && (sel.getSelection().size() == 1));

    }

    /**
     * @return a {@link CreateMapCommand}
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        CreateASDiagramCommand create = new CreateASDiagramCommand(sel.getUrnspec());
        if (sel.getUrnspec()!=null && sel.getMap()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getMap())+1);
        else if (sel.getUrnspec()!=null && sel.getGrlgraph()!=null)
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getGrlgraph())+1);
        else if (sel.getUrnspec()!=null && sel.getASDiagram()!=null)        	
            create.setIndex(sel.getUrnspec().getUrndef().getSpecDiagrams().indexOf(sel.getASDiagram())+1);
        
        if (create.canExecute()) {
            DisplayPreferences.getInstance().setShowASDS(true);
        }
        return create;
    }
}