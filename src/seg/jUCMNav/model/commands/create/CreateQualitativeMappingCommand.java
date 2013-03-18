/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.kpimodel.QualitativeMapping;
import grl.kpimodel.QualitativeMappings;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command adds a qualitative mapping
 * 
 * 
 * @author jkealey
 * 
 */
public class CreateQualitativeMappingCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private QualitativeMappings mappings;
    private QualitativeMapping mapping;

    /**
     * 
     */
    public CreateQualitativeMappingCommand(URNspec urn, QualitativeMappings mappings) {
        this.urn = urn;
        this.mappings = mappings;
        setLabel("Create Qualitative Mapping");
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null && mappings != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (mapping == null) {
            mapping = (QualitativeMapping) ModelCreationFactory.getNewObject(urn, QualitativeMapping.class);
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        mappings.getMapping().add(mapping);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && mappings!=null && mapping!=null: "post not null"; //$NON-NLS-1$
        assert mappings.getMapping().contains(mapping) : "post mapping not in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && mappings!=null && mapping!=null: "pre not null"; //$NON-NLS-1$
        assert !mappings.getMapping().contains(mapping) : "pre mapping not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        mappings.getMapping().remove(mapping);
        testPreConditions();
    }

    public QualitativeMapping getMapping() {
        return mapping;
    }
}
