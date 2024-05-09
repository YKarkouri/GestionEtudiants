package ma.est.gestionetudiants.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.Note;
import ma.est.gestionetudiants.model.dao.CoursDAO;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.model.dao.NoteDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.util.List;

public class NouvelleNoteController {

    @FXML
    private ComboBox<Etudiant> etudiantComboBox;

    @FXML
    private ComboBox<Cours> coursComboBox;

    @FXML
    private Spinner<Double> noteSpinner;

    public void initialize() {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 20.0, 0.0);
        noteSpinner.setValueFactory(valueFactory);

        List<Cours> cours = CoursDAO.getAll();
        coursComboBox.getItems().addAll(cours);

        coursComboBox.setOnAction(event -> {
            Cours selectedCourse = coursComboBox.getValue();
            if (selectedCourse != null) {
                List<Etudiant> etudiants = EtudiantDAO.findByCoursId(selectedCourse.getId());
                etudiantComboBox.getItems().setAll(etudiants);
            }
        });
    }

    @FXML
    public void handleCreateButtonAction() {
        Etudiant etudiant = etudiantComboBox.getValue();
        Cours cours = coursComboBox.getValue();
        double note = noteSpinner.getValue();

        if (cours != null && etudiant != null) {
            Note nouvelleNote = new Note();
            nouvelleNote.setEtudiant(etudiant);
            nouvelleNote.setCours(cours);
            nouvelleNote.setNote(note);
            boolean res = NoteDAO.create(nouvelleNote);
            if (res) {
                ControllerUtils.displayMessage("Ajout Note", "Note ajoutée avec succès.", Alert.AlertType.INFORMATION);
                clearInputFields();
            } else {
                ControllerUtils.displayMessage("Ajout Note", "Une erreur s'est produite lors de l'ajout de la note.", Alert.AlertType.ERROR);
            }
        } else {
            ControllerUtils.displayMessage("Ajout Note", "Veuillez remplir tous les champs obligatoires.", Alert.AlertType.ERROR);
        }

    }

    private void clearInputFields() {
        noteSpinner.getValueFactory().setValue(0.0);
        etudiantComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleBackButtonAction() {
        Stage stage = (Stage) noteSpinner.getScene().getWindow();
        ControllerUtils.changeScene("Cours", "cours-view.fxml", stage);
    }
}
