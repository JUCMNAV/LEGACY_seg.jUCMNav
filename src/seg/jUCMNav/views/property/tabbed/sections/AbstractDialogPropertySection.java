package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractDialogPropertySection extends AbstractGEFPropertySection {

    protected Text text;

    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        Composite parentComposite = getWidgetFactory().createFlatFormComposite(parent);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.horizontalSpacing = 0;

        parentComposite.setLayout(gridLayout);

        CLabel nameLabel = getWidgetFactory().createCLabel(parentComposite, getLabelText(), SWT.WRAP | SWT.MULTI);

        GridData gridData = new GridData();
        gridData.widthHint = getStandardLabelWidth(parentComposite, new String[] { getLabelText() });
        nameLabel.setLayoutData(gridData);

        text = createText(parentComposite);

        Button b = new Button(parentComposite, SWT.NULL);
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent arg0) {
                openDialog();
            }
        });
        b.setText("..."); //$NON-NLS-1$
    }

    protected Text createText(Composite parent) {
        Text result = getWidgetFactory().createText(parent, ""); //$NON-NLS-1$
        result.setEnabled(false);

        GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.horizontalIndent = 2;

        result.setLayoutData(gridData);

        return result;
    }

    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        resolve(eObject);

        if (text != null) {
            if (!text.isDisposed())
                text.setText(getText());
        }
    }

    public void refresh() {
        super.refresh();

        if (text != null) {
            if (!text.isDisposed())
                text.setText(getText());
        }
    }

    protected abstract void openDialog();

    protected abstract Object resolve(Object obj);

    protected abstract String getText();
}
