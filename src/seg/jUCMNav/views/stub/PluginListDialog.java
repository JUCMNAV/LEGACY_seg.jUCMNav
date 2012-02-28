package seg.jUCMNav.views.stub;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.figures.ColorManager;
import ucm.map.InBinding;
import ucm.map.OutBinding;
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
        setShellStyle(SWT.TOOL | SWT.BORDER | SWT.RESIZE);
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
        setWidthInChars(50);
        if (input != null)
            setHeightInChars(input.size());
        else
            setHeightInChars(15);
        Control toReturn = super.createDialogArea(container);
        toReturn.setBackground(ColorManager.WHITE);
        getTableViewer().getControl().setBackground(ColorManager.LIGHTGRAY);
        toReturn.pack();
        return toReturn;
    }

    protected Control createButtonBar(Composite parent) {

        return super.createButtonBar(parent);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed() {
        IStructuredSelection selection = (IStructuredSelection) getTableViewer().getSelection();
        if (selection.size() == 0)
            selection = new StructuredSelection(getTableViewer().getElementAt(0));
        UCMmap map = null;
        EObject selectedItem = (EObject) selection.toList().get(0);
        if (selectedItem instanceof PluginBinding) {
            map = ((PluginBinding) selection.toList().get(0)).getPlugin();
        } else if (selectedItem instanceof OutBinding) {
            OutBinding binding = (OutBinding) selection.toList().get(0);
            map = (UCMmap) binding.getBinding().getStub().getDiagram();

        } else if (selectedItem instanceof InBinding) {
            InBinding binding = (InBinding) selection.toList().get(0);
            map = (UCMmap) binding.getBinding().getStub().getDiagram();
        }

        if (map != null) {
            editor.setActivePage(map);

            GraphicalViewer viewer = ((UcmEditor) editor.getCurrentPage()).getGraphicalViewer();
            if (selectedItem instanceof InBinding) {
                InBinding binding = (InBinding) selectedItem;
                Vector v = new Vector();
                v.add(viewer.getEditPartRegistry().get(binding.getBinding().getStub()));
                v.add(viewer.getEditPartRegistry().get(binding.getStubEntry()));
                StructuredSelection sel = new StructuredSelection(v);
                viewer.setSelection(sel);
                
                //viewer.select((EditPart) viewer.getEditPartRegistry().get(binding.getBinding().getStub()));

            } else if (selectedItem instanceof OutBinding) {
                OutBinding binding = (OutBinding) selectedItem;
                //viewer.select((EditPart) viewer.getEditPartRegistry().get(binding.getBinding().getStub()));
                Vector v = new Vector();
                v.add(viewer.getEditPartRegistry().get(binding.getBinding().getStub()));
                v.add(viewer.getEditPartRegistry().get(binding.getStubExit()));
                StructuredSelection sel = new StructuredSelection(v);
                viewer.setSelection(sel);
            }

        }

        super.okPressed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {

        super.createButtonsForButtonBar(parent);
        // createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        // Button button = getButton(IDialogConstants.CANCEL_ID);
        // if (button != null)
        // button.setText(IDialogConstants.CLOSE_LABEL);

        parent.setBackground(ColorManager.WHITE);
        parent.getParent().setBackground(ColorManager.WHITE);

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

    public boolean isHelpAvailable() {
        return false;
    }
}