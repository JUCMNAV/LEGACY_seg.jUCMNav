package seg.jUCMNav.importexport.reports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.reports.utils.ReportUtils;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.views.wizards.importexport.ExportPreferenceHelper;
import urn.URNspec;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;

public class ReportTitlePage extends Report {

    public ReportTitlePage() {

    }

    public void CreateTitlePage(Document document, URNspec urn) {

        try {
        	//float reportWidthPref = Float.parseFloat(ReportGeneratorPreferences.getWidth());
        	//float reportHeightPref = Float.parseFloat(ReportGeneratorPreferences.getHeight());
        	int spaceAfterImg = 4;
        	float imgWidth = 0;
        	float imgHeight = 0;
            // jUCMNav title
        	Font font;
        	if (reportHeight < 11) {
        		font = new Font(Font.HELVETICA, 24, Font.BOLD);
        	} else {
        		font = new Font(Font.HELVETICA, 36, Font.BOLD);
        	}
            Paragraph projectName = new Paragraph(Messages.getString("ReportTitlePage.jUCMNavReport"), font); //$NON-NLS-1$
            projectName.setAlignment(Element.ALIGN_CENTER);
            document.add(projectName);

            document.add(Chunk.NEWLINE);

            // Load jUCMNav image by returning the runtime of the class of the object and retrieve its resources
            Image image = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/jUCMNavLogo_large.png")); //$NON-NLS-1$
            
            if (reportHeight >= 11) {
            	imgWidth = image.getWidth() * 0.5f;
                imgHeight = image.getHeight() * 0.5f;
            } else {
            	imgWidth = image.getWidth() * 0.25f;
                imgHeight = image.getHeight() * 0.25f;
            	spaceAfterImg = 1;
            }
            
            image.scaleAbsolute(imgWidth, imgHeight);
            image.setAlignment(Image.MIDDLE);
            document.add(image);

            Paragraph appURL = new Paragraph("http://softwareengineering.ca/jucmnav"); //$NON-NLS-1$
            appURL.setAlignment(Element.ALIGN_CENTER);
            document.add(appURL);

            // Create some space between the image and the title page info
            for (int x = 0; x < spaceAfterImg; x++) {

                document.add(Chunk.NEWLINE);

            }

            Font specsFont = new Font(Font.BOLD);
            
            Table specsTable;
            
            float tableWidth = (0.7f * 8.5f * 72f)/(reportWidth * 72) * 100;
            if (tableWidth > 100) {
            	specsTable = ReportUtils.createTable(2, 2, 0, 100);
            } else {
            	specsTable = ReportUtils.createTable(2, 2, 0, (int)Math.floor(tableWidth));
            }
            
            // URN title
            Chunk titleLabel = new Chunk(Messages.getString("ReportTitlePage.Title"), specsFont); //$NON-NLS-1$
            Chunk titleValue = new Chunk(CheckforEmpty(ExportPreferenceHelper.getFilenamePrefix().replace( Messages.getString("ReportTitlePage.0"), Messages.getString("ReportTitlePage.1")))); //$NON-NLS-1$ //$NON-NLS-2$

            // URN Model name
            Chunk urnModelNameLabel = new Chunk(Messages.getString("ReportTitlePage.URNModelName"), specsFont); //$NON-NLS-1$
            Chunk urnModelNameValue = new Chunk(CheckforEmpty(urn.getName()));
            
            // URN description
            Chunk descriptionLabel = new Chunk(Messages.getString("ReportTitlePage.Description"), specsFont); //$NON-NLS-1$
            Chunk descriptionValue = new Chunk(CheckforEmpty(urn.getDescription()));

            // URN author
            Chunk authorLabel = new Chunk(Messages.getString("ReportTitlePage.Author"), specsFont); //$NON-NLS-1$
            Chunk authorValue = new Chunk(CheckforEmpty(urn.getAuthor()));

            // URN creation date
            Chunk creationLabel = new Chunk(Messages.getString("ReportTitlePage.CreationDate"), specsFont); //$NON-NLS-1$
            Chunk creationValue;
            if (CheckforEmpty(urn.getCreated()) != "") { //$NON-NLS-1$
            	SimpleDateFormat newFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DateFormat")); //$NON-NLS-1$
    			// TODO Replace the hard-coded Locale value (to find the language in which dates were generated in the model)
    			SimpleDateFormat originalDateFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DefaultDateFormat"), new Locale("en,US")); //$NON-NLS-1$ //$NON-NLS-2$
    			// verify if the model's creation time corresponds to a PM time
    			int indexOfPMString = -1;
    			boolean isPM = false;
    			indexOfPMString = urn.getCreated().indexOf(" PM "); //$NON-NLS-1$
    			if (indexOfPMString > -1) {
    				isPM = true;
    			}
    			Date dateCreated = (Date)originalDateFormat.parse(urn.getCreated());
    			String strDateCreated = newFormat.format(dateCreated);
    			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
    			// are replaced by "PM" if isPM is true
    			if (isPM) {
    				strDateCreated = strDateCreated.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
    			}
            	creationValue = new Chunk(strDateCreated);
            } else {
            	creationValue = new Chunk(""); //$NON-NLS-1$
            }

            // URN modification date/time
            Chunk modLabel = new Chunk(Messages.getString("ReportTitlePage.ModificaitonDate"), specsFont); //$NON-NLS-1$
            Chunk modValue;
            if (CheckforEmpty(urn.getModified()) != "") { //$NON-NLS-1$
            	SimpleDateFormat newFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DateFormat")); //$NON-NLS-1$
    			// TODO Replace the hard-coded Locale value (to find the language in which dates were generated in the model)
    			SimpleDateFormat originalDateFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DefaultDateFormat"), new Locale("en,US")); //$NON-NLS-1$ //$NON-NLS-2$
    			// verify if the model's modification time corresponds to a PM time
    			int indexOfPMString = -1;
    			boolean isPM = false;
    			indexOfPMString = urn.getModified().indexOf(" PM "); //$NON-NLS-1$
    			if (indexOfPMString > -1) {
    				isPM = true;
    			}
    			Date dateModified = (Date)originalDateFormat.parse(urn.getModified());
    			String strDateModified = newFormat.format(dateModified);
    			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
    			// are replaced by "PM" if isPM is true
    			if (isPM) {
    				strDateModified = strDateModified.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
    			}
    			modValue = new Chunk(strDateModified);
            }
            else {
            	modValue = new Chunk(""); //$NON-NLS-1$
            }

            // URN current date/time
            // generate the current date using the standard Locale (English-US) and convert it to the default locale
            String strCurrentDate;
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
            strCurrentDate = df.format(new Date());
            
            SimpleDateFormat newFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DateFormat")); //$NON-NLS-1$
			// TODO Replace the hard-coded Locale value (to find the language in which dates were generated in the model)
			SimpleDateFormat originalDateFormat = new SimpleDateFormat(Messages.getString("ReportTitlePage.DefaultDateFormat"), new Locale("en,US")); //$NON-NLS-1$ //$NON-NLS-2$
			// verify if current time corresponds to a PM time
			int indexOfPMString = -1;
			boolean isPM = false;
			indexOfPMString = strCurrentDate.indexOf(" PM "); //$NON-NLS-1$
			if (indexOfPMString > -1) {
				isPM = true;
			}
			Date dateGenerated = (Date)originalDateFormat.parse(strCurrentDate);
			String strDateGenerated = newFormat.format(dateGenerated);
			// since not all locales recognize the 12-hour AM/PM system, make sure that all "AM" strings
			// are replaced by "PM" if isPM is true
			if (isPM) {
				strDateGenerated = strDateGenerated.replaceAll(" AM ", " PM "); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
            Chunk dateLabel = new Chunk(Messages.getString("ReportTitlePage.ReportGenerationDate"), specsFont); //$NON-NLS-1$
            Chunk dateValue = new Chunk(strDateGenerated);

            // URN specification version
            Chunk specLabel = new Chunk(Messages.getString("ReportTitlePage.SpecificationVersion"), specsFont); //$NON-NLS-1$
            Chunk specValue = new Chunk(CheckforEmpty(urn.getSpecVersion()));

            // Create each table cell
            Cell titleCell = new Cell(titleLabel);
            Cell titleCellValue = new Cell(titleValue);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCellValue.setBorder(Rectangle.NO_BORDER);

            Cell urnModelNameCell = new Cell(urnModelNameLabel);
            Cell urnModelNameCellValue = new Cell(urnModelNameValue);
            urnModelNameCell.setBorder(Rectangle.NO_BORDER);
            urnModelNameCellValue.setBorder(Rectangle.NO_BORDER);
            
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

            specsTable.addCell(urnModelNameCell);
            specsTable.addCell(urnModelNameCellValue);
            
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

            specsTable.addCell(specCell);
            specsTable.addCell(specCellValue);

            document.add(specsTable);

            document.newPage();

        } catch (Exception e) {
            jUCMNavErrorDialog error = new jUCMNavErrorDialog(e.getMessage());
            e.printStackTrace();
        } finally {

        }
    }

    private String CheckforEmpty(String emptyString) {

        // check if the string being passed in is empty
        if (emptyString == null || emptyString.length() == 0) {
            return ""; //$NON-NLS-1$
        } else {
            return emptyString;
        }

    }

}
