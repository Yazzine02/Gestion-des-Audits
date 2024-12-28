package model;

import java.time.LocalDate;

public class Correction {
    private int id;
    private static int counterId;
    private String name;
    private String description;
    private int responsableId;

    // Constructor for new organisation (increments id)
    public Correction(String name, String description, int responsableId) {
        counterId++;
        this.id=counterId;
        this.name = name;
        this.description = description;
        this.responsableId = responsableId;
    }
    // Constructor for temporary organisation variables (does not increment id)
    public Correction(int id, String name, String description, int responsableId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.responsableId = responsableId;
    }
    // Organisation in string data format for database
    public String writeCorrection() {
        return id+"#"+name+"#"+description+"#"+responsableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Correction.counterId = counterId;
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

    public int getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(int responsableId) {
        this.responsableId = responsableId;
    }
}
