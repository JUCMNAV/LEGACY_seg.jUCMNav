package seg.jUCMNav.importexport.z151.unmarshal;

//	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//	<!--  PluginBinding  -->
//	<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//	<xsd:complexType name="PluginBinding">
//		<xsd:sequence>
//			<xsd:element name="id" type="xsd:ID" />
//			<xsd:element default="100" name="probability" type="xsd:nonNegativeInteger" />
//			<xsd:element name="replicationFactor" type="xsd:string" />
//			<xsd:element maxOccurs="unbounded" minOccurs="0" name="in"
//				type="InBinding" />
//			<xsd:element maxOccurs="unbounded" minOccurs="0" name="out"
//				type="OutBinding" />
//			<xsd:element name="plugin" type="xsd:IDREF" /> <!-- UCMmap -->
//			<xsd:element minOccurs="0" name="precondition" type="Condition" />
//			<xsd:element maxOccurs="unbounded" minOccurs="0" name="components"
//				type="ComponentBinding" />
//		</xsd:sequence>
//	</xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.PluginBinding;
import seg.jUCMNav.importexport.z151.generated.UCMmap;

public class PluginBindingUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		PluginBinding elemZ = (PluginBinding) o;
		String objId = elemZ.getId();
		ucm.map.PluginBinding elem = (ucm.map.PluginBinding) getObject(elemZ.getId(), target, ucm.map.PluginBinding.class);
		if (isFullConstruction) {
			elem.setId(elemZ.getId());
			if (elemZ.getPrecondition() != null) {
				urncore.Condition condition = (urncore.Condition) process(elemZ.getPrecondition(), null, true);
				elem.setPrecondition(condition);
				if (null != condition)
					condition.setPluginBinding(elem);
			}
			elem.setProbability(elemZ.getProbability().intValue() / 100);

			elem.setTransaction(false); // set to default
			elem.setReplicationFactor(Integer.valueOf(elemZ.getReplicationFactor()));
			elem.setPlugin((ucm.map.UCMmap) process((UCMmap) elemZ.getPlugin(), null, false));
			// elem.setStub(); handled by StubUMHandler

			// elem.getPrecondition();
			// elem.getProbability();
			// elem.getReplicationFactor();
			processList(elemZ.getIn(), elem.getIn(), true);
			for (Object item : elem.getIn()) {
				((ucm.map.InBinding) item).setBinding(elem);
			}
			processList(elemZ.getOut(), elem.getOut(), true);
			for (Object item : elem.getOut()) {
				((ucm.map.OutBinding) item).setBinding(elem);
			}
			// elem.getStub();
			// elem.getPlugin();
			// elem.getId();
			processList(elemZ.getComponents(), elem.getComponents(), true);
			for (Object item : elem.getComponents()) {
				((ucm.map.ComponentBinding) item).setBinding(elem);
			}
			// elem.getClass();
		}
		return elem;
	}
}
