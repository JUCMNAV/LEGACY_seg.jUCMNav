package seg.jUCMNav.editparts.concernsTreeEditparts;

import grl.GRLGraph;

import org.eclipse.gef.EditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Concern;

/**
 * TreeEditPartFactory associated with the Concerns Outline
 * 
 * @author gunterm
 */
public class ConcernsTreeEditPartFactory extends TreeEditPartFactory {

    /**
     * @param urn
     *            is the urnspec to display in the concern outline
     */
    public ConcernsTreeEditPartFactory(URNspec urn) {
        super(urn, false);
    }

    /**
     * @see seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor)
            return new ConcernsRootTreeEditPart((UCMNavMultiPageEditor) model);
        else if (model instanceof Concern)
            return new ConcernsConcernTreeEditPart((Concern) model);
        else if (model instanceof String)
            return new ConcernsLabelTreeEditPart((String) model, urn);
        else if (model instanceof UCMmap)
            return new ConcernsMapTreeEditPart((UCMmap) model);
        else if (model instanceof GRLGraph)
            return new ConcernsGrlGraphTreeEditPart((GRLGraph) model);
        else if (model instanceof Stub)
            return new ConcernsStubTreeEditPart((Stub) model);
        else
            return null;
    }

}