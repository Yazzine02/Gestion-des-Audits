package controller;

import model.Processus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ProcessusController {
    private static String PROCESSUS_FILE_PATH = "src/database/processus.txt";
    // Add processus
    public static boolean addProcessus(Processus processus) {
        // OPEN THE FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PROCESSUS_FILE_PATH,true))) {
            writer.write(processus.writeProcessus());
            writer.newLine();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // Read processus
    public static List<Processus> getProcessus() {
        List<Processus> processus = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(PROCESSUS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("#");
                if(tokens.length == 5){
                    Processus processus1 = new Processus(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4]));
                    processus.add(processus1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return processus;
    }
    // Modify processus
    public static boolean updateProcessus(int id, String descriptionNew, String nameNew, int organisationIdNew, int responsableIdNew) {
        // Get all processus stored in a list
        List<Processus> processus = getProcessus();
        // Rewrite the whole file and update the chosen site
        // OPEN THE FILE IN APPEND MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PROCESSUS_FILE_PATH, false))){
            for (Processus processus1 : processus) {
                if(processus1.getId() == id){
                    processus1.setDescription(descriptionNew);
                    processus1.setName(nameNew);
                    processus1.setOrganisationId(organisationIdNew);
                    processus1.setResponsableId(responsableIdNew);
                    writer.write(processus1.writeProcessus());
                    writer.newLine();
                }
                else{
                    writer.write(processus1.writeProcessus());
                    writer.newLine();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Delete processus
    public static boolean deleteProcessus(int id) {
        // Get all sites stored in a list
        List<Processus> processus = getProcessus();
        // Remove the site from the list of files
        processus.removeIf(processus1 -> processus1.getId() == id);
        // Rewrite the whole file without the deleted site
        // OPEN THE SITE IN WRITE MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(PROCESSUS_FILE_PATH, false))){
            for (Processus processus1 : processus) {
                writer.write(processus1.writeProcessus());
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
