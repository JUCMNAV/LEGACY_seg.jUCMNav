/**
 * 
 */
package seg.jUCMNav.views.preferences;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.model.commands.delete.DeletionContext;
import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

/**
 * @author jfroy
 * 
 */
public class DeletePreferences {

    // Preferences to delete definition when deleting last definition
    public static final String PREF_DELDEFINITION = "PREF_DELDEFINITION"; //$NON-NLS-1$
    public static final String PREF_DELREFERENCE = "PREF_DELPREFERENCE"; //$NON-NLS-1$

    public static final String PREF_ALWAYS = Messages.getString("DeletePreferences_Always"); //$NON-NLS-1$
    public static final String PREF_NEVER = Messages.getString("DeletePreferences_Never"); //$NON-NLS-1$
    public static final String PREF_PROMPT = Messages.getString("DeletePreferences_Prompt"); //$NON-NLS-1$

    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {

        getPreferenceStore().setDefault(DeletePreferences.PREF_DELDEFINITION, DeletePreferences.PREF_PROMPT);
        getPreferenceStore().setDefault(DeletePreferences.PREF_DELREFERENCE, DeletePreferences.PREF_PROMPT);
    }

    /**
     * 
     * @return Preference store where the properties are stored.
     */
    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Verify if the definition associated with the element should be deleted.
     * 
     * @param element
     *            The element to delete
     * @return TRUE if the definition should be delete.
     */
    public static boolean getDeleteDefinition(URNmodelElement element) {
        if (DeletionContext.isPerformingCutAction() || DeletionContext.isPerformingPasteAction()) return false;
        String currentPrefValue = getPreferenceStore().getString(PREF_DELDEFINITION);
        if (currentPrefValue.equals(PREF_ALWAYS)) {
            return true;
        } else if (currentPrefValue.equals(PREF_NEVER)) {
            return false;
        } else {
            // Must prompt the user if he wants to delete the definition
            MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(new Shell(), Messages.getString("DeletePreferences_Delete") + URNNamingHelper.getName(element) //$NON-NLS-1$
                    + Messages.getString("DeletePreferences_Definition"), Messages.getString("DeletePreferences_NoMoreRefAssociated") + URNNamingHelper.getName(element) //$NON-NLS-1$ //$NON-NLS-2$
                    + Messages.getString("DeletePreferences_DoYouWantToDelete"), Messages.getString("DeletePreferences_Remember"), false, null, "DELETE_PREFERENCE"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (dialog.getReturnCode() == IDialogConstants.YES_ID) {
                if (dialog.getToggleState()) {
                    getPreferenceStore().setValue(PREF_DELDEFINITION, PREF_ALWAYS);
                }
                return true;
            } else {
                if (dialog.getToggleState()) {
                    getPreferenceStore().setValue(PREF_DELDEFINITION, PREF_NEVER);
                }
                return false;
            }
        }

    }

    /**
     * Verify if the references associated with the element should be deleted
     * 
     * @param element
     *            The element to delete
     * @return TRUE if the references should be delete.
     */
    public static boolean getDeleteReference(URNmodelElement element) {
        if (DeletionContext.isPerformingCutAction() || DeletionContext.isPerformingPasteAction()) return false;
        // this if statement was added to support the CORE interface; when jUCMNav is accessed through the CORE interface,
    	// the plugin environment is not defined which causes a null pointer exception here
    	if (COREFactory4URN.isCOREInterfaceActive())
    		return COREFactory4URN.ALWAYS_DELETE;
        String currentPrefValue = getPreferenceStore().getString(PREF_DELREFERENCE);
        if (currentPrefValue.equals(PREF_ALWAYS)) {
            return true;
        } else if (currentPrefValue.equals(PREF_NEVER)) {
            return false;
        } else {
            // Must prompt the user if he wants to delete the definition
            MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(new Shell(), Messages.getString("DeletePreferences_DeleteSpace") + URNNamingHelper.getName(element) //$NON-NLS-1$
                    + Messages.getString("DeletePreferences_DefAndRef"), URNNamingHelper.getName(element) //$NON-NLS-1$
                    + Messages.getString("DeletePreferences_IsAssociatedWith"), Messages.getString("DeletePreferences_12"), false, null, //$NON-NLS-1$ //$NON-NLS-2$
                    "DELETE_REFPREFERENCE"); //$NON-NLS-1$
            if (dialog.getReturnCode() == IDialogConstants.YES_ID) {
                if (dialog.getToggleState()) {
                    getPreferenceStore().setValue(PREF_DELREFERENCE, PREF_ALWAYS);
                }
                return true;
            } else {
                if (dialog.getToggleState()) {
                    getPreferenceStore().setValue(PREF_DELREFERENCE, PREF_NEVER);
                }
                return false;
            }
        }

    }
}
