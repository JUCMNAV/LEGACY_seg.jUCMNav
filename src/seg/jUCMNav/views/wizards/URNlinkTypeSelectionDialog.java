package seg.jUCMNav.views.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;

public class URNlinkTypeSelectionDialog extends Dialog {

	public URNlinkTypeSelectionDialog(Shell parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	public String open( String title, String message, String initialType )
	{
		FormData formData;
		Point size;

		final int minWidth = 300;

		String [] modes = { "Select existing URN Link Type", "Specify New URN Link Type" };
		String [] types = { "Traceability", "Refinement", "Compliance" };
		
        Shell parent = getParent();
        final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.CENTER | SWT.RESIZE);

        shell.setText(title);
        
		FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = 3;
        formLayout.marginHeight = 3;
        shell.setLayout( formLayout );

		final Combo modeSelection = new Combo( shell, SWT.DROP_DOWN|SWT.READ_ONLY );
		modeSelection.setItems( modes );
		modeSelection.select( 0 );
		
		size = modeSelection.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( 0, 5 );
		modeSelection.setLayoutData( formData );

		final Label entryLabel = new Label( shell, SWT.LEFT );
		entryLabel.setText( "Please enter a new Link Type" );
		
		size = entryLabel.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( modeSelection, 5 );
		entryLabel.setLayoutData( formData );

		final Text typeEntry = new Text( shell, SWT.SINGLE|SWT.LEFT|SWT.BORDER );

		size = typeEntry.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( entryLabel, 5 );
		formData.right = new FormAttachment( 100, -5 );
		typeEntry.setLayoutData( formData );

		
		final Label listLabel = new Label( shell, SWT.LEFT );
		listLabel.setText( "URN Link Types" );
		
		size = listLabel.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( typeEntry, 5 );
		listLabel.setLayoutData( formData );
		
		final List typesList = new List( shell, SWT.SINGLE|SWT.BORDER );
		typesList.setItems(types);

		final Button cancelButton = new Button(shell, SWT.PUSH);
		
		formData = new FormData( minWidth, 5*size.y );
		formData.top = new FormAttachment( listLabel, 5 );
		formData.left = new FormAttachment( 0, 5 );
		formData.right = new FormAttachment( 100, -5 );
		formData.bottom = new FormAttachment( cancelButton, -5 );
		typesList.setLayoutData( formData );

		
        cancelButton.setText("Cancel"); //$NON-NLS-1$

		size = cancelButton.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x+8, size.y );
		formData.right = new FormAttachment( 100, -5 );
		formData.bottom = new FormAttachment( 100, -5 );
		cancelButton.setLayoutData( formData );
        
        final Button OK_Button = new Button(shell, SWT.PUSH);
        OK_Button.setText("Ok"); //$NON-NLS-1$
		
		size = OK_Button.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x+8, size.y );
		formData.right = new FormAttachment( cancelButton, -5 );
		formData.bottom = new FormAttachment( 100, -5 );
		OK_Button.setLayoutData( formData );

		shell.pack();
		
		final Point minSize = shell.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		shell.setMinimumSize(minSize);
		shell.setSize(minSize);

		shell.open();
		
        Display display = parent.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		URNlinkTypeSelectionDialog testDialog = new URNlinkTypeSelectionDialog( new Shell() );
		testDialog.open( "Specify URN Link Type", "", "" );

	}

}
