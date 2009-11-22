package seg.jUCMNav.model.util;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

public class MetadataHelper {

    /**
     * Adds metadata to an element.
     * 
     * @param urnspec
     *            the urnspec containing everything
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata to add
     * @param value
     *            the value to add
     */
    public static void addMetaData(URNspec urnspec, URNmodelElement elem, String name, String value) {
        Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        data.setName(name);
        data.setValue(value);
        elem.getMetadata().add(data);
    }

    /**
     * Removes metadata from an element.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata to add
     * @param value
     *            the value to add
     */
    public static void removeMetaData(URNmodelElement elem, String name) {
        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equals(name))
            {
            	elem.getMetadata().remove(data);
            	return;
            }
        }
    }

    /**
     * Returns an element's metadata.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the value of the metadata
     */
    public static String getMetaData(URNmodelElement elem, String name) {

        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equals(name))
                return data.getValue();
        }

        return null;
    }

    /**
     * Returns an element's metadata object
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the metadata object
     */
    public static Metadata getMetaDataObj(URNmodelElement elem, String name) {

        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equals(name))
                return data;
        }
        
        return null;
    }
    
}
