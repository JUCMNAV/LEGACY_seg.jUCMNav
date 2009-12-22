package seg.jUCMNav.actions;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.StubKind;
import ucm.map.PathNode;
import ucm.map.Stub;
import urn.URNspec;

public class AddStubAction extends AddResponsibilityAction {
    protected int stubType = 0;
    
    public AddStubAction(IWorkbenchPart part, int id) {
        super(part);
        stubType = id;
        setId(generateId(id));
        
        setImageDescriptor(ChangeStubTypeAction.getImageDescriptorForStubType(stubType));

        setText("Add " + ChangeStubTypeAction.STUB_TYPES[stubType]);
    }
    
    public static String generateId(int id) {
        return "seg.jUCMNav.AddStub" + id;
    }

    /**
     * @param urn
     * @return the PathNode to be inserted.
     */
    protected PathNode getNewPathNode(URNspec urn) {
        Stub stub = (Stub) ModelCreationFactory.getNewObject(urn, Stub.class, stubType);

        return stub;
    }

}
