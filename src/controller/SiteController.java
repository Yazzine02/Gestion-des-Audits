package controller;

import model.Site;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SiteController {
    private static final String SITES_FILE_PATH = "src/database/sites.txt";
    // Add Site
    public static boolean addSite(Site site) {
        // OPEN THE FILE IN WRITE APPEND MODE
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(SITES_FILE_PATH,true))) {
            writer.write(site.writeSite());
            writer.newLine();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // Read Sites
    public static List<Site> getSites() {
        List<Site> sites = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(SITES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("#");
                if(tokens.length == 4){
                    Site site = new Site(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3]);
                    sites.add(site);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sites;
    }
    // Delete site by id
    public static boolean deleteSite(int id) {
        // Get all sites stored in a list
        List<Site> sites = getSites();
        // Remove the site from the list of files
        sites.removeIf(site -> site.getId() == id);
        // Rewrite the whole file without the deleted site
        // OPEN THE SITE IN WRITE MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(SITES_FILE_PATH, false))){
            for (Site site : sites) {
                writer.write(site.writeSite());
                writer.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Modify site
    public static boolean updateSite(int id, String adresseNew, String descriptionNew, String nameNew) {
        // Get all sites stored in a list
        List<Site> sites = getSites();
        // Rewrite the whole file and update the chosen site
        // OPEN THE FILE IN APPEND MODE WITH NO APPEND
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(SITES_FILE_PATH, false))){
            for (Site site : sites) {
                if(site.getId() == id){
                    site.setAdresse(adresseNew);
                    site.setDescription(descriptionNew);
                    site.setName(nameNew);
                    writer.write(site.writeSite());
                    writer.newLine();
                }
                else{
                    writer.write(site.writeSite());
                    writer.newLine();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
