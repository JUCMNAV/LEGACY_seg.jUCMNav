package seg.jUCMNav.views.wizards.metadata;

import grl.Actor;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.StrategiesGroup;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;

import java.util.HashMap;

import seg.jUCMNav.Messages;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import ucm.performance.Workload;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;
import urncore.Component;
import urncore.Responsibility;
import urncore.URNmodelElement;
import urn.URNspec;

/**
 * URNmodelElementType.
 * 
 * @author pchen
 */
public class URNmodelElementType {
    public static final HashMap urnElementTypes = new HashMap();
    public static final String[] urnElementTypeNames = new String[] { Messages.getString("URNmodelElementType.All"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Actor"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Contribution"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Decomposition"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Dependency"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.EvaluationStrategy"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.GrlGraph"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.IntentionalElement"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.IndicatorGroup"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.KPIInformationElement"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.KPIModelLink"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.GrlNode"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.StrategiesGroup"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Component"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Enumeration"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.UcmMap"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.PathNode"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Responsibility"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.RespRef"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Scenario"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.ScenarioGroup"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Variable"), //$NON-NLS-1$
            Messages.getString("URNmodelElementType.Workload"), //$NON-NLS-1$
            Messages.getString("URNspecType.URNspec") //$NON-NLS-1$
    };

    static {
        urnElementTypes.put(urnElementTypeNames[0], URNmodelElement.class);
        urnElementTypes.put(urnElementTypeNames[1], Actor.class);
        urnElementTypes.put(urnElementTypeNames[2], Contribution.class);
        urnElementTypes.put(urnElementTypeNames[3], Decomposition.class);
        urnElementTypes.put(urnElementTypeNames[4], Dependency.class);
        urnElementTypes.put(urnElementTypeNames[5], EvaluationStrategy.class);
        urnElementTypes.put(urnElementTypeNames[6], GRLGraph.class);
        urnElementTypes.put(urnElementTypeNames[7], IntentionalElement.class);
        urnElementTypes.put(urnElementTypeNames[8], IndicatorGroup.class);
        urnElementTypes.put(urnElementTypeNames[9], KPIInformationElement.class);
        urnElementTypes.put(urnElementTypeNames[10], KPIModelLink.class);
        urnElementTypes.put(urnElementTypeNames[11], GRLNode.class);
        urnElementTypes.put(urnElementTypeNames[12], StrategiesGroup.class);
        urnElementTypes.put(urnElementTypeNames[13], Component.class);
        urnElementTypes.put(urnElementTypeNames[14], EnumerationType.class);
        urnElementTypes.put(urnElementTypeNames[15], UCMmap.class);
        urnElementTypes.put(urnElementTypeNames[16], PathNode.class);
        urnElementTypes.put(urnElementTypeNames[17], Responsibility.class);
        urnElementTypes.put(urnElementTypeNames[18], RespRef.class);
        urnElementTypes.put(urnElementTypeNames[19], ScenarioDef.class);
        urnElementTypes.put(urnElementTypeNames[20], ScenarioGroup.class);
        urnElementTypes.put(urnElementTypeNames[21], Variable.class);
        urnElementTypes.put(urnElementTypeNames[22], Workload.class);
        urnElementTypes.put(urnElementTypeNames[23], URNspec.class);
    }
}
