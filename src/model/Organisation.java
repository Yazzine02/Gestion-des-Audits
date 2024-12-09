package model;

public class Organisation {
    private final int id;
    private static int counterId;
    private String name;
    private String address;
    // Constructor for new organisation (increments id)
    public Organisation(String name, String address) {
        counterId++;
        this.id=counterId;
        this.name = name;
        this.address = address;
    }
    // Constructor for temporary organisation variables (does not increment id)
    public Organisation(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    // Organisation in string data format for database
    public String writeOrganisation(){
        return id+"#"+name+"#"+address;
    }
    // toString
    @Override
    public String toString(){
        return "Organisation [id="+id+"#name="+name+"#address="+address+"]";
    }
    // Getters and setters
    public int getId() {
        return id;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Organisation.counterId = counterId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
