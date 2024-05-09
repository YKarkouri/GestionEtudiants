package ma.est.gestionetudiants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.StatutEtudiant;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

public class NouvelEtudiantController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField cniField;

    @FXML
    private TextField cneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField adresseField;

    @FXML
    private ComboBox<String> statutComboBox;

    @FXML
    private void initialize() {
        statutComboBox.setValue("PREMIERE_ANNEE");
    }

    @FXML
    private void handleCreateButtonAction() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String cni = cniField.getText();
        String cne = cneField.getText();
        String email = emailField.getText();
        String telephone = telephoneField.getText();
        String adresse = adresseField.getText();
        StatutEtudiant statut = StatutEtudiant.valueOf(statutComboBox.getValue());

        if (nom.isEmpty() || prenom.isEmpty() || cni.isEmpty() || cne.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty()) {
            ControllerUtils.displayMessage("Ajout Etudiant", "Tous les champs sont obligatoires.", Alert.AlertType.ERROR);
            return;
        }

        Etudiant nouvelEtudiant = new Etudiant(nom, prenom, cni, cne, email, telephone, adresse, statut);
        boolean res = EtudiantDAO.create(nouvelEtudiant);
        if (res) {
            ControllerUtils.displayMessage("Ajout Etudiant", "Étudiant ajouté avec succès.", Alert.AlertType.INFORMATION);
            clearInputFields();
        } else {
            ControllerUtils.displayMessage("Ajout Etudiant", "Une erreur s'est produite lors de l'ajout de l'étudiant.", Alert.AlertType.ERROR);
        }
    }

    private void clearInputFields() {
        nomField.clear();
        prenomField.clear();
        cniField.clear();
        cneField.clear();
        emailField.clear();
        telephoneField.clear();
        adresseField.clear();
        statutComboBox.setValue("PREMIERE_ANNEE");
    }

    @FXML
    private void handleBackButtonAction() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        ControllerUtils.changeScene("Etudiants", "etudiants-view.fxml", stage);
    }
}
