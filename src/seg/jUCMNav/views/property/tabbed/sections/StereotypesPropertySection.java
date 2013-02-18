package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;

import seg.jUCMNav.views.wizards.URNspec.StereotypeListPage;
import seg.jUCMNav.views.wizards.metadata.MetadataEditorPage;



public class StereotypesPropertySection extends MetadataPropertySection {

    @Override
    protected MetadataEditorPage createMetadataEditorPage(IStructuredSelection sel, EObject obj, EObject initialObject) {
        return new StereotypeListPage(sel, obj, initialObject != obj ? initialObject : null);
    }

}
