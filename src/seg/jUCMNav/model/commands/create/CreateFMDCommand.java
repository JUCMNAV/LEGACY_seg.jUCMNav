/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.IURNDiagram;
import fm.FeatureDiagram;

/**
 * This command add a new FeatureModel graph to the model
 */
public class CreateFMDCommand extends Command implements JUCMNavCommand, IGlobalStackCommand {

    private URNspec urn;
    private FeatureDiagram diagram;
    private int oldCount;
    private int index=-1;

    public CreateFMDCommand(URNspec urn) {
        this.urn = urn;

        // must be created here for getDiagram() to work properly.
        diagram = (FeatureDiagram) ModelCreationFactory.getNewObject(urn, FeatureDiagram.class);
        setLabel(Messages.getString("CreateFMDCommand.createFMD")); //$NON-NLS-1$
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

    public FeatureDiagram getDiagram() {
        return diagram;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (getIndex() >= 0 && getIndex() <= urn.getUrndef().getSpecDiagrams().size())
            urn.getUrndef().getSpecDiagrams().add(index, diagram);
        else
            urn.getUrndef().getSpecDiagrams().add(diagram);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUrndef() != null && diagram != null : "post not null"; //$NON-NLS-1$
        assert urn.getUrndef().getSpecDiagrams().contains(diagram) : "post diagram not in model"; //$NON-NLS-1$
        assert oldCount + 1 == urn.getUrndef().getSpecDiagrams().size() : "post should have only one diagram added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUrndef() != null && diagram != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUrndef().getSpecDiagrams().contains(diagram) : "pre diagram not in model"; //$NON-NLS-1$
        assert oldCount == urn.getUrndef().getSpecDiagrams().size() : "pre diagram count wrong"; //$NON-NLS-1$   

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUrndef().getSpecDiagrams().remove(diagram);
        testPreConditions();
    }

    public IURNDiagram getAffectedDiagram() {
        return getDiagram();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
