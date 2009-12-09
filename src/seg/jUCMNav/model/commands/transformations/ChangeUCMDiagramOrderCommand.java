package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;
import urncore.URNdefinition;

public class ChangeUCMDiagramOrderCommand extends Command implements JUCMNavCommand, IGlobalStackCommand {
    protected URNdefinition def;
    private int from;
    private int to;

    private IURNDiagram fromDiag;
    protected IURNDiagram toDiag;

    public ChangeUCMDiagramOrderCommand(URNdefinition def, int from, int to) {
        this.def = def;
        this.setFrom(from);
        this.setTo(to);

        setMovedDiagram((IURNDiagram) def.getSpecDiagrams().get(from));
        toDiag = (IURNDiagram) def.getSpecDiagrams().get(to);
    }

    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();

        //def.getSpecDiagrams().move(to, from);
        def.getSpecDiagrams().remove(getFrom());
        def.getSpecDiagrams().add(getTo(), getMovedDiagram());

        testPostConditions();
    }

    public void undo() {
        testPostConditions();

        //def.getSpecDiagrams().move(from, to);
        def.getSpecDiagrams().remove(getTo());
        def.getSpecDiagrams().add(getFrom(), getMovedDiagram());
        
        testPreConditions();
    }

    public void testPreConditions() {
        assert def.getSpecDiagrams().indexOf(getMovedDiagram()) == getFrom();
        assert def.getSpecDiagrams().indexOf(toDiag) == getTo();
    }

    public void testPostConditions() {
        //assert def.getSpecDiagrams().indexOf(toDiag) == getFrom();
        assert def.getSpecDiagrams().indexOf(getMovedDiagram()) == getTo();
    }

    public boolean canExecute() {
        return getFrom() != getTo() && getFrom() >= 0 && getTo() >= 0 && getFrom() < def.getSpecDiagrams().size() && getTo() < def.getSpecDiagrams().size();
    }

    public void setMovedDiagram(IURNDiagram fromDiag) {
        this.fromDiag = fromDiag;
    }

    public IURNDiagram getMovedDiagram() {
        return fromDiag;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public IURNDiagram getAffectedDiagram() {
        return getMovedDiagram();
    }
}
