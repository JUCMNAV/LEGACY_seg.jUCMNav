package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import ca.mcgill.sel.core.CorePackage;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.RespRef;
import ucm.map.Stub;
import urn.URNspec;
import urn.UrnPackage;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class NamePropertySection extends AbstractMultiLineStringPropertySection {

    protected EAttribute getFeature() {
        if (eObject instanceof URNspec)
            return UrnPackage.eINSTANCE.getURNspec_Name();
        else
            return CorePackage.eINSTANCE.getCORENamedElement_Name();
    }

    public String getLabelText() {
        return Messages.getString("NamePropertySection_Name"); //$NON-NLS-1$
    }

    protected boolean isTextValid(String text) {
        boolean result = true;

        /*
         * if (eObject instanceof Responsibility) { result = !URNNamingHelper.doesResponsibilityNameExists(((Responsibility)
         * eObject).getUrndefinition().getUrnspec(), text) || ((Responsibility)eObject).getName().equals(text); } else if(eObject instanceof Component) { result
         * = !URNNamingHelper.doesComponentNameExists(((Component) eObject).getUrndefinition().getUrnspec(), text) ||
         * ((Component)eObject).getName().equals(text); } else if(eObject instanceof Actor) { result = !URNNamingHelper.doesActorNameExists(((Actor)
         * eObject).getGrlspec().getUrnspec(), text) || ((Actor)eObject).getName().equals(text); } else if(eObject instanceof Variable) { result =
         * !URNNamingHelper.doesVariableNameExist(((Variable) eObject).getUcmspec().getUrnspec(), text) || ((Variable)eObject).getName().equals(text); } else
         * if(eObject instanceof IntentionalElement) { result = !URNNamingHelper.doesIntentionalElementNameExists(((IntentionalElement)
         * eObject).getGrlspec().getUrnspec(), text) || ((IntentionalElement)eObject).getName().equals(text); } else if(eObject instanceof
         * KPIInformationElement) { result = !URNNamingHelper.doesKPIInformationElementNameExists(((KPIInformationElement) eObject).getGrlspec().getUrnspec(),
         * text) || ((KPIInformationElement) eObject).getName().equals(text); }
         * 
         * if(!result) MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
         * Messages.getString("NamePropertySection.NameAlreadyExists"), Messages.getString("NamePropertySection.ThisNameConflicts")); //$NON-NLS-1$
         * //$NON-NLS-2$
         */
        if (!(eObject instanceof URNmodelElement))
            return false;

        URNspec urn = null;
        EObject parent = eObject;
        do {
            if (parent instanceof URNspec) {
                urn = (URNspec) parent;
                break;
            }
            if (parent != null)
                parent = parent.eContainer();

        } while (parent != null);

        if (text!=null)
            text = text.trim();
        String message = URNNamingHelper.isNameValid(urn, (URNmodelElement) eObject, text);

        result = message.length() == 0;

        if (!result) {
            MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
        }

        return result;
    }

    protected Object getFeatureValue(String newText) {
        String newline = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        // remove trailing.
        while (newText.endsWith(newline))
            newText = newText.substring(0, newText.length() - newline.length());

        newText = newText.trim();
        if (eObject instanceof Responsibility || eObject instanceof RespRef || eObject instanceof Stub) {
            return newText; // multiline is allowed, but remove trailing newline.
        } else
            return newText.replaceAll(newline, " "); // single line. //$NON-NLS-1$
    }
}
