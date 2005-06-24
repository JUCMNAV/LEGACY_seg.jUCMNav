package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Created on 24-Jun-2005
 * 
 * CreationEntry that loads UCMELementCreationTool
 * 
 * @author jkealey
 *  
 */
public class UCMElementCreationEntry extends CombinedTemplateCreationEntry {
    public UCMElementCreationEntry(String label, String shortDesc, Object template, CreationFactory factory, ImageDescriptor iconSmall,
            ImageDescriptor iconLarge) {
        super(label, shortDesc, template, factory, iconSmall, iconLarge);

    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.palette.CreationToolEntry#createTool()
     */
    public Tool createTool() {
        return new UCMElementCreationTool(factory);
    }
}