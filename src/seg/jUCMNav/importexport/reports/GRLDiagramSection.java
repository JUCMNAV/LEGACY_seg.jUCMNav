package seg.jUCMNav.importexport.reports;

import grl.Belief;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

import com.lowagie.text.Document;

/**
*
* @author Andrew Miga
*/

public class GRLDiagramSection extends PDFReportDiagram {
	
	public GRLDiagramSection() {
		
	}

	public void createGRLDiagramDescription( Document document, URNmodelElement element, IURNDiagram diagram )
	{	
		try {		
			outputGRLIntentionalElements( document, diagram );
			outputGRLBeliefs( document, diagram );
			outputGRL_URNLinks( document, diagram );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void outputGRLBeliefs( Document document, IURNDiagram diagram )
	{
		boolean hasData = false;
		
		if ( !ReportGeneratorPreferences.getGRLShowBeliefs() )
			return;
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if ( currentElement instanceof Belief )
				hasData = hasGRLBeliefData( (Belief) currentElement );
		}
		
		if ( !hasData )
			return;
		
		insertDiagramSectionHeader( document, tableParams, "Beliefs" );

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if ( currentElement instanceof Belief ) {
				Belief currentBelief = (Belief) currentElement;
				if ( hasGRLBeliefData( currentBelief ) ) {
					ReportUtils.writeLineWithSeparator( document, currentBelief.getName(), ": ", notNull( currentBelief.getDescription() ), descriptionFont, true );
					insertMetadata( document, currentBelief.getMetadata() );
				}
			}
		}
	}
	
	private boolean hasGRLBeliefData( Belief belief )
	{
		return( ReportUtils.notEmpty( belief.getDescription() ) || !belief.getMetadata().isEmpty() );
	}

	private void outputGRLIntentionalElements( Document document, IURNDiagram diagram )
	{
		boolean hasData = false;
		
		if ( !ReportGeneratorPreferences.getGRLShowIntentionalElements() )
			return;

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasData;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if ( currentElement instanceof IntentionalElementRef ) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
				hasData = hasGRLIntentionalElementData( ie );
			}
		}
		
		if ( !hasData )
			return;
		
		insertDiagramSectionHeader( document, tableParams, "Intentional Elements" );

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if ( currentElement instanceof IntentionalElementRef ) {
				IntentionalElement ie = ((IntentionalElementRef) currentElement).getDef();
				if ( hasGRLIntentionalElementData( ie ) ) {
					ReportUtils.writeLineWithSeparator( document, ie.getName(), ": ", notNull( ie.getDescription() ), descriptionFont, true );
					insertMetadata( document, ie.getMetadata() );
				}
			}
		}
	}
	
	private boolean hasGRLIntentionalElementData( IntentionalElement ie )
	{
		return( ReportUtils.notEmpty( ie.getDescription() ) || !ie.getMetadata().isEmpty() );
	}

	private void outputGRL_URNLinks( Document document, IURNDiagram diagram )
	{
		if ( !ReportGeneratorPreferences.getGRLShowURNLinks() )
			return;

		
	}
	
}