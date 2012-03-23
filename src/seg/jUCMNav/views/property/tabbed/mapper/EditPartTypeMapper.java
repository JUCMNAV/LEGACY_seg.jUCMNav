package seg.jUCMNav.views.property.tabbed.mapper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;

/**
 * This class maps EditParts to their model object so that the TabbedPropertySheetPage knows witch property section to show.
 * 
 * @author Etienne
 * 
 */
public class EditPartTypeMapper extends AbstractTypeMapper {

    public Class mapType(Object object) {
        if (object instanceof LabelEditPart) {
            LabelEditPart labelEditPart = (LabelEditPart) object;
            if (labelEditPart instanceof ConditionEditPart)
                return mapType(labelEditPart.getModelObj());
            if(labelEditPart instanceof ConnectionEditPart)
                return mapType(labelEditPart.getModelObj());
            if (labelEditPart.getModelObj().eContainer() != null)
                return labelEditPart.getModelObj().eContainer().getClass();
        }
        if (object instanceof LinkRefEditPart) {
            LinkRefEditPart linkRefEditPart = (LinkRefEditPart) object;
            return linkRefEditPart.getLinkRef().getLink().getClass();
        } else if (object instanceof EditPart) {
            if (((EditPart)object).getModel() == null)
                return object.getClass(); // bug 818 - rare but just to avoid errors - deleted items for which properties are shown 
            else
                return ((EditPart) object).getModel().getClass();
        } else if (object instanceof EObject) {
            return ((EObject) object).getClass();
        }

        return super.mapType(object);
    }
}
