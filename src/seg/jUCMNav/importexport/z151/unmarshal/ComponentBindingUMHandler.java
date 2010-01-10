package seg.jUCMNav.importexport.z151.unmarshal;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ComponentBinding  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ComponentBinding">
//	<xsd:sequence>
//		<xsd:element name="id" type="xsd:ID" /> <!-- ADDED -->
//		<xsd:element name="parentComponent" type="xsd:IDREF" /> <!-- ComponentRef -->
//		<xsd:element name="pluginComponent" type="xsd:IDREF" /> <!-- ComponentRef -->
//	</xsd:sequence>
//</xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ComponentBinding;
import seg.jUCMNav.model.ModelCreationFactory;

public class ComponentBindingUMHandler extends EObjectImplUMHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        ComponentBinding elemZ = (ComponentBinding) o;
        String objId = elemZ.getId();
        ucm.map.ComponentBinding elem = (ucm.map.ComponentBinding) getObject(objId, target, ucm.map.ComponentBinding.class);
        if (null == elem) {
            if (null == target) {
                elem = (ucm.map.ComponentBinding) ModelCreationFactory
                .getNewObject(urn, ucm.map.ComponentBinding.class);
            }
            else
                elem = (ucm.map.ComponentBinding) target;
        }
        if (isFullConstruction) {
            //			elem.setBinding(); handled by PluginBindingUMHandler
            elem.setParentComponent((ucm.map.ComponentRef) process(elemZ.getParentComponent(), null, false));
            elem.setPluginComponent((ucm.map.ComponentRef) process(elemZ.getPluginComponent(), null, false));

            //			elem.getBinding();
            //			elem.getParentComponent();
            //			elem.getPluginComponent();
            //			elem.getClass();
        }
        return elem;
    }
}
