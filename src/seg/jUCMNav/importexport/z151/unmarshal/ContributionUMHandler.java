package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Contribution  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Contribution">
//    <xsd:complexContent>
//      <xsd:extension base="ElementLink">
//        <xsd:sequence>
//          <xsd:element default="Unknown" name="contribution" type="ContributionType"/>
//          <xsd:element name="quantitativeContribution" type="xsd:integer"/>
//          <xsd:element name="correlation" type="xsd:boolean"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Contribution;

public class ContributionUMHandler extends ElementLinkUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Contribution elemZ = (Contribution) o;
		String objId = elemZ.getId();
		grl.Contribution elem = (grl.Contribution) getObject(objId, target, grl.Contribution.class);
		if (isFullConstruction) {
			elem = (grl.Contribution) super.handle(elemZ, elem, true);
			elem.setContribution(grl.ContributionType.get(elemZ.getContribution().ordinal()));
			elem.setQuantitativeContribution(elemZ.getQuantitativeContribution().intValue());
			elem.setCorrelation(elemZ.isCorrelation());
			// elem.setGrlspec();
			// elem.setSrc();
			// elem.setDest();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getContribution();
			// elem.getQuantitativeContribution();
			// elem.getGrlspec();
			// elem.getRefs();
			// elem.getSrc();
			// elem.getDest();
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