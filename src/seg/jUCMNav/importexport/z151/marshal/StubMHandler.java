package seg.jUCMNav.importexport.z151.marshal;

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
// DONE

import java.util.List;

import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.Stub;

public class StubMHandler extends PathNodeMHandler {
    public Object handle(Object o, Object target, boolean isFullConstruction) {
        ucm.map.Stub elem = (ucm.map.Stub) o;
        String objId = elem.getId();
        Stub elemZ = (Stub) getObject(objId, target, "createStub"); //$NON-NLS-1$
        if (isFullConstruction) {
            elemZ = (Stub) super.handle(elem, elemZ, true);
            elemZ.setDynamic(elem.isDynamic());
            elemZ.setSynchronizing(elem.isSynchronization());
            elemZ.setBlocking(elem.isBlocking());
            // elemZ.setContRef();
            // elemZ.setLabel();
            // elemZ.setPos();
            // elemZ.setId();
            // elemZ.setDesc();
            // elemZ.setConcern();
            // elemZ.setName();

            processList(elem.getBindings(), elemZ.getBindings(), true);
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

            List<Metadata> list = elemZ.getMetadata();

            boolean shared = elem.isShared();
            Metadata mdZ = of.createMetadata();
            mdZ.setName("jUCMNav Stub shared"); //$NON-NLS-1$
            mdZ.setValue(Boolean.toString(shared));
            list.add(mdZ);

            // The pointcut attribute is deprecated, but included just in case.
            boolean pointcut = elem.isPointcut();
            mdZ = of.createMetadata();
            mdZ.setName("jUCMNav Stub pointcut"); //$NON-NLS-1$
            mdZ.setValue(Boolean.toString(pointcut));
            list.add(mdZ);

            String repetitionCount = elem.getRepetitionCount();
            if (repetitionCount != null) {
                mdZ = of.createMetadata();
                mdZ.setName("jUCMNav Stub repetitionCount"); //$NON-NLS-1$
                mdZ.setValue(repetitionCount);
                list.add(mdZ);
            }

            ucm.map.PointcutKind aopointcut = elem.getAopointcut();
            mdZ = of.createMetadata();
            mdZ.setName("jUCMNav Stub Aopointcut"); //$NON-NLS-1$
            mdZ.setValue(aopointcut.toString());
            list.add(mdZ);

            ucm.map.AspectKind aspect = elem.getAspect();
            mdZ = of.createMetadata();
            mdZ.setName("jUCMNav Stub aspect"); //$NON-NLS-1$
            mdZ.setValue(aspect.toString());
            list.add(mdZ);
        }
        return elemZ;
    }
}
