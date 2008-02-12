package seg.jUCMNav.views.preferences.staticSemantic;

import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.dialogs.Dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class RuleUtilityEditDialog extends Dialog {
    private String title;
    private String sUtilityExpression="";
    Text txtExpression;
    
    public RuleUtilityEditDialog(Shell parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }
    /**
     * @param parentShell
     */
    public RuleUtilityEditDialog(IShellProvider parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub
    }
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }    
    
    public void setText(String sExpression)
    {
        sUtilityExpression = sExpression;
    }
    public String getText()
    {
        return sUtilityExpression;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        Composite c1 = new Composite(parent,SWT.NULL);
        GridLayout layout1 = new GridLayout();
        layout1.numColumns = 1;
        c1.setLayout(layout1);
        
        txtExpression = new Text(c1,SWT.MULTI | SWT.BORDER | SWT.H_SCROLL|SWT.V_SCROLL);
        
        txtExpression.setText(this.sUtilityExpression);
        txtExpression.setLayoutData(new GridData( 600,200));
       
        return composite;
    }
    protected void okPressed() {
        this.sUtilityExpression = txtExpression.getText();
        super.okPressed();
    }
    
}
