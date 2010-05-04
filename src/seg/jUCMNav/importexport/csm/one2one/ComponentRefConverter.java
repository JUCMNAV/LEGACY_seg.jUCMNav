package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.Iterator;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.map.ComponentRef;
import ucm.performance.ProcessingResource;
import urncore.Component;
import urncore.ComponentKind;

/**
 * Creates the CSM representation(Component) of the Component-Ref object.
 * 
 */
public class ComponentRefConverter {

    // component references
    public ComponentRef compRef;

    private ComponentRef parentCompRef;

    private ComponentRef childrenCompRef;

    // component definitions
    private Component compDef;

    private Component parentCompDef;

    private Component childrenCompDef;

    // other variables
    private String childrenIDs = new String();

    private String parentID = new String();

    private boolean activeProcess;

    private boolean activeProcStatusDefined;

    public String host = new String();

    // constructors
    public ComponentRefConverter(ComponentRef compRef) {
        this.compRef = compRef;
        compDef = (Component) compRef.getContDef();

        // processing active_process
        activeProcess = false;

        // set is_active_process to true if component is Process or Agent, false
        // if Team or Object, undefined otherwise
        activeProcStatusDefined = false;
        if ((compDef.getKind() == ComponentKind.PROCESS_LITERAL) || (compDef.getKind() == ComponentKind.AGENT_LITERAL)) {
            activeProcess = true;
            activeProcStatusDefined = true;
        } else if ((compDef.getKind() == ComponentKind.TEAM_LITERAL) || (compDef.getKind() == ComponentKind.OBJECT_LITERAL)) {
            activeProcess = false;
            activeProcStatusDefined = true;
        }

        // initialize parentID only if a reference to the parentID component exists
        if (((ComponentRef) compRef.getParent()) != null) {
            parentCompRef = (ComponentRef) compRef.getParent();
            parentCompDef = (Component) parentCompRef.getContDef();
            parentID += "c" + parentCompDef.getId(); //$NON-NLS-1$
        } else {
            parentID += " "; //$NON-NLS-1$
        }

        // retrieve childrenIDs
        for (Iterator iter = compRef.getChildren().listIterator(); iter.hasNext();) {
            childrenCompRef = (ComponentRef) iter.next();
            childrenCompDef = (Component) childrenCompRef.getContDef();
            childrenIDs += "c" + childrenCompDef.getId() + " "; //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps) {

        /*
         * Only convert Process, Agent, Team and Object components to CSM components. For all of those, activeProcStatusDefined is true.
         * 
         * NOT! What if some activeProcStatusDefined() component is contained (parentID) within a !activeProcStatusDefined()??? Answer: There is no ID/IDREF
         * binding for IDREF 'cNNN'.
         */
        // if (!activeProcStatusDefined) return;
        String comp_host = ""; //$NON-NLS-1$
        // resources do not exist yet. js
        if (compRef.getContDef() != null) {
            if (compRef.getContDef() instanceof Component) {
                if (((Component) compRef.getContDef()).getHost() != null) {
                    ProcessingResource procRes = ((Component) compRef.getContDef()).getHost();
                    comp_host = "host=\"" + "r" + procRes.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                }
            }
        }

        // object attributes --- host attribute to be implemanteds
        String id = ((Component) compRef.getContDef()).getId();
        String name = EscapeUtils.escapeXML(((Component) compRef.getContDef()).getName());
        String comp_attributes = "<Component id=\"" + "c" + id + "\" " + "name=\"" + name + "\" " + comp_host + " "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        String traceabilityLink = "traceabilityLink=\"" + compRef.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String close = "/>"; //$NON-NLS-1$

        String comp_attributes_sub = "sub=\"" + childrenIDs + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String comp_attributes_parent = "parent=\"" + parentID + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String comp_attributes_active_process;

        if (activeProcStatusDefined) {
            comp_attributes_active_process = "isActiveProcess=\"" + activeProcess + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            comp_attributes_active_process = ""; //$NON-NLS-1$
        }

        ps.print("        " + comp_attributes + traceabilityLink); //$NON-NLS-1$
        ps.print(" " + comp_attributes_active_process); //$NON-NLS-1$

        if (parentID.compareTo(" ") != 0) { //$NON-NLS-1$
            ps.print(comp_attributes_parent);
        }
        if (childrenIDs.compareTo("") != 0) { //$NON-NLS-1$
            ps.print(comp_attributes_sub);
        }
        ps.println(" " + close); //$NON-NLS-1$
        ps.flush();

    }
}
