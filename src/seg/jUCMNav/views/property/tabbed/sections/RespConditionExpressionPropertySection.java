package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;

public class RespConditionExpressionPropertySection extends ConditionExpressionPropertySection {

    protected Text createText(Composite parent) {
        Text result = getWidgetFactory().createText(parent, "", SWT.MULTI | SWT.WRAP); //$NON-NLS-1$

        result.setEnabled(false);

        GridData gridData = new GridData();
        gridData.heightHint = 75;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.horizontalIndent = 2;
        gridData.verticalAlignment = GridData.CENTER;

        result.setLayoutData(gridData);

        return result;
    }

    public String getLabelText() {
        return Messages.getString("RespConditionExpressionPropertySection_Code"); //$NON-NLS-1$
    }
}
