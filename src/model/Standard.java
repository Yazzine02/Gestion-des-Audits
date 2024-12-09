package model;

public class Standard {
    private final int id;
    private static int countId = 0;
    private String description;
    private String reference;
    // Constructor for new standards (increments id)
    public Standard(String description, String reference) {
        countId++;
        this.id = countId;
        this.description = description;
        this.reference = reference;
    }
    // Constructor for temporary standard variables (does not increment id)
    public Standard(int id, String description, String reference) {
        this.id = id;
        this.description = description;
        this.reference = reference;
    }
    // Standard in string data format for database
    public String writeStandard(){
        return id+"#"+description+"#"+reference;
    }
    // toString
    @Override
    public String toString() {
        return "Standard [id=" + id + "#description=" + description + "#reference=" + reference + "]";
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Standard.countId = countId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
