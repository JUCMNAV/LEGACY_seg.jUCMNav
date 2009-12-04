package seg.jUCMNav.views.property;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class has been created to allow the PropertySheetViewer to detect changes properly (because the original implementation used an Object array, and
 * hashCode() wasn't computed appropriately)
 * 
 * Furthermore, this class should help increase readability in the property source classes.
 * 
 * @author jkealey
 * 
 */
public class PropertyID {

    private EClass c;
    private EStructuralFeature feature;

    /**
     * Construct a property id using the EClass and EStructuralFeature.
     * 
     * @param c
     * @param feature
     */
    public PropertyID(EClass c, EStructuralFeature feature) {
        this.c = c;
        this.feature = feature;
    }

    /**
     * Overridden to allow the properties HashMap to detect changes properly.
     */
    public int hashCode() {
        return c.hashCode() + feature.hashCode();
    }

    /**
     * Overridden to allow appropriate comparisons.
     * 
     * @param obj
     * @return boolean showing equality
     */
    public boolean equals(Object obj) {
        if (obj instanceof PropertyID) {
            PropertyID prop = (PropertyID) obj;
            return prop.getEClass().equals(c) && prop.getFeature().equals(feature);
        } else
            return false;
    }

    public EClass getEClass() {
        return c;
    }

    public void setEClass(EClass c) {
        this.c = c;
    }

    public EStructuralFeature getFeature() {
        return feature;
    }

    public void setFeature(EStructuralFeature feature) {
        this.feature = feature;
    }
}