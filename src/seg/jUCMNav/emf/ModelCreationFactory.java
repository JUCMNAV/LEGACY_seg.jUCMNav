/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.emf;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.Responsibility;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmFactory;
import seg.jUCMNav.model.ucm.UcmPackage;


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
		String workflowURI = UcmPackage.eNS_URI;
		UcmPackage workflowPackage =
		(UcmPackage) registry.get(workflowURI);
		UcmFactory factory = workflowPackage.getUcmFactory();
			
		Object		result = null;
		
		if(targetClass != null){
			if( targetClass.equals( Node.class ) ) {
				result = factory.createNode();
			}
			else if(targetClass.equals(Path.class)){
				result = factory.createPath();
			}
			else if(targetClass.equals(Responsibility.class)){
				result = factory.createResponsibility();
			}
			else if(targetClass.equals(StartPoint.class)){
				result = factory.createStartPoint();
			}
			else if(targetClass.equals(EndPoint.class)){
				result = factory.createEndPoint();
			}
			else if(targetClass.equals(Component.class)){
				result = factory.createComponent();
			}
			else{
				result = factory.createXYElement();
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
