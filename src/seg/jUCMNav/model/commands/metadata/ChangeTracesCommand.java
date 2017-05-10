package seg.jUCMNav.model.commands.metadata;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Changes the Traces metadata associated with a URN model element.
 * 
 * @author kshen
 */
public class ChangeTracesCommand extends Command implements JUCMNavCommand {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	private String oldTraces;
	private String newTraces;
	private static String traces = "Traces";

	public ChangeTracesCommand(URNspec spec, EObject obj, String traces) {
		urnspec = spec;
		if (obj instanceof URNmodelElement) {
			this.urnelem = (URNmodelElement) obj;
			this.newTraces = traces;
			setLabel("Change metadata Traces"); //$NON-NLS-1$
		}
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldTraces = MetadataHelper.getMetaData(urnelem, traces);
		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();

		if (newTraces != null && newTraces != "")
			MetadataHelper.addMetaData(urnspec, urnelem, traces, newTraces);
		else
			MetadataHelper.removeMetaData(urnelem, traces);

		testPostConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert urnelem != null : "post no elemement to modify!"; //$NON-NLS-1$
		assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert urnelem != null : "post no elemement to modify!"; //$NON-NLS-1$
		assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		if (oldTraces != null && oldTraces != "")
			MetadataHelper.addMetaData(urnspec, urnelem, traces, oldTraces);
		else
			MetadataHelper.removeMetaData(urnelem, traces);

		testPreConditions();
	}
}
