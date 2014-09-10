package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.IntentionalElement;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import seg.jUCMNav.Messages;
import urn.URNspec;

public class RemoveIntentionalElementCommandHelper implements Command {

    
    // the intentionalElement to delete
    private IntentionalElement element;

    // the linked Indicator Groups
    private IndicatorGroup[] indGroups = null;

    // the URNspec in which it is contained
    private URNspec urn;

    /**
     * 
     */
    public RemoveIntentionalElementCommandHelper(IntentionalElement intentionalelement) {
        this.element = intentionalelement;
   //     setLabel(Messages.getString("RemoveIntentionalElementCommand.removeIntentionalElement")); //$NON-NLS-1$
    }

    /**
     * Only if not referenced.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return element != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = element.getGrlspec().getUrnspec();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // remove from Indicator Groups, if it is Indicator
        if ((element instanceof Indicator) && ((Indicator) element).getGroups().size() > 0) {
            indGroups = (IndicatorGroup[]) ((Indicator) element).getGroups().toArray(new IndicatorGroup[0]);
            ((Indicator) element).getGroups().clear();
        }

        // remove the IntentionalElement from the urnspec
        urn.getGrlspec().getIntElements().remove(element);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getGrlspec().getIntElements().contains(element) : "pre element in model"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert element.getRefs().size() == 0 : "post can't delete if still referenced."; //$NON-NLS-1$
        assert !urn.getGrlspec().getIntElements().contains(element) : "post element in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add intentionalelement
        urn.getGrlspec().getIntElements().add(element);

        if (indGroups != null) {
            for (int i = 0; i < indGroups.length; i++) {
                indGroups[i].getIndicators().add(element);
            }
        }

        testPreConditions();
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
