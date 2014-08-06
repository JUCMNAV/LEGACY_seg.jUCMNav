package seg.jUCMNav.importexport;

import grl.Actor;
import grl.BeliefLink;
import grl.LinkRef;

import java.io.FileOutputStream;

import org.eclipse.draw2d.IFigure;

import seg.jUCMNav.extensionpoints.IUseCaseMapExport;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.preferences.AutoLayoutPreferences;
import ucm.map.UCMmap;
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
	 * Recursive method that builds a DOT cluster using the ContainerRef relationship.
	 * 
	 * @param contRef
	 *            the parent
	 * @param dot
	 *            where to write the output.
	 */
	private static void buildCluster(IURNContainerRef contRef, StringBuffer dot) {

		dot.append("subgraph " + AutoLayoutPreferences.CONTAINERPREFIX + ((URNmodelElement) contRef).getId() + " {\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
		dot.append("CheapTrick" + id++ + " [pos=\"\", width=\""+ contRef.getWidth()/72.0 +"\", height=\""+ contRef.getHeight()/72.0 +"\"];\n"); //$NON-NLS-1$ //$NON-NLS-2$

		IURNContainerRef child;
		for (int i = 0; i < contRef.getChildren().size(); i++) {
			child = (IURNContainerRef) contRef.getChildren().get(i);
			buildCluster(child, dot);
		}
		for (int i = 0; i < contRef.getNodes().size(); i++) {
			URNmodelElement node = (URNmodelElement) contRef.getNodes().get(i);

			dot.append(AutoLayoutPreferences.URNODEPREFIX + node.getId() + " ;\n"); //$NON-NLS-1$
		}

		dot.append("} \n"); //$NON-NLS-1$
	}

	/**
	 * Returns a string representation of a Graphviz dot file format which includes the layout information for our use case map.
	 * 
	 * @param diagram
	 *            the map to be converted to Graphviz dot file format.
	 */
	public static String convertURNToDot(IURNDiagram diagram) {
		int i;
		StringBuffer dot = new StringBuffer();
		String rankdir = AutoLayoutPreferences.getOrientation();
		String size = AutoLayoutPreferences.getWidth() + "," + AutoLayoutPreferences.getHeight(); //$NON-NLS-1$

		if (!(diagram instanceof UCMmap)) {
			dot.append("digraph " + AutoLayoutPreferences.DIAGPREFIX + ((URNmodelElement) diagram).getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\nranksep=\"1.0\";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} else {
			dot.append("digraph " + AutoLayoutPreferences.DIAGPREFIX + ((URNmodelElement) diagram).getId() + " {\nrankdir=\"" + rankdir + "\";\nsize=\"" + size + "\";\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$            
		}
		for (i = 0; i < diagram.getContRefs().size(); i++) {
			IURNContainerRef contRef = (IURNContainerRef) diagram.getContRefs().get(i);
			// we only want root components/actors
			if (contRef.getParent() == null) {
				buildCluster(contRef, dot);
			}
		}

		for (i = 0; i < diagram.getNodes().size(); i++) {
			IURNNode node = (IURNNode) diagram.getNodes().get(i);
			// we only want loose nodes containers
			if (node.getContRef() == null) {

				double height = 0.0;
				double width = 0.0;

				if ( MetadataHelper.getMetaData((URNmodelElement)node, "_height") != null && 
						MetadataHelper.getMetaData((URNmodelElement)node, "_width") != null ){
					height = Double.valueOf(MetadataHelper.getMetaData((URNmodelElement)node, "_height"));
					width = Double.valueOf(MetadataHelper.getMetaData((URNmodelElement)node, "_width"));
				}

				dot.append(AutoLayoutPreferences.URNODEPREFIX + ((URNmodelElement) node).getId() + 
						"[height=\"" + height/72.0 + "\", width=\"" + width/72.0 + "\"];\n"); //$NON-NLS-1$
			}
		}

		for (i = 0; i < diagram.getConnections().size(); i++) {
			IURNConnection conn = (IURNConnection) diagram.getConnections().get(i);
			if (conn instanceof LinkRef || conn instanceof BeliefLink) {
				dot.append(AutoLayoutPreferences.URNODEPREFIX + ((URNmodelElement) conn.getTarget()).getId()
						+ "->" + AutoLayoutPreferences.URNODEPREFIX + ((URNmodelElement) conn.getSource()).getId() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				dot.append(AutoLayoutPreferences.URNODEPREFIX + ((URNmodelElement) conn.getSource()).getId()
						+ "->" + AutoLayoutPreferences.URNODEPREFIX + ((URNmodelElement) conn.getTarget()).getId() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
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
		String contents = convertURNToDot(diagram);
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
