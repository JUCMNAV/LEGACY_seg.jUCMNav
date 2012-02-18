package urn.util;

import org.eclipse.gef.commands.CommandStack;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;
import urncore.URNmodelElement;

public class TagURNElements {

	public void tagElement( CommandStack cmdStack, URNmodelElement element )
	{
		
		if( JUCMNavPlugin.isInDebug() ) System.out.println( "Tag Element ... selected for element: " + element.getName());

		URNspec urnspec = EditURNLink.getURNspec( element );
		
//		if( ) {
//			
//			
//		}
	}
	
}
