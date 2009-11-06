package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.change.SetCommand;
import seg.jUCMNav.views.property.StackHelper;
import ucm.map.MapPackage;
import ucm.map.Stub;

public class StubKindPropertySection extends AbstractChoicePropertySection {
	
	protected int lastSelection = 0;

	protected String[] getList() {
		return new String[] { "Static", "Pointcut", "Dynamic" };
	}

	protected void updateSelection() {
		Stub s = (Stub)eObject;

		if(!s.isDynamic())
			combo.select(0);
		else if(s.isPointcut() && s.isDynamic())
			combo.select(1);
		else if(s.isDynamic())
			combo.select(2);
		
		lastSelection = combo.getSelectionIndex();
	}

	protected void itemSelected(int index) {
		if(index != lastSelection)
		{
			Stub s = (Stub)eObject;
			SetCommand c;
			
			CommandStack cs = StackHelper.getStack(propertySheetPage);
			if (cs!=null) {
		        CompoundCommand cmd = new CompoundCommand();
		        
				switch(index)
				{
				case 0:
					c = new SetCommand(s, MapPackage.eINSTANCE.getStub_Dynamic(), new Boolean(false));
					cmd.add(c);
					break;
				case 1:
					c = new SetCommand(s, MapPackage.eINSTANCE.getStub_Pointcut(), new Boolean(true));
					cmd.add(c);
					c = new SetCommand(s, MapPackage.eINSTANCE.getStub_Dynamic(), new Boolean(true));
					cmd.add(c);
					break;
				case 2:
					c = new SetCommand(s, MapPackage.eINSTANCE.getStub_Pointcut(), new Boolean(false));
					cmd.add(c);
					c = new SetCommand(s, MapPackage.eINSTANCE.getStub_Dynamic(), new Boolean(true));
					cmd.add(c);
					break;
				}
				
				if(cmd.canExecute())
					cs.execute(cmd);
			}
		}
		
		lastSelection = index;
	}

	public String getLabelText() {
		return "Stub Kind:";
	}
	
}
