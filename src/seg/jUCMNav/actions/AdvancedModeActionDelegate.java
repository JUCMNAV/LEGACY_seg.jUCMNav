package seg.jUCMNav.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.wizards.AutoLayoutWizard;

/**
 * Toggles the advanced mode. 
 * 
 * @author jkealey
 * 
 */
public class AdvancedModeActionDelegate implements IEditorActionDelegate {
    
    protected IAction action;
    
    public AdvancedModeActionDelegate() {
        super();

        JUCMNavPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                if(action != null)
                    action.setChecked(DisplayPreferences.getInstance().isAdvancedControlEnabled());
            }
        });
    }

    public void setActiveEditor(final IAction action, IEditorPart targetEditor) {

        this.action = action;
        action.setChecked(DisplayPreferences.getInstance().isAdvancedControlEnabled());
    }

    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * Launches the {@link AutoLayoutWizard}
     */
    public void run(IAction action) {
        boolean isEnabled = DisplayPreferences.getInstance().isAdvancedControlEnabled();

        DisplayPreferences.getInstance().setIsAdvancedControlEnabled(!isEnabled);
    }

}