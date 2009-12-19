package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.ConcreteURNspec;
import seg.jUCMNav.importexport.z151.generated.GRLGraph;
import seg.jUCMNav.importexport.z151.generated.GRLspec;
import seg.jUCMNav.importexport.z151.generated.UCMmap;
import seg.jUCMNav.importexport.z151.generated.UCMspec;
import seg.jUCMNav.importexport.z151.generated.URNspec;

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  URNspec  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="URNspec">
//  <xsd:sequence>
//    <xsd:element name="name" type="xsd:string"/>
//    <xsd:element minOccurs="0" name="ucmspec" type="UCMspec"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="metadata" type="Metadata"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="urnLinks" type="URNlink"/>
//    <xsd:element minOccurs="0" name="grlspec" type="GRLspec"/>
//    <xsd:element minOccurs="0" name="info" type="ConcreteURNspec"/>
//    <xsd:element maxOccurs="unbounded" minOccurs="0" name="concerns" type="Concern"/>
//  </xsd:sequence>
//</xsd:complexType>

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ConcreteURNspec  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ConcreteURNspec">
//  <xsd:sequence>
//    <xsd:element name="description" type="xsd:string"/>
//    <xsd:element name="author" type="xsd:string"/>
//    <xsd:element name="created" type="xsd:string"/>
//    <xsd:element name="modified" type="xsd:string"/>
//    <xsd:element name="specVersion" type="xsd:string"/>
//    <xsd:element name="urnVersion" type="xsd:string"/>
//  </xsd:sequence>
//</xsd:complexType>

/***
 * Done! but TODO Done!: concerns: jUCMNav does not have concerns, but Z151 has
 * concerns -- use elem.getUrndef().getConcerns()
 ***/
public class URNspecMHandler extends MHandler {
	public URNspecMHandler() {
		super();
	}

	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		urn.URNspec elem = (urn.URNspec) obj;
		URNspec elemZ = null;
		if (null == target)
			elemZ = of.createURNspec();
		else
			elemZ = (URNspec) target;
		urnZ = elemZ;
		//name
		elemZ.setName(elem.getName());
		//ucmspec
		if (elem.getUcmspec() != null)
			elemZ.setUcmspec((UCMspec) process(elem.getUcmspec(), null, true));
		//metadata
		processList(elem.getMetadata(), elemZ.getMetadata(), true);
		//urnLinks
		processList(elem.getUrnLinks(), elemZ.getUrnLinks(), true);
		//grlspec
		if (elem.getGrlspec() != null) {
			elemZ.setGrlspec((GRLspec) process(elem.getGrlspec(), null, true));
		}
		//info
		ConcreteURNspec info = of.createConcreteURNspec();
		info.setDescription(elem.getDescription());
		info.setAuthor(elem.getAuthor());
		info.setCreated(elem.getCreated());
		info.setModified(elem.getModified());
		info.setSpecVersion(elem.getSpecVersion());
		info.setUrnVersion(elem.getUrnVersion());
		elemZ.setInfo(info);
		
		urncore.URNdefinition urnDef = elem.getUrndef();
		//concerns
		processList(urnDef.getConcerns(), elemZ.getConcerns(), true);
		//urnDef.getComponents() handled by UCMspecMHandler
		//urnDef.getComponentTypes() handled by UCMspecMHandler
		//urnDef.getResponsibilities() handled by UCMspecMHandler
		for (Object specDiagram : urnDef.getSpecDiagrams()){
			if (elemZ.getGrlspec()!=null && specDiagram instanceof grl.impl.GRLGraphImpl){
				GRLGraph grlGrapZ = (GRLGraph) process(specDiagram, null, true);
				if (!elemZ.getGrlspec().getGrlGraphs().contains(grlGrapZ)){
					elemZ.getGrlspec().getGrlGraphs().add(grlGrapZ);
				}
			}else if (elemZ.getUcmspec()!=null && specDiagram instanceof ucm.map.impl.UCMmapImpl){
				UCMmap ucmMapZ = (UCMmap) process(specDiagram, null, true);
				if (!elemZ.getUcmspec().getUcmMaps().contains(ucmMapZ)){
					elemZ.getUcmspec().getUcmMaps().add(ucmMapZ);
				}
			}
		}
		//urnDef.getUrnspec()
		
		return elemZ;
	}

}
