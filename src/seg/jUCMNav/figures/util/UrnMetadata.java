package seg.jUCMNav.figures.util;

import grl.IntentionalElement;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;

import ucm.map.RespRef;
import urn.URNlink;
import urncore.Component;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Utility class to handle URN metadata, including stereotypes.
 * 
 * @author Daniel Amyot
 */
public class UrnMetadata {

	// prefix used in metadata names to identify stereotypes 
	public static final String STEREOTYPE_PREFIX = "ST";
	// Metadata indicator added to text labels
	public static final String METADATA_PRESENCE = " ¶";


	/**
	 * Checks whether a metadata of the URN element has a specific value for the given name.
	 */
	public static boolean checkPresence(EObject object, String mname, String mvalue) {

		URNmodelElement elem = (URNmodelElement)object;
		Iterator it = elem.getMetadata().iterator();
		boolean result = false;
		
		while (it.hasNext() && !result)
		{
			Metadata metadata = (Metadata) it.next();
			result = (metadata.getName()==mname && metadata.getValue()==mvalue);
		}

		return result;
	}

	
	/**
	 * Checks whether there are metadata associated with the element
	 * Adds those that are stereotypes, and indicates the presence of others.
	 */
	public static String getStereotypes(EObject object) {

		URNmodelElement elem = (URNmodelElement)object;
		String stereotypes = ""; // List of stereotypes
		boolean otherMetadataTypes = false; // Indicates whether there are metadata elements that are not stereotypes 
		Iterator it = elem.getMetadata().iterator();

		while (it.hasNext())
		{
			Metadata metadata = (Metadata) it.next();
			String name = metadata.getName();

			if (name.toUpperCase().startsWith(STEREOTYPE_PREFIX))
			{
				// Could be added to extract the kind of stereotype, but takes too much real estate.
				// name = name.substring(STEREOTYPE_PREFIX.length()); 
				stereotypes = stereotypes + " «" + metadata.getValue() + "»";  //$NON-NLS-1$  //$NON-NLS-2$
			}
			else
				otherMetadataTypes = true;
		}

		if (otherMetadataTypes)
			stereotypes = stereotypes + METADATA_PRESENCE;  //$NON-NLS-1$
		return stereotypes;
	}

	/**
	 * Removes stereotype names and metadata indicators from a string.
	 */
	public static String removeStereotypes(String text) {
		String name = text;

		// Removes importance
		int sub = name.indexOf("  ("); //$NON-NLS-1$
		if (sub>-1)
			name = name.substring(0,sub);
		else
		{
			// Removes stereotypes at the end of the name
			sub = name.indexOf(" «"); //$NON-NLS-1$
			if (sub>-1)
				name = name.substring(0,sub);
			else
			{
				// Removes ¶ at the end of the name
				sub = name.indexOf(METADATA_PRESENCE); //$NON-NLS-1$
				if (sub>-1)
					name = name.substring(0,sub);
			}
		}
		return name;
	}


	/**
	 * Set the figure tool tip with the description, metadata (other than stereotypes), 
	 * and links of the URN model element.
	 */
	public static void setToolTip(URNmodelElement elem, IFigure fig)
	{
		String toolTipText = "";   //$NON-NLS-1$
		String metadataText = "";  //$NON-NLS-1$
		boolean descOnly = false; // Indicates if only the description is in the text so far.

		if (elem instanceof RespRef){
			elem = ((RespRef)elem).getRespDef();
		}

		if (elem.getDescription() != null) {
			toolTipText = toolTipText + " " + elem.getDescription() + " "; //$NON-NLS-1$ $NON-NLS-2$  
			descOnly = true;
		}

		// Consider only non-stereotype metadata.
		Iterator it=elem.getMetadata().iterator();
		while (it.hasNext()){
			Metadata metadata = (Metadata) it.next();
			if (!metadata.getName().toUpperCase().startsWith(STEREOTYPE_PREFIX)) {
				metadataText = metadataText + "\n    " + metadata.getName() + "=" +  metadata.getValue();  //$NON-NLS-1$  $NON-NLS-2$
			}
		}

		if (!metadataText.equals("")) {   //$NON-NLS-1$
			if (descOnly) {
				toolTipText = toolTipText + "\n\n";    //$NON-NLS-1$
			}
			toolTipText = toolTipText + " METADATA:" + metadataText;    //$NON-NLS-1$
			descOnly = false;
		}

		if (elem.getFromLinks().size() + elem.getToLinks().size() >0) {
			if (!toolTipText.equals("")) {   //$NON-NLS-1$
				toolTipText = toolTipText.concat("\n");    //$NON-NLS-1$
			}
			if (descOnly) {   //$NON-NLS-1$
				toolTipText = toolTipText.concat("\n");    //$NON-NLS-1$
			}
			descOnly = false;			
			toolTipText = toolTipText + " URN LINKS:";    //$NON-NLS-1$

			it=elem.getFromLinks().iterator();
			while (it.hasNext()){
				String classname;
				URNlink link = (URNlink) it.next();				
				if (link.getToElem() instanceof IntentionalElement) {
					classname = ((IntentionalElement)link.getToElem()).getType().getName();
				} 
				else if (link.getToElem() instanceof Component) {
					classname = ((Component)link.getToElem()).getKind().getName();
				} 
				else {
					classname = link.getToElem().getClass().toString();
					classname = classname.substring(classname.lastIndexOf(".")+1, classname.length()-4);
				}
				toolTipText = toolTipText + "\n    to " + link.getToElem().getName() + " (" + classname + ")";    //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
			}
			it=elem.getToLinks().iterator();
			while (it.hasNext()){
				String classname;
				URNlink link = (URNlink) it.next();
				if (link.getFromElem() instanceof IntentionalElement) {
					classname = ((IntentionalElement)link.getFromElem()).getType().getName();
				} 
				else if (link.getFromElem() instanceof Component) {
					classname = ((Component)link.getToElem()).getKind().getName();
				} 
				else {
					classname = link.getFromElem().getClass().toString();
					classname = classname.substring(classname.lastIndexOf(".")+1, classname.length()-4);
				}
				toolTipText = toolTipText + "\n    from " + link.getFromElem().getName()+ " (" + classname + ")";    //$NON-NLS-1$  $NON-NLS-2$ $NON-NLS-3$
			}
		}

		if (toolTipText.equals("")) {   //$NON-NLS-1$
			fig.setToolTip(null);
		}
		else {
			fig.setToolTip(new Label (toolTipText));
		}
	}
}
