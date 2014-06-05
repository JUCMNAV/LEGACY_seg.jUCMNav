package seg.jUCMNav.importexport.msc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.resourceManagement.UcmScenariosModelManager;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import seg.jUCMNav.model.util.modelexplore.queries.ResponsibilityFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ResponsibilityFinder.QFindResponsibilities;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucmscenarios.Event;
import ucmscenarios.EventType;
import ucmscenarios.Instance;
import ucmscenarios.Message;
import ucmscenarios.ModelElement;
import ucmscenarios.Parallel;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.Sequence;
import ucmscenarios.UcmscenariosFactory;
import ucmscenarios.Metadata;
import urn.URNspec;
import urncore.Component;
import urncore.Condition;
import urncore.Responsibility;
import urncore.URNmodelElement;


/**
 * Generates an scenario xml from a URNspec produced by MscTraversalListener
 * 
 * TODO: all id's are from the generated URN, not the source. TODO: scenario preconditions/postconditions
 * 
 * @author jkealey
 * 
 */
public class ScenarioGenerator {

    private static int msgId = 0;
    private ucmscenarios.Component _environmentComponent;
    private Instance _lastEnvironmentInstance;

    // TODO: parallel is only an approximation in the current implementation; works well except for parallel scenariostartpoints that merge into each other
    private boolean ARE_SCENARIO_STARTPOINTS_PARALLEL = false;

    // factory
    private UcmscenariosFactory f = UcmscenariosFactory.eINSTANCE;
    private HashMap hmCompDefToComponent;

    private HashMap hmCompRefToInstance;
    private HashMap processedPathNodes;

    private HashMap queuedMessages;

    private ScenarioSpec scenariospec;

    private URNspec urnspec;

    public ScenarioGenerator(URNspec urnspec) {
        this.urnspec = urnspec;

    }

    /**
     * For each of the and-fork's branches, create a sub-branch (child of parallel) until the next and-join or until the end of the path if none exists.
     * 
     * @param seq
     *            where to insert
     * @param fork
     *            what to insert
     * @return the fork's ComponentRef
     */
    private ComponentRef addAllBranches(Sequence seq, AndFork fork) {

        // create the new parallel element.
        Parallel par = f.createParallel();
        processedPathNodes.put(fork, par);

        par.setSequence(seq);
        par.setName(fork.getName());
        ComponentRef compRef = (ComponentRef) fork.getContRef();

        Vector common = null;

        // for all branches
        // TODO: might be able to optimize
        for (int i = 0; i < fork.getSucc().size(); i++) {

            // follow all other branches
            HashSet ncs = new HashSet();
            ncs.addAll(fork.getSucc());
            ncs.remove(fork.getSucc().get(i));
            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(fork, ncs, QFindReachableNodes.DIRECTION_FORWARD);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();

            if (common == null) // first one, keep the whole list
                common = vReachable;
            else
                common.retainAll(vReachable); // filter out what was not reachable.

        }

        common.remove(fork);
        // at this point, common contains only the path nodes that are common to all of the and-fork's branches

        // create all subbranches, stopping at the common and-join if it exists.
        for (int i = 0; i < fork.getSucc().size(); i++) {
            NodeConnection nc = (NodeConnection) fork.getSucc().get(i);
            Sequence subseq = f.createSequence();

            ComponentRef branchCompRef = addPath(subseq, (PathNode) nc.getTarget(), common.size() == 0 ? null : (PathNode) common.firstElement());
            subseq.setParent(par);
        }
        return compRef;

    }

    /**
     * Adds all component definitions under a specific scenario.
     * 
     * @param scenarios
     *            the scenario
     */
    private void addComponentDefinitions(ScenarioSpec scenarios) {

        ucmscenarios.Component comp = f.createComponent();
        comp.setId("C0"); //$NON-NLS-1$
        comp.setName(Messages.getString("ScenarioGenerator.Environment")); //$NON-NLS-1$
        comp.setDescription(Messages.getString("ScenarioGenerator.TheExternalEnvironment")); //$NON-NLS-1$
        comp.setScenarioSpec(scenarios);

        _environmentComponent = comp;

        for (Iterator iter = urnspec.getUrndef().getComponents().iterator(); iter.hasNext();) {
            Component element = (Component) iter.next();
           
            
            comp = f.createComponent();
            setIdNameDesc(element, comp);
            comp.setScenarioSpec(scenarios);
            hmCompDefToComponent.put(element, comp);
        }
    }

    /**
     * Creates instances for each component reference in the given map.
     * 
     * @param scenario
     *            where to add the instances
     * @param map
     *            the map containing the component references.
     */
    private void addComponentReferences(ucmscenarios.ScenarioDef scenario, UCMmap map) {
        Instance instance = f.createInstance();
        instance.setId("I0"); //$NON-NLS-1$
        instance.setName(Messages.getString("ScenarioGenerator.Environment")); //$NON-NLS-1$
        instance.setDescription(Messages.getString("ScenarioGenerator.TheExternalEnvironment")); //$NON-NLS-1$
        instance.setScenario(scenario);
        instance.setDefinition(_environmentComponent);
        _lastEnvironmentInstance = instance;
        for (Iterator iter = map.getContRefs().iterator(); iter.hasNext();) {
            ComponentRef element = (ComponentRef) iter.next();           
            instance = f.createInstance();
            setIdNameDesc(element, instance);
            // refs have no useful names.
            instance.setName(((Component) element.getContDef()).getName());
            instance.setScenario(scenario);
            hmCompRefToInstance.put(element, instance);
        }
    }

    /**
     * Creates a condition
     * 
     * @param seq
     *            where to add it
     * @param wp
     *            from which waiting place.
     * @return the component reference to which the waiting place was bound.
     */
    private ComponentRef addCondition(Sequence seq, WaitingPlace wp) {
        ComponentRef compRef = (ComponentRef) wp.getContRef();
        
        
        Condition cond = ((NodeConnection) wp.getSucc().get(0)).getCondition();
        assert cond != null;

        ucmscenarios.Condition condition = f.createCondition();
        processedPathNodes.put(wp, condition);
        setIdNameDesc(wp, condition);

        // TODO: we currently don't have labels on our generated NCs
        // condition.setLabel(cond.getLabel());
        condition.setLabel(wp.getName());
        condition.setExpression(cond.getExpression());

        condition.setInstance(getInstance(compRef));

        // add preconditions before start point.
        if (MetadataHelper.getMetaData(wp, "isPreCondition") != null && "true".equalsIgnoreCase(MetadataHelper.getMetaData(wp, "isPreCondition"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        {
            if (seq.getChildren().size() > 0 && seq.getChildren().get(seq.getChildren().size() - 1) instanceof Event) {
                Event event = (Event) seq.getChildren().get(seq.getChildren().size() - 1);
                if (event.getType() == EventType.START_POINT_LITERAL)
                    seq.getChildren().add(seq.getChildren().size() - 1, condition);
                else
                    condition.setSequence(seq);

            }
        } else
            condition.setSequence(seq);

        return compRef;

    }

    /**
     * Creates an event from a responsibility reference.
     * 
     * @param seq
     *            where to add the event
     * @param respref
     *            the source element
     * @return the component reference to which the responsibility is bound.
     */
    private ComponentRef addDo(Sequence seq, RespRef respref) {
        Event action = f.createEvent();
        processedPathNodes.put(respref, seq);
        setIdNameDesc(getDef(respref), action);
        // override id
        action.setId(respref.getId());
        action.setType(EventType.RESPONSIBILITY_LITERAL);

        ComponentRef compRef = (ComponentRef) respref.getContRef();
        action.setInstance(getInstance(compRef));
        action.setSequence(seq);
        return compRef;
    }

    /**
     * Creates an event.
     * 
     * @param seq
     *            where to add the event
     * @param pn
     *            the source element.
     * @return the component reference to which the path node is bound.
     */
    private ComponentRef addDoSimple(Sequence seq, PathNode pn) {
        Event action = f.createEvent();
        processedPathNodes.put(pn, action);

        setIdNameDesc(pn, action);
        ComponentRef compRef = (ComponentRef) pn.getContRef();
        action.setInstance(getInstance(compRef));

        
        
        if (pn instanceof StartPoint)
            action.setType(EventType.START_POINT_LITERAL);
        else if (pn instanceof EndPoint)
            action.setType(EventType.END_POINT_LITERAL);
        else if (pn instanceof Timer) {
            action.setType(EventType.TIMEOUT_LITERAL);
        } else if (pn instanceof DirectionArrow) {
            EventType type = EventType.get(MetadataHelper.getMetaData(pn, "type")); //$NON-NLS-1$
            action.setType(type);  
            if (type == EventType.WP_ENTER_LITERAL){
                action.setName(MetadataHelper.getMetaData(pn, "name") + Messages.getString("ScenarioGenerator.SpaceEnter")); //$NON-NLS-1$ //$NON-NLS-2$
                if (MetadataHelper.getMetaData(pn, "period") != null )
                	MetadataHelper.addMetaData(urnspec, action, "period", MetadataHelper.getMetaData(pn, "period"));
                }
            else if (type == EventType.WP_LEAVE_LITERAL) {
                action.setName(MetadataHelper.getMetaData(pn, "name") + Messages.getString("ScenarioGenerator.SpaceLeave")); //$NON-NLS-1$ //$NON-NLS-2$
            } else if (type == EventType.TIMER_SET_LITERAL){
            	action.setName(MetadataHelper.getMetaData(pn, "name") + Messages.getString("ScenarioGenerator.SpaceSet")); //$NON-NLS-1$ //$NON-NLS-2$
            	  if (MetadataHelper.getMetaData(pn, "period") != null )
                  	MetadataHelper.addMetaData(urnspec, action, "period", MetadataHelper.getMetaData(pn, "period"));
            }else if (type == EventType.TIMER_RESET_LITERAL) {
                action.setName(MetadataHelper.getMetaData(pn, "name") + Messages.getString("ScenarioGenerator.SpaceReset")); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }

        action.setSequence(seq);
        return compRef;
    }

    /**
     * Creates a message between two instances.
     * 
     * @param element
     *            where to add it
     * @param from
     *            the sending instance
     * @param to
     *            the receiving instance
     * @param src
     *            what event caused the message
     * @param target
     *            what event is blocked until this message?
     * @return the message
     */
    private Message addMessage(Sequence element, ComponentRef from, ComponentRef to, PathNode src, PathNode target) {
        return addMessage(element, -1, from, to, src, target);
    }

    /**
     * Creates a message between two isntances.
     * 
     * @param element
     *            where to add it
     * @param index
     *            what position should this message have in the queue.
     * @param from
     *            the sending instance
     * @param to
     *            the receiving instance
     * @param src
     *            what event caused the message
     * @param target
     *            what event is blocked until this message?
     * @return the message
     */
    private Message addMessage(Sequence element, int index, ComponentRef from, ComponentRef to, PathNode src, PathNode target) {
        Message msg = f.createMessage();

        // msg.setId("M" + ++msgId);
        msg.setId(src.getId() + "_" + target.getId()); //$NON-NLS-1$

        if (target instanceof Timer) {
            msg.setName(Messages.getString("ScenarioGenerator.SetSpace") + MetadataHelper.getMetaData(target, "name")); //$NON-NLS-1$ //$NON-NLS-2$
            msg.setDescription(target.getDescription());
        }
        if (target instanceof EndPoint || target instanceof WaitingPlace || target instanceof DirectionArrow) {
            msg.setName(target.getName());
            msg.setDescription(target.getDescription());
        } else if (target instanceof RespRef) {
            msg.setName(getDef((RespRef) target).getName());
            msg.setDescription(getDef((RespRef) target).getDescription());
        } else {
            msg.setName(Messages.getString("ScenarioGenerator.DefaultMessageName")); //$NON-NLS-1$
            msg.setDescription(Messages.getString("ScenarioGenerator.DefaultMessageDescription")); //$NON-NLS-1$
        }

        // the message MUST be added later or else we'll get serialization errors
        msg.setSource(getInstance(from));
        msg.setTarget(getInstance(to));

        if (element != null) {

            if (index < 0)
                element.getChildren().add(msg);
            else
                element.getChildren().add(index, msg);
        } else {
            // this is normal for queued messages
            // System.out.println("Creating a message but we have no element to add it to!");
        }

        return msg;
    }

    /**
     * Adds a path
     * 
     * @param seq
     *            where to add it
     * @param start
     *            where to start
     * @param stopAtNode
     *            where to stop, null if stop at end point
     * @return the initial component reference.
     */
    private ComponentRef addPath(Sequence seq, PathNode start, PathNode stopAtNode) {
        QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(start, new HashSet(), QFindReachableNodes.DIRECTION_FORWARD);
        ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
        Vector vReachable = rReachableNodes.getNodes();
        ComponentRef initialCompRef = (ComponentRef) start.getContRef();
        ComponentRef compRef = null;
        int index = -1;
        for (int i = 0; i < vReachable.size(); i++) {
            PathNode pn = (PathNode) vReachable.get(i);

            // add queued messages
            if (queuedMessages.containsKey(pn)) {
                ArrayList msgs = (ArrayList) queuedMessages.get(pn);
                for (Iterator iter = msgs.iterator(); iter.hasNext();) {
                    Message msg = (Message) iter.next();
                    msg.setSequence(seq);
                }
                msgs.clear();
            }

            // stop when subbranch is done
            if (pn == stopAtNode)
                break;

            // skip subbranch elements covered by other calls.
            if (processedPathNodes.containsKey(pn)) {
                continue;
            }

            EventType type = null;
            if (pn instanceof RespRef) {
                compRef = addDo(seq, (RespRef) pn);
            } else if (pn instanceof DirectionArrow) {
                type = EventType.get(MetadataHelper.getMetaData(pn, "type")); //$NON-NLS-1$
                // these types are ignored.
                if (type == EventType.CONNECT_END_LITERAL || type == EventType.CONNECT_START_LITERAL) { // || type == EventType.TRIGGER_END_LITERAL) {
                    // continue;
                    compRef = (ComponentRef) pn.getContRef();
                } else if (type == EventType.TRIGGER_END_LITERAL) {
                    // compRef = (ComponentRef) pn.getContRef();
                    continue;
                } else
                    compRef = addDoSimple(seq, pn);

                if (!processedPathNodes.containsKey(pn))
                    processedPathNodes.put(pn, "ignored"); //$NON-NLS-1$

            } else if (pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Timer) {
                compRef = addDoSimple(seq, pn);
            } else if (pn instanceof WaitingPlace) {
                compRef = addCondition(seq, (WaitingPlace) pn);
            } else if (pn instanceof AndFork) {
                compRef = addAllBranches(seq, (AndFork) pn);
            } else if (pn instanceof AndJoin) {
                compRef = (ComponentRef) pn.getContRef();

                processedPathNodes.put(pn, new Object[] { seq, new Integer(seq.getChildren().size()) });
            } else {
                //System.out.println("unexpected pathnode"); //$NON-NLS-1$
                continue;
            }

            // skip flow points
            if (pn instanceof AndFork || pn instanceof AndJoin)
                continue;

            // infer messages to be sent.
            // find the next responsibilities
            QFindResponsibilities qReachableResponsibilities = new ResponsibilityFinder.QFindResponsibilities(pn, new HashSet(),
                    QFindResponsibilities.DIRECTION_FORWARD, false, true);
            ResponsibilityFinder.RNextResponsibilities rReachableResponsibilities = (ResponsibilityFinder.RNextResponsibilities) GraphExplorer
                    .run(qReachableResponsibilities);
            Vector vResponsibilities = rReachableResponsibilities.getNodes();

            int count = 0;
            for (int j = 0; j < vResponsibilities.size(); j++) {
                PathNode next = (PathNode) vResponsibilities.get(j);
                ComponentRef nextCompRef = (ComponentRef) next.getContRef();
                if (nextCompRef != compRef) {
                    count++;
                }
            }
            // count is the number of messages that will be sent.

            if (count >= 2) {
                // delay creation so that it appears inside the concurrency and not before it
                for (int j = 0; j < vResponsibilities.size(); j++) {
                    PathNode next = (PathNode) vResponsibilities.get(j);
                    ComponentRef nextCompRef = (ComponentRef) next.getContRef();
                    if (nextCompRef != compRef) {
                        enqueueMessage(compRef, pn, next, nextCompRef);
                    }
                }
            } else if (count > 0) {

                // add on current path
                for (int j = 0; j < vResponsibilities.size(); j++) {
                    PathNode next = (PathNode) vResponsibilities.get(j);
                    ComponentRef nextCompRef = (ComponentRef) next.getContRef();
                    if (nextCompRef != compRef) {
                        // this solves some issues but may cause others; to explore.
                        if (type == EventType.WP_LEAVE_LITERAL || type == EventType.TIMER_RESET_LITERAL)
                            enqueueMessage(compRef, pn, next, nextCompRef);
                        else
                            addMessage(seq, compRef, nextCompRef, pn, next);

                    }
                }
            }

        }

        return initialCompRef;
    }

    private void enqueueMessage(ComponentRef compRef, PathNode pn, PathNode next, ComponentRef nextCompRef) {
        Message msg = addMessage(null, compRef, nextCompRef, pn, next);
        if (!queuedMessages.containsKey(next))
            queuedMessages.put(next, new ArrayList());

        ((ArrayList) queuedMessages.get(next)).add(msg);
    }

    /**
     * Creates a path for the given scenario
     * 
     * @param out
     *            where to add it
     * @param in
     *            which scenario should be added (starting at its scenario start points)
     * @param map
     *            the source map containing the path nodes.
     */
    private void addPath(ucmscenarios.ScenarioDef out, ScenarioDef in, UCMmap map) {
        Sequence seq = f.createSequence();

        // TODO: semantic variation point.
        if (ARE_SCENARIO_STARTPOINTS_PARALLEL) {
            if (in.getStartPoints().size() > 1) {
                // multiple paths started in parallel
                Parallel par = f.createParallel();
                par.setSequence(seq);

                // inverse order by convention
                // for (int i = in.getStartPoints().size() - 1; i >= 0; i--) {
                for (int i = 0; i < in.getStartPoints().size(); i++) {
                    ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
                    StartPoint sp = ssp.getStartPoint();

                    Sequence seq2 = f.createSequence();
                    // TODO: we assume they are in parallel but we may run into merged paths from different scenario start points
                    // TODO: look at code below for sequencing, but move the nodes after the join point on seq, instead of their original sequence inside
                    // parallel
                    addPath(seq2, sp, null);
                    seq2.setParent(par);
                }

            } else {
                ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(0);

                addPath(seq, ssp.getStartPoint(), null);
            }
        } else {
            Vector[] reachable = new Vector[in.getStartPoints().size()];

            for (int i = 0; i < in.getStartPoints().size(); i++) {
                ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
                QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(ssp.getStartPoint(), new HashSet(),
                        QFindReachableNodes.DIRECTION_FORWARD);
                ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
                reachable[i] = rReachableNodes.getNodes();
            }

            // inverse order by convention
            // for (int i = in.getStartPoints().size() - 1; i >= 0; i--) {
            for (int i = 0; i < in.getStartPoints().size(); i++) {
                ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
                StartPoint sp = ssp.getStartPoint();

                Sequence seq2 = f.createSequence();

                addPath(seq2, sp, null);

                // look to see if was merged in other path.
                // for (int j = i - 1; j < in.getStartPoints().size(); j++) {
                for (int j = 0; j < i; j++) {
                    // if (j<0 || j==i)break;
                    Vector curr = (Vector) reachable[i].clone();
                    curr.retainAll(reachable[j]);

                    // basically, we're try to figure out if seq2 was merged into an and-join. if so, we need to add seq2 as a child before and-join
                    if (curr.size() > 0) {
                        PathNode join = (PathNode) curr.firstElement();
                        assert join instanceof AndJoin;
                        assert processedPathNodes.containsKey(join);

                        Object[] location = (Object[]) processedPathNodes.get(join);
                        Sequence location_seq = (Sequence) location[0];
                        Integer location_pos = (Integer) location[1];

                        // location_seq.getChildren().add(location_pos.intValue() == 0 ? 0 : location_pos.intValue() - 1, seq2);

                        int where = -1;

                        // the following if checks to see if we're inserting a path right after a timer reset or waiting place leave (which are consecutive). if
                        // so, lets insert ourselves in between the two
                        if (location_pos.intValue() > 0 && location_pos.intValue() - 1 < location_seq.getChildren().size()
                                && location_seq.getChildren().get(location_pos.intValue() - 1) instanceof Event) {
                            Event previous = (Event) location_seq.getChildren().get(location_pos.intValue() - 1);
                            if (previous.getType().getValue() == EventType.TIMER_RESET || previous.getType().getValue() == EventType.WP_LEAVE) {
                                location_seq.getChildren().add(location_pos.intValue() - 1, seq2);
                                where = location_pos.intValue();
                            }

                        }

                        if (where == -1) {
                            if (location_pos.intValue() >= location_seq.getChildren().size())
                                location_pos = new Integer(location_seq.getChildren().size()); // last
                            location_seq.getChildren().add(location_pos.intValue(), seq2);
                            where = location_pos.intValue();
                        }

                        // update ALL pointers for next, not just this one.
                        // location[1] = new Integer(location_pos.intValue() +1);
                        for (Iterator iter = processedPathNodes.values().iterator(); iter.hasNext();) {
                            Object o = (Object) iter.next();
                            if (o instanceof Object[]) {
                                Integer location_pos2 = (Integer) ((Object[]) o)[1];
                                if (location_pos2.intValue() >= where) {
                                    ((Object[]) o)[1] = new Integer(location_pos2.intValue() + 1);
                                }

                            }

                        }

                        break;
                    }

                }

                // nope, not merged!
                if (seq2.getSequence() == null)
                    seq2.setSequence(seq);

            }
        }
        seq.setParentScenario(out);
    }

    /**
     * Add a scenario
     * 
     * @param in
     *            the source scenario
     * @param out
     *            the target scenario
     * @return false if this scenario does not have any start points, true otherwise.
     */
    private boolean addScenario(ScenarioDef in, ucmscenarios.ScenarioDef out) {
        processedPathNodes = new HashMap();
        queuedMessages = new HashMap();

        if (in.getStartPoints().size() > 0) {
            // assumption: all SSP point to the same map.

            ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(0);
            UCMmap map = (UCMmap) ssp.getStartPoint().getDiagram();

            addComponentReferences(out, map);

            addPath(out, in, map);

            if (_lastEnvironmentInstance.getSent().size() == 0 && _lastEnvironmentInstance.getReceived().size() == 0
                    && _lastEnvironmentInstance.getElements().size() == 0) {
                _lastEnvironmentInstance.setDefinition(null);
                _lastEnvironmentInstance.setScenario(null);
            }

            return true;
        } else
            return false;
    }

    /**
     * Add all scenarios in the group, if they have start points.
     * 
     * @param in
     *            the source scenario group
     * @param out
     *            the target scenario group
     */
    private void addScenarios(ScenarioGroup in, ucmscenarios.ScenarioGroup out) {

        for (Iterator iter = in.getScenarios().iterator(); iter.hasNext();) {
            ScenarioDef element = (ScenarioDef) iter.next();

            ucmscenarios.ScenarioDef scenario = f.createScenarioDef();
            setIdNameDesc(element, scenario);

            boolean b = addScenario(element, scenario);

            for (Iterator iterator = processedPathNodes.values().iterator(); iterator.hasNext();) {
                Object model = (Object) iterator.next();
                // if parallel
                // if timer reset at end of child sequence, move it outside after the parallel
                if (model instanceof Parallel) {
                    Parallel parallel = (Parallel) model;
                    for (Iterator it = parallel.getChildren().iterator(); it.hasNext();) {
                        Sequence child = (Sequence) it.next();
                        if (child.getChildren().size() > 0 && child.getChildren().get(child.getChildren().size() - 1) instanceof Event) {
                            Event ev = (Event) child.getChildren().get(child.getChildren().size() - 1);
                            if (ev.getType() == EventType.TIMER_RESET_LITERAL) {
                                ev.setSequence(null);
                                parallel.getSequence().getChildren().add(parallel.getSequence().getChildren().indexOf(parallel) + 1, ev);
                            }
                        }
                    }

                }

            }

            scenario.setGroup(out);

        }

    }

    /**
     * Make sure we don't get any NPEs by replacing null with an empty string.
     * 
     * @param s
     *            the string to convert
     * @return a non-null String
     */
    private String fix(String s) {
        if (s == null)
            return ""; //$NON-NLS-1$
        else
            return s;
    }

    /**
     * Returns the target component related to a source component definition.
     * 
     * @param comp
     *            the component definition
     * @return the component.
     */
    private ucmscenarios.Component getComponent(Component comp) {

        if (hmCompDefToComponent.containsKey(comp))
            return (ucmscenarios.Component) hmCompDefToComponent.get(comp);
        else
            return _environmentComponent;

    }

    /**
     * Returns the component reference's definition
     * 
     * @param element
     *            the reference
     * @return the definition
     */
    private Component getDef(ComponentRef element) {
        return ((Component) element.getContDef());
    }

    /**
     * Returns the responsibility reference's definition
     * 
     * @param element
     *            the reference
     * @return the definition
     */
    private Responsibility getDef(RespRef element) {
        return (element.getRespDef());
    }

    /**
     * Returns the instance associated with a component reference. Returns the environment instance if none is found.
     * 
     * @param comp
     *            the reference
     * @return the instance
     */
    private Instance getInstance(ComponentRef comp) {

        if (hmCompRefToInstance.containsKey(comp))
            return (Instance) hmCompRefToInstance.get(comp);
        else
            return _lastEnvironmentInstance;
    }

    /**
     * Returns the condition's label, or its expression if it is null.
     * 
     * @param cond
     *            the condition
     * @return a string representing the condition
     */
    private String getLabel(Condition cond) {
        if (cond.getLabel() != null && cond.getLabel().length() > 0)
            return "[" + cond.getLabel() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        else
            return "[" + cond.getExpression() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Returns the target scenario; caches the result for future calls.
     * 
     * @return the target scenario
     */
    public ScenarioSpec getScenarioDocument() {
        if (this.scenariospec == null) {
            this.hmCompDefToComponent = new HashMap();
            this.hmCompRefToInstance = new HashMap();

            scenariospec = f.createScenarioSpec();

            scenariospec.setCreated(urnspec.getCreated());
            scenariospec.setModified(urnspec.getModified());
            scenariospec.setName(urnspec.getName());
            scenariospec.setSpecVersion(urnspec.getSpecVersion());
            scenariospec.setDescription(urnspec.getDescription());

            // TODO: stop using temporary field.
            scenariospec.setFilename(urnspec.getAuthor());

            // urnspec has no ID
            // scenariospec.setId("");

            addComponentDefinitions(scenariospec);

            for (Iterator iter = urnspec.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
                ScenarioGroup element = (ScenarioGroup) iter.next();
                ucmscenarios.ScenarioGroup group = f.createScenarioGroup();

                setIdNameDesc(element, group);

                addScenarios(element, group);

                group.setScenarioSpec(scenariospec);

            }

            if (_environmentComponent.getInstances().size() == 0)
                _environmentComponent.setScenarioSpec(null);

        }

        return this.scenariospec;

    }

    /**
     * Save the model to the given path.
     * 
     * @param path
     *            the path
     */
    public void save(File path) {
        UcmScenariosModelManager mgr = new UcmScenariosModelManager();        
        mgr.createScenarioSpec(path, getScenarioDocument());
    }

    /**
     * Sets a target element's id, name, and description to that of the source element.,
     * 
     * @param in
     *            the source element
     * @param out
     *            the target element
     */
    private void setIdNameDesc(URNmodelElement in, ModelElement out) {
        out.setId(in.getId());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        
        if(!in.getMetadata().isEmpty() && in.getMetadata() != null ){
        	
            for ( Object currentObj : in.getMetadata()){
            	urncore.Metadata currentMetadata = (urncore.Metadata)currentObj;
            	//System.out.println("We are accessing metadata" + in.getMetadata().get(0) + "   Metadata objet = " + in);
            }
        	
        	for ( Object currentObject : in.getMetadata()){
        		urncore.Metadata currentUrnMetadata = (urncore.Metadata) currentObject;
        		
        		Metadata currentScenMetadata = f.createMetadata();
        		currentScenMetadata.setName(currentUrnMetadata.getName());
        		currentScenMetadata.setValue(currentUrnMetadata.getValue());
        	
        		out.getMetadata().add(currentScenMetadata);
        	}
        }
        	
    }
}
