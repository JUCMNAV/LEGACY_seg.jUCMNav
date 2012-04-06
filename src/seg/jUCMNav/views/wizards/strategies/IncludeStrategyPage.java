package seg.jUCMNav.views.wizards.strategies;

import grl.EvaluationStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;

/**
 * The page actually containing the code for including a strategy.
 * 
 * Not robust enough to support loading when none are available.
 * 
 */
public class IncludeStrategyPage extends WizardPage {
    private ISelection selection;
    private EvaluationStrategy parent;
    private Vector children;
    private List strategies;
    
    private String _description = Messages.getString("IncludeStrategyPage.PleaseChooseStrategies");  //$NON-NLS-1$

    /**
     * The selection contains a strategy definition for which we want to include another strategy. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public IncludeStrategyPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("IncludeStrategyPage.IncludeStrategy")); //$NON-NLS-1$
        setDescription(_description);

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        //PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_includestrategy"); //$NON-NLS-1$
        children = new Vector();
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(_description);

        initialize();

        strategies = new List(container, SWT.MULTI | SWT.BORDER | SWT.SCROLL_LINE);
        strategies.setItems(getPossibleChildren());

        GridData gd = new GridData(GridData.FILL_BOTH);
        strategies.setLayoutData(gd);
        strategies.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                dialogChanged();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        dialogChanged();
        setControl(container);

    }

    private String[] getPossibleChildren() {
        java.util.List possibleChildren = EvaluationStrategyManager.getPossibleIncludedStrategies(this.parent);

        ArrayList childrenStrings = new ArrayList();
        for (Iterator iter = possibleChildren.iterator(); iter.hasNext();) {
            EvaluationStrategy possibleChild = (EvaluationStrategy) iter.next();
            childrenStrings.add(URNNamingHelper.getName(possibleChild.getGroup())
                    + Messages.getString("IncludeScenarioPage.GroupScenarioSeperator") + URNNamingHelper.getName(possibleChild)); //$NON-NLS-1$
        }

        Object[] o = childrenStrings.toArray();
        String[] strings = new String[o.length];
        for (int i = 0; i < o.length; i++) {
            strings[i] = o[i].toString();
        }
        return strings;
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof EvaluationStrategy) {
                parent = (EvaluationStrategy) obj;
                // if (resp.getExpression() == null)
                //					codeText.setText(""); //$NON-NLS-1$
                // else
                // codeText.setText(resp.getExpression());
            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {

        if (strategies.getSelectionIndex() < 0)
            updateStatus(_description); 
        else {
            children.clear();
            for (int i = 0; i < strategies.getSelectionIndices().length; i++) {
                int index = strategies.getSelectionIndices()[i];
                children.add(EvaluationStrategyManager.getPossibleIncludedStrategies(parent).get(index));
            }
            updateStatus(null);
        }
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

    }

    /**
     * Returns the child strategy that has been selected.
     * 
     * @return the children
     */
    public Vector getChildStrategies() {
        return children;
    }

    /**
     * Returns the parent strategy that was initially given
     * 
     * @return the child
     */
    public EvaluationStrategy getParentStrategy() {
        return parent;
    }

}
