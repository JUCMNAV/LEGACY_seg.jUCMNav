package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;
import seg.jUCMNav.importexport.z151.generated.*;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  LinkRef  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="LinkRef">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element name="curve" type="xsd:boolean"/>
//        <xsd:element name="link" type="xsd:IDREF"/>  <!-- ElementLink -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="bendpoints" type="LinkRefBendpoint"/> <!-- {ordered} -->
//        <xsd:element minOccurs="0" name="label" type="Label"/>
//        <xsd:element name="target" type="xsd:IDREF"/>  <!-- GRLNode -->
//        <xsd:element name="source" type="xsd:IDREF"/>  <!-- GRLNode -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

//* The following features are supported:
//	 * <ul>
//	 *   <li>{@link grl.LinkRef#getLink <em>Link</em>}</li>
//	 *   <li>{@link grl.LinkRef#getBendpoints <em>Bendpoints</em>}</li>
//	 * </ul>

//* The following features are supported:
//	 * <ul>
//	 *   <li>{@link urncore.IURNConnection#getSource <em>Source</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getTarget <em>Target</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getDiagram <em>Diagram</em>}</li>
//	 *   <li>{@link urncore.IURNConnection#getLabel <em>Label</em>}</li>
//	 * </ul>
//	 * </p>
/*** Done! ***/

/***
 * Done! But TODO Done!: Id: jUCMNav does not have an Id, but Z151 has ID -- use
 * hashcode to make up an ID Name: jUCMNav does not have Name, but Z151 has Name
 * -- use ID as Name as well metadata: jUCMNav does not have a metadata, but
 * Z151 has metadata -- Since it is optional, we do not set metadata toLinks:
 * jUCMNav does not have an toLinks, but Z151 has toLinks -- Since it is
 * optional, we do not set toLinks fromLinks: jUCMNav does not have an
 * fromLinks, but Z151 has fromLinks -- Since it is optional, we do not set
 * toLinks -- undecided //use elem.getTarget().getSucc() desc: jUCMNav does not
 * have a description, but Z151 has description -- Since it is optional, we do
 * not set description Concern: jUCMNav does not have a concern, but Z151 has
 * concern -- Since this is optional, we do not set concern.
 * 
 ***/

public class LinkRefMHandler extends MHandler {
	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.LinkRef elem = (grl.LinkRef) obj;
		String objId = this.getObjectId(elem);
		LinkRef elemZ = (LinkRef) getObject(objId, target, "createLinkRef");
		if (isFullConstruction) {
			elemZ.setId(objId);
			elemZ.setName(objId);
			elemZ.setCurve(false);
			elemZ.setLink(process(elem.getLink(), null, false));
			processList(elem.getBendpoints(), elemZ.getBendpoints(), true);

			if (elem.getLabel() != null) {
				Label labelZ = new Label();
				labelZ.setDeltaX(new BigInteger(Integer.toString(elem.getLabel().getDeltaX())));
				labelZ.setDeltaY(new BigInteger(Integer.toString(elem.getLabel().getDeltaY())));
				elemZ.setLabel(labelZ);
			}
			elemZ.setTarget((GRLNode) process(elem.getTarget(), null, false));
			elemZ.setSource((GRLNode) process(elem.getSource(), null, false));
			// TODO? elem.getDiagram()

		}
		return elemZ;
	}
	public int hashCode(Object obj){
		int hash = 7;
		if (null!=obj){
			urncore.IURNConnection elem = (urncore.IURNConnection) obj;
			hash = 31 * hash + ((urncore.IURNNode) elem.getSource()).getX();
			hash = 31 * hash + ((urncore.IURNNode) elem.getSource()).getY();
			hash = 31 * hash + ((urncore.IURNNode) elem.getTarget()).getX();
			hash = 31 * hash + ((urncore.IURNNode) elem.getTarget()).getY();
		}else{
			hash=0;
		}
		return hash;
	}
}
