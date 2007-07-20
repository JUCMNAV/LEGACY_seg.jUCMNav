/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.GRLGraph;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command create a IntentionalElementRef and a IntentionalElement object in the model
 * 
 * @author pchen
 * 
 */
public class AddKPIInformationElementRefCommand extends Command implements JUCMNavCommand {

    private KPIInformationElementRef elementRef;

    // Graph where the element has been added.
    private GRLGraph graph;

    private boolean bDefAlreadyExists;
    private KPIInformationElement existingDef;

    /**
     * 
     * @param graph
     *            The graph to which to add the KPIInformationElementRef
     * @param ir
     *            The KPIInformationElementRef
     */
    public AddKPIInformationElementRefCommand(GRLGraph graph, KPIInformationElementRef ir) {
        this.graph = graph;
        this.elementRef = ir;
        setLabel(Messages.getString("AddKPIInformationElementRefCommand.CreateKPIInformationElements")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        existingDef = elementRef.getDef();
        bDefAlreadyExists = graph.getUrndefinition().getUrnspec().getGrlspec().getKpiInformationElements().contains(existingDef);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        URNspec urnspec = graph.getUrndefinition().getUrnspec();
        if (!bDefAlreadyExists)
            urnspec.getGrlspec().getKpiInformationElements().add(elementRef.getDef());
        else
            elementRef.setDef(existingDef);
        graph.getNodes().add(elementRef);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elementRef != null : "pre elementRef"; //$NON-NLS-1$
        assert bDefAlreadyExists || elementRef.getDef() != null : "pre elementDef"; //$NON-NLS-1$
        assert graph != null : "pre graph"; //$NON-NLS-1$

        assert !graph.getNodes().contains(elementRef) : "pre elementref in graph"; //$NON-NLS-1$
        assert bDefAlreadyExists || !graph.getUrndefinition().getUrnspec().getGrlspec().getKpiInformationElements().contains(elementRef.getDef()) : "pre elementDef in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert elementRef != null : "post elementRef"; //$NON-NLS-1$
        assert elementRef.getDef() != null : "post elementDef"; //$NON-NLS-1$
        assert graph != null : "post graph"; //$NON-NLS-1$

        assert graph.getNodes().contains(elementRef) : "post elementref in graph"; //$NON-NLS-1$
        assert graph.getUrndefinition().getUrnspec().getGrlspec().getKpiInformationElements().contains(elementRef.getDef()) : "post elementDef in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        URNspec urnspec = graph.getUrndefinition().getUrnspec();
        if (!bDefAlreadyExists)
            urnspec.getGrlspec().getKpiInformationElements().remove(elementRef.getDef());
        else
            elementRef.setDef(null);

        graph.getNodes().remove(elementRef);

        testPreConditions();
    }
}
