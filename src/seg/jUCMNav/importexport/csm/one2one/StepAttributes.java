package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.performance.ProcessingResource;
import urncore.Component;
import urncore.ComponentKind;
import urncore.IURNContainerRef;

/**
 * Prints Step optional attributes to CSM file.
 * 
 */
public class StepAttributes {
    public void OptionalAttributes(PathNode af, PrintStream ps) {
        printDescription(ps, af);
        Component(ps, af);
        hostDemand(ps, af);
        printRepCount(ps, af);
        tracebilityLink(ps, af);
    }

    public static void tracebilityLink(PrintStream ps, PathNode af) {
        String traceabilityLink = "traceabilityLink=\"" + af.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print(traceabilityLink);
    }

    public static ProcessingResource pr;

    // print hostDemand
    public static void hostDemand(PrintStream ps, PathNode pathnode) {
        String hostDemand;
        String hostDemand_attribute;
        if (pathnode instanceof RespRef) {
            hostDemand = ((RespRef) pathnode).getHostDemand();
            if ((hostDemand != null) && (hostDemand != "")) { //$NON-NLS-1$
                hostDemand_attribute = "hostDemand=\"" + hostDemand + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print(hostDemand_attribute);
            }
        }

    }

    // print component id
    public static void Component(PrintStream ps, PathNode pathnode) {
        IURNContainerRef contRef = pathnode.getContRef();
        if ((ComponentRef) contRef != null && ((ComponentRef) contRef).getId() != null) {
            if (contRef.getContDef() instanceof Component) {
                Component compReg = (Component) contRef.getContDef();
                ComponentKind compKind = compReg.getKind();
                /*
                 * Produce the "component" attribute only if the component reference is refering to a component of kind Process, Agent, Teamn or Object.
                 */
                if ((compKind == ComponentKind.PROCESS_LITERAL) || (compKind == ComponentKind.AGENT_LITERAL) || (compKind == ComponentKind.TEAM_LITERAL)
                        || (compKind == ComponentKind.OBJECT_LITERAL)) {
                    String comp_id = ((Component) (((ComponentRef) contRef).getContDef())).getId();
                    String comp_attribute = "component=\"" + "c" + comp_id + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    ps.print(comp_attribute);
                }
            }
        }
    }

    // prints description attribute
    public static void printDescription(PrintStream ps, PathNode pathnode) {
        if (pathnode.getDescription() != null) {
            String description_attribute = "description=\"" + EscapeUtils.escapeXML(pathnode.getDescription()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(description_attribute);
        }
    }

    // prints repitition count (repCount)
    public static void printRepCount(PrintStream ps, PathNode pathnode) {
        String repCount;
        String repCount_attribute = "repCount=\"" + "1" + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        if (pathnode instanceof RespRef) {
            repCount = ((RespRef) pathnode).getRepetitionCount();
            if ((repCount != null) && (repCount != "1")) { //$NON-NLS-1$
                repCount_attribute = "repCount=\"" + repCount + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        if (pathnode instanceof Stub) {
            repCount = ((Stub) pathnode).getRepetitionCount();
            if ((repCount != null) && (repCount != "1")) { //$NON-NLS-1$
                repCount_attribute = "repCount=\"" + repCount + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        ps.print(repCount_attribute);
    }
}
