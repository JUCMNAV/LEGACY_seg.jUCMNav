package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import ucm.map.PathNode;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * This class is special cased for ScenarioStartPoint/ScenarioEndPoint so that we can add the reference element's properties on the same level as the others.
 * 
 * @author jkealey
 * 
 */
public class ScenarioPathNodePropertySource extends URNElementPropertySource {

    // reference to the startpoint/endpoint.
    private PathNode node = null;

    /**
     * @param obj
     */
    public ScenarioPathNodePropertySource(EObject obj) {
        super(obj);
        if ((object instanceof ScenarioStartPoint) && ((ScenarioStartPoint) object).getStartPoint() != null) {
            node = ((ScenarioStartPoint) object).getStartPoint();
        } else if ((object instanceof ScenarioEndPoint) && ((ScenarioEndPoint) object).getEndPoint() != null) {
            node = ((ScenarioEndPoint) object).getEndPoint();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addSpecificProperties()
     */
    protected Vector addSpecificProperties() {
        Iterator it;
        Collection descriptors = new Vector();

        if (node != null) {
            // we are referencing another object; show its properties here.
            it = node.eClass().getEAllStructuralFeatures().iterator();

            // add the new properties
            while (it.hasNext()) {
                EStructuralFeature attr = (EStructuralFeature) it.next();
                addPropertyToDescriptor(descriptors, attr, node.eClass());
            }
        }
        return (Vector) descriptors;
    }

    /**
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass() != object.eClass())
            result = node.eGet(feature);
        else
            result = object.eGet(feature);
        return result;
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass() != object.eClass()) {
            node.eSet(feature, result);
        } else
            object.eSet(feature, result);
    }

}