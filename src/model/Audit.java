package model;

import java.time.LocalDate;

public class Audit {
    private int id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String intitule;
    private String status;
    private int systemeExigenceId;
    private String type;
    private int auditeurId;
    private static int counterId;

    // Constructor for new audit (increments id)
    public Audit(LocalDate dateDebut, LocalDate dateFin, String intitule, String status, int systemeExigenceId, String type, int auditeurId) {
        counterId++;
        this.id=counterId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.intitule = intitule;
        this.status = status;
        this.systemeExigenceId = systemeExigenceId;
        this.type = type;
        this.auditeurId = auditeurId;
    }
    // Constructor for temporary organisation variables (does not increment id)
    public Audit(int id, LocalDate dateDebut, LocalDate dateFin, String intitule, String status, int systemeExigenceId, String type, int auditeurId) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.intitule = intitule;
        this.status = status;
        this.systemeExigenceId = systemeExigenceId;
        this.type = type;
        this.auditeurId = auditeurId;
    }
    // Organisation in string data format for database
    public String writeAudit(){
        return id+"#"+dateDebut+"#"+dateFin+"#"+intitule+"#"+status+"#"+systemeExigenceId+"#"+type+"#"+auditeurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSystemeExigenceId() {
        return systemeExigenceId;
    }

    public void setSystemeExigenceId(int systemeExigenceId) {
        this.systemeExigenceId = systemeExigenceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Audit.counterId = counterId;
    }

    public int getAuditeurId() {
        return auditeurId;
    }

    public void setAuditeurId(int auditeurId) {
        this.auditeurId = auditeurId;
    }
}
