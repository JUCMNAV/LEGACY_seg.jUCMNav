package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  PluginBinding  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="PluginBinding">
//    <xsd:sequence>
//      <xsd:element name="id" type="xsd:string"/>
//      <xsd:element default="100" name="probability" type="xsd:nonNegativeInteger"/>
//      <xsd:element name="replicationFactor" type="xsd:string"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="in" type="InBinding"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="out" type="OutBinding"/>
//      <xsd:element name="plugin" type="xsd:IDREF"/> <!-- UCMmap -->
//      <xsd:element minOccurs="0" name="precondition" type="Condition"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="components" type="ComponentBinding"/>
//    </xsd:sequence>
//  </xsd:complexType>

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.*;

public class PluginBindingMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.PluginBinding elem = (ucm.map.PluginBinding) o;
		String objId = this.getObjectId(elem);//elem.getId();
		PluginBinding elemZ = (PluginBinding) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createPluginBinding();
				elemZ.setId(objId);
			}else
				elemZ = (PluginBinding) target;
			id2object.put(objId, elemZ);
		}
		
		if (isFullConstruction) {
			//probability
			elemZ.setProbability(new BigInteger(Integer.toString((int) (elem.getProbability() * 100))));
			
			//replicationFactor
			elemZ.setReplicationFactor(Integer.toString(elem.getReplicationFactor()));
			//plugin
			elemZ.setPlugin(process(elem.getPlugin(), null, false));
			//precondition
			if (elem.getPrecondition() != null) {
				elemZ.setPrecondition((Condition) process(elem.getPrecondition(), null, true));
			}
			// elemZ.getProbability();
			// elemZ.getReplicationFactor();
			//in
			 processList(elem.getIn(), elemZ.getIn(), true);
			 //out
			 processList(elem.getOut(), elemZ.getOut(), true);
			// elemZ.getPlugin();
			// elemZ.getPrecondition();
			// elemZ.getId();
			 //components
			processList(elem.getComponents(), elemZ.getComponents(), true);
			// elemZ.getClass();
		}
		return elemZ;
	}
	
	public int hashCode(Object obj) {
		int hash = 7;
		if (null != obj) {
			ucm.map.PluginBinding elem = (ucm.map.PluginBinding) obj;
			hash = 31 * hash +  elem.hashCode();
		} else {
			hash = 0;
		}
		return hash;
	}
}
