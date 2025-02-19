package ciphres;

public class HEX {
    
    // Функция для шифрования текста в HEX
    public static String hex_encrypt(String text) {
        StringBuilder hexString = new StringBuilder();
        
        // Проходим по каждому символу текста
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Преобразуем символ в его HEX представление
            String hex = Integer.toHexString(c);
            // Добавляем HEX представление символа к результату
            hexString.append(hex);
        }
        
        return hexString.toString();
    }
    
    // Функция для расшифровки текста из HEX
    public static String hex_decrypt(String hex) {
        StringBuilder result = new StringBuilder();
        
        // Проходим по каждому HEX символу (по два символа за раз)
        for (int i = 0; i < hex.length(); i += 2) {
            // Извлекаем два символа HEX
            String hexChar = hex.substring(i, i + 2);
            // Преобразуем HEX символ в десятичное значение
            int decimal = Integer.parseInt(hexChar, 16);
            // Преобразуем десятичное значение в символ
            result.append((char) decimal);
        }
        
        return result.toString();
    }
}