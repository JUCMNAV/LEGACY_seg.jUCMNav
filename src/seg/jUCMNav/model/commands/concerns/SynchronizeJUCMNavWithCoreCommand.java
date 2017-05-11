package seg.jUCMNav.model.commands.concerns;
import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.CoreSynchronizeHelper;
import urn.URNspec;


public class SynchronizeJUCMNavWithCoreCommand extends CompoundCommand {

	//private COREConcern concern;
	private URNspec urn;
	private IFile  jucmFile;
	private boolean coreExistent;
	public SynchronizeJUCMNavWithCoreCommand(URNspec urn , IFile jucmFile , boolean coreExistent){
		this.urn = urn ;
		this.jucmFile = jucmFile;
	    this.coreExistent = coreExistent;
	    setLabel(Messages.getString("SynchronizeJUCMNavWithCoreCommand.synchronize")); //$NON-NLS-1$
	}
	
	public boolean canExecute(){
		
		// both .jucm and .core file exist, at the same time the concern in .core file exist
		String coreFileName = jucmFile.getProject().toString().substring(2) + File.separator + jucmFile.getName() + "."+ "core";
		IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(coreFileName));
		if( resource!=null && resource instanceof IFile){
			
	       return  jucmFile!=null && urn!= null && coreExistent;
			 
		}else{
			
			return false;
		}
		 
		
	}
	
	public void execute(){
		if( coreExistent){
			//call CoreSynchronizeHelper to set up and then start synchronization
			String filePath = jucmFile.getRawLocation().makeAbsolute().removeFileExtension().toOSString();
			String coreFileName =  jucmFile.getProject().toString().substring(2) + File.separator + jucmFile.getName().substring(0, jucmFile.getName().lastIndexOf("."))+ "."+ "core";
			CoreSynchronizeHelper.setUp( urn , coreFileName, filePath , true);
		}else{
			// display error message to say can't execute because the core file isn't found
		}
	}
	
	
}
