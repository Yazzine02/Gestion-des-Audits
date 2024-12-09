package model;

public class Processus {
    private final int id;
    private static int counterId = 0;
    private String description;
    private String name;
    private int organisationId;
    private int responsableId;
    // Constructor for new processus (increments id)
    public Processus(String description, String name, int organisationId, int responsableId) {
        counterId++;
        this.id = counterId;
        this.description = description;
        this.name = name;
        this.organisationId = organisationId;
        this.responsableId = responsableId;
    }
    // Constructor for temporary processus variables (does not increment id)
    public Processus(int id, String description, String name, int organisationId, int responsableId) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.organisationId = organisationId;
        this.responsableId = responsableId;
    }
    // Processus in string data format for database
    public String writeProcessus() {
        return id+"#"+description+"#"+name+"#"+organisationId;
    }
    // toString
    public String toString() {
        return "Processus [id=" + id + "#description=" + description + "#name=" + name + "#organisationId="+organisationId+"#responsableId="+responsableId+"]";
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
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
        Processus.counterId = counterId;
    }
}
