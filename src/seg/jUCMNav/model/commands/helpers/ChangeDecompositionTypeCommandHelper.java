package seg.jUCMNav.model.commands.helpers;

import grl.DecompositionType;
import grl.IntentionalElementRef;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

public class ChangeDecompositionTypeCommandHelper implements Command {
    private IntentionalElementRef intElemRef;
    private int oldType, newType;

    public ChangeDecompositionTypeCommandHelper(IntentionalElementRef intElemRef, int id) {
        this.intElemRef = intElemRef;
        oldType = intElemRef.getDef().getDecompositionType().getValue();
        switch (id) {
        case 0 : // AND
            newType = DecompositionType.AND;
            break;
        case 1 : // OR
            newType = DecompositionType.OR;
            break;
        case 2 : // XOR
            newType = DecompositionType.XOR;
            break;      
        }

  //      setLabel(Messages.getString("ChangeDecompositionTypeCommand.ChangeDecompositionType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        intElemRef.getDef().setDecompositionType(DecompositionType.get(newType));

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert intElemRef != null : "post no element!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        intElemRef.getDef().setDecompositionType(DecompositionType.get(oldType));

        testPreConditions();
    }

    @Override
    public boolean canExecute() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
}
