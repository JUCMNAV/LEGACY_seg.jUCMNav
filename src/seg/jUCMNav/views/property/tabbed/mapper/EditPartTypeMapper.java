package seg.jUCMNav.views.property.tabbed.mapper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.LabelEditPart;

/**
 * This class maps EditParts to their model object so that the
 * TabbedPropertySheetPage knows witch property section to show.
 * 
 * @author Etienne
 * 
 */
public class EditPartTypeMapper extends AbstractTypeMapper
{

	public Class mapType(Object object)
	{
		if (object instanceof LabelEditPart)
		{
			LabelEditPart labelEditPart = (LabelEditPart) object;
			if (labelEditPart instanceof ConditionEditPart)
				return mapType(labelEditPart.getModelObj());
			if (labelEditPart.getModelObj().eContainer()!=null)
				return labelEditPart.getModelObj().eContainer().getClass();
		}
		else if (object instanceof EditPart)
		{
			return ((EditPart) object).getModel().getClass();
		}
		else if (object instanceof EObject)
		{
			return ((EObject) object).getClass();
		}

		return super.mapType(object);
	}
}
