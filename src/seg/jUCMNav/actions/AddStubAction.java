package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.PathNode;
import ucm.map.Stub;
import urn.URNspec;


public class AddStubAction extends AddResponsibilityAction
{

	public static final String ADDSTUB= "seg.jUCMNav.AddStub"; //$NON-NLS-1$
	public static final String ADDDYNAMICSTUB= "seg.jUCMNav.AddDynamicStub"; //$NON-NLS-1$
	public static final String ADDPOINTCUTSTUB= "seg.jUCMNav.AddPointcutStub"; //$NON-NLS-1$
	
	public AddStubAction(IWorkbenchPart part, String id)
	{
		super(part);
        setId(id);
    	if (id.equals(ADDDYNAMICSTUB))
        	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/DynStub16.gif")); //$NON-NLS-1$
        else if (id.equals(ADDPOINTCUTSTUB))
        	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/PointcutStub16.gif")); //$NON-NLS-1$
        else
        	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/Stub16.gif")); //$NON-NLS-1$
	}
	
    
    /**
     * @param urn
     * @return the PathNode to be inserted. 
     */
    protected PathNode getNewPathNode(URNspec urn) {
        Stub stub = (Stub) ModelCreationFactory.getNewObject(urn, Stub.class);
    	if (getId().equals(ADDDYNAMICSTUB))
        	stub.setDynamic(true);
        else if (getId().equals(ADDPOINTCUTSTUB))
        	stub.setPointcut(true);
          
        return stub;
    }	

}
