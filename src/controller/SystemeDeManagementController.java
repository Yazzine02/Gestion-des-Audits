package controller;

import model.SystemeDeManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SystemeDeManagementController {
    private static String ORGANISATION_FILE_PATH = "src/database/systemeDeManagements.txt";
    // Add
    public static boolean addSystemeDeManagements(SystemeDeManagement systemeDeManagement) {
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, true))) {
            writer.write(systemeDeManagement.writeSystemeDeManagement());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read
    public static List<SystemeDeManagement> getAllSystemeDeManagements() {
        List<SystemeDeManagement> systemeDeManagements = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(ORGANISATION_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==6){
                    SystemeDeManagement systemeDeManagement = new SystemeDeManagement(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), tokens[3],Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                    systemeDeManagements.add(systemeDeManagement);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return systemeDeManagements;
    }
    // Modify
    public static boolean updateSystemeDeManagement(int id, String description, int nbrPersonnes, String nom, int organisationId, int responsablId) {
        List<SystemeDeManagement> systemeDeManagements = getAllSystemeDeManagements();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, false))){
            for (SystemeDeManagement systemeDeManagement : systemeDeManagements) {
                if(systemeDeManagement.getId()==id){
                    systemeDeManagement.setDescription(description);
                    systemeDeManagement.setNbrPersonnes(nbrPersonnes);
                    systemeDeManagement.setNom(nom);
                    systemeDeManagement.setOrganisationId(organisationId);
                    systemeDeManagement.setResponsablId(responsablId);
                    writer.write(systemeDeManagement.writeSystemeDeManagement());
                    writer.newLine();
                }
                else{
                    writer.write(systemeDeManagement.writeSystemeDeManagement());
                    writer.newLine();
                }
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Delete
    public static boolean deleteSystemeDeManagement(int id) {
        List<SystemeDeManagement> systemeDeManagements = getAllSystemeDeManagements();
        systemeDeManagements.removeIf(systemeDeManagement -> systemeDeManagement.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH,false))){
            for(SystemeDeManagement systemeDeManagement : systemeDeManagements){
                writer.write(systemeDeManagement.writeSystemeDeManagement());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
