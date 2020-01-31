package javatest.utils;

import java.text.Normalizer;

public class StringUtils {
	
	public static String normalize(String input) {
       return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
	
	public static String normalizeAlphaNumeric(String input) {
       return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[^A-Za-z0-9]", "");
    }
}
