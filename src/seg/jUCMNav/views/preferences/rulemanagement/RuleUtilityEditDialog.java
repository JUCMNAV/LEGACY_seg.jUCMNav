package seg.jUCMNav.views.preferences.rulemanagement;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class provides the GUI of creating a new rule utility or editing an existing utility.
 * 
 * @author Byrne Yan
 * 
 */
public class RuleUtilityEditDialog extends Dialog {
    /**
     * The title of the dialog
     */
    private String title;
    /**
     * The utility expression
     */
    private String sUtilityExpression = ""; //$NON-NLS-1$
    Text txtExpression;

    public RuleUtilityEditDialog(Shell parent) {
        super(parent);
    }

    public RuleUtilityEditDialog(IShellProvider parentShell) {
        super(parentShell);
    }

    /**
     * Set the title of the dialog
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }

    /**
     * Set the utility expression
     */
    public void setText(String sExpression) {
        sUtilityExpression = sExpression;
    }

    /**
     * Returns the utility expression
     */
    public String getText() {
        return sUtilityExpression;
    }

    /**
     * Set the title of dialog. This method must be called before the dialog is created.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Create all GUI components.
     */
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        Composite c1 = new Composite(parent, SWT.NULL);
        GridLayout layout1 = new GridLayout();
        layout1.numColumns = 1;
        c1.setLayout(layout1);

        txtExpression = new Text(c1, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);

        txtExpression.setText(this.sUtilityExpression);
        txtExpression.setLayoutData(new GridData(600, 200));

        return composite;
    }

    /**
     * Close the dialog with saving the utility expression.
     */
    protected void okPressed() {
        this.sUtilityExpression = txtExpression.getText();
        super.okPressed();
    }

}
