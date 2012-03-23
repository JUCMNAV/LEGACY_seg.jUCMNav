package seg.jUCMNav.actions.scenarios;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.DuplicateCommand;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

/**
 * Duplicates a start/end point, pre/post condition.
 * 
 * @author jkealey
 */
public class DuplicateAction extends IncludeScenarioAction {

    public static final String DUPLICATEACTION = "seg.jUCMNav.DuplicateAction"; //$NON-NLS-1$
    private EObject child;
    private ScenarioGroup group;
    private EvaluationStrategy strategy;
    private StrategiesGroup group2;
    private ContributionContext context;
    private ContributionContextGroup group3;

    /**
     * @param part
     */
    public DuplicateAction(IWorkbenchPart part) {
        super(part);
        setId(DUPLICATEACTION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/duplicate.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with code.
     */
    protected boolean calculateEnabled() {
        initScenario();
        return scenario != null || child != null || group != null || strategy != null || group2 != null || context != null || group3 != null;
    }

    /**
     * Initializes variables to know what we want to duplicate and where we want to duplicate it. We don't necessarily duplicate to its container because of
     * scenario inheritance.
     */
    protected void initScenario() {
        scenario = null;
        child = null;
        group = null;
        strategy = null;
        group2 = null;
        context = null;
        group3 = null;
        List list = getSelectedObjects();
        if (list.size() == 0 || list.size() > 1 || !(list.get(0) instanceof EditPart) || !(((EditPart) list.get(0)).getModel() instanceof EObject))
            return;

        child = (EObject) ((EditPart) list.get(0)).getModel();
        if (!(child instanceof ScenarioStartPoint || child instanceof ScenarioEndPoint || child instanceof Condition || child instanceof ScenarioGroup
                || child instanceof ScenarioDef || child instanceof EvaluationStrategy || child instanceof StrategiesGroup
                || child instanceof ContributionContext || child instanceof ContributionContextGroup))
            child = null;

        if (child != null) {
            // if (child instanceof Condition)
            // {
            // Condition condition = (Condition) child;
            // if (condition.getScenarioDefPost()!=null)
            // scenario = condition.getScenarioDefPost();
            // else
            // scenario = condition.getScenarioDefPre();
            // } else if (child instanceof ScenarioStartPoint)
            // {
            // ScenarioStartPoint point = (ScenarioStartPoint) child;
            // scenario = point.getScenarioDef();
            // } else if (child instanceof ScenarioEndPoint)
            // {
            // ScenarioEndPoint point = (ScenarioEndPoint) child;
            // scenario = point.getScenarioDef();
            // }

            // this will give us the behaviour we want for inherited elements.

            if (child instanceof ScenarioDef) {

                EditPart part = (EditPart) list.get(0);
                if (part.getParent() != null && part.getParent().getParent() != null && part.getParent().getParent().getModel() instanceof ScenarioDef) {
                    // included scenario
                    this.child = null;
                } else {
                    scenario = (ScenarioDef) child;
                    this.child = null;
                }
            } else if (child instanceof ScenarioGroup) {
                this.group = (ScenarioGroup) child;
                this.child = null;
            } else if (child instanceof EvaluationStrategy) {
                this.strategy = (EvaluationStrategy) child;
                this.child = null;
            } else if (child instanceof StrategiesGroup) {
                this.group2 = (StrategiesGroup) child;
                this.child = null;
            } else if (child instanceof ContributionContext) {
                this.context = (ContributionContext) child;
                this.child = null;
            } else if (child instanceof ContributionContextGroup) {
                this.group3 = (ContributionContextGroup) child;
                this.child = null;

            } else {
                EditPart part = (EditPart) list.get(0);
                if (part.getParent() != null && part.getParent().getParent() != null) {
                    if (part.getParent().getParent().getModel() instanceof ScenarioDef) {
                        scenario = (ScenarioDef) part.getParent().getParent().getModel();
                    } else if (part.getParent().getParent().getModel() instanceof EvaluationStrategy) {
                        strategy = (EvaluationStrategy) part.getParent().getParent().getModel();
                    } else if (part.getParent().getParent().getModel() instanceof ContributionContext) {
                        context = (ContributionContext) part.getParent().getParent().getModel();
                    }
                }
            }
        }

    }

    /**
     * Create and run the appropriate {@link seg.jUCMNav.model.commands.create.DuplicateCommand}
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {

        UCMNavMultiPageEditor editor = ((UCMNavMultiPageEditor) getWorkbenchPart());
        CommandStack cs = editor.getDelegatingCommandStack();

        DuplicateCommand command = null;
        if (child != null && scenario != null)
            command = new DuplicateCommand(scenario, child);
        else if (scenario != null)
            command = new DuplicateCommand(scenario);
        else if (group != null)
            command = new DuplicateCommand(group);
        else if (strategy != null)
            command = new DuplicateCommand(strategy);
        else if (group2 != null)
            command = new DuplicateCommand(group2);
        else if (context != null)
            command = new DuplicateCommand(context);
        else if (group3 != null)
            command = new DuplicateCommand(group3);

        if (command != null)
            cs.execute(command);

    }

}