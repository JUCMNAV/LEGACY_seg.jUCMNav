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
     * @param pn
     *            the element
     * @param name
     *            the name of the metadata to add
     * @param value
     *            the value to add
     */
    public static void addMetaData(URNspec urnspec, URNmodelElement pn, String name, String value) {
        Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
        data.setName(name);
        data.setValue(value);
        pn.getMetadata().add(data);

    }

    /**
     * Returns an element's metadata.
     * 
     * @param pn
     *            the element
     * @param name
     *            the name of the metadata
     * @return the value of the metadata
     */
    public static String getMetaData(URNmodelElement pn, String name) {

        for (Iterator iter = pn.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equals(name))
                return data.getValue();
        }

        return null;
    }

}
