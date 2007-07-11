package seg.jUCMNav.views.wizards.metadata;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.util.HashMap;

import seg.jUCMNav.Messages;
import ucm.map.PathNode;
import ucm.map.RespRef;
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
    public static final HashMap urnElementTypes = new HashMap();
    public static final String[] urnElementTypeNames = new String[] {
        Messages.getString("URNmodelElementType.All"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Actor"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.ElementLink"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.EvaluationStrategy"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.GrlGraph"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.GrlNode"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.IntentionalElement"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.StrategiesGroup"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Component"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Enumeration"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.PathNode"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.PerfMeasure"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.ResponseTimeReq"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Responsibility"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.RespRef"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Scenario"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.ScenarioGroup"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.UcmMap"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Variable"), //$NON-NLS-1$
        Messages.getString("URNmodelElementType.Workload") //$NON-NLS-1$
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
        urnElementTypes.put(urnElementTypeNames[14], RespRef.class);
        urnElementTypes.put(urnElementTypeNames[15], ScenarioDef.class);
        urnElementTypes.put(urnElementTypeNames[16], ScenarioGroup.class);
        urnElementTypes.put(urnElementTypeNames[17], UCMmap.class);
        urnElementTypes.put(urnElementTypeNames[18], Variable.class);
        urnElementTypes.put(urnElementTypeNames[19], Workload.class);
    }
}
