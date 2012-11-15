package seg.jUCMNav.figures.util;

import grl.IntentionalElement;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.map.FailurePoint;
import ucm.map.RespRef;
import urn.URNlink;
import urncore.Component;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Utility class to handle URN metadata, including stereotypes.
 * 
 * @author Daniel Amyot
 */
public class UrnMetadata {

    /**
     * Prefix used in metadata names to identify stereotypes
     */
    public static final String STEREOTYPE_PREFIX = "ST"; //$NON-NLS-1$
    /**
     * Metadata indicator added to text labels
     */
    public static final String METADATA_PRESENCE = " \u00B6"; //$NON-NLS-1$
    public static final String METADATA_PRESENCE_NOSPACE = "\u00B6"; //$NON-NLS-1$
    public static final String STEREOTYPE_OPEN = " \u00AB"; //$NON-NLS-1$
    public static final String STEREOTYPE_CLOSE = "\u00BB"; //$NON-NLS-1$

    /**
     * Checks whether a metadata of the URN element has a specific value for the given name.
     */
    public static boolean checkPresence(EObject object, String mname, String mvalue) {

        URNmodelElement elem = (URNmodelElement) object;
        Iterator it = elem.getMetadata().iterator();
        boolean result = false;

        while (it.hasNext() && !result) {
            Metadata metadata = (Metadata) it.next();
            result = (metadata.getName() == mname && metadata.getValue() == mvalue);
        }

        return result;
    }

    /**
     * Checks whether there are metadata associated with the element Adds those that are stereotypes, and indicates the presence of others.
     */
    public static String getStereotypes(EObject object) {

        URNmodelElement elem = (URNmodelElement) object;
        String stereotypes = ""; //$NON-NLS-1$ // List of stereotypes
        boolean otherMetadataTypes = false; // Indicates whether there are metadata elements that are not stereotypes
        Iterator it = elem.getMetadata().iterator();

        while (it.hasNext()) {
            Metadata metadata = (Metadata) it.next();
            String name = metadata.getName();

            if (name.toUpperCase().startsWith(STEREOTYPE_PREFIX)) {
                // Could be added to extract the kind of stereotype, but takes too much real estate.
                stereotypes = stereotypes + STEREOTYPE_OPEN + metadata.getValue() + STEREOTYPE_CLOSE; 
            } else if (!MetadataHelper.isRuntimeMetadata(name))
                otherMetadataTypes = true;
        }

        if (otherMetadataTypes && GeneralPreferencePage.getMetadataIndVisible())
            stereotypes = stereotypes + METADATA_PRESENCE;
        return stereotypes;
    }

    public static String getAllStereotypes( URNmodelElement reference, URNmodelElement parent ) {

    	StringBuilder sb = new StringBuilder();
    	
    	if( UrnMetadata.getElementStereotypes( reference, sb) || UrnMetadata.getElementStereotypes( parent, sb) ) {
    		sb.append( METADATA_PRESENCE );
    	}
    	
    	return sb.toString();
    }
    
    private static boolean getElementStereotypes( URNmodelElement element, StringBuilder sb ) {
        boolean otherMetadataTypes = false; // Indicates whether there are metadata elements that are not stereotypes
 	
    	for( Iterator it = element.getMetadata().iterator(); it.hasNext(); ) {
            Metadata metadata = (Metadata) it.next();
            String name = metadata.getName();

            if (name.toUpperCase().startsWith(STEREOTYPE_PREFIX)) {
                sb.append( STEREOTYPE_OPEN + metadata.getValue() + STEREOTYPE_CLOSE ); 
            } else if (!MetadataHelper.isRuntimeMetadata(name)) {
                otherMetadataTypes = true;
            }
    	}
	
        return (otherMetadataTypes && GeneralPreferencePage.getMetadataIndVisible());
    }    
    
    /**
     * Removes stereotype names and metadata indicators from a string.
     */
    public static String removeStereotypes(String text) {
        String name = text;

        // Removes importance
        int sub = name.indexOf("  ("); //$NON-NLS-1$
        if (sub > -1)
            name = name.substring(0, sub);
        else {
            // Removes stereotypes at the end of the name
            sub = name.indexOf(STEREOTYPE_CLOSE); //$NON-NLS-1$
            if (sub > -1)
                name = name.substring(0, sub);
            else {
                // Removes paragraph mark at the end of the name
                sub = name.indexOf(METADATA_PRESENCE);
                if (sub > -1)
                    name = name.substring(0, sub);
            }
        }
        return name;
    }

    /**
     * Set the figure tool tip with the description, metadata (other than stereotypes), and links of the URN model element.
     */
    public static void setToolTip(URNmodelElement elem, IFigure fig) {
        URNmodelElement elemOrig = elem;

        if (elem == null || fig == null)
            return;
        String toolTipText = ""; //$NON-NLS-1$
        String metadataText = ""; //$NON-NLS-1$
        boolean descOnly = false; // Indicates if only the description is in the text so far.

        if (elem instanceof RespRef) {
            elem = ((RespRef) elem).getRespDef();
        }
        if (elem == null)
            return;

        if (elem.getDescription() != null && !elem.getDescription().equals("")) { //$NON-NLS-1$
            toolTipText = toolTipText + " " + elem.getDescription() + " "; //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$  
            descOnly = true;
        }

        if (elem instanceof Responsibility && ((Responsibility) elem).getExpression() != null) {
            String exp = ((Responsibility) elem).getExpression();
            if (exp.length() != 0) {
                if (!toolTipText.equals("")) { //$NON-NLS-1$
                    toolTipText += "\n\n"; //$NON-NLS-1$
                }
                toolTipText += " CODE:\n    " + exp; //$NON-NLS-1$
                descOnly = true;
            }
        } else if (elem instanceof FailurePoint && ((FailurePoint) elem).getExpression() != null) {
            String exp = ((FailurePoint) elem).getExpression();
            if (exp.length() != 0) {
                if (!toolTipText.equals("")) { //$NON-NLS-1$
                    toolTipText += "\n\n"; //$NON-NLS-1$
                }
                toolTipText += " CODE:\n    " + exp; //$NON-NLS-1$
                descOnly = true;
            }
        }

        // Consider only non-stereotype metadata and remove AltName and AltDescription.
        Iterator it = elem.getMetadata().iterator();
        while (it.hasNext()) {
            Metadata metadata = (Metadata) it.next();
            if ( UrnMetadata.isMetadataMeaningful( metadata.getName() )) {
                metadataText = metadataText + "\n    " + metadata.getName() + "=" + metadata.getValue() + " "; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
            }
        }

        // Consider run-time metadata for resp. references
        if (elemOrig instanceof RespRef) {
            it = elemOrig.getMetadata().iterator();
            while (it.hasNext()) {
                Metadata metadata = (Metadata) it.next();
                if (!metadata.getName().toUpperCase().startsWith(STEREOTYPE_PREFIX)) {
                    metadataText = metadataText + "\n    " + metadata.getName() + "=" + metadata.getValue() + " "; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$
                }
            }
        }

        if (!metadataText.equals("")) { //$NON-NLS-1$
            if (descOnly) {
                toolTipText = toolTipText + "\n\n"; //$NON-NLS-1$
            }
            toolTipText = toolTipText + " METADATA: " + metadataText; //$NON-NLS-1$
            descOnly = false;
        }

        if (elem.getFromLinks().size() + elem.getToLinks().size() > 0) {
            if (!toolTipText.equals("")) { //$NON-NLS-1$
                toolTipText = toolTipText.concat("\n"); //$NON-NLS-1$
            }
            if (descOnly) { //$NON-NLS-1$
                toolTipText = toolTipText.concat("\n"); //$NON-NLS-1$
            }
            descOnly = false;
            toolTipText = toolTipText + " URN LINKS: "; //$NON-NLS-1$

            it = elem.getFromLinks().iterator();
            while (it.hasNext()) {
                String classname;
                URNlink link = (URNlink) it.next();
                if (link.getToElem() != null) { // Needed for link creation with partial refresh...
                    if (link.getToElem() instanceof IntentionalElement) {
                        classname = ((IntentionalElement) link.getToElem()).getType().getName();
                    } else if (link.getToElem() instanceof Component) {
                        classname = ((Component) link.getToElem()).getKind().getName();
                    } else {
                        classname = link.getToElem().getClass().toString();
                        classname = classname.substring(classname.lastIndexOf(".") + 1, classname.length() - 4); //$NON-NLS-1$
                    }
                    toolTipText = toolTipText + "\n   " + link.getType() + Messages.getString("UrnMetadata_To") + link.getToElem().getName() + " (" + classname + ") "; //$NON-NLS-1$ $NON-NLS-2$ $NON-NLS-3$ $NON-NLS-4$
                }
            }
            it = elem.getToLinks().iterator();
            while (it.hasNext()) {
                String classname;
                URNlink link = (URNlink) it.next();
                if (link.getFromElem() instanceof IntentionalElement) {
                    classname = ((IntentionalElement) link.getFromElem()).getType().getName();
                } else if (link.getFromElem() instanceof Component) {
                    classname = ((Component) link.getToElem()).getKind().getName();
                } else {
                    classname = link.getFromElem().getClass().toString();
                    classname = classname.substring(classname.lastIndexOf(".") + 1, classname.length() - 4); //$NON-NLS-1$
                }
                toolTipText = toolTipText + "\n   " + link.getType() + Messages.getString("UrnMetadata_From") + link.getFromElem().getName() + " (" + classname + ") "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ $NON-NLS-2$ $NON-NLS-3$ $NON-NLS-4$
            }
        }

        if (toolTipText.equals("")) { //$NON-NLS-1$
            fig.setToolTip(null);
        } else {
            fig.setToolTip(new Label(toolTipText));
        }
    }
    
    private static boolean isMetadataMeaningful( String name ) {
    	
    	if( name.toUpperCase().startsWith(STEREOTYPE_PREFIX) )
    		return false;
    	else if( name.contentEquals( "AltName" ) || name.contentEquals( "AltDescription" ) ) //$NON-NLS-1$ $NON-NLS-2$
    		return false;
    	
    	return true;
    }
}
