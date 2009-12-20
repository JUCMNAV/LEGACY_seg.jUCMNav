package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.views.property.descriptors.CheckboxPropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.StubPluginsPropertyDescriptor;
import ucm.map.NodeConnection;
import ucm.map.Stub;

/**
 * Property source for stubs; adds a specific entry for stub bindings.
 * 
 * @author Etienne Tremblay
 */
public class StubPropertySource extends URNElementPropertySource {

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
        /* would need to add one for each nc. 
        if (hasThreshold()) 
        {
            NodeConnection nc = (NodeConnection)stub.getSucc().get(0);
            property.add(new CodePropertyDescriptor(new PropertyID(nc.eClass(), nc.eClass().getEStructuralFeature("threshold")), nc)); 
        }
        */
        return property;
    }

    private boolean hasThreshold() {
        return stub!=null && stub.isSynchronization() && stub.getSucc().size()>=1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.property.UCMElementPropertySource#addPropertyToDescriptor(java.util.Collection, org.eclipse.emf.ecore.EStructuralFeature,
     * org.eclipse.emf.ecore.EClass)
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
    
    /**
     * @param propertyid
     * @param feature
     * @return the feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass() != object.eClass() && hasThreshold())
            result = ((NodeConnection)stub.getSucc().get(0)).eGet(feature);
        else
            result = object.eGet(feature);
        return result;
    }
}