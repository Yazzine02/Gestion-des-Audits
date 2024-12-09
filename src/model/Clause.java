package model;

public class Clause {
    private final int id;
    private static int counterId = 0;
    private String description;
    private String reference;
    // Constructor for new clause (increments id)
    public Clause(String description, String reference) {
        counterId++;
        this.id = counterId;
        this.description = description;
        this.reference = reference;
    }
    // Constructor for temporary clause variables (does not increment id)
    public Clause(int id, String description, String reference) {
        this.id = id;
        this.description = description;
        this.reference = reference;
    }
    // Clause in string data format for database
    public String writeClause() {
        return id+"#"+description+"#"+reference;
    }
    // toString
    @Override
    public String toString() {
        return "Clause [id=" + id + "#description=" + description + "#reference=" + reference + "]";
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Clause.counterId = counterId;
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
