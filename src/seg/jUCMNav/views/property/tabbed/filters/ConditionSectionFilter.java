package seg.jUCMNav.views.property.tabbed.filters;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IFilter;

import seg.jUCMNav.views.property.tabbed.mapper.ConditionDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.IPropertyDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.UrnPropertyResolver;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.StartPoint;
import urncore.Responsibility;

public class ConditionSectionFilter implements IFilter {
    protected static IPropertyDataResolver resolver = new ConditionDataResolver();
    protected static IPropertyDataResolver urnResolver = new UrnPropertyResolver();

    public boolean select(Object arg0) {
        if (arg0 instanceof EditPart)
            arg0 = ((EditPart) arg0).getModel();

        if ((arg0 instanceof StartPoint) || (arg0 instanceof EndPoint))
            return false;

        arg0 = urnResolver.getData(arg0);

        arg0 = resolver.getData(arg0);

        return arg0 != null && !(arg0 instanceof Responsibility) && !(arg0 instanceof FailurePoint);
    }
}
