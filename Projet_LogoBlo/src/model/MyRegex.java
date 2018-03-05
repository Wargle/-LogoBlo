/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.*;

/**
 * Classe utilitaire pour faciliter l'utlisation de regex
 * @author Alexis Arnould
 */
public class MyRegex {
    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Teste si une chaine de caractère correspond à un pattern
     * @param regex : le pattern
     * @param match : la chaine à tester
     * @return 
     */
    public static boolean isMatch(String regex, String match) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(match);
        if(matcher.find()) {
            return true;
        }
        return false;
    }    
}
