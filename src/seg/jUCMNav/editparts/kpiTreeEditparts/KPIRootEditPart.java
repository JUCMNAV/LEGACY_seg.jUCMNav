package seg.jUCMNav.editparts.kpiTreeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.UrnAbstractTreeEditPart;

/**
 * This class is the root of editpart
 * 
 * @author pchen
 * 
 */
public class KPIRootEditPart extends UrnAbstractTreeEditPart {
    public KPIRootEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * Return the root URNSpec
     */
    protected List getModelChildren() {
        ArrayList l = new ArrayList();
        l.add(((UCMNavMultiPageEditor) getModel()).getModel().getGrlspec());
        return l;
    }

    /**
     * @see org.eclipse.gef.EditPart#getRoot()
     */
    public RootEditPart getRoot() {
        if (getParent() == null)
            return null;
        else
            return getParent().getRoot();
    }

    public void refreshIndicatorTreeEditPart(EditPart editPart) {
        if (editPart instanceof IndicatorTreeEditPart) {
            ((IndicatorTreeEditPart) editPart).getImage();
            editPart.refresh();
        } else {
            List kpiChildren = editPart.getChildren();
            for (int i = 0; i < kpiChildren.size(); i++) {
                refreshIndicatorTreeEditPart((EditPart) kpiChildren.get(i));
            }
        }
    }
}
