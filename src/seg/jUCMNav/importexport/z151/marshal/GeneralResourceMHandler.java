package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  GeneralResource  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="GeneralResource">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element default="1" name="multiplicity" type="xsd:nonNegativeInteger"/>
//          <xsd:element name="schedPolicy" type="xsd:string"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

//DONE
import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.GeneralResource;

public class GeneralResourceMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.GeneralResource elem = (ucm.performance.GeneralResource) o;
		String objId = elem.getId();
		GeneralResource elemZ = (GeneralResource) getObject(objId, target, "createGeneralResource"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (GeneralResource) super.handle(elem, elemZ, true);
			elemZ.setMultiplicity(new BigInteger(elem.getMultiplicity()));
			elemZ.setSchedPolicy(elem.getSchedPolicy());
		}
		return elemZ;
	}
}
