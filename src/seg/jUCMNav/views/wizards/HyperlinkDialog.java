package seg.jUCMNav.views.wizards;

/**
 * Input dialog for a hyperlink (URL), with validation
 * 
 * @author damyot
 */

import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public class HyperlinkDialog extends Dialog {

    String value;

    /**
     * HyperlinkDialog constructor
     * 
     * @param parent
     *            the parent
     */
    public HyperlinkDialog(Shell parent) {
        super(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
    }

    /**
     * HyperlinkDialog constructor
     * 
     * @param parent
     *            the parent
     * @param stylethe
     *            style
     */
    public HyperlinkDialog(Shell parent, int style) {
        super(parent, style);
    }

    /**
     * Makes the dialog visible.
     * 
     * @return
     */
    public String open(String title, String message, String input) {
        // Create the dialog window
        Shell shell = new Shell(getParent(), getStyle());
        shell.setText(title);
        createContents(shell, message, input);
        shell.pack();
        shell.open();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        // Return the entered value, or null
        return value;
    }

    /**
     * Creates the dialog's contents
     * 
     * @param shell
     *            the dialog window
     */
    private void createContents(final Shell shell, String message, String input) {
        shell.setLayout(new GridLayout(2, true));

        // Show the message
        Label label = new Label(shell, SWT.NONE);
        label.setText(message);
        GridData data = new GridData();
        data.horizontalSpan = 2;
        label.setLayoutData(data);

        // Display the input box
        final Text text = new Text(shell, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        text.setLayoutData(data);
        text.setText(input);

        // Create the OK button and add a handler
        // so that pressing it will set input
        // to the entered value
        final Button ok = new Button(shell, SWT.PUSH);
        ok.setText("Ok"); //$NON-NLS-1$
        data = new GridData(GridData.FILL_HORIZONTAL);
        ok.setLayoutData(data);
        ok.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                value = text.getText();
                shell.close();
            }
        });

        // Validate input
        text.addListener(SWT.Modify, new Listener() {
            public void handleEvent(Event event) {
                try {
                    value = text.getText();
                    URL urlTest = new URL(value);
                    ok.setEnabled(true);
                } catch (Exception e) {
                    ok.setEnabled(false);
                }
            }
        });

        // Create the cancel button and add a handler
        // so that pressing it will set input to null
        Button cancel = new Button(shell, SWT.PUSH);
        cancel.setText(Messages.getString("IntegerInputRangeDialog.Cancel")); //$NON-NLS-1$

        data = new GridData(GridData.FILL_HORIZONTAL);
        cancel.setLayoutData(data);
        cancel.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                value = null;
                shell.close();
            }
        });

        // Set the OK button as the default, so
        // user can type input and press Enter
        // to dismiss
        shell.setDefaultButton(ok);
    }
}
