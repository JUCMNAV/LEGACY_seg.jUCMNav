package seg.jUCMNav.views.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Variable;
import urn.URNspec;

/**
 * Property source for variables. Must have unique names.
 * 
 * @author jkealey
 * 
 */
public class VariablePropertySource extends URNElementPropertySource {

    private int i = 0;

    /**
     * @param obj
     *            a Variable
     */
    public VariablePropertySource(EObject obj) {
        super(obj);
    }

    /**
     * Given the property id, return the contained value
     */
    public Object getPropertyValue(Object id) {

        // int propertyid = Integer.parseInt((String) id);
        // EStructuralFeature feature =
        // object.eClass().getEStructuralFeature(propertyid);
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();
        if (feature.getName() == "type") { //$NON-NLS-1$
            Object result = getFeature(propertyid, feature);
            result = returnPropertyValue(feature, result);
            if (result.equals(ScenarioUtils.sTypeEnumeration) && ((Variable) getEditableValue()).getEnumerationType() != null)
                result = ((Variable) getEditableValue()).getEnumerationType().getName();
            return result != null ? result : ""; //$NON-NLS-1$
        } else
            return super.getPropertyValue(id);

    }

    public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        URNspec urn = getVariable().getUcmspec().getUrnspec();

        if (feature.getName() == "name") { //$NON-NLS-1$
            value = URNNamingHelper.cleanVariableName(value.toString());
            value = value.toString().trim();
            String message = URNNamingHelper.isNameValid(urn, (Variable) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
            }

        } else {
            super.setPropertyValue(id, value);
        }
    }

    /**
     * 
     * @return the variable we are editing.
     */
    public Variable getVariable() {
        return (Variable) getEditableValue();
    }
}
