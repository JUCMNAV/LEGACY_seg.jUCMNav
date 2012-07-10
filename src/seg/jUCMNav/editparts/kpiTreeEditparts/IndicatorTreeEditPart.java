package seg.jUCMNav.editparts.kpiTreeEditparts;

import grl.Evaluation;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIEvalValueSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.IntentionalElementComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.IndicatorPropertySource;

/**
 * @author pchen
 * 
 */
public class IndicatorTreeEditPart extends KPIUrnModelElementTreeEditPart {
    private boolean selected;

    /**
     * @param model
     */
    public IndicatorTreeEditPart(Indicator model) {
        super(model);
        selected = false;
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new IntentionalElementComponentEditPolicy());
    }

    public Indicator getIndicator() {
        return (Indicator) getModel();
    }

    /**
     * Returns the icon
     */
    protected Image getImage() {
        String iconFile = "icons/indicator.gif"; //$NON-NLS-1$

        EvaluationStrategyManager sm = EvaluationStrategyManager.getInstance();
        if (sm.getEvaluationStrategy() != null) {
            Evaluation eval = sm.getEvaluationObject(getNode());
            int evalLevel = eval.getEvaluation();
            KPIEvalValueSet kpiEvalValueSet = sm.getActiveKPIEvalValueSet(getNode());
            if (evalLevel > 0) {
                iconFile = "icons/indicator_up.gif"; //$NON-NLS-1$
            } else if (evalLevel < 0) {
                iconFile = "icons/indicator_down.gif"; //$NON-NLS-1$
            } else {
                iconFile = "icons/indicator_flat.gif"; //$NON-NLS-1$
            }
        } else {
            iconFile = "icons/indicator.gif"; //$NON-NLS-1$
        }

        setImage(JUCMNavPlugin.getImage(iconFile));

        return super.getImage();
    }

    // If selected, set the element in bold.
    public void setSelected(boolean selected) {
        // bug 411
        if (widget == null)
            return;
        this.selected = selected;
        if (selected) {
            ((TreeItem) widget).setBackground(ColorManager.LIGHTGRAY);
        } else {
            ((TreeItem) widget).setBackground(ColorManager.WHITE);
        }
        // refreshVisuals();
    }

    /**
     * @return the sorted list of Indicators
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();

        // The list of IntentionalElementRef of Indicator is not showed now
        // list.addAll(getIndicator().getRefs());

        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /**
     * Returns a IntentionalElementPropertySource
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#getPropertySource()
     */
    protected IPropertySource getPropertySource() {
        if (propertySource == null)
            propertySource = new IndicatorPropertySource((Indicator) getModel());

        return propertySource;
    }

    /**
     * 
     * @return the node.
     */
    private Indicator getNode() {
        return (Indicator) getModel();
    }
}
