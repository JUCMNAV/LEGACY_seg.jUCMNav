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
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.StartPoint;
import seg.jUCMNav.importexport.z151.generated.Workload;

public class StartPointMHandler extends PathNodeMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		ucm.map.StartPoint elem = (ucm.map.StartPoint) o;
		String objId = elem.getId();
		StartPoint elemZ = (StartPoint) getObject(objId, target, "createStartPoint"); //$NON-NLS-1$
		if (isFullConstruction) {
			elemZ = (StartPoint) super.handle(elem, elemZ, true);
			elemZ.setPrecondition((Condition) process(elem.getPrecondition(), null, true));
			elemZ.setWorkload((Workload) process(elem.getWorkload(), null, true));
			// elemZ.setContRef();
			// elemZ.setLabel();
			// elemZ.setPos();
			// elemZ.setId();
			// elemZ.setDesc();
			// elemZ.setConcern();
			// elemZ.setName();

			processList(elem.getInBindings(), elemZ.getInBindings(), "createStartPointInBindings", false); //$NON-NLS-1$
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
			boolean local = elem.isLocal();
			Metadata mdZ = of.createMetadata();
			mdZ.setName("jUCMNav StartPoint local"); //$NON-NLS-1$
			mdZ.setValue(""+local); //$NON-NLS-1$
			elemZ.getMetadata().add(mdZ);
			
			ucm.map.FailureKind  failureKind = elem.getFailureKind();
			mdZ = of.createMetadata();
			mdZ.setName("jUCMNav StartPoint failureKind"); //$NON-NLS-1$
			mdZ.setValue(failureKind.toString());
			elemZ.getMetadata().add(mdZ);
		}
		return elemZ;
	}
}
