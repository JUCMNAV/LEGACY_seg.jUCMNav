package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;

/**
 * Creates the CSM representation(Refinement) of the PluginBinding object.
 * 
 */

public class PluginBindingConverter implements AbstractConverter {
    private PluginBinding pluginBinding;

    // constructors
    public PluginBindingConverter(PluginBinding p_bind) {
        pluginBinding = p_bind;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String object_attributes_head = "<Refinement parent=\"" + "h" + pluginBinding.getStub().getId() + "\" " + "sub=\"" + "m" + pluginBinding.getPlugin().getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        String object_closing = ">"; //$NON-NLS-1$

        String object_attributes = object_attributes_head + object_closing;

        // output to file
        ps.println("                " + object_attributes); //$NON-NLS-1$

        // get inbindings
        int nthIn;
        nthIn = 0;
        for (Iterator inbind_iter = pluginBinding.getIn().iterator(); inbind_iter.hasNext();) {
            InBinding in_bind = (InBinding) inbind_iter.next();
            InBindingConverter in_bind_conv = new InBindingConverter(in_bind);
            // output to file
            ArrayList singleElementArrayIncomingNode = new ArrayList();
            singleElementArrayIncomingNode.add(source.get(nthIn));
            in_bind_conv.Convert(ps, singleElementArrayIncomingNode, null, warnings);
            nthIn++;
        }

        // get outbindings
        int nthOut;
        nthOut = 0;
        for (Iterator outbind_iter = pluginBinding.getOut().iterator(); outbind_iter.hasNext();) {
            OutBinding out_bind = (OutBinding) outbind_iter.next();
            OutBindingConverter out_bind_conv = new OutBindingConverter(out_bind);
            // output to file
            ArrayList singleElementArrayOutgoingNode = new ArrayList();
            singleElementArrayOutgoingNode.add(target.get(nthOut));
            out_bind_conv.Convert(ps, null, singleElementArrayOutgoingNode, warnings);
            nthOut++;
        }

        String object_attributes_close = "</Refinement>"; //$NON-NLS-1$

        // output to file
        ps.println("                " + object_attributes_close); //$NON-NLS-1$
        ps.flush();
    }

}
