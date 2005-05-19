package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;

/**
 * Created 2005-05-17
 * 
 * @author Etienne Tremblay
 */
public class LabelTreeEditPart extends UcmModelElementTreeEditPart {

    private URNspec root;

    /**
     * @param model
     */
    public LabelTreeEditPart(Object model, URNspec root) {
        super(model);
        this.root = root;
    }

    public void activate() {
        if (!isActive()) {
            ((EObject) root.getUrndef()).eAdapters().add(this);
        }

        setFlag(FLAG_ACTIVE, true);

        activateEditPolicies();

        List c = getChildren();
        for (int i = 0; i < c.size(); i++)
            if (c.get(i) instanceof EditPart)
                ((EditPart) c.get(i)).activate();

        fireActivated();
    }

    public void deactivate() {
        if (isActive()) {
            ((EObject) root.getUrndef()).eAdapters().remove(this);
        }
        List c = getChildren();
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i) instanceof EditPart)
                ((EditPart) c.get(i)).deactivate();
        }

        deactivateEditPolicies();

        setFlag(FLAG_ACTIVE, false);
        fireDeactivated();
    }

    protected IPropertySource getPropertySource() {
        return null;
    }

    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        if (getLabel().equals("Components"))
            list.addAll(root.getUrndef().getComponents());
        else if (getLabel().equals("Responsibilities"))
            list.addAll(root.getUrndef().getResponsibilities());
        return list;
    }

    protected String getLabel() {
        return (String) getModel();
    }

    protected String getText() {
        return getLabel();
    }

    protected Image getImage() {
        if (super.getImage() == null && getLabel().equals("Components"))
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")).createImage());
        else if (super.getImage() == null && getLabel().equals("Responsibilities"))
            setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage());

        return super.getImage();
    }
}