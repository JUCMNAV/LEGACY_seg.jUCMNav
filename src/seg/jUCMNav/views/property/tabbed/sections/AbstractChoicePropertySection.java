package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractChoicePropertySection extends AbstractGEFPropertySection {

    protected CCombo combo;

    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        Composite parentComposite = getWidgetFactory().createFlatFormComposite(parent);

        combo = getWidgetFactory().createCCombo(parentComposite);
        combo.setItems(getList());

        combo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                itemSelected(combo.getSelectionIndex());
            }
        });

        CLabel nameLabel = getWidgetFactory().createCLabel(parentComposite, getLabelText());
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(combo, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(combo, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        data = new FormData();
        data.left = new FormAttachment(0, getStandardLabelWidth(parent, new String[] { getLabelText() }));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        combo.setLayoutData(data);
    }

    protected abstract void itemSelected(int index);

    protected abstract String[] getList();

    protected abstract void updateSelection();

    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);

        if (!combo.isDisposed())
            updateSelection();
    }

    public void refresh() {
        super.refresh();

        if (!combo.isDisposed())
            updateSelection();
    }
}
