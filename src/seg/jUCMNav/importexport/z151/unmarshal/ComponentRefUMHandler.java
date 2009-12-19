package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ComponentRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="ComponentRef">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//		<xsd:element maxOccurs="unbounded" minOccurs="0" name="parentBindings" type="xsd:IDREF"/> <!-- ComponentBinding -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="pluginBindings" type="xsd:IDREF"/>  <!-- ComponentBinding -->
//          <xsd:element name="compDef" type="xsd:IDREF"/>  <!-- Component -->
//          <xsd:element minOccurs="0" name="label" type="Label"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="children" type="xsd:IDREF"/>  <!-- ComponentRef -->
//          <xsd:element minOccurs="0" name="parent" type="xsd:IDREF"/>  <!-- ComponentRef -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="nodes" type="xsd:IDREF"/>  <!-- PathNode -->
//          <xsd:element minOccurs="0" name="pos" type="Position"/>
//          <xsd:element minOccurs="0" name="size" type="Size"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Component;
import seg.jUCMNav.importexport.z151.generated.ComponentRef;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.model.ModelCreationFactory;

public class ComponentRefUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ComponentRef elemZ = (ComponentRef) o;
		String objId = elemZ.getId();
		ucm.map.ComponentRef elem = (ucm.map.ComponentRef) getObject(objId, target, ucm.map.ComponentRef.class);
		if (isFullConstruction) {

			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav ComponentRef replicationFactor")){ //$NON-NLS-1$
					elem.setReplicationFactor(Integer.parseInt(item.getValue()));
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav ComponentRef role")){ //$NON-NLS-1$
					elem.setRole(item.getValue());
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav ComponentRef anchored")){ //$NON-NLS-1$
					elem.setAnchored(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav ComponentRef fixed")){ //$NON-NLS-1$
					elem.setFixed(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (ucm.map.ComponentRef) super.handle(elemZ, elem, true);
			elem.setX(elemZ.getPos().getX().intValue());
			elem.setY(elemZ.getPos().getY().intValue());
			elem.setHeight(elemZ.getSize().getHeight().intValue());
			// elem.setDiagram(); handled by UCMmapUMHandler
			elem.setContDef((urncore.Component) process((Component) elemZ.getCompDef(), null, false));
			elem.setParent((ucm.map.ComponentRef) process((ComponentRef) elemZ.getParent(), null, false));
			elem.setWidth(elemZ.getSize().getWidth().intValue());
			urncore.ComponentLabel compLabel = (urncore.ComponentLabel) ModelCreationFactory.getNewObject(urn, urncore.ComponentLabel.class);
			compLabel.setDeltaX(elemZ.getLabel().getDeltaX().intValue());
			compLabel.setDeltaY(elemZ.getLabel().getDeltaY().intValue());
			compLabel.setContRef(elem);
			elem.setLabel(compLabel);
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getDiagram();
			// elem.getContDef();
			processList(elemZ.getNodes(), elem.getNodes(), false);
			// elem.getReplicationFactor();
			// elem.getRole();
			processList(elemZ.getParentBindings(), elem.getParentBindings(), false);
			processList(elemZ.getPluginBindings(), elem.getPluginBindings(), false);
			// elem.getParent();
			// elem.getX();
			// elem.getY();
			// elem.getHeight();
			// elem.getWidth();
			// elem.getLabel();
			processList(elemZ.getChildren(), elem.getChildren(), false);
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
