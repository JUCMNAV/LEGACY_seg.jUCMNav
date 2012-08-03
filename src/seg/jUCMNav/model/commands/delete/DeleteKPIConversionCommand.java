/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.kpimodel.KPIConversion;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;

/**
 * Command to delete a KPI Conversion
 * 
 * @author jkealey
 * 
 */
public class DeleteKPIConversionCommand extends CompoundCommand {

    /**
     * @param conv
     *            the KPI Conversion to delete
     */
    public DeleteKPIConversionCommand(KPIConversion conv) {
        setLabel("Delete KPI Conversion");
        add(new PreDeleteUrnModelElementCommand(conv));
        add(new RemoveURNmodelElementCommand(conv));
    }

}
