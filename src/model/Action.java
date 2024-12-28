package model;

import java.time.LocalDate;
import java.util.Date;

public class Action {
    private int id;
    private String name;
    private String description;
    private LocalDate dateDebutReelle;
    private LocalDate dateDebutPrevue;
    private LocalDate dateFinReelle;
    private LocalDate dateFinPrevue;
    private int responsableId;
    private static int counterId;

    // Constructor for new organisation (increments id)
    public Action(String name, String description, LocalDate dateDebutReelle, LocalDate dateDebutPrevue, LocalDate dateFinReelle, LocalDate dateFinPrevue, int responsableId) {
        counterId++;
        this.id=counterId;
        this.name = name;
        this.description = description;
        this.dateDebutReelle = dateDebutReelle;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateFinReelle = dateFinReelle;
        this.dateFinPrevue = dateFinPrevue;
        this.responsableId = responsableId;
    }
    // Constructor for temporary organisation variables (does not increment id)
    public Action(int id, String name, String description, LocalDate dateDebutReelle, LocalDate dateDebutPrevue, LocalDate dateFinReelle, LocalDate dateFinPrevue, int responsableId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateDebutReelle = dateDebutReelle;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateFinReelle = dateFinReelle;
        this.dateFinPrevue = dateFinPrevue;
        this.responsableId = responsableId;
    }
    // Organisation in string data format for database
    public String writeAction(){
        return id+"#"+name+"#"+description+"#"+dateDebutReelle+"#"+dateDebutPrevue+"#"+dateFinReelle+"#"+dateFinPrevue+"#"+responsableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(LocalDate dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public LocalDate getDateDebutPrevue() {
        return dateDebutPrevue;
    }

    public void setDateDebutPrevue(LocalDate dateDebutPrevue) {
        this.dateDebutPrevue = dateDebutPrevue;
    }

    public LocalDate getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(LocalDate dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public LocalDate getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(LocalDate dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public int getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(int responsableId) {
        this.responsableId = responsableId;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Action.counterId = counterId;
    }
}
