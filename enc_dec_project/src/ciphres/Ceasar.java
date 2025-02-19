package ciphres;

public class Ceasar {
    public static String ceasar_encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }

    public static String ceasar_decrypt(String text, int shift) {
        return ceasar_encrypt(text, 26 - shift);
    }
}