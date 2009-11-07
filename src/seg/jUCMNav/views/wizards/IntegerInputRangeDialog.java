package seg.jUCMNav.views.wizards;

/*******************************************************************************
 * All Right Reserved. Copyright (c) 1998, 2004 Jackwind Li Guojie
 * 
 * Created on Mar 18, 2004 1:01:54 AM by JACK $Id$
 *  
 ******************************************************************************/



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

public class IntegerInputRangeDialog extends Dialog
{
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
  public Integer open( String title, String prompt, String initialValue, int lowRange, int highRange )
  {
	  	IntegerInputRangeDialog.lowRange = lowRange;
	  	IntegerInputRangeDialog.highRange = highRange;
	  
	  	Shell parent = getParent();
	  	final Shell shell =
	  		new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
	  	shell.setText( title );
	  	
	  	shell.setLayout(new GridLayout(2, true));

	  	Label label = new Label(shell, SWT.NULL);
	  	label.setText( prompt );

	  	final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

	  	final Button buttonOK = new Button(shell, SWT.PUSH);
	  	buttonOK.setText("Ok");
	  	buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	  	Button buttonCancel = new Button(shell, SWT.PUSH);
	  	buttonCancel.setText("Cancel");

	  	text.addListener(SWT.Modify, new Listener() {
	  		public void handleEvent(Event event) {
	  			try {
	  				value = new Integer( text.getText().trim() );
          
	  				if ( value.intValue() >= IntegerInputRangeDialog.lowRange && value.intValue() <= IntegerInputRangeDialog.highRange )
	  					buttonOK.setEnabled(true);
	  				else
	  					buttonOK.setEnabled(false);
	  			} catch (Exception e) {
	  				buttonOK.setEnabled(false);
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
	  			if(event.detail == SWT.TRAVERSE_ESCAPE)
	  				event.doit = false;
	  		}
	  	});

	  	text.setText( initialValue + "       " ); // add extra spaces so text entry is not too narrow
	  	
	  	System.out.println ( text.getBounds() );
	  	shell.pack();
	  	shell.open();

	  	Display display = parent.getDisplay();
	  	while (!shell.isDisposed()) {
	  		if (!display.readAndDispatch())
	  			display.sleep();
	  	}

	  	return value;
  	}

 // public static void main(String[] args) {
 //   Shell shell = new Shell();
 //   IntegerInputRangeDialog dialog = new IntegerInputRangeDialog(shell);
 //   int iv = 23;
 //   System.out.println( dialog.open( "Enter Numerical Evaluation", "Enter the new Numerical Evaluation [-100,100]: ", Integer.toString( iv ), -100, 100 ) );
 // }
}

