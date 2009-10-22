package seg.jUCMNav.importexport.reports;

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
		
		outputDescriptions( document, diagram );
		
		try {
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void outputDescriptions( Document document, IURNDiagram diagram )
	{
		boolean hasDescriptions = false;
		
		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext() && !hasDescriptions;) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if( ReportUtils.notEmpty( currentElement.getDescription() ) )
				hasDescriptions = true;
		}
		
		if ( !hasDescriptions )
			return;
		
		insertDiagramSectionHeader( document, tableParams, "GRL Elements" );

		for (Iterator iter = diagram.getNodes().iterator(); iter.hasNext();) {
			URNmodelElement currentElement = (URNmodelElement) iter.next();
			if( ReportUtils.notEmpty( currentElement.getDescription() ) ) {
				ReportUtils.writeLineWithSeparator( document, currentElement.getName(), ": ", currentElement.getDescription(), descriptionFont, true );
			}
		}
	}
}