package seg.jUCMNav.actions;

import grl.ImportanceType;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeImportanceCommand;

/**
*
* @author Andrew Miga
*/

public class SetQualitativeImportanceAction extends URNSelectionAction
{
    public static final String SET_QUALITATIVE_IMPORTANCE = "seg.jUCMNav.SET_QUALITATIVE_IMPORTANCE"; //$NON-NLS-1$
    private IntentionalElementRef selection;
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
        return new ChangeQualitativeImportanceCommand( selection, id );
    }

    /**
     * We need to have an intentional element reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled()
    {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalelementref();
            
            if ( id < 4 )
            	return true;
            else if ( id == 4 ) { // increase operation, verify if possible
            	if ( selection.getDef().getImportance() == ImportanceType.HIGH_LITERAL )
            		return false; // can't increase from HIGH
            } else if ( id == 5 ) { // decrease operation, verify if possible
            	if ( selection.getDef().getImportance() == ImportanceType.NONE_LITERAL )
            		return false; // can't decrease from NONE
            }
            
            return true;
        default:
            return false;
        }
    }

	public void run()
	{
    	System.out.println( "SetQualitativeImportance run method called. id: " +id );

		super.run();

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
