package seg.jUCMNav.importexport;

import java.io.FileOutputStream;

import org.eclipse.draw2d.IFigure;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import seg.jUCMNav.views.preferences.AutoLayoutPreferences;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urncore.URNmodelElement;

/**
 * Export the layout information in a DOT file.
 * 
 * @author jkealey
 * 
 */
public class ExportLayoutDOT implements IUseCaseMapExport {

    /**
     * Generate a DOT layout file with the given model instance.
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(ucm.map.Map, java.io.FileOutputStream)
     */
    public void export(UCMmap map, FileOutputStream fos) {
        String contents = convertUCMToDot(map);
        try {
            fos.write(contents.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IUseCaseMapExport#export(org.eclipse.draw2d.IFigure, java.io.FileOutputStream)
     */
    public void export(IFigure map, FileOutputStream fos) {
        // not used.
    }

    /**
     * Recursive method that builds a DOT cluster using the ComponentRef bindings.
     * 
     * @param compRef
     *            the parent
     * @param dot
     *            where to write the output.
     */
    private static void buildCluster(ComponentRef compRef, StringBuffer dot) {
        int id = 0;
        dot.append("subgraph " + AutoLayoutPreferences.COMPONENTPREFIX + compRef.getId() + "{\r\n"); //$NON-NLS-1$ //$NON-NLS-2$

        // ensure visibility
        // dot.append("cheaptrick[shape=\"none\",label=\"\"];\n");

        dot.append("CheapTrick" + id++ + " [pos=\"\", width=\"0.50\", height=\"0.50\"];\n"); //$NON-NLS-1$ //$NON-NLS-2$

        ComponentRef child;
        for (int i = 0; i < compRef.getChildren().size(); i++) {
            child = (ComponentRef) compRef.getChildren().get(i);
            buildCluster(child, dot);
        }
        for (int i = 0; i < compRef.getNodes().size(); i++) {
            PathNode node = (PathNode) compRef.getNodes().get(i);

            dot.append(AutoLayoutPreferences.PATHNODEPREFIX + node.getId() + ";\n"); //$NON-NLS-1$
        }

        dot.append("}\n"); //$NON-NLS-1$
    }

    /**
     * Returns a string representation of a Graphviz dot file format which includes the layout information for our use case map.
     * 
     * @param map
     *            the map to be converted to Graphviz dot file format.
     */
    public static String convertUCMToDot(UCMmap map) {
        int i;
        StringBuffer dot = new StringBuffer();
        String rankdir = AutoLayoutPreferences.getOrientation();
        String size = AutoLayoutPreferences.getWidth() + "," + AutoLayoutPreferences.getHeight(); //$NON-NLS-1$

        dot.append("digraph " + AutoLayoutPreferences.MAPPREFIX + map.getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

        for (i = 0; i < map.getCompRefs().size(); i++) {
            ComponentRef compRef = (ComponentRef) map.getCompRefs().get(i);
            // we only want root components
            if (compRef.getParent() == null) {
                buildCluster(compRef, dot);
            }
        }

        for (i = 0; i < map.getNodes().size(); i++) {
            PathNode node = (PathNode) map.getNodes().get(i);
            // we only want loose nodes components
            if (node.getCompRef() == null) {
                dot.append(AutoLayoutPreferences.PATHNODEPREFIX + node.getId() + ";\n"); //$NON-NLS-1$
            }
        }

        for (i = 0; i < map.getConnections().size(); i++) {
            NodeConnection conn = (NodeConnection) map.getConnections().get(i);

            dot.append(AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement)conn.getSource()).getId()
                    + "->" + AutoLayoutPreferences.PATHNODEPREFIX + ((URNmodelElement)conn.getTarget()).getId() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        dot.append("}\n"); //$NON-NLS-1$
        System.out.println(dot.toString());
        return dot.toString();
    }
}
