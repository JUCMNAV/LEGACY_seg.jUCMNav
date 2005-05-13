package seg.jUCMNav.views.wizards;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.Wizard;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundComponentRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Created on 9-May-2005
 * 
 * @author jkealey
 *  
 */
public class AutoLayoutWizard extends Wizard {
    public final static String DEFAULTDOTPATH = "c:\\program files\\ATT\\GraphViz\\bin\\dot.exe";
    public final static double DEFAULTHEIGHT = 11;
    public final static int DEFAULTORIENTATION = 0;
    public final static double DEFAULTWIDTH = 8.5;
    public final static String PREF_DOTPATH = "seg.jUCMNav.AutoLayout.DotPath";
    public final static String PREF_HEIGHT = "seg.jUCMNav.AutoLayout.Height";
    public final static String PREF_ORIENTATION = "seg.jUCMNav.AutoLayout.Orientation";
    public final static String PREF_WIDTH = "seg.jUCMNav.AutoLayout.Width";
    private final static String PATHNODEPREFIX = "PathNode";
    private final static String MAPPREFIX = "Map";
    // must start with cluster if we want them rendered.
    private final static String COMPONENTPREFIX = "cluster_ComponentRef";

    public static IPreferenceStore getPreferenceStore() {
        return JUCMNavPlugin.getDefault().getPreferenceStore();
    }
    private CompoundCommand cmd;
    private int id;

    private Map map;

    public AutoLayoutWizard(Map map) {
        this.map = map;
        createPreferences();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        addPage(new AutoLayoutDotSettingsWizardPage("Dot Config"));

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

    private void buildCluster(ComponentRef compRef, StringBuffer dot) {

        dot.append("subgraph " + COMPONENTPREFIX + compRef.getId() + "{\n");

        //ensure visibility
        //dot.append("cheaptrick[shape=\"none\",label=\"\"];\n");
        dot.append("CheapTrick" + id++ + ";");

        ComponentRef child;
        for (int i = 0; i < compRef.getChildren().size(); i++) {
            child = (ComponentRef) compRef.getChildren().get(i);
            buildCluster(child, dot);
        }
        for (int i = 0; i < compRef.getPathNodes().size(); i++) {
            PathNode node = (PathNode) compRef.getPathNodes().get(i);

            dot.append(PATHNODEPREFIX + node.getId() + ";\n");
        }

        dot.append("}\n");
    }

    private synchronized InputStream callDOT(byte input_for_dot[], String image_type) {
        InputStream istream = null;
        String dot_command = getPreferenceStore().getString(PREF_DOTPATH) + " -T" + image_type;
        try {
            Process p = Runtime.getRuntime().exec(dot_command);
            OutputStream ostream = p.getOutputStream();
            ostream.write(input_for_dot);
            ostream.close();
            istream = new BufferedInputStream(p.getInputStream());
        } catch (Exception e) {
            Status status = new Status(Status.ERROR, "seg.jUCMNav", 1, e.toString(), e);
            ErrorDialog
                    .openError(
                            getShell(),
                            "Auto layout error",
                            "An error occured while invoking dot. Please verify that you are using a recent version of Graphviz dot and that you have correctly set the path to dot.",
                            status, IStatus.ERROR | IStatus.WARNING);

            return null;
        }
        return istream;
    }

    /**
     * @param map
     */
    private String convertUCMToDot(Map map) {
        int i;
        id = 0;

        StringBuffer dot = new StringBuffer();
        String rankdir = getPreferenceStore().getInt(PREF_ORIENTATION) == 0 ? "TB" : "LR";
        String size = getPreferenceStore().getString(PREF_WIDTH) + "," + getPreferenceStore().getString(PREF_HEIGHT);

        dot.append("digraph " + MAPPREFIX + map.getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\n");

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
                dot.append(PATHNODEPREFIX + node.getId() + ";\n");
            }
        }

        for (i = 0; i < map.getPathGraph().getNodeConnections().size(); i++) {
            NodeConnection conn = (NodeConnection) map.getPathGraph().getNodeConnections().get(i);

            dot.append(PATHNODEPREFIX + conn.getSource().getId() + "->" + PATHNODEPREFIX + conn.getTarget().getId() + ";\n");
        }
        dot.append("}\n");
        System.out.println(dot.toString());
        return dot.toString();
    }

    private void createPreferences() {
        getPreferenceStore().setDefault(AutoLayoutWizard.PREF_DOTPATH, AutoLayoutWizard.DEFAULTDOTPATH);
        getPreferenceStore().setDefault(AutoLayoutWizard.PREF_ORIENTATION, AutoLayoutWizard.DEFAULTORIENTATION);
        getPreferenceStore().setDefault(AutoLayoutWizard.PREF_WIDTH, AutoLayoutWizard.DEFAULTWIDTH);
        getPreferenceStore().setDefault(AutoLayoutWizard.PREF_HEIGHT, AutoLayoutWizard.DEFAULTHEIGHT);
    }

    /**
     * Returns the command built by repositionLayout(). Returns an UnexecutableCommand.INSTANCE if anything failed.
     * 
     * @return
     */
    public Command getCommand() {
        if (cmd == null)
            return UnexecutableCommand.INSTANCE;
        else
            return cmd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish() {

        //        AutoLayoutDotSettingsWizardPage page0 = ((AutoLayoutDotSettingsWizardPage)getPage("Dot Config"));
        //page0.setDotPath(page0.getTextDotPath());

        String initial = convertUCMToDot(map);
        String positioned = autoLayoutDotString(initial);
        try {
            cmd = repositionLayout(map, positioned);
        } catch (Exception e) {
            Status status = new Status(Status.ERROR, "seg.jUCMNav", 1, e.toString(), e);
            ErrorDialog.openError(getShell(), "Auto layout error", "An error occured while repositioning the map.", status, IStatus.ERROR | IStatus.WARNING);
            e.printStackTrace();
        }

        return true;
    }

    private static CompoundCommand repositionLayout(Map usecasemap, String positioned) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(positioned));
        String line;

        CompoundCommand cmd = new CompoundCommand();

        int pageHeight = 0;
        while ((line = reader.readLine()) != null) {

            // ex: graph [bb="0,0,192,212"]; (for the digraph)
            if (line.matches("\\s*digraph " + MAPPREFIX + "\\d+\\s*\\{")) {
                Map temp = URNElementFinder.findMap((URNspec) usecasemap.eContainer().eContainer(), line.substring(
                        line.indexOf(MAPPREFIX) + MAPPREFIX.length(), line.lastIndexOf('{')).trim());
                if (!usecasemap.equals(temp)) {
                    throw new Exception("The layout information doesn't concern the appropriate map, it concerns the map with ID "
                            + line.substring(line.indexOf(MAPPREFIX) + MAPPREFIX.length(), line.lastIndexOf('{')).trim() + ". Please verify the dot input.");
                }

            } else if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"\\];")) {
                pageHeight = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf("\"")));
            } else if (line.matches("\\s*subgraph " + COMPONENTPREFIX + "\\d+ \\{")) { // ex: subgraph cluster_0 {

                ComponentRef compRef = URNElementFinder.findComponentRef(usecasemap, line.substring(line.indexOf(COMPONENTPREFIX) + COMPONENTPREFIX.length(),
                        line.lastIndexOf('{')).trim());

                if (compRef == null)
                    throw new Exception("Unable to find ComponentRef with the ID "
                            + line.substring(line.indexOf(COMPONENTPREFIX) + COMPONENTPREFIX.length(), line.lastIndexOf('{')).trim()
                            + " in the map. Please verify the dot input.");


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
                     * 36)) { //Command resize = new SetConstraintBoundComponentRefCompoundCommand(compRef, compRef.getX(), compRef.getY(), 108, 72); Command
                     * resize = new SetConstraintBoundComponentRefCompoundCommand(compRef, compRef.getX(), height-compRef.getY()-36, 54, 36); cmd.add(resize); }
                     */
                }
            } else if (line.matches("\\s*" + PATHNODEPREFIX + "\\d+ \\[pos=\"\\d+,\\d+\", width=\".+\", height=\".+\"];")) // PathNode5 [pos="76,122",
            // width="1.22",
            // height="0.50"];
            {
                line = line.trim();
                PathNode pn = URNElementFinder.findPathNode(usecasemap, line.substring(PATHNODEPREFIX.length(), line.indexOf(" ")));

                if (pn == null)
                    throw new Exception("Unable to find PathNode with the ID " + line.substring(PATHNODEPREFIX.length(), line.indexOf(" "))
                            + " in the map. Please verify the dot input.");

                String subline = line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1));
                String[] coords = subline.split(",");
                Command move = new SetConstraintCommand(pn, Integer.parseInt(coords[0]), pageHeight - Integer.parseInt(coords[1]));
                cmd.add(move);

            }
        }
        return cmd;
    }
}