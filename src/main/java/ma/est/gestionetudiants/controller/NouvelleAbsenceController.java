package ma.est.gestionetudiants.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Absence;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.dao.AbsenceDAO;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class NouvelleAbsenceController {
    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private ComboBox<Etudiant> etudiantComboBox;

    private ObservableList<Etudiant> etudiantsList = FXCollections.observableArrayList();

    public void initialize() {
        List<Etudiant> etudiantsFromDatabase = EtudiantDAO.getAll();
        etudiantsList.addAll(etudiantsFromDatabase);
        etudiantComboBox.setItems(etudiantsList);
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleCreateButtonAction() {
        LocalDate selectedDate = datePicker.getValue();
        String selectedHour = hourComboBox.getValue();
        Etudiant selectedEtudiant = etudiantComboBox.getValue();

        if (selectedDate != null && selectedHour != null && selectedEtudiant != null) {
            Absence absence = new Absence();
            LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, LocalTime.parse(selectedHour));
            absence.setDateTime(selectedDateTime);
            absence.setEtudiant(selectedEtudiant);

            boolean res = AbsenceDAO.create(absence);
            if (res) {
                ControllerUtils.displayMessage("Ajout Absence", "Absence ajouté avec succès.", Alert.AlertType.INFORMATION);
                clearInputFields();
            } else {
                ControllerUtils.displayMessage("Ajout Absence", "Une erreur s'est produite lors de l'ajout de l'absence.", Alert.AlertType.ERROR);
            }
        } else {
            ControllerUtils.displayMessage("Ajout Absence", "Tous les champs sont obligatoires.", Alert.AlertType.ERROR);
        }

    }

    private void clearInputFields() {
        hourComboBox.setValue(null);
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleBackButtonAction() {
        Stage stage = (Stage) etudiantComboBox.getScene().getWindow();
        ControllerUtils.changeScene("Absences", "absences-view.fxml", stage);
    }
}
