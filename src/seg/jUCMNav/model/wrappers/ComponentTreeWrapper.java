package seg.jUCMNav.model.wrappers;

import ucm.performance.GeneralResource;
import urncore.Component;

/**
 * This class is a wrapper around components that are children of the resources in the outline.
 * 
 * @author jkealey
 * 
 */
public class ComponentTreeWrapper {

    protected Component comp;
    protected GeneralResource resx;

    public ComponentTreeWrapper(Component comp, GeneralResource resx) {
        this.comp = comp;
        this.resx = resx;
    }

    public void setComp(Component comp) {
        this.comp = comp;
    }

    public Component getComp() {
        return comp;
    }

    public GeneralResource getResx() {
        return resx;
    }

    public void setResx(GeneralResource resx) {
        this.resx = resx;
    }

}
