package seg.jUCMNav.views.wizards.metadata;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;

public class MetadataEntryDialog extends Dialog {
    Shell shell;
    Shell parent;
    String[] values;
    String[] labels;

    private final int BUTTON_WIDTH = 10;
    private final int LABEL_WIDTH = 0;
    
    public MetadataEntryDialog(Shell _parent) {
        super(_parent);

        parent = _parent;
        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL );
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        shell.setLayout(layout);
    }

    protected void addTextListener(final Text text) {
        text.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                Integer index = (Integer) (text.getData("index")); //$NON-NLS-1$
                values[index.intValue()] = text.getText();
            }
        });
    }

    protected void createControls() {
		Point size;
		GridData gridData = null;

        if (labels == null)
            return;

        if (values == null)
            values = new String[labels.length];

        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(shell, SWT.RIGHT);
            label.setText(labels[i] + ": "); //$NON-NLS-1$
            size = label.computeSize( SWT.DEFAULT, SWT.DEFAULT );
            gridData = new GridData();
            gridData.widthHint = size.x + LABEL_WIDTH;
            label.setLayoutData(gridData);

            Text text = new Text(shell, SWT.BORDER);
            gridData = new GridData();
            gridData.widthHint = 320;
            gridData.grabExcessHorizontalSpace = true;
            text.setLayoutData(gridData);
            if (values[i] != null) {
                text.setText(values[i]);
            }
            text.setData("index", new Integer(i)); //$NON-NLS-1$
            addTextListener(text);
        }

        Composite buttonComp = new Composite(shell, SWT.NONE);
        gridData = new GridData();
        gridData.horizontalSpan = 2;
        buttonComp.setLayoutData(gridData);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        buttonComp.setLayout(layout);

        Button okButton = new Button(buttonComp, SWT.PUSH);
        okButton.setText(Messages.getString("MetadataEditorPage.button_ok")); //$NON-NLS-1$
		size = okButton.computeSize( SWT.DEFAULT, SWT.DEFAULT );

        gridData = new GridData();
        gridData.widthHint = size.x + BUTTON_WIDTH;
        okButton.setLayoutData(gridData);
        okButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });

        Button cancelButton = new Button(buttonComp, SWT.PUSH);
        cancelButton.setText(Messages.getString("MetadataEditorPage.button_cancel")); //$NON-NLS-1$
		size = cancelButton.computeSize( SWT.DEFAULT, SWT.DEFAULT );
        gridData = new GridData();
        gridData.widthHint = size.x + BUTTON_WIDTH;
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                values = null;
                shell.close();
            }
        });

        shell.setDefaultButton(okButton);
    }

    public String[] getLabels() {
        return labels;
    }

    public String getTitle() {
        return shell.getText();
    }

    /**
     * Returns the contents of the <code>Text</code> widgets in the dialog in a <code>String</code> array.
     * 
     * @return String[] The contents of the text widgets of the dialog. May return null if all text widgets are empty.
     */
    public String[] getValues() {
        return values;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public void setTitle(String title) {
        shell.setText(title);
    }

    /**
     * Sets the values of the <code>Text</code> widgets of the dialog to the values supplied in the parameter array.
     * 
     * @param itemInfo
     *            String[] The values to which the dialog contents will be set.
     */
    public void setValues(String[] itemInfo) {
        if (labels == null)
            return;

        if (values == null)
            values = new String[labels.length];

        int numItems = Math.min(values.length, itemInfo.length);
        for (int i = 0; i < numItems; i++) {
            values[i] = itemInfo[i];
        }
    }

    /**
     * Opens the dialog in the given state. Sets <code>Text</code> widget contents and dialog behaviour accordingly.
     * 
     * @return values of the edited metadata
     */
    public String[] open() {
        createControls();

        int width = 340;
        int height = 125;
        
        shell.setBounds(parent.getBounds().x + parent.getBounds().width / 3, parent.getBounds().y + parent.getBounds().height / 3, width, height);
        shell.pack();
        shell.open();

        Display display = shell.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        return getValues();
    }
}
