package controller;

import model.Clause;
import model.Processus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ClauseController {
    private static final String CLAUSE_PATH_FILE = "src/database/clauses.txt";
    // Add clause
    public static boolean addClause(Clause clause) {
        // OPEN THE FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CLAUSE_PATH_FILE,true))) {
            writer.write(clause.writeClause());
            writer.newLine();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // Read clause
    public static List<Clause> getClauses() {
        List<Clause> processus = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(CLAUSE_PATH_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("#");
                if(tokens.length == 3){
                    Clause clause = new Clause(Integer.parseInt(tokens[0]),tokens[1],tokens[2]);
                    processus.add(clause);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return processus;
    }
    // Modify clause
    public static boolean updateClause(int id, String descriptionNew, String referenceNew) {
        // Get all processus stored in a list
        List<Clause> clauses = getClauses();
        // Rewrite the whole file and update the chosen site
        // OPEN THE FILE IN APPEND MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CLAUSE_PATH_FILE, false))){
            for (Clause clause : clauses) {
                if(clause.getId() == id){
                    clause.setDescription(descriptionNew);
                    clause.setReference(referenceNew);
                    writer.write(clause.writeClause());
                    writer.newLine();
                }
                else{
                    writer.write(clause.writeClause());
                    writer.newLine();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Delete clause
    public static boolean deleteClause(int id) {
        // Get all sites stored in a list
        List<Clause> clauses = getClauses();
        // Remove the site from the list of files
        clauses.removeIf(clause -> clause.getId() == id);
        // Rewrite the whole file without the deleted site
        // OPEN THE SITE IN WRITE MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CLAUSE_PATH_FILE, false))){
            for (Clause clause : clauses) {
                writer.write(clause.writeClause());
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
