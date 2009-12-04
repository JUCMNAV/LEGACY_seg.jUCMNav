package seg.jUCMNav.editors.palette;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;

public class UrnDropTargetListener extends TemplateTransferDropTargetListener {
    protected URNspec urn;

    public UrnDropTargetListener(EditPartViewer viewer, URNspec urn) {
        super(viewer);
        this.urn = urn;
    }

    public CreationFactory getFactory(Object template) {
        setEnablementDeterminedByCommand(true);

        if (template instanceof CreationFactory)
            return (CreationFactory) template;
        else
            return new ModelCreationFactory(urn, (Class) template);
    }
}