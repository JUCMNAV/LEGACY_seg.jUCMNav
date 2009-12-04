package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

import urn.URNspec;

/**
 * ToolEntry that loads the PathTool.
 * 
 * @author Etienne Tremblay
 */
public class PathToolEntry extends ToolEntry {

    private URNspec urn;

    /**
     * @param label
     * @param shortDesc
     * @param iconSmall
     * @param iconLarge
     */
    public PathToolEntry(String label, String shortDesc, URNspec urn, ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
        super(label, shortDesc, iconSmall, iconLarge);
        this.urn = urn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.palette.ToolEntry#createTool()
     */
    public Tool createTool() {
        return new PathTool(urn);
    }
}
