package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Dependency  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Dependency">
//    <xsd:complexContent>
//      <xsd:extension base="ElementLink"/>
//    </xsd:complexContent>
//  </xsd:complexType>

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;

public class DependencyUMHandler extends ElementLinkUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Dependency elemZ = (Dependency) o;
		String objId = elemZ.getId();
		grl.Dependency elem = (grl.Dependency) id2object.get(objId);
		if (null == elem) {
			if (null == target){
				elem = (grl.Dependency) ModelCreationFactory.getNewObject(urn,
						grl.Dependency.class);
			elem.setId(objId);
			if (Integer.valueOf(globelId)< Integer.valueOf(objId)) globelId = objId;
		}
			else
				elem = (grl.Dependency) target;
			id2object.put(objId, elem);
		}
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
