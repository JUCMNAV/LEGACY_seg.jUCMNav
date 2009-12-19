package seg.jUCMNav.importexport.z151.marshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  StartPoint  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="StartPoint">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//          <xsd:element minOccurs="0" name="workload" type="Workload"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="inBindings" type="xsd:IDREF"/> <!-- InBinding -->
//          <xsd:element minOccurs="0" name="precondition" type="Condition"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

//DONE
import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.StartPoint;
import seg.jUCMNav.importexport.z151.generated.Workload;

public class ScenarioStartPointMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.scenario.ScenarioStartPoint elem = (ucm.scenario.ScenarioStartPoint) o;
		ucm.map.StartPoint startPoint = elem.getStartPoint();
		String objId = startPoint.getId();
		StartPoint elemZ = (StartPoint) getObject(objId, target, "createStartPoint"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (StartPoint) super.handle(startPoint, elemZ, true);
			elemZ.setPrecondition((Condition) process(startPoint.getPrecondition(), null, true));
			elemZ.setWorkload((Workload) process(startPoint.getWorkload(), null, true));
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(startPoint.getInBindings(), elemZ.getInBindings(), "createStartPointInBindings", false); //$NON-NLS-1$
			// elemZ.getPrecondition();
			// elemZ.getWorkload();
			// elemZ.getPred();
			// elemZ.getSucc();
			// elemZ.getContRef();
			// elemZ.getLabel();
			// elemZ.getPos();
			// elemZ.getMetadata();
			// elemZ.getToLinks();
			// elemZ.getFromLinks();
			// elemZ.getConcern();
			// elemZ.getName();
			// elemZ.getId();
			// elemZ.getDesc();
			// elemZ.getClass();
		}
		return elemZ;
	}
}
