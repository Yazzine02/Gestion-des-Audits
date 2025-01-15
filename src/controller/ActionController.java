package controller;

import model.Action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActionController {
    private static String ORGANISATION_FILE_PATH = "src/database/actions.txt";
    // Add organisation
    public static boolean addAction(Action action) {
        // OPEN FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, true))) {
            writer.write(action.writeAction());
            writer.newLine();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Read organisation
    public static List<Action> getAllActions() {
        List<Action> actions = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(ORGANISATION_FILE_PATH))) {
            String line;
            while((line= reader.readLine())!=null){
                String[] tokens = line.split("#");
                if(tokens.length==8){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Action action = new Action(Integer.parseInt(tokens[0]), tokens[1], tokens[2], LocalDate.parse(tokens[3]),LocalDate.parse(tokens[4]), LocalDate.parse(tokens[5]), LocalDate.parse(tokens[6]),Integer.parseInt(tokens[7]));
                    actions.add(action);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return actions;
    }
    // Modify organisation
    public static boolean updateAction(int id, String name, String description, LocalDate dateDebutReelle, LocalDate dateDebutPrevue, LocalDate dateFinReelle, LocalDate dateFinPrevue, int responsableId) {
        List<Action> actions = getAllActions();
        // OPEN FILE IN WRITE MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH, false))){
            for (Action action : actions) {
                if(action.getId()==id){
                    action.setName(name);
                    action.setDescription(description);
                    action.setDateDebutReelle(dateDebutReelle);
                    action.setDateDebutPrevue(dateDebutPrevue);
                    action.setDateFinReelle(dateFinReelle);
                    action.setDateFinPrevue(dateFinPrevue);
                    action.setResponsableId(responsableId);
                    writer.write(action.writeAction());
                    writer.newLine();
                }
                else{
                    writer.write(action.writeAction());
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
    public static boolean deleteAction(int id) {
        List<Action> actions = getAllActions();
        actions.removeIf(action -> action.getId()==id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ORGANISATION_FILE_PATH,false))){
            for(Action action : actions){
                writer.write(action.writeAction());
                writer.newLine();
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
