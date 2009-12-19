package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  LinkRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="LinkRef">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element name="curve" type="xsd:boolean"/>
//          <xsd:element name="link" type="xsd:IDREF"/>  <!-- ElementLink -->
//	  <xsd:element maxOccurs="unbounded" minOccurs="0" name="bendpoints" type="LinkRefBendpoint"/> <!-- {ordered} -->
//          <xsd:element minOccurs="0" name="label" type="Label"/>
//          <xsd:element name="target" type="xsd:IDREF"/>  <!-- GRLNode -->
//          <xsd:element name="source" type="xsd:IDREF"/>  <!-- GRLNode -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ElementLink;
import seg.jUCMNav.importexport.z151.generated.GRLNode;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementType;
import seg.jUCMNav.importexport.z151.generated.LinkRef;
import seg.jUCMNav.model.ModelCreationFactory;

public class LinkRefUMHandler extends EObjectImplUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		LinkRef elemZ = (LinkRef) o;
		String objId = elemZ.getId();
		IntentionalElementType type = ((IntentionalElement) ((IntentionalElementRef) elemZ.getSource()).getDef()).getType();
		if (type.equals(IntentionalElementType.BELIEF)) {
			grl.BeliefLink elem = (grl.BeliefLink) getObject(elemZ.getId(), target, grl.BeliefLink.class);
			if (isFullConstruction) {
				// elem.setDiagram(); //Handled in GRLGraphUMHandler
				
				//grl.GRLNode grlNode = (grl.GRLNode) process((GRLNode) elemZ.getSource(), null, false);
				// if (!grlNode.getSucc().contains(grlNode))
				// grlNode.getSucc().add(grlNode); //handling GRLNodeUMHandler
				elem.setSource((grl.Belief) process(elemZ.getSource(), null, false));
				if (elemZ.getLabel() != null) {
					urncore.ConnectionLabel connectionLabel = (urncore.ConnectionLabel) ModelCreationFactory.getNewObject(urn, urncore.ConnectionLabel.class);
					connectionLabel = (urncore.ConnectionLabel) process(elemZ.getLabel(), connectionLabel, true);
					connectionLabel.setConnection(elem);
					elem.setLabel(connectionLabel);
				}
				// if (!grlNode.getPred().contains(grlNode))
				// grlNode.getPred().add(grlNode);
				elem.setTarget((grl.IntentionalElementRef) process(elemZ.getTarget(), null, false));
				// elem.getDiagram();
				// elem.getLink();
				// elem.getSource();
				// elem.getTarget();
				// elem.getLabel();
				// elem.getClass();
			}
			return elem;
		} else {
			grl.LinkRef elem = (grl.LinkRef) getObject(elemZ.getId(), target, grl.LinkRef.class);
			if (isFullConstruction) {
				// elem.setDiagram(); //Handled in GRLGraphUMHandler
				elem.setLink((grl.ElementLink) process((ElementLink) elemZ.getLink(), null, false));

				grl.GRLNode grlNode = (grl.GRLNode) process((GRLNode) elemZ.getSource(), null, false);
				// if (!grlNode.getSucc().contains(grlNode))
				// grlNode.getSucc().add(grlNode); //handling GRLNodeUMHandler
				elem.setSource((grl.GRLNode) process((GRLNode) elemZ.getSource(), null, false));
				if (elemZ.getLabel() != null) {
					urncore.ConnectionLabel connectionLabel = (urncore.ConnectionLabel) ModelCreationFactory.getNewObject(urn, urncore.ConnectionLabel.class);
					connectionLabel = (urncore.ConnectionLabel) process(elemZ.getLabel(), connectionLabel, true);
					connectionLabel.setConnection(elem);
					elem.setLabel(connectionLabel);
				}
				grlNode = (grl.GRLNode) process((GRLNode) elemZ.getTarget(), null, false);
				// if (!grlNode.getPred().contains(grlNode))
				// grlNode.getPred().add(grlNode);
				elem.setTarget(grlNode);
				// elem.getDiagram();
				// elem.getLink();
				processList(elemZ.getBendpoints(), elem.getBendpoints(), true);
				for (Object item : elem.getBendpoints()) {
					((grl.LinkRefBendpoint) item).setLinkref(elem);
				}
				// elem.getSource();
				// elem.getTarget();
				// elem.getLabel();
				// elem.getClass();
			}
			return elem;
		}
	}
}
