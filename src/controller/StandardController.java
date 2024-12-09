package controller;

import model.Organisation;
import model.Standard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class StandardController {
    private static String STANDARD_FILE_PATH = "src/database/standards.txt";
    // Add organisation
    public static boolean addStandard(Standard standard){
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(STANDARD_FILE_PATH, true))) {
            writer.write(standard.writeStandard());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read organisation
    public static List<Standard> getAllStandards() {
        List<Standard> standards = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(STANDARD_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==3){
                    Standard standard = new Standard(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
                    standards.add(standard);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return standards;
    }
    // Modify organisation
    public static boolean updateStandard(int id, String description, String reference) {
        List<Standard> standards = getAllStandards();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(STANDARD_FILE_PATH, false))){
            for (Standard standard : standards) {
                if(standard.getId()==id){
                    standard.setDescription(description);
                    standard.setReference(reference);
                    writer.write(standard.writeStandard());
                    writer.newLine();
                }
                else{
                    writer.write(standard.writeStandard());
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
    public static boolean deleteStandard(int id) {
        List<Standard> standards = getAllStandards();
        standards.removeIf(organisation -> organisation.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(STANDARD_FILE_PATH,false))){
            for(Standard standard : standards){
                writer.write(standard.writeStandard());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
