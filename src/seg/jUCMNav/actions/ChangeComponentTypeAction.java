package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeComponentTypeCommand;
import urncore.Component;
import urncore.ComponentKind;

/**
 * Changes the component type.
 * 
 * @author jkealey
 */
public class ChangeComponentTypeAction extends URNSelectionAction {
    public static final String CHANGE_COMPONENT_TYPE = "seg.jUCMNav.CHANGE_COMPONENT_TYPE"; //$NON-NLS-1$

    public static final String[] COMPONENT_TYPES = new String[] {
            Messages.getString("ChangeComponentTypeAction.Team"), Messages.getString("ChangeComponentTypeAction.Object"), Messages.getString("ChangeComponentTypeAction.Process"), Messages.getString("ChangeComponentTypeAction.Agent"), Messages.getString("ChangeComponentTypeAction.Actor"), Messages.getString("ChangeComponentTypeAction.Other") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    public static final ComponentKind[] COMPONENT_KINDS = new ComponentKind[] { ComponentKind.TEAM_LITERAL, ComponentKind.OBJECT_LITERAL,
            ComponentKind.PROCESS_LITERAL, ComponentKind.AGENT_LITERAL, ComponentKind.ACTOR_LITERAL, ComponentKind.OTHER_LITERAL };
    protected int componentType;

    /**
     * @param part
     */
    public ChangeComponentTypeAction(IWorkbenchPart part, int componentType) {
        super(part);
        setId(generateId(componentType));
        this.componentType = componentType;

        if (componentType == 1)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Object16.gif")); //$NON-NLS-1$
        else if (componentType == 2)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Process16.gif")); //$NON-NLS-1$
        else if (componentType == 3)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Agent16.gif")); //$NON-NLS-1$
        else if (componentType == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Actor16.gif")); //$NON-NLS-1$
        else
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif")); //$NON-NLS-1$

        setText(Messages.getString("ChangeComponentTypeAction.ConvertTo") + COMPONENT_TYPES[componentType]); //$NON-NLS-1$
    }

    public static String generateId(int componentType) {
        return CHANGE_COMPONENT_TYPE + componentType;
    }

    /**
     * True if we've selected an empty point or a node connection.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.COMPONENTREF:
            Component comp = (Component) sel.getComponentref().getContDef();
            if (comp.getKind() == null)
                return true;

            if (comp.getKind().equals(COMPONENT_KINDS[componentType])) // already one
                return false;
            else
                return true;
        }
        return false;
    }

    /**
     * Returns the appropriate convert command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm;

        switch (sel.getSelectionType()) {

        case SelectionHelper.COMPONENTREF:
            comm = new ChangeComponentTypeCommand(sel.getComponentref(), COMPONENT_KINDS[componentType]);
            return comm;
        default:
            return null;
        }

    }

}