package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.transformations.internal.AttachEndCommand;
import seg.jUCMNav.model.commands.transformations.internal.AttachStartCommand;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * Adds an incoming or outgoing branch to an existing stub. 
 * 
 * @author jkealey
 *  
 */
public class AddBranchOnStubCommand extends CompoundCommand {

	private boolean isInBranch;
	private Stub stub;
	public AddBranchOnStubCommand(Stub stub, boolean isInBranch) {
		this.stub= stub;
		this.isInBranch=isInBranch;
		
		CreatePathCommand cpc = new CreatePathCommand((UCMmap)stub.getDiagram(), stub.getX()-50,stub.getY()-50);
		cpc.createElements();
		add(cpc);
		
		if (isInBranch)
			add(new AttachEndCommand(cpc.getEnd(), stub));
		else
			add(new AttachStartCommand(cpc.getStart(), stub));
			
		
	}
}