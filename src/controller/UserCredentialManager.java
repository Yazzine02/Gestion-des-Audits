package controller;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserCredentialManager {
    private static final String CREDENTIALS_FILE_PATH = "src/database/credentials.txt";
    // Hash password using sha256 : raw password => hashed password
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert byte array to hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyLogin(String username, String password) {
        try{
            String hashedPassword = hashPassword(password);
            try(BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE_PATH))){
                String line;
                while( (line=reader.readLine()) != null){
                    String[] tokens = line.split("#");
                    if(tokens.length == 4 && tokens[1].equals(username) && tokens[2].equals(hashedPassword)){
                        return true;
                    }
                }
            }
            return false;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
