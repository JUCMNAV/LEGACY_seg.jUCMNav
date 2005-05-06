package seg.jUCMNav.actions;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * Created on 4-May-2005
 * 
 * @author jkealey
 *  
 */
public class AutoLayoutAction implements IEditorActionDelegate {

    private UcmEditor editor;
    private static int id;
    private int mapid = 0;
    HashMap names;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        editor = (UcmEditor) targetEditor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        // we dont' depend on the selection.

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        Map map = editor.getMap(mapid);
        String initial = convertUCMToDot(map);
        String positioned = autoLayoutDotString(initial);
        repositionLayout(positioned);

    }

    /**
     * @param initial
     */
    private String autoLayoutDotString(String initial) {
        String s = "";
        StringBuffer builder = new StringBuffer();
        InputStream is = callDOT(initial.getBytes(), "dot");
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            try {

                while ((s = reader.readLine()) != null)
                    builder.append(s + "\n");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    /**
     * @param map
     */
    private String convertUCMToDot(Map map) {
        names = new HashMap();
        int i;
        id = 0;

        StringBuffer dot = new StringBuffer();

        dot.append("digraph usecasemap {\n");

        for (i = 0; i < map.getCompRefs().size(); i++) {
            ComponentRef compRef = (ComponentRef) map.getCompRefs().get(i);
            // we only want root components
            if (compRef.getParent() == null) {
                buildCluster(compRef, dot);
            }
        }

        for (i = 0; i < map.getPathGraph().getPathNodes().size(); i++) {
            PathNode node = (PathNode) map.getPathGraph().getPathNodes().get(i);
            // we only want loose nodes components
            if (node.getCompRef() == null) {
                names.put("PathNode" + id, node);
                names.put(node, "PathNode" + id);
                dot.append("PathNode" + id++ + ";\n");
            }
        }

        for (i = 0; i < map.getPathGraph().getNodeConnections().size(); i++) {
            NodeConnection conn = (NodeConnection) map.getPathGraph().getNodeConnections().get(i);

            dot.append(names.get(conn.getSource()) + "->" + names.get(conn.getTarget()) + ";\n");
        }
        dot.append("}\n");
        System.out.println(dot.toString());
        return dot.toString();
    }

    private void buildCluster(ComponentRef compRef, StringBuffer dot) {

        names.put("cluster_" + id, compRef);
        dot.append("subgraph cluster_" + id++ + "{\n");

        //ensure visibility
        dot.append("cheaptrick[shape=\"none\",label=\"\"];\n");
        ComponentRef child;
        for (int i = 0; i < compRef.getChildren().size(); i++) {
            child = (ComponentRef) compRef.getChildren().get(i);
            buildCluster(child, dot);
        }
        for (int i = 0; i < compRef.getPathNodes().size(); i++) {
            PathNode node = (PathNode) compRef.getPathNodes().get(i);
            names.put("PathNode" + id, node);
            names.put(node, "PathNode" + id);
            dot.append("PathNode" + id++ + ";\n");
        }

        dot.append("}\n");
    }

    public void repositionLayout(String positioned) {
        BufferedReader reader = new BufferedReader(new StringReader(positioned));
        String line;

        CompoundCommand cmd = new CompoundCommand();

        int pageHeight = 0;

        try {
            while ((line = reader.readLine()) != null) {

                // ex: graph [bb="0,0,192,212"]; (for the digraph)
                if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"\\];")) {
                    pageHeight = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf("\"")));
                }
                if (line.matches("\\s*subgraph cluster_\\d+ \\{")) { // ex: subgraph cluster_0 {
                    ComponentRef compRef = (ComponentRef) names.get(line.substring(line.indexOf("cluster"), line.length() - 2));
                    line = reader.readLine();

                    // ex: graph [bb="0,0,192,212"];
                    if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"];")) {
                        String subline = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                        String[] coords = subline.split(",");
                        // we've got lower left x, y, upper right x, y
                        Command resize = new SetConstraintBoundComponentRefCompoundCommand(compRef, Integer.parseInt(coords[0]), pageHeight
                                - Integer.parseInt(coords[3]), Integer.parseInt(coords[2]) - Integer.parseInt(coords[0]), Integer.parseInt(coords[3])
                                - Integer.parseInt(coords[1]));
                        cmd.add(resize);
                    } else if (line.matches("\\s*graph \\[bb=\"\"];")) { // ex: graph [bb=""];

                        // don't know what to do with these.
                        System.out.println("empty componentref. don't know what to do with it.");
                        /*
                         * if (compRef.getParent() == ParentFinder.findParent((Map) compRef.eContainer(), compRef, compRef.getX(), height-compRef.getY()-36, 54,
                         * 36)) { //Command resize = new SetConstraintBoundComponentRefCompoundCommand(compRef, compRef.getX(), compRef.getY(), 108, 72);
                         * Command resize = new SetConstraintBoundComponentRefCompoundCommand(compRef, compRef.getX(), height-compRef.getY()-36, 54, 36);
                         * cmd.add(resize); }
                         */
                    }
                } else if (line.matches("\\s*PathNode\\d+ \\[pos=\"\\d+,\\d+\", width=\".+\", height=\".+\"];")) // PathNode5 [pos="76,122", width="1.22",
                // height="0.50"];
                {
                    line = line.trim();
                    PathNode pn = (PathNode) names.get(line.substring(0, line.indexOf(" ")));

                    String subline = line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1));
                    String[] coords = subline.split(",");
                    Command move = new SetConstraintCommand(pn, Integer.parseInt(coords[0]), pageHeight - Integer.parseInt(coords[1]));
                    cmd.add(move);

                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        editor.execute(cmd, mapid);

    }

    public static synchronized InputStream callDOT(byte input_for_dot[], String image_type) {
        InputStream istream = null;
        String dot_command = "c:\\program files\\ATT\\GraphViz\\bin\\dot -T" + image_type;
        try {
            Process p = Runtime.getRuntime().exec(dot_command);
            OutputStream ostream = p.getOutputStream();
            ostream.write(input_for_dot);
            ostream.close();
            istream = new BufferedInputStream(p.getInputStream());
        } catch (Exception e) {
            return null;
        }
        return istream;
    }

}