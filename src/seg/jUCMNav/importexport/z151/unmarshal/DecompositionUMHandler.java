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
		grl.Decomposition elem = (grl.Decomposition) id2object.get(objId);
		if (null == elem) {
		if (null == target){
				elem = (grl.Decomposition) ModelCreationFactory.getNewObject(
						urn, grl.Decomposition.class);
					elem.setId(objId);
				if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
			}
			else
				elem = (grl.Decomposition) target;
			id2object.put(objId, elem);
		}
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
