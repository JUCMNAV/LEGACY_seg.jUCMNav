package seg.jUCMNav.model.commands.ConsistencyCheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import grl.Actor;
import grl.IntentionalElement;
import grl.IntentionalElementType;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.create.CreateResponsibilityCommand;
import ucm.map.UCMmap;
import urn.URNlink;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Command to Create And Make URN link to New Elements.
 * 
 * @author kshen
 * 
 */
public class CreateAndLinkToElementCommand extends CompoundCommand {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	private Set<URNmodelElement> elemSet;
	private Set<URNmodelElement> linkedElementsSet;			// Used to track which elements are fixed, so that corresponding problems can be deleted;
	private List<CreateMapCommand> createMapCommandLists;

	public CreateAndLinkToElementCommand(URNspec urnspec, Set<URNmodelElement> elemSet) {
		this.urnspec = urnspec;
		this.elemSet = elemSet;
		this.linkedElementsSet = new HashSet<URNmodelElement>();
		this.createMapCommandLists = new ArrayList<CreateMapCommand>();
		build();

		setLabel("Create And Link To Element"); //$NON-NLS-1$
	}

	/**
	 * Returns true even if no commands exist.
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (getCommands().size() == 0) {
			return true;
		}
		return super.canExecute();
	}

	/**
	 * Returns true even if no commands exist.
	 */
	public boolean canUndo() {
		if (getCommands().size() == 0)
			return true;
		else
			return super.canUndo();
	}
	
/*	*//**
	 * Returns true even if no commands exist.
	 *//*
	public boolean canRedo() {
		if (getCommands().size() == 0)
			return true;
		else
			return super.canRedo();
	}*/

	public Set<URNmodelElement> getLinkedElements() {
		return linkedElementsSet;
	}

	/**
	 * Late building
	 */
	public void execute() {
		super.execute();
	}

	/** 
	 * Execute these commands separately because multiple CreateMapCommand can't be incorporated into a CompoundCommand.
	 * And once a newly created map changes, it can't be undo.
	 * See http://jucmnav.softwareengineering.ca/foswiki/ProjetSEG/DevDocCommandStack 
	 * @return A list of CreateMapCommand created within this command.
	 */
	public List<CreateMapCommand> getCreateMapCommands() {
		return this.createMapCommandLists;
	}

	/**
	 * Builds a sequence of CreateAndLinkToElementCommand
	 * 
	 */
	public void build() {
		for (URNmodelElement elem : elemSet) {
			if (elem == null) {
				continue;
			}
			urnelem = elem;

			// Create and link to a UCM Map from a GRL IE;
			if (urnelem instanceof IntentionalElement) { // 1, GRL IE -> New UCM Map
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog dialog = new MessageDialog(shell, "", null,
						"Create and Link Intentional Element [" + urnelem.getName() + "] to a:", MessageDialog.NONE, new String[] { "Map",
				"Responsibility"}, 2);
				int result = dialog.open();
				switch (result) {
				case -1:
					//MessageDialog.openInformation(shell, "no selection", "noting selected");
					break;
				case 0:
					createAndLinkToMap();
					linkedElementsSet.add(urnelem);
					break;
				case 1:
					createAndLinkToResponsibility();
					linkedElementsSet.add(urnelem);
					break;
				}
			} else if (urnelem instanceof Actor) {// 2, GRL Actor -> New UCM Component
				// Create new UCM Component;
				AddComponentCommand addComponentCmd = new AddComponentCommand(urnspec, ComponentKind.ACTOR);
				addComponentCmd.getComponent().setName(urnelem.getName());
				add(addComponentCmd);

				// Link to that new UCM Component;
				URNmodelElement fromElement = urnelem;
				URNmodelElement toElement = addComponentCmd.getComponent();
				URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
				newLink.setType( new String("Traces") );
				add(new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement));
				linkedElementsSet.add(urnelem);
			} else if (urnelem instanceof Component) {// 3, UCM Component -> New GRL Actor
				// Create new GRL Actor; 
				AddActorCommand addActorCmd = new AddActorCommand(urnspec);
				addActorCmd.getActor().setName(urnelem.getName());
				add(addActorCmd);

				// Link to that new GRL Actor;
				URNmodelElement fromElement = addActorCmd.getActor();
				URNmodelElement toElement = urnelem;
				URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
				newLink.setType( new String("Traces") );
				add(new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement));
				linkedElementsSet.add(urnelem);
			} else if (urnelem instanceof UCMmap || urnelem instanceof Responsibility) {// 4, UCM Map -> New GRL IE
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog dialog = new MessageDialog(shell, "", null,
						"Create and Link Element [" + urnelem.getName() + "] to a:", MessageDialog.NONE, new String[] { "Goal",
								"SoftGoal", "Task"}, 3);
				int result = dialog.open();
				switch (result) {
				case -1:
					//MessageDialog.openInformation(shell, "no selection", "noting selected");
					break;
				case 0:
					createAndLinkToIE(IntentionalElementType.GOAL);
					linkedElementsSet.add(urnelem);
					break;
				case 1:
					createAndLinkToIE(IntentionalElementType.SOFTGOAL);
					linkedElementsSet.add(urnelem);
					break;
				case 2:
					createAndLinkToIE(IntentionalElementType.TASK);
					linkedElementsSet.add(urnelem);
					break;
				}
			}
		}
	}

	public void createAndLinkToMap() {
		// Create new UCM Map;
		CreateMapCommand createNewMapCmd = new CreateMapCommand(urnspec);
		createMapCommandLists.add(createNewMapCmd);
		createNewMapCmd.getMap().setName(urnelem.getName());

		// Link to that new UCM Map;
		URNmodelElement fromElement = urnelem;
		URNmodelElement toElement = createNewMapCmd.getMap();
		URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
		newLink.setType( new String("Traces") );
		add(new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement));
	}

	public void createAndLinkToResponsibility() {
		// Create new UCM Responsibility;
		Responsibility resp = (Responsibility) ModelCreationFactory.getNewObject(urnspec, Responsibility.class, 0);
		resp.setName(urnelem.getName());
		add(new CreateResponsibilityCommand(urnspec, resp));

		// Link to that new UCM Responsibility;
		URNmodelElement fromElement = urnelem;
		URNmodelElement toElement = resp;
		URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
		newLink.setType( new String("Traces") );
		add(new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement));
	}

	public void createAndLinkToIE(int type) {
		// Create new Intentional Element;
		AddIntentionalElementCommand addIEcmd = new AddIntentionalElementCommand(urnspec, type);
		addIEcmd.getIntentionalElement().setName(urnelem.getName());
		add(addIEcmd);

		// Link to that new GRL IE;
		URNmodelElement fromElement = addIEcmd.getIntentionalElement();
		URNmodelElement toElement = urnelem;
		URNlink newLink = (URNlink) ModelCreationFactory.getNewObject(urnspec, URNlink.class);        	
		newLink.setType( new String("Traces") );
		add(new AddUrnLinkCommand(urnspec, newLink, fromElement, toElement));
	}
}