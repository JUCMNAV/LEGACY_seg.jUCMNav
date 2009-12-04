package seg.jUCMNav.actions;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.palette.SelectPaletteEntryAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import ucm.map.StartPoint;
import urn.URNspec;

public class AddStartPointAction extends URNSelectionAction {
    public final static String ADDSTART = "seg.jUCMNav.AddStart";//$NON-NLS-1$

    protected UCMNavMultiPageEditor editor;

    public AddStartPointAction(UCMNavMultiPageEditor part) {
        super(part);
        this.editor = part;
        setId(ADDSTART);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Start16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we select a map.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
            return true;
        }
        return false;
    }

    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        StartPoint newPn = getNewPathNode(sel.getUrnspec());
        Point pos = Display.getCurrent().getCursorLocation();
        if (editor.getCurrentPage() != null)
            pos = editor.getCurrentPage().getGraphicalViewer().getControl().toControl(pos);

        return new CreatePathCommand(sel.getMap(), newPn, pos.x, pos.y);
    }

    /**
     * @param urn
     * @return the StartPoint to be inserted.
     */
    protected StartPoint getNewPathNode(URNspec urn) {
        return (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);
    }

    /**
     * Executes the command returned by getCommand();
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        CreatePathCommand cmd = (CreatePathCommand) getCommand();
        execute(cmd);

        if (editor.getCurrentPage() != null) {
            GraphicalViewer viewer = editor.getCurrentPage().getGraphicalViewer();
            StructuredSelection sel = new StructuredSelection(viewer.getEditPartRegistry().get(cmd.getEnd()));
            viewer.setSelection(sel);
        }

        autoDirectEdit(cmd);

        SelectPaletteEntryAction.selectTool(editor, " "); //$NON-NLS-1$
    }
}
