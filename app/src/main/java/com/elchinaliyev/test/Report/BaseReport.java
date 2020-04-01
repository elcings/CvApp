package com.elchinaliyev.test.Report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseReport {
    protected BaseFont bf;
    protected Font headerFont;
    protected Font bFont; // bold font
    protected Font font; // normal font
    protected Font italicFont; // normal font
    protected Document document = new Document(PageSize.A4);
    protected PdfPCell _cell;
    protected BaseReport() throws IOException, DocumentException {
        bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.WINANSI,BaseFont.EMBEDDED);
        headerFont = new Font(bf, 16, Font.BOLD);
        bFont = new Font(bf, 10, Font.NORMAL);
        font = new Font(bf, 8, Font.NORMAL);
        italicFont = new Font(bf, 8, Font.ITALIC);
        document.setMargins(0, 0, 50f, 20f);
    }

    public abstract void AddBody();

    public  void GeneratePdf(String output) {

        try {
            PdfWriter.getInstance(document, new FileOutputStream(output));
            document.open();
            AddBody();
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected PdfPTable GetDefaultTable(int numColumns) {
        PdfPTable table = new PdfPTable(numColumns);
        table.getDefaultCell().setBorder(0);
        return table;
    }
    protected PdfPTable GetDefaultBorderedTable(int numColumns, float borderWidth)
    {
        PdfPTable table = new PdfPTable(numColumns);
        table.getDefaultCell().setPadding(5);
        table.getDefaultCell().setUseVariableBorders(true);
        table.getDefaultCell().setBorderWidth(borderWidth);
        table.getDefaultCell().setBorderColor(BaseColor.BLACK);
        return table;
    }

    protected PdfPCell GetDefaultCell(Phrase phrase, int colspan , int rowspan)
    {
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        return cell;
    }
    protected PdfPCell GetDefaultBorderedCell(Phrase phrase, int colspan, int rowspan, float borderWidth)
    {
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setUseVariableBorders(true);
        cell.setBorderWidth(borderWidth);
        cell.setBorderColor(BaseColor.BLACK);
        cell.setColspan(colspan);
         cell.setRowspan(rowspan);
        return cell;
    }

    protected Paragraph GetDefaultParagraph(String label, String text)
    {
        Paragraph p = new Paragraph();
        p.add(new Phrase(label, bFont));
        p.add(new Phrase(text, font));
        return p;

    }
}
