package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Workload  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Workload">
//    <xsd:complexContent>
//      <xsd:extension base="UCMmodelElement">
//        <xsd:sequence>
//          <xsd:element default="ms" name="unit" type="TimeUnit"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OWPeriodic  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OWPeriodic">
//  <xsd:complexContent>
//    <xsd:extension base="OpenWorkload">
//      <xsd:sequence>
//        <xsd:element name="period" type="xsd:string"/>
//        <xsd:element name="deviation" type="xsd:string"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OWPhaseType  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OWPhaseType">
//  <xsd:complexContent>
//    <xsd:extension base="OpenWorkload">
//      <xsd:sequence>
//        <xsd:element name="alpha" type="xsd:string"/>
//        <xsd:element name="s" type="xsd:string"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OWPoisson  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OWPoisson">
//  <xsd:complexContent>
//    <xsd:extension base="OpenWorkload">
//      <xsd:sequence>
//        <xsd:element name="mean" type="xsd:string"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OWUniform  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OWUniform">
//  <xsd:complexContent>
//    <xsd:extension base="OpenWorkload">
//      <xsd:sequence>
//        <xsd:element name="start" type="xsd:string"/>
//        <xsd:element name="end" type="xsd:string"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  OpenWorkload  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="OpenWorkload">
//  <xsd:complexContent>
//    <xsd:extension base="Workload"/>
//  </xsd:complexContent>
//</xsd:complexType>
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<!--  ClosedWorkload  -->
//<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//<xsd:complexType name="ClosedWorkload">
//  <xsd:complexContent>
//    <xsd:extension base="Workload">
//      <xsd:sequence>
//        <xsd:element name="population" type="xsd:string"/>
//        <xsd:element name="externalDelay" type="xsd:string"/>
//      </xsd:sequence>
//    </xsd:extension>
//  </xsd:complexContent>
//</xsd:complexType>

import seg.jUCMNav.importexport.z151.generated.ClosedWorkload;
import seg.jUCMNav.importexport.z151.generated.OWPeriodic;
import seg.jUCMNav.importexport.z151.generated.OWPhaseType;
import seg.jUCMNav.importexport.z151.generated.OWPoisson;
import seg.jUCMNav.importexport.z151.generated.OWUniform;
import seg.jUCMNav.importexport.z151.generated.Workload;

public class WorkloadMHandler extends UCMmodelElementMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.performance.Workload elem = (ucm.performance.Workload) o;
		String objId = elem.getId();
		Workload elemZ = (Workload) id2object.get(objId);
		if (null == elemZ) {
			if (null == target){
				if (elem.isClosed()){
					elemZ = of.createClosedWorkload();
					((ClosedWorkload) elemZ).setPopulation(elem.getPopulation());
					((ClosedWorkload) elemZ).setExternalDelay(elem.getExternalDelay());
				}else{
					switch (elem.getArrivalPattern().getValue()){
					case ucm.performance.ArrivalProcess.PERIODIC:
						elemZ=of.createOWPeriodic();
						((OWPeriodic) elemZ).setPeriod(elem.getArrivalParam1());
						((OWPeriodic) elemZ).setDeviation(elem.getArrivalParam2());
					case ucm.performance.ArrivalProcess.PHASE_TYPE:
						elemZ=of.createOWPhaseType();
						((OWPhaseType) elemZ).setAlpha(elem.getArrivalParam1());
						((OWPhaseType) elemZ).setS(elem.getArrivalParam2());
					case ucm.performance.ArrivalProcess.POISSON_PDF: 
						elemZ=of.createOWPoisson();
						((OWPoisson) elemZ).setMean(elem.getArrivalParam1());						
					case ucm.performance.ArrivalProcess.UNIFORM:
						elemZ=of.createOWUniform();
						((OWUniform) elemZ).setStart(elem.getArrivalParam1());
						((OWUniform) elemZ).setEnd(elem.getArrivalParam2());
						
					}
				}
			}else		
				elemZ = (Workload) target;
			id2object.put(objId, elemZ);
		}
		if (isFullConstruction){
		elemZ = (Workload) super.handle(elem, elemZ, true);
        elemZ.setUnit(getTimeUnit(elem.getUnit()));
		//elemZ.setId();
		//elemZ.setDesc();
		//elemZ.setConcern();
		//elemZ.setName();

		//elemZ.getUnit();
		//elemZ.getMetadata();
		//elemZ.getToLinks();
		//elemZ.getFromLinks();
		//elemZ.getConcern();
		//elemZ.getName();
		//elemZ.getId();
		//elemZ.getDesc();
		//elemZ.getClass();
		}
		return elemZ;
	}
}
