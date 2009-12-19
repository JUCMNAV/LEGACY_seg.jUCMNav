package seg.jUCMNav.importexport.z151.marshal;

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

//* The following features are supported:
//	 * <ul>
//	 *   <li>{@link urncore.IURNConnection#getSource <em>Source</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getTarget <em>Target</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getDiagram <em>Diagram</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getLabel <em>Label</em>}</li>
//	 * </ul>
// * The following features are supported:
//	 * <ul>
//	 *   <li>{@link ucm.map.NodeConnection#getProbability <em>Probability</em>}</li>
//	 *   <li>{@link ucm.map.NodeConnection#getInBindings <em>In Bindings</em>}</li>
//	 *   <li>{@link ucm.map.NodeConnection#getOutBindings <em>Out Bindings</em>}</li>
//	 *   <li>{@link ucm.map.NodeConnection#getCondition <em>Condition</em>}</li>
//	 * </ul>	 

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.importexport.z151.generated.NodeConnection;

public class NodeConnectionMHandler extends MHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.NodeConnection elem = (ucm.map.NodeConnection) o;
		String objId = this.getObjectId(elem); //jUCMNav does not assign value to ID
		NodeConnection elemZ = (NodeConnection) getObject(objId, target, "createNodeConnection"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ.setId(objId);
			//condition
			elemZ.setCondition((Condition) process(elem.getCondition(), null, true));
			
			//probability
			elemZ.setProbability(new BigInteger(Integer.toString((int) (elem.getProbability() * 100))));

			// elemZ.setTimer(); handled by TimerMHandler
			if(elem.getThreshold()!=null) elemZ.setThreshold(elem.getThreshold());
			else elemZ.setThreshold(""); //$NON-NLS-1$
			//source
			elemZ.setSource(process(elem.getSource(), null, false));
			//label
			if (elem.getLabel() != null) {
				Label labelZ = of.createLabel();
				labelZ.setDeltaX(new BigInteger(Integer.toString(elem.getLabel().getDeltaX())));
				labelZ.setDeltaY(new BigInteger(Integer.toString(elem.getLabel().getDeltaY())));
				elemZ.setLabel(labelZ);
			}
			//target
			elemZ.setTarget(process(elem.getTarget(), null, false));

			// elemZ.getCondition();
			//outBindings
			processList(elem.getOutBindings(), elemZ.getOutBindings(), "createNodeConnectionOutBindings", false); //$NON-NLS-1$
			// elemZ.getProbability();
			//TODO elemZ.getThreshold();
			//inBindings
			processList(elem.getInBindings(), elemZ.getInBindings(), "createNodeConnectionInBindings", false); //$NON-NLS-1$
			// elemZ.getId();
			// elemZ.getSource();
			// elemZ.getTarget();
			// elemZ.getLabel();
			// elemZ.getTimer();
			// elemZ.getClass();
			// elem.getDiagram()
		}
		return elemZ;
	}

	public int hashCode(Object obj) {
		int hash = 7;
		if (null != obj) {
			urncore.IURNConnection elem = (urncore.IURNConnection) obj;
			hash = 31 * hash + ((urncore.IURNNode) elem.getSource()).getX();
			hash = 31 * hash + ((urncore.IURNNode) elem.getSource()).getY();
			hash = 31 * hash + ((urncore.IURNNode) elem.getTarget()).getX();
			hash = 31 * hash + ((urncore.IURNNode) elem.getTarget()).getY();
		} else {
			hash = 0;
		}
		return hash;
	}
}
