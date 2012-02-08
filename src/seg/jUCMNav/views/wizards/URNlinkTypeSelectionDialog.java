package seg.jUCMNav.views.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

public class URNlinkTypeSelectionDialog extends Dialog {
	
	private Combo modeSelection = null;
	private Label entryLabel = null;
	private Text typeEntry = null;
	private Label listLabel = null;
	private List typesList = null;
	private String title, message, initialType, acceptLabel, cancelLabel;
	
	final private int minWidth = 300, EXISTING_TYPE = 0, NEW_TYPE = 1;
	private int currentMode;
	public static String linkType = null;
	
	public URNlinkTypeSelectionDialog( Shell parent, String title, String message, String initialType, String acceptLabel, String cancelLabel )
	{
		super(parent);
		this.title = title;
		this.message = message;
		this.initialType = initialType;
		this.acceptLabel = acceptLabel;
		this.cancelLabel = cancelLabel;
	}

	public String open()
	{
		FormData formData;
		Point size;

		String [] modes = { "Select existing URN Link Type", "Specify New URN Link Type" };
		String [] types = { "Traceability", "Refinement", "Compliance" };
		
        Shell parent = getParent();
        final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.CENTER | SWT.RESIZE);

        shell.setText(title);
        
		FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = 3;
        formLayout.marginHeight = 3;
        shell.setLayout( formLayout );
        
        Label messageLabel = new Label( shell, SWT.LEFT );
        messageLabel.setText(message);
        
		size = messageLabel.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( 0, 5 );
		messageLabel.setLayoutData( formData );

		modeSelection = new Combo( shell, SWT.DROP_DOWN|SWT.READ_ONLY );
		modeSelection.setItems( modes );
		modeSelection.addSelectionListener( new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		    	  setMode();
		      }

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		size = modeSelection.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( messageLabel, 5 );
		modeSelection.setLayoutData( formData );

		entryLabel = new Label( shell, SWT.LEFT );
		entryLabel.setText( "Please enter a new Link Type" );
		
		size = entryLabel.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( modeSelection, 5 );
		entryLabel.setLayoutData( formData );

		typeEntry = new Text( shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
		size = typeEntry.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( entryLabel, 5 );
		formData.right = new FormAttachment( 100, -5 );
		typeEntry.setLayoutData( formData );

		
		listLabel = new Label( shell, SWT.LEFT );
		listLabel.setText( "URN Link Types" );
		
		size = listLabel.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x, size.y );
		formData.left = new FormAttachment( 0, 5 );
		formData.top = new FormAttachment( typeEntry, 5 );
		listLabel.setLayoutData( formData );
		
		typesList = new List( shell, SWT.SINGLE | SWT.BORDER );
		typesList.setItems(types);

		Button cancelButton = new Button(shell, SWT.PUSH);
		
		formData = new FormData( minWidth, 5*size.y );
		formData.top = new FormAttachment( listLabel, 5 );
		formData.left = new FormAttachment( 0, 5 );
		formData.right = new FormAttachment( 100, -5 );
		formData.bottom = new FormAttachment( cancelButton, -5 );
		typesList.setLayoutData( formData );
		
		typesList.addMouseListener( new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
		    	  setType();
		    	  shell.close();
			}
			public void mouseDown(MouseEvent e) {}
			public void mouseUp(MouseEvent e) {}
		});

        Button OK_Button = new Button(shell, SWT.PUSH);
        OK_Button.setText( acceptLabel );
		
		size = OK_Button.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x+8, size.y );
		formData.right = new FormAttachment( 100, -5 );
		formData.bottom = new FormAttachment( 100, -5 );
		OK_Button.setLayoutData( formData );

		OK_Button.addSelectionListener( new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		    	  setType();
		    	  shell.close();
		      }

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});		
		
		cancelButton.setText( cancelLabel );
		size = cancelButton.computeSize( SWT.DEFAULT, SWT.DEFAULT );
		formData = new FormData( size.x+8, size.y );
		formData.right = new FormAttachment( OK_Button, -5 );
		formData.bottom = new FormAttachment( 100, -5 );
		cancelButton.setLayoutData( formData );
        
		cancelButton.addSelectionListener( new SelectionListener() {
		      public void widgetSelected(SelectionEvent e) {
		    	  linkType = null;
		    	  shell.close();
		      }

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		modeSelection.select( 0 );
		this.setMode();
		
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

		return linkType;
	}
	
	private void setMode()
	{
		currentMode = modeSelection.getSelectionIndex();
		switch( currentMode ) {
		case EXISTING_TYPE:
			entryLabel.setEnabled( false );
			typeEntry.setEnabled( false );
			listLabel.setEnabled( true );
			typesList.setEnabled( true );
			break;
		case NEW_TYPE:
			entryLabel.setEnabled( true );
			typeEntry.setEnabled( true );
			listLabel.setEnabled( false );
			typesList.setEnabled( false );
			break;
		}
	}
	
	private void setType()
	{
		switch( currentMode ) {
		case EXISTING_TYPE:
			linkType = new String( (typesList.getSelection())[0] );
			break;
		case NEW_TYPE:
			linkType = new String( typeEntry.getText() );
			break;
		}		
	}
	
	public static void main(String[] args) {
		
		URNlinkTypeSelectionDialog testDialog = new URNlinkTypeSelectionDialog( new Shell(), "Specify URN Link Type", "Create New Link or Modify Existing Link's Type",
				"", "Accept", "Cancel" );
		String response = testDialog.open();
		System.out.println( "The selection was \"" + response + "\"." );
	}

}
