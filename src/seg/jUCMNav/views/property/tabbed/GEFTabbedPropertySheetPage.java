package seg.jUCMNav.views.property.tabbed;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.views.property.tabbed.mapper.IPropertyDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.UrnPropertyResolver;

public class GEFTabbedPropertySheetPage extends TabbedPropertySheetPage {

	protected UCMNavMultiPageEditor editor;
	protected IPropertyDataResolver dataResolver;
	protected IStructuredSelection selection;
	
	public GEFTabbedPropertySheetPage(
			UCMNavMultiPageEditor tabbedPropertySheetPageContributor) {
		super(tabbedPropertySheetPageContributor);
		editor = (UCMNavMultiPageEditor)tabbedPropertySheetPageContributor;
		dataResolver = new UrnPropertyResolver();
	}
	
	public UCMNavMultiPageEditor getMultiPageEditor()
	{
		return editor;
	}

	/**
	 * Get the EMF AdapterFactory for this editor.
	 * 
	 * @return the EMF AdapterFactory for this editor.
	 */
	public UrnEditor getEditor() {
		return (UrnEditor)editor.getActiveEditor();
	}

	public IPropertyDataResolver getDataResolver() {
		return dataResolver;
	}

	public IStructuredSelection getSelection() {
		return selection;
	}
}
