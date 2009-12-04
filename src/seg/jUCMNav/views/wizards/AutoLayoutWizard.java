package seg.jUCMNav.views.wizards;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.importexport.ExportLayoutDOT;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintContainerRefCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;
import seg.jUCMNav.model.util.AutoLayoutCommandComparator;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.views.preferences.AutoLayoutPreferences;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * The autolayout wizard. Uses graphviz dot.
 * 
 * Code was originally created for UCMs only but was modified to support GRL. Therefore, variable names retain their UCM origins.
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutWizard extends Wizard {

    private IURNDiagram map;
    private UrnEditor editor;
    public static final int PADDING = 50;

    public AutoLayoutWizard(UrnEditor editor, IURNDiagram map) {
        this.map = map;
        this.editor = editor;
        AutoLayoutPreferences.createPreferences();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        addPage(new AutoLayoutDotSettingsWizardPage(Messages.getString("AutoLayoutWizard.dotConfig"))); //$NON-NLS-1$

    }

    /**
     * @param initial
     */
    private String autoLayoutDotString(String initial) {
        String s = ""; //$NON-NLS-1$
        StringBuffer builder = new StringBuffer();
        InputStream is = callDOT(initial.getBytes(), "dot"); //$NON-NLS-1$
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            try {

                while ((s = reader.readLine()) != null)
                    builder.append(s + "\n"); //$NON-NLS-1$
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        // System.out.println(builder.toString());
        return builder.toString();
    }

    private synchronized InputStream callDOT(byte input_for_dot[], String image_type) {
        InputStream istream = null;
        String dot_command = AutoLayoutPreferences.getDotPath() + " -T" + image_type; //$NON-NLS-1$
        try {
            Process p = Runtime.getRuntime().exec(dot_command);
            OutputStream ostream = p.getOutputStream();
            ostream.write(input_for_dot);
            ostream.close();
            istream = new BufferedInputStream(p.getInputStream());
        } catch (Exception e) {
            Status status = new Status(IStatus.ERROR, "seg.jUCMNav", 1, e.toString(), e); //$NON-NLS-1$
            ErrorDialog.openError(getShell(), Messages.getString("AutoLayoutWizard.autoLayoutError"), //$NON-NLS-1$
                    Messages.getString("AutoLayoutWizard.errorOccured"), //$NON-NLS-1$
                    status, IStatus.ERROR | IStatus.WARNING);

            return null;
        }
        return istream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish() {

        if (trimEmptyPoints() == false)
            return false;

        String initial = ExportLayoutDOT.convertUCMToDot(map);
        String positioned = autoLayoutDotString(initial);

        try {
            CompoundCommand cmd2 = repositionLayout(map, positioned);

            if (cmd2.canExecute()) {
                editor.execute(cmd2);
            }

        } catch (Exception e) {
            Status status = new Status(IStatus.ERROR, "seg.jUCMNav", 1, e.toString(), e); //$NON-NLS-1$
            ErrorDialog
                    .openError(
                            getShell(),
                            Messages.getString("AutoLayoutWizard.autoLayoutError"), Messages.getString("AutoLayoutWizard.repositioningError"), status, IStatus.ERROR | IStatus.WARNING); //$NON-NLS-1$ //$NON-NLS-2$
            e.printStackTrace();
            return false;
        }

        /*
         * if (trimEmptyPoints() == false) { return false; }
         */

        return true;
    }

    /**
     * @return success
     */
    private boolean trimEmptyPoints() {
        CompoundCommand cmd;

        if (map instanceof UCMmap && AutoLayoutPreferences.getEmptyPoints()) {
            cmd = new TrimEmptyNodeCommand((UCMmap) map);
            if (cmd.canExecute()) {
                editor.execute(cmd);
            } else {
                MessageDialog.openError(getShell(), Messages.getString("AutoLayoutWizard.error"), Messages.getString("AutoLayoutWizard.emptyNodeError")); //$NON-NLS-1$ //$NON-NLS-2$
                return false;
            }
        }
        return true;
    }

    private static CompoundCommand repositionLayout(IURNDiagram usecasemap, String positioned) throws Exception {
        positioned = positioned.replaceAll("\\\\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
        BufferedReader reader = new BufferedReader(new StringReader(positioned));
        String line;

        CompoundCommand cmd = new CompoundCommand();

        int pageHeight = 0;
        while ((line = reader.readLine()) != null) {

            // ex: graph [bb="0,0,192,212"]; (for the digraph)
            if (line.matches("\\s*digraph " + AutoLayoutPreferences.MAPPREFIX + "\\d+\\s*\\{")) { //$NON-NLS-1$ //$NON-NLS-2$
                IURNDiagram temp = URNElementFinder.findMap(usecasemap.getUrndefinition().getUrnspec(), line.substring(
                        line.indexOf(AutoLayoutPreferences.MAPPREFIX) + AutoLayoutPreferences.MAPPREFIX.length(), line.lastIndexOf('{')).trim());
                if (!usecasemap.equals(temp)) {
                    throw new Exception(Messages.getString("AutoLayoutWizard.invalidMap") //$NON-NLS-1$
                            + line.substring(line.indexOf(AutoLayoutPreferences.MAPPREFIX) + AutoLayoutPreferences.MAPPREFIX.length(), line.lastIndexOf('{'))
                                    .trim() + Messages.getString("AutoLayoutWizard.verifyDotInput")); //$NON-NLS-1$
                }

            } else if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"\\];")) { //$NON-NLS-1$
                pageHeight = PADDING + Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf("\""))); //$NON-NLS-1$ //$NON-NLS-2$
            } else if (line.matches("\\s*subgraph " + AutoLayoutPreferences.COMPONENTPREFIX + "\\d+ \\{")) { // ex: //$NON-NLS-1$ //$NON-NLS-2$
                // subgraph
                // cluster_0
                // {
                // //$NON-NLS-1$
                // //$NON-NLS-2$

                IURNContainerRef compRef = URNElementFinder.findContainerRef(usecasemap, line.substring(
                        line.indexOf(AutoLayoutPreferences.COMPONENTPREFIX) + AutoLayoutPreferences.COMPONENTPREFIX.length(), line.lastIndexOf('{')).trim());

                if (compRef == null)
                    throw new Exception(Messages.getString("AutoLayoutWizard.cantFindCompRef") //$NON-NLS-1$
                            + line.substring(line.indexOf(AutoLayoutPreferences.COMPONENTPREFIX) + AutoLayoutPreferences.COMPONENTPREFIX.length(),
                                    line.lastIndexOf('{')).trim() + Messages.getString("AutoLayoutWizard.inMap")); //$NON-NLS-1$

                line = reader.readLine();
                if (line == null)
                    break;
                // ex: graph [bb="0,0,192,212"];
                if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"];")) { //$NON-NLS-1$
                    String subline = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")); //$NON-NLS-1$ //$NON-NLS-2$
                    String[] coords = subline.split(","); //$NON-NLS-1$
                    // we've got lower left x, y, upper right x, y
                    Command resize = new SetConstraintBoundContainerRefCompoundCommand(compRef, PADDING + Integer.parseInt(coords[0]), pageHeight
                            - Integer.parseInt(coords[3]), Integer.parseInt(coords[2]) - Integer.parseInt(coords[0]), Integer.parseInt(coords[3])
                            - Integer.parseInt(coords[1]) - 10);
                    cmd.add(resize);
                    if (compRef.getParent() != null) {
                        SetConstraintContainerRefCommand cmd2 = new SetConstraintContainerRefCommand(compRef, PADDING + Integer.parseInt(coords[0]), pageHeight
                                - Integer.parseInt(coords[3]) + 40, Integer.parseInt(coords[2]) - Integer.parseInt(coords[0]), Integer.parseInt(coords[3])
                                - Integer.parseInt(coords[1]) - 40);
                        cmd.add(cmd2);
                    }
                } else if (line.matches("\\s*graph \\[bb=\"\"];")) { // ex: //$NON-NLS-1$
                    // graph
                    // [bb=""];
                    // //$NON-NLS-1$

                    // don't know what to do with these.
                    System.out.println("empty componentref. don't know what to do with it."); //$NON-NLS-1$
                    /*
                     * if (compRef.getParent() == ParentFinder.findParent((Map) compRef.eContainer(), compRef, compRef.getX(), height-compRef.getY()-36, 54,
                     * 36)) { //Command resize = new SetConstraintBoundContainerRefCompoundCommand(compRef, compRef.getX(), compRef.getY(), 108, 72); Command
                     * resize = new SetConstraintBoundContainerRefCompoundCommand(compRef, compRef.getX(), height-compRef.getY()-36, 54, 36); cmd.add(resize); }
                     */
                }
            } else if (line.matches("\\s*" + AutoLayoutPreferences.PATHNODEPREFIX + "\\d+ \\[pos=\"\\d+,\\d+\", width=\"?.+\"?, height=\"?.+\"?];")) { //$NON-NLS-1$ //$NON-NLS-2$
                // ex: PathNode5 [pos="76,122", width="1.22", height="0.50"];
                line = line.trim();
                IURNNode pn = URNElementFinder.findNode(usecasemap, line.substring(AutoLayoutPreferences.PATHNODEPREFIX.length(), line.indexOf(" "))); //$NON-NLS-1$

                if (pn == null)
                    throw new Exception(
                            Messages.getString("AutoLayoutWizard.cantFindPathNode") + line.substring(AutoLayoutPreferences.PATHNODEPREFIX.length(), line.indexOf(" ")) //$NON-NLS-1$ //$NON-NLS-2$
                                    + Messages.getString("AutoLayoutWizard.inMap")); //$NON-NLS-1$

                String subline = line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                String[] coords = subline.split(","); //$NON-NLS-1$
                Command move = new SetConstraintCommand(pn, Integer.parseInt(coords[0]) + PADDING, pageHeight - Integer.parseInt(coords[1]));
                cmd.add(move);
            } else if (line
                    .matches("\\s*" + AutoLayoutPreferences.PATHNODEPREFIX + "\\d+\\s*->\\s*" + AutoLayoutPreferences.PATHNODEPREFIX + "\\d+ \\[pos=\"e,(\\d+,\\d+\\s+)*\\d+,\\d+\"];")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                // ex: PathNode50 -> PathNode34 [pos="e,436,488 436,524 436,516
                // 436,507 436,498"];

                if (usecasemap instanceof UCMmap && AutoLayoutPreferences.getEmptyPoints()) {
                    line = line.trim();
                    String sCoordsList = line.substring(line.indexOf(",") + 1, line.lastIndexOf("\"")).replace(' ', ','); //$NON-NLS-1$ //$NON-NLS-2$
                    String[] sCoords = sCoordsList.split(","); //$NON-NLS-1$

                    // the dot file puts the last point at the start. move it.
                    String firstX = sCoords[0], firstY = sCoords[1];

                    for (int i = 2; i < sCoords.length - 2; i++) {
                        sCoords[i - 2] = sCoords[i];
                    }
                    sCoords[sCoords.length - 2] = firstX;
                    sCoords[sCoords.length - 1] = firstY;

                    String sSource = line.substring(line.indexOf(AutoLayoutPreferences.PATHNODEPREFIX) + AutoLayoutPreferences.PATHNODEPREFIX.length(),
                            line.indexOf("-")).trim(); //$NON-NLS-1$
                    String sTarget = line.substring(
                            line.indexOf(AutoLayoutPreferences.PATHNODEPREFIX, line.indexOf(">")) + AutoLayoutPreferences.PATHNODEPREFIX.length(), //$NON-NLS-1$
                            line.indexOf("[", line.indexOf(AutoLayoutPreferences.PATHNODEPREFIX, line.indexOf(">")))).trim(); //$NON-NLS-1$ //$NON-NLS-2$

                    IURNConnection link = URNElementFinder.findConnection(usecasemap, sSource, sTarget);

                    double[] distances = new double[sCoords.length / 2 - 1];
                    StatCalc sc = new StatCalc();
                    for (int i = 2; i < sCoords.length; i += 2) {
                        int curX = Integer.parseInt(sCoords[i]);
                        int curY = Integer.parseInt(sCoords[i + 1]);
                        int prevX = Integer.parseInt(sCoords[i - 2]);
                        int prevY = Integer.parseInt(sCoords[i - 1]);
                        distances[i / 2 - 1] = Math.sqrt(Math.pow(curX - prevX, 2) + Math.pow(curY - prevY, 2));
                        sc.enter(distances[i / 2 - 1]);
                    }

                    double avg = sc.getMean();
                    double stdDev = sc.getStandardDeviation();

                    // for (int i = sCoords.length - 2; i >= 2; i -= 2) {
                    for (int i = sCoords.length - 4; i >= 0; i -= 2) {
                        // if (((i / 2) - 2) % 3 == 0) {

                        if (i == sCoords.length - 2 || (Math.abs(avg - distances[i / 2]) > 0.97 * stdDev && distances[i / 2] >= 30)) {
                            PathNode empty = (PathNode) ModelCreationFactory.getNewObject(usecasemap.getUrndefinition().getUrnspec(), EmptyPoint.class);
                            Command addEmpty = new SplitLinkCommand((UCMmap) usecasemap, empty, (NodeConnection) link, Integer.parseInt(sCoords[i]), pageHeight
                                    - Integer.parseInt(sCoords[i + 1]));
                            if (addEmpty.canExecute()) {
                                cmd.add(addEmpty);
                            }
                        }

                    }
                }

            }
        }
        // bug 304: Sort commands, putting component ref moves before pathnode
        // moves.
        Collections.sort(cmd.getCommands(), new AutoLayoutCommandComparator());
        return cmd;
    }
}