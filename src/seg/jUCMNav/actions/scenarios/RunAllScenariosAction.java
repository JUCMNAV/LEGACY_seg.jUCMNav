/**
 * 
 */
package seg.jUCMNav.actions.scenarios;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.EnumerationTypeTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.scenarios.ScenarioUtils;

/**
 * Runs all scenarios in a group, or all scenarios if the top level folder is selected. 
 * 
 * @author jkealey
 *
 */
public class RunAllScenariosAction extends SelectionAction {

    public static final String RUNALLSCENARIOS = "Run All Scenarios"; //$NON-NLS-1$
    /**
     * @param part
     */
    public RunAllScenariosAction(IWorkbenchPart part) {
        super(part);

        setId(RUNALLSCENARIOS);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/refresh.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec or a group. 
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        return sel.getUrnspec() != null && sel.getUCMspec()!=null && sel.getScenario()==null && (sel.getScenarioGroup()!=null || (sel.getScenarioGroup()==null && getSelectedObjects().size()==1 && !(getSelectedObjects().get(0) instanceof VariableListTreeEditPart) && !(getSelectedObjects().get(0) instanceof EnumerationTypeTreeEditPart)));
    }
    

    /** Runs all the scenarios
     * 
     *
     */
    public void run() {
    	UCMNavMultiPageEditor multieditor = (UCMNavMultiPageEditor) getWorkbenchPart();
    	SelectionHelper sel = new SelectionHelper(getSelectedObjects());
    	if (sel.getScenarioGroup()==null)
    		ScenarioUtils.setActiveScenario(sel.getUCMspec());
    	else
    		ScenarioUtils.setActiveScenario(sel.getScenarioGroup());
    		
        for (int i=0; i< multieditor.getPageCount(); i++){
            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setScenarioView(true);         
        }
    }
}
