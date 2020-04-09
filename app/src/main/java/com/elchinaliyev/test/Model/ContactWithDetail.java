package com.elchinaliyev.test.Model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ContactWithDetail {
    @Embedded
    public Contact contact;

    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity = Skills.class
    )
    public List<Skills> skills;


    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity = Education.class
    )
    public List<Education> educations;

    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity = Experiance.class
    )
    public List<Experiance> experiances;

    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity = Project.class
    )
    public List<Project> projects;
    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity = Certificate.class
    )
    public List<Certificate> certs;


    @Relation(
            parentColumn = "id",
            entityColumn = "contactId",
            entity=Language.class
    )
    public List<Language> languages;

}
