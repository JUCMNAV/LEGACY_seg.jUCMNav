package seg.jUCMNav.importexport.z151.unmarshal;

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

import seg.jUCMNav.importexport.z151.generated.ClosedWorkload;
import seg.jUCMNav.importexport.z151.generated.OWPeriodic;
import seg.jUCMNav.importexport.z151.generated.OWPhaseType;
import seg.jUCMNav.importexport.z151.generated.OWPoisson;
import seg.jUCMNav.importexport.z151.generated.OWUniform;
import seg.jUCMNav.importexport.z151.generated.Workload;

public class WorkloadUMHandler extends UCMmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Workload elemZ = (Workload) o;
		String objId = elemZ.getId();
		ucm.performance.Workload elem = (ucm.performance.Workload) getObject(elemZ.getId(), target, ucm.performance.Workload.class);
		if (isFullConstruction) {
			elem = (ucm.performance.Workload) super.handle(elemZ, elem, true);
			// elem.setStartPoint(); handled by StartPointUMHandler
	        elem.setUnit(getTimeUnit(elemZ.getUnit()));

			ucm.performance.ArrivalProcess ArrivalProcess = null;
			if (elemZ instanceof ClosedWorkload) {
				elem.setPopulation(((ClosedWorkload) elemZ).getPopulation());
				elem.setExternalDelay(((ClosedWorkload) elemZ).getExternalDelay());
				elem.setClosed(true);
			} else if (elemZ instanceof OWPeriodic) {
				ArrivalProcess = ucm.performance.ArrivalProcess.PERIODIC_LITERAL;
				elem.setArrivalParam1(((OWPeriodic) elemZ).getPeriod());
				elem.setArrivalParam2(((OWPeriodic) elemZ).getDeviation());
			} else if (elemZ instanceof OWPhaseType) {
				ArrivalProcess = ucm.performance.ArrivalProcess.PHASE_TYPE_LITERAL;
				elem.setArrivalParam1(((OWPhaseType) elemZ).getAlpha());
				elem.setArrivalParam2(((OWPhaseType) elemZ).getS());
			} else if (elemZ instanceof OWPoisson) {
				ArrivalProcess = ucm.performance.ArrivalProcess.POISSON_PDF_LITERAL;
				elem.setArrivalParam1(((OWPoisson) elemZ).getMean());
				elem.setArrivalParam2(null);
			} else if (elemZ instanceof OWUniform) {
				ArrivalProcess = ucm.performance.ArrivalProcess.UNIFORM_LITERAL;
				elem.setArrivalParam1(((OWUniform) elemZ).getStart());
				elem.setArrivalParam2(((OWUniform) elemZ).getEnd());
			}

			elem.setArrivalPattern(ArrivalProcess);

			// case POISSON_PDF: return POISSON_PDF_LITERAL;
			// case PERIODIC: return PERIODIC_LITERAL;
			// case UNIFORM: return UNIFORM_LITERAL;
			// case PHASE_TYPE: return PHASE_TYPE_LITERAL;
			//		
		
			// OWPhaseType
			// OWPoisson
			// OWUniform

			// TODO elem.setCoeffVarSeq();

			// TODO elem.setValue();

			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			// elem.getArrivalPattern();
			// elem.getArrivalParam1();
			// elem.getArrivalParam2();
			// elem.getExternalDelay();
			// elem.getCoeffVarSeq();
			// elem.getPopulation();
			// elem.getValue();
			// elem.getStartPoint();
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
