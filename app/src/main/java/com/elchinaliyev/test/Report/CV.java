package com.elchinaliyev.test.Report;

import android.content.Context;
import com.elchinaliyev.test.Model.Contact;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;

public class CV extends BaseReport {
    Context context;
    Contact contact;

    public CV(Context context, Contact contact) throws IOException, DocumentException {
        this.context = context;
        this.contact=contact;
    }

    @Override
    public void AddBody() {

        try {
            PdfPTable tab = GetDefaultBorderedTable(2, 1);
            tab.setWidths(new int[]{240, 80});
            _cell = GetDefaultCell(GetDefaultParagraph("", ""), 1, 1);
            PdfPTable tabName = new PdfPTable(2);
            tabName.setWidths(new int[]{200, 40});
            tabName.setWidthPercentage(100);
            tabName.getDefaultCell().setBorder(2);
            PdfPCell cellName = GetDefaultCell(GetDefaultParagraph(contact.getFirstName()+" "+contact.getLastName(), ""), 1, 1);
            cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellName.setPaddingLeft(0);
            tabName.addCell(cellName);

            //PhotoCell
            PdfPCell cellPhotoBasic = GetDefaultCell(GetDefaultParagraph("", ""), 1, 3);
            cellPhotoBasic.setBorder(2);
            PdfPTable imgTable = new PdfPTable(1);
            imgTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);


            Image image = Image.getInstance(contact.getImage());
            PdfPCell cellPhoto = new PdfPCell();
            cellPhoto.setFixedHeight(70);
            cellPhoto.setBorder(0);
            cellPhoto.addElement(image);
            imgTable.addCell(cellPhoto);
            cellPhotoBasic.addElement(imgTable);
            tabName.addCell(cellPhotoBasic);

            PdfPCell cellPos = GetDefaultCell(GetDefaultParagraph("", contact.getOccupation()), 1, 1);
            cellPos.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellPos.setPaddingLeft(0);
            tabName.addCell(cellPos);

            PdfPCell cellDesc = GetDefaultCell(GetDefaultParagraph("", contact.getDescription()), 1, 1);
            cellDesc.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDesc.setPaddingLeft(0);
            cellDesc.setBorder(2);
            tabName.addCell(cellDesc);

            //Experience
            PdfPCell cellExper = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellExper.setBorder(2);
            cellExper.setPaddingLeft(0);
            cellExper.setPaddingBottom(8);
            PdfPTable tabExper = new PdfPTable(2);
            tabExper.setWidths(new int[]{100, 150});
            tabExper.setWidthPercentage(100);
            PdfPCell cellExperHeader = GetDefaultCell(GetDefaultParagraph("Experience", ""), 2, 1);
            cellExperHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellExperHeader.setPaddingLeft(0);
            tabExper.addCell(cellExperHeader);

            //experPlaceDate
            _cell = GetDefaultCell(GetDefaultParagraph("Azerbaijan,Baku", "\nOct 2014-Sept 2015"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabExper.addCell(_cell);

            //experPositionCompany
            _cell = GetDefaultCell(GetDefaultParagraph("Development Operation Centre Engineer", "\nHuawei /Azertelecom"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabExper.addCell(_cell);
            cellExper.addElement(tabExper);
            tabName.addCell(cellExper);


            //Education
            PdfPCell cellEdu = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellEdu.setBorder(2);
            cellEdu.setPaddingBottom(8);
            cellEdu.setPaddingLeft(0);
            PdfPTable tabEdu = new PdfPTable(2);
            tabEdu.setWidths(new int[]{100, 150});
            tabEdu.setWidthPercentage(100);
            PdfPCell cellEduHeader = GetDefaultCell(GetDefaultParagraph("Education", ""), 2, 1);
            cellEduHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellEduHeader.setPaddingLeft(0);
            tabEdu.addCell(cellEduHeader);

            //eduPlaceDate
            _cell = GetDefaultCell(GetDefaultParagraph("Azerbaijan,Baku", "\n2011"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);

            //eduPositionUni
            _cell = GetDefaultCell(GetDefaultParagraph("Bachelor of  Management", "\nHAzerbaijan State Oil Academy"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);
            cellEdu.addElement(tabEdu);


            //eduPlaceDate
            _cell = GetDefaultCell(GetDefaultParagraph("Azerbaijan,Baku", "\n2011"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);

            //eduPositionUni
            _cell = GetDefaultCell(GetDefaultParagraph("Bachelor of  Management", "\nHAzerbaijan State Oil Academy"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);
            cellEdu.addElement(tabEdu);

            //eduPlaceDate
            _cell = GetDefaultCell(GetDefaultParagraph("Azerbaijan,Baku", "\n2011"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);

            //eduPositionUni
            _cell = GetDefaultCell(GetDefaultParagraph("Bachelor of  Management", "\nHAzerbaijan State Oil Academy"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabEdu.addCell(_cell);
            cellEdu.addElement(tabEdu);


            tabName.addCell(cellEdu);


            //Certification&Courses
            PdfPCell cellCert = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellCert.setBorder(2);
            cellCert.setPaddingBottom(8);
            cellCert.setPaddingLeft(0);
            PdfPTable tabCert = new PdfPTable(1);
            tabCert.setWidths(new int[]{250});
            tabCert.setWidthPercentage(100);
            PdfPCell cellCertHeader = GetDefaultCell(GetDefaultParagraph("Certifications & Courses", ""), 1, 1);
            cellCertHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellCertHeader.setPaddingLeft(0);
            tabCert.addCell(cellCertHeader);

            //Certification
            _cell = GetDefaultCell(GetDefaultParagraph("Microsoft MCSP(Programming in C#)", ""), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabCert.addCell(_cell);

            cellCert.addElement(tabCert);
            tabName.addCell(cellCert);




            //Projects
            PdfPCell cellPro = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellPro.setPaddingBottom(8);
            cellPro.setPaddingLeft(0);
            PdfPTable tabPro = new PdfPTable(1);
            tabPro.setWidths(new int[]{250});
            tabPro.setWidthPercentage(100);
            PdfPCell cellProHeader = GetDefaultCell(GetDefaultParagraph("PROJECTS", ""), 1, 1);
            cellProHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellProHeader.setPaddingLeft(0);
            tabPro.addCell(cellProHeader);


            _cell = GetDefaultCell(GetDefaultParagraph("Microsoft MCSP(Programming in C#)", "DELIVERY,HRdkdlak;ldak sfksfskfl;skfls;"), 1, 1);
            _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            _cell.setPaddingLeft(0);
            tabPro.addCell(_cell);

            cellPro.addElement(tabPro);
            tabName.addCell(cellPro);


            _cell.addElement(tabName);
            tab.addCell(_cell);


            //Left
            PdfPCell celLeft = GetDefaultCell(GetDefaultParagraph("", ""), 1, 1);
            celLeft.setBackgroundColor(BaseColor.LIGHT_GRAY);
            PdfPTable tabContact = new PdfPTable(1);
            tabContact.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("Contact Information", ""), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("\nEMAIL\n", contact.getEmail()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Address\n", contact.getAddress()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Phone\n", contact.getPhone()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Date of Birth\n", contact.getBirthDate()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Nationality\n", contact.getNationality()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Sosial media\n", contact.getSosialMedia()), 1, 1);
            tabContact.addCell(_cell);
            celLeft.addElement(tabContact);

            //Skills

            PdfPTable tabSkill = new PdfPTable(1);
            tabSkill.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("SKILLS", ""), 1, 1);
            _cell.setBorder(2);
            _cell.setPaddingTop(10);
            _cell.setPaddingBottom(10);
            tabSkill.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("\nC# Programming", ""), 1, 1);
            tabSkill.addCell(_cell);
            celLeft.addElement(tabSkill);


            //Languages
            PdfPTable tabLang = new PdfPTable(1);
            tabLang.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("LANGUAGES", ""), 1, 1);
            _cell.setBorder(2);
            _cell.setPaddingBottom(10);
            _cell.setPaddingTop(20);
            tabLang.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("\nEnglish", "\nIntermediate"), 1, 1);
            _cell.setPaddingBottom(20);
            tabLang.addCell(_cell);
            celLeft.addElement(tabLang);


            tab.addCell(celLeft);


            document.add(tab);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
