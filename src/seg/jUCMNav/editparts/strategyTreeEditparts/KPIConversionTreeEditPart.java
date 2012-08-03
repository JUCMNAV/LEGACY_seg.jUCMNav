package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.kpimodel.KPIConversion;
import grl.kpimodel.QualitativeMappings;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.KPIConversionComponentEditPolicy;

/**
 * This class is the edit part for a KPI Conversion.
 * 
 * @author jkealey
 * 
 */
public class KPIConversionTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The KPI conversion model
     */
    public KPIConversionTreeEditPart(KPIConversion model) {
        super(model);
    }

    /**
     * Listens to conversion
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getKPIConversion().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new KPIConversionComponentEditPolicy());
    }

    /**
     * Stops listening to the kpi conversion.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getKPIConversion().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the kpi conversion
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/urnlink-bidir.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * KPIConversions have no children, unless they are QualToQuanMappings. 
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        
        if (getKPIConversion() instanceof QualitativeMappings)
        {
            QualitativeMappings mappings = (QualitativeMappings) getKPIConversion();
            list.addAll(mappings.getMapping());
        }
        return list;
    }

    /**
     * 
     * @return the KPIConversion
     */
    private KPIConversion getKPIConversion() {
        return (KPIConversion) getModel();
    }

    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getKPIConversion().getName();
    }
}
