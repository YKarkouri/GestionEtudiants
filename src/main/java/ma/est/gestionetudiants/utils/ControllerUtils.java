package ma.est.gestionetudiants.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import ma.est.gestionetudiants.GestionEtudiantsApp;

import java.io.IOException;

public class ControllerUtils {

    public static void changeScene(String title, String fxmlFileName, Stage currentStage) {
        try {
            currentStage.close();
            FXMLLoader loader = new FXMLLoader(GestionEtudiantsApp.class.getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1080, 720);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors de la redirectToPage: " + e.getMessage());
        }
    }

    public static void displayMessage(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
