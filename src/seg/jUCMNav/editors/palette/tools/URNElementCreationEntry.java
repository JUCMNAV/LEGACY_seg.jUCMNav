package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * CreationEntry that loads UCMElementCreationTool
 * 
 * @author jkealey
 */
public class URNElementCreationEntry extends CombinedTemplateCreationEntry {
    public URNElementCreationEntry(String label, String shortDesc, Object template, CreationFactory factory, ImageDescriptor iconSmall,
            ImageDescriptor iconLarge) {
        super(label, shortDesc, template, /* factory */null, iconSmall, iconLarge);
        myFactory = factory;
    }

    protected CreationFactory myFactory = null;

    /**
     * @return a UCMElementCreationTool
     */
    public Tool createTool() {
        return new URNElementCreationTool(myFactory);
    }

    public void setFactory(CreationFactory factory) {
        this.myFactory = factory;
    }
}