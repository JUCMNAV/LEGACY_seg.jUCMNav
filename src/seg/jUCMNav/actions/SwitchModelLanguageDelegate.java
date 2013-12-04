package seg.jUCMNav.actions;

import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.IntentionalElementRef;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.MetadataHelper;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.RespRef;
import ucm.scenario.EnumerationType;
import ucm.scenario.Variable;
import ucmscenarios.Condition;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

public class SwitchModelLanguageDelegate implements IEditorActionDelegate {

    private UCMNavMultiPageEditor editor;
    private URNspec urnSpec;
    
    private Class [] excludedClasses = { Variable.class, EnumerationType.class, Decomposition.class, Contribution.class, IntentionalElementRef.class, ActorRef.class,
    		RespRef.class, DirectionArrow.class, ComponentRef.class, Condition.class, Dependency.class }; 
    
    
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
    	// DB: avoid class cast exception with e4.2
    	if ( targetEditor instanceof UCMNavMultiPageEditor ) {
    		editor = (UCMNavMultiPageEditor) targetEditor;
    	}
        //editor = (UCMNavMultiPageEditor) targetEditor;
    }

	public void run(IAction action) {
		this.switchModelLanguage( editor.getModel() );
	}

	private void switchModelLanguage( URNspec urnspec )
	{	
		this.urnSpec = urnspec;
		
		TreeIterator tree = EcoreUtil.getAllContents( urnSpec, false );
		
//		int i = 0, j = 0, k = 0;
		
		while( tree.hasNext() ) {
			Object obj;
//			k++;
			if( (obj = tree.next()) instanceof URNmodelElement ) {
				URNmodelElement element = (URNmodelElement) obj;
//				j++;
				
				if( !this.includesClass(element, excludedClasses)) {
					this.switchElementLanguage(element);
//					System.out.println( i++ + ": class: " + element.getClass().getSimpleName() + " name: " + element.getName() );
				}
			}
		}
//		System.out.println( "Total # elements = " + k + " Total # of URNmodelElement = " + j + " # named elements = " + i );
	}
	
	private void switchElementLanguage( URNmodelElement element )
	{
		String altName = null, altDescription = null;
		
		if( (altName = MetadataHelper.getMetaData(element, "AltName")) == null ) { //$NON-NLS-1$
			MetadataHelper.addMetaData( urnSpec, element, "AltName", element.getName() ); // create new initial AltName metadata //$NON-NLS-1$
		} else { // switch the strings in AltName and element.name
			String temp = element.getName();
			element.setName(altName);
			Metadata mdata = MetadataHelper.getMetaDataObj( element, "AltName"); //$NON-NLS-1$
			mdata.setValue(temp);
		}
		
		if( (altDescription = MetadataHelper.getMetaData(element, "AltDescription")) == null ) { //$NON-NLS-1$
			MetadataHelper.addMetaData( urnSpec, element, "AltDescription", element.getDescription() ); // create new initial AltDescription metadata //$NON-NLS-1$
		} else { // switch the strings in AltDescription and element.description
			String temp = element.getDescription();
			element.setDescription(altDescription);
			Metadata mdata = MetadataHelper.getMetaDataObj( element, "AltDescription"); //$NON-NLS-1$
			mdata.setValue(temp);
		}		
	}
	
	private boolean includesClass( URNmodelElement element, Class [] classList )
	{
		for( int i = 0; i < classList.length; i++ ) {
			if( classList[i].isAssignableFrom( element.getClass() ) )
				return true;
		}
		
		return false;
	}

	public void selectionChanged(IAction action, ISelection selection) {
		 // we don't depend on the selection.
	}
}
