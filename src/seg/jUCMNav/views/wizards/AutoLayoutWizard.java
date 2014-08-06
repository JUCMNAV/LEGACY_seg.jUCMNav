package seg.jUCMNav.views.wizards;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.importexport.ExportLayoutDOT;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintContainerRefCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import seg.jUCMNav.model.commands.transformations.TrimEmptyNodeCommand;
import seg.jUCMNav.model.util.AutoLayoutCommandComparator;
import seg.jUCMNav.model.util.MetadataHelper;
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
import urncore.URNmodelElement;

/**
 * The autolayout wizard. Uses graphviz dot.
 * 
 * Code was originally created for UCMs only but was modified to support GRL.
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutWizard extends Wizard {

	private IURNDiagram diagram;
	private UrnEditor editor;
	public static final int PADDING = 50;

	public AutoLayoutWizard(UrnEditor editor, IURNDiagram map) {
		this.diagram = map;
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
	public String autoLayoutDotString(String initial) {
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

		addIntentionalElemRefDimensions();


		String initial = ExportLayoutDOT.convertURNToDot(diagram);
		String positioned = autoLayoutDotString(initial);

		System.out.println(initial);
		System.out.println("***********************************************************************");
		System.out.println(positioned);
		
		try {
			CompoundCommand cmd2 = repositionLayout(diagram, positioned);

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

	public void addIntentionalElemRefDimensions() {
		UCMNavMultiPageEditor multi = (UCMNavMultiPageEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		Collection<EditPart> editparts = ((UrnEditor)multi.getCurrentPage()).getGraphicalViewer().getEditPartRegistry().values();

		for (EditPart editPart : editparts){

			if (editPart instanceof NodeEditPart){
				NodeEditPart nodeEditPart = (NodeEditPart)editPart;

				if ( nodeEditPart instanceof IntentionalElementEditPart){
					int height = nodeEditPart.getFigure().getBounds().height;
					int width = nodeEditPart.getFigure().getBounds().width;

					IURNNode node = (IURNNode) nodeEditPart.getModel();

					MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(),
							(URNmodelElement)node, "_height", String.valueOf(height));
					MetadataHelper.addMetaData(node.getDiagram().getUrndefinition().getUrnspec(),
							(URNmodelElement)node, "_width", String.valueOf(width));
				}
			}
		}

	}

	/**
	 * @return success
	 */
	public boolean trimEmptyPoints() {
		CompoundCommand cmd;

		if (diagram instanceof UCMmap && AutoLayoutPreferences.getEmptyPoints()) {
			cmd = new TrimEmptyNodeCommand((UCMmap) diagram);
			if (cmd.canExecute()) {
				editor.execute(cmd);
			} else {
				MessageDialog.openError(getShell(), Messages.getString("AutoLayoutWizard.error"), Messages.getString("AutoLayoutWizard.emptyNodeError")); //$NON-NLS-1$ //$NON-NLS-2$
				return false;
			}
		}
		return true;
	}

	public static CompoundCommand repositionLayout(IURNDiagram urndiagram, String positioned) throws Exception  {
		positioned = positioned.replaceAll("\\\\n", ""); //$NON-NLS-1$ //$NON-NLS-2$
		BufferedReader reader = new BufferedReader(new StringReader(positioned));
		String line;

		CompoundCommand cmd = new CompoundCommand();

		int pageHeight = 0;
		while ((line = reader.readLine()) != null) {

			// ex: graph [bb="0,0,192,212"]; (for the digraph)
			if (line.matches("\\s*digraph " + AutoLayoutPreferences.DIAGPREFIX + "\\d+\\s*\\{")) { //$NON-NLS-1$ //$NON-NLS-2$
				IURNDiagram temp = URNElementFinder.findMap(urndiagram.getUrndefinition().getUrnspec(), line.substring(
						line.indexOf(AutoLayoutPreferences.DIAGPREFIX) + AutoLayoutPreferences.DIAGPREFIX.length(), line.lastIndexOf('{')).trim());
				if (!urndiagram.equals(temp)) {
					throw new Exception(Messages.getString("AutoLayoutWizard.invalidMap") //$NON-NLS-1$
							+ line.substring(line.indexOf(AutoLayoutPreferences.DIAGPREFIX) + AutoLayoutPreferences.DIAGPREFIX.length(), line.lastIndexOf('{'))
							.trim() + Messages.getString("AutoLayoutWizard.verifyDotInput")); //$NON-NLS-1$
				}

			} else if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\"\\];")) { //$NON-NLS-1$
				// version 2.28
				pageHeight = PADDING + Integer.parseInt(line.substring(line.lastIndexOf(",") + 1, line.lastIndexOf("\""))); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (line.matches("\\s*graph \\[bb=\"\\d+,\\d+,\\d+,\\d+\",")) { //$NON-NLS-1$
				// version 2.38
				line = line.substring(0, line.lastIndexOf(",")-1);
				pageHeight = PADDING + Integer.parseInt(line.substring(line.lastIndexOf(",") + 1)); //$NON-NLS-1$ //$NON-NLS-2$
			}else if (line.matches("\\s*subgraph " + AutoLayoutPreferences.CONTAINERPREFIX + "\\d+ \\{")) { // ex: //$NON-NLS-1$ //$NON-NLS-2$
				// subgraph
				// cluster_0
				// {

				IURNContainerRef contRef = URNElementFinder.findContainerRef(urndiagram, line.substring(
						line.indexOf(AutoLayoutPreferences.CONTAINERPREFIX) + AutoLayoutPreferences.CONTAINERPREFIX.length(), line.lastIndexOf('{')).trim());

				if (contRef == null)
					throw new Exception(Messages.getString("AutoLayoutWizard.cantFindCompRef") //$NON-NLS-1$
							+ line.substring(line.indexOf(AutoLayoutPreferences.CONTAINERPREFIX) + AutoLayoutPreferences.CONTAINERPREFIX.length(),
									line.lastIndexOf('{')).trim() + Messages.getString("AutoLayoutWizard.inMap")); //$NON-NLS-1$

				line = reader.readLine();
				if (line == null)
					break;
				// ex: graph [bb="0,0,192,212"];
				if (line.matches("\\s*graph \\[bb=\"?[0-9]*(.[0-9]+)?,?[0-9]*(.[0-9]+)?,?[0-9]*(.[0-9]+)?,?[0-9]*(.[0-9]+)?\"];")) { //$NON-NLS-1$
						String subline = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")); //$NON-NLS-1$ //$NON-NLS-2$
						String[] coords = subline.split(","); //$NON-NLS-1$
						// we've got lower left x, y, upper right x, y
						Command resize = new SetConstraintBoundContainerRefCompoundCommand(contRef, PADDING + (int)Math.round(Double.parseDouble( coords[0])), pageHeight
								- (int)Math.round(Double.parseDouble(coords[3])), (int)Math.round(Double.parseDouble(coords[2])) - (int)Math.round(Double.parseDouble(coords[0])), 
								(int)Math.round(Double.parseDouble(coords[3])) - (int)Math.round(Double.parseDouble(coords[1])) - 10);
						cmd.add(resize);
						if (contRef.getParent() != null) {
							SetConstraintContainerRefCommand cmd2 = 
									new SetConstraintContainerRefCommand(contRef, 
											PADDING + (int)Math.round(Double.parseDouble(coords[0])), 	// x position
											pageHeight - (int)Math.round(Double.parseDouble(coords[3]))+ 40, // y position
											(int)Math.round(Double.parseDouble(coords[2])) - (int)Math.round(Double.parseDouble(coords[0])), // width
											(int)Math.round(Double.parseDouble(coords[3])) - (int)Math.round(Double.parseDouble(coords[1])) - 40);	//height
							cmd.add(cmd2);
						}
				} else if (line.matches("\\s*graph \\[bb=\"\"];")) { // ex: //$NON-NLS-1$
					// graph
					// [bb=""];

					// don't know what to do with these.
					System.out.println("empty containerref. don't know what to do with it."); //$NON-NLS-1$
				}
			} else if (line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+ \\[pos=\"\\d+,\\d+\", width=\"?.+\"?, height=\"?.+\"?];") ||
					line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+ \\[height=\"?.+\"?, width=\"?.+\"?, pos=\"\\d+,\\d+\"];")) { //$NON-NLS-1$ //$NON-NLS-2$
				// ex: UrnNode5 [pos="76,122", width="1.22", height="0.50"];
				// for GraphViz v2.28
				line = line.trim();
				IURNNode pn = URNElementFinder.findNode(urndiagram, line.substring(AutoLayoutPreferences.URNODEPREFIX.length(), line.indexOf(" "))); //$NON-NLS-1$

				if (pn == null)
					throw new Exception(
							Messages.getString("AutoLayoutWizard.cantFindPathNode") + line.substring(AutoLayoutPreferences.URNODEPREFIX.length(), line.indexOf(" ")) //$NON-NLS-1$ //$NON-NLS-2$
							+ Messages.getString("AutoLayoutWizard.inMap")); //$NON-NLS-1$

				String subline;

				if (line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+ \\[pos=\"\\d+,\\d+\", width=\"?.+\"?, height=\"?.+\"?];")){
					subline = line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}else{
					subline = line.substring(line.indexOf("pos=\"") + 5, line.lastIndexOf("]")-1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				String[] coords = subline.split(","); //$NON-NLS-1$

				Command move = new SetConstraintCommand(pn, Integer.parseInt(coords[0]) + PADDING, pageHeight - Integer.parseInt(coords[1]));
				cmd.add(move);
			} else if ( line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+\t \\[height=\\d+\\.\\d+,") ||
					line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+\t \\[height=\\d+,") ||
						line.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+\\s* \\[height=[0-9]*.?[0-9],")) { //$NON-NLS-1$ //$NON-NLS-2$
				// updated for compatibility with GraphViz v2.38 
				// ex: UrnNode5	 [height=0.50,
				//		pos="7406,612",
				line = line.trim();
				IURNNode pn = URNElementFinder.findNode(urndiagram, line.substring(AutoLayoutPreferences.URNODEPREFIX.length(), line.indexOf("\t"))); //$NON-NLS-1$

				line = reader.readLine();
				line = line.trim();

				if (pn == null)
					throw new Exception(
							Messages.getString("AutoLayoutWizard.cantFindPathNode") + line.substring(AutoLayoutPreferences.URNODEPREFIX.length(), line.indexOf(" ")) //$NON-NLS-1$ //$NON-NLS-2$
							+ Messages.getString("AutoLayoutWizard.inMap")); //$NON-NLS-1$

				String subline = line.substring(line.indexOf("pos=\"") + 5, line.lastIndexOf(",")-1); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				//old way is ->> //String subline = line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				String[] coords = subline.split(","); //$NON-NLS-1$
				Command move = new SetConstraintCommand(pn, (int)Math.round(Double.parseDouble(coords[0])) + PADDING, pageHeight - (int)Math.round(Double.parseDouble(coords[1])));
				cmd.add(move);
			}else if (line
					.matches("\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+\\s*->\\s*" + AutoLayoutPreferences.URNODEPREFIX + "\\d+ \\[pos=\"e,(\\d+,\\d+\\s+)*\\d+,\\d+\"];")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				// ex: UrnNode50 -> UrnNode34 [pos="e,436,488 436,524 436,516
				// 436,507 436,498"];

				if (urndiagram instanceof UCMmap && AutoLayoutPreferences.getEmptyPoints()) {
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

					String sSource = line.substring(line.indexOf(AutoLayoutPreferences.URNODEPREFIX) + AutoLayoutPreferences.URNODEPREFIX.length(),
							line.indexOf("-")).trim(); //$NON-NLS-1$
							String sTarget = line.substring(
									line.indexOf(AutoLayoutPreferences.URNODEPREFIX, line.indexOf(">")) + AutoLayoutPreferences.URNODEPREFIX.length(), //$NON-NLS-1$
									line.indexOf("[", line.indexOf(AutoLayoutPreferences.URNODEPREFIX, line.indexOf(">")))).trim(); //$NON-NLS-1$ //$NON-NLS-2$

							IURNConnection link = URNElementFinder.findConnection(urndiagram, sSource, sTarget);

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

								for (int i = sCoords.length - 4; i >= 0; i -= 2) {

									if (i == sCoords.length - 2 || (Math.abs(avg - distances[i / 2]) > 0.97 * stdDev && distances[i / 2] >= 30)) {
										PathNode empty = (PathNode) ModelCreationFactory.getNewObject(urndiagram.getUrndefinition().getUrnspec(), EmptyPoint.class);
										Command addEmpty = new SplitLinkCommand((UCMmap) urndiagram, empty, (NodeConnection) link, Integer.parseInt(sCoords[i]), pageHeight
												- Integer.parseInt(sCoords[i + 1]));
										if (addEmpty.canExecute()) {
											cmd.add(addEmpty);
										}
									}

								}
				}

			}
		}
		// bug 304: Sort commands, putting container ref moves before node moves.
		Collections.sort(cmd.getCommands(), new AutoLayoutCommandComparator());
		return cmd;
	}
}