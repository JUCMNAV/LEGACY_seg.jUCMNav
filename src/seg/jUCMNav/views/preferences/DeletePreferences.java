/**
 * 
 */
package seg.jUCMNav.views.preferences;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

/**
 * @author jfroy
 *
 */
public class DeletePreferences {
	
    //Preferences to delete definition when deleting last definition
    public static final String PREF_DELDEFINITION = "PREF_DELDEFINITION"; //$NON-NLS-1$
    public static final String PREF_DELREFERENCE = "PREF_DELPREFERENCE"; //$NON-NLS-1$
    public static final String PREF_RENAMEREFERENCE = "PREF_RENAMEPREFERENCE"; //$NON-NLS-1$
    
    public static final String PREF_ALWAYS = "Always";
    public static final String PREF_NEVER = "Never";
    public static final String PREF_PROMPT = "Prompt";
    
    /**
     * Sets the default values in the preference store.
     */
    public static void createPreferences() {
    	
        getPreferenceStore().setDefault(DeletePreferences.PREF_DELDEFINITION, DeletePreferences.PREF_PROMPT);
        getPreferenceStore().setDefault(DeletePreferences.PREF_DELREFERENCE, DeletePreferences.PREF_PROMPT);
        getPreferenceStore().setDefault(DeletePreferences.PREF_RENAMEREFERENCE, DeletePreferences.PREF_PROMPT);
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
     * @param element The element to delete
     * @return TRUE if the definition should be delete.
     */
    public static boolean getDeleteDefinition(URNmodelElement element)
    {
    	String currentPrefValue = getPreferenceStore().getString(PREF_DELDEFINITION);
    	if(currentPrefValue.equals(PREF_ALWAYS))
    	{
    		return true;
    	} else if(currentPrefValue.equals(PREF_NEVER))
    	{
    		return false;
    	} else
    	{
    		//Must prompt the user if he wants to delete the definition
    		MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(new Shell(), "Delete " + URNNamingHelper.getName(element) + " Definition", "There is no more reference associated with element " + URNNamingHelper.getName(element) + ".\n\nDo you want to delete the definition?", "Remember my decision", false, null, "DELETE_PREFERENCE");
    		if(dialog.getReturnCode() == IDialogConstants.YES_ID)
    		{
	    		if(dialog.getToggleState())
	    		{
	    			getPreferenceStore().setValue(PREF_DELDEFINITION, PREF_ALWAYS);
	    		}
	    		return true;
    		} else
    		{
    			if(dialog.getToggleState())
    			{
    				getPreferenceStore().setValue(PREF_DELDEFINITION, PREF_NEVER);
    			}
    			return false;
    		}
    	}
    	
    }
    
    /**
     * Verify if the references associated with the element should be deleted
     * @param element The element to delete
     * @return TRUE if the references should be delete.
     */
    public static boolean getDeleteReference(URNmodelElement element)
    {
    	String currentPrefValue = getPreferenceStore().getString(PREF_DELREFERENCE);
    	if(currentPrefValue.equals(PREF_ALWAYS))
    	{
    		return true;
    	} else if(currentPrefValue.equals(PREF_NEVER))
    	{
    		return false;
    	} else
    	{
    		//Must prompt the user if he wants to delete the definition
    		MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(new Shell(), "Delete " + URNNamingHelper.getName(element) + " Definition and References", URNNamingHelper.getName(element) + " is associated with references.\n\nDo you want to delete the definition and references?", "Remember my decision", false, null, "DELETE_REFPREFERENCE");
    		if(dialog.getReturnCode() == IDialogConstants.YES_ID)
    		{
	    		if(dialog.getToggleState())
	    		{
	    			getPreferenceStore().setValue(PREF_DELREFERENCE, PREF_ALWAYS);
	    		}
	    		return true;
    		} else
    		{
    			if(dialog.getToggleState())
    			{
    				getPreferenceStore().setValue(PREF_DELREFERENCE, PREF_NEVER);
    			}
    			return false;
    		}
    	}
    }
    
    /**
     * Verify if the references associated with the element should be renamed
     * @param element The element to rename
     * @return TRUE if the references should be renamed.
     */
    public static boolean getRenameReference(String element)
    {
    	String currentPrefValue = getPreferenceStore().getString(PREF_RENAMEREFERENCE);
    	if(currentPrefValue.equals(PREF_ALWAYS))
    	{
    		return true;
    	} else if(currentPrefValue.equals(PREF_NEVER))
    	{
    		return false;
    	} else
    	{
    		//Must prompt the user if he wants to delete the definition
    		MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(new Shell(), "Name already in use: " + element, element + ":  Another definition already exists with this name. Press Yes if you wish to change this element's definition. Otherwise, press No to choose another name.", "Remember my decision", false, null, "RENAME_REFPREFERENCE");
    		if(dialog.getReturnCode() == IDialogConstants.YES_ID)
    		{
	    		if(dialog.getToggleState())
	    		{
	    			getPreferenceStore().setValue(PREF_RENAMEREFERENCE, PREF_ALWAYS);
	    		}
	    		return true;
    		} else
    		{
    			if(dialog.getToggleState())
    			{
    				getPreferenceStore().setValue(PREF_RENAMEREFERENCE, PREF_NEVER);
    			}
    			return false;
    		}
    	}
    }
    
}
