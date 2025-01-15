package model;

public class SystemeDeManagement {
    private int id;
    private String description;
    private int nbrPersonnes;
    private String nom;
    private int organisationId;
    private int responsablId;
    private static int counterId;

    // Constructor for new organisation (increments id)
    public SystemeDeManagement(String description, int nbrPersonnes, String nom, int organisationId, int responsablId) {
        counterId++;
        this.id=counterId;
        this.description=description;
        this.nbrPersonnes=nbrPersonnes;
        this.nom=nom;
        this.organisationId=organisationId;
        this.responsablId=responsablId;

    }
    // Constructor for temporary organisation variables (does not increment id)
    public SystemeDeManagement(int id, String description, int nbrPersonnes, String nom, int organisationId, int responsablId) {
        this.id = id;
        this.id=counterId;
        this.description=description;
        this.nbrPersonnes=nbrPersonnes;
        this.nom=nom;
        this.organisationId=organisationId;
        this.responsablId=responsablId;
    }
    // Organisation in string data format for database
    public String writeSystemeDeManagement() {
        return id+"#"+description+"#"+nbrPersonnes+"#"+nom+"#"+organisationId+"#"+responsablId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrPersonnes() {
        return nbrPersonnes;
    }

    public void setNbrPersonnes(int nbrPersonnes) {
        this.nbrPersonnes = nbrPersonnes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        SystemeDeManagement.counterId = counterId;
    }

    public int getResponsablId() {
        return responsablId;
    }

    public void setResponsablId(int responsablId) {
        this.responsablId = responsablId;
    }
}
