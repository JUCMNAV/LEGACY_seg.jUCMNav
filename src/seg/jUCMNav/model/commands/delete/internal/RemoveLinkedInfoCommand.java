package seg.jUCMNav.model.commands.delete.internal;

import grl.GRLGraph;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import urncore.Condition;

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
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element instanceof NodeConnection)
            this.condition = ((NodeConnection) element).getCondition();
        else if (element instanceof ScenarioStartPoint)
        	this.scenarioPathNode = ((ScenarioStartPoint)element).getStartPoint();
        else if (element instanceof ScenarioEndPoint)
        	this.scenarioPathNode = ((ScenarioEndPoint)element).getEndPoint();  
        else if (element instanceof Initialization)
        	this.scenario = ((Initialization)element).getScenarioDef();        
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
        	((ScenarioStartPoint)element).setStartPoint(null);
        else if (element instanceof ScenarioEndPoint)
        	((ScenarioEndPoint)element).setEndPoint(null);
        else if (element instanceof Initialization)
        	((Initialization)element).setScenarioDef(null);

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
        	((ScenarioStartPoint)element).setStartPoint((StartPoint)scenarioPathNode);
        else if (element instanceof ScenarioEndPoint)
        	((ScenarioEndPoint)element).setEndPoint((EndPoint)scenarioPathNode);  
        else if (element instanceof Initialization)
        	((Initialization)element).setScenarioDef(scenario);        
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre something is null"; //$NON-NLS-1$
        assert (element instanceof NodeConnection || element instanceof ComponentRef || element instanceof PathNode || element instanceof UCMmap || element instanceof ScenarioStartPoint || element instanceof ScenarioEndPoint || element instanceof Initialization) : "pre invalid class"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post something is null"; //$NON-NLS-1$
        if (condition != null && element instanceof NodeConnection)
            assert ((NodeConnection) element).getCondition() == null : "post condition not removed"; //$NON-NLS-1$
    }

}
