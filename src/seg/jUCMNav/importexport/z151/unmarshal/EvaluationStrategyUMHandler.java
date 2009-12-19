package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  EvaluationStrategy  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="EvaluationStrategy">
//    <xsd:complexContent>
//      <xsd:extension base="GRLmodelElement">
//        <xsd:sequence>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="evaluations" type="Evaluation"/>
//          <xsd:element maxOccurs="unbounded" name="group" type="xsd:IDREF"/> <!-- StrategiesGroup -->
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.List;

import seg.jUCMNav.importexport.z151.generated.EvaluationStrategy;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.StrategiesGroup;

public class EvaluationStrategyUMHandler extends GRLmodelElementUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		EvaluationStrategy elemZ = (EvaluationStrategy) o;
		String objId = elemZ.getId();
		grl.EvaluationStrategy elem = (grl.EvaluationStrategy) getObject(objId, target, grl.EvaluationStrategy.class);
		if (isFullConstruction) {
			List<Metadata> metaDataList = elemZ.getMetadata();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav EvaluationStrategy author")){ //$NON-NLS-1$
					elem.setAuthor(item.getValue());
					metaDataList.remove(item);
					break;
				}
			}
			elem = (grl.EvaluationStrategy) super.handle(elemZ, elem, true);
			//elem.setGrlspec(); //Handled in GRLspecUMHandler

			if (elemZ.getGroup()!=null && elemZ.getGroup().size()>0) elem.setGroup((grl.StrategiesGroup) process((StrategiesGroup) elemZ
					.getGroup().get(0).getValue(), null, false));
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			elem.getGrlspec();
			//elem.getAuthor();
			processList(elemZ.getEvaluations(), elem.getEvaluations(), true);
			for (Object item : elem.getEvaluations()){
				((grl.Evaluation) item).setStrategies(elem);
			}
			
			
			// elem.getKpiInfoConfig();
			// elem.getGroup();
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
