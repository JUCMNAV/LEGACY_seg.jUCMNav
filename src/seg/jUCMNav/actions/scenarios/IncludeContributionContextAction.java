package seg.jUCMNav.actions.scenarios;

import grl.ContributionContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.ContributionContextLabelTreeEditPart;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.strategies.IncludeContributionContext;

/**
 * Opens the include contribution context wizard
 * 
 * @author jkealey
 */
public class IncludeContributionContextAction extends URNSelectionAction {

    public static final String INCLUDECONTRIBUTIONCONTEXT = "seg.jUCMNav.IncludeContributionContext"; //$NON-NLS-1$

    protected ContributionContext context;

    /**
     * @param part
     */
    public IncludeContributionContextAction(IWorkbenchPart part) {
        super(part);
        setId(INCLUDECONTRIBUTIONCONTEXT);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ContributionContext16.gif")); //$NON-NLS-1$
    }

    /**
     * Can include a context if there are contexts left that can be added without causing recursive loops.
     */
    protected boolean calculateEnabled() {
        initContexts();
        return context != null && EvaluationStrategyManager.getPossibleIncludedContributionContexts(context).size() > 0;
    }

    /**
     * Allows us to include a context inside another one even if we have selected an element in the tree which is not associated to a model element (the
     * multiple folders that contain the children of a context).
     * 
     */
    protected void initContexts() {
        List list = getSelectedObjects();
        ArrayList list2 = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object object = iter.next();

            if (object instanceof ContributionContextLabelTreeEditPart)
                list2.add(((ContributionContextLabelTreeEditPart) object).getParent());
            else
                list2.add(object);
        }
        SelectionHelper sel = new SelectionHelper(list2);
        switch (sel.getSelectionType()) {
        case SelectionHelper.CONTRIBUTIONCONTEXT:
            context = sel.getContributionContext();
            break;
        default:
            context = null;
        }
    }

    /**
     * Opens the include contribution context wizard.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IncludeContributionContext wizard = new IncludeContributionContext();

        StructuredSelection selection = new StructuredSelection(context);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}