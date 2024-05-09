package ma.est.gestionetudiants.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import ma.est.gestionetudiants.utils.ControllerUtils;

public class AccueilController {

    @FXML
    private void handleEtudiantsClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        ControllerUtils.changeScene("Etuidiants", "etudiants-view.fxml", stage);
    }

    @FXML
    private void handleEnsignantsClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Enseignants", "enseignants-view.fxml", stage);
    }

    @FXML
    private void handleAbsencesClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Absences", "absences-view.fxml", stage);
    }

    @FXML
    private void handleCoursClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Cours", "cours-view.fxml", stage);
    }

    @FXML
    private void handleLogoutClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Authentification", "login-view.fxml", stage);
    }
}
