package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.map.RespRef;
import ucm.performance.Demand;

/**
 * Creates the CSM representation(Step) of the Responsibility object.
 * 
 */
public class ResponsibilityRefConverter implements AbstractConverter {
    private RespRef respRef;

    StepAttributes stepAttribs = new StepAttributes();

    // constructors
    public ResponsibilityRefConverter(RespRef resp) {
        respRef = resp;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String mandatory_attribute = "<Step id=\"h" + respRef.getId() + "\" " + "name=\"" + EscapeUtils.escapeXML(respRef.getRespDef().getName()) + "\" " + "predecessor=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                + source.toString().subSequence(1, (source.toString().length() - 1)) + "\" " + "successor=\"" //$NON-NLS-1$ //$NON-NLS-2$
                + target.toString().subSequence(1, (target.toString().length() - 1)) + "\" "; //$NON-NLS-1$
        ps.print("            " + mandatory_attribute); //$NON-NLS-1$

        // optional attributes
        stepAttribs.OptionalAttributes(respRef, ps);

        if (respRef.getRespDef().getDemands().size() == 0) {
            String closing_attribute = "/>"; //$NON-NLS-1$
            // output to file
            ps.println(closing_attribute);
        } else {
            String closing_attribute1 = ">"; //$NON-NLS-1$
            // output to file
            ps.println(closing_attribute1);
            for (Iterator demands = respRef.getRespDef().getDemands().iterator(); demands.hasNext();) {
                Demand demand = (Demand) demands.next();
                String currentDemandQty = demand.getQuantity();
                if (currentDemandQty == null)
                    currentDemandQty = "1"; //$NON-NLS-1$
                String demand_line = "                  <ExternalDemand demand=\"" + currentDemandQty + "\" extOp=\"e" + demand.getResource().getId() + "\"/>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ps.println(demand_line);
            }
            String closing_attribute2 = "            </Step>"; //$NON-NLS-1$
            // output to file
            ps.println(closing_attribute2);
        }

        ps.flush();
    }
}
