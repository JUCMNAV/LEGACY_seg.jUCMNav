package seg.jUCMNav.actions;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.util.Iterator;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;
import urn.impl.URNlinkImpl;
import urn.util.TagURNElements;
import urncore.Component;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * Adds or removes stereotype tags to/from URN model elements.
 * 
 * @author amiga
 */

public class TagElementAction extends URNSelectionAction {

    public static final String TAG_ELEMENT_ACTION = "seg.jUCMNav.TagElementAction"; //$NON-NLS-1$

    private URNmodelElement element;
    private URNspec urnspec;
	
	public TagElementAction(IWorkbenchPart part) {
		super(part);
		setId(TAG_ELEMENT_ACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Metadata.gif")); //$NON-NLS-1$
	}

    protected boolean calculateEnabled() {

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        element = sel.getURNmodelElement();
        if (element == null) {
            return false;
        }
        
		urnspec = this.getURNspec( element );
		String className = this.className( URNlinkImpl.getParentElement( element ));
		
		if(  JUCMNavPlugin.isInDebug() ) {
			if( urnspec == null  ){
				System.out.println( "No urnspec for element name: " + element.getName());
			} else {
				System.out.println( "urnspec.getMetadata().size() = " + urnspec.getMetadata().size() );
			}
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

        return false;
    }
    
    public void run() {
    	if( element == null || urnspec == null )
    		return; // sanity check

    	TagURNElements te = new TagURNElements();
    	te.tagElement( getCommandStack(), element, urnspec );
    }

	private String className( URNmodelElement element )
	{
	    String className = element.getClass().getSimpleName();
	    return className.substring( 0, className.length()-4 );  // strip suffix 'Impl' from class name
	}
	
	private URNspec getURNspec( URNmodelElement element )
	{
		URNspec urnspec = null;
		
        if (element instanceof IntentionalElement) {
            urnspec = ((IntentionalElement) element).getGrlspec().getUrnspec();
        } else if (element instanceof Actor) {
            urnspec = ((Actor) element).getGrlspec().getUrnspec();
        } else if( element instanceof IURNNode ){ // handles UCM, GRL Nodes
        	urnspec = ((IURNNode) element).getDiagram().getUrndefinition().getUrnspec();
        } else if(  element instanceof IURNContainerRef ){ // handles ActorRef, ComponentRef
        	urnspec = ((IURNContainerRef) element).getDiagram().getUrndefinition().getUrnspec();
        } else if( element instanceof ElementLink ){
        	urnspec = ((ElementLink) element).getGrlspec().getUrnspec();
        } else if( element instanceof Responsibility ){
        	urnspec = ((Responsibility) element).getUrndefinition().getUrnspec();
        }  else if( element instanceof Component ){
        	urnspec = ((Component) element).getUrndefinition().getUrnspec();
        }  else if (element instanceof EvaluationStrategy) {
            urnspec = ((EvaluationStrategy) element).getGroup().getGrlspec().getUrnspec();
        } else if (element instanceof StrategiesGroup) {
            urnspec = ((StrategiesGroup) element).getGrlspec().getUrnspec();
        }
        
        return urnspec;
	}
}
