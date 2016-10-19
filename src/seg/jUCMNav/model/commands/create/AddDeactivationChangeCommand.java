package seg.jUCMNav.model.commands.create;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import grl.ActorRef;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;

/**
 * This command adds a DeactivationChange to the selected URNModelElement and Dynamic Context
 * 
 * @author aprajita
 * 
 */
public class AddDeactivationChangeCommand extends Command implements JUCMNavCommand {
	
	private Object parent;
	private String selectedChange;
	private Date startDate;
	private Date endDate;
    boolean aborted = false;
    boolean isInCompoundCommand = false;
    private URNspec urn;
    private DeactivationChange newChange;
    private DynamicContext dyn;

    /**
	 * Constructor
	 */
    public AddDeactivationChangeCommand(Object parent, DynamicContext dyn, String selectedChange, Date startDate, Date endDate, URNspec urn) {
        this.parent = parent;
        this.urn = urn;
        this.dyn = dyn;
        this.selectedChange = selectedChange;
        this.startDate = startDate;
        this.endDate = endDate;
        setLabel(Messages.getString("AddDeactivationChangeCommand.AddDeactivationChange")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public AddDeactivationChangeCommand(Object parent, String selectedChange, boolean isInCompoundCommand, DynamicContext dyn, Date startDate, Date endDate, URNspec urn) {
    	this.parent = parent;
        this.urn = urn;
        this.dyn = dyn;
        this.selectedChange = selectedChange;
        this.startDate = startDate;
        this.endDate = endDate;
        setLabel(Messages.getString("AddDeactivationChangeCommand.AddDeactivationChange")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        return parent != null && selectedChange != null && startDate != null && endDate != null && dyn != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (!canExecute()) {
            aborted = true; // another command in same compound command invalidated our preconditions
            return;
        }
        testPreConditions();
        
        //Add the new Deactivation Change
        if (selectedChange.equals("Deactivation Change")){
        	newChange = (DeactivationChange) ModelCreationFactory.getNewObject(urn, DeactivationChange.class);
        	urncore.URNmodelElement elt = null;
        	if (parent instanceof LinkRefEditPart)
        		elt = ((LinkRef)((LinkRefEditPart) this.parent).getModel()).getLink();
        	else if (parent instanceof ElementLink)
        		elt = (ElementLink) this.parent;
        	else if (parent instanceof IntentionalElementEditPart)
        		elt = ((IntentionalElementRef)((IntentionalElementEditPart) this.parent).getModel()).getDef();
        	else if (parent instanceof IntentionalElement)
        		elt = (IntentionalElement) this.parent;
        	else if (parent instanceof ActorRefEditPart)
        		elt = ((ActorRef)((ActorRefEditPart) this.parent).getModel());
        	else if (parent instanceof ActorRef)
        		elt = (ActorRef) this.parent;
        	newChange.setElement(elt);
        	newChange.setStart(startDate);
        	newChange.setEnd(endDate);
        	newChange.setContext(dyn);
        	
        }
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && selectedChange != null && urn != null && dyn != null: "post something null"; //$NON-NLS-1$
        assert dyn.getChanges().contains(newChange) : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && selectedChange != null && urn != null && dyn != null : "pre something null"; //$NON-NLS-1$
        
        
    }
    
    public Change getAddedChange(){
    	return newChange;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        dyn.getChanges().remove(newChange);
        testPreConditions();
    }

}
