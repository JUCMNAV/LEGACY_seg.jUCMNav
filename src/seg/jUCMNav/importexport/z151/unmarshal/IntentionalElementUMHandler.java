package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  IntentionalElement  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="IntentionalElement">
//    <xsd:complexContent>
//      <xsd:extension base="GRLLinkableElement">
//        <xsd:sequence>
//          <xsd:element name="type" type="IntentionalElementType"/>
//          <xsd:element default="AND" name="decompositionType" type="DecompositionType"/>
//          <xsd:element default="None" name="importance" type="ImportanceType"/>
//          <xsd:element name="importanceQuantitative" type="xsd:integer"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="refs" type="xsd:IDREF"/>  <!-- IntentionalElementRef -->
//          <xsd:element minOccurs="0" name="style" type="ConcreteStyle"/>
//	  <xsd:element minOccurs="0" name="actor" type="xsd:IDREF"/> <!-- Actor -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.List;

import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementType;
import seg.jUCMNav.importexport.z151.generated.Metadata;

public class IntentionalElementUMHandler extends GRLLinkableElementUMHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        IntentionalElement elemZ = (IntentionalElement) o;
        grl.IntentionalElement elem = null;
        
        if (!elemZ.getType().equals(IntentionalElementType.BELIEF)) {
            if (elemZ.getType().equals(IntentionalElementType.TASK)) {
                List<Metadata> metaDataList = elemZ.getMetadata();
                for(Metadata item: metaDataList){
                    if (item.getName().equals("jUCMNav Indicator")){ //$NON-NLS-1$
                    	elem = (grl.kpimodel.Indicator) getObject(elemZ.getId(), target, grl.kpimodel.Indicator.class);
                        metaDataList.remove(item);
                        break;
                    }
                }               
            }
            String objId = elemZ.getId();
            if (elem == null) {
                elem = (grl.IntentionalElement) getObject(elemZ.getId(), target, grl.IntentionalElement.class);
            }
            if (isFullConstruction) {
                if (elem instanceof grl.kpimodel.Indicator) {
                    elem = (grl.kpimodel.Indicator) super.handle(elemZ, elem, true);
                    elem.setType(grl.IntentionalElementType.INDICATOR_LITERAL);
                }else {
                    elem = (grl.IntentionalElement) super.handle(elemZ, elem, true);
                    elem.setType(getIntentionalElementType(elemZ.getType()));
                }
                elem.setLineColor(elemZ.getStyle().getLineColor());
                elem.setFillColor(elemZ.getStyle().getFillColor());
                elem.setFilled(elemZ.getStyle().isFilled());
                // elem.setGrlspec(); //Handled in GRLspecUMHandler
                elem.setDecompositionType(getDecompositionType(elemZ.getDecompositionType()));
                elem.setImportance(grl.ImportanceType.get(elemZ.getImportance().ordinal()));
                elem.setImportanceQuantitative(elemZ.getImportanceQuantitative().intValue());
                // elem.setId();
                // elem.setName();
                // elem.setDescription();

                // elem.getFillColor();
                // elem.getGrlspec();
                processList(elemZ.getRefs(), elem.getRefs(), false);
                // elem.getDecompositionType();
                // elem.getImportance();
                // elem.getImportanceQuantitative();
                // elem.getType();
                // elem.getLineColor();
                // elem.getLinksDest();
                // elem.getLinksSrc();
                // elem.getFromLinks();
                // elem.getToLinks();
                // elem.getMetadata();
                // elem.getName();
                // elem.getId();
                // elem.getDescription();
                // elem.getClass();
                // TODO elemZ.getActor()
            }
            return elem;
        } else {
            return null;
        }

    }
}
