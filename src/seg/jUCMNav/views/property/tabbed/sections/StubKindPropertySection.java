package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.change.SetCommand;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;
import seg.jUCMNav.views.property.StackHelper;
import ucm.map.MapPackage;
import ucm.map.Stub;

public class StubKindPropertySection extends AbstractChoicePropertySection {

    protected int lastSelection = 0;

    protected String[] getList() {
        return new String[] { Messages.getString("StubKindPropertySection_Static"), Messages.getString("StubKindPropertySection_Dynamic"), Messages.getString("StubKindPropertySection_Pointcut"), Messages.getString("StubKindPropertySection_Synchronizing"), Messages.getString("StubKindPropertySection_Blocking") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    }

    protected void updateSelection() {
        Stub s = (Stub) eObject;

        if (!s.isDynamic())
            combo.select(0);
        else if (s.isPointcut() && s.isDynamic())
            combo.select(2);
        else if (s.isDynamic() && s.isSynchronization()) {
            if (s.isBlocking())
                combo.select(4);
            else
                combo.select(3);
        } else if (s.isDynamic())
            combo.select(1);

        lastSelection = combo.getSelectionIndex();
    }

    protected void itemSelected(int index) {
        if (index != lastSelection) {
            Stub s = (Stub) eObject;
            SetCommand c;

            CommandStack cs = StackHelper.getStack(propertySheetPage);
            if (cs != null) {
                ChangeStubTypeCommand cmd = null;

                switch (index) {
                case 0: // Static
                    cmd = new ChangeStubTypeCommand(s, false, false, false, false);
                    break;
                case 1: // Dynamic
                    cmd = new ChangeStubTypeCommand(s, true, false, false, false);
                    break;
                case 2: // Pointcut
                    cmd = new ChangeStubTypeCommand(s, true, true, false, false);
                    break;
                case 3: // Synchronizing
                    cmd = new ChangeStubTypeCommand(s, true, false, true, false);
                    break;
                case 4: // Blocking
                    cmd = new ChangeStubTypeCommand(s, true, false, true, true);
                    break;
                }

                if (cmd != null) {
                    if (cmd.canExecute())
                        cs.execute(cmd);
                    else {
                        combo.select(lastSelection);
                        
                        MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.getString("StubKindPropertySection_WarningCantChange"), Messages.getString("StubKindPropertySection_WarningLong")); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }

        lastSelection = index;
    }

    public String getLabelText() {
        return Messages.getString("StubKindPropertySection_Kind"); //$NON-NLS-1$
    }

}
