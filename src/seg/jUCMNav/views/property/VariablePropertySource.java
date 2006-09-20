package seg.jUCMNav.views.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.model.util.URNNamingHelper;
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

	public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);
        URNspec urn = getVariable().getUcmspec().getUrnspec();

        
		if (feature.getName() == "name") { //$NON-NLS-1$
            String message = URNNamingHelper.isNameValid(urn, (Variable) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
            }

        } else {
            super.setPropertyValue(id, value);
        }	}

	/**
	 * 
	 * @return the variable we are editing.
	 */
	public Variable getVariable() {
		return (Variable) getEditableValue();
	}
}
