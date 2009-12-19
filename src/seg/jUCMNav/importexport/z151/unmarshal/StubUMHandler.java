package seg.jUCMNav.importexport.z151.unmarshal;

//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  Stub  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:complexType name="Stub">
//    <xsd:complexContent>
//      <xsd:extension base="PathNode">
//        <xsd:sequence>
//          <xsd:element name="dynamic" type="xsd:boolean"/>
//          <xsd:element name="synchronizing" type="xsd:boolean"/>
//          <xsd:element name="blocking" type="xsd:boolean"/>
//          <xsd:element maxOccurs="unbounded" minOccurs="0" name="bindings" type="PluginBinding"/>
//        </xsd:sequence>
//      </xsd:extension>
//    </xsd:complexContent>
//  </xsd:complexType>

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Stub;


public class StubUMHandler extends PathNodeUMHandler {
	public Object handle(Object o, Object target, boolean isFullConstruction) {
		Stub elemZ = (Stub) o;
		ucm.map.Stub elem = (ucm.map.Stub) getObject(elemZ.getId(), target, ucm.map.Stub.class);
		if (isFullConstruction) {
			List<Metadata> metaDataList = elemZ.getMetadata();
			List<Metadata> removeList = new ArrayList <Metadata> ();
			for(Metadata item: metaDataList){
				if (item.getName().equals("jUCMNav Stub shared")){ //$NON-NLS-1$
					elem.setShared(Boolean.parseBoolean(item.getValue()));
					removeList.add(item);
				}
                // The pointcut attribute is deprecated, but included just in case.
                if (item.getName().equals("jUCMNav Stub pointcut")){ //$NON-NLS-1$
                    elem.setPointcut(Boolean.parseBoolean(item.getValue()));
                    removeList.add(item);
                }
				if (item.getName().equals("jUCMNav Stub repetitionCount")){ //$NON-NLS-1$
					elem.setRepetitionCount(item.getValue());
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav Stub Aopointcut")){ //$NON-NLS-1$
					elem.setAopointcut(ucm.map.PointcutKind.get(item.getValue()));
					removeList.add(item);
				}
				if (item.getName().equals("jUCMNav Stub aspect")){ //$NON-NLS-1$
					elem.setAspect(ucm.map.AspectKind.get(item.getValue()));
					removeList.add(item);
				}
			}
			metaDataList.removeAll(removeList);
			
			elem = (ucm.map.Stub) super.handle(elemZ, elem, true);
			elem.setDynamic(elemZ.isDynamic());
			elem.setSynchronization(elemZ.isSynchronizing());
			elem.setBlocking(elemZ.isBlocking());
			// elem.setX();
			// elem.setY();
			// elem.setDiagram();
			// elem.setContRef();
			// elem.setLabel();
			// elem.setId();
			// elem.setName();
			// elem.setDescription();

			processList(elemZ.getBindings(), elem.getBindings(), true);
			for (Object item : elem.getBindings()){
				((ucm.map.PluginBinding) item).setStub(elem);
			}
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
