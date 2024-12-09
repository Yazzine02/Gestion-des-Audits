package model;

public class Site {
    private final int id;
    private static int idCounter = 0;
    private String adresse;
    private String description;
    private String name;
    // Constructor for new sites (increments id)
    public Site(String adresse, String description, String name) {
        idCounter += 1;
        this.id = idCounter;
        this.adresse = adresse;
        this.description = description;
        this.name = name;
        System.out.println("New site object created, id counter: "+idCounter);
    }
    // Constructor for temporary site variables (does not increment id)
    public Site(int id, String adresse, String description, String name) {
        this.id = id;
        this.adresse = adresse;
        this.description = description;
        this.name = name;
        System.out.println("New site object created, id counter: "+idCounter);
    }
    // Site in string data format for database
    public String writeSite(){
        return id+"#"+adresse+"#"+description+"#"+name;
    }
    // toString
    @Override
    public String toString() {
        return "Site [id=" + id + "#adresse=" + adresse + "#description="+description + "#name=" + name + "]";
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Site.idCounter = idCounter;
    }
}
