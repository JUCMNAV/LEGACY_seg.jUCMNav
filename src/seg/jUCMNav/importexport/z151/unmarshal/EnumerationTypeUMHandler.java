package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EnumerationType  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EnumerationType">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="values" type="xsd:string"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="instances" type="xsd:IDREF"/> <!-- Variable -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class EnumerationTypeUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		EnumerationType elemZ = (EnumerationType) o;
		String objId = elemZ.getId();
		ucm.scenario.EnumerationType elem = (ucm.scenario.EnumerationType) id2object
				.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (ucm.scenario.EnumerationType) ModelCreationFactory
						.getNewObject(urn, ucm.scenario.EnumerationType.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (ucm.scenario.EnumerationType) target;
			id2object.put(objId, elem);
		}
		if (isFullConstruction) {
			elem = (ucm.scenario.EnumerationType) super.handle(elemZ, elem, true);
			//elem.setUcmspec(); handled by UCMspecUMHandler
			elem.setValues(elemZ.getValues());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUcmspec();
			processList(elemZ.getInstances(), elem.getInstances(), false);
			// elem.getValues();
			// elem.getFromLinks();
			// elem.getToLinks();
			// elem.getMetadata();
			// elem.getName();
			// elem.getId();
			// elem.getDescription();
			// elem.getClass();
		}
		return elem;
		
	}
}