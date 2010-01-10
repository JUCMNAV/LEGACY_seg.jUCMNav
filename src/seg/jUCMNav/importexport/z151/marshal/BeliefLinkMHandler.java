package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.ElementLink;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;
import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.importexport.z151.generated.LinkRef;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ElementLink  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ElementLink">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="refs" type="xsd:IDREF"/> <!-- LinkRef -->
//        <xsd:element name="dest" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//        <xsd:element name="src" type="xsd:IDREF"/> <!-- GRLLinkableElement -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

/*** Done! ***/

public class BeliefLinkMHandler extends MHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.BeliefLink elem = (grl.BeliefLink) obj;
		String objId = this.getObjectId(elem);
		ElementLink elemZ = (ElementLink) id2object.get(objId);
		LinkRef linkRef = (LinkRef) id2object.get("Z151_jUCMNav_grl.impl.LinkRefImpl"+elem.hashCode()); //$NON-NLS-1$
		if (null == elemZ) {
			if (null == target){
				elemZ = of.createElementLink();
			}else
				elemZ = (ElementLink) target;
			id2object.put(objId, elemZ);
		}
		if (linkRef == null) {
			linkRef = of.createLinkRef();
			id2object.put("Z151_jUCMNav_grl.impl.LinkRefImpl"+elem.hashCode(), linkRef); //$NON-NLS-1$
		}
		if (isFullConstruction) {
			elemZ.setId(objId);
			elemZ.setName(""); //$NON-NLS-1$
						
			//elem.getDiagram();
			//elem.getLabel();
			IntentionalElementRef srcNode = (IntentionalElementRef) process(elem.getSource(), null, false);
			IntentionalElementRef dstNode = (IntentionalElementRef) process(elem.getTarget(), null, false);
			
			elemZ.setSrc(srcNode.getDef());
			elemZ.setDest(dstNode.getDef());
			
			linkRef.setId("Z151_jUCMNav_grl.impl.LinkRefImpl"+elem.hashCode()); //$NON-NLS-1$
			linkRef.setName(""); //$NON-NLS-1$
			linkRef.setCurve(false);
			linkRef.setLink(elemZ);
			//processList(elem.getBendpoints(), linkRef.getBendpoints(), true);

			if (elem.getLabel() != null) {
				Label labelZ = new Label();
				labelZ.setDeltaX(new BigInteger(Integer.toString(elem.getLabel().getDeltaX())));
				labelZ.setDeltaY(new BigInteger(Integer.toString(elem.getLabel().getDeltaY())));
				linkRef.setLabel(labelZ);
			}
			linkRef.setSource(srcNode);
			linkRef.setTarget(dstNode);
			// TODO? elem.getDiagram()
			elemZ.getRefs().add(of.createElementLinkRefs(linkRef));
			urnZ.getGrlspec().getLinks().add(elemZ);
		}
		return linkRef;
	}
}
