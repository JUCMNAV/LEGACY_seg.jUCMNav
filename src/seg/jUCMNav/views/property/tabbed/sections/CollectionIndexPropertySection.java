package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.transformations.ChangeUCMDiagramOrderCommand;
import seg.jUCMNav.views.wizards.URNDiagramIndexDialog;
import urncore.IURNDiagram;
import urncore.URNdefinition;
import urncore.URNmodelElement;

public class CollectionIndexPropertySection extends AbstractDialogPropertySection {
    protected URNdefinition spec;
    protected IURNDiagram current;

    @Override
    protected String getText() {
        if (spec == null)
            return ""; //$NON-NLS-1$
        return (new Integer(spec.getSpecDiagrams().indexOf(current))).toString();
    }

    @Override
    protected void openDialog() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        ILabelProvider labelP = new ILabelProvider() {

            public Image getImage(Object element) {
                return null;
            }

            public String getText(Object element) {
                String name = "";  //$NON-NLS-1$
                if (element instanceof URNmodelElement)
                    name= ((URNmodelElement) element).getName();
                
                if (element instanceof IURNDiagram)
                {
                    name += Messages.getString("CollectionIndexPropertySection.Tab") + ((IURNDiagram)element).getUrndefinition().getSpecDiagrams().indexOf(element) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                }

                return name;
            }

            public void addListener(ILabelProviderListener listener) {

            }

            public void dispose() {

            }

            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            public void removeListener(ILabelProviderListener listener) {

            }
        };

        URNDiagramIndexDialog dialog = new URNDiagramIndexDialog(shell, labelP);
        dialog.setElements(spec.getSpecDiagrams().toArray());
        dialog.open();

        Object[] result = dialog.getResult();

        if (result != null) {
            IURNDiagram d = (IURNDiagram) result[0];

            int from = spec.getSpecDiagrams().indexOf(current);
            int to = spec.getSpecDiagrams().indexOf(d);
            boolean after = ((Boolean) result[1]).booleanValue();

            if (from < to && !after)
                to--;
            else if (from > to && after)
                to++;

            ChangeUCMDiagramOrderCommand cmd = new ChangeUCMDiagramOrderCommand(current.getUrndefinition(), from, to);
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            editor.getDelegatingCommandStack().execute(cmd);
        }
    }

    @Override
    protected Object resolve(Object obj) {

        current = (IURNDiagram) obj;
        spec = ((IURNDiagram) obj).getUrndefinition();

        return spec;
    }

    @Override
    public String getLabelText() {
        return Messages.getString("CollectionIndexPropertySection_Index"); //$NON-NLS-1$
    }

}
