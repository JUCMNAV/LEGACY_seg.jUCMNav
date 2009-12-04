package seg.jUCMNav.importexport.csm.implicit;

import ucm.performance.GeneralResource;

/**
 * Manage General Resource attributes
 * 
 * @author jack
 */
public class ResourceAttribs {
    private GeneralResource resource;

    private String name = null;

    private String rUnits;

    private final int ACQUIRE = 1; // when defined via responsibility's
    // MetaData

    private final int RELEASE = -1; // when defined via responsibility's
    // MetaData

    private final int BOTH = 0; // default

    private int usage = BOTH;

    public ResourceAttribs(GeneralResource genRes) {
        resource = genRes;
    }

    public ResourceAttribs(String name) {
        this.name = name;
    }

    public String getResId() {
        return (name != null) ? name : resource.getId();
    }

    public void setRes(GeneralResource genRes) {
        resource = genRes;
    }

    public GeneralResource getRes() {
        return resource;
    }

    /**
     * 
     * @param qty
     *            number of resources
     */
    public void setRUnits(String qty) {
        rUnits = qty;
    }

    public String getRUnits() {
        return rUnits;
    }

    public void setAcquire() {
        usage = ACQUIRE;
    }

    /**
     * 
     * @return TRUE if this resource deals with ACQUISITION
     */
    public boolean isAcquire() {
        return (usage == ACQUIRE) || (usage == BOTH);
    }

    public void setRelease() {
        usage = RELEASE;
    }

    /**
     * 
     * @return TRUE if this resource deals with RELEASE
     */
    public boolean isRelease() {
        return (usage == RELEASE) || (usage == BOTH);
    }
}