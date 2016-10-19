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
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.NumericChange;
import urn.dyncontext.QuadraticChange;
import urncore.URNmodelElement;

/**
 * This command adds a NumericChange to the selected URNModelElement and Dynamic Context
 * 
 * @author aprajita
 * 
 */
public class AddNumericChangeCommand extends Command implements JUCMNavCommand {
	private Object parent;
	private String selectedChange, affectedProperty;
	private Date startDate;
	private Date endDate;
    boolean aborted = false;
    boolean isInCompoundCommand = false;
    private URNspec urn;
    private NumericChange newChange;
    private DynamicContext dyn;
    private int newValue;
    private float quadCoeff, linCoeff, conCoeff;
    private String formula;

    /**
	 * Constructor
	 */
    public AddNumericChangeCommand(Object parent, DynamicContext dyn, String selectedChange, String affectedProperty, Date startDate, Date endDate, int newValue, float quadCoeff, float linCoeff, float conCoeff, String formula, URNspec urn) {
        this.parent = parent;
        this.urn = urn;
        this.dyn = dyn;
        this.selectedChange = selectedChange;
        this.affectedProperty = affectedProperty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        this.quadCoeff = quadCoeff;
        this.linCoeff = linCoeff;
        this.conCoeff = conCoeff;
        this.formula = formula;
        setLabel(Messages.getString("AddNumericChangeCommand.AddNumericChange")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public AddNumericChangeCommand(Object parent, String selectedChange, String affectedProperty, boolean isInCompoundCommand, DynamicContext dyn, Date startDate, Date endDate, int newValue, float quadCoeff, float linCoeff, float conCoeff, String formula, URNspec urn) {
    	this.parent = parent;
        this.urn = urn;
        this.dyn = dyn;
        this.selectedChange = selectedChange;
        this.affectedProperty = affectedProperty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        this.quadCoeff = quadCoeff;
        this.linCoeff = linCoeff;
        this.conCoeff = conCoeff;
        this.formula = formula;
        setLabel(Messages.getString("AddNumericChangeCommand.AddNumericChange")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && selectedChange != null && affectedProperty != null && startDate != null && endDate != null && dyn != null;
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
        URNmodelElement elt = null;
        if (parent instanceof LinkRefEditPart)
    		elt = ((LinkRef)((LinkRefEditPart) this.parent).getModel()).getLink();
    	else if (parent instanceof ElementLink)
    		elt = (ElementLink) this.parent;
    	else if (parent instanceof IntentionalElementEditPart)
    		elt = ((IntentionalElementRef)((IntentionalElementEditPart) this.parent).getModel()).getDef();
    	else if (parent instanceof IntentionalElement)
    		elt = (IntentionalElement) this.parent;
    	else if (parent instanceof ActorRefEditPart)
    		elt = (ActorRef)((ActorRefEditPart) this.parent).getModel();
    	else if (parent instanceof ActorRef)
    		elt = (ActorRef) this.parent;
        
        //Add a new type of numeric change according to the selected change 
        if (selectedChange.equals("Constant Change")){
        	newChange = (ConstantChange) ModelCreationFactory.getNewObject(urn, ConstantChange.class);
        	((ConstantChange) newChange).setNewValue(newValue);
        	
        } else if (selectedChange.equals("Linear Change")){
        	newChange = (LinearChange) ModelCreationFactory.getNewObject(urn, LinearChange.class);
        	((LinearChange) newChange).setNewValue(newValue);
        } else if (selectedChange.equals("Quadratic Change")){
        	newChange = (QuadraticChange) ModelCreationFactory.getNewObject(urn, QuadraticChange.class);
        	((QuadraticChange) newChange).setQuadraticCoefficient(quadCoeff);
        	((QuadraticChange) newChange).setLinearCoefficient(linCoeff);
        	((QuadraticChange) newChange).setConstant(conCoeff);
        } else if (selectedChange.equals("Formula Change")){
        	newChange = (FormulaChange) ModelCreationFactory.getNewObject(urn, FormulaChange.class);
        	((FormulaChange) newChange).setFormula(formula);
        }
        newChange.setElement(elt);
    	newChange.setAffectedProperty(affectedProperty);
    	newChange.setStart(startDate);
    	newChange.setEnd(endDate);
    	newChange.setContext(dyn);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && selectedChange != null && affectedProperty != null && urn != null && dyn != null: "post something null"; //$NON-NLS-1$
        assert dyn.getChanges().contains(newChange) : "post child not added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && selectedChange != null && affectedProperty != null && urn != null && dyn != null : "pre something null"; //$NON-NLS-1$
        
        
    }
    
    /**
     * 
     * @return the added change
     */
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
