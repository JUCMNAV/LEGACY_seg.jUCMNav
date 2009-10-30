package seg.jUCMNav.model.commands.transformations;

import javax.swing.JOptionPane;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 *
 * @author Andrew Miga
 */

public class ChangeNumericalImportanceCommand  extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private int id, oldValue, newValue;
	private static int[] values = { 100, 75, 50, 25, 0 };
    private Shell shell;
    private static final String[] columnNames = { "Enter the Numerical Importance" };
    private boolean cancelled = false;

	public ChangeNumericalImportanceCommand( IntentionalElementRef intElemRef, int id ) {
		this.intElemRef = intElemRef;
		this.id = id;
		oldValue = intElemRef.getDef().getImportanceQuantitative();
		if ( id < 5 )
			newValue = values[id];
		else
			enterImportance();
	}
	
	public void execute() {
		if ( cancelled )
			cancelled = false;
		else
			redo();
	}

	public void redo() {
		testPreConditions();

		intElemRef.getDef().setImportanceQuantitative( newValue );

		testPostConditions();
	}

	public void testPostConditions() {
		assert intElemRef != null : "post no element!"; //$NON-NLS-1$
	}

	public void testPreConditions() {
		assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
	}

	public void undo() {
		testPostConditions();

		intElemRef.getDef().setImportanceQuantitative( oldValue );

		testPreConditions();
	}

	private void enterImportance()
	{	
		String user_data = JOptionPane.showInputDialog( null, "Enter the Numerical Importance. Currently: (" + oldValue + ")" );
		
		if (user_data == null) {
			cancelled = true;
			return;
		}
		
		try {
			newValue = Integer.parseInt( user_data );
		} catch ( NumberFormatException nfe ) {
			JOptionPane.showMessageDialog( null, "Please enter an integer", "Error", JOptionPane.PLAIN_MESSAGE );
			cancelled = true;
		}
	}

}
