package seg.jUCMNav.views.property;

import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.views.property.descriptors.StubPluginsPropertyDescriptor;
import ucm.map.Stub;

/**
 * Created 2005-06-05
 * 
 * @author Etienne Tremblay
 */
public class StubPropertySource extends UCMElementPropertySource {
	
	private Stub stub;
	private CommandStack cmdStack;

	/**
	 * @param obj
	 */
	public StubPropertySource(EObject obj, CommandStack cmdStack) {
		super(obj);
		stub = (Stub)obj;
		this.cmdStack = cmdStack;
	}
	
	
	protected Vector addSpecificProperties() {
		Vector property = new Vector();
		
		property.add(new StubPluginsPropertyDescriptor(new PropertyID(stub.eClass(), stub.eClass().getEStructuralFeature("bindings")), stub, cmdStack)); //$NON-NLS-1$
		
		return property;
	}
}
