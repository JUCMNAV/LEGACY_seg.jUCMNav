package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;
import urncore.URNdefinition;

public class ChangeUCMDiagramOrder extends Command implements JUCMNavCommand {
    protected URNdefinition def;
    protected int from;
    protected int to;
    
    protected IURNDiagram fromDiag;
    protected IURNDiagram toDiag;
    
    public ChangeUCMDiagramOrder(URNdefinition def, int from, int to) {
        this.def = def;
        this.from = from;
        this.to = to;
        
        fromDiag = (IURNDiagram)def.getSpecDiagrams().get(from);
        toDiag = (IURNDiagram) def.getSpecDiagrams().get(to);
    }

    public void execute() {
        redo();
    }

    public void redo() {
        testPreConditions();
        
        def.getSpecDiagrams().move(to, from);
        
        testPostConditions();
    }

    public void undo() {
        testPostConditions();
        
        def.getSpecDiagrams().move(from, to);
        
        testPreConditions();
    }

    public void testPreConditions() {
        assert def.getSpecDiagrams().indexOf(fromDiag) == from;
        assert def.getSpecDiagrams().indexOf(toDiag) == to;
    }

    public void testPostConditions() {
        assert def.getSpecDiagrams().indexOf(toDiag) == from;
        assert def.getSpecDiagrams().indexOf(fromDiag) == to;
    }
}
