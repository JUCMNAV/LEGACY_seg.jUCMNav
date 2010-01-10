package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.Contribution;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Contribution  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Contribution">
//  <xsd:complexContent>
//    <xsd:extension base="ElementLink">
//      <xsd:sequence>
//        <xsd:element default="Unknown" name="contribution" type="ContributionType"/>
//        <xsd:element name="quantitativeContribution" type="xsd:integer"/>
//        <xsd:element name="correlation" type="xsd:boolean"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>


public class ContributionMHandler extends ElementLinkMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.Contribution elem = (grl.Contribution) obj;
		String objId = elem.getId();
		Contribution elemZ = (Contribution) getObject(objId, target, "createContribution"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (Contribution) super.handle(elem, elemZ, true);
			elemZ.setContribution(getContributionType(elem.getContribution()));
			elemZ.setQuantitativeContribution(new BigInteger(Integer.toString(elem.getQuantitativeContribution())));
			elemZ.setCorrelation(elem.isCorrelation());
		}
		return elemZ;
	}
}
