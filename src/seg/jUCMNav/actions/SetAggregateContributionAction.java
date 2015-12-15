package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import grl.IntentionalElementRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.SetAggregateContributionCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm;

/**
 * 
 * @author aprajita
 */

public class SetAggregateContributionAction extends URNSelectionAction{
	public static final String SET_AGGREGATE_CONTRIBUTION = "SetAggregateContributionCommand.SetAggregateContribution"; //$NON-NLS-1$
    private IntentionalElementRef selection;
   private int id;
    private static String[] values = {Messages.getString("SetAggregateContribution.Disable"), //$NON-NLS-1$ 
    		Messages.getString("SetAggregateContribution.Enable"), //$NON-NLS-1$ //$NON-NLS-2$ 
    		Messages.getString("SetAggregateContribution.Show") }; //$NON-NLS-1$ //$NON-NLS-2$ 
    
    public SetAggregateContributionAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_AGGREGATE_CONTRIBUTION + id);
        setText(values[id]);
        this.id = id;
    }
    
    /**
     * @return a {@link SetAggregateContributionCommand} to set the aggregate contribution metadata of the intentional element (selection)
     */
    protected Command getCommand() {
        return new SetAggregateContributionCommand(selection, id);
    }
    
    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled() {
    	SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalElementRef();
            String addAggr = MetadataHelper.getMetaData(selection.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
            
            //If "METADATA_ADDAGGR" added to the element(only after evaluation strategy execution)
            if (addAggr != null)
            	return true;
        default:
            return false;
        }
    }
    
    public static String generateId(int id) {
        return SET_AGGREGATE_CONTRIBUTION + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_AGGREGATE_CONTRIBUTION + index;
        }
        return null;
    }
}
