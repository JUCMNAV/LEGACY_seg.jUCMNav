package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.kpimodel.QualitativeMapping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.QualitativeMappingComponentEditPolicy;

/**
 * This class is the edit part for a KPI Conversion Qualitative Mapping.
 * 
 * @author jkealey
 * 
 */
public class QualitativeMappingTreeEditPart extends StrategyUrnModelElementTreeEditPart {

    /**
     * @param model
     *            The QualitativeMapping model
     */
    public QualitativeMappingTreeEditPart(QualitativeMapping model) {
        super(model);
    }

    /**
     * Listens to QualitativeMapping
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getQualitativeMapping().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new QualitativeMappingComponentEditPolicy());
    }

    /**
     * Stops listening to the QualitativeMapping.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getQualitativeMapping().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with the variable.
     */
    protected Image getImage() {
        if (super.getImage() == null) {
            setImage(JUCMNavPlugin.getImage("icons/urnlink-bidir.gif")); //$NON-NLS-1$
        }
        return super.getImage();
    }

    /**
     * QualitativeMappings have no children.  
     * 
     * @return empty list
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        return list;
    }

    /**
     * 
     * @return the QualitativeMapping
     */
    private QualitativeMapping getQualitativeMapping() {
        return (QualitativeMapping) getModel();
    }

    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getQualitativeMapping().getRealWorldLabel();
    }
}
