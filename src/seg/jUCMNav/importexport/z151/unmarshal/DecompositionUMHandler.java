package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Decomposition  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Decomposition">
//    <xsd:complexContent>
//      <xsd:extension base="ElementLink"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class DecompositionUMHandler extends ElementLinkUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Decomposition elemZ = (Decomposition) o;
		String objId = elemZ.getId();
		grl.Decomposition elem = (grl.Decomposition) getObject(objId, target, grl.Decomposition.class);
		if (isFullConstruction) {
			elem = (grl.Decomposition) super.handle(elemZ, elem, true);
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
