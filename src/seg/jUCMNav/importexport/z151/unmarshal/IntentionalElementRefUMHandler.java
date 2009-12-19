package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  IntentionalElementRef  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="IntentionalElementRef">
//    <xsd:complexContent>
//      <xsd:extension base="GRLNode">
//        <xsd:sequence>
//          <xsd:element name="def" type="xsd:IDREF"/> <!-- IntentionalElement -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.List;

import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementType;
import seg.jUCMNav.importexport.z151.generated.Metadata;

public class IntentionalElementRefUMHandler extends GRLNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		IntentionalElementRef elemZ = (IntentionalElementRef) o;
		if (((IntentionalElement) elemZ.getDef()).getType().equals(IntentionalElementType.BELIEF)) {
			String objId = elemZ.getId();
			grl.Belief elem = (grl.Belief) getObject(objId, target, grl.Belief.class);
			if (isFullConstruction) {
				List<Metadata> metaDataList = elemZ.getMetadata();
				for(Metadata item: metaDataList){
					if (item.getName().equals("jUCMNav Belief author")){ //$NON-NLS-1$
						elem.setAuthor(item.getValue());
						metaDataList.remove(item);
						break;
					}
				}
				elem = (grl.Belief) super.handle(elemZ, elem, true);
				// elem.setContRef();
				// elem.setDescription();
				// elem.setDescription();
				// elem.setId();
				// elem.setLabel();
				// elem.setName();
				// elem.setX();
				// elem.setY();
				//				
				// elemZ.getConcern();
				// elemZ.getContRef();
				// elemZ.getDef();
				// elemZ.getDesc();
				// elemZ.getFromLinks();
				// elemZ.getToLinks();
				// elemZ.getId();
				// elemZ.getMetadata();
				// elemZ.getName();
				// elemZ.getPos();
				// elemZ.getPred();
				// elemZ.getSize();
				// elemZ.getSucc();
			}
			return elem;
		} else {
			String objId = elemZ.getId();
			grl.IntentionalElementRef elem = (grl.IntentionalElementRef) getObject(objId, target, grl.IntentionalElementRef.class);
			if (isFullConstruction) {
				elem = (grl.IntentionalElementRef) super.handle(elemZ, elem, true);
				elem.setCriticality(grl.Criticality.NONE_LITERAL); // set to
																	// default
				elem.setDef((grl.IntentionalElement) process((IntentionalElement) elemZ.getDef(), null, false));
				elem.setPriority(grl.Priority.NONE_LITERAL); // set to default

				// elem.setX();
				// elem.setY();
				// elem.setDiagram();
				// elem.setContRef();
				// elem.setLabel();
				// elem.setId();
				// elem.setName();
				// elem.setDescription();

				// elem.getCriticality();
				// elem.getDef();
				// elem.getPriority();
				// elem.getDiagram();
				// elem.getContRef();
				// elem.getSucc();
				// elem.getPred();
				// elem.getX();
				// elem.getY();
				// elem.getLabel();
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
}
