package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import ucm.map.ComponentRef;
import urn.URNspec;
import urncore.ComponentKind;

public class AddContainerRefAction extends URNSelectionAction {
    public final static String ADDTEAM = "seg.jUCMNav.AddTeam";//$NON-NLS-1$
    public final static String ADDOBJECT = "seg.jUCMNav.AddObject";//$NON-NLS-1$
    public final static String ADDPROCESS = "seg.jUCMNav.AddProcess";//$NON-NLS-1$
    public final static String ADDAGENT = "seg.jUCMNav.AddAgent";//$NON-NLS-1$
    public final static String ADDACTOR = "seg.jUCMNav.AddActor";//$NON-NLS-1$
    public final static String ADDOTHER = "seg.jUCMNav.AddOther";//$NON-NLS-1$

    protected UCMNavMultiPageEditor editor;

    public AddContainerRefAction(UCMNavMultiPageEditor part, String id) {
        super(part);
        this.editor = part;
        setId(id);
        if (id.equals(ADDOBJECT))
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Object16.gif")); //$NON-NLS-1$
        else if (id.equals(ADDPROCESS))
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Process16.gif")); //$NON-NLS-1$
        else if (id.equals(ADDAGENT))
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Agent16.gif")); //$NON-NLS-1$
        else if (id.equals(ADDACTOR))
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Actor16.gif")); //$NON-NLS-1$
        else
            // team and other
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Component16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we select a map.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
            return true;
        }
        return false;
    }

    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ComponentRef newCont = getNewContainerRef(sel.getUrnspec());

        Point pos = Display.getCurrent().getCursorLocation();
        if (editor.getCurrentPage() != null)
            pos = editor.getCurrentPage().getGraphicalViewer().getControl().toControl(pos);

        newCont.setX(pos.x);
        newCont.setY(pos.y);

        return new AddContainerRefCommand(sel.getMap(), newCont);
    }

    /**
     * @param urn
     * @return the ComponentRef to be inserted.
     */
    protected ComponentRef getNewContainerRef(URNspec urn) {
        if (getId().equals(ADDOBJECT))
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.OBJECT);
        else if (getId().equals(ADDPROCESS))
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.PROCESS);
        else if (getId().equals(ADDAGENT))
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.AGENT);
        else if (getId().equals(ADDACTOR))
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.ACTOR);
        else if (getId().equals(ADDOTHER))
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.OTHER);
        else
            // team
            return (ComponentRef) ModelCreationFactory.getNewObject(urn, ComponentRef.class, ComponentKind.TEAM);

    }

}
