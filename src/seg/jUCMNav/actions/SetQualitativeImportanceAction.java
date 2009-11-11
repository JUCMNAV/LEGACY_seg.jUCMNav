package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.Vector;

import grl.ImportanceType;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeImportanceCommand;

/**
*
* @author Andrew Miga
*/

public class SetQualitativeImportanceAction extends URNSelectionAction
{
    public static final String SET_QUALITATIVE_IMPORTANCE = "seg.jUCMNav.SET_QUALITATIVE_IMPORTANCE"; //$NON-NLS-1$
    private Vector intElementRefs;
    private int id;
    private static String[] values = { "(H)igh", "(M)edium", "(L)ow", "None", "Increase    (x)", "Decrease   (z)" };

	public SetQualitativeImportanceAction(IWorkbenchPart part, int id )
	{
		super(part);
        setId( SET_QUALITATIVE_IMPORTANCE + id );
        setText( values[id] );
        if ( id == 4 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_up.gif")); //$NON-NLS-1$
        else if ( id == 5 )
           	setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/move_down.gif")); //$NON-NLS-1$

        this.id = id;
	}

    protected Command getCommand()
    {
        return new ChangeQualitativeImportanceCommand( intElementRefs, id );
    }

    /**
     * We need to have an intentional element reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled()
    {
    	for ( Iterator iter = getSelectedObjects().iterator(); iter.hasNext(); )
    	{
    		Object obj = iter.next();
    		if ( !(obj instanceof IntentionalElementEditPart) )
    			return false;    		
    		
            if ( id < 4 )  // operation is not increase or decrease, skip further tests
            	continue;
            
    		IntentionalElementRef ier = (IntentionalElementRef) (((IntentionalElementEditPart) obj).getModel());
            
            if ( id == 4 ) { // increase operation, verify if possible
            	if ( ier.getDef().getImportance() == ImportanceType.HIGH_LITERAL )
            		return false; // can't increase from HIGH
            } else if ( id == 5 ) { // decrease operation, verify if possible
            	if ( ier.getDef().getImportance() == ImportanceType.NONE_LITERAL )
            		return false; // can't decrease from NONE
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

	public static String generateId(int id)
	{
		return SET_QUALITATIVE_IMPORTANCE + id;
	}

	public static String getId( String operation )
	{	
		for ( int index = 0; index < values.length; index++ ){
			if( values[index].contains( operation ) )
				return SET_QUALITATIVE_IMPORTANCE + index;
		}
		return null;
	}

}
