package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Component  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Component">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element name="kind" type="ComponentKind"/>
//          <xsd:element name="protected" type="xsd:boolean"/>
//          <xsd:element name="context" type="xsd:boolean"/>
//	  <xsd:element minOccurs="0" name="type" type="xsd:IDREF"/> <!-- ComponentType -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="includedComponents" type="xsd:IDREF"/>  <!-- Component -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="includingComponents" type="xsd:IDREF"/>  <!-- Component -->
//	  <xsd:element minOccurs="0" name="host" type="xsd:IDREF"/> <!-- ProcessingResource -->
//	  <xsd:element minOccurs="0" name="resource" type="xsd:IDREF"/> <!-- PassiveResource -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="compRefs" type="xsd:IDREF"/> <!-- ComponentRef -->
//          <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Component;
import seg.jUCMNav.importexport.z151.generated.ComponentType;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.PassiveResource;
import seg.jUCMNav.importexport.z151.generated.ProcessingResource;

public class ComponentUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Component elemZ = (Component) o;
		String objId = elemZ.getId();
		urncore.Component elem = (urncore.Component) getObject(objId, target, urncore.Component.class);
		if (isFullConstruction) {
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav Component slot")){ //$NON-NLS-1$
					elem.setSlot(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (urncore.Component) super.handle(elemZ, elem, true);
			elem.setFillColor(elemZ.getStyle().getFillColor());
			elem.setUrndefinition(urn.getUrndef());
			elem.setLineColor(elemZ.getStyle().getLineColor());
			elem.setFilled(elemZ.getStyle().isFilled());
			elem.setKind(getComponentKind(elemZ.getKind()));
			elem.setProtected(elemZ.isProtected());
			elem.setContext(elemZ.isContext());
			if (elemZ.getIncludedComponents().size()>0)
			elem.setIncludingComponent((urncore.Component) process(elemZ.getIncludingComponents()
					.get(0).getValue(), null, false));
			//Notification message for omitting other includingComponent
			elem.setResource((ucm.performance.PassiveResource) process(
					(PassiveResource) elemZ.getResource(), null, false));
			elem.setHost((ucm.performance.ProcessingResource) process(
					(ProcessingResource) elemZ.getHost(), null, false));
			elem.setType((urncore.ComponentType) process((ComponentType) elemZ
					.getType(), null, false));
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getFillColor();
			// elem.getUrndefinition();
			processList(elemZ.getCompRefs(), elem.getContRefs(), false);
//			elem.getKind();
			processList(elemZ.getIncludedComponents(), elem
					.getIncludedComponent(), false);
			// elem.getIncludingComponent();
			// elem.getResource();
			// elem.getType();
			// elem.getHost();
			// elem.getLineColor();
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
