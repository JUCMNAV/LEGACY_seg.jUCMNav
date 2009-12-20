package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Concern  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Concern">
//    <xsd:complexContent>
//      <xsd:extension base="URNmodelElement">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="condition" type="xsd:IDREF"/>  <!-- Condition -->
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="elements" type="xsd:IDREF"/>  <!-- URNmodelElement -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>


import seg.jUCMNav.importexport.z151.generated.Concern;

public class ConcernUMHandler extends URNmodelElementUMHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        Concern elemZ = (Concern) o;
        String objId = elemZ.getId();
        urncore.Concern elem = (urncore.Concern) getObject(objId, target, urncore.Concern.class);
        if (isFullConstruction) {
            elem = (urncore.Concern) super.handle(elemZ, elem, true);
            elem.setUrndefinition(urn.getUrndef());
            urncore.Condition condition = (urncore.Condition) process(elemZ.getCondition(), null, false);
            elem.setCondition(condition);
            if (condition != null)
                condition.setConcern(elem);
            // elem.setId();
            // elem.setName();
            // elem.setDescription();

            // elem.getUrndefinition();
            // elem.getSpecDiagrams() : To update when concerns target elements other than diagrams.
                processList(elemZ.getElements(), elem.getSpecDiagrams(), false);
            // elem.getCondition();
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
