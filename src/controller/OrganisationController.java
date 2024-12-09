package controller;

import model.Organisation;
import model.Processus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class OrganisationController {
    private static String ORGANISATION_FILE_PATH = "src/database/organisations.txt";
    // Add organisation
    public static boolean addOrganisation(Organisation organisation) {
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, true))) {
            writer.write(organisation.writeOrganisation());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read organisation
    public static List<Organisation> getAllOrganisations() {
        List<Organisation> organisations = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(ORGANISATION_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==3){
                    Organisation organisation = new Organisation(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
                    organisations.add(organisation);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return organisations;
    }
    // Modify organisation
    public static boolean updateOrganisation(int id, String name, String address) {
        List<Organisation> organisations = getAllOrganisations();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, false))){
            for (Organisation organisation : organisations) {
                if(organisation.getId()==id){
                    organisation.setName(name);
                    organisation.setAddress(address);
                    writer.write(organisation.writeOrganisation());
                    writer.newLine();
                }
                else{
                    writer.write(organisation.writeOrganisation());
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
    public static boolean deleteOrganisation(int id) {
        List<Organisation> organisations = getAllOrganisations();
        organisations.removeIf(organisation -> organisation.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH,false))){
            for(Organisation organisation : organisations){
                writer.write(organisation.writeOrganisation());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
