package seg.jUCMNav.importexport.reports.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog showing the error message caught in application
 * 
 * 
 * @author dessure
 * 
 */
public class jUCMNavErrorDialog {

    Display display = new Display();
    Shell shell = new Shell(display);

    Button button;

    /**
     * display the message box
     * 
     * @param errorMessage
     *            the error message coming from the exception
     * 
     */
    public jUCMNavErrorDialog(final String errorMessage) {
    	
    	// need syncExec as it is called from non-UI wizard threads
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
		        MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);

		        messageBox.setText("Warning"); //$NON-NLS-1$
		        messageBox.setMessage(errorMessage);
		        messageBox.open();
			}
		});
    }

}
