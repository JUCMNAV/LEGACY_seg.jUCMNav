package seg.jUCMNav.views.wizards;

import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import seg.jUCMNav.Messages;
import urncore.IURNDiagram;


public class URNDiagramIndexDialog extends ElementListSelectionDialog {
    
    protected Combo mapDropdown;

    public URNDiagramIndexDialog(Shell parent, ILabelProvider renderer) {
        super(parent, renderer);

        setTitle(Messages.getString("URNDiagramIndexDialog_ChangeOrder")); //$NON-NLS-1$
        setMessage(Messages.getString("URNDiagramIndexDialog_Insert")); //$NON-NLS-1$
    }

    @Override
    protected Control createContents(Composite parent) {
//        FillLayout la = new FillLayout();
//        
//        GridLayout layout = (GridLayout)parent.getLayout();
//        layout.numColumns = 1;
//        
//        Composite comboComp = new Composite(parent, SWT.NONE);
//        comboComp.setLayout(la);
//        
//        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
//        comboComp.setLayoutData(data);
//        
//        CLabel label = new CLabel(comboComp, SWT.None);
//        label.setText("Before/After:");
//        
//        Combo mapDropdown = new Combo(parent, SWT.DROP_DOWN | SWT.BORDER);
        
        return super.createContents(parent);
    }

    @Override
    protected void computeResult() {
        List<Object> result = new Vector<Object>();
        
        result.add(getSelectedElements()[0]);
        result.add(new Boolean(mapDropdown.getSelectionIndex() != 0));
        
        setResult(result);
        
        //super.computeResult();
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        
        return super.createDialogArea(parent);
    }

    @Override
    protected Label createMessageArea(Composite composite) {
        Label label = super.createMessageArea(composite);
        
        mapDropdown = new Combo(composite, SWT.DROP_DOWN | SWT.BORDER);
        mapDropdown.add(Messages.getString("URNDiagramIndexDialog_Before")); //$NON-NLS-1$
        mapDropdown.add(Messages.getString("URNDiagramIndexDialog_After")); //$NON-NLS-1$
        mapDropdown.select(0);
        
        return label;
    }
}
