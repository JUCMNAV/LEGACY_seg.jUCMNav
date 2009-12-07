package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ActiveResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ActiveResource">
//    <xsd:complexContent>
//      <xsd:extension base="GeneralResource">
//        <xsd:sequence>
//          <xsd:element name="opTime" type="xsd:string"/>
//          <xsd:element default="ms" name="unit" type="TimeUnit"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.*;

public class ActiveResourceMHandler extends GeneralResourceMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.ActiveResource elem = (ucm.performance.ActiveResource) o;
		String objId = elem.getId();
		ActiveResource elemZ = (ActiveResource) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createActiveResource();
				elemZ.setId(objId);
			}else
				elemZ = (ActiveResource) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction) {
			elemZ = (ActiveResource) super.handle(elem, elemZ, isFullConstruction);
			elemZ.setOpTime(elem.getOpTime());
			elemZ.setUnit(TimeUnit.MS); // set to default

			// elemZ.setMultiplicity(elem.getMultiplicity());
			// elemZ.setSchedPolicy(elem.getSchedPolicy());
			// elemZ.setId(elem.getId());
			// elemZ.setDesc(elem.getDescription());
			// elemZ.setConcern();
			// elemZ.setName(elem.getName());
			//
			//
			//
			// processList(elem.getMetadata(), elemZ.getMetadata());
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
