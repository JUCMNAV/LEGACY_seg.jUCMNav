package seg.jUCMNav.importexport.csm.implicit;

import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.importexport.csm.Messages;
import seg.jUCMNav.importexport.csm.one2one.CsmExportWarning;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.performance.Demand;
import ucm.performance.GeneralResource;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.Metadata;
import urncore.Responsibility;

/**
 * Provides supports for the management of sets of CSM resources.
 * 
 * @author jack
 */
public class CSMResourceSet {
    private final int RESLIMIT = 500; // TODO: remove limitation *** MAXIMUM NUMBER OF RESOURCES ***

    private CSMResource[] resources = new CSMResource[RESLIMIT];

    private int resources_count = 0;

    public CSMResourceSet(PathNode pathnode, Vector warnings) {
        // Add the resources bound to components, implicitly and explicitly
        getContainingComponentsAndResources((ComponentRef) pathnode.getContRef(), resources);
        // Responsibilities:
        if (pathnode instanceof RespRef) {
            RespRef respref = (RespRef) pathnode;
            // Add resources commanded by demands tied to respnsibilities (external opn)
            if (respref.getRespDef().getDemands().size() != 0) {
                for (int i = 0; i < respref.getRespDef().getDemands().size(); i++) {
                    Responsibility resp = respref.getRespDef();
                    for (int j = 0; j < resp.getDemands().size(); j++) {
                        Demand demand = (Demand) resp.getDemands().get(j);
                        resources[resources_count++] = new CSMResource(demand.getResource());
                    }
                }
            }
            // Add resources commanded by MetaData tied to responsibilities
            for (Iterator md = respref.getMetadata().iterator(); md.hasNext();) {
                Metadata mdElement = (Metadata) md.next();
                if (mdElement.getName().equals("RR")) { //$NON-NLS-1$
                    if (md.hasNext()) {
                        Metadata mdValue = (Metadata) md.next();
                        if (mdValue.getName().equals("Qty")) { //$NON-NLS-1$
                            URNspec urn = respref.getRespDef().getUrndefinition().getUrnspec();
                            boolean found = false;
                            for (Iterator genRes = urn.getUcmspec().getResources().iterator(); genRes.hasNext();) {
                                GeneralResource genResElement = (GeneralResource) genRes.next();
                                if (genResElement.getName().equals(mdElement.getValue())) {
                                    // A corresponding resource (same name) has been found
                                    found = true;
                                    ResourceAttribs resAttr = new ResourceAttribs(genResElement);
                                    resAttr.setRUnits(mdValue.getValue());
                                    resAttr.setRelease();
                                    resources[resources_count++] = new CSMResource(resAttr);
                                }
                            }
                            if (!found) {
                                warnings
                                        .add(new CsmExportWarning(
                                                Messages.getString("CSMResourceSet.ReleasedResource") + mdElement.getValue() + Messages.getString("CSMResourceSet.NotFoundInRespMD") + respref.getRespDef().getName() + Messages.getString("CSMResourceSet.ReleaseSkipped"), respref)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            }

                        } else {
                            warnings.add(new CsmExportWarning(Messages.getString("CSMResourceSet.Responsibility") + respref.getRespDef().getName() //$NON-NLS-1$
                                    + Messages.getString("CSMResourceSet.ContainsRRnoQty"), respref)); //$NON-NLS-1$
                        }
                    } else {
                        warnings
                                .add(new CsmExportWarning(
                                        Messages.getString("CSMResourceSet.Responsibility") + respref.getRespDef().getName() + Messages.getString("CSMResourceSet.ContainsRRnoMD"), respref)); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                } else if (mdElement.getName().equals("RA")) { //$NON-NLS-1$
                    if (md.hasNext()) {
                        Metadata mdValue = (Metadata) md.next();
                        if (mdValue.getName().equals("Qty")) { //$NON-NLS-1$
                            URNspec urn = respref.getRespDef().getUrndefinition().getUrnspec();
                            boolean found = false;
                            for (Iterator genRes = urn.getUcmspec().getResources().iterator(); genRes.hasNext();) {
                                GeneralResource genResElement = (GeneralResource) genRes.next();
                                if (genResElement.getName().compareTo(mdElement.getValue()) == 0) {
                                    // A corresponding resource (same name) has been found
                                    found = true;
                                    ResourceAttribs resAttr = new ResourceAttribs(genResElement);
                                    resAttr.setRUnits(mdValue.getValue());
                                    resAttr.setAcquire();
                                    resources[resources_count++] = new CSMResource(resAttr);
                                }
                            }
                            if (!found) {
                                warnings
                                        .add(new CsmExportWarning(
                                                Messages.getString("CSMResourceSet.AcquiredResource") + mdElement.getValue() + Messages.getString("CSMResourceSet.NotFoundInRespMD") + respref.getRespDef().getName() + Messages.getString("CSMResourceSet.AcquireSkipped"), respref)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            }
                        } else {
                            warnings.add(new CsmExportWarning(Messages.getString("CSMResourceSet.Responsibility") + respref.getRespDef().getName() //$NON-NLS-1$
                                    + Messages.getString("CSMResourceSet.ContainsRANoQty"), respref)); //$NON-NLS-1$
                        }

                    } else {
                        warnings
                                .add(new CsmExportWarning(
                                        Messages.getString("CSMResourceSet.Responsibility") + respref.getRespDef().getName() + Messages.getString("CSMResourceSet.ContainsRANoMD"), respref)); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }
    }

    public CSMResourceSet(CSMResource[] resArrayList, int size) {
        resources = resArrayList;
        resources_count = size;
    }

    public CSMResource get(int n) {
        return resources[n];
    }

    public void remove(int n) {
        if (n < resources_count) { // TODO: look into replacing test with assert?
            for (int i = 0; i < resources_count; i++) {
                if (i > n) {
                    resources[i - 1] = resources[i];
                } else {
                    resources[i] = resources[i];
                }
            }
            resources_count--;
        }
    }

    /**
     * Registers a resource to be managed for each of the following cases:
     * <UL>
     * <LI>UCM Component of kind Team, Object, Process and Agent
     * <LI>ProcessingResource bound to a UCM Component
     * <LI>PassiveResource bound to a UCM Component
     * </UL>
     * 
     * @param compRef
     * @param resourcesIn
     */
    public void getContainingComponentsAndResources(ComponentRef compRef, CSMResource[] resourcesIn) {
        if (compRef != null) {
            // In order to obtain outermost precedence component-wise, traversal is processed
            // using a head recursion approach
            getContainingComponentsAndResources((ComponentRef) compRef.getParent(), resourcesIn);
            // TYPE: UCM Component of kind Team, Object, Process and Agent
            if (compRef.getContDef() instanceof Component) {
                // TODO: check that only Component has a *kind* ?
                if (((Component) compRef.getContDef()).getKind().equals(ComponentKind.TEAM_LITERAL)
                        || ((Component) compRef.getContDef()).getKind().equals(ComponentKind.OBJECT_LITERAL)
                        || ((Component) compRef.getContDef()).getKind().equals(ComponentKind.PROCESS_LITERAL)
                        || ((Component) compRef.getContDef()).getKind().equals(ComponentKind.AGENT_LITERAL)) {
                    resourcesIn[resources_count++] = new CSMResource(compRef);
                }
                // TYPE: ProcessingResource bound to a UCM Component
                if (((Component) compRef.getContDef()).getResource() != null) {
                    resourcesIn[resources_count++] = new CSMResource(((Component) compRef.getContDef()).getResource());
                }
                // Possibly a futile test (because Component seem inexistent...). Nevertheless...
                // TYPE: PassiveResource bound to a UCM Component
            } else if (compRef.getContDef() instanceof Component) {
                if (((Component) compRef.getContDef()).getResource() != null) {
                    resourcesIn[resources_count++] = new CSMResource(((Component) compRef.getContDef()).getResource());
                }
            }
        }
    }

    public int size() {
        return resources_count;
    }

    public void add(CSMResourceSet addedRes) {
        for (int i = 0; i < addedRes.resources_count; i++) {
            if (!contains(addedRes.resources[i])) {
                resources[resources_count++] = addedRes.resources[i];
            }
        }
    }

    public CSMResourceSet toAcquire() {
        CSMResource[] temp = new CSMResource[RESLIMIT];
        int count = 0;
        for (int i = 0; i < resources_count; i++) {
            if (resources[i].isAcquire()) {
                temp[count++] = resources[i];
            }
        }
        return new CSMResourceSet(temp, count);
    }

    public CSMResourceSet toRelease() {
        CSMResource[] temp = new CSMResource[RESLIMIT];
        int count = 0;
        for (int i = 0; i < resources_count; i++) {
            if (resources[i].isRelease()) {
                temp[count++] = resources[i];
            }
        }
        return new CSMResourceSet(temp, count);
    }

    /**
     * Returns elements of this CSMResourceSet which are not in (CSMResourceSet) second
     * 
     * @param second
     *            CSMResourceSet of which the elements should be removed from this
     * @return CSMResourceSet == this - second
     */
    public CSMResourceSet minus(CSMResourceSet second) {
        CSMResource[] curMinusSecond = new CSMResource[RESLIMIT];
        int count = 0;
        for (int i = 0; i < resources_count; i++) {
            if ((second == null) || (!second.contains(resources[i]))) {
                curMinusSecond[count++] = resources[i];
            }
        }
        return new CSMResourceSet(curMinusSecond, count);
    }

    private boolean contains(CSMResource res) {
        boolean found = false;
        for (int i = 0; (i < resources_count) && (!found); i++) {
            if (resources[i].equivalent(res)) {
                found = true;
            }
        }
        return found;
    }
}
