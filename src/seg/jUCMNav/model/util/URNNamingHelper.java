package seg.jUCMNav.model.util;

import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.Contribution;
import grl.ContributionChange;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.UCMspec;
import ucm.map.Anything;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Component;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNdefinition;
import urncore.URNmodelElement;

/**
 * This class provides functionality to name (and number using the ID) the meta model objects in jUCMNav. See setElementNameAndID() for this purpose.
 * 
 * Furthermore, using sanitizeURNspec(), one can clean up a meta-model and make sure all elements have ids and names.
 * 
 * @author jkealey, pchen
 * 
 */
public class URNNamingHelper {

    // to be used for shorthands or other mappings
    public static final Hashtable htPrefixes;

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
            id = "2"; // for backwards compatibility reasons with early //$NON-NLS-1$
            // jUCMNav files. //$NON-NLS-1$
            System.out.println(Messages.getString("URNNamingHelper.oldFileDiscard")); //$NON-NLS-1$
        }

        urn.setNextGlobalID(id);

        return id;
    }

    /**
     * When creating names, we often need a generic name. Using this method, we can obtain a prefix using the appropriate naming convention.
     * 
     * @param targetClass
     *            the class
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
     *            the class
     * @return simple name
     */
    private static String getSimpleName(Class targetClass) {
        String simpleName = targetClass.getName();
        return simpleName.substring(simpleName.lastIndexOf(".") + 1); // strip //$NON-NLS-1$
        // the
        // package
        // name
        // //$NON-NLS-1$
    }

    /**
     * Verifies if the object has both its name and id set. If it only has one of the two, simply checks the one that is there. Useful because it can handle
     * many types like UCMmodelElement, GRLmodelElement, etc.
     * 
     * @param o
     *            the object to test
     * @return boolean showing whether name and ID are set
     */
    private static boolean isNameAndIDSet(Object o) {
        if (o instanceof UCMmodelElement) {
            UCMmodelElement elem = ((UCMmodelElement) o);
            return elem.getName() != null && elem.getName().length() > 0 && elem.getId() != null && elem.getId().length() > 0;
        } else if (o instanceof GRLmodelElement) {
            GRLmodelElement elem = ((GRLmodelElement) o);
            return elem.getName() != null && elem.getName().length() > 0 && elem.getId() != null && elem.getId().length() > 0;
            // } else if (o instanceof URNlink) {
            // URNlink elem = ((URNlink) o);
            // return elem.getName() != null && elem.getName().length() > 0 &&
            // elem.getId() != null && elem.getId().length() > 0;
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
     *            the id in String format
     * @return true if s is equivalent to the canonical form of a Long
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
     *            the URNspec to sanitize
     */
    public static void sanitizeURNspec(URNspec urn) {
        String proposedTopID = urn.getNextGlobalID();
        HashMap htIDs = new HashMap();
        HashMap htComponentNames = new HashMap();
        HashMap htResponsibilityNames = new HashMap();
        HashMap htVariableNames = new HashMap();
        HashMap htActorNames = new HashMap();
        HashMap htIntElementNames = new HashMap();
        HashMap htKpiInfoElementNames = new HashMap();
        // HashMap htContributionElementNames = new HashMap();

        Vector IDConflicts = new Vector();
        Vector CompNameConflicts = new Vector();
        Vector RespNameConflicts = new Vector();
        Vector VariableNameConflicts = new Vector();
        Vector ActorNameConflicts = new Vector();
        Vector IntElementNameConflicts = new Vector();
        Vector KpiInfoElementNameConflicts = new Vector();
        // Vector ContributionNameConflicts = new Vector();

        // make sure that we have a legal Long as our proposedTopID
        if (proposedTopID == null || proposedTopID.length() == 0 || !isValidID(proposedTopID)) {
            proposedTopID = setTopID(urn, "2"); //$NON-NLS-1$
        }

        // make sure that our URN is named.
        if (!isNameAndIDSet(urn)) {
            urn.setName(getPrefix(urn.getClass()));
        }

        if (urn.getUrnVersion() == null || urn.getUrnVersion().length() == 0)
            urn.setUrnVersion(ModelCreationFactory.URNSPEC_VERSION);

        if (urn.getSpecVersion() == null || urn.getSpecVersion().length() == 0)
            urn.setSpecVersion("1"); //$NON-NLS-1$

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
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

        // make sure all component elements and responsibilities have unique ids
        // and names.
        sanitizeURNdef(urn, htIDs, htComponentNames, htResponsibilityNames, IDConflicts, CompNameConflicts, RespNameConflicts);

        // make sure all componentrefs and pathnodes have unique ids.
        sanitizeUCMspec(urn, htIDs, IDConflicts, htVariableNames, VariableNameConflicts);

        // make sure all nodes and actorref have unique ids
        sanitizeGRLspec(urn, htIDs, IDConflicts, htActorNames, ActorNameConflicts, htIntElementNames, IntElementNameConflicts, htKpiInfoElementNames,
                KpiInfoElementNameConflicts);

        // now that we have found our conflicts, clean them up.
        resolveConflicts(urn, htIDs, htComponentNames, htResponsibilityNames, htVariableNames, htActorNames, htIntElementNames, htKpiInfoElementNames,
                IDConflicts, CompNameConflicts, RespNameConflicts, VariableNameConflicts, ActorNameConflicts, IntElementNameConflicts,
                KpiInfoElementNameConflicts);
    }

    /**
     * For each diagram in GRLspec, verify that all nodes and actorref have unique ids.
     * 
     * @param urn
     *            Will check the GRLspec contained in this urn
     * @param htIDs
     *            The hash map of used ids
     * @param IDConflicts
     *            The vector of conflictual elements. Add problems here.
     */
    private static void sanitizeGRLspec(URNspec urn, HashMap htIDs, Vector IDConflicts, HashMap htActorNames, Vector ActorNameConflicts,
            HashMap htIntElementNames, Vector IntElementNameConflicts, HashMap htKpiInfoElementNames, Vector KpiInfoElementNameConflicts) {
        // we need a ucm specification
        if (urn.getGrlspec() == null) {
            // create a default one; no name required.
            urn.setGrlspec((GRLspec) ModelCreationFactory.getNewObject(null, GRLspec.class));
        }

        // look at all actors
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            // find name and id conflicts for actors
            findConflicts(htIDs, htActorNames, IDConflicts, ActorNameConflicts, urn, (URNmodelElement) iter.next());
        }

        // int elements
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            findConflicts(htIDs, htIntElementNames, IDConflicts, IntElementNameConflicts, urn, (URNmodelElement) iter.next());
        }

        // kpi info elements
        for (Iterator iter = urn.getGrlspec().getKpiInformationElements().iterator(); iter.hasNext();) {
            // find name and id conflicts for actors
            findConflicts(htIDs, htKpiInfoElementNames, IDConflicts, KpiInfoElementNameConflicts, urn, (URNmodelElement) iter.next());
        }

        // links
        for (Iterator iter = urn.getGrlspec().getLinks().iterator(); iter.hasNext();) {
            findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iter.next());
        }

        // kpi model links.
        for (Iterator iter = urn.getGrlspec().getKpiModelLinks().iterator(); iter.hasNext();) {
            // don't care about their names.
            findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iter.next());
        }

        // look at all strategies
        for (Iterator iterator = urn.getGrlspec().getStrategies().iterator(); iterator.hasNext();) {
            findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iterator.next());
        }

        // look at all diagrams
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof GRLGraph) {
                GRLGraph diagram = (GRLGraph) g;
                findConflicts(htIDs, null, IDConflicts, null, urn, diagram);

                // look at all actorref
                for (Iterator iterator = diagram.getContRefs().iterator(); iterator.hasNext();) {
                    findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iterator.next());
                }

                // look at all nodes
                for (Iterator iterator = diagram.getNodes().iterator(); iterator.hasNext();) {
                    findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iterator.next());
                }
            }
        }
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
     * @param htVariableNames
     *            a hashmap of used variable names
     * @param VariableNameConflicts
     *            a vector in which to store variable name conflicts.
     * 
     */
    private static void sanitizeUCMspec(URNspec urn, HashMap htIDs, Vector IDConflicts, HashMap htVariableNames, Vector VariableNameConflicts) {
        // we need a ucm specification
        if (urn.getUcmspec() == null) {
            // create a default one; no name required.
            urn.setUcmspec((UCMspec) ModelCreationFactory.getNewObject(null, UCMspec.class));
        }

        // look at all variables
        for (Iterator iterator = urn.getUcmspec().getVariables().iterator(); iterator.hasNext();) {
            findConflicts(htIDs, htVariableNames, IDConflicts, VariableNameConflicts, urn, (URNmodelElement) iterator.next());
        }

        // look at all resources
        for (Iterator iterator = urn.getUcmspec().getResources().iterator(); iterator.hasNext();) {
            findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iterator.next());
        }

        // look at all scenarios
        for (Iterator iterator = urn.getUcmspec().getScenarioGroups().iterator(); iterator.hasNext();) {
            ScenarioGroup g = (ScenarioGroup) iterator.next();
            findConflicts(htIDs, null, IDConflicts, null, urn, g);

            for (Iterator iter = g.getScenarios().iterator(); iter.hasNext();) {
                findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iter.next());
            }
        }

        // look at all maps
        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;
                findConflicts(htIDs, null, IDConflicts, null, urn, map);

                // look at all componentrefs
                for (Iterator iterator = map.getContRefs().iterator(); iterator.hasNext();) {
                    findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iterator.next());
                }

                // look at all pathnodes
                for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                    UCMmodelElement elem = (UCMmodelElement) iterator.next();
                    boolean resetToEmptyName= (elem.getName()==null || elem.getName().length()==0) && (elem instanceof StartPoint || elem instanceof EndPoint || elem instanceof Stub || elem instanceof FailurePoint || elem instanceof WaitingPlace || elem instanceof Anything);

                    findConflicts(htIDs, null, IDConflicts, null, urn, elem);

                    if (resetToEmptyName) // bug 748
                        elem.setName(""); //$NON-NLS-1$

                    if (elem instanceof Stub) {
                        if(((Stub) elem).isPointcut()) {
                            ((Stub) elem).setAopointcut(PointcutKind.REGULAR_LITERAL);
                            ((Stub) elem).setPointcut(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * For each component element and responsibility in the URNdef, make sure that there are no ID or Name conflicts.
     * 
     * If you find any, using the hash maps, add them to the appropriate conflict vectors.
     * 
     * @param urn
     *            the URNspec containing the URNdef
     * @param htIDs
     *            a hashmap of used ids
     * @param htComponentNames
     *            a hashmap of used component names
     * @param htResponsibilityNames
     *            a hashmap of used responsibility names
     * @param IDConflicts
     *            a vector in which to store id conflicts
     * @param CompNameConflicts
     *            a vector in which to store component name conflicts
     * @param RespNameConflicts
     *            a vector in which to store responsibility name conflicts.
     * 
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
            // find name and id conflicts for components
            findConflicts(htIDs, htComponentNames, IDConflicts, CompNameConflicts, urn, (URNmodelElement) iter.next());
        }

        // look at all responsibilities
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            // find name and id conflicts for responsibilities
            findConflicts(htIDs, htResponsibilityNames, IDConflicts, RespNameConflicts, urn, (URNmodelElement) iter.next());
        }

        for (Iterator iter = urn.getUrndef().getConcerns().iterator(); iter.hasNext();) {
            // find name and id conflicts for responsibilities
            findConflicts(htIDs, null, IDConflicts, null, urn, (URNmodelElement) iter.next());
        }
    }

    /**
     * Resolve ID and naming conflicts; change the ids and names so that no problems subsist.
     * 
     * @param urn
     *            the urn to clean
     * @param htIDs
     *            a hashmap of used ids
     * @param htComponentNames
     *            a hashmap of used component names
     * @param htResponsibilityNames
     *            a hashmap of used responsibility names
     * @param htVariableNames
     *            a hashmap of used variable names
     * @param IDConflicts
     *            a vector in which to store id conflicts
     * @param CompNameConflicts
     *            a vector in which to store component name conflicts
     * @param RespNameConflicts
     *            a vector in which to store responsibility name conflicts.
     * @param VariableNameConflicts
     *            a vector in which to store variable name conflicts.
     */
    private static void resolveConflicts(URNspec urn, HashMap htIDs, HashMap htComponentNames, HashMap htResponsibilityNames, HashMap htVariableNames,
            HashMap htActorNames, HashMap htIntElementNames, HashMap htKpiInfoElementNames, Vector IDConflicts, Vector CompNameConflicts,
            Vector RespNameConflicts, Vector VariableNameConflicts, Vector ActorNameConflicts, Vector IntElementNameConflicts,
            Vector KpiInfoElementNameConflicts) {

        resolveIDConflicts(urn, htIDs, IDConflicts);

        resolveNamingConflicts(urn, htComponentNames, CompNameConflicts);

        resolveNamingConflicts(urn, htResponsibilityNames, RespNameConflicts);

        resolveNamingConflicts(urn, htVariableNames, VariableNameConflicts);

        resolveNamingConflicts(urn, htActorNames, ActorNameConflicts);

        resolveNamingConflicts(urn, htIntElementNames, IntElementNameConflicts);

        resolveNamingConflicts(urn, htKpiInfoElementNames, KpiInfoElementNameConflicts);
    }

    /**
     * Resolve ID conflicts; change the ids so that no problems subsist. Update the URNspec with the new top ID if it changes.
     * 
     * @param urn
     *            the urn to clean
     * @param htIDs
     *            a hashmap of used ids
     * @param IDConflicts
     *            a vector in which to store id conflicts
     */
    private static void resolveIDConflicts(URNspec urn, HashMap htIDs, Vector IDConflicts) {
        String proposedTopID;
        // sort the ids to get the highest one.
        ArrayList ids = new ArrayList(htIDs.keySet());
        Collections.sort(ids, new LongAsStringComparator());

        // because of our calls to isValidID, should be convertible to a Long
        // the proposedTopID is the minimal legal value.

        if (ids.size() > 0) {
            proposedTopID = Long.toString(Long.parseLong((String) ids.get(ids.size() - 1)) + 1);

            // update the ID if necessary
            if (!urn.getNextGlobalID().equals(proposedTopID)) {
                // don't lower the top id; simply increment it if changes
                // occured.
                if (Long.parseLong(proposedTopID) > Long.parseLong(urn.getNextGlobalID()))
                    urn.setNextGlobalID(proposedTopID);
            }
        }

        while (IDConflicts.size() > 0) {
            URNmodelElement elem = (URNmodelElement) IDConflicts.get(0);

            do {
                String oldDescription = "Description..."; //$NON-NLS-1$
                // set it to nothing
                elem.setId(""); //$NON-NLS-1$

                // get the next ID; might take a while.. find first free space.
                if (elem instanceof Belief) {
                    // Bug 555. We do not want to override the description in a duplicated belief.
                    oldDescription = ((Belief) elem).getDescription();
                }
                setElementNameAndID(urn, elem);
                if (elem instanceof Belief) {
                    // Bug 555. Reinsert old description in duplicated belief.
                    ((Belief) elem).setDescription(oldDescription);
                }
            } while (htIDs.containsKey(elem.getId()));
            htIDs.put(elem.getId(), null);

            IDConflicts.remove(0);
        }

    }

    /**
     * Resolve naming conflicts; change the names so that no problems subsist.
     * 
     * @param urn
     *            the urn to clean
     * @param htNames
     *            a hashmap of used names.
     * @param nameConflicts
     *            a vector in which to store conflicts
     */
    private static void resolveNamingConflicts(URNspec urn, HashMap htNames, Vector nameConflicts) {
        // resolve responsibility naming conflicts
        while (nameConflicts.size() > 0) {
            URNmodelElement elem = (URNmodelElement) nameConflicts.get(0);
            int i = 1;

            // it might be a custom name, try setting the default name (maybe we
            // fixed the ID)
            // elem.setName(""); //$NON-NLS-1$
            // setElementNameAndID(urn, elem);
            String initialName = elem.getName();
            if (initialName.equals("")) { //$NON-NLS-1$
                setElementNameAndID(urn, elem);
                initialName = elem.getName();
            }
            // if that didn't work, try adding -1, -2, -3 ... until it works.
            while (htNames.containsKey(elem.getName().toLowerCase())) {
                elem.setName(initialName);
                // setElementNameAndID(urn, elem);
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
     *            a hashmap of used ids
     * @param htNames
     *            a hashmap of used names
     * @param idConflicts
     *            a vector in which to store id conflicts
     * @param nameConflicts
     *            a vector in which to store naming conflicts
     * @param elem
     *            the element to check
     */
    private static void findConflicts(HashMap htIDs, HashMap htNames, Vector idConflicts, Vector nameConflicts, URNspec urn, URNmodelElement elem) {

        if (urn != null) {
            if (!isNameAndIDSet(elem)) {
                setElementNameAndID(urn, elem);
            }
        }

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
     * the name, it uses getPrefix() for most cases, getPrefix() concatenated with the ID for Components and reponsibilities. Does not verify naming unicity.
     * 
     * @param urn
     *            the urnspec containing all the elements
     * @param o
     *            the element to name
     */
    public static void setElementNameAndID(URNspec urn, Object o) {

        // Component, Actors and Responsibility are two special cases;
        // they must have unique names.
        // Generics would help minimize the code for the rest; we could use EMF
        // to determine of the name and id attributes exist but decided to go
        // for legibility
        if (o instanceof URNmodelElement) {
            URNmodelElement ce = (URNmodelElement) o;
            if (ce.getId() == null || ce.getId().trim().length() == 0) {
                ce.setId(getNewID(urn));
            }

            if (ce.getName() == null || ce.getName().trim().length() == 0) {
                ce.setName(getPrefix(o.getClass()) + ce.getId());
            }

            // Set the name properly for intentional elements. "IntentionalElementXXX" is too long...
            if (o instanceof IntentionalElement) {
                // Fix badly named intentional element type Ressource (should have only 1 s...)
                if (((IntentionalElement) o).getType().getValue() == IntentionalElementType.RESSOURCE)
                    ce.setName("Resource" + ce.getId()); //$NON-NLS-1$
                else if (o instanceof Feature)
                	ce.setName("Feature" + ce.getId()); //$NON-NLS-1$
                else
                    ce.setName(((IntentionalElement) o).getType().toString() + ce.getId()); //$NON-NLS-1$
            }
            
            // Set the name properly for mandatory/optional links. "Mandatory/OptionalFMLinkXXX" is too long...
            if (o instanceof MandatoryFMLink) {
              	ce.setName("Mandatory" + ce.getId()); //$NON-NLS-1$
            }
            if (o instanceof OptionalFMLink) {
              	ce.setName("Optional" + ce.getId()); //$NON-NLS-1$
            }

            // Dummy description for beliefs
            if (o instanceof Belief) {
                ((Belief) o).setDescription("Description..."); //$NON-NLS-1$
            }

        } else if (o instanceof EvaluationStrategy) {
            EvaluationStrategy strategy = (EvaluationStrategy) o;
            if (strategy.getId() == null || strategy.getId().trim().length() == 0) {
                strategy.setId(getNewID(urn));
            }

            strategy.setName("Strategy" + strategy.getId()); //$NON-NLS-1$
        } else if (o instanceof ScenarioDef) {
            ScenarioDef scenario = (ScenarioDef) o;
            if (scenario.getId() == null || scenario.getId().trim().length() == 0) {
                scenario.setId(getNewID(urn));
            }

            scenario.setName("Scenario" + scenario.getId()); //$NON-NLS-1$
        } else if (o instanceof Variable) {
            Variable var = (Variable) o;
            if (var.getId() == null || var.getId().trim().length() == 0) {
                var.setId(getNewID(urn));
            }

            if ("boolean".equals(var.getType())) //$NON-NLS-1$
                var.setName(Messages.getString("URNNamingHelper.DefaultPrefixBoolean") + var.getId()); //$NON-NLS-1$
            else if ("integer".equals(var.getType())) //$NON-NLS-1$
                var.setName(Messages.getString("URNNamingHelper.DefaultPrefixInteger") + var.getId()); //$NON-NLS-1$
            else
                var.setName(var.getType() + var.getId());

        } else if (o instanceof URNmodelElement) {
            URNmodelElement model = (URNmodelElement) o;
            if (model.getId() == null || model.getId().trim().length() == 0) {
                model.setId(getNewID(urn));
            }

            if (model.getName() == null || model.getName().trim().length() == 0) {
                model.setName(getPrefix(o.getClass()));
            }
            // } else if (o instanceof URNlink) {
            // URNlink model = (URNlink) o;
            // if (model.getId() == null || model.getId().trim().length() == 0)
            // {
            // model.setId(getNewID(urn));
            // }
            //
            // if (model.getName() == null || model.getName().trim().length() ==
            // 0) {
            // model.setName(getPrefix(o.getClass()));
            // }
        } else {
            System.out.println(Messages.getString("URNNamingHelper.unknownClass")); //$NON-NLS-1$
        }
    }

    /**
     * Changes the top ID in the URNspec; to be used if we find an error.
     * 
     * @param urn
     *            the urnspec to name
     * @param id
     *            the new id
     * @return the new ID
     */
    private static String setTopID(URNspec urn, String id) {
        urn.setNextGlobalID(id);
        return id;
    }

    /**
     * Verifies in the urnspec to see if a actor exists with the proposed name.If you plan on calling resolveNamingConflict after this call, call it directly;
     * this method will only add overhead.
     * 
     * @param urn
     *            the urnspec containg all actors
     * @param proposedName
     *            the proposed name
     * @return true if name exists
     */
    public static boolean doesActorNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    /**
     * Verifies in the urnspec to see if a component exists with the proposed name.If you plan on calling resolveNamingConflict after this call, call it
     * directly; this method will only add overhead.
     * 
     * @param urn
     *            the urnspec containg all components
     * @param proposedName
     *            the proposed name
     * @return true if name exists
     */
    public static boolean doesComponentNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    /**
     * Verifies in the ucmspec to see if the variable name is already in use. If you plan on calling resolveNamingConflict after this call, call it directly;
     * this method will only add overhead.
     * 
     * @param urn
     *            the urnspec containg all variables
     * @param proposedName
     *            the proposed name
     * @return true if name exists
     */
    public static boolean doesVariableNameExist(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    public static String cleanVariableName(String proposedName) {
        proposedName = proposedName.replaceAll("[^\\w]", "_"); //$NON-NLS-1$ //$NON-NLS-2$
        if (proposedName.length() > 0 && proposedName.charAt(0) >= '0' && proposedName.charAt(0) <= '9')
            proposedName = "_" + proposedName.substring(1); //$NON-NLS-1$
        return proposedName;
    }

    /**
     * Verifies in the urnspec to see if an intentionalElement exists with the proposed name.If you plan on calling resolveNamingConflict after this call, call
     * it directly; this method will only add overhead.
     * 
     * @param urn
     *            the urnspec containg all components
     * @param proposedName
     *            the proposed name
     * @return true if name exists
     */
    public static boolean doesIntentionalElementNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            if (element.getName().equalsIgnoreCase(proposedName))
                return true;
        }
        return proposedName.length() == 0;
    }

    /**
     * Verifies in the urnspec to see if a KPIInformationElement exists with the proposed name.If you plan on calling resolveNamingConflict after this call,
     * call it directly; this method will only add overhead.
     * 
     * @param urn
     *            the urnspec containg all components
     * @param proposedName
     *            the proposed name
     * @return true if name exists
     */
    public static boolean doesKPIInformationElementNameExists(URNspec urn, String proposedName) {
        for (Iterator iter = urn.getGrlspec().getKpiInformationElements().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
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
     *            the urnspec containg all responsibilities
     * @param proposedName
     *            the proposed name
     * @return true if resp name exists
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
     *            the urnspec containing all elements.
     * @param elem
     *            the element with a naming conflict
     */
    public static void resolveNamingConflict(URNspec urn, URNmodelElement elem) {
        Collection c;
        if (elem instanceof Responsibility) {
            c = urn.getUrndef().getResponsibilities();
        } else if (elem instanceof Component) {
            c = urn.getUrndef().getComponents();
        } else if (elem instanceof IntentionalElement) {
            c = urn.getGrlspec().getIntElements();
        } else if (elem instanceof Actor) {
            c = urn.getGrlspec().getActors();
        } else if (elem instanceof Variable) {
            c = urn.getUcmspec().getVariables();
        } else if (elem instanceof KPIInformationElement) {
            c = urn.getGrlspec().getKpiInformationElements();
        } else {
            System.out.println(Messages.getString("URNNamingHelper.unableToResolve")); //$NON-NLS-1$
            if (elem != null)
                System.out.println("\t(" + elem.getClass().getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }

        HashMap names = new HashMap();
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            names.put(element.getName().toLowerCase(), null);
        }

        if (names.containsKey(elem.getName().toLowerCase())) {
            Vector v = new Vector();
            v.add(elem);
            resolveNamingConflicts(urn, names, v);
        }

    }

    /**
     * Checks to see if the given name is valid, in the given context
     * 
     * @param urn
     *            the urnspec containin all the names.
     * @param elem
     *            the element to name. (a componentref or respref)
     * @param name
     *            the proposed name.
     * @return an empty string if unused
     */
    public static String isNameValid(URNspec urn, URNmodelElement elem, String name) {
        String message = ""; //$NON-NLS-1$

        if (name!=null) 
            name = name.trim();
        else 
            name = ""; //$NON-NLS-1$
        
        if (elem instanceof IURNContainerRef || elem instanceof RespRef || elem instanceof Responsibility || elem instanceof IntentionalElementRef
                || elem instanceof IntentionalElement || elem instanceof KPIInformationElementRef || elem instanceof KPIInformationElement
                || elem instanceof IURNContainer) {
            if (name.trim().length() == 0) {
                message = Messages.getString("URNNamingHelper.invalidName"); //$NON-NLS-1$
            }
        }
        if (!getName(elem).equals(name)) /* bug 787 made this case-insensitive - partially */ {
            if (elem instanceof ComponentRef || elem instanceof Component) {
                if (URNNamingHelper.doesComponentNameExists(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.compNameExist"); //$NON-NLS-1$
                }
            } else if (elem instanceof ActorRef || elem instanceof Actor) {
                if (URNNamingHelper.doesActorNameExists(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.ActorNameAlreadyExists"); //$NON-NLS-1$
                }
            } else if (elem instanceof RespRef || elem instanceof Responsibility) {
                if (URNNamingHelper.doesResponsibilityNameExists(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.respNameExist"); //$NON-NLS-1$
                }
            } else if (elem instanceof IntentionalElementRef || elem instanceof IntentionalElement) {
                if (URNNamingHelper.doesIntentionalElementNameExists(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.IntentionalElementNameAlreadyExists"); //$NON-NLS-1$
                }
            } else if (elem instanceof KPIInformationElementRef || elem instanceof KPIInformationElement) {
                if (URNNamingHelper.doesKPIInformationElementNameExists(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.KPIInformationElementNameAlreadyExists"); //$NON-NLS-1$
                }
            } else if (elem instanceof Variable) {
                if (URNNamingHelper.doesVariableNameExist(urn, name) && !getName(elem).equalsIgnoreCase(name)) {
                    message = Messages.getString("URNNamingHelper.VariableNameAlreadyExists"); //$NON-NLS-1$
                }
            }
        }

        return message;
    }

    public static String getName(ContributionChange change) {
        Contribution contrib = change.getContribution();
        if (contrib == null)
            return ""; //$NON-NLS-1$
        else {
            int val = change.getNewQuantitativeContribution();
            //val = StrategyEvaluationPreferences.getValueToVisualize(val);
            return getName(contrib.getSrc()) + " (" + change.getNewContribution().getName() + "/" + val + ") " + getName(contrib.getDest()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }
    /**
     * Returns the name of the definition if this is a reference, and its direct name otherwise.
     * 
     * @param elem
     *            the element for which we want the name.
     * @return the name
     */
    public static String getName(URNmodelElement elem) {
        if (elem instanceof IURNContainerRef) {
            IURNContainerRef ref = (IURNContainerRef) elem;
            return getName((URNmodelElement) ref.getContDef());
        } else if (elem instanceof RespRef) {
            RespRef ref = (RespRef) elem;
            return getName(ref.getRespDef());
        } else if (elem instanceof IntentionalElementRef) {
            IntentionalElementRef ref = (IntentionalElementRef) elem;
            return getName(ref.getDef());
        } else if (elem instanceof KPIInformationElementRef) {
            KPIInformationElementRef ref = (KPIInformationElementRef) elem;
            return getName(ref.getDef());
        } else if (elem instanceof Contribution)
        {
            Contribution contrib = (Contribution)elem;
            int val = contrib.getQuantitativeContribution();
            //val = StrategyEvaluationPreferences.getValueToVisualize(val);
            return getName(contrib.getSrc()) + " (" + contrib.getContribution().getName() + "/" + val + ") " + getName(contrib.getDest());    //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
         if (elem == null)
            return ""; //$NON-NLS-1$
        else
            return elem.getName();
    }

    /**
     * Returns the label if it is defined or a truncated version of the expression otherwise.
     * 
     * @param cond
     *            the condition for which to get the name
     * @return the name
     */
    public static String getName(Condition cond) {
        return getName(cond, cond.getExpression());
    }

    /**
     * Returns the label if it is defined or a truncated version of the expression otherwise.
     * 
     * Uses the expression that is passed as a possible replacement for the condition's current expression.
     * 
     * @param cond
     *            the condition for which to get the name
     * 
     * @param expression
     *            a proposed expression that replaces cond.getExpression()
     * 
     * @return the name
     */
    public static String getName(Condition cond, String expression) {
        String name = null;
        if (cond.getLabel() != null && cond.getLabel().length() > 0) {
            name = cond.getLabel();
        } else {
            if (cond.eContainer() instanceof PluginBinding) {
                PluginBinding binding = (PluginBinding) cond.eContainer();
                name = getName(binding.getStub()) + " <-> " + getName(binding.getPlugin()); //$NON-NLS-1$
            } else if (cond.eContainer() instanceof NodeConnection) {
                NodeConnection connection = (NodeConnection) cond.eContainer();
                if (connection.getSource() instanceof Timer) {
                    if (connection.getSource().getSucc().indexOf(connection) == 0)
                        return Messages.getString("URNNamingHelper.NormalPath") + getNameFromExpression(expression); //$NON-NLS-1$
                    else
                        return Messages.getString("URNNamingHelper.TimeoutPath") + getNameFromExpression(expression); //$NON-NLS-1$

                } else if (connection.getSource() instanceof OrFork || connection.getSource() instanceof WaitingPlace) {
                    return Messages.getString("URNNamingHelper.Branch") + (connection.getSource().getSucc().indexOf(connection) + 1) + Messages.getString("URNNamingHelper.ColonSpace") + getNameFromExpression(expression); //$NON-NLS-1$ //$NON-NLS-2$
                } else if (connection.getSource() instanceof FailurePoint) {
                    return Messages.getString("URNNamingHelper.FailureCondition") + getNameFromExpression(expression); //$NON-NLS-1$
                } else
                    name = getNameFromExpression(expression);

            } else {
                name = getNameFromExpression(expression);
            }

        }
        return name;
    }

    /**
     * Returns the label if it is defined or a truncated version of the expression otherwise.
     * 
     * Uses the expression that is passed as a possible replacement for the condition's current expression.
     * 
     * @param expression
     *            an expression
     * 
     * @return the name
     */
    public static String getNameFromExpression(String expression) {
        String name;
        name = expression;
        if (name == null)
            name = ""; //$NON-NLS-1$

        name = name.replace("\r", ""); //$NON-NLS-1$ //$NON-NLS-2$
        name = name.replace('\n', ' ');
        if (name.length() > 40) {
            name = name.substring(0, 37) + "..."; //$NON-NLS-1$
        }
        return name;
    }

    /**
     * Checks to see if the given name is valid, in the given context. calls isNameValid(URNspec, UCMmodelElement, String) using the URNspec inferred from the
     * UCMmodelElement. Only works if element is already in URNspec.
     * 
     * @param elem
     *            the element to check.
     * @param name
     *            the proposed name
     * @return an empty string if unused
     */
    public static String isNameValid(URNmodelElement elem, String name) {
        EObject parent = elem;

        while (!(parent instanceof URNspec)) {
            if (parent == null)
                return Messages.getString("URNNamingHelper.elementNotInUrnspec"); //$NON-NLS-1$

            parent = parent.eContainer();
        }
        return isNameValid((URNspec) parent, elem, name);
    }

    /**
     * Returns a sorted list of GRL variable names (intentional elements)
     * 
     * @param urn
     *            the source URN model
     * @return the sorted list of GRL variables.
     */
    public static Vector getGrlVariableNames(URNspec urn) {
        Vector v2 = new Vector();
        for (int i = 0; i < urn.getGrlspec().getIntElements().size(); i++) {
            v2.add(getGrlVariableName((IntentionalElement) urn.getGrlspec().getIntElements().get(i)));
        }

        Collections.sort(v2);
        return v2;
    }

    /**
     * Returns an intentional element's variable for use in UCM scenarios.
     * 
     * @param element
     *            the intentional element
     * @return the variable name
     */
    public static String getGrlVariableName(IntentionalElement element) {
        return getGrlVariableName(element.getName());
    }

    /**
     * Returns an intentional element's variable for use in UCM scenarios.
     * 
     * @param elementName
     *            the intentional element
     * @return the variable name
     */
    public static String getGrlVariableName(String elementName) {
        return URNNamingHelper.cleanVariableName("_GRL_" + elementName); //$NON-NLS-1$
    }

}