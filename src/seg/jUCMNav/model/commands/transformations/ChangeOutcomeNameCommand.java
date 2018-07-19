package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import asd.ASDmodelElement;
import asd.ASDspec;
import asd.Outcome;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

/**
 * Renames a PathNode or ComponentRef. Will rename the definition if this is a reference.
 * 
 * @author jkealey
 */
public class ChangeOutcomeNameCommand extends Command implements JUCMNavCommand {
    // PluginBinding dependant.
    private String description = "", oldDesc = ""; //$NON-NLS-1$ //$NON-NLS-2$
    private EObject elem;

    private String expression = "", oldExp = ""; //$NON-NLS-1$ //$NON-NLS-2$

    private Outcome lbl;
    private String name, oldName;

    public ChangeOutcomeNameCommand(Outcome lbl, String name) {
        this.lbl = lbl;
        this.elem= lbl;
        this.name = name;
        setLabel(Messages.getString("ChangeOutcomeNameCommand.changeOutcomeName")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if (elem instanceof Outcome) {
           // return verifyUniqueness(name);
        	return true;
        } else
            return false;
    }

   
    public void execute() {
   
    	oldName=((URNmodelElement) lbl).getName();
    	//((URNmodelElement) lbl).setName(name);
    	redo();
    	
    }

    public String getDescription() {
        return description;
    }

    public String getExpression() {
        return expression;
    }

    public String getName() {
        return name;
    }

    public String getOldName() {
        return oldName;
    }

    public Outcome getRenamedLabel() {
        return this.lbl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        if(elem instanceof Outcome)
    	{
    		((Outcome)elem).setName(name);
    		((URNmodelElement) lbl).setName(name);
    		
    		/*for(Object o : ((Outcome) elem).getEnabledElements())
    			
    		{
    			if(o instanceof ASDmodelElement && o!=null && ((ASDmodelElement) o).getName().equals(oldName))
    			{
    				((Outcome) elem).getEnabledElements().remove(o);
    			}
    		}*/
    		
    		
        }
        testPostConditions();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Sets the new Column name
     * 
     * @param string
     *            the new name
     */
    public void setName(String string) {
        this.name = string;
    }

    /**
     * Sets the old Column name
     * 
     * @param string
     *            the old name
     */
    public void setOldName(String string) {
        oldName = string;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
     /*   assert verifyUniqueness(oldName) : "post problem; non unique name used."; //$NON-NLS-1$
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
*/    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
      /*  assert verifyUniqueness(name) : "pre problem; non unique name used."; //$NON-NLS-1$
        assert elem != null : "pre no elemement to name!"; //$NON-NLS-1$
*/
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
    
        if(elem instanceof Outcome)
    	{
    		((Outcome)elem).setName(oldName);
    		((URNmodelElement) lbl).setName(oldName);
    		 ASDspec spec= ((Outcome)elem).getAsdSpec();
    		/*for(Object a : ((Outcome)elem).getEnabledElements() )
    		{
    			
    		}*/
    		
        }
        testPreConditions();
    }

    /**
     * @return true or false - uniqueness of name
     */
    private boolean verifyUniqueness(String name) {
        if (elem instanceof URNmodelElement) {
            return URNNamingHelper.isNameValid((URNmodelElement) elem, name).length() == 0;
        }
        return true;
    }
}