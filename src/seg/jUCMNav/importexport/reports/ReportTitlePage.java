package seg.jUCMNav.importexport.reports;

import java.text.SimpleDateFormat;

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
            // jUCMNav title
            Font font = new Font(Font.HELVETICA, 36, Font.BOLD);
            Paragraph projectName = new Paragraph(Messages.getString("ReportTitlePage.jUCMNavReport"), font); //$NON-NLS-1$
            projectName.setAlignment(Element.ALIGN_CENTER);
            document.add(projectName);

            document.add(Chunk.NEWLINE);

            // Load jUCMNav image by returning the runtime of the class of the object and retrieve its resources
            Image image = Image.getInstance(getClass().getResource("/seg/jUCMNav/icons/LogoFinalLarge.gif")); //$NON-NLS-1$
            image.setAlignment(Image.MIDDLE);
            document.add(image);

            Paragraph appURL = new Paragraph("http://www.softwareengineering.ca/jucmnav/"); //$NON-NLS-1$
            appURL.setAlignment(Element.ALIGN_CENTER);
            document.add(appURL);

            // Create some space between the image and the title page info
            for (int x = 0; x < 6; x++) {

                document.add(Chunk.NEWLINE);

            }

            Font specsFont = new Font(Font.BOLD);

            Table specsTable = ReportUtils.createTable(2, 2, 0, 70);

            // URN title
            Chunk titleLabel = new Chunk(Messages.getString("ReportTitlePage.Title"), specsFont); //$NON-NLS-1$
            Chunk titleValue = new Chunk(CheckforEmpty(ExportPreferenceHelper.getFilenamePrefix().replace( ".jucm", "")));

            // URN description
            Chunk descriptionLabel = new Chunk(Messages.getString("ReportTitlePage.Description"), specsFont); //$NON-NLS-1$
            Chunk descriptionValue = new Chunk(CheckforEmpty(urn.getDescription()));

            // URN author
            Chunk authorLabel = new Chunk(Messages.getString("ReportTitlePage.Author"), specsFont); //$NON-NLS-1$
            Chunk authorValue = new Chunk(CheckforEmpty(urn.getAuthor()));

            // URN creation date
            Chunk creationLabel = new Chunk(Messages.getString("ReportTitlePage.CreationDate"), specsFont); //$NON-NLS-1$
            Chunk creationValue = new Chunk(CheckforEmpty(urn.getCreated()));

            // URN modification date/time
            Chunk modLabel = new Chunk(Messages.getString("ReportTitlePage.ModificaitonDate"), specsFont); //$NON-NLS-1$
            Chunk modValue = new Chunk(CheckforEmpty(urn.getModified()));

            // URN current date/time
            SimpleDateFormat format = new SimpleDateFormat(Messages.getString("ReportTitlePage.DateFormat")); //$NON-NLS-1$
            String date = format.format(new java.util.Date());
            Chunk dateLabel = new Chunk(Messages.getString("ReportTitlePage.ReportGenerationDate"), specsFont); //$NON-NLS-1$
            Chunk dateValue = new Chunk(date);

            // URN specification version
            Chunk specLabel = new Chunk(Messages.getString("ReportTitlePage.SpecificationVersion"), specsFont); //$NON-NLS-1$
            Chunk specValue = new Chunk(CheckforEmpty(urn.getSpecVersion()));

            // Create each table cell
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
