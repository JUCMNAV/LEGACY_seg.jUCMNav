package seg.jUCMNav.views.property.tabbed;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabContents;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.views.property.StackHelper;
import seg.jUCMNav.views.property.tabbed.mapper.IPropertyDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.UrnPropertyResolver;
import seg.jUCMNav.views.property.tabbed.sections.AbstractGEFPropertySection;

public class GEFTabbedPropertySheetPage extends TabbedPropertySheetPage implements CommandStackListener {

    protected UCMNavMultiPageEditor editor;
    protected IPropertyDataResolver dataResolver;
    protected IStructuredSelection selection;

    protected HashMap maxLabelWidth = new HashMap();
    protected HashMap sectionToTab = new HashMap();
    protected Vector sectionsToRefresh = new Vector();

    public GEFTabbedPropertySheetPage(UCMNavMultiPageEditor tabbedPropertySheetPageContributor) {
        super(tabbedPropertySheetPageContributor);
        editor = (UCMNavMultiPageEditor) tabbedPropertySheetPageContributor;
        dataResolver = new UrnPropertyResolver();
    }

    public UCMNavMultiPageEditor getMultiPageEditor() {
        return editor;
    }

    public int getLabelWidth(AbstractGEFPropertySection section, Composite parent, String[] labels) {
        int standardLabelWidth = AbstractPropertySection.STANDARD_LABEL_WIDTH;

        String stringWidth = (String) maxLabelWidth.get(sectionToTab.get(section));

        GC gc = new GC(parent);
        int indent = gc.textExtent("XXX").x; //$NON-NLS-1$

        int width = gc.textExtent(stringWidth).x;
        if (width + indent > standardLabelWidth) {
            standardLabelWidth = width + indent;
        } else
            standardLabelWidth = Math.max(width + indent, standardLabelWidth);

        gc.dispose();

        return standardLabelWidth;
    }

    /**
     * Get the EMF AdapterFactory for this editor.
     * 
     * @return the EMF AdapterFactory for this editor.
     */
    public UrnEditor getEditor() {
        return (UrnEditor) editor.getActiveEditor();
    }

    public IPropertyDataResolver getDataResolver() {
        return dataResolver;
    }

    public IStructuredSelection getSelection() {
        return selection;
    }

    protected TabContents createTab(ITabDescriptor tabDescriptor) {
        TabContents content = super.createTab(tabDescriptor);

        ISection[] sections = content.getSections();
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] instanceof AbstractGEFPropertySection) {
                AbstractGEFPropertySection current = (AbstractGEFPropertySection) sections[i];

                if (!maxLabelWidth.containsKey(tabDescriptor.getId()))
                    maxLabelWidth.put(tabDescriptor.getId(), ""); //$NON-NLS-1$

                sectionToTab.put(current, tabDescriptor.getId());

                if (current.getLabelText().length() > ((String) maxLabelWidth.get(tabDescriptor.getId())).length()) {
                    maxLabelWidth.put(tabDescriptor.getId(), current.getLabelText());
                }
            }
        }

        return content;
    }

    protected void updateTabs(ITabDescriptor[] descriptors) {
        maxLabelWidth.clear();
        sectionToTab.clear();

        super.updateTabs(descriptors);
    }

    public void commandStackChanged(EventObject e) {
        for (Iterator iterator = sectionsToRefresh.iterator(); iterator.hasNext();) {
            AbstractGEFPropertySection type = (AbstractGEFPropertySection) iterator.next();
            type.refresh();
        }
    }

    public void addSectionToRefresh(AbstractGEFPropertySection section) {
        sectionsToRefresh.add(section);

        if (sectionsToRefresh.size() == 1) {
            CommandStack stack = StackHelper.getStack(this);
            if (stack != null)
                stack.addCommandStackListener(this);

        }
    }

    public void dispose() {

        CommandStack stack = StackHelper.getStack(this);
        if (stack != null)
            stack.removeCommandStackListener(this);

        editor = null;
        dataResolver = null;
        selection = null;
        maxLabelWidth = null;
        sectionToTab = null;
        tabListContentProvider = null;

        sectionsToRefresh = null;

        super.dispose();
    }
}
