package seg.jUCMNav.model.commands.delete.internal;

import grl.GRLGraph;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIEvalValueSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;
import urncore.Component;
import urncore.Concern;
import urncore.Condition;
import urncore.IURNDiagram;

/**
 * Given a ComponentRef, PathNode, NodeConnection or Map, remove its performance/scenario information.
 * 
 * Currently does nothing for performance because we have not implemented the relationship between UCMspec and performance annotations.
 * 
 * Currently does nothing for PathNode conditions because we have not implemented the relationship between UCMspec and its variables/scenarios.
 * 
 * All that it currently does is remove Conditions from NodeConnections because we need to move the conditions to NodeConnections that directly follow a
 * Timer/WaitingPlace/OrFork.
 * 
 * This command does not clean plugin binding relationships.
 * 
 * @author jkealey
 * 
 */
public class RemoveLinkedInfoCommand extends Command implements JUCMNavCommand {

    private EObject element;

    private Condition condition;
    private PathNode scenarioPathNode;
    private ScenarioDef scenario;
    private EnumerationType enumType;
    private List components; // Single Component or list of ComponentRegular
    private List perfMeasures;
    private List kpiEvalValueSet;
    private Concern concern;

    /**
     * 
     * @param pn
     *            the PathNode to be cleaned.
     */
    public RemoveLinkedInfoCommand(PathNode pn) {
        this.element = pn;
    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be cleaned.
     */
    public RemoveLinkedInfoCommand(ComponentRef cr) {
        this.element = cr;
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be cleaned.
     */
    public RemoveLinkedInfoCommand(NodeConnection nc) {
        this.element = nc;
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned.
     */
    public RemoveLinkedInfoCommand(UCMmap map) {
        this.element = map;
    }

    /**
     * 
     * @param graph
     *            the GRLGraph to be cleaned.
     */
    public RemoveLinkedInfoCommand(GRLGraph graph) {
        this.element = graph;
    }

    /**
     * 
     * @param pt
     *            the ScenarioStartPoint to be cleaned.
     */
    public RemoveLinkedInfoCommand(ScenarioStartPoint pt) {
        this.element = pt;
    }

    /**
     * 
     * @param pt
     *            the ScenarioEndPoint to be cleaned.
     */
    public RemoveLinkedInfoCommand(ScenarioEndPoint pt) {
        this.element = pt;
    }

    /**
     * 
     * @param init
     *            the Initialization to be cleaned.
     */
    public RemoveLinkedInfoCommand(Initialization init) {
        this.element = init;
    }

    /**
     * 
     * @param var
     *            the Variable to be cleaned.
     */
    public RemoveLinkedInfoCommand(Variable var) {
        this.element = var;
    }

    /**
     * 
     * @param conv
     *            the KPIConversion to be cleaned.
     */
    public RemoveLinkedInfoCommand(KPIConversion conv) {
        this.element = conv;
    }

    /**
     * 
     * @param resx
     *            the GeneralResource to be cleaned.
     */
    public RemoveLinkedInfoCommand(GeneralResource resx) {
        this.element = resx;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element instanceof NodeConnection)
            this.condition = ((NodeConnection) element).getCondition();
        else if (element instanceof ScenarioStartPoint)
            this.scenarioPathNode = ((ScenarioStartPoint) element).getStartPoint();
        else if (element instanceof ScenarioEndPoint)
            this.scenarioPathNode = ((ScenarioEndPoint) element).getEndPoint();
        else if (element instanceof Initialization)
            this.scenario = ((Initialization) element).getScenarioDef();
        else if (element instanceof Variable)
            this.enumType = ((Variable) element).getEnumerationType();
        else if (element instanceof GeneralResource) {
            // TODO: could use RemoveResourceFromComponentCommand

            this.components = new ArrayList();
            if (element instanceof PassiveResource) {
                PassiveResource passiveResource = (PassiveResource) element;
                if (passiveResource.getComponent() != null)
                    this.components.add(passiveResource.getComponent());
            } else if (element instanceof ProcessingResource) {
                ProcessingResource processingResource = (ProcessingResource) element;
                this.components.addAll(processingResource.getComponents());
            }
        } else if (element instanceof KPIConversion) {
            KPIConversion conv = (KPIConversion) element;
            this.kpiEvalValueSet = new ArrayList();
            this.kpiEvalValueSet.addAll(conv.getKpiEvalValueSet());
        } else if (element instanceof UCMmap || element instanceof GRLGraph) {
        	this.concern = ((IURNDiagram) element).getConcern();
        }
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (element instanceof NodeConnection)
            ((NodeConnection) element).setCondition(null);
        else if (element instanceof ScenarioStartPoint)
            ((ScenarioStartPoint) element).setStartPoint(null);
        else if (element instanceof ScenarioEndPoint)
            ((ScenarioEndPoint) element).setEndPoint(null);
        else if (element instanceof Initialization)
            ((Initialization) element).setScenarioDef(null);
        else if (element instanceof Variable)
            ((Variable) element).setEnumerationType(null);
        else if (element instanceof GeneralResource) {
            // this.components = new ArrayList(); // This looks too destructive. JS
            if (element instanceof PassiveResource) {
                PassiveResource passiveResource = (PassiveResource) element;
                passiveResource.setComponent(null);
            } else if (element instanceof ProcessingResource) {
                ProcessingResource processingResource = (ProcessingResource) element;
                processingResource.getComponents().clear();
            }
        } else if (element instanceof KPIConversion) {
            for (Iterator iterator = kpiEvalValueSet.iterator(); iterator.hasNext();) {
                KPIEvalValueSet kpi = (KPIEvalValueSet) iterator.next();
                kpi.setKpiConv(null);
            }
        } else if (element instanceof UCMmap || element instanceof GRLGraph) {
        	((IURNDiagram) element).setConcern(null);
        }

        testPostConditions();

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (element instanceof NodeConnection)
            ((NodeConnection) element).setCondition(condition);
        else if (element instanceof ScenarioStartPoint)
            ((ScenarioStartPoint) element).setStartPoint((StartPoint) scenarioPathNode);
        else if (element instanceof ScenarioEndPoint)
            ((ScenarioEndPoint) element).setEndPoint((EndPoint) scenarioPathNode);
        else if (element instanceof Initialization)
            ((Initialization) element).setScenarioDef(scenario);
        else if (element instanceof Variable)
            ((Variable) element).setEnumerationType(enumType);
        else if (element instanceof GeneralResource) {
            if (element instanceof PassiveResource) {
                PassiveResource passiveResource = (PassiveResource) element;
                if (this.components.size() == 0)
                    passiveResource.setComponent(null);
                else
                    passiveResource.setComponent((Component) this.components.get(0));
            } else if (element instanceof ProcessingResource) {
                ProcessingResource processingResource = (ProcessingResource) element;
                processingResource.getComponents().addAll(this.components);
            }
        } else if (element instanceof KPIConversion) {
            KPIConversion conv = (KPIConversion) element;
            for (Iterator iterator = kpiEvalValueSet.iterator(); iterator.hasNext();) {
                KPIEvalValueSet kpi = (KPIEvalValueSet) iterator.next();
                kpi.setKpiConv(conv);
            }
        } else if (element instanceof UCMmap || element instanceof GRLGraph) {
        	((IURNDiagram) element).setConcern(concern);
        }

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre something is null"; //$NON-NLS-1$
        assert (element instanceof NodeConnection || element instanceof ComponentRef || element instanceof PathNode || element instanceof UCMmap
                || element instanceof GRLGraph || element instanceof ScenarioStartPoint || element instanceof ScenarioEndPoint
                || element instanceof Initialization || element instanceof Variable || element instanceof GeneralResource || element instanceof KPIConversion) : "pre invalid class"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post something is null"; //$NON-NLS-1$
        if (condition != null && element instanceof NodeConnection)
            assert ((NodeConnection) element).getCondition() == null : "post condition not removed"; //$NON-NLS-1$

        if (element instanceof PassiveResource) {
            PassiveResource passiveResource = (PassiveResource) element;
            assert passiveResource.getComponent() == null : "passive resource still linked"; //$NON-NLS-1$
        } else if (element instanceof ProcessingResource) {
            ProcessingResource processingResource = (ProcessingResource) element;
            assert processingResource.getComponents().size() == 0 : "processing resource still linked"; //$NON-NLS-1$
        }

        if (element instanceof ucm.performance.ExternalOperation) {
            ucm.performance.ExternalOperation exOp = (ucm.performance.ExternalOperation) element;
            assert exOp.getDemands().size() == 0 : "external operation still linked"; //$NON-NLS-1$
        }

        if (element instanceof GeneralResource) {
            GeneralResource resource = (GeneralResource) element;
        }

        if (element instanceof KPIConversion) {
            assert kpiEvalValueSet != null : "post kpi set null";
        }
        
        if ((element instanceof UCMmap || element instanceof GRLGraph) && concern != null) {
        	assert ((IURNDiagram) element).getConcern() == null : "concern not removed"; //@NON-NLS-1$
        }
    }

}
