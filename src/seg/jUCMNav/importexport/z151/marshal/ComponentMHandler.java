package seg.jUCMNav.importexport.z151.marshal;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  Component  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="Component">
//  <xsd:complexContent>
//    <xsd:extension base="UCMmodelElement">
//      <xsd:sequence>
//        <xsd:element name="kind" type="ComponentKind"/>
//        <xsd:element name="protected" type="xsd:boolean"/>
//        <xsd:element name="context" type="xsd:boolean"/>
//	  <xsd:element minOccurs="0" name="type" type="xsd:IDREF"/> <!-- ComponentType -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="includedComponents" type="xsd:IDREF"/>  <!-- Component -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="includingComponents" type="xsd:IDREF"/>  <!-- Component -->
//	  <xsd:element minOccurs="0" name="host" type="xsd:IDREF"/> <!-- ProcessingResource -->
//	  <xsd:element minOccurs="0" name="resource" type="xsd:IDREF"/> <!-- PassiveResource -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="compRefs" type="xsd:IDREF"/> <!-- ComponentRef -->
//        <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

//Z151 includingComponent is List<JAXBElement<Object>>, but jUCMNav is an object
//No other difference. 
import seg.jUCMNav.importexport.z151.generated.Component;
import seg.jUCMNav.importexport.z151.generated.ComponentType;
import seg.jUCMNav.importexport.z151.generated.ConcreteStyle;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.PassiveResource;
import seg.jUCMNav.importexport.z151.generated.ProcessingResource;

public class ComponentMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		urncore.Component elem = (urncore.Component) o;
		String objId = elem.getId();
		Component elemZ = (Component) getObject(objId, target, "createComponent"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (Component) super.handle(elem, elemZ, true);
			//kind
			elemZ.setKind(getComponentKind(elem.getKind()));
			//protected
			elemZ.setProtected(elem.isProtected());
			//context
			elemZ.setContext(elem.isContext());
			//host
			elemZ.setHost((ProcessingResource) process(elem.getHost(), null, false));
			//resource
			elemZ.setResource((PassiveResource) process(elem.getResource(), null, false));
			//style
			ConcreteStyle csZ = of.createConcreteStyle();
			csZ.setFillColor(elem.getFillColor());
			csZ.setFilled(elem.isFilled());
			csZ.setLineColor(elem.getLineColor());
			elemZ.setStyle(csZ);
			//type
			elemZ.setType((ComponentType) process(elem.getType(), null, false));

			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();
			//
			// elemZ.getKind();
			//includedComponents
			processList(elem.getIncludedComponent(), elemZ.getIncludedComponents(), "createComponentIncludedComponents", false); //$NON-NLS-1$
			//includingComponents
			elemZ.getIncludingComponents().add(of.createComponentIncludingComponents(process(elem.getIncludingComponent(), null, false)));
			//compRefs
			processList(elem.getContRefs(), elemZ.getCompRefs(), "createComponentCompRefs", false); // Not 100 certain //$NON-NLS-1$
			
			// elemZ.getCompRefs()
			// elemZ.getCompRefs();
			// elemZ.getResource();
			// elemZ.getType();
			// elemZ.getHost();
			// elemZ.getStyle();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
			
			boolean slot = elem.isSlot();
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav Component slot"); //$NON-NLS-1$
			mdZ.setValue(Boolean.toString(slot));
			elemZ.getMetadata().add(mdZ);
		}
		return elemZ;
	}
}
