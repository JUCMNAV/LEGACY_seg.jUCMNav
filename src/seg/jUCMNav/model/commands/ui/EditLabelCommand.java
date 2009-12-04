package seg.jUCMNav.model.commands.ui;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.actions.palette.SelectPaletteEntryAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.LabelEditPart;

/***
 * This is not really a command but rather a utility to allow editing a node label without having to hit the escape key when the palette is selected. We had to
 * use a Command to make it work in the infrastructure, but there is no redo/undo logic.
 * 
 * @author jkealey
 * 
 */
public class EditLabelCommand extends Command {
    private UCMNavMultiPageEditor editor;
    private LabelEditPart labelPart;

    public EditLabelCommand(UCMNavMultiPageEditor editor, LabelEditPart labelPart) {
        this.editor = editor;
        this.labelPart = labelPart;
    }

    public void execute() {
        SelectPaletteEntryAction.selectTool(editor, null);
        labelPart.openDirectEditor();
    }
}
