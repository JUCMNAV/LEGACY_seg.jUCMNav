package seg.jUCMNav.importexport.csm.implicit;

import ucm.map.ComponentRef;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urncore.Component;

/**
 * Provides supports for the management of (UCM elements converted to) CSM resources. A CSM resource may be deduced implicitly from some UCM components and
 * explictly from UCM demands and RA/RA instructions embeded in responsibility metadata.
 * 
 * @author jack
 */
public class CSMResource {

    private GeneralResource genRes; // components->() + responsibility->demand->(external operations)

    private ComponentRef compRef; // implicit resources

    private ResourceAttribs resAtr; // explicit RA/RR within resp. metadata

    private final int UNKNOWN = 0; // the types of resource being handled

    public final int GENRES = 1;

    public final int COMPREF = 2;

    public final int RESATR = 3;

    private int type = UNKNOWN;

    public int getType() {
        return type;
    }

    private String name;

    private String quantity;

    public CSMResource(GeneralResource genRes) {
        this.genRes = genRes;
        type = GENRES;
        name = genRes.getId();
        quantity = this.genRes.getMultiplicity();
    }

    /**
     * A CSM Resource implicitly derived from a UCM Component. <BR>
     * <BR>
     * <EM>NB: the <B>quantity</B> attribute is currently set according to the replication factor of the
     * component.  This is an implementation choice.  An alternative could be to use a fixed value
     * (e.g. '1').</EM>
     * 
     * @param compRef
     */
    public CSMResource(ComponentRef compRef) {
        this.compRef = compRef;
        type = COMPREF;
        name = ((Component) compRef.getContDef()).getId();
        quantity = "" + this.compRef.getReplicationFactor(); //$NON-NLS-1$
    }

    public CSMResource(ResourceAttribs resAtr) {
        this.resAtr = resAtr;
        type = RESATR;
        name = this.resAtr.getResId();
        quantity = "" + this.resAtr.getRUnits(); //$NON-NLS-1$
    }

    public boolean equivalent(CSMResource compRes) {
        return name == compRes.name; // TODO: should this be stronger?
    }

    public String getResource() {
        return name;
    }

    public String getQty() {
        return quantity;
    }

    public boolean isAcquire() {
        return !((resAtr != null) && (resAtr.isRelease()));
    }

    public boolean isRelease() {
        return !((resAtr != null) && (resAtr.isAcquire()));
    }

    public String getResourcePrefix() {
        String resType = null;
        if (getType() == COMPREF) {
            resType = "c"; //$NON-NLS-1$
        } else if (getType() == GENRES) {
            if (genRes instanceof ExternalOperation) {
                resType = "e"; //$NON-NLS-1$
            } else if (genRes instanceof ProcessingResource) {
                resType = "r"; //$NON-NLS-1$
            } else if (genRes instanceof PassiveResource) {
                resType = "p"; //$NON-NLS-1$
            } else {
                resType = "u"; // unforeseen case... //$NON-NLS-1$
            }
        } else if (getType() == RESATR) {
            // resType = "a";
            if (resAtr.getRes() instanceof ExternalOperation) {
                resType = "e"; //$NON-NLS-1$
            } else if (resAtr.getRes() instanceof ProcessingResource) {
                resType = "r"; //$NON-NLS-1$
            } else if (resAtr.getRes() instanceof PassiveResource) {
                resType = "p"; //$NON-NLS-1$
            } else {
                resType = "u"; // unforeseen case... //$NON-NLS-1$
            }
        }
        return resType;
    }
}