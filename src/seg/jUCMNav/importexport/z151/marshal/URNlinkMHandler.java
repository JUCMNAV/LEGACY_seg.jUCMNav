package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.URNlink;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  URNlink  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="URNlink">
//  <xsd:complexContent>
//    <xsd:extension base="URNmodelElement">
//      <xsd:sequence>
//        <xsd:element name="type" type="xsd:string"/>
//        <xsd:element name="toElem" type="xsd:IDREF"/> <!-- URNmodelElement -->
//        <xsd:element name="fromElem" type="xsd:IDREF"/> <!-- URNmodelElement -->
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  URNmodelElement  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="URNmodelElement">
//<xsd:sequence>
//  <xsd:element name="id" type="xsd:ID"/>
//  <xsd:element name="name" type="xsd:string"/>
//  <xsd:element maxOccurs="unbounded" minOccurs="0" name="metadata" type="Metadata"/>
//  <xsd:element maxOccurs="unbounded" minOccurs="0" name="toLinks" type="xsd:IDREF"/> <!-- URNlink -->
//  <xsd:element maxOccurs="unbounded" minOccurs="0" name="fromLinks" type="xsd:IDREF"/> <!-- URNlink -->
//  <xsd:element minOccurs="0" name="desc" type="Description"/>
//  <xsd:element minOccurs="0" name="concern" type="xsd:IDREF"/> <!-- Concern -->
//</xsd:sequence>
//</xsd:complexType>

/***
 * Done! But TODO Done!: 
 * Id: jUCMNav does not have an Id, but Z151 has ID -- use
 * hashcode to make up an ID 
 * Name: jUCMNav does not have Name, but Z151 has Name -- use ID as Name as well 
 * toLinks: jUCMNav does not have an toLinks, but Z151 has toLinks -- use elem.getToElem().getToLinks() fromLinks: jUCMNav does not
 * have an fromLinks, but Z151 has fromLinks -- use
 * elem.getFromElem().getFromLinks() desc: jUCMNav does not have a description,
 * but Z151 has description -- Since it is optional, we do not set description
 * Concern: jUCMNav does not have a concern, but Z151 has concern -- Since this
 * is optional, we do not set concern.
 * 
 ***/

public class URNlinkMHandler extends MHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        urn.URNlink elem = (urn.URNlink) o;
        String objId = this.getObjectId(elem);
        URNlink elemZ = (URNlink) id2object.get(objId);
        if (null == elemZ) {
            if (null == target)
                elemZ = of.createURNlink();
            else
                elemZ = (URNlink) target;
            id2object.put(objId, elemZ);
            elemZ.setId(objId);
        }
        if (isFullConstruction) {
            elemZ.setName(objId); // not 100% certain
            //processList(elem.getToElem().getToLinks(), elemZ.getToLinks(), false); JUCMnav does not support this. 
            //processList(elem.getFromElem().getFromLinks(), elemZ.getFromLinks(), false); JUCMnav does not support this. 
            processList(elem.getMetadata(), elemZ.getMetadata(), true);
            elemZ.setType(elem.getType());
            elemZ.setToElem(process(elem.getToElem(), null, false));
            elemZ.setFromElem(process(elem.getFromElem(), null, false));
        }

        return elemZ;
    }

    public int hashCode(Object obj) {
        int hash = 7;
        if (null != obj) {
            urn.URNlink elem = (urn.URNlink) obj;
            hash = 31 * hash + elem.getType().hashCode();
            hash = 31 * hash + ((urncore.URNmodelElement) elem.getToElem()).getId().hashCode();
            hash = 31 * hash + ((urncore.URNmodelElement) elem.getFromElem()).getId().hashCode();
        } else {
            hash = 0;
        }
        return hash;
    }
}
