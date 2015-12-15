package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;
import grl.IntentionalElementRef;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm;
import urn.URNspec;
import urncore.Metadata;

/**
 * Command to set the metadata to show Aggregate Contribution using the selected value
 * from context menu "Set Aggregate Contribution"
 * @author aprajita
 */
public class SetAggregateContributionCommand extends Command implements JUCMNavCommand {
	private IntentionalElementRef intElemRef;
    private String oldType, newType;

    public SetAggregateContributionCommand(IntentionalElementRef intElemRef, int id) {
        this.intElemRef = intElemRef;
        this.oldType = MetadataHelper.getMetaData(intElemRef.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
        
        switch (id) {
        case 0 : // Disable
            newType = "disable";
            break;
        case 1 : // Enable
            newType = "true";
            break;
        case 2 : // Show
            newType = "false" ;
            break;      
        }

        setLabel(Messages.getString("SetAggregateContributionCommand.SetAggregateContribution")); //$NON-NLS-1$
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(intElemRef.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
        
        // Run-time metadata already exists for this element, so directly set new value
        if (metaNumerical != null) {
        	metaNumerical.setValue(newType);
        } 
        // Add new run-time metadata for this element and set its value as selected
        else {
            URNspec urnSpec = intElemRef.getDef().getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, intElemRef.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR, newType);
         }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); 
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert intElemRef != null : "post no element!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions() ;

        Metadata metaNumerical = MetadataHelper.getMetaDataObj(intElemRef.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR);
        if (metaNumerical != null) {
        	// Run-time metadata already exists for this element
            metaNumerical.setValue(oldType) ;
        } else {
            // Add new run-time metadata for this element
            URNspec urnSpec = intElemRef.getDef().getGrlspec().getUrnspec();
            MetadataHelper.addMetaData(urnSpec, intElemRef.getDef(), QuantitativeGRLStrategyAlgorithm.METADATA_ADDAGGR, oldType);
         }
        EvaluationStrategyManager.getInstance().calculateEvaluation(); 
        testPreConditions();
    }
}
