package seg.jUCMNav.actions;

import java.util.Iterator;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;
import urn.util.EditURNLink;
import urn.util.TagURNElements;
import urncore.Metadata;
import urncore.URNmodelElement;

public class TagElementAction extends URNSelectionAction {

    public static final String TAG_ELEMENT_ACTION = "seg.jUCMNav.TagElementAction"; //$NON-NLS-1$

    private URNmodelElement element;

	
	
	
	public TagElementAction(IWorkbenchPart part) {
		super(part);
		setId(TAG_ELEMENT_ACTION);
	}

    protected boolean calculateEnabled() {
        boolean enable = false;

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        element = sel.getURNmodelElement();
        if (element == null) {
            return false;
        }
        
		URNspec urnspec = EditURNLink.getURNspec( element );
		String className = EditURNLink.className( element );
		
		if( urnspec == null && JUCMNavPlugin.isInDebug() ){
			System.out.println( "No urnspec for element name: " + element.getName());
		}
		
		
    	if( (urnspec != null) && urnspec.getMetadata().size() > 0 ) {
    		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
    			Metadata md = (Metadata) iter.next();	
    			if(md.getName().equalsIgnoreCase( "StereotypeDef" )){
    				if( md.getValue().contains(className))
    					return true;
    			}
    		}    		
    	}

        return enable;
    }
    
    public void run() {
    	if( element == null )
    		return; // sanity check

    	TagURNElements te = new TagURNElements();
    	te.tagElement( getCommandStack(), element );
    }

}
