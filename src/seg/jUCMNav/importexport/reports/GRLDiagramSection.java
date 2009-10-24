package seg.jUCMNav.importexport.reports;

import grl.Belief;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.Iterator;

import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

import com.lowagie.text.Document;

public class GRLDiagramSection extends PDFReportDiagram {
	
	public GRLDiagramSection() {
		
	}

	public void createGRLDiagramDescription( Document document, URNmodelElement element, IURNDiagram diagram ) {
		
		//System.out.println( "In GRLDiagram Section name: \"" + element.getName() + "\" #nodes: " + diagram.getNodes().size() + "\n" );
		
		try {		
			outputGRLIntentionalElements( document, diagram );
			outputGRLBeliefs( document, diagram );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void outputGRLBeliefs( Document document, IURNDiagram diagram )
	{
		boolean hasData = false;
		
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

}