package seg.jUCMNav.importexport;

import grl.BeliefLink;
import grl.GRLGraph;
import grl.LinkRef;

import java.io.FileOutputStream;

import org.eclipse.draw2d.IFigure;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import seg.jUCMNav.views.preferences.AutoLayoutPreferences;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Export the layout information in a DOT file.
 * 
 * @author jkealey
 * 
 */
public class ExportLayoutDOT implements IUseCaseMapExport {
    static int id = 0;

    /**
     * Recursive method that builds a DOT cluster using the ComponentRef bindings.
     * 
     * @param compRef
     *            the parent
     * @param dot
     *            where to write the output.
     */
    private static void buildCluster(IURNContainerRef compRef, StringBuffer dot) {

        dot.append("subgraph " + AutoLayoutPreferences.COMPONENTPREFIX + ((URNmodelElement) compRef).getId() + " {\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
        // dot.append(" label=\""+((URNmodelElement)compRef.getContDef()).getName()+"\"\r\n");
        // ensure visibility
        // dot.append("cheaptrick[shape=\"none\",label=\"\"];\n");

        dot.append("CheapTrick" + id++ + " [pos=\"\", width=\"1\", height=\"0.01\"];\n"); //$NON-NLS-1$ //$NON-NLS-2$

        IURNContainerRef child;
        for (int i = 0; i < compRef.getChildren().size(); i++) {
            child = (IURNContainerRef) compRef.getChildren().get(i);
            buildCluster(child, dot);
        }
        for (int i = 0; i < compRef.getNodes().size(); i++) {
            URNmodelElement node = (URNmodelElement) compRef.getNodes().get(i);

            dot.append(AutoLayoutPreferences.PATHNODEPREFIX + node.getId() + " ;\n"); //$NON-NLS-1$
        }

        dot.append("} \n"); //$NON-NLS-1$
    }

    /**
     * Returns a string representation of a Graphviz dot file format which includes the layout information for our use case map.
     * 
     * @param map
     *            the map to be converted to Graphviz dot file format.
     */
    public static String convertUCMToDot(IURNDiagram map) {
        int i;
        StringBuffer dot = new StringBuffer();
        String rankdir = AutoLayoutPreferences.getOrientation();
        String size = AutoLayoutPreferences.getWidth() + "," + AutoLayoutPreferences.getHeight(); //$NON-NLS-1$

        if (map instanceof GRLGraph) {
            dot
                    .append("digraph " + AutoLayoutPreferences.MAPPREFIX + ((URNmodelElement) map).getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\nranksep=\"1.0\";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        } else {
            dot
                    .append("digraph " + AutoLayoutPreferences.MAPPREFIX + ((URNmodelElement) map).getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$            
        }
        for (i = 0; i < map.getContRefs().size(); i++) {
            IURNContainerRef compRef = (IURNContainerRef) map.getContRefs().get(i);
            // we only want root components
            if (compRef.getParent() == null) {
                buildCluster(compRef, dot);
            }
        }

        for (i = 0; i < map.getNodes().size(); i++) {
            IURNNode node = (IURNNode) map.getNodes().get(i);
            // we only want loose nodes components
            if (node.getContRef() == null) {
                dot.append(AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement) node).getId() + ";\n"); //$NON-NLS-1$
            }
        }

        for (i = 0; i < map.getConnections().size(); i++) {
            IURNConnection conn = (IURNConnection) map.getConnections().get(i);
            if (conn instanceof LinkRef || conn instanceof BeliefLink) {
                dot.append(AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement) conn.getTarget()).getId()
                        + "->" + AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement) conn.getSource()).getId() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                dot.append(AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement) conn.getSource()).getId()
                        + "->" + AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement) conn.getTarget()).getId() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        dot.append("}\n"); //$NON-NLS-1$
        // System.out.println(dot.toString());
        return dot.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure map, FileOutputStream fos) {
        // not used.
    }

    public void export(IFigure map, String path) {
        // not used.
    }

    /**
     * Generate a DOT layout file with the given model instance.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(IURNDiagram, java.io.FileOutputStream)
     */
    public void export(IURNDiagram diagram, FileOutputStream fos) {
        id = 0;
        //if (diagram instanceof UCMmap) {
        String contents = convertUCMToDot(diagram);
        try {
            fos.write(contents.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //}
    }

    public void export(IURNDiagram diagram, String path) {
        // not used.
    }
}
