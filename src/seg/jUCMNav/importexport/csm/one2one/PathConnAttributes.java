package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;

import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Prints PathConnection optional attributes to CSM file.
 * 
 */

public class PathConnAttributes {

    public void OptionalAttributes(PathNode af, PrintStream ps, ArrayList source, ArrayList target) {
        printDescription(ps, af);
        printSource(ps, af, source);
        printTarget(ps, af, target);
    }

    // prints the source attribute
    public static void printSource(PrintStream ps, PathNode pathnode, ArrayList source) {
        if (source != null) {
            String str_source = source.toString().substring(1, (source.toString().length() - 1));
            String refined_source = str_source.replaceAll(",", ""); //$NON-NLS-1$ //$NON-NLS-2$
            String source_attribute = "source=\"" + refined_source + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(source_attribute);
        }
    }

    // prints the target attribute
    public static void printTarget(PrintStream ps, PathNode pathnode, ArrayList target) {
        if (target != null) {
            // special case for StartPoint
            if (pathnode instanceof StartPoint) {
                String str_target = target.toString().substring(1, (target.toString().length() - 1));
                String refined_target = str_target.replaceAll(",", ""); // can a StartPoint have multiple targets? //$NON-NLS-1$ //$NON-NLS-2$
                String target_attribute = "target=\"" + refined_target + "\" " + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ps.println(" " + target_attribute); //$NON-NLS-1$
            } else {
                String str_target = target.toString().substring(1, (target.toString().length() - 1));
                String refined_target = str_target.replaceAll(",", ""); //$NON-NLS-1$ //$NON-NLS-2$
                String target_attribute = "target=\"" + refined_target + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
                ps.print(target_attribute);
            }
        }
    }

    // prints description attribute
    public static void printDescription(PrintStream ps, PathNode pathnode) {
        if (pathnode.getDescription() != null) {
            String description_attribute = "description=\"" + pathnode.getDescription() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(description_attribute);
        }
    }

    // prints inbinding attribute
    public void inbinding(PrintStream ps, StartPoint sp) {
        if (!sp.getInBindings().isEmpty()) {
            String inbind = ""; //$NON-NLS-1$
            for (int i = 0; i < sp.getInBindings().size(); i++) {
                String in_bind_str = sp.getInBindings().get(i).toString();
                String in_bind_id = in_bind_str.substring(28, (in_bind_str.length() - 1));
                inbind += in_bind_id;
            }
            String source_attribute = "Inbinding=\"" + inbind + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(source_attribute);
        }
    }

    // prints outbinding attribute
    public void outbinding(PrintStream ps, EndPoint ep) {
        if (!ep.getOutBindings().isEmpty()) {
            String outbind = ""; //$NON-NLS-1$
            for (int i = 0; i < ep.getOutBindings().size(); i++) {
                String out_bind_str = ep.getOutBindings().get(i).toString();
                String out_bind_id = out_bind_str.substring(28, (out_bind_str.length() - 1));
                outbind += out_bind_id;
            }
            String source_attribute = "Outbinding=\"" + outbind + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(source_attribute);
        }
    }
}
