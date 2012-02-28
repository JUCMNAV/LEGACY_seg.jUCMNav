package seg.jUCMNav.editors.palette.tools;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.resource.ImageDescriptor;

public class BaseConnectionCreationToolEntry extends ConnectionCreationToolEntry {

    public BaseConnectionCreationToolEntry(String label, String shortDesc, CreationFactory factory, ImageDescriptor iconSmall, ImageDescriptor iconLarge) {
        super(label, shortDesc, factory, iconSmall, iconLarge);
    }

    public Tool createTool() {
        return new BaseConnectionCreationTool((CreationFactory)getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY));
    }

}
