package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.views.property.descriptors.CheckboxPropertyDescriptor;
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
        stub = (Stub) obj;
        this.cmdStack = cmdStack;
    }

    protected Vector addSpecificProperties() {
        Vector property = new Vector();

        property.add(new StubPluginsPropertyDescriptor(new PropertyID(stub.eClass(), stub.eClass().getEStructuralFeature("bindings")), stub, cmdStack)); //$NON-NLS-1$

        return property;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.property.UCMElementPropertySource#addPropertyToDescriptor(java.util.Collection, org.eclipse.emf.ecore.EStructuralFeature,
     *      org.eclipse.emf.ecore.EClass)
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        super.addPropertyToDescriptor(descriptors, attr, c);
        if (attr.getName().equalsIgnoreCase("dynamic")) { //$NON-NLS-1$
            Vector v = (Vector) descriptors;
            CheckboxPropertyDescriptor pd = (CheckboxPropertyDescriptor) v.get(v.size() - 1);

            if (((Stub) getEditableValue()).getBindings().size() > 1)
                pd.setReadOnly(true);
            else
                pd.setReadOnly(false);
        }
    }
}