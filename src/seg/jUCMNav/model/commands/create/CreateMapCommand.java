package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * This command adds a new map to the URNspec.
 * 
 * @author jkealey
 * 
 */
public class CreateMapCommand extends Command implements JUCMNavCommand, IGlobalStackCommand {
    private UCMmap map;
    private URNspec urn;
    private int oldCount;

    private int index = -1;

    public CreateMapCommand(URNspec urn) {
        this.urn = urn;

        // must be created here for getMap() to work properly.
        map = (UCMmap) ModelCreationFactory.getNewObject(urn, UCMmap.class);
        setLabel(Messages.getString("CreateMapCommand.createMap")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldCount = urn.getUrndef().getSpecDiagrams().size();
        redo();
    }

    /**
     * @return the newly created map;
     */
    public UCMmap getMap() {
        return map;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (getIndex() >= 0 && getIndex() <= urn.getUrndef().getSpecDiagrams().size())
            urn.getUrndef().getSpecDiagrams().add(index, map);
        else
            urn.getUrndef().getSpecDiagrams().add(map);

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUrndef() != null && map != null : "post not null"; //$NON-NLS-1$
        assert urn.getUrndef().getSpecDiagrams().contains(map) : "post map not in model"; //$NON-NLS-1$
        assert oldCount + 1 == urn.getUrndef().getSpecDiagrams().size() : "post should have only one map added"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUrndef() != null && map != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUrndef().getSpecDiagrams().contains(map) : "pre map not in model"; //$NON-NLS-1$
        assert oldCount == urn.getUrndef().getSpecDiagrams().size() : "pre map count wrong"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUrndef().getSpecDiagrams().remove(map);
        testPreConditions();
    }

    public IURNDiagram getAffectedDiagram() {
        return getMap();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}