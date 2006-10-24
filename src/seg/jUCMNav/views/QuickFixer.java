package seg.jUCMNav.views;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.EndPoint;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioDef;
import urncore.Condition;
/**
 * This class is generates quick fixes for elements, which open code editors. 
 * 
 * @author jkealey
 * 
 */
public class QuickFixer implements IMarkerResolutionGenerator {
    public IMarkerResolution[] getResolutions(IMarker mk) {
       try {
          IEditorPart ed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

          if ((mk.getAttribute("NodePreCondition")!=null || mk.getAttribute("NodePostCondition")!=null || mk.getAttribute("Scenario")!=null) && mk.exists() && ed instanceof UCMNavMultiPageEditor) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          {
			UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) ed;
			Object o = mk.getAttribute("EObject"); //$NON-NLS-1$
			if (o!=null) {
								
				Object element = URNElementFinder.find(editor.getModel(), o.toString());
				if (element!=null) {

					Condition cond=null;
					if (mk.getAttribute("NodePreCondition")!=null) //$NON-NLS-1$
					{

						cond = ((StartPoint)element).getPrecondition();

					}
					else if (mk.getAttribute("NodePostCondition")!=null) //$NON-NLS-1$
					{
						cond = ((EndPoint)element).getPostcondition();

					} else if (mk.getAttribute("Scenario")!=null) //$NON-NLS-1$
					{
						ScenarioDef scenario = (ScenarioDef)element;
						if (mk.getAttribute("ScenarioPreConditionIndex")!=null && mk.getAttribute("ScenarioPreConditionIndex") instanceof Integer) //$NON-NLS-1$ //$NON-NLS-2$
						{
							Integer i = (Integer) mk.getAttribute("ScenarioPreConditionIndex"); //$NON-NLS-1$
							if (i.intValue()<scenario.getPreconditions().size())
								cond = (Condition) scenario.getPreconditions().get(i.intValue());
						
						} else if (mk.getAttribute("ScenarioPostConditionIndex")!=null && mk.getAttribute("ScenarioPostConditionIndex") instanceof Integer) //$NON-NLS-1$ //$NON-NLS-2$
						{
							Integer i = (Integer) mk.getAttribute("ScenarioPostConditionIndex"); //$NON-NLS-1$
							if (i.intValue()<scenario.getPostconditions().size())
								cond = (Condition) scenario.getPostconditions().get(i.intValue());
						
						}
					}
					
					if (cond!=null)
					{
						return new IMarkerResolution[] {new OpenEditorQuickFix(cond) };
					}
				}else {
					System.out.println("can't find it"); //$NON-NLS-1$
				}
          }
          }
       }
       catch (CoreException e) {
       }
       
       return new IMarkerResolution[0];
    }
 }
