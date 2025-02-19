package ciphres;

public class Affine {
    
    // Функция для шифрования текста с использованием аффинного шифра
    public static String affine_encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        
        // Проходим по каждому символу текста
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                // Применяем аффинное преобразование: E(x) = (a * x + b) % 26
                c = (char) ((a * (c - base) + b) % 26 + base);
            }
            result.append(c);
        }
        
        return result.toString();
    }
    
    // Функция для расшифровки текста с использованием аффинного шифра
    public static String affine_decrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        int a_inv = modInverse(a, 26); // Находим мультипликативное обратное a по модулю 26
        
        // Проходим по каждому символу текста
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                // Применяем обратное аффинное преобразование: D(x) = a_inv * (x - b) % 26
                c = (char) ((a_inv * ((c - base) - b + 26) % 26) + base);
            }
            result.append(c);
        }
        
        return result.toString();
    }
    
    // Функция для нахождения мультипликативного обратного числа по модулю m
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 0) {
                return x;
            }
        }
        return 1; // Если обратное не найдено, возвращаем 1 (хотя это не должно происходить для корректных значений a)
    }
}