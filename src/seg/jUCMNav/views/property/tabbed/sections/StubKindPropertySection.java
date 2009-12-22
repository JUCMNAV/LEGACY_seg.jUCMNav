package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.change.SetCommand;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;
import seg.jUCMNav.model.util.StubHelper;
import seg.jUCMNav.model.util.StubKind;
import seg.jUCMNav.views.property.StackHelper;
import ucm.map.AspectKind;
import ucm.map.PointcutKind;
import ucm.map.Stub;

public class StubKindPropertySection extends AbstractChoicePropertySection {

    protected int lastSelection = 0;

    protected String[] getList() {
        return new String[] {
                Messages.getString("StubKindPropertySection_Static"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection_Dynamic"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection_Pointcut"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection.PointcutRep"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection_Synchronizing"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection_Blocking"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection.AE"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection.AEEntrance"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection.AEExit"), //$NON-NLS-1$
                Messages.getString("StubKindPropertySection.AEConditional") //$NON-NLS-1$
                };
    }

    protected void updateSelection() {
        Stub s = (Stub) eObject;
        
        int index = StubHelper.getStubKind(s);;
        combo.select(index);

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
                case StubKind.STATIC:
                    cmd = new ChangeStubTypeCommand(s, false, PointcutKind.NONE_LITERAL, false, false, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.DYNAMIC:
                    cmd = new ChangeStubTypeCommand(s, true, PointcutKind.NONE_LITERAL, false, false, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.POINTCUT:
                    cmd = new ChangeStubTypeCommand(s, true, PointcutKind.REGULAR_LITERAL, false, false, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.POINTCUT_REPLACEMENT:
                    cmd = new ChangeStubTypeCommand(s, true, PointcutKind.REPLACEMENT_LITERAL, false, false, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.SYNCHRONIZING:
                    cmd = new ChangeStubTypeCommand(s, true, PointcutKind.NONE_LITERAL, true, false, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.SYNCHRONIZING_BLOCKING:
                    cmd = new ChangeStubTypeCommand(s, true, PointcutKind.NONE_LITERAL, true, true, AspectKind.NONE_LITERAL);
                    break;
                case StubKind.ASPECT_MARKER:
                    cmd = new ChangeStubTypeCommand(s, false, PointcutKind.NONE_LITERAL, false, false, AspectKind.REGULAR_LITERAL);
                    break;
                case StubKind.ASPECT_ENTRANCE:
                    cmd = new ChangeStubTypeCommand(s, false, PointcutKind.NONE_LITERAL, false, false, AspectKind.ENTRANCE_LITERAL);
                    break;
                case StubKind.ASPECT_EXIT:
                    cmd = new ChangeStubTypeCommand(s, false, PointcutKind.NONE_LITERAL, false, false, AspectKind.EXIT_LITERAL);
                    break;
                case StubKind.ASPECT_CONDITIONNAL:
                    cmd = new ChangeStubTypeCommand(s, false, PointcutKind.NONE_LITERAL, false, false, AspectKind.CONDITIONAL_LITERAL);
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
