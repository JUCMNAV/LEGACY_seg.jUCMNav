package seg.jUCMNav.importexport.reports;
import urn.URNspec;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;

import com.lowagie.text.*;


public class ReportTitlePage extends Report {

    public ReportTitlePage() {
        
    }
 
public void CreateTitlePage(Document document) {
    
    try 
    {
        URNspec specs;
        specs.getName();
        
        Chunk modelName = new Chunk("" );
        modelName.setUnderline(0.2f, -2f);
        Chunk modelDescription = new Chunk("Description: ");
        Chunk modelAuthor = new Chunk("Author: ");
        Chunk modelCreationDate = new Chunk("Created on: ");
        Chunk modelModifiedDate = new Chunk("Modified on: ");
        Chunk modelGenerated = new Chunk("Generated on: ");
        Chunk modelSpecVersion = new Chunk("Specification Version: ");
        
    } catch (Exception e) {
        jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
        e.printStackTrace();
    }
}

    
}
