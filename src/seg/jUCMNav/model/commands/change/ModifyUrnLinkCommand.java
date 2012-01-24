package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNlink;

public class ModifyUrnLinkCommand extends Command implements JUCMNavCommand {
	
    private URNlink link;
    private String oldType, newType;
    
    public ModifyUrnLinkCommand( URNlink link, String newType ) {
        this.link = link;
        this.oldType = link.getType();
        this.newType = newType;
        setLabel(Messages.getString("ModifyUrnLinkCommand.modifyLinkType")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ( link != null && newType != null ) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();
        link.setType( newType );
        testPostConditions();
    }

    public void undo() {
        testPostConditions();
        link.setType( oldType );
        testPreConditions();    	
    }
    
	public void testPreConditions() {
		assert link != null && newType != null : "pre null"; //$NON-NLS-1$
	}

	public void testPostConditions() {
		assert link != null && oldType != null : "post null"; //$NON-NLS-1$		
	}

}
