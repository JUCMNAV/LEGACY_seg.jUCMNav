package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Dependency  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Dependency">
//    <xsd:complexContent>
//      <xsd:extension base="ElementLink"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.Dependency;

public class DependencyUMHandler extends ElementLinkUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Dependency elemZ = (Dependency) o;
		String objId = elemZ.getId();
		grl.Dependency elem = (grl.Dependency) getObject(objId, target, grl.Dependency.class);
		if (isFullConstruction) {
			elem = (grl.Dependency) super.handle(elemZ, elem, true);
			// elem.setGrlspec();
			// elem.setSrc();
			// elem.setDest();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

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
