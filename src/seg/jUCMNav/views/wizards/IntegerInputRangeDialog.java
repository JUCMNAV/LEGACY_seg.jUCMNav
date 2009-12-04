package seg.jUCMNav.views.wizards;

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 18, 2004 1:01:54 AM by JACK $Id$
 *  
 ******************************************************************************/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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

public class IntegerInputRangeDialog extends Dialog {
    Integer value;
    public static int lowRange, highRange;

    /**
     * @param parent
     */
    public IntegerInputRangeDialog(Shell parent) {
        super(parent);
    }

    /**
     * @param parent
     * @param style
     */
    public IntegerInputRangeDialog(Shell parent, int style) {
        super(parent, style);
    }

    /**
     * Makes the dialog visible.
     * 
     * @return
     */
    public Integer open(String title, String prompt, String initialValue, int lowRange, int highRange) {
        IntegerInputRangeDialog.lowRange = lowRange;
        IntegerInputRangeDialog.highRange = highRange;

        Shell parent = getParent();
        final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.CENTER);

        shell.setText(title);
        shell.setLayout(new GridLayout(2, true));

        Label label = new Label(shell, SWT.NULL);
        label.setText(prompt);

        final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

        final Button buttonOK = new Button(shell, SWT.PUSH);
        buttonOK.setText("Ok"); //$NON-NLS-1$
        buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        Button buttonCancel = new Button(shell, SWT.PUSH);
        buttonCancel.setText(Messages.getString("IntegerInputRangeDialog.Cancel")); //$NON-NLS-1$

        text.addListener(SWT.Modify, new Listener() {

            public void handleEvent(Event event) {

                try {
                    value = new Integer(text.getText().trim());

                    if (value.intValue() >= IntegerInputRangeDialog.lowRange && value.intValue() <= IntegerInputRangeDialog.highRange) {
                        buttonOK.setEnabled(true);
                    } else
                        buttonOK.setEnabled(false);
                } catch (Exception e) {
                    buttonOK.setEnabled(false);
                }
            }
        });

        text.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent ke) {
                // TODO Auto-generated method stub
            }

            public void keyReleased(KeyEvent ke) {
                if (ke.character == SWT.CR) {
                    if (buttonOK.isEnabled())
                        shell.dispose();
                }
            }
        });

        buttonOK.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                shell.dispose();
            }
        });

        buttonCancel.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                value = null;
                shell.dispose();
            }
        });

        shell.addListener(SWT.Traverse, new Listener() {
            public void handleEvent(Event event) {
                if (event.detail == SWT.TRAVERSE_ESCAPE)
                    event.doit = false;
            }
        });

        // Add extra spaces so text entry is not too narrow
        text.setText(initialValue + "       "); //$NON-NLS-1$ 

        shell.pack();
        shell.open();
        shell.setDefaultButton(buttonOK);

        Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        return value;
    }

}
