package seg.jUCMNav.model.commands.transformations;

import java.util.Date;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.NumericChange;
import urn.dyncontext.QuadraticChange;

/**
 * Command to update the selected Numeric Change
 * 
 * @author aprajita
 */
public class UpdateNumericChangeCommand extends Command implements JUCMNavCommand {
	
	private Object parent;
	private String selectedChange, affectedProperty, oldAffectedProperty;
	private Date startDate, oldStartDate;
	private Date endDate, oldEndDate;
    boolean aborted = false;
    boolean isInCompoundCommand = false;
    private URNspec urn;
    private NumericChange changeToUpdate;
    private int newValue, oldValue;
    private float newQuadCoeff, oldQuadCoeff, newLinCoeff, oldLinCoeff, newConCoeff, oldConCoeff;
    private String newFormula, oldFormula;
    private DynamicContext dynContext;
    
    /**
	 * Constructor
	 */
    public UpdateNumericChangeCommand(Object parent, NumericChange changeToUpdate, String selectedChange, String affectedProperty, Date startDate, Date endDate, int newValue, float newQuadCoeff, float newLinCoeff, float newConCoeff, String newFormula, URNspec urn) {
        this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.selectedChange = selectedChange;
        this.affectedProperty = affectedProperty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        this.newQuadCoeff = newQuadCoeff;
        this.newLinCoeff = newLinCoeff;
        this.newConCoeff = newConCoeff;
        this.newFormula = newFormula;
        setLabel(Messages.getString("UpdateNumericChangeCommand.UpdateNumericChange")); //$NON-NLS-1$
    }

    /**
	 * 
	 */
    public UpdateNumericChangeCommand(Object parent, NumericChange changeToUpdate, String selectedChange, String affectedProperty, boolean isInCompoundCommand, Date startDate, Date endDate, int newValue, float newQuadCoeff, float newLinCoeff, float newConCoeff, String newFormula, URNspec urn) {
    	this.parent = parent;
        this.urn = urn;
        this.changeToUpdate = changeToUpdate;
        this.selectedChange = selectedChange;
        this.affectedProperty = affectedProperty;
        this.startDate = startDate;
        this.endDate = endDate;
        this.newValue = newValue;
        this.newQuadCoeff = newQuadCoeff;
        this.newLinCoeff = newLinCoeff;
        this.newConCoeff = newConCoeff;
        this.newFormula = newFormula;
        setLabel(Messages.getString("UpdateNumericChangeCommand.UpdateNumericChange")); //$NON-NLS-1$
        this.isInCompoundCommand = isInCompoundCommand;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return parent != null && changeToUpdate != null && selectedChange != null && affectedProperty != null && startDate != null && endDate != null;
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
        oldAffectedProperty = changeToUpdate.getAffectedProperty();
        oldStartDate = changeToUpdate.getStart();
        oldEndDate = changeToUpdate.getEnd();
        dynContext = changeToUpdate.getContext();
        
        if (selectedChange.equals("Constant Change")){
        	oldValue = ((ConstantChange)changeToUpdate).getNewValue();
        	((ConstantChange)changeToUpdate).setNewValue(newValue);
        	
        } else if (selectedChange.equals("Linear Change")){
        	oldValue = ((LinearChange)changeToUpdate).getNewValue();
        	((LinearChange)changeToUpdate).setNewValue(newValue);
        } else if (selectedChange.equals("Quadratic Change")){
        	oldQuadCoeff = ((QuadraticChange)changeToUpdate).getQuadraticCoefficient();
        	((QuadraticChange)changeToUpdate).setQuadraticCoefficient(newQuadCoeff);
        	oldLinCoeff = ((QuadraticChange)changeToUpdate).getLinearCoefficient();
        	((QuadraticChange)changeToUpdate).setLinearCoefficient(newLinCoeff);
        	oldConCoeff = ((QuadraticChange)changeToUpdate).getConstant();
        	((QuadraticChange)changeToUpdate).setConstant(newConCoeff);
        } else if (selectedChange.equals("Formula Change")){
        	oldFormula = ((FormulaChange)changeToUpdate).getFormula();
        	((FormulaChange)changeToUpdate).setFormula(newFormula);;
        }
        
        changeToUpdate.setAffectedProperty(affectedProperty);
		changeToUpdate.setStart(startDate);
		changeToUpdate.setEnd(endDate);
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert parent != null && selectedChange != null && affectedProperty != null && urn != null: "post not null"; //$NON-NLS-1$
        assert (dynContext.getChanges().contains(changeToUpdate)) && changeToUpdate.getAffectedProperty().equals(affectedProperty)
        && changeToUpdate.getStart().equals(startDate) && changeToUpdate.getEnd().equals(endDate) : "post child not updated"; //$NON-NLS-1$
        if (changeToUpdate instanceof ConstantChange) 
        	assert ((ConstantChange) changeToUpdate).getNewValue() == newValue : "post value not updated";
        else if (changeToUpdate instanceof LinearChange) 
        	assert ((LinearChange) changeToUpdate).getNewValue() == newValue : "post value not updated";
        else if (changeToUpdate instanceof QuadraticChange) {
        	assert ((QuadraticChange) changeToUpdate).getQuadraticCoefficient() == newQuadCoeff || 
        			((QuadraticChange) changeToUpdate).getLinearCoefficient() == newLinCoeff || 
                			((QuadraticChange) changeToUpdate).getConstant() == newConCoeff : "post coefficients not updated";
        }
        else if (changeToUpdate instanceof FormulaChange) 
        	assert ((FormulaChange) changeToUpdate).getFormula().equals(newFormula) : "post formula not updated";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert parent != null && changeToUpdate != null && selectedChange != null && affectedProperty != null && urn != null: "pre not null"; //$NON-NLS-1$
        
        
    }
    
    public Change getUpdatedChange(){
    	return changeToUpdate;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        changeToUpdate.setAffectedProperty(oldAffectedProperty);
		changeToUpdate.setStart(oldStartDate);
		changeToUpdate.setEnd(oldEndDate);
		if (changeToUpdate instanceof ConstantChange)
			((ConstantChange) changeToUpdate).setNewValue(oldValue);
		else if (changeToUpdate instanceof LinearChange)
			((LinearChange) changeToUpdate).setNewValue(oldValue);
		else if (changeToUpdate instanceof QuadraticChange) {
			((QuadraticChange) changeToUpdate).setQuadraticCoefficient(oldQuadCoeff);
			((QuadraticChange) changeToUpdate).setLinearCoefficient(oldLinCoeff);
			((QuadraticChange) changeToUpdate).setConstant(oldConCoeff);
		}
		else if (changeToUpdate instanceof FormulaChange)
			((FormulaChange) changeToUpdate).setFormula(oldFormula);
			
		
        testPreConditions();
    }

}
