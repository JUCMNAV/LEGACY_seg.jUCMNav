package seg.jUCMNav.editparts.concernsTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.editparts.treeEditparts.StubTreeEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * TreeEditPart for a Stub in the Concern Outline
 * 
 * @author gunterm
 */
public class ConcernsStubTreeEditPart extends StubTreeEditPart {

    /**
     * @param model
     *            represents a stub
     */
    public ConcernsStubTreeEditPart(Stub model) {
        super(model);
    }

    /**
     * @return sorted list of plug-in maps
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        for (Iterator iter = getStub().getBindings().iterator(); iter.hasNext();) {
            PluginBinding element = (PluginBinding) iter.next();
            UCMmap map = element.getPlugin();
            if (map != null)
                list.add(map);
        }
        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

}