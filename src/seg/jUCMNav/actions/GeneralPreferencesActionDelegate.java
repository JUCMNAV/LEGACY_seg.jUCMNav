package seg.jUCMNav.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

import seg.jUCMNav.views.wizards.AutoLayoutWizard;

/**
 * Opens the general preferences page.
 * 
 * @author jkealey
 * 
 */
public class GeneralPreferencesActionDelegate implements IEditorActionDelegate {

    protected IAction action;

    public GeneralPreferencesActionDelegate() {
        super();
    }

    public void setActiveEditor(final IAction action, IEditorPart targetEditor) {

        this.action = action;
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * Launches the {@link AutoLayoutWizard}
     */
    public void run(IAction action) {
        PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                "seg.jUCMNav.views.preferences.GeneralPreferencePage", new String[] { "seg.jUCMNav.views.preferences.GeneralPreferencePage" }, null); //$NON-NLS-1$ //$NON-NLS-2$
        if (pref != null)
            pref.open();
    }

}