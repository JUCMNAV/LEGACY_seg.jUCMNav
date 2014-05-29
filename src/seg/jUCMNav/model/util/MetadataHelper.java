package seg.jUCMNav.model.util;

import grl.Actor;
import grl.Contribution;
import grl.ElementLink;
import grl.IntentionalElement;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.strategies.BatchEvaluationUtil;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.Metadata;
import urncore.URNmodelElement;

public class MetadataHelper {

    public static final String WIDTH = "_width"; //$NON-NLS-1$
    public static final String HEIGHT = "_height"; //$NON-NLS-1$
    
    /**
     * Adds metadata to an element. Modifies if the name already exists.
     * 
     * @param urnspec
     *            the urnspec containing everything
     * @param name
     *            the name of the metadata to add
     * @param value
     *            the value to add
     */
    public static void addMetaData(URNspec urnspec, String name, String value) {
        Metadata md = getMetaDataObj(urnspec, name);
        if (md == null) {
            Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
            data.setName(name);
            data.setValue(value);
            urnspec.getMetadata().add(data);
        } else {
            md.setValue(value);
        }
    }
    
    /**
     * Adds metadata to an element. Modifies if the name already exists.
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
        Metadata md = getMetaDataObj(elem, name);
        if (md == null) {
            Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
            data.setName(name);
            data.setValue(value);
            elem.getMetadata().add(data);
        } else {
            md.setValue(value);
        }
    }
    
    /**
     * Adds metadata to an element. Modifies if the name already exists.
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
    public static void addMetaData(URNspec urnspec, ucmscenarios.ModelElement elem, String name, String value) {
        ucmscenarios.Metadata md = getMetaDataObj(elem, name);
        if (md == null) {
            Metadata data = (Metadata) ModelCreationFactory.getNewObject(urnspec, Metadata.class);
            data.setName(name);
            data.setValue(value);
            elem.getMetadata().add(data);
        } else {
            md.setValue(value);
        }
    }

    /**
     * Removes all metadata of specified name from an element.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata to remove
     */
    public static void removeMetaData(URNmodelElement elem, String name) {
        Vector v = new Vector();
        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name)) {
                v.add(data);
                //iter.remove(); - sometimes crashed in infinite loop
                // elem.getMetadata().remove(data); - assuming crashed because modified coll for iter.  
            }
        }
        
        elem.getMetadata().removeAll(v);
    }
    
    /**
     * Removes all metadata of specified name from the URNspec.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata to remove
     */
    public static void removeMetaData(URNspec elem, String name) {
        Vector v = new Vector();
        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name)) {
                v.add(data);
            }
        }
        
        elem.getMetadata().removeAll(v);
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
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                return data.getValue();
        }

        return null;
    }
    
    public static int getIntMetaData(URNmodelElement elem, String name, int def) {
        Integer i = new Integer(def);
        
        try {
            i = new Integer(getMetaData(elem, name));
        } catch(Exception e) { return def; }
        
        return i.intValue();
    }

    /**
     * Returns a URNSpec's metadata.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the value of the metadata
     */
    public static String getMetaData(URNspec urn, String name) {

        for (Iterator iter = urn.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                return data.getValue();
        }

        return null;
    }

    /**
     * Returns a URNSpec's metadata.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the value of the metadata
     */
    public static Vector getAllMetaData(URNspec urn, String name) {
        Vector ret = new Vector();
        
        for (Iterator iter = urn.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                ret.add(data.getValue());
        }

        return ret;
    }

    /**
     * Returns a URNSpec's last metadata.
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the value of the last metadata with that name, if any
     */
    public static String getLastMetaData(URNspec urn, String name) {

    	String value = null;
    	
        for (Iterator iter = urn.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                value = data.getValue();
        }

        return value;
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
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                return data;
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
    public static ucmscenarios.Metadata getMetaDataObj(ucmscenarios.ModelElement elem, String name) {

        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            ucmscenarios.Metadata data = (ucmscenarios.Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                return data;
        }

        return null;
    }
    
    /**
     * Returns a URNSpec's metadata object
     * 
     * @param elem
     *            the element
     * @param name
     *            the name of the metadata
     * @return the metadata object
     */
    public static Metadata getMetaDataObj(URNspec elem, String name) {

        for (Iterator iter = elem.getMetadata().iterator(); iter.hasNext();) {
            Metadata data = (Metadata) iter.next();
            if (data.getName() != null && data.getName().equalsIgnoreCase(name))
                return data;
        }

        return null;
    }

    public static void duplicateMetadata(URNmodelElement from, URNmodelElement onto)
    {
        if (from != null && onto != null) {
            for (Iterator iterator = from.getMetadata().iterator(); iterator.hasNext();) {
                Metadata data = (Metadata) iterator.next();
                Metadata clone = (Metadata) EcoreUtil.copy(data);
                onto.getMetadata().add(clone);
            }
        }
    }
    /**
     * Removes the run-time metadata elements created during GRL evaluation and/or UCM execution
     * 
     * @param model
     *            the URNspec instance
     */
    public static synchronized void cleanRunTimeMetadata(URNspec model) {
        if (model != null) {
            // Remove run-time evaluation metadata attached to actors
            for (Iterator iter = model.getGrlspec().getActors().iterator(); iter.hasNext();) {
                Actor actor = (Actor) iter.next();
                MetadataHelper.removeMetaData(actor, EvaluationStrategyManager.METADATA_NUMEVAL);
                MetadataHelper.removeMetaData(actor, EvaluationStrategyManager.METADATA_QUALEVAL);
                MetadataHelper.removeMetaData(actor, BatchEvaluationUtil.METADATA_TREND);
            }
            // Remove run-time evaluation metadata attached to intentional elements
            for (Iterator iter2 = model.getGrlspec().getIntElements().iterator(); iter2.hasNext();) {
                IntentionalElement ie = (IntentionalElement) iter2.next();
                MetadataHelper.removeMetaData(ie, EvaluationStrategyManager.METADATA_NUMEVAL);
                MetadataHelper.removeMetaData(ie, EvaluationStrategyManager.METADATA_QUALEVAL);
                MetadataHelper.removeMetaData(ie, EvaluationStrategyManager.METADATA_RANGEVALUES);
                MetadataHelper.removeMetaData(ie, BatchEvaluationUtil.METADATA_TREND);
                MetadataHelper.removeMetaData(ie, Messages.getString("ConditionalGRLStrategyAlgorithm_IgnoreNode")); //$NON-NLS-1$
            }
            // Remove run-time evaluation metadata attached to path nodes
            for (Iterator iter3 = model.getUrndef().getSpecDiagrams().iterator(); iter3.hasNext();) {
                IURNDiagram diagram = (IURNDiagram) iter3.next();
                if (diagram instanceof UCMmap) {
                    for (Iterator iterIn = diagram.getNodes().iterator(); iterIn.hasNext();) {
                        PathNode pn = (PathNode) iterIn.next();
                        MetadataHelper.removeMetaData(pn, PathNodeEditPart.METADATA_HITS);
                    }
                }
            }
            // Remove run-time contribution metadata attached to contribution links

            for (Iterator iter4 = model.getGrlspec().getLinks().iterator(); iter4.hasNext();) {
                ElementLink link = (ElementLink) iter4.next();
                if (link instanceof Contribution)
                	MetadataHelper.removeMetaData(link, Messages.getString("ConditionalGRLStrategyAlgorithm_RuntimeContribution")); //$NON-NLS-1$
            }

        }
    }

    /**
     * Indicates whether the named metadata was inserted at run-time (e.g. for GRL evaluation)
     * 
     * @param name
     *            name of the metadata
     * @return true if the metadata was inserted at run-time
     */
    public static boolean isRuntimeMetadata(String name) {
        return name!=null && (name.equalsIgnoreCase(EvaluationStrategyManager.METADATA_NUMEVAL) || name.equalsIgnoreCase(EvaluationStrategyManager.METADATA_RANGEVALUES)
                || name.equalsIgnoreCase(EvaluationStrategyManager.METADATA_QUALEVAL) || name.equalsIgnoreCase(PathNodeEditPart.METADATA_HITS) || name.equalsIgnoreCase(BatchEvaluationUtil.METADATA_TREND)
                        || name.equalsIgnoreCase(WIDTH) || name.equalsIgnoreCase(HEIGHT)  );
    }
}
