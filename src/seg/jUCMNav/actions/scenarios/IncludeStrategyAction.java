package seg.jUCMNav.actions.scenarios;

import grl.EvaluationStrategy;

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
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyLabelTreeEditPart;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.strategies.IncludeStrategy;

/**
 * Opens the include strategy wizard
 * 
 * @author jkealey
 */
public class IncludeStrategyAction extends URNSelectionAction {

    public static final String INCLUDESTRATEGY = "seg.jUCMNav.IncludeStrategy"; //$NON-NLS-1$

    protected EvaluationStrategy strategy;

    /**
     * @param part
     */
    public IncludeStrategyAction(IWorkbenchPart part) {
        super(part);
        setId(INCLUDESTRATEGY);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/grlstrat16.gif")); //$NON-NLS-1$
    }

    /**
     * Can include a strategy if there are strategies left that can be added without causing recursive loops.
     */
    protected boolean calculateEnabled() {
        initStrategy();
        return strategy != null && EvaluationStrategyManager.getPossibleIncludedStrategies(strategy).size() > 0;
    }

    /**
     * Allows us to include a strategy inside another one even if we have selected an element in the tree which is not associated to a model element (the
     * multiple folders that contain the children of a strategy).
     * 
     */
    protected void initStrategy() {
        List list = getSelectedObjects();
        ArrayList list2 = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object object = iter.next();

            if (object instanceof StrategyLabelTreeEditPart)
                list2.add(((StrategyLabelTreeEditPart) object).getParent());
            else
                list2.add(object);
        }
        SelectionHelper sel = new SelectionHelper(list2);
        switch (sel.getSelectionType()) {
        case SelectionHelper.EVALUATIONSTRATEGY:
            strategy = sel.getEvaluationStrategy();
            break;
        default:
            strategy = null;
        }
    }

    /**
     * Opens the include scenario wizard.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        IncludeStrategy wizard = new IncludeStrategy();

        StructuredSelection selection = new StructuredSelection(strategy);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}