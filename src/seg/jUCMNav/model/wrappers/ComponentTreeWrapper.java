package seg.jUCMNav.model.wrappers;

import ucm.performance.GeneralResource;
import urncore.ComponentElement;

/**
 * This class is a wrapper around components that are children of the resources in the outline. 
 * 
 * @author jkealey
 *
 */
public class ComponentTreeWrapper {

    protected ComponentElement comp;
    protected GeneralResource resx;
    
    public ComponentTreeWrapper(ComponentElement comp, GeneralResource resx) {
        this.comp = comp;
        this.resx = resx;
    }
    
    public void setComp(ComponentElement comp) {
        this.comp = comp;
    }
    
    public ComponentElement getComp() {
        return comp;
    }

    public GeneralResource getResx() {
        return resx;
    }

    public void setResx(GeneralResource resx) {
        this.resx = resx;
    }
    
}
