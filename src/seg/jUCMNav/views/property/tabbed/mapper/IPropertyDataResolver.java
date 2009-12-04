package seg.jUCMNav.views.property.tabbed.mapper;

/**
 * For a given selected object, we don't necessarily want to after the properties of the root object. Sometimes it can be a child of the root object.
 * 
 * @author Etienne
 * 
 */
public interface IPropertyDataResolver {
    /**
     * Should return the real object we want to change the properties from.
     * 
     * @param obj
     * @return
     */
    Object getData(Object obj);
}
