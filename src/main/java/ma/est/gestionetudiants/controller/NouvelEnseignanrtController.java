package ma.est.gestionetudiants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Enseignant;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.StatutEtudiant;
import ma.est.gestionetudiants.model.dao.EnseignantDAO;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

public class NouvelEnseignanrtController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField cinField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField adresseField;


    @FXML
    private void handleCreateButtonAction() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        String cin = cinField.getText();
        String telephone = telephoneField.getText();
        String adresse = adresseField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || cin.isEmpty() || telephone.isEmpty() || adresse.isEmpty()) {
            ControllerUtils.displayMessage("Ajout Enseignant", "Tous les champs sont obligatoires.", Alert.AlertType.ERROR);
            return;
        }

        Enseignant nouvelEnseignant = new Enseignant(nom, prenom, email, cin, telephone, adresse);
        boolean res = EnseignantDAO.create(nouvelEnseignant);
        if (res) {
            ControllerUtils.displayMessage("Ajout Enseignant", "Enseignant ajouté avec succès.", Alert.AlertType.INFORMATION);
            clearInputFields();
        } else {
            ControllerUtils.displayMessage("Ajout Enseignant", "Une erreur s'est produite lors de l'ajout de l'enseignant.", Alert.AlertType.ERROR);
        }
    }

    private void clearInputFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        cinField.clear();
        telephoneField.clear();
        adresseField.clear();
    }

    @FXML
    private void handleBackButtonAction() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        ControllerUtils.changeScene("Enseignants", "enseignants-view.fxml", stage);
    }
}
