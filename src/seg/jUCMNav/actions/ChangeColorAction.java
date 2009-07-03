package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeColorCommand;

/**
 * Changes the fill color of an element.
 * 
 * @author jkealey
 */
public class ChangeColorAction extends URNSelectionAction {
	public static final String CHANGECOLOR = "seg.jUCMNav.CHANGECOLOR"; //$NON-NLS-1$

	public static final String[] COLOR_CHART = new String[] {
			"0,0,0", "0,0,191", "0,191,0", "0,191,191", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"191,0,0", "191,0,191", "191,191,0", "191,191,191", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"64,64,64", "64,64,255", "64,255,64", "64,255,255", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			"255,64,64", "255,64,255", "255,255,64", "255,255,255" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	public static final String[] COLOR_NAMES = new String[] {
			Messages.getString("ChangeColorAction.black"), Messages.getString("ChangeColorAction.blue"), Messages.getString("ChangeColorAction.green"), Messages.getString("ChangeColorAction.cyan"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			Messages.getString("ChangeColorAction.red"), Messages.getString("ChangeColorAction.purple"), Messages.getString("ChangeColorAction.yellow"), Messages.getString("ChangeColorAction.lightgray"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					Messages.getString("ChangeColorAction.gray"), Messages.getString("ChangeColorAction.lightblue"), Messages.getString("ChangeColorAction.lightgreen"), Messages.getString("ChangeColorAction.lightcyan"),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					Messages.getString("ChangeColorAction.lightred"), Messages.getString("ChangeColorAction.lightpurple"), Messages.getString("ChangeColorAction.lightyellow"), Messages.getString("ChangeColorAction.white")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	protected int colorId;
	
	/**
	 * @param part
	 */
	public ChangeColorAction(IWorkbenchPart part, int colorId) {
		super(part);
		setId(generateId(colorId));
		this.colorId=colorId;

		setImageDescriptor(JUCMNavPlugin.getImageDescriptor( "icons/Color"+colorId+"_16.gif")); //$NON-NLS-1$ //$NON-NLS-2$
		
		setText(Messages.getString("ChangeColorAction.ChangeFillColorTO") + COLOR_NAMES[colorId]); //$NON-NLS-1$
	}

	public static String generateId(int colorId)
	{
		return CHANGECOLOR + colorId;
	}
	/**
	 * True if we've selected an empty point or a node connection.
	 */
	protected boolean calculateEnabled() {
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		switch (sel.getSelectionType()) {
		case SelectionHelper.COMMENT:
		case SelectionHelper.COMPONENTREF:
		case SelectionHelper.ACTORREF:
			return true;

		}
		return false;
	}

	/**
	 * Returns the appropriate change color command, given the
	 * current selection.
	 */
	protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm; 
        
        switch (sel.getSelectionType()) {

        case SelectionHelper.COMMENT:
        	comm = new ChangeColorCommand(sel.getComment(), COLOR_CHART[colorId]);
        	return comm;
        case SelectionHelper.COMPONENTREF:
        	comm = new ChangeColorCommand(sel.getComponentref(), COLOR_CHART[colorId]);
        	return comm;
        case SelectionHelper.ACTORREF:
        	comm = new ChangeColorCommand(sel.getActorref(), COLOR_CHART[colorId]);
            return comm;
        default:
            return null;
        }

    }


}