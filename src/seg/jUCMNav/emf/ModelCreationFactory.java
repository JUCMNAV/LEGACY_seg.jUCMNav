/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.emf;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.requests.CreationFactory;

import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.RespRef;
import ucm.map.StartPoint;


/**
 * This class implements the CreationFactory used by the CreationTool. It in turn
 * uses the EMF-generated factories to create the model instances
 * @author ddean
 *
 */
public class ModelCreationFactory implements CreationFactory {
	private Class targetClass;
	
	public ModelCreationFactory( Class targetClass ) {
		this.targetClass = targetClass;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject() {
		Map registry = EPackage.Registry.INSTANCE;
		String workflowURI = MapPackage.eNS_URI;
		MapPackage workflowPackage =
		(MapPackage) registry.get(workflowURI);
		MapFactory factory = workflowPackage.getMapFactory();
			
		Object		result = null;
		
		if(targetClass != null){
			if( targetClass.equals( EmptyPoint.class ) ) {
				result = factory.createEmptyPoint();
			}
			else if(targetClass.equals(NodeConnection.class)){
				result = factory.createNodeConnection();
			}
			else if(targetClass.equals(RespRef.class)){
				result = factory.createRespRef();
			}
			else if(targetClass.equals(StartPoint.class)){
				result = factory.createStartPoint();
			}
			else if(targetClass.equals(EndPoint.class)){
				result = factory.createEndPoint();
			}
			else if(targetClass.equals(ComponentRef.class)) {
			    result = factory.createComponentRef();
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType() {
		return targetClass;
	}
}
