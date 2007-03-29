package seg.jUCMNav.model.commands.delete.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.UCMspec;
import ucm.map.PathNode;
import ucm.performance.GeneralResource;
import ucm.performance.PerfAttribute;
import ucm.performance.PerfMeasure;
import ucm.performance.Workload;

/**
 * Removes perfMeasure
 * 
 * @author jack
 *  
 */
public class DeletePerfMeasureCommand extends Command implements JUCMNavCommand {

    private PerfMeasure perfMeasure;
    private String id;
    private String name;
    private String description;
    private PerfAttribute perfAttrib;
    private GeneralResource genRes;
    private UCMspec spec;
    private PathNode end;
    private PathNode trigger;
    private List perfValues;
    private Workload workload;
    
    private List fromLinks; // ???
    private List toLinks; // ???
    
    /**
     * @param pm
     *            the PerfMeasure to be deleted.
     */
    public DeletePerfMeasureCommand(PerfMeasure pm) {
        super();
        this.perfMeasure = pm;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
	this.id = perfMeasure.getId();
	this.name = perfMeasure.getName();
	this.description = perfMeasure.getDescription();
	this.perfAttrib = perfMeasure.getMeasure();
	this.genRes = perfMeasure.getResource();
	this.spec = perfMeasure.getUcmspec();
	this.end = perfMeasure.getEnd();
	this.trigger = perfMeasure.getTrigger();
	this.perfValues = new ArrayList();
	this.perfValues.addAll(perfMeasure.getPerfValues());
	this.workload = perfMeasure.getDuration();
	this.fromLinks = new ArrayList();
	this.fromLinks.addAll(perfMeasure.getFromLinks());
	this.toLinks = new ArrayList();
	this.toLinks.addAll(perfMeasure.getToLinks());
	redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
	perfMeasure.setId(null);
	perfMeasure.setName(null);
	perfMeasure.setDescription(null);
	perfMeasure.setMeasure(null);
	perfMeasure.setResource(null);
	perfMeasure.setUcmspec(null);
	if (perfMeasure.getEnd() != null) {
	    end.getPerfMEnd().remove(perfMeasure);
	    perfMeasure.setEnd(null);
	}
	if (perfMeasure.getTrigger() != null) {
	    trigger.getPerfMTrig().remove(perfMeasure);
	    perfMeasure.setTrigger(null);
	}
	perfMeasure.getPerfValues().clear(); // TODO:  ensure that each item need not be destroyed individually.  JS
	perfMeasure.setDuration(null);
	perfMeasure.getFromLinks().clear();
	perfMeasure.getToLinks().clear();
        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
	perfMeasure.setId(id);
	perfMeasure.setName(name);
	perfMeasure.setDescription(description);
	perfMeasure.setMeasure(perfAttrib);
	perfMeasure.setResource(genRes);
	perfMeasure.setUcmspec(spec);
	perfMeasure.setEnd(end);
	end.getPerfMEnd().add(perfMeasure);
	perfMeasure.setTrigger(trigger);
	trigger.getPerfMTrig().add(perfMeasure);
	perfMeasure.getPerfValues().addAll(perfValues);
	perfMeasure.setDuration(workload);
	perfMeasure.getFromLinks().addAll(fromLinks);
	perfMeasure.getToLinks().addAll(toLinks);
        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert perfMeasure != null : "Pre perfMeasure is null";
        assert perfMeasure.getId().compareTo(id) == 0 : "Pre id changed";
        assert perfMeasure.getName().compareTo(name) == 0 : "Pre name changed";
        assert perfMeasure.getDescription().compareTo(description) == 0 : "Pre description changed";
        assert perfMeasure.getMeasure() == perfAttrib : "Pre measure changed";
        assert perfMeasure.getResource() == genRes : "Pre resource changed";
        assert perfMeasure.getUcmspec() == spec : "Pre specification changed";
        assert perfMeasure.getEnd() == end : "Pre end (pathnode) changed";
        assert perfMeasure.getTrigger() == trigger : "Pre trigger (pathnode) changed";
        assert perfMeasure.getPerfValues() == perfValues : "Pre perfValues changed";
        assert perfMeasure.getDuration() == workload : "Pre workload changed";
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
//        assert perfMeasure == null : "Pre perfMeasure is null";
        assert perfMeasure.getId().compareTo(null) == 0 : "Pre id changed";
        assert perfMeasure.getName().compareTo(null) == 0 : "Pre name changed";
        assert perfMeasure.getDescription().compareTo(null) == 0 : "Pre description changed";
        assert perfMeasure.getMeasure() == null : "Pre measure changed";
        assert perfMeasure.getResource() == null : "Pre resource changed";
        assert perfMeasure.getUcmspec() == null : "Pre specification changed";
        assert perfMeasure.getEnd() == null : "Pre end (pathnode) changed";
        assert perfMeasure.getTrigger() == null : "Pre trigger (pathnode) changed";
        assert perfMeasure.getPerfValues() == null : "Pre perfValues changed";
        assert perfMeasure.getDuration() == null : "Pre workload changed";
    }
}
