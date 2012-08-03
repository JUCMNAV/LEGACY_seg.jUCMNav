package seg.jUCMNav.model.commands.delete;

import grl.kpimodel.QualitativeMapping;
import grl.kpimodel.QualitativeMappings;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Delete a QualitativeMapping;
 * 
 * @author jkealey
 * 
 */
public class DeleteQualitativeMappingCommand extends Command implements JUCMNavCommand {

    private QualitativeMapping mapping;
    private QualitativeMappings mappings;
    private int index;
    boolean aborted=false;

    /**
     * 
     */
    public DeleteQualitativeMappingCommand(QualitativeMapping mapping) {
        this.mapping = mapping;
        setLabel("Delete Qualitative Mapping");
    }

  
    /**
     * Only if not null.  
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return mapping!=null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (mapping!=null) {
            this.mappings = (QualitativeMappings) mapping.eContainer();
            this.index = mappings.getMapping().indexOf(mapping);
            this.aborted=false;
        }
        else
            this.aborted=true;

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        mappings.getMapping().remove(index);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert mapping != null && (aborted || mappings!=null)  : "pre something is null"; //$NON-NLS-1$
        assert (aborted || (index>=0 && mappings.getMapping().indexOf(mapping) == index)) : "pre in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert mapping != null && (aborted || mappings!=null)  : "pre something is null"; //$NON-NLS-1$
        assert (aborted || (index>=0 && mappings.getMapping().contains(mapping))) : "pre in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        mappings.getMapping().add(index,  mapping);
        testPreConditions();
    }
}
