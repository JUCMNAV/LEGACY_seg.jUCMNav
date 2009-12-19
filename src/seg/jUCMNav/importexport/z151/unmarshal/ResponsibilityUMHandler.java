package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Responsibility  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Responsibility">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="expression" type="xsd:string"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="demands" type="Demand"/>
//	  <xsd:element maxOccurs="unbounded" name="respRefs" type="xsd:IDREF"/> <!-- RespRef -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Responsibility;

public class ResponsibilityUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Responsibility elemZ = (Responsibility) o;
		String objId = elemZ.getId();
		urncore.Responsibility elem = (urncore.Responsibility) getObject(elemZ.getId(), target, urncore.Responsibility.class);
		if (isFullConstruction) {
			
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav Responsibility context")){ //$NON-NLS-1$
					elem.setContext(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav Responsibility empty")){ //$NON-NLS-1$
					elem.setEmpty(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (urncore.Responsibility) super.handle(elemZ, elem, true);
			elem.setUrndefinition(urn.getUrndef());
			elem.setExpression(elemZ.getExpression());
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getUrndefinition();
			// elem.getExpression();
			processList(elemZ.getDemands(), elem.getDemands(), true);
			for (Object demand : elem.getDemands()) {
				((ucm.performance.Demand) demand).setResponsibility(elem);
			}
			processList(elemZ.getRespRefs(), elem.getRespRefs(), false);
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
