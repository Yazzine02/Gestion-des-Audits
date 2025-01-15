package controller;

import model.Correction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CorrectionController {
    private static String ORGANISATION_FILE_PATH = "src/database/corrections.txt";
    // Add organisation
    public static boolean addCorrection(Correction correction) {
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, true))) {
            writer.write(correction.writeCorrection());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read organisation
    public static List<Correction> getAllCorrections() {
        List<Correction> corrections = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(ORGANISATION_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==4){
                    Correction correction = new Correction(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
                    corrections.add(correction);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return corrections;
    }
    // Modify organisation
    public static boolean updateCorrection(int id, String name, String description, int responsableId) {
        List<Correction> corrections = getAllCorrections();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, false))){
            for (Correction correction : corrections) {
                if(correction.getId()==id){
                    correction.setName(name);
                    correction.setDescription(description);
                    correction.setResponsableId(responsableId);
                    writer.write(correction.writeCorrection());
                    writer.newLine();
                }
                else{
                    writer.write(correction.writeCorrection());
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
    public static boolean deleteCorrection(int id) {
        List<Correction> corrections = getAllCorrections();
        corrections.removeIf(correction -> correction.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH,false))){
            for(Correction correction : corrections){
                writer.write(correction.writeCorrection());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
