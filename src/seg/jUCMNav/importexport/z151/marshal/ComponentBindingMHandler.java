package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ComponentBinding  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ComponentBinding">
//    <xsd:sequence>
//	    <xsd:element name="parentComponent" type="xsd:IDREF"/> <!-- ComponentRef -->
//      <xsd:element name="pluginComponent" type="xsd:IDREF"/> <!-- ComponentRef -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ComponentBinding;
import seg.jUCMNav.importexport.z151.generated.ComponentRef;

public class ComponentBindingMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.ComponentBinding elem = (ucm.map.ComponentBinding) o;
		ComponentBinding elemZ = null;
		if (null == target)
			elemZ = of.createComponentBinding();
		else
			elemZ = (ComponentBinding) target;
		if (isFullConstruction) {
			ComponentRef crZ = of.createComponentRef();
			elemZ.setParentComponent(process(elem.getParentComponent(), null, false));
			elemZ.setPluginComponent(process(elem.getPluginComponent(), null, false));
		}
		return elemZ;
	}
}
