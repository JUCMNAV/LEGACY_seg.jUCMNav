package seg.jUCMNav.actions.scenarios;

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

	/**
	 * @param part
	 */
	public DuplicateAction(IWorkbenchPart part) {
		super(part);
		setId(DUPLICATEACTION);
		setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/duplicate.gif")); //$NON-NLS-1$
	}

	/**
	 * True if we've selected something with code. 	 */
	protected boolean calculateEnabled() {
		initScenario();
		return scenario!=null && child!=null;
	}
	
	protected void initScenario() {
		scenario=null;
		child=null;
		List list = getSelectedObjects();
		if (list.size()==0 || list.size()>1 || !(list.get(0) instanceof EditPart) || !(((EditPart)list.get(0)).getModel() instanceof EObject)) return;
		
	
		child = (EObject) ((EditPart)list.get(0)).getModel();
		if (!(child instanceof ScenarioStartPoint || child instanceof ScenarioEndPoint || child instanceof Condition))
			child=null;

		if (child!=null)
		{
//			if (child instanceof Condition)
//			{
//				Condition condition = (Condition) child;
//				if (condition.getScenarioDefPost()!=null)
//					scenario = condition.getScenarioDefPost();
//				else 
//					scenario = condition.getScenarioDefPre();
//			} else if (child instanceof ScenarioStartPoint)
//			{
//				ScenarioStartPoint point = (ScenarioStartPoint) child;
//				scenario = point.getScenarioDef();
//			} else if (child instanceof ScenarioEndPoint)
//			{
//				ScenarioEndPoint point = (ScenarioEndPoint) child;
//				scenario = point.getScenarioDef();
//			}
		
			// this will give us the behaviour we want for inherited elements. 
			scenario = (ScenarioDef)((EditPart)list.get(0)).getParent().getParent().getModel();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {

		UCMNavMultiPageEditor editor = ((UCMNavMultiPageEditor)getWorkbenchPart());
		CommandStack cs = editor.getDelegatingCommandStack();
		DuplicateCommand command = new DuplicateCommand(scenario, child);
		cs.execute(command);

	}

}