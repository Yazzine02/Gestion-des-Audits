package controller;

import model.Action;
import model.Audit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuditController {
    private static String ORGANISATION_FILE_PATH = "src/database/audits.txt";
    // Add audit
    public static boolean addAudit(Audit audit) {
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, true))) {
            writer.write(audit.writeAudit());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read organisation
    public static List<Audit> getAllAudits() {
        List<Audit> audits = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(ORGANISATION_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==8){
                    Audit audit = new Audit(Integer.parseInt(tokens[0]), LocalDate.parse(tokens[1]), LocalDate.parse(tokens[2]), tokens[3], tokens[4], Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7]));
                    audits.add(audit);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return audits;
    }
    // Modify organisation
    public static boolean updateAudit(int id, LocalDate dateDebut, LocalDate dateFin, String intitule, String status, int systemeExigenceId, String type, int auditeurId) {
        List<Audit> audits = getAllAudits();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, false))){
            for (Audit audit : audits) {
                if(audit.getId()==id){
                    audit.setDateDebut(dateDebut);
                    audit.setDateFin(dateFin);
                    audit.setIntitule(intitule);
                    audit.setStatus(status);
                    audit.setSystemeExigenceId(systemeExigenceId);
                    audit.setType(type);
                    audit.setAuditeurId(auditeurId);
                    writer.write(audit.writeAudit());
                    writer.newLine();
                }
                else{
                    writer.write(audit.writeAudit());
                    writer.newLine();
                }
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Delete organisation
    public static boolean deleteAudit(int id) {
        List<Audit> audits = getAllAudits();
        audits.removeIf(audit -> audit.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH,false))){
            for(Audit audit : audits){
                writer.write(audit.writeAudit());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
