package seg.jUCMNav.editparts.kpiTreeEditparts;

import grl.GRLspec;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

/**
 * @author pchen
 * 
 */
public class KPITreeEditPartFactory implements EditPartFactory {

    // the urn spec being edited.
    protected URNspec urn;

    /**
     * @param urn
     *            the urnspec to display
     */
    public KPITreeEditPartFactory(URNspec urn) {
        this.urn = urn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof UCMNavMultiPageEditor) {
            return new KPIRootEditPart((UCMNavMultiPageEditor) model);
        } else if (model instanceof GRLspec) {
            return new GRLspecKPITreeEditPart((GRLspec) model);
        } else if (model instanceof IndicatorGroup) {
            return new IndicatorGroupTreeEditPart((IndicatorGroup) model);
        } else if (model instanceof Indicator) {
            return new IndicatorTreeEditPart((Indicator) model);
            // } else if (model instanceof IntentionalElementRef) {
            // return new IndicatorRefTreeEditPart((IntentionalElementRef) model);
        } else {
            return null;
        }
    }

}
