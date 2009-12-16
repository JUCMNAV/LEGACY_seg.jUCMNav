package seg.jUCMNav.views.property.tabbed.mapper;

import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;
import urncore.Condition;
import urncore.Responsibility;

public class ConditionDataResolver implements IPropertyDataResolver {

    public Object getData(Object object) {
        if (object instanceof NodeConnection)
            return ((NodeConnection) object).getCondition();
        else if (object instanceof StartPoint)
            return ((StartPoint) object).getPrecondition();
        else if (object instanceof EndPoint)
            return ((EndPoint) object).getPostcondition();
        else if (object instanceof Responsibility)
            return object;
        else if (object instanceof FailurePoint)
            return object;
        else if (object instanceof Condition)
            return object;

        return null;
    }
}
