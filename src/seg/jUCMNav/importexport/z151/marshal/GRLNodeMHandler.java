package seg.jUCMNav.importexport.z151.marshal;

import java.math.BigInteger;

import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.GRLNode;
import seg.jUCMNav.importexport.z151.generated.Position;
import seg.jUCMNav.importexport.z151.generated.Size;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  GRLNode  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="GRLNode">
//  <xsd:complexContent>
//    <xsd:extension base="GRLmodelElement">
//      <xsd:sequence>
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="pred" type="xsd:IDREF"/> <!-- LinkRef -->
//        <xsd:element maxOccurs="unbounded" minOccurs="0" name="succ" type="xsd:IDREF"/> <!-- LinkRef -->
//        <xsd:element minOccurs="0" name="contRef" type="xsd:IDREF"/> <!-- ActorRef -->
//        <xsd:element name="pos" type="Position"/>
//        <xsd:element name="size" type="Size"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

public class GRLNodeMHandler extends GRLmodelElementMHandler {

	@Override
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.GRLNode elem = (grl.GRLNode) obj;
		String objId = elem.getId();
		GRLNode elemZ = (GRLNode) getObject(objId, target, "createGRLNode"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
			processList(elem.getPred(), elemZ.getPred(), "createGRLNodePred", false); //$NON-NLS-1$
			processList(elem.getSucc(), elemZ.getSucc(), "createGRLNodeSucc", false); //$NON-NLS-1$
			elemZ.setContRef((ActorRef) process((grl.ActorRef) elem.getContRef(), null, false));
			Position posZ = new Position();
			posZ.setX(new BigInteger(Integer.toString(elem.getX())));
			posZ.setY(new BigInteger(Integer.toString(elem.getY())));
			elemZ.setPos(posZ);
			Size sizeZ = new Size();
			sizeZ.setHeight(new BigInteger("0")); // Size auto-computed from content in in jUCMNav //$NON-NLS-1$
			sizeZ.setWidth(new BigInteger("0"));  // Size auto-computed from content in in jUCMNav //$NON-NLS-1$
			elemZ.setSize(sizeZ);
		}
		return elemZ;
	}
}
