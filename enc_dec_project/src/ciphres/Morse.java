package ciphres;


import java.util.HashMap;
import java.util.Map;

public class Morse {
    private static final String[] morse = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
        "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."
    };
    private static final Map<String, Character> morseToChar = new HashMap<>();
    static {
        for (int i = 0; i < 26; i++) {
            morseToChar.put(morse[i], (char) ('A' + i));
        }
        for (int i = 26; i < 36; i++) {
            morseToChar.put(morse[i], (char) ('0' + (i - 26)));
        }
    }

    public static String morse_encrypt(String text) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase(); // Преобразуем текст в верхний регистр
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result.append(morse[c - 'A']).append(" ");
            } else if (c >= '0' && c <= '9') {
                result.append(morse[c - '0' + 26]).append(" ");
            } else if (c == ' ') {
                result.append(" / ");
            }
        }
        return result.toString().trim();
    }

    public static String morse_decrypt(String morseCode) {
        StringBuilder result = new StringBuilder();
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                Character c = morseToChar.get(letter);
                if (c != null) {
                    result.append(c);
                }
            }
            result.append(' ');
        }
        return result.toString().trim();
    }
}