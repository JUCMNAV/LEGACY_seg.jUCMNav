


package seg.jUCMNav.views.wizards;

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 18, 2004 1:01:54 AM by JACK $Id$
 *  
 ******************************************************************************/

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;

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
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

public class DistributeCustomDialog extends JDialog {
    Integer value;
    Button btDistributeVertical;
    Button btDistributeHorizontal;
    HashMap<String, String> values;
    private Shell parent;
    private int lowRange, highRange;
    private Button buttonOK;

    /**
     * @param parent
     */
    public DistributeCustomDialog(Shell parent) {
        super();
        this.values = new HashMap<String, String>();
        this.parent = parent;
        super.setLocationRelativeTo(null);
        buttonOK = null;
    }

    /**
     * Makes the dialog visible.
     * 
     * @return
     */
    public HashMap<String, String> getValues() {
        lowRange = 0;
        highRange = 600;

        final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.CENTER);

        shell.setText(Messages.getString("DistributeCustomDialog.title"));
        shell.setLayout(new GridLayout(2, true));
        
        
        Label label = new Label(shell, SWT.NULL);
        label.setText(Messages.getString("DistributeCustomDialog.valueField"));
        
        final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);
        
        //Create the radio buttons.
        
        btDistributeHorizontal = new Button(shell, SWT.RADIO);
        btDistributeHorizontal.setText("Distribute Horizontally"); //$NON-NLS-1$
        btDistributeHorizontal.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
        btDistributeHorizontal.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                
            	values.put("DistributeType", "seg.jUCMNav.DistributeHorizontally");
                
                if(value != null){
           		 if (value.intValue() >= lowRange && value.intValue() <= highRange ){
           			 if( buttonOK != null)
           				 buttonOK.setEnabled(true);
           		 }
           	 }
            }
        });
        
        btDistributeVertical = new Button(shell, SWT.RADIO);
        btDistributeVertical.setText("Distribute Vertically"); //$NON-NLS-1$
        btDistributeVertical.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
        btDistributeVertical.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
            	 
            	values.put("DistributeType", "seg.jUCMNav.DistributeVertically");
            	
            	if(value != null){
            		 if (value.intValue() >= lowRange && value.intValue() <= highRange ){
            			 buttonOK.setEnabled(true);
            		 }
            	 }
            	
            }
        });
        
        final Button buttonOK = new Button(shell, SWT.PUSH);
        buttonOK.setText("Ok"); //$NON-NLS-1$
        buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        Button buttonCancel = new Button(shell, SWT.PUSH);
        buttonCancel.setText(Messages.getString("IntegerInputRangeDialog.Cancel")); //$NON-NLS-1$
        this.buttonOK = buttonOK;
        
        text.addListener(SWT.Modify, new Listener() {
        	
            public void handleEvent(Event event) {

                try {
                    value = new Integer(text.getText().trim());

                    if (value.intValue() >= lowRange && value.intValue() <= highRange &&
                    		values.get("DistributeType") != null) {
                    	
                        values.put("SpacingValue", String.valueOf(value));
                        GeneralPreferencePage.setDistributeSpacing(value);
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
                values = null;
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
        text.setText(GeneralPreferencePage.getDistributeSpacing() + "    "); //$NON-NLS-1$ 

        shell.pack();
        shell.open();
        shell.setDefaultButton(buttonOK);

        Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        return values;
    }

}