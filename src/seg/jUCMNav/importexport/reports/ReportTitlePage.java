package seg.jUCMNav.importexport.reports;

import java.text.SimpleDateFormat;

//import org.eclipse.swt.graphics.Color;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import urn.URNspec; 
import com.lowagie.text.*;
//import com.lowagie.text.Image.*;


public class ReportTitlePage extends Report {

    public ReportTitlePage() {
        
    }
 
public void CreateTitlePage(Document document,URNspec urn) {
    
    try 
    {   
        
        
        // Create some space between the header and the image
        for (int x = 0; x<7; x++) {
        
            Paragraph topSpace = new Paragraph("     "); 
            document.add(Chunk.NEWLINE);
              
        }
        
        //Image titlePageImage = (JUCMNavPlugin.getImage( "icons/LogoFinalLarge.gif"));
        //com.lowagie.text.Image.getInstance(titlePageImage);
        //com.lowagie.text.Image image1 = com.lowagie.text.Image.getInstance ( "/seg.jUCMNav/icons/LogoFinalLarge.gif");
        //ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Component16.gif")
        
        //temporary until I can get the image in place
        Font font = new Font(Font.COURIER, 36, Font.BOLD);
        Paragraph projectName = new Paragraph("  jUCMNav  ",font);
        projectName.setAlignment (Element.ALIGN_CENTER);
        document.add(projectName);
        
        
        // jUCMNav Image here!
        //Image image = Image.getInstance ("icons/LogoFinalLarge.gif");
        //Image image = Image.getInstance ("C:\\temp\\LogoFinalLarge.gif");
        //image.setAlignment (Image.MIDDLE);
        //document.add (image);
        
        Paragraph appURL = new Paragraph("http://www.softwareengineering.ca/jucmnav");
        appURL.setAlignment (Element.ALIGN_CENTER);
        document.add(appURL);
        
     // Create some space between the image and the title page info
        for (int x = 0; x<20; x++) {
            
            document.add(Chunk.NEWLINE); 

        }
        
        
/*        Paragraph modelName = new Paragraph("Title: " + CheckforEmpty(urn.getName())); 
        modelName.setAlignment (Element.ALIGN_CENTER);
        document.add(modelName);
        
        Paragraph modelDescription = new Paragraph("Description: " + CheckforEmpty(urn.getDescription())); 
        modelDescription.setAlignment (Element.ALIGN_CENTER);
        document.add(modelDescription);
        
        Paragraph modelAuthor = new Paragraph("Author: " + CheckforEmpty(urn.getAuthor())); 
        modelAuthor.setAlignment (Element.ALIGN_CENTER);
        document.add(modelAuthor);
        
        Paragraph modelCreationDate = new Paragraph("Creation Date: " + CheckforEmpty(urn.getCreated())); 
        modelCreationDate.setAlignment (Element.ALIGN_CENTER);
        document.add(modelCreationDate);
        
        Paragraph modelModifiedDate = new Paragraph("Modification Date: " + CheckforEmpty(urn.getModified())); 
        modelModifiedDate.setAlignment (Element.ALIGN_CENTER);
        document.add(modelModifiedDate);*/
        
        //SimpleDateFormat format = new SimpleDateFormat("MMMMM d, yyyy HH:mm:ss aaa z");
        //String date = format.format(new java.util.Date());
        //Paragraph modelGeneratedDate = new Paragraph("Report Generation Date: " + date); 
        //modelGeneratedDate.setAlignment (Element.ALIGN_CENTER);
        //document.add(modelGeneratedDate);
        
/*        Paragraph modelSpecVersion = new Paragraph("Specification Version: " + CheckforEmpty(urn.getSpecVersion())); 
        modelSpecVersion.setAlignment (Element.ALIGN_CENTER);
        document.add(modelSpecVersion);*/
        
        Font specsFont = new Font(Font.BOLD);
        
        Table specsTable = ReportUtils.createTable(2, 2, 0, 70);
        
        Chunk titleLabel = new Chunk("Title:", specsFont);
        Chunk titleValue = new Chunk(CheckforEmpty(urn.getName()));
        
        Chunk descriptionLabel = new Chunk("Description:",specsFont);
        Chunk descriptionValue = new Chunk(CheckforEmpty(urn.getDescription()));
        
        Chunk authorLabel = new Chunk("Author:",specsFont);
        Chunk authorValue = new Chunk(CheckforEmpty(urn.getAuthor()));
        
        Chunk creationLabel = new Chunk("Creation Date:",specsFont);
        Chunk creationValue = new Chunk(CheckforEmpty(urn.getCreated()));
        
        Chunk modLabel = new Chunk("Modification Date:",specsFont);
        Chunk modValue = new Chunk(CheckforEmpty(urn.getModified()));
        
        SimpleDateFormat format = new SimpleDateFormat("MMMMM d, yyyy HH:mm:ss aaa z");
        String date = format.format(new java.util.Date());
        Chunk dateLabel = new Chunk("Report Generation Date:", specsFont);
        Chunk dateValue = new Chunk(date);
        
        Chunk specLabel = new Chunk("Specification Version:", specsFont);
        Chunk specValue = new Chunk(CheckforEmpty(urn.getSpecVersion()));
        
        Cell titleCell = new Cell(titleLabel);
        Cell titleCellValue = new Cell(titleValue);
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell descCell = new Cell(descriptionLabel);
        Cell descCellValue = new Cell(descriptionValue);
        descCell.setBorder(Rectangle.NO_BORDER);
        descCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell authorCell = new Cell(authorLabel);
        Cell authorCellValue = new Cell(authorValue);
        authorCell.setBorder(Rectangle.NO_BORDER);
        authorCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell creationCell = new Cell(creationLabel);
        Cell creationCellValue = new Cell(creationValue);
        creationCell.setBorder(Rectangle.NO_BORDER);
        creationCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell modCell = new Cell(modLabel);
        Cell modCellValue = new Cell(modValue);
        modCell.setBorder(Rectangle.NO_BORDER);
        modCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell dateCell = new Cell(dateLabel);
        Cell dateCellValue = new Cell(dateValue);
        dateCell.setBorder(Rectangle.NO_BORDER);
        dateCellValue.setBorder(Rectangle.NO_BORDER);
        
        Cell specCell = new Cell(specLabel);
        Cell specCellValue = new Cell(specValue);
        specCell.setBorder(Rectangle.NO_BORDER);
        specCellValue.setBorder(Rectangle.NO_BORDER);
        
        specsTable.addCell(titleCell);
        specsTable.addCell(titleCellValue);
        
        specsTable.addCell(descCell);
        specsTable.addCell(descCellValue);
        
        specsTable.addCell(authorCell);
        specsTable.addCell(authorCellValue);
        
        specsTable.addCell(creationCell);
        specsTable.addCell(creationCellValue);
        
        specsTable.addCell(modCell);
        specsTable.addCell(modCellValue);
        
        specsTable.addCell(dateCell);
        specsTable.addCell(dateCellValue);
        
        specsTable.addCell(creationCell);
        specsTable.addCell(creationCellValue);
        
        specsTable.addCell(specCell);
        specsTable.addCell(specCellValue);
        
        document.add(specsTable);
        
        document.newPage();
              
    } catch (Exception e) {
        jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
        e.printStackTrace();
        //document.close();
    } finally {
        
    }
}

private String CheckforEmpty(String emptyString) {
    
    // check if the string being passed in is empty
    if (emptyString == null || emptyString.length() == 0) {
    
        return "";
        
    }
    else {
        return emptyString;      
        
    }            
    
}    

}


