package seg.jUCMNav.views.wizards.metadata;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.util.HashMap;

import ucm.map.PathNode;
import ucm.map.UCMmap;
import ucm.performance.PerfMeasure;
import ucm.performance.ResponseTimeReq;
import ucm.performance.Workload;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;
import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class URNmodelElementType {
    public static HashMap urnElementTypes = new HashMap();
    public static String[] urnElementTypeNames = new String[] {
        "All",
        "Actor",
        "Element Link",
        "Evaluation Strategy",
        "GRL Graph",
        "GRL Node",
        "Intentional Element",
        "Strategies Group",
        "Component",
        "Enumeration",
        "Path Node",
        "Perf Measure",
        "Response Time Req",
        "Responsibility",
        "Scenario",
        "Scenario Group",
        "UCM Map",
        "Variable",
        "Workload"
    };

    static {
        urnElementTypes.put(urnElementTypeNames[0], URNmodelElement.class);
        urnElementTypes.put(urnElementTypeNames[1], Actor.class);
        urnElementTypes.put(urnElementTypeNames[2], ElementLink.class);
        urnElementTypes.put(urnElementTypeNames[3], EvaluationStrategy.class);
        urnElementTypes.put(urnElementTypeNames[4], GRLGraph.class);
        urnElementTypes.put(urnElementTypeNames[5], GRLNode.class);
        urnElementTypes.put(urnElementTypeNames[6], IntentionalElement.class);
        urnElementTypes.put(urnElementTypeNames[7], StrategiesGroup.class);
        urnElementTypes.put(urnElementTypeNames[8], ComponentElement.class);
        urnElementTypes.put(urnElementTypeNames[9], EnumerationType.class);
        urnElementTypes.put(urnElementTypeNames[10], PathNode.class);
        urnElementTypes.put(urnElementTypeNames[11], PerfMeasure.class);
        urnElementTypes.put(urnElementTypeNames[12], ResponseTimeReq.class);
        urnElementTypes.put(urnElementTypeNames[13], Responsibility.class);
        urnElementTypes.put(urnElementTypeNames[14], ScenarioDef.class);
        urnElementTypes.put(urnElementTypeNames[15], ScenarioGroup.class);
        urnElementTypes.put(urnElementTypeNames[16], UCMmap.class);
        urnElementTypes.put(urnElementTypeNames[17], Variable.class);
        urnElementTypes.put(urnElementTypeNames[18], Workload.class);
    }
}
