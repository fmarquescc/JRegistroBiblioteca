/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author fmarques
 */
public class LanguageManager {
    public static final String[][] LANGUAGES = {{"en_us", "English"},{"pt_pt","Português"}};
    public static String currentLanguage = "pt_pt";
    
    public static final Map<String, String> translations = new HashMap<>();
    
    public static void switchLanguage(String code) {
        currentLanguage = code;
        translations.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(LanguageManager.class.getResourceAsStream("/lang/" + code + ".txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] l = line.split("=");
                translations.put(l[0], l[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String translate(String key) {
        return translations.getOrDefault(key, key);
    }
    
    public static String translate(String key, Object... args) {
        return String.format(translations.getOrDefault(key, key), args);
    }
    
    static {
        switchLanguage("pt_pt");
    }
}
