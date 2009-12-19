package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  NodeConnection  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="NodeConnection">
//    <xsd:sequence>
//      <xsd:element name="id" type="xsd:ID"/> <!-- ADDED because NodeConnection is not a URNmodelElement (no ID) -->	    
//      <xsd:element default="100" name="probability" type="xsd:nonNegativeInteger"/>
//      <xsd:element name="threshold" type="xsd:string"/>
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="inBindings" type="xsd:IDREF"/> <!-- InBinding -->
//      <xsd:element maxOccurs="unbounded" minOccurs="0" name="outBindings" type="xsd:IDREF"/>  <!-- OutBinding -->
//      <xsd:element minOccurs="0" name="condition" type="Condition"/>
//      <xsd:element minOccurs="0" name="timer" type="xsd:IDREF"/> <!-- Timer -->
//      <xsd:element minOccurs="0" name="label" type="Label"/>
//      <xsd:element name="target" type="xsd:IDREF"/> <!-- PathNode -->
//      <xsd:element name="source" type="xsd:IDREF"/> <!-- PathNode -->
//    </xsd:sequence>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.NodeConnection;
import seg.jUCMNav.model.ModelCreationFactory;

public class NodeConnectionUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		NodeConnection elemZ = (NodeConnection) o;
		String objId = elemZ.getId();
		ucm.map.NodeConnection elem = (ucm.map.NodeConnection) getObject(elemZ.getId(), target, ucm.map.NodeConnection.class);
		if (isFullConstruction) {
			// TODO elemZ.getId()
			// elem.setDiagram(); handled by UCMmodelElementUMHandler
			if (elemZ.getCondition() != null) {
				urncore.Condition condition = (urncore.Condition) process(elemZ.getCondition(), null, true);
				elem.setCondition(condition);
				condition.setNodeConnection(elem);
			}
			elem.setProbability(elemZ.getProbability().doubleValue() / 100);
			elem.setSource((ucm.map.PathNode) process(elemZ.getSource(), null, false));
			if (elemZ.getLabel() != null) {
				urncore.ConnectionLabel connectionLabel = (urncore.ConnectionLabel) ModelCreationFactory.getNewObject(urn, urncore.ConnectionLabel.class);
				connectionLabel = (urncore.ConnectionLabel) process(elemZ.getLabel(), connectionLabel, true);
				connectionLabel.setConnection(elem);
				elem.setLabel(connectionLabel);
			}
			elem.setTarget((ucm.map.PathNode) process(elemZ.getTarget(), null, false));

			// elem.getDiagram();
			// elem.getCondition();
			processList(elemZ.getInBindings(), elem.getInBindings(), false);
			processList(elemZ.getOutBindings(), elem.getOutBindings(), false);
			// elem.getProbability();
			// elem.getSource();
			// elem.getTarget();
			// elem.getLabel();
			// elem.getClass();
            if (elemZ.getThreshold() != null && !elemZ.getThreshold().equals("") ) { //$NON-NLS-1$
                elem.setThreshold(elemZ.getThreshold());
            }
			// TODO elemZ.getTimer()
		}
		return elem;
	}
}
