package seg.jUCMNav.model.commands.cutcopypaste;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.editparts.CommentEditPart;
import seg.jUCMNav.editpolicies.layout.GrlGraphXYLayoutEditPolicy;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.changeConstraints.MoveNodeCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddCommentCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddContributionChangeCommand;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;
import seg.jUCMNav.model.commands.create.CreateContainerCommand;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.create.CreateResponsibilityCommand;
import seg.jUCMNav.model.commands.create.CreateVariableCommand;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.model.commands.create.DuplicateCommand;
import seg.jUCMNav.model.commands.create.IncludeContributionContextCommand;
import seg.jUCMNav.model.commands.create.IncludePathNodeInScenarioCommand;
import seg.jUCMNav.model.commands.create.IncludeScenarioCommand;
import seg.jUCMNav.model.commands.create.IncludeStrategyCommand;
import seg.jUCMNav.model.commands.transformations.CopyStubPluginUtils;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.DuplicateMapCommand;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.internal.DuplicatePathCommand;
import seg.jUCMNav.model.util.Clipboard;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.SyntaxChecker;
import seg.jUCMNav.scenarios.model.TraversalWarning;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Comment;
import urncore.Component;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class PasteCommand extends CompoundCommand {
    protected EObject insertionPoint;
    protected Point nodeConnectionMiddle, cursorLocation;
    protected List sourceIds;
    protected List sourceSelection;
    protected URNspec sourceUrn;

    protected IURNDiagram targetDiagram;
    protected UCMmap targetMap;
    protected GRLGraph targetGraph;
    protected URNspec targetUrn;

    protected boolean alreadyBuilt;

    protected EObject firstPlaced;
    protected int firstPlacedX;
    protected int firstPlacedY;

    protected Vector impactedNodes;

    public PasteCommand(EObject insertionPoint, URNspec targetUrn, IURNDiagram targetMap, Point nodeConnectionMiddle, Point cursorLocation) {
        this.insertionPoint = insertionPoint;
        this.targetUrn = targetUrn;

        this.targetDiagram = targetMap;
        if (targetDiagram instanceof UCMmap)
            this.targetMap = (UCMmap) targetDiagram;
        else if (targetDiagram instanceof GRLGraph)
            this.targetGraph = (GRLGraph) targetDiagram;

        this.nodeConnectionMiddle = nodeConnectionMiddle;
        this.cursorLocation = cursorLocation;

        Clipboard clip = Clipboard.getInstance();
        sourceIds = clip.getSelectedIds();
        sourceUrn = clip.getOriginalURNspec();
        sourceSelection = clip.getSelection();
        alreadyBuilt = false;
    }

    public void build() {
        if (alreadyBuilt == true)
            return;

        if (insertionPoint == null)
            return;
        else {

            if (insertionPoint instanceof EmptyPoint || insertionPoint instanceof NodeConnection || insertionPoint instanceof DirectionArrow) {

                Responsibility def = getFirstResponsibility();
                PathNode oldPn = getFirstPathNode();

                // this must only be executed when actually doing the initial
                // insert. otherwise create bogus items that are not used

                // only use the ModelCreationFactory for References. otherwise clone or lose metadata, urnlinks, etc.
                ModelCreationFactory factory;
                PathNode newPathNode = null;

                if (def != null) {
                    factory = new ModelCreationFactory(targetUrn, RespRef.class, def);
                    newPathNode = (RespRef) factory.getNewObject();
                    MetadataHelper.duplicateMetadata(oldPn,  newPathNode);
                } else if (oldPn != null) {
                    newPathNode = (PathNode) EcoreUtil.copy(oldPn);
                    if (newPathNode instanceof Stub) {
                        Stub stub = (Stub) newPathNode;
                        stub.getBindings().clear();
                    }
                    resetCloneId(newPathNode);
                }

                if (newPathNode != null && targetMap != null) {

                    setNewNodePosition(newPathNode);

                    if (newPathNode instanceof AndFork || newPathNode instanceof OrFork || newPathNode instanceof AndJoin || newPathNode instanceof OrJoin
                            || newPathNode instanceof Stub || newPathNode instanceof Timer) {
                        buildDividePath(oldPn, newPathNode);
                    } else {
                        buildRegularSplice(oldPn, newPathNode);
                    }

                    if (newPathNode instanceof Stub) {
                        Stub newStub = (Stub) newPathNode;
                        Stub oldStub = (Stub) oldPn;

                        CopyStubPluginUtils util = new CopyStubPluginUtils();
                        Vector cmds = util.copyStubPlugins(targetUrn, targetMap, newStub, oldStub);
                        for (Iterator iterator2 = cmds.iterator(); iterator2.hasNext();) {
                            add((Command) iterator2.next());
                        }
                    }
                }
            } else if (insertionPoint instanceof UCMmap) {
                impactedNodes = new Vector();
                // pasting of anything directly on map (bug 706)
                // if we only have selected extremities, copy the whole paths
                if (getFirstPathNode() == null) {
                    for (Iterator iterator = getPathExtremityList().iterator(); iterator.hasNext();) {
                        PathNode sp = (PathNode) iterator.next();
                        Vector v = new Vector();
                        v.add(sp);
                        buildPathExtremity(v);
                    }
                } else {
                    // we chose a non-extremity, meaning we want to copy only what is selected.
                    buildPathExtremity(getPathNonExtremityList());
                }

            }

            if (targetGraph != null && getFirstGRLNode() != null) {
                buildGrlNodes();
            }

            if (targetDiagram != null) {
                buildAllContainerRefs();

                buildAllComments();
            }

            if (targetUrn != null) {
                Vector list = getSimpleList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof Responsibility) {
                        Responsibility oldResp = (Responsibility) obj;
                        buildResponsibility(oldResp);
                    } else if (obj instanceof Component) {
                        Component oldComponent = (Component) obj;
                        buildComponent(oldComponent);
                    } else if (obj instanceof Actor) {
                        Actor oldActor = (Actor) obj;
                        buildActor(oldActor);
                    } else if (obj instanceof IURNDiagram) {
                        IURNDiagram oldDiagram = (IURNDiagram) obj;
                        buildDiagram(oldDiagram);
                    } else
                        System.out.println("TODO: Paste " + obj); //$NON-NLS-1$
                }

                list = getScenarioList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof ScenarioDef && insertionPoint instanceof ScenarioGroup) {
                        ScenarioDef oldScenario = (ScenarioDef) obj;
                        buildScenario(oldScenario, (ScenarioGroup) insertionPoint);
                    }
                }

                list = getScenarioGroupList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof ScenarioGroup) {
                        ScenarioGroup oldScenarioGroup = (ScenarioGroup) obj;
                        buildScenarioGroup(oldScenarioGroup);
                    }
                }

                list = getStrategyList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof EvaluationStrategy && insertionPoint instanceof StrategiesGroup) {
                        EvaluationStrategy old = (EvaluationStrategy) obj;
                        buildStrategy(old, (StrategiesGroup) insertionPoint);
                    }
                }

                list = getStrategyGroupList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof StrategiesGroup) {
                        StrategiesGroup old = (StrategiesGroup) obj;
                        buildStrategyGroup(old);
                    }
                }
                
                list = getContributionContextList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof ContributionContext && insertionPoint instanceof ContributionContextGroup) {
                        ContributionContext old = (ContributionContext) obj;
                        buildContributionContext(old, (ContributionContextGroup) insertionPoint);
                    }
                }

                list = getContributionContextGroupList(-1);
                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                    EObject obj = (EObject) iterator.next();
                    if (obj instanceof ContributionContextGroup) {
                        ContributionContextGroup old = (ContributionContextGroup) obj;
                        buildContributionContextGroup(old);
                    }
                }                
            }
        }

        alreadyBuilt = true;
    }

    private void buildPathExtremity(Vector points) {
        int x = cursorLocation.x, y = cursorLocation.y;

        PathNode sp = null;
        boolean isInclusive = false;
        if (points.size() >= 1) {
            sp = (PathNode) points.get(0);
        }

        if (points.size() == 1 && (sp instanceof StartPoint || sp instanceof EndPoint)) {
            isInclusive = true;
        }

        if (firstPlaced != null) {
            x += sp.getX() - firstPlacedX;
            y += sp.getY() - firstPlacedY;
        }

        if (firstPlaced == null) // leave null for first added, then set it.
        {
            firstPlaced = sp;
            firstPlacedX = sp.getX();
            firstPlacedY = sp.getY();
        }

        DuplicatePathCommand cmd;
        if (isInclusive)
            cmd = new DuplicatePathCommand((UCMmap) insertionPoint, sp, x - sp.getX(), y - sp.getY());
        else
            cmd = new DuplicatePathCommand((UCMmap) insertionPoint, points, x - sp.getX(), y - sp.getY());

        Vector currentImpactedNodes = cmd.getImpactedNodes();
        for (Iterator iterator = impactedNodes.iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            // have we already duplicated this path.
            if (currentImpactedNodes.contains(pn))
                return;
        }

        impactedNodes.addAll(currentImpactedNodes);

        // must make sure these are all in the model before we execute the previous command.
        Vector v = cmd.getResponsibilitiesThatMustBeCreatedBeforeExecution();

        if (v != null && v.size() > 0) {
            for (Iterator iterator = v.iterator(); iterator.hasNext();) {
                Responsibility r = (Responsibility) iterator.next();
                buildResponsibility(r);
            }
        }

        add(cmd);

        HashMap duplicatedNodes = cmd.getDuplicatedNodes();
        if (duplicatedNodes != null) {
            for (Iterator iterator = duplicatedNodes.values().iterator(); iterator.hasNext();) {
                Object object = (Object) iterator.next();
                if (object instanceof PathNode) {
                    PathNode pn = (PathNode) object;
                    add(new MoveNodeCommand(pn, pn.getX(), pn.getY()));
                }
            }
        }
        HashMap stubs = cmd.getBindingsThatMustBeCreatedAfterExecution();
        if (stubs != null && stubs.size() > 0) {
            for (Iterator iterator = stubs.keySet().iterator(); iterator.hasNext();) {
                Stub oldStub = (Stub) iterator.next();
                CopyStubPluginUtils util = new CopyStubPluginUtils();
                Vector cmds = util.copyStubPlugins(targetUrn, targetMap, (Stub) stubs.get(oldStub), oldStub);
                for (Iterator iterator2 = cmds.iterator(); iterator2.hasNext();) {
                    add((Command) iterator2.next());
                }
            }
        }
    }

    private void buildGrlNodes() {
        Vector list = getClonedGrlNodeRefs();

        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            EObject obj = (EObject) iterator.next();

            if (obj instanceof IntentionalElementRef) {
                add(GrlGraphXYLayoutEditPolicy.buildCreateGrlNodeCommand(targetGraph, (IntentionalElementRef) obj, list /* bug 822 - force creation*/));
            } else if (obj instanceof Belief) {
                add(new AddBeliefCommand(targetGraph, (Belief) obj));
            } else if (obj instanceof KPIInformationElementRef) {
                add(GrlGraphXYLayoutEditPolicy.buildCreateKPIInformationElementCommand(targetGraph, (KPIInformationElementRef) obj, list));
            }
        }
    }

    private void buildActor(Actor oldActor) {
        if (URNElementFinder.findActorByName(targetUrn, oldActor.getName()) == null) {
            Actor newActor = (Actor) EcoreUtil.copy(oldActor);
            resetCloneId(newActor);
            add(new CreateContainerCommand(targetUrn, newActor));
        }
    }

    private void buildAllComments() {
        Vector commentList = getClonedComments();
        for (Iterator iterator = commentList.iterator(); iterator.hasNext();) {
            Comment comment = (Comment) iterator.next();
            add(new AddCommentCommand(targetDiagram, comment));
        }
    }

    private void buildAllContainerRefs() {
        Vector compRefList = getClonedContainerRefs();
        if (compRefList != null) {
            for (Iterator iterator = compRefList.iterator(); iterator.hasNext();) {
                IURNContainerRef newCompRef = (IURNContainerRef) iterator.next();
                add(new AddContainerRefCommand(targetDiagram, newCompRef));
                add(new SetConstraintBoundContainerRefCompoundCommand(newCompRef, newCompRef.getX(), newCompRef.getY(), newCompRef.getWidth(),
                        newCompRef.getHeight()));

                add(new ContainerRefBindChildCommand(newCompRef));
            }
        }
    }

    private void buildComponent(Component oldComponent) {
        if (URNElementFinder.findComponentByName(targetUrn, oldComponent.getName()) == null) {
            Component newComponent = (Component) EcoreUtil.copy(oldComponent);
            resetCloneId(newComponent);
            add(new CreateContainerCommand(targetUrn, newComponent));
        }
    }

    private void buildDiagram(IURNDiagram oldDiagram) {
        String oldName = ((URNmodelElement) oldDiagram).getName();
        IURNDiagram toClone = URNElementFinder.findMapByName(targetUrn, oldName);
        if (toClone instanceof UCMmap) {
            add(new DuplicateMapCommand((UCMmap) toClone));
        } else if (toClone instanceof GRLGraph) {
            add(new DuplicateMapCommand((GRLGraph) toClone));
        }
    }

    private void buildDividePath(PathNode oldPn, PathNode newPathNode) {
        DividePathCommand cmd = null;
        if (insertionPoint instanceof EmptyPoint) {
            cmd = new DividePathCommand(newPathNode, (EmptyPoint) insertionPoint);
            cmd.chain(new MoveNodeCommand(newPathNode, ((PathNode) insertionPoint).getX(), ((PathNode) insertionPoint).getY()));
        } else if (insertionPoint instanceof DirectionArrow) {
            cmd = new DividePathCommand(newPathNode, (DirectionArrow) insertionPoint);
            cmd.chain(new MoveNodeCommand(newPathNode, ((PathNode) insertionPoint).getX(), ((PathNode) insertionPoint).getY()));
        } else if (insertionPoint instanceof NodeConnection) {
            cmd = new DividePathCommand(newPathNode, (NodeConnection) insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y);
            cmd.chain(new MoveNodeCommand(newPathNode, nodeConnectionMiddle.x, nodeConnectionMiddle.y));
        }

        if (cmd != null && oldPn != null)
            cmd.cloneBranchesFrom(oldPn, targetMap);
        if (cmd != null)
            add(cmd);
    }

    private void buildRegularSplice(PathNode oldPn, PathNode newPathNode) {
        Condition cond = null;
        if (oldPn.getSucc().size() > 0) {
            NodeConnection nc = (NodeConnection) oldPn.getSucc().get(0);
            cond = nc.getCondition();
        }

        Command cmd = null;
        if (insertionPoint instanceof PathNode) {
            cmd = new ReplaceEmptyPointCommand((PathNode) insertionPoint, newPathNode, cond);
            cmd.chain(new MoveNodeCommand(newPathNode, ((PathNode) insertionPoint).getX(), ((PathNode) insertionPoint).getY()));
        } else if (insertionPoint instanceof NodeConnection) {
            cmd = new SplitLinkCommand(targetMap, newPathNode, (NodeConnection) insertionPoint, nodeConnectionMiddle.x, nodeConnectionMiddle.y, cond);
            cmd.chain(new MoveNodeCommand(newPathNode, nodeConnectionMiddle.x, nodeConnectionMiddle.y));
        }

        if (cmd != null)
            add(cmd);
    }

    private void buildResponsibility(Responsibility oldResp) {
        if (URNElementFinder.findResponsibilityByName(targetUrn, oldResp.getName()) == null) {
            Responsibility newResp = (Responsibility) EcoreUtil.copy(oldResp);
            resetCloneId(newResp);
            add(new CreateResponsibilityCommand(targetUrn, newResp));
        }
    }

    private void buildScenario(ScenarioDef oldScenario, ScenarioGroup targetGroup) {
        if (targetGroup != null) {

            // create a shallow copy to break references to stuff we don't know exists.
            ScenarioDef newScenario = (ScenarioDef) EcoreUtil.copy(oldScenario);
            resetCloneId(newScenario);
            newScenario.getStartPoints().clear();
            newScenario.getEndPoints().clear();
            newScenario.getInitializations().clear();

            DuplicateCommand duplicateCommand = new DuplicateCommand(newScenario, targetGroup, targetUrn);
            add(duplicateCommand);

            ScenarioDef duplicate = (ScenarioDef) duplicateCommand.getDuplicate();
            for (int i = 0; i < oldScenario.getInitializations().size(); i++) {
                Initialization init = (Initialization) oldScenario.getInitializations().get(i);
                Variable var = URNElementFinder.findVariable(targetUrn, init.getVariable().getId());
                if (var != null) {
                    add(new CreateVariableInitializationCommand(var, duplicate, init.getValue()));
                }
            }
            for (int i = 0; i < oldScenario.getStartPoints().size(); i++) {
                ScenarioStartPoint start = (ScenarioStartPoint) oldScenario.getStartPoints().get(i);
                Object pn = URNElementFinder.find(targetUrn, start.getStartPoint().getId());
                if (pn != null && pn instanceof StartPoint) {
                    IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
                    inc.setClone(start);
                    add(inc);
                }
            }
            for (int i = 0; i < oldScenario.getEndPoints().size(); i++) {
                ScenarioEndPoint end = (ScenarioEndPoint) oldScenario.getEndPoints().get(i);
                Object pn = URNElementFinder.find(targetUrn, end.getEndPoint().getId());
                if (pn != null && pn instanceof EndPoint) {
                    IncludePathNodeInScenarioCommand inc = new IncludePathNodeInScenarioCommand(duplicate, (PathNode) pn, targetUrn);
                    inc.setClone(end);
                    add(inc);
                }
            }
            for (int i = 0; i < oldScenario.getIncludedScenarios().size(); i++) {
                ScenarioDef oldChild = (ScenarioDef) oldScenario.getIncludedScenarios().get(i);
                ScenarioDef newChild = URNElementFinder.findScenario(targetUrn, oldChild.getId());
                if (newChild != null) {
                    IncludeScenarioCommand inc = new IncludeScenarioCommand(duplicate, newChild, true);
                    add(inc);
                }
            }
        }
    }

    private void buildStrategy(EvaluationStrategy old, StrategiesGroup targetGroup) {
        if (targetGroup != null) {
            // create a shallow copy to break references to stuff we don't know exists.
            EvaluationStrategy newStrategy = (EvaluationStrategy) EcoreUtil.copy(old);
            resetCloneId(newStrategy);
            newStrategy.getKpiInfoConfig().clear();
            newStrategy.getEvaluations().clear();

            DuplicateCommand duplicateCommand = new DuplicateCommand(newStrategy, targetGroup, targetUrn);
            add(duplicateCommand);

            EvaluationStrategy duplicate = (EvaluationStrategy) duplicateCommand.getDuplicate();
            for (int i = 0; i < old.getEvaluations().size(); i++) {
                Evaluation oldEval = (Evaluation) old.getEvaluations().get(i);
                GRLmodelElement elem = (GRLmodelElement) URNElementFinder.findGRLmodelElement(targetUrn, oldEval.getIntElement().getId());
                if (elem != null && elem instanceof IntentionalElement) {
                    Evaluation eval = (Evaluation) ModelCreationFactory.getNewObject(targetUrn, Evaluation.class);
                    add(new AddEvaluationCommand(eval, (IntentionalElement) elem, duplicate));
                }
            }
            for (int i = 0; i < old.getIncludedStrategies().size(); i++) {
                EvaluationStrategy oldChild = (EvaluationStrategy) old.getIncludedStrategies().get(i);
                EvaluationStrategy newChild = URNElementFinder.findStrategy(targetUrn, oldChild.getId());
                if (newChild != null) {
                    IncludeStrategyCommand inc = new IncludeStrategyCommand(duplicate, newChild, true);
                    add(inc);
                }
            }
        }
    }
    
    private void buildContributionContext(ContributionContext old, ContributionContextGroup targetGroup) {
        if (targetGroup != null) {
            // create a shallow copy to break references to stuff we don't know exists.
            ContributionContext newContext = (ContributionContext) EcoreUtil.copy(old);
            resetCloneId(newContext);
            newContext.getIncludedContexts().clear();
            newContext.getChanges().clear();

            DuplicateCommand duplicateCommand = new DuplicateCommand(newContext, targetGroup, targetUrn);
            add(duplicateCommand);

            ContributionContext duplicate = (ContributionContext) duplicateCommand.getDuplicate();
            for (int i = 0; i < old.getChanges().size(); i++) {
                ContributionChange oldChange = (ContributionChange) old.getChanges().get(i);
                ContributionChange change = (ContributionChange)EcoreUtil.copy(oldChange);
                change.setContribution(null);
                resetCloneId(newContext);
                if (oldChange.getContribution()!=null) {
                    AddContributionChangeCommand cmd = new AddContributionChangeCommand(duplicate, oldChange.getContribution(), change);
                    add(cmd);
                }
            }
            for (int i = 0; i < old.getIncludedContexts().size(); i++) {
                ContributionContext oldChild = (ContributionContext) old.getIncludedContexts().get(i);
                ContributionContext newChild = URNElementFinder.findContributionContext(targetUrn, oldChild.getId());
                if (newChild != null) {
                    IncludeContributionContextCommand inc = new IncludeContributionContextCommand(duplicate, newChild, true);
                    add(inc);
                }
            }
        }
    }

    private void buildScenarioGroup(ScenarioGroup oldScenarioGroup) {
        if (insertionPoint instanceof URNspec) {
            ScenarioGroup newScenarioGroup = (ScenarioGroup) EcoreUtil.copy(oldScenarioGroup);
            resetCloneId(newScenarioGroup);

            newScenarioGroup.getScenarios().clear();
            DuplicateCommand duplicateCommand = new DuplicateCommand(newScenarioGroup, targetUrn);
            add(duplicateCommand);

            ScenarioGroup duplicate = (ScenarioGroup) duplicateCommand.getDuplicate();

            for (Iterator iterator = oldScenarioGroup.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef oldScenario = (ScenarioDef) iterator.next();
                buildScenario(oldScenario, duplicate);
            }
        }
    }

    private void buildStrategyGroup(StrategiesGroup oldGroup) {
        if (insertionPoint instanceof URNspec) {
            StrategiesGroup newGroup = (StrategiesGroup) EcoreUtil.copy(oldGroup);
            resetCloneId(newGroup);

            newGroup.getStrategies().clear();
            DuplicateCommand duplicateCommand = new DuplicateCommand(newGroup, targetUrn);
            add(duplicateCommand);

            StrategiesGroup duplicate = (StrategiesGroup) duplicateCommand.getDuplicate();

            for (Iterator iterator = oldGroup.getStrategies().iterator(); iterator.hasNext();) {
                EvaluationStrategy oldStrategy = (EvaluationStrategy) iterator.next();
                buildStrategy(oldStrategy, duplicate);
            }
        }
    }
    

    private void buildContributionContextGroup(ContributionContextGroup oldGroup) {
        if (insertionPoint instanceof URNspec) {
            ContributionContextGroup newGroup = (ContributionContextGroup) EcoreUtil.copy(oldGroup);
            resetCloneId(newGroup);

            newGroup.getContribs().clear();
            DuplicateCommand duplicateCommand = new DuplicateCommand(newGroup, targetUrn);
            add(duplicateCommand);

            ContributionContextGroup duplicate = (ContributionContextGroup) duplicateCommand.getDuplicate();

            for (Iterator iterator = oldGroup.getContribs().iterator(); iterator.hasNext();) {
                ContributionContext oldContext = (ContributionContext) iterator.next();
                buildContributionContext(oldContext, duplicate);
            }
        }
    }

    public boolean canExecute() {
        boolean found = insertionPoint != null;

        if (found) {
            // we've selected something that is copiable.
            found = (insertionPoint instanceof NodeConnection || insertionPoint instanceof EmptyPoint || insertionPoint instanceof DirectionArrow)
                    && targetMap != null && (getFirstResponsibility() != null || getFirstPathNode() != null);
            if (found)
                return true;

            found = (insertionPoint instanceof UCMmap && (getFirstPathExtremity() != null || getFirstPathNode() != null));
            if (found)
                return true;

            found = insertionPoint instanceof IURNDiagram && (getFirstContainer() != null);
            if (found)
                return true;

            found = insertionPoint instanceof IURNDiagram && getFirstComment() != null;
            if (found)
                return true;

            found = insertionPoint instanceof GRLGraph && getFirstGRLNode() != null;
            if (found)
                return true;

            found = insertionPoint instanceof ScenarioGroup && getFirstScenario() != null;
            if (found)
                return true;

            found = insertionPoint instanceof StrategiesGroup && getFirstStrategy() != null;
            if (found)
                return true;
            
            found = insertionPoint instanceof ContributionContextGroup && getFirstContributionContext() != null;
            if (found)
                return true;

            found = insertionPoint instanceof URNspec && getFirstScenarioGroup() != null;
            if (found)
                return true;

            found = insertionPoint instanceof URNspec && getFirstStrategyGroup() != null;
            if (found)
                return true;
            
            found = insertionPoint instanceof URNspec && getFirstContributionContextGroup() != null;
            if (found)
                return true;

            found = insertionPoint instanceof URNspec && getFirstSimple() != null;
            return found;
        } else
            return false;
    }

    public void execute() {
        build();
        super.execute();
        postBuild();
    }

    protected Vector getClonedComments() {
        return getCommentList(-1);
    }

    /***
     * Special case for containers. Builds a list of containers refs that are ready to be inserted.
     * 
     * @return a list of containers refs
     */
    protected Vector getClonedContainerRefs() {
        return getContainerList(-1, true);
    }

    protected Vector getClonedGrlNodeRefs() {
        return getGRLNodeList(-1, true);
    }

    protected Vector getCommentList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceSelection.iterator(); iterator.hasNext();) {
                Object object = (Object) iterator.next();
                if (object instanceof CommentEditPart) {
                    Comment oldComment = (Comment) ((CommentEditPart) object).getModel();
                    Comment newComment = (Comment) EcoreUtil.copy(oldComment);

                    setNewCommentConstraints(newComment, oldComment);

                    if (firstPlaced == null) // leave null for first added, then set it.
                    {
                        firstPlaced = oldComment;
                        firstPlacedX = oldComment.getX();
                        firstPlacedY = oldComment.getY();
                    }

                    results.add(newComment);
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    /**
     * Builds a list of elements that are ready to be inserted on a diagram.
     * 
     * @param maxCount
     *            the maximum number of answers. limit the number for faster checks.
     * @param generateRefs
     *            if true, will return a new ref - otherwise returns the original def
     * 
     * @return a list of containers refs/containers
     */
    protected Vector getContainerList(int maxCount, boolean generateRefs) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();

            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                IURNContainer def = null;
                IURNContainerRef ref = null;
                Object obj = URNElementFinder.find(sourceUrn, id);
                // pasting on wrong map.
                if ((targetMap == null && obj instanceof ComponentRef) || (targetMap != null && obj instanceof ActorRef))
                    continue;

                if (obj instanceof IURNContainerRef) {
                    ref = (IURNContainerRef) obj;
                    IURNContainer comp = null;

                    String oldDefinition = ((URNmodelElement) ref.getContDef()).getName();
                    if (ref instanceof ComponentRef)
                        comp = URNElementFinder.findComponentByName(targetUrn, oldDefinition);
                    else if (ref instanceof ActorRef)
                        comp = URNElementFinder.findActorByName(targetUrn, oldDefinition);

                    if (comp == null) {
                        if (ref instanceof ComponentRef)
                            comp = URNElementFinder.findComponentByName(sourceUrn, oldDefinition);
                        else if (ref instanceof ActorRef)
                            comp = URNElementFinder.findActorByName(sourceUrn, oldDefinition);

                        comp = (IURNContainer) EcoreUtil.copy(comp); // clone it because it is used to create a new element.
                        resetCloneId((URNmodelElement) comp);
                    }
                    def = comp;
                }

                if (def != null) {
                    if (generateRefs) {
                        ModelCreationFactory factory = null;
                        if (ref instanceof ComponentRef)
                            factory = new ModelCreationFactory(targetUrn, ComponentRef.class, ((Component) def).getKind().getValue(), def);
                        else if (ref instanceof ActorRef)
                            factory = new ModelCreationFactory(targetUrn, ActorRef.class, def);

                        if (factory != null) {
                            IURNContainerRef newCompRef = (IURNContainerRef) factory.getNewObject();
                            setNewContainerConstraints(newCompRef, ref);
                            MetadataHelper.duplicateMetadata((URNmodelElement)ref,  (URNmodelElement)newCompRef);

                            if (firstPlaced == null) // leave null for first added, then set it.
                            {
                                firstPlaced = ref;
                                firstPlacedX = ref.getX();
                                firstPlacedY = ref.getY();
                            }
                            results.add(newCompRef);
                        }
                    } else {
                        results.add(def);
                    }
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }

            return results;
        }
        return null;
    }

    /**
     * Builds a list of elements that are ready to be inserted on a diagram.
     * 
     * @param maxCount
     *            the maximum number of answers. limit the number for faster checks.
     * @param generateRefs
     *            if true, will return a new ref - otherwise returns the original def
     * 
     * @return a list of refs/defs
     */
    protected Vector getGRLNodeList(int maxCount, boolean generateRefs) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();

            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                GRLmodelElement def = null;
                GRLNode ref = null;
                Object obj = URNElementFinder.find(sourceUrn, id);
                // pasting on wrong graph.
                if (targetGraph == null && obj instanceof GRLNode)
                    continue;

                if (obj instanceof IntentionalElementRef) {
                    IntentionalElementRef intentionalElementRef = (IntentionalElementRef) obj;
                    IntentionalElement intentionalElement = null;

                    String oldDefinition = ((URNmodelElement) intentionalElementRef.getDef()).getName();

                    intentionalElement = URNElementFinder.findIntentionalElementByName(targetUrn, oldDefinition);

                    if (intentionalElement == null) {
                        intentionalElement = URNElementFinder.findIntentionalElementByName(sourceUrn, oldDefinition);

                        IntentionalElement oldElement = intentionalElement;
                        intentionalElement = (IntentionalElement) EcoreUtil.copy(intentionalElement); // clone it because it is used to create a new element.
                        resetCloneId(intentionalElement);
                    }

                    def = intentionalElement;
                    ref = intentionalElementRef;

                } else if (obj instanceof Belief) {
                    def = null;
                    ref = (Belief) obj;
                    if (generateRefs) {
                        Belief newNode = (Belief) EcoreUtil.copy(ref);
                        setNewGRLConstraints(newNode, ref);

                        if (firstPlaced == null) // leave null for first added, then set it.
                        {
                            firstPlaced = ref;
                            firstPlacedX = ref.getX();
                            firstPlacedY = ref.getY();
                        }
                        results.add(newNode);

                    } else
                        results.add(ref);
                } else if (obj instanceof KPIInformationElementRef) {
                    KPIInformationElementRef informationElementRef = (KPIInformationElementRef) obj;
                    KPIInformationElement informationElement = null;

                    String oldDefinition = ((URNmodelElement) informationElementRef.getDef()).getName();

                    informationElement = URNElementFinder.findKPIInformationElementByName(targetUrn, oldDefinition);

                    if (informationElement == null) {
                        informationElement = URNElementFinder.findKPIInformationElementByName(sourceUrn, oldDefinition);

                        informationElement = (KPIInformationElement) EcoreUtil.copy(informationElement); // clone it because it is used to create a new element.
                        resetCloneId(informationElement);
                    }

                    def = informationElement;
                    ref = informationElementRef;
                }

                if (def != null) {
                    if (generateRefs) {
                        ModelCreationFactory factory = null;
                        if (ref instanceof IntentionalElementRef)
                            factory = new ModelCreationFactory(targetUrn, IntentionalElementRef.class, ((IntentionalElement) def).getType().getValue(), def);
                        else if (ref instanceof KPIInformationElementRef)
                            factory = new ModelCreationFactory(targetUrn, KPIInformationElementRef.class, def);

                        if (factory != null) {
                            GRLNode newNode = (GRLNode) factory.getNewObject();
                            setNewGRLConstraints(newNode, ref);
                            MetadataHelper.duplicateMetadata((URNmodelElement)ref,  (URNmodelElement)newNode);

                            if (firstPlaced == null) // leave null for first added, then set it.
                            {
                                firstPlaced = ref;
                                firstPlacedX = ref.getX();
                                firstPlacedY = ref.getY();
                            }
                            results.add(newNode);
                        }
                    } else {
                        results.add(def);
                    }
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }

            return results;
        }
        return null;
    }

    protected Comment getFirstComment() {
        Vector v = getCommentList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (Comment) v.get(0);
    }

    /***
     * Special case for components.
     * 
     * @return the first ComponentRef in our list
     */
    protected IURNContainer getFirstContainer() {
        Vector v = getContainerList(1, false);
        if (v == null || v.size() == 0)
            return null;
        else
            return (IURNContainer) v.get(0);
    }

    protected GRLmodelElement getFirstGRLNode() {
        Vector v = getGRLNodeList(1, false);
        if (v == null || v.size() == 0)
            return null;
        else
            return (GRLmodelElement) v.get(0);
    }

    protected PathNode getFirstPathNode() {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                Object obj = URNElementFinder.find(sourceUrn, id);
                if (IsPastablePathNode(obj)) {
                    PathNode oldPn = (PathNode) obj;
                    return oldPn; // pn from old urn
                }
            }
        }
        return null;
    }

    /***
     * Special case for responsibilities.
     * 
     * @return the first Responsibility in our
     */
    protected Responsibility getFirstResponsibility() {

        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                Object obj = URNElementFinder.find(sourceUrn, id);
                if (obj instanceof RespRef) {
                    String oldDefinition = ((RespRef) obj).getRespDef().getName();
                    // this one is faster... searching for the definition.
                    Responsibility resp = URNElementFinder.findResponsibilityByName(targetUrn, oldDefinition);
                    if (resp == null) {
                        resp = URNElementFinder.findResponsibilityByName(sourceUrn, oldDefinition);
                        resp = (Responsibility) EcoreUtil.copy(resp); // clone it because it is used to create a new element.
                        resetCloneId(resp);
                    }
                    return resp;
                }
            }
        }
        return null;
    }

    protected EObject getFirstSimple() {
        Vector v = getSimpleList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstScenario() {
        Vector v = getScenarioList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstScenarioGroup() {
        Vector v = getScenarioGroupList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstStrategy() {
        Vector v = getStrategyList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstStrategyGroup() {
        Vector v = getStrategyGroupList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstContributionContext() {
        Vector v = getContributionContextList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    protected EObject getFirstContributionContextGroup() {
        Vector v = getContributionContextGroupList(1);
        if (v == null || v.size() == 0)
            return null;
        else
            return (EObject) v.get(0);
    }

    
    protected PathNode getFirstPathExtremity() {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                Object obj = URNElementFinder.find(sourceUrn, id);
                if (obj instanceof StartPoint || obj instanceof EndPoint) {
                    return (PathNode) obj; // pn from old urn
                }
            }
        }
        return null;
    }

    protected Vector getPathExtremityList() {
        Vector list = new Vector();
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                Object obj = URNElementFinder.find(sourceUrn, id);
                if (obj instanceof StartPoint || obj instanceof EndPoint) {
                    list.add(obj);
                }
            }
        }
        return list;
    }

    protected Vector getPathNonExtremityList() {
        Vector list = new Vector();
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                Object obj = URNElementFinder.find(sourceUrn, id);
                if (IsPastablePathNode(obj)) {
                    list.add(obj);
                }
            }
        }
        return list;
    }

    protected Vector getSimpleList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof IURNContainer || obj instanceof Responsibility || obj instanceof IURNDiagram) {
                    results.add(obj);
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    protected Vector getScenarioList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof ScenarioDef) {
                    results.add(obj);
                } else if (obj instanceof ScenarioGroup) {
                    for (int i = 0; i < ((ScenarioGroup) obj).getScenarios().size(); i++)
                        results.add(((ScenarioGroup) obj).getScenarios().get(i));
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    protected Vector getScenarioGroupList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof ScenarioGroup) {
                    results.add(obj);
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    protected Vector getStrategyList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof EvaluationStrategy) {
                    results.add(obj);
                } else if (obj instanceof StrategiesGroup) {
                    for (int i = 0; i < ((StrategiesGroup) obj).getStrategies().size(); i++)
                        results.add(((StrategiesGroup) obj).getStrategies().get(i));
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    protected Vector getStrategyGroupList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof StrategiesGroup) {
                    results.add(obj);
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }
    
    protected Vector getContributionContextList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof ContributionContext) {
                    results.add(obj);
                } else if (obj instanceof ContributionContextGroup) {
                    for (int i = 0; i < ((ContributionContextGroup) obj).getContribs().size(); i++)
                        results.add(((ContributionContextGroup) obj).getContribs().get(i));
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }

    protected Vector getContributionContextGroupList(int maxCount) {
        if (sourceIds != null && sourceUrn != null && targetUrn != null) {
            Vector results = new Vector();
            for (Iterator iterator = sourceIds.iterator(); iterator.hasNext();) {
                String id = iterator.next().toString();

                // search for the old id in the old model.
                Object obj = URNElementFinder.find(sourceUrn, id);

                if (obj instanceof ContributionContextGroup) {
                    results.add(obj);
                }
                if (results.size() >= maxCount && maxCount > 0)
                    break;
            }
            return results;
        }
        return null;
    }


    protected boolean IsPastablePathNode(Object obj) {
        return obj instanceof PathNode && !(obj instanceof EndPoint) && !(obj instanceof StartPoint) && !(obj instanceof Connect);
    }

    protected void postBuild() {
        Vector errors = SyntaxChecker.verifySyntax(targetUrn);
        Vector toBeAdded = new Vector(); // variables to create.
        for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
            TraversalWarning warning = (TraversalWarning) iterator.next();
            if (warning.getExpression() != null) {
                for (int i = 0; i < sourceUrn.getUcmspec().getVariables().size(); i++) {
                    Variable var = (Variable) sourceUrn.getUcmspec().getVariables().get(i);
                    if (warning.getExpression().toLowerCase().indexOf(var.getName().toLowerCase()) >= 0) {
                        if (!toBeAdded.contains(var) && !URNNamingHelper.doesVariableNameExist(targetUrn, var.getName()))
                            toBeAdded.add(var);
                    }
                }
            }
        }

        CompoundCommand cmd = new CompoundCommand();
        HashMap createdEnumerations = new HashMap();
        for (int i = 0; i < toBeAdded.size(); i++) {
            Variable var = (Variable) toBeAdded.get(i);
            Variable newVar = (Variable) EcoreUtil.copy(var);
            resetCloneId(newVar);
            CreateVariableCommand createVar = new CreateVariableCommand(targetUrn, newVar);

            if (var.getType() != null && var.getType().equals(ScenarioUtils.sTypeEnumeration)) {

                EnumerationType newType = null;
                for (int j = 0; j < targetUrn.getUcmspec().getEnumerationTypes().size(); j++) {
                    EnumerationType type = (EnumerationType) targetUrn.getUcmspec().getEnumerationTypes().get(j);
                    if (type.getName().equals(var.getEnumerationType().getName()))
                        newType = type;
                }
                // we need to create the enumeration type.
                if (newType == null) {
                    if (createdEnumerations.containsKey(var.getEnumerationType().getName()))
                        newType = (EnumerationType) createdEnumerations.get(var.getEnumerationType().getName());
                    else {
                        newType = (EnumerationType) EcoreUtil.copy(var.getEnumerationType());
                        resetCloneId(newType);
                        CreateEnumerationTypeCommand createEnum = new CreateEnumerationTypeCommand(targetUrn);
                        createEnum.setEnumerationType(newType);
                        createdEnumerations.put(newType.getName(), newType);
                        cmd.add(createEnum);
                    }
                }
                createVar.setEnumerationType(newType);
            }

            cmd.add(createVar);
        }

        // don't execute full command, just this portion.
        if (cmd.canExecute()) {
            // chain it for the undo.
            add(cmd);

            cmd.execute();
        }
    }

    private void resetCloneId(URNmodelElement clone) {
        String name = clone.getName();
        clone.setId(""); //$NON-NLS-1$
        URNNamingHelper.setElementNameAndID(targetUrn, clone);
        clone.setName(name);
    }

    private void setNewCommentConstraints(Comment newComment, Comment oldComment) {
        if (cursorLocation != null) {
            int x = cursorLocation.x, y = cursorLocation.y;

            if (firstPlaced != null) {
                x += oldComment.getX() - firstPlacedX;
                y += oldComment.getY() - firstPlacedY;
            }

            newComment.setX(x);
            newComment.setY(y);
        }

        if (oldComment != null) {
            newComment.setWidth(oldComment.getWidth());
            newComment.setHeight(oldComment.getHeight());
        }
    }

    private void setNewContainerConstraints(IURNContainerRef newCompRef, IURNContainerRef oldCompRef) {
        if (cursorLocation != null) {
            int x = cursorLocation.x, y = cursorLocation.y;

            if (firstPlaced != null) {
                x += oldCompRef.getX() - firstPlacedX;
                y += oldCompRef.getY() - firstPlacedY;
            }

            newCompRef.setX(x);
            newCompRef.setY(y);
        }

        if (oldCompRef != null) {
            newCompRef.setWidth(oldCompRef.getWidth());
            newCompRef.setHeight(oldCompRef.getHeight());
        }
    }

    private void setNewGRLConstraints(GRLNode newNode, GRLNode oldNode) {
        if (cursorLocation != null) {
            int x = cursorLocation.x, y = cursorLocation.y;

            if (firstPlaced != null) {
                x += oldNode.getX() - firstPlacedX;
                y += oldNode.getY() - firstPlacedY;
            }

            newNode.setX(x);
            newNode.setY(y);
        }
    }

    private void setNewNodePosition(PathNode newPathNode) {
        // ensures that created branches are at the right place.
        if (insertionPoint instanceof PathNode) {
            newPathNode.setX(((PathNode) insertionPoint).getX());
            newPathNode.setY(((PathNode) insertionPoint).getY());
        } else if (insertionPoint instanceof NodeConnection) {
            newPathNode.setX(nodeConnectionMiddle.x);
            newPathNode.setY(nodeConnectionMiddle.y);
        }
    }

}
