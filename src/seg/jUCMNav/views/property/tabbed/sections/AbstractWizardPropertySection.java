package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public abstract class AbstractWizardPropertySection extends AbstractGEFPropertySection {
    protected IWizardPage page;
    protected Composite parent;

    protected abstract IWizardPage createPage(Composite parent);

    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        this.parent = parent;

        this.page = createPage(parent);
    }
}