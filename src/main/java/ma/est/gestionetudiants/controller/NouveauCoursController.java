package ma.est.gestionetudiants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Enseignant;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.dao.CoursDAO;
import ma.est.gestionetudiants.model.dao.EnseignantDAO;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.util.List;

public class NouveauCoursController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private ComboBox<Enseignant> enseignantComboBox;

    @FXML
    private ListView<Etudiant> etudiantsListView;

    public void initialize() {
        etudiantsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Enseignant> enseignants = EnseignantDAO.getAll();
        enseignantComboBox.getItems().addAll(enseignants);

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        etudiantsListView.getItems().addAll(etudiants);
    }

    @FXML
    public void handleAddButtonAction() {
        String nom = nomTextField.getText();
        String description = descriptionTextField.getText();
        Enseignant enseignant = enseignantComboBox.getValue();
        List<Etudiant> etudiantsSelectionnes = etudiantsListView.getSelectionModel().getSelectedItems();
        if (nom != null && !nom.isEmpty() && enseignant != null && !etudiantsSelectionnes.isEmpty()) {
            Cours nouveauCours = new Cours();
            nouveauCours.setNom(nom);
            nouveauCours.setDescription(description);
            nouveauCours.setEnseignant(enseignant);
            nouveauCours.setEtudiants(etudiantsSelectionnes);

            boolean res = CoursDAO.create(nouveauCours);
            if (res) {
                ControllerUtils.displayMessage("Ajout Cours", "Cours ajouté avec succès.", Alert.AlertType.INFORMATION);
                clearInputFields();
            } else {
                ControllerUtils.displayMessage("Ajout Cours", "Une erreur s'est produite lors de l'ajout du cours.", Alert.AlertType.ERROR);
            }
        } else {
            ControllerUtils.displayMessage("Ajout Cours", "Veuillez remplir tous les champs obligatoires.", Alert.AlertType.ERROR);
        }

    }

    private void clearInputFields() {
        nomTextField.clear();
        descriptionTextField.clear();
        enseignantComboBox.setValue(null);
        etudiantsListView.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBackButtonAction() {
        Stage stage = (Stage) nomTextField.getScene().getWindow();
        ControllerUtils.changeScene("Cours", "cours-view.fxml", stage);
    }
}
