package seg.jUCMNav.model.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.UCMspec;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.PathGraph;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import urn.URNlink;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.GRLmodelElement;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNdefinition;

/**
 * Created on 12-May-2005
 * 
 * This class provides functionality to name (and number using the ID) the meta model objects in jUCMNav. See setElementNameAndID() for this purpose.
 * 
 * Furthermore, using sanitizeURNspec(), one can clean up a meta-model and make sure all elements have ids and names.
 * 
 * @author jkealey
 *  
 */
public class URNNamingHelper {

    //  to be used for shorthands or other mappings
    public static Hashtable htPrefixes;

    static {
        htPrefixes = new Hashtable();
        htPrefixes.put(StartPoint.class, Messages.getString("URNNamingHelper.start")); //$NON-NLS-1$
        htPrefixes.put(EndPoint.class, Messages.getString("URNNamingHelper.end")); //$NON-NLS-1$

    }

    /**
     * Returns the next ID that can be used in this document. Assumes it will be used and increments the count in the URNspec.
     * 
     * @param urn
     *            The URNspec containing the value.
     * 
     * @return a string
     */
    private static String getNewID(URNspec urn) {

        if (urn == null) {
            return ""; //$NON-NLS-1$
        }

        String id = urn.getNextGlobalID();

        // if we can't convert it, the model is in an invalid state.
        // don't catch the exception
        if (id != null && id.length() > 0)
            id = Long.toString(Long.parseLong(id) + 1);
        else {
            id = "2"; // for backwards compatibility reasons with early jUCMNav files. //$NON-NLS-1$
            System.out.println(Messages.getString("URNNamingHelper.oldFileDiscard")); //$NON-NLS-1$
        }

        urn.setNextGlobalID(id);

        return id;
    }

    /**
     * When creating names, we often need a generic name. Using this method, we can obtain a prefix using the appropriate naming convention.
     * 
     * @param targetClass
     * @return prefix
     */
    public static String getPrefix(Class targetClass) {
        if (htPrefixes.get(targetClass) != null)
            return (String) htPrefixes.get(targetClass);
        else if (getSimpleName(targetClass).endsWith("Impl")) //$NON-NLS-1$
            return getSimpleName(targetClass).substring(0, getSimpleName(targetClass).length() - 4);
        else
            return getSimpleName(targetClass);

    }

    /**
     * In simple cases, equivalent to the java 1.5 Class.getSimpleName(); To avoid depending on Java 1.5
     * 
     * @param targetClass
     * @return simple name
     */
    private static String getSimpleName(Class targetClass) {
        String simpleName = targetClass.getName();
        return simpleName.substring(simpleName.lastIndexOf(".") + 1); // strip the package name //$NON-NLS-1$
    }

    /**
     * Verifies if the object has both its name and id set. If it only has one of the two, simply checks the one that is there. Useful because it can handle
     * many types like UCMmodelElement, GRLmodelElement, etc.
     * 
     * @param o
     * @return boolean showing whether name and ID are set
     */
    private static boolean isNameAndIDSet(Object o) {
        if (o instanceof UCMmodelElement) {
            UCMmodelElement elem = ((UCMmodelElement) o);
            return elem.getName() != null && elem.getName().length() > 0 && elem.getId() != null && elem.getId().length() > 0;
        } else if (o instanceof GRLmodelElement) {
            GRLmodelElement elem = ((GRLmodelElement) o);
            return elem.getName() != null && elem.getName().length() > 0 && elem.getId() != null && elem.getId().length() > 0;
        } else if (o instanceof URNlink) {
            URNlink elem = ((URNlink) o);
            return elem.getName() != null && elem.getName().length() > 0 && elem.getId() != null && elem.getId().length() > 0;
        } else if (o instanceof URNspec) {
            URNspec elem = ((URNspec) o);
            return elem.getName() != null && elem.getName().length() > 0;
        }

        return false;
    }

    /**
     * Verifies that the passed string is equivalent to the canonical form of a Long
     * 
     * @param s
     * @return true if the passed string is equivalent to the canonical form of a Long
     */
    private static boolean isValidID(String s) {
        try {
            long l = Long.parseLong(s);

            // we want to have the canonical form of the long
            return Long.toString(l).equals(s);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Given a URNspec, it will make sure that all IDs are set to unique values, that the top ID stored in the URNspec is valid.
     * 
     * This is only a partial implementation. It doesn't scan all GRL elements. Pretty much limited to what is needed for UCM manipulation.
     * 
     * @param urn
     */
    public static void sanitizeURNspec(URNspec urn) {
        String proposedTopID = urn.getNextGlobalID();
        HashMap htIDs = new HashMap();
        HashMap htComponentNames = new HashMap();
        HashMap htResponsibilityNames = new HashMap();
        Vector IDConflicts = new Vector();
        Vector CompNameConflicts = new Vector();
        Vector RespNameConflicts = new Vector();

        // make sure that we have a legal Long as our proposedTopID
        if (proposedTopID == null || proposedTopID.length() == 0 || !isValidID(proposedTopID)) {
            proposedTopID = setTopID(urn, "2"); //$NON-NLS-1$
        }

        // make sure that our URN is named.
        if (!isNameAndIDSet(urn)) {
            urn.setName(getPrefix(urn.getClass()));
        }

        if (urn.getUrnVersion() == null || urn.getUrnVersion().length() == 0)
            urn.setUrnVersion("0.9"); //$NON-NLS-1$

        if (urn.getSpecVersion() == null || urn.getSpecVersion().length() == 0)
            urn.setSpecVersion("1"); //$NON-NLS-1$

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String sDate = df.format(new Date());
        try {
            if (urn.getModified() == null || urn.getModified().length() == 0)
                urn.setModified(sDate);
            else
                df.parse(urn.getModified());
        } catch (Exception ex) {

            urn.setModified(sDate);
        }
        try {
            if (urn.getCreated() == null || urn.getCreated().length() == 0)
                urn.setCreated(sDate);
            else
                df.parse(urn.getCreated());
        } catch (Exception ex) {

            urn.setCreated(sDate);
        }

        // make sure all component elements and responsibilities have unique ids and names.
        sanitizeURNdef(urn, htIDs, htComponentNames, htResponsibilityNames, IDConflicts, CompNameConflicts, RespNameConflicts);

        // make sure all componentrefs and pathnodes have unique ids.
        sanitizeUCMspec(urn, htIDs, IDConflicts);

        // now that we have found our conflicts, clean them up.
        resolveConflicts(urn, htIDs, htComponentNames, htResponsibilityNames, IDConflicts, CompNameConflicts, RespNameConflicts);
    }

    /**
     * For each map in the UCMspec, verify that all componentrefs and pathnodes have unique ids.
     * 
     * @param urn
     *            Will check the UCMspec contained in this urn
     * @param htIDs
     *            The hash map of used ids
     * @param IDConflicts
     *            The vector of conflictual elements. Add problems here.
     */
    private static void sanitizeUCMspec(URNspec urn, HashMap htIDs, Vector IDConflicts) {
        // we need a ucm specification
        if (urn.getUcmspec() == null) {
            // create a default one; no name required.
            urn.setUcmspec((UCMspec) ModelCreationFactory.getNewObject(null, UCMspec.class));
        }

        //look at all maps
        for (Iterator iter = urn.getUcmspec().getMaps().iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();
            if (!isNameAndIDSet(map)) {
                setElementNameAndID(urn, map);
            }
            findConflicts(htIDs, null, IDConflicts, null, map);

            // look at all componentrefs
            for (Iterator iterator = map.getCompRefs().iterator(); iterator.hasNext();) {
                findConflicts(htIDs, null, IDConflicts, null, (UCMmodelElement) iterator.next());
            }

            // make sure we have a pathgraph.
            if (map.getPathGraph() == null) {
                map.setPathGraph((PathGraph) ModelCreationFactory.getNewObject(null, PathGraph.class));
            }

            // look at all pathnodes
            for (Iterator iterator = map.getPathGraph().getPathNodes().iterator(); iterator.hasNext();) {
                findConflicts(htIDs, null, IDConflicts, null, (UCMmodelElement) iterator.next());
            }

        }
    }

    /**
     * For each component element and responsibility in the URNdef, make sure that there are no ID or Name conflicts.
     * 
     * If you find any, using the hash maps, add them to the appropriate conflict vectors.
     * 
     * @param urn
     * @param htIDs
     * @param htComponentNames
     * @param htResponsibilityNames
     * @param IDConflicts
     * @param CompNameConflicts
     * @param RespNameConflicts
     */
    private static void sanitizeURNdef(URNspec urn, HashMap htIDs, HashMap htComponentNames, HashMap htResponsibilityNames, Vector IDConflicts,
            Vector CompNameConflicts, Vector RespNameConflicts) {
        // we need a urn definition
        if (urn.getUrndef() == null) {
            // create a default one; no name required.
            urn.setUrndef((URNdefinition) ModelCreationFactory.getNewObject(null, URNdefinition.class));
        }

        // look at all components
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            ComponentElement comp = (ComponentElement) iter.next();
            if (!isNameAndIDSet(comp)) {
                setElementNameAndID(urn, comp);
            }

            // find name and id conflicts for components
            findConflicts(htIDs, htComponentNames, IDConflicts, CompNameConflicts, comp);
        }

        // look at all responsibilities
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            Responsibility resp = (Responsibility) iter.next();
            if (!isNameAndIDSet(resp)) {
                setElementNameAndID(urn, resp);
            }

            //  find name and id conflicts for responsibilities
            findConflicts(htIDs, htResponsibilityNames, IDConflicts, RespNameConflicts, resp);
        }
    }

    /**
     * Resolve ID and naming conflicts; change the ids and names so that no problems subsist.
     * 
     * @param urn
     * @param htIDs
     * @param htComponentNames
     * @param htResponsibilityNames
     * @param IDConflicts
     * @param CompNameConflicts
     * @param RespNameConflicts
     */
    private static void resolveConflicts(URNspec urn, HashMap htIDs, HashMap htComponentNames, HashMap htResponsibilityNames, Vector IDConflicts,
            Vector CompNameConflicts, Vector RespNameConflicts) {

        resolveIDConflicts(urn, htIDs, IDConflicts);

        resolveNamingConflicts(urn, htComponentNames, CompNameConflicts);

        resolveNamingConflicts(urn, htResponsibilityNames, RespNameConflicts);
    }

    /**
     * Resolve ID conflicts; change the ids so that no problems subsist. Update the URNspec with the new top ID if it changes.
     * 
     * @param urn
     * @param htIDs
     * @param IDConflicts
     */
    private static void resolveIDConflicts(URNspec urn, HashMap htIDs, Vector IDConflicts) {
        String proposedTopID;
        while (IDConflicts.size() > 0) {
            UCMmodelElement elem = (UCMmodelElement) IDConflicts.get(0);

            do {
                // set it to nothing
                elem.setId(""); //$NON-NLS-1$

                // get the next ID; might take a while.. find first free space.
                setElementNameAndID(urn, elem);
            } while (htIDs.containsKey(elem.getId()));
            htIDs.put(elem.getId(), null);

            IDConflicts.remove(0);
        }

        // sort the ids to get the highest one.
        ArrayList ids = new ArrayList(htIDs.keySet());
        Collections.sort(ids, new LongAsStringComparator());

        // because of our calls to isValidID, should be convertible to a Long
        // the proposedTopID is the minimal legal value.

        if (ids.size() > 0) {
            proposedTopID = Long.toString(Long.parseLong((String) ids.get(ids.size() - 1)) + 1);

            // update the ID if necessary
            if (!urn.getNextGlobalID().equals(proposedTopID)) {
                // don't lower the top id; simply increment it if changes occured.
                if (Long.parseLong(proposedTopID) > Long.parseLong(urn.getNextGlobalID()))
                    urn.setNextGlobalID(proposedTopID);
            }
        }
    }

    /**
     * Resolve naming conflicts; change the names so that no problems subsist.
     * 
     * @param urn
     * @param htNames
     * @param nameConflicts
     */
    private static void resolveNamingConflicts(URNspec urn, HashMap htNames, Vector nameConflicts) {
        // resolve responsibility naming conflicts
        while (nameConflicts.size() > 0) {
            UCMmodelElement elem = (UCMmodelElement) nameConflicts.get(0);
            int i = 1;

            // it might be a custom name, try setting the default name (maybe we fixed the ID)
            elem.setName(""); //$NON-NLS-1$
            setElementNameAndID(urn, elem);

            // if that didn't work, try adding -1, -2, -3 ... until it works.
            while (htNames.containsKey(elem.getName().toLowerCase())) {
                elem.setName(""); //$NON-NLS-1$
                setElementNameAndID(urn, elem);
                elem.setName(elem.getName() + "-" + (i++)); //$NON-NLS-1$
            }
            htNames.put(elem.getName().toLowerCase(), null);

            nameConflicts.remove(0);
        }
    }

    /**
     * Make sure that a certain element doesn't cause any id/naming conflicts. If it is not desired to check either one of them, simply pass null for the hash
     * map and vector.
     * 
     * @param htIDs
     * @param htNames
     * @param idConflicts
     * @param nameConflicts
     * @param elem
     */
    private static void findConflicts(HashMap htIDs, HashMap htNames, Vector idConflicts, Vector nameConflicts, UCMmodelElement elem) {

        if (htIDs != null && idConflicts != null) {
            // do we have an id conflict or a non numeric one?
            if (htIDs.containsKey(elem.getId()) || !isValidID(elem.getId())) {
                idConflicts.add(elem);
            } else {
                // remember the ID
                htIDs.put(elem.getId(), null);
            }
        }

        if (htNames != null && nameConflicts != null) {

            // do we have a naming conflict?
            if (elem.getName().length() == 0 || htNames.containsKey(elem.getName().toLowerCase())) {
                nameConflicts.add(elem);
            } else {
                // remember the name
                htNames.put(elem.getName().toLowerCase(), null);
            }
        }
    }

    /**
     * Given an object that might not have its name or ID set, set the default id and name. For the ID, it should be the next one available in the URNspec. For
     * the name, it uses getPrefix() for most cases, getPrefix() concatenated with the ID for componentelements and reponsibilities. Does not verify naming
     * unicity.
     * 
     * @param urn
     * @param o
     */
    public static void setElementNameAndID(URNspec urn, Object o) {

        // ComponentElement and Responsibilty are two special cases; they must have unique names.
        // Generics would help minimize the code for the rest; we could use EMF to determine of the name and id attributes exist but decided to go for
        // legibility
        if (o instanceof ComponentElement) {
            ComponentElement ce = (ComponentElement) o;
            if (ce.getId() == null || ce.getId().trim().length() == 0) {
                ce.setId(getNewID(urn));
            }

            if (ce.getName() == null || ce.getName().trim().length() == 0) {
                ce.setName(getPrefix(o.getClass()) + ce.getId());
            }
        } else if (o instanceof Responsibility) {
            Responsibility resp = (Responsibility) o;
            if (resp.getId() == null || resp.getId().trim().length() == 0) {
                resp.setId(getNewID(urn));
            }

            if (resp.getName() == null || resp.getName().trim().length() == 0) {
                resp.setName(getPrefix(o.getClass()) + resp.getId());
            }
        } else if (o instanceof UCMmodelElement) {
            UCMmodelElement model = (UCMmodelElement) o;
            if (model.getId() == null || model.getId().trim().length() == 0) {
                model.setId(getNewID(urn));
            }

            if (model.getName() == null || model.getName().trim().length() == 0) {
                model.setName(getPrefix(o.getClass()));
            }
        } else if (o instanceof GRLmodelElement) {
            GRLmodelElement model = (GRLmodelElement) o;
            if (model.getId() == null || model.getId().trim().length() == 0) {
                model.setId(getNewID(urn));
            }

            if (model.getName() == null || model.getName().trim().length() == 0) {
                model.setName(getPrefix(o.getClass()));
            }
        } else if (o instanceof URNlink) {
            URNlink model = (URNlink) o;
            if (model.getId() == null || model.getId().trim().length() == 0) {
                model.setId(getNewID(urn));
            }

            if (model.getName() == null || model.getName().trim().length() == 0) {
                model.setName(getPrefix(o.getClass()));
            }
        } else {
            System.out.println(Messages.getString("URNNamingHelper.unknownClass")); //$NON-NLS-1$
        }
    }

    /**
     * Changes the top ID in the URNspec; to be used if we find an error.
     * 
     * @param urn
     * @param id
     * @return the new ID
     */
    private static String setTopID(URNspec urn, String id) {
        urn.setNextGlobalID(id);
        return id;
    }

    /**
     * Verifies in the urnspec to see if a component exists with the proposed name.If you plan on calling resolveNamingConflict after this call, call it
     * directly; this method will only add overhead.
     * 
     * @param urn
     * @param proposedName
     * @return bool showing if name exists
     */
    public static boolean doesComponentNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            UCMmodelElement element = (UCMmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    /**
     * Verifies in the urnspec to see if a responsibility exists with the proposed name. If you plan on calling resolveNamingConflict after this call, call it
     * directly; this method will only add overhead.
     * 
     * @param urn
     * @param proposedName
     * @return bool: does resp name exist
     */
    public static boolean doesResponsibilityNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            UCMmodelElement element = (UCMmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    /**
     * Given a component or responsibility, decide if there is a naming conflict by looking in the appropriate collection. If there is one, rename the object.
     * Should be used by automated background processes as will drop any custom name. If interacting with the user, use
     * does(Component|Responsibility)NameExist().
     * 
     * @param urn
     * @param elem
     */
    public static void resolveNamingConflict(URNspec urn, UCMmodelElement elem) {
        Collection c;
        if (elem instanceof Responsibility) {
            c = urn.getUrndef().getResponsibilities();
        } else if (elem instanceof ComponentElement) {
            c = urn.getUrndef().getComponents();
        } else {
            System.out.println(Messages.getString("URNNamingHelper.unableToResolve")); //$NON-NLS-1$
            return;
        }

        HashMap names = new HashMap();
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            UCMmodelElement element = (UCMmodelElement) iter.next();
            names.put(element.getName().toLowerCase(), null);
        }

        if (names.containsKey(elem.getName().toLowerCase())) {
            Vector v = new Vector();
            v.add(elem);
            resolveNamingConflicts(urn, names, v);
        }

    }

    public static String isNameValid(URNspec urn, UCMmodelElement elem, String name) {
        String message = ""; //$NON-NLS-1$

        if (elem instanceof ComponentRef || elem instanceof RespRef || elem instanceof Responsibility || elem instanceof ComponentElement) {
            if (name.toString().trim().length() == 0) {
                message = Messages.getString("URNNamingHelper.invalidName"); //$NON-NLS-1$
            }
        }
        if (elem instanceof ComponentRef || elem instanceof ComponentElement) {
            if (URNNamingHelper.doesComponentNameExists(urn, name)) {
                message = Messages.getString("URNNamingHelper.compNameExist"); //$NON-NLS-1$
            }
        } else if (elem instanceof RespRef || elem instanceof Responsibility) {
            if (URNNamingHelper.doesResponsibilityNameExists(urn, name)) {
                message = Messages.getString("URNNamingHelper.respNameExist"); //$NON-NLS-1$
            }
        }

        return message;
    }

    public static String isNameValid(UCMmodelElement elem, String name) {
        EObject parent = elem;

        while (!(parent instanceof URNspec)) {
            if (parent == null)
                return Messages.getString("URNNamingHelper.elementNotInUrnspec"); //$NON-NLS-1$

            parent = parent.eContainer();
        }
        return isNameValid((URNspec) parent, elem, name);
    }

}