package seg.jUCMNav.actions;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.util.Iterator;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.metadata.TagURNElements;
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
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
    
    private URNspec urnElement;
    
    private URNmodelElement element;
    private URNspec urnspec;
	
	public TagElementAction(IWorkbenchPart part) {
		super(part);
		setId(TAG_ELEMENT_ACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Metadata.gif")); //$NON-NLS-1$
	}

    protected boolean calculateEnabled() {

    	boolean hasParent = false;
    	URNmodelElement parentElement = null;
    	String parentClassName = null;
    	
    	URNspec urnParentElement = null;
    	
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        element = sel.getURNmodelElement();
        urnElement = sel.getUrnspec();
        
        if (element == null /* && urnElement == null */) {
            return false;
        }
        
        // if the selection is a UrnModelElement
        if ( element != null){
		urnspec = this.getURNspec( element );
		String className = this.className( element );
		parentElement = URNElementFinder.getParentElement( element );
		
		if( element != parentElement ) {
			hasParent = true;
			parentClassName = this.className( parentElement );
		}
		
    	if( (urnspec != null) && urnspec.getMetadata().size() > 0 ) {
    		for( Iterator iter = urnspec.getMetadata().iterator(); iter.hasNext();) {
    			Metadata md = (Metadata) iter.next();	
    			if(md.getName().equalsIgnoreCase( "StereotypeDef" )){ //$NON-NLS-1$
    				String tagClassName = this.getTagClassName( md.getValue() );
    				if( tagClassName.equals(className) ) { // temporary using strings, need comparison using instanceof
    					return true;
    				}
    				else if( hasParent && tagClassName.equals(parentClassName) ) {
    					return true;
    				}
    			}
    		}    		
    	}
        
        }
            return false;
        
    }
    
	private String getTagClassName( String value ) {
		return( value.substring( value.lastIndexOf(',')+1 ));
	}

    public void run() {
    	if( element == null || urnspec == null )
    		return; // sanity check

    	TagURNElements te = new TagURNElements();
    	te.tagElement( getCommandStack(), element, urnspec );
    }

    private String className( URNspec urnElement){
    	String className = urnElement.getClass().getSimpleName();
    	return className.substring( 0, className.length()-4 );  // strip suffix 'Impl' from class name
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
