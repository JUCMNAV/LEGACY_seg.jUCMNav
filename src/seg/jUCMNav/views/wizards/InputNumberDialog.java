package seg.jUCMNav.views.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;

public class InputNumberDialog extends Dialog {

    Shell parent;
    Display display;
    String promptedFor;
    String initialValue;
    Shell shell;
    Text inputNumber;
    Button btOK;
    Button btCancel;
    Double value;

    public InputNumberDialog(Shell parent, String promptFor, String initVal) {
        super(parent);
        this.parent = parent;
        this.display = parent.getDisplay();
        this.promptedFor = promptFor;
        this.initialValue = initVal;
    }

    public Double getValue() {
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setText(Messages.getString("InputNumberDialog.InputNumber")); //$NON-NLS-1$
        shell.setLayout(new GridLayout(2, false));

        Label label = new Label(shell, SWT.NULL);
        label.setText(promptedFor + ":"); //$NON-NLS-1$
        label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

        inputNumber = new Text(shell, SWT.SINGLE | SWT.BORDER);
        inputNumber.setText(initialValue);
        GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
        gd1.widthHint = 50;
        inputNumber.setLayoutData(gd1);

        btOK = new Button(shell, SWT.PUSH);
        btOK.setText("Ok"); //$NON-NLS-1$
        btOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        btOK.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });

        btCancel = new Button(shell, SWT.PUSH);
        btCancel.setText(Messages.getString("InputNumberDialog.Cancel")); //$NON-NLS-1$
        btCancel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
        btCancel.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                value = null;
                shell.dispose();
            }
        });

        inputNumber.addListener(SWT.Modify, new Listener() {
            public void handleEvent(Event event) {
                try {
                    value = new Double(inputNumber.getText());
                    btOK.setEnabled(true);
                } catch (Exception e) {
                    btOK.setEnabled(false);
                }
            }
        });

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        return value;
    }
}
