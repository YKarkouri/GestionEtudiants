package ma.est.gestionetudiants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Utilisateur;
import ma.est.gestionetudiants.model.dao.UtilisateurDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

public class LoginController {

    @FXML
    private TextField nomUtilisateurField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private void initialize() {
    }

    @FXML
    private void login() {
        String nomUtilisateur = nomUtilisateurField.getText();
        String motDePasse = motDePasseField.getText();

        Utilisateur utilisateur = UtilisateurDAO.findByNomUtilisateur(nomUtilisateur);

        if (utilisateur != null
                && nomUtilisateur.equals(utilisateur.getNomUtilisateur())
                && motDePasse.equals(utilisateur.getMotDePasse())) {
            // ControllerUtils.displayMessage("Connexion réussie", "Bienvenue, " + nomUtilisateur + "!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) nomUtilisateurField.getScene().getWindow();
            ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
        } else {
            ControllerUtils.displayMessage("Erreur de connexion", "Nom d'utilisateur ou mot de passe incorrect.", Alert.AlertType.ERROR);
        }
    }

}
