package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.properties.UndoablePropertySheetEntry;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.views.property.StackHelper;
import seg.jUCMNav.views.property.tabbed.GEFTabbedPropertySheetPage;

public class AdvancedPropertySection extends org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection {

    GEFTabbedPropertySheetPage tabbed;

    public void createControls(Composite parent, TabbedPropertySheetPage atabbedPropertySheetPage) {
        super.createControls(parent, atabbedPropertySheetPage);

        tabbed = (GEFTabbedPropertySheetPage) atabbedPropertySheetPage;

        CommandStack stack = StackHelper.getDelegatingStack(tabbed);

        if (stack != null) {
            UndoablePropertySheetEntry root = new UndoablePropertySheetEntry(stack);
            page.setRootEntry(root);
        }
    }

}
