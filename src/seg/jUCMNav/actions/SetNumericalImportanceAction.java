package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.Vector;

import grl.IntentionalElementRef;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalImportanceCommand;
import seg.jUCMNav.views.wizards.IntegerInputRangeDialog;

/**
*
* @author Andrew Miga
*/

public class SetNumericalImportanceAction extends URNSelectionAction
{
    public static final String SET_NUMERICAL_IMPORTANCE = "seg.jUCMNav.SET_NUMERICAL_IMPORTANCE"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "100", "75", "50", "25", "0", "Other...", "Increase    (Shift+X)", "Decrease   (Shift+Z)" };

	public SetNumericalImportanceAction( IWorkbenchPart part, int id )
	{
		super(part);
        setId( SET_NUMERICAL_IMPORTANCE + id );
        setText( values[id] );
        if ( id == ChangeNumericalImportanceCommand.INCREASE )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_up.gif")); //$NON-NLS-1$
        else if ( id == ChangeNumericalImportanceCommand.DECREASE )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_down.gif")); //$NON-NLS-1$

        this.id = id;
	}

    /**
     * We need to have only intentional element references selected. For increase/decrease other tests are necessary.
     */
    protected boolean calculateEnabled()
    {
    	for ( Iterator iter = getSelectedObjects().iterator(); iter.hasNext(); )
    	{
    		Object obj = iter.next();
    		if ( !(obj instanceof IntentionalElementEditPart) )
    			return false;
    		
            if ( id <= ChangeNumericalImportanceCommand.USER_ENTRY ) // operation is not increase or decrease, skip further tests
            	continue;
            
    		IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) obj).getModel());
            int oldEval = ier.getDef().getImportanceQuantitative();
            
            if ( id == ChangeNumericalImportanceCommand.INCREASE ) { // increase operation, verify if possible
            	if ( oldEval == 100 )
            		return false; // can't increase from 100
            } else if ( id == ChangeNumericalImportanceCommand.DECREASE ) { // decrease operation, verify if possible
            	if ( oldEval == 0 )
            		return false; // can't decrease from 0
            }
    	}
    	    
    	intElementRefs = new Vector(); // all tests passed, create list
    	
    	for ( Iterator iter = getSelectedObjects().iterator(); iter.hasNext(); )
    	{
    		IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) iter.next()).getModel());    		
            intElementRefs.add( ier );    	
    	}   	
    	
    	return true;
    }

    public void run()
    {
    	if ( id < ChangeNumericalImportanceCommand.USER_ENTRY || id >= ChangeNumericalImportanceCommand.INCREASE )
    		execute( new ChangeNumericalImportanceCommand( intElementRefs, id, 0, getCommandStack() ) );
    	else if ( id == ChangeNumericalImportanceCommand.USER_ENTRY )
    	{
    	    String currentValue = ( intElementRefs.size() > 1 ) ? "" :
    	    	Integer.toString( ((IntentionalElementRef) (intElementRefs.get(0))).getDef().getImportanceQuantitative() );
    		Integer userEntry = enterImportance( currentValue );
    		if ( userEntry != null ) {
    			int enteredImportance = userEntry.intValue();
        		execute( new ChangeNumericalImportanceCommand( intElementRefs, id, enteredImportance, getCommandStack() ) );    			
    		}
    	}
    }
    
    private Integer enterImportance( String currentValue )
	{	
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	    IntegerInputRangeDialog dialog = new IntegerInputRangeDialog( shell );
	    	
	    return ( dialog.open( "Enter Numerical Importance   (range: [0, 100])", "Enter the new Numerical Importance: ", currentValue, 0, 100 ) );		
	}
	
	public static String generateId( int id )
	{
		return SET_NUMERICAL_IMPORTANCE + id;
	}
	
	public static String getId( String operation )
	{	
		for ( int index = 0; index < values.length; index++ ){
			if( values[index].contains( operation ) )
				return SET_NUMERICAL_IMPORTANCE + index;
		}
		return null;
	}

}
