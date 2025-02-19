// import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static ciphres.Affine.*;
import static ciphres.Ceasar.*;
import static ciphres.HEX.*;
import static ciphres.Morse.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Encryption/Decryption");

        // Создание элементов интерфейса
        Label functionLabel = new Label("Choose function:");
        ComboBox<String> functionComboBox = new ComboBox<>();
        functionComboBox.getItems().addAll("Encrypt", "Decrypt");

        Label cipherLabel = new Label("Choose cipher:");
        ComboBox<String> cipherComboBox = new ComboBox<>();
        cipherComboBox.getItems().addAll("Ceasar", "Morse", "HEX", "Affine");

        Label inputLabel = new Label("Input text:");
        TextField inputTextField = new TextField();

        Label shiftLabel = new Label("Shift value (for Ceasar):");
        TextField shiftTextField = new TextField();

        Label aLabel = new Label("Value a (for Affine):");
        TextField aTextField = new TextField();

        Label bLabel = new Label("Value b (for Affine):");
        TextField bTextField = new TextField();

        Button processButton = new Button("Process");
        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        // Обработка нажатия кнопки
        processButton.setOnAction(e -> {
            String function = functionComboBox.getValue();
            String cipher = cipherComboBox.getValue();
            String inputText = inputTextField.getText();
            String outputText = "";

            if (function == null || cipher == null || inputText.isEmpty()) {
                outputTextArea.setText("Please fill in all fields.");
                return;
            }

            try {
                if (function.equals("Encrypt")) {
                    switch (cipher) {
                        case "Ceasar":
                            int shift = Integer.parseInt(shiftTextField.getText());
                            outputText = ceasar_encrypt(inputText, shift);
                            break;
                        case "Morse":
                            outputText = morse_encrypt(inputText);
                            break;
                        case "HEX":
                            outputText = hex_encrypt(inputText);
                            break;
                        case "Affine":
                            int a = Integer.parseInt(aTextField.getText());
                            int b = Integer.parseInt(bTextField.getText());
                            outputText = affine_encrypt(inputText, a, b);
                            break;
                    }
                } else if (function.equals("Decrypt")) {
                    switch (cipher) {
                        case "Ceasar":
                            int shift = Integer.parseInt(shiftTextField.getText());
                            outputText = ceasar_decrypt(inputText, shift);
                            break;
                        case "Morse":
                            outputText = morse_decrypt(inputText);
                            break;
                        case "HEX":
                            outputText = hex_decrypt(inputText);
                            break;
                        case "Affine":
                            int a = Integer.parseInt(aTextField.getText());
                            int b = Integer.parseInt(bTextField.getText());
                            outputText = affine_decrypt(inputText, a, b);
                            break;
                    }
                }
            } catch (NumberFormatException ex) {
                outputTextArea.setText("Invalid number format.");
                return;
            }

            outputTextArea.setText(outputText);
        });

        // Создание и настройка макета
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(functionLabel, 0, 0);
        GridPane.setConstraints(functionComboBox, 1, 0);
        GridPane.setConstraints(cipherLabel, 0, 1);
        GridPane.setConstraints(cipherComboBox, 1, 1);
        GridPane.setConstraints(inputLabel, 0, 2);
        GridPane.setConstraints(inputTextField, 1, 2);
        GridPane.setConstraints(shiftLabel, 0, 3);
        GridPane.setConstraints(shiftTextField, 1, 3);
        GridPane.setConstraints(aLabel, 0, 4);
        GridPane.setConstraints(aTextField, 1, 4);
        GridPane.setConstraints(bLabel, 0, 5);
        GridPane.setConstraints(bTextField, 1, 5);
        GridPane.setConstraints(processButton, 1, 6);
        GridPane.setConstraints(outputTextArea, 0, 7, 2, 1);

        grid.getChildren().addAll(functionLabel, functionComboBox, cipherLabel, cipherComboBox, inputLabel, inputTextField, shiftLabel, shiftTextField, aLabel, aTextField, bLabel, bTextField, processButton, outputTextArea);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}