package seg.jUCMNav.model.commands.transformations;

import grl.ImportanceType;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

public class ChangeQualitativeImportanceCommand   extends Command implements JUCMNavCommand
{
	private IntentionalElementRef intElemRef;
	private int id;
	private ImportanceType oldType, newType;

	public ChangeQualitativeImportanceCommand( IntentionalElementRef intElemRef, int id ) {
		this.intElemRef = intElemRef;
		this.id = id;
		oldType = intElemRef.getDef().getImportance();
		newType = ImportanceType.get( id );
	}
	
	public void execute() {
		redo();
	}

	public void redo() {
		testPreConditions();

		intElemRef.getDef().setImportance( newType );

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

		intElemRef.getDef().setImportance( oldType );

		testPreConditions();
	}

}
