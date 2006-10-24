package seg.jUCMNav.views.stub;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.figures.ColorManager;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;

/**
 * Dialog listing all the PluginBindings for the given dynamic stub.
 * 
 * @author Etienne Tremblay
 */
public class PluginListDialog extends ListDialog {

    private List input;

    private UCMNavMultiPageEditor editor;

    /**
     * @param parent
     * @param editor
     */
    public PluginListDialog(Shell parent, UCMNavMultiPageEditor editor) {
        super(parent);
        setShellStyle(SWT.TOOL | SWT.BORDER);
        this.setContentProvider(new PluginContentProvider());
        this.setLabelProvider(new PluginLabelProvider());
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.dialogs.ListDialog#setInput(java.lang.Object)
     */
    public void setInput(Object input) {
        this.input = (List) input;
        super.setInput(input);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite container) {
        container.setBackground(ColorManager.WHITE);
        setWidthInChars(30);
        if (input != null)
            setHeightInChars(input.size());
        else
            setHeightInChars(10);
        Control toReturn = super.createDialogArea(container);
        toReturn.setBackground(ColorManager.WHITE);
        getTableViewer().getControl().setBackground(ColorManager.LIGHTGRAY);
        return toReturn;
    }

    protected Control createButtonBar(Composite parent) {
        return parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) getTableViewer().getSelection();
        UCMmap map = ((PluginBinding) selection.toList().get(0)).getPlugin();
        if (map != null)
            editor.setActivePage(map);

        super.okPressed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        parent.setVisible(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.dialogs.SelectionDialog#createMessageArea(org.eclipse.swt.widgets.Composite)
     */
    protected Label createMessageArea(Composite composite) {
        Label toReturn = super.createMessageArea(composite);
        toReturn.setBackground(ColorManager.WHITE);
        return toReturn;
    }
}