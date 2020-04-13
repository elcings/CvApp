package com.elchinaliyev.test.Report;

import android.content.Context;
import android.util.Log;

import com.elchinaliyev.test.Common.Common;
import com.elchinaliyev.test.Model.Certificate;
import com.elchinaliyev.test.Model.ContactWithDetail;
import com.elchinaliyev.test.Model.Education;
import com.elchinaliyev.test.Model.Experiance;
import com.elchinaliyev.test.Model.Language;
import com.elchinaliyev.test.Model.Project;
import com.elchinaliyev.test.Model.Skills;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;

public class CV extends BaseReport {
    Context context;
    Common common;
    ContactWithDetail all;

    public CV(Context context, ContactWithDetail all) throws IOException, DocumentException {
        this.context = context;
        this.all = all;
        common = new Common();
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
            tabName.getDefaultCell().setPaddingLeft(0);
            PdfPCell cellName = GetDefaultCell(GetDefaultParagraph(all.contact.getFirstName() + " " + all.contact.getLastName(), ""), 1, 1);
            cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellName.setPaddingLeft(0);
            tabName.addCell(cellName);

            //PhotoCell
            PdfPCell cellPhotoBasic = GetDefaultCell(GetDefaultParagraph("", ""), 1, 3);
            cellPhotoBasic.setBorder(2);
            PdfPTable imgTable = new PdfPTable(1);
            imgTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);


            Image image = Image.getInstance(all.contact.getImage());
            PdfPCell cellPhoto = new PdfPCell();
            cellPhoto.setFixedHeight(70);
            cellPhoto.setBorder(0);
            cellPhoto.addElement(image);
            imgTable.addCell(cellPhoto);
            cellPhotoBasic.addElement(imgTable);
            tabName.addCell(cellPhotoBasic);

            PdfPCell cellPos = GetDefaultCell(GetDefaultParagraph("", all.contact.getOccupation() + "\n\n"), 1, 1);
            cellPos.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellPos.setPaddingLeft(0);
            tabName.addCell(cellPos);

            PdfPCell cellDesc = GetDefaultCell(GetDefaultParagraph("", all.contact.getDescription()), 1, 1);
            cellDesc.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDesc.setPaddingLeft(0);
            cellDesc.setPaddingBottom(15);
            cellDesc.setBorder(2);
            tabName.addCell(cellDesc);

            //Experience
            PdfPCell cellExper = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellExper.setBorder(2);
            cellExper.setPaddingLeft(0);
            cellExper.setPaddingTop(20);
            cellExper.setPaddingBottom(15);
            PdfPTable tabExper = new PdfPTable(2);
            tabExper.setWidths(new int[]{100, 150});
            tabExper.setWidthPercentage(100);
            PdfPCell cellExperHeader = GetDefaultCell(GetDefaultParagraph("Experience\n\n", ""), 2, 1);
            cellExperHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellExperHeader.setPaddingLeft(0);
            tabExper.addCell(cellExperHeader);
            for (Experiance exper : all.experiances) {
                String end = "";
                if (exper.isWork()) {
                    end = "Present";
                } else {
                    end = common.convertDate(exper.getEndDate());
                }
                //experPlaceDate
                _cell = GetDefaultCell(GetDefaultParagraph(exper.getLocation(), "\n" + common.convertDate(exper.getStartDate()) + " - " + end), 1, 1);
                _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                _cell.setPaddingLeft(0);
                tabExper.addCell(_cell);

                //experPositionCompany
                _cell = GetDefaultCell(GetDefaultParagraph(exper.getPosition(), "\n" + exper.getCompany()), 1, 1);
                _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                _cell.setPaddingLeft(0);
                tabExper.addCell(_cell);
            }
            cellExper.addElement(tabExper);
            tabName.addCell(cellExper);

            //Education
            PdfPCell cellEdu = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
            cellEdu.setBorder(2);
            cellEdu.setPaddingBottom(15);
            cellEdu.setPaddingLeft(0);
            cellEdu.setPaddingTop(20);
            PdfPTable tabEdu = new PdfPTable(2);
            tabEdu.setWidths(new int[]{100, 150});
            tabEdu.setWidthPercentage(100);
            PdfPCell cellEduHeader = GetDefaultCell(GetDefaultParagraph("Education\n\n", ""), 2, 1);
            cellEduHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellEduHeader.setPaddingLeft(0);
            tabEdu.addCell(cellEduHeader);

            for (Education edu : all.educations) {
                //eduPlaceDate
                _cell = GetDefaultCell(GetDefaultParagraph(edu.getLocation(), "\n\n" + edu.getEndDate()), 1, 1);
                _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                _cell.setPaddingLeft(0);
                tabEdu.addCell(_cell);

                //eduPositionUni
                _cell = GetDefaultCell(GetDefaultParagraph(edu.getSpecialty(), "\n\n" + edu.getUniversity()), 1, 1);
                _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                _cell.setPaddingLeft(0);
                tabEdu.addCell(_cell);
            }
            cellEdu.addElement(tabEdu);
            tabName.addCell(cellEdu);


            //Certification&Courses
            if(all.certs!=null&&all.certs.size()>0) {
                PdfPCell cellCert = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
                cellCert.setBorder(2);
                cellCert.setPaddingBottom(15);
                cellCert.setPaddingTop(20);
                cellCert.setPaddingLeft(0);
                PdfPTable tabCert = new PdfPTable(1);
                tabCert.setWidths(new int[]{250});
                tabCert.setWidthPercentage(100);
                PdfPCell cellCertHeader = GetDefaultCell(GetDefaultParagraph("Certifications & Courses\n\n", ""), 1, 1);
                cellCertHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellCertHeader.setPaddingLeft(0);
                tabCert.addCell(cellCertHeader);

                //Certification
                for (Certificate cert : all.certs) {
                    _cell = GetDefaultCell(GetDefaultParagraph(cert.getName(), ""), 1, 1);
                    _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    _cell.setPaddingLeft(0);
                    tabCert.addCell(_cell);
                }

                cellCert.addElement(tabCert);
                tabName.addCell(cellCert);
            }


            //Projects
            if(all.projects!=null&& all.projects.size()>0) {
                PdfPCell cellPro = GetDefaultCell(GetDefaultParagraph("", ""), 2, 1);
                cellPro.setPaddingBottom(15);
                cellPro.setPaddingLeft(0);
                cellPro.setPaddingTop(20);
                PdfPTable tabPro = new PdfPTable(1);
                tabPro.setWidths(new int[]{250});
                tabPro.setWidthPercentage(100);
                PdfPCell cellProHeader = GetDefaultCell(GetDefaultParagraph("PROJECTS\n\n", ""), 1, 1);
                cellProHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellProHeader.setPaddingLeft(0);
                tabPro.addCell(cellProHeader);

                for (Project project : all.projects) {
                    _cell = GetDefaultCell(GetDefaultParagraph(project.getName(), ""), 1, 1);
                    _cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    _cell.setPaddingLeft(0);
                    tabPro.addCell(_cell);
                }
                cellPro.addElement(tabPro);
                tabName.addCell(cellPro);
                _cell.addElement(tabName);
                tab.addCell(_cell);
            }


            //Left
            PdfPCell celLeft = GetDefaultCell(GetDefaultParagraph("", ""), 1, 1);
            celLeft.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celLeft.setMinimumHeight(PageSize.A4.getHeight());
            PdfPTable tabContact = new PdfPTable(1);
            tabContact.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("Contact Information", ""), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("\nEMAIL\n", all.contact.getEmail()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Address\n", all.contact.getAddress()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Phone\n", all.contact.getPhone()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Date of Birth\n", all.contact.getBirthDate()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Nationality\n", all.contact.getNationality()), 1, 1);
            tabContact.addCell(_cell);
            _cell = GetDefaultCell(GetDefaultParagraph("Sosial media\n", all.contact.getSosialMedia()), 1, 1);
            tabContact.addCell(_cell);
            celLeft.addElement(tabContact);

            //Skills

            PdfPTable tabSkill = new PdfPTable(1);
            tabSkill.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("SKILLS\n", ""), 1, 1);
            _cell.setBorder(2);
            _cell.setPaddingTop(10);
            _cell.setPaddingBottom(10);
            tabSkill.addCell(_cell);
            int i = 0;
            for (Skills skill : all.skills) {
                if (i == 0) {
                    _cell = GetDefaultCell(GetDefaultParagraph("\n" + skill.getName(), ""), 1, 1);
                    tabSkill.addCell(_cell);
                } else {
                    _cell = GetDefaultCell(GetDefaultParagraph(skill.getName(), ""), 1, 1);
                    tabSkill.addCell(_cell);
                }
                i++;
            }
            celLeft.addElement(tabSkill);


            //Languages
            PdfPTable tabLang = new PdfPTable(1);
            tabLang.setWidthPercentage(100);
            _cell = GetDefaultCell(GetDefaultParagraph("LANGUAGES", ""), 1, 1);
            _cell.setBorder(2);
            _cell.setPaddingBottom(10);
            _cell.setPaddingTop(20);
            tabLang.addCell(_cell);
            int j = 0;
            for (Language lang : all.languages) {
                if (j == 0) {
                    _cell = GetDefaultCell(GetDefaultParagraph("\n" + lang.getName(), " :" + lang.getLevel()), 1, 1);
                    tabLang.addCell(_cell);
                } else {
                    _cell = GetDefaultCell(GetDefaultParagraph(lang.getName(), " :" + lang.getLevel()), 1, 1);
                    tabLang.addCell(_cell);
                }
                j++;
            }

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
