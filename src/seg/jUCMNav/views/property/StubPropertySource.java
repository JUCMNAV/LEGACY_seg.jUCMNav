package seg.jUCMNav.views.property;

import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.views.property.descriptors.StubPluginsPropertyDescriptor;
import ucm.map.Stub;

/**
 * Created 2005-06-05
 * 
 * @author Etienne Tremblay
 */
public class StubPropertySource extends UCMElementPropertySource {
	
	private Stub stub;

	/**
	 * @param obj
	 */
	public StubPropertySource(EObject obj) {
		super(obj);
		stub = (Stub)obj;
	}
	
	
	protected Vector addSpecificProperties() {
		Vector property = new Vector();
		
		property.add(new StubPluginsPropertyDescriptor(new PropertyID(stub.eClass(), stub.eClass().getEStructuralFeature("bindings")), stub));
		
		return property;
	}
}
