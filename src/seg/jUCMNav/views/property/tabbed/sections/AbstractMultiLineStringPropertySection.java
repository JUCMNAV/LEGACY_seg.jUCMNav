package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public abstract class AbstractMultiLineStringPropertySection extends AbstractStringPropertySection {

    protected boolean fireChangeOnEnter() {
        return false;
    }

    protected Text createText(Composite composite, String value) {
        Text text = getWidgetFactory().createText(composite, value, SWT.MULTI | SWT.WRAP);

        FormData data = new FormData();
        data.height = 75;
        data.width = composite.getBounds().width;
        data.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[] { getLabelText() }));
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        text.setLayoutData(data);

        return text;
    }
}
