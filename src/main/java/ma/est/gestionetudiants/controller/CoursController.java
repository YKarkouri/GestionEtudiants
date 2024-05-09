package ma.est.gestionetudiants.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Note;
import ma.est.gestionetudiants.model.dao.CoursDAO;
import ma.est.gestionetudiants.model.dao.NoteDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;
import ma.est.gestionetudiants.utils.FileUtils;

import java.io.File;
import java.util.List;

public class CoursController {

    @FXML
    private TableColumn<Cours, String> fullNomEnseignantColumn;

    @FXML
    private TableColumn<Cours, String> numberEtudiantColumn;

    @FXML
    private TableView<Cours> coursTableView;

    public void initialize() {

        numberEtudiantColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty("" + cellData.getValue().getEtudiants().size()));
        fullNomEnseignantColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEnseignantFullName()));

        List<Cours> coursList = CoursDAO.getAll();

        ObservableList<Cours> observableCoursList = FXCollections.observableArrayList(coursList);

        coursTableView.setItems(observableCoursList);
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Nouveau Cours", "nouveau-cours-view.fxml", stage);
    }

    @FXML
    private void handleAddNoteButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Ajout Notes", "nouvelle-note-view.fxml", stage);
    }

    @FXML
    private void handleExportNotesButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();

        ComboBox<Cours> coursComboBox = new ComboBox<>();
        coursComboBox.getItems().addAll(CoursDAO.getAll());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choisir un cours");
        alert.setHeaderText(null);
        alert.setContentText("Choisissez un cours:");

        alert.getDialogPane().setContent(coursComboBox);

        alert.showAndWait().ifPresent(result -> {
            if (result == javafx.scene.control.ButtonType.OK) {
                Cours selectedCourse = coursComboBox.getValue();
                if (selectedCourse != null) {
                    try {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setInitialFileName(selectedCourse.getNom() + "_rapport.xlsx");
                        File file = fileChooser.showSaveDialog(stage);
                        if (file != null) {
                            List<Note> notes = NoteDAO.findByCours(selectedCourse);
                            FileUtils.exportToExcel(selectedCourse, notes, file.getAbsolutePath());
                            ControllerUtils.displayMessage("Rapport généré", "Le rapport des notes a été généré avec succès !", Alert.AlertType.INFORMATION);
                        }
                    } catch (Exception e) {
                        System.err.println("Une erreur est survenue lors de la génération du rapport: " + e.getMessage());
                        ControllerUtils.displayMessage("Erreur", "Une erreur est survenue lors de la génération du rapport.", Alert.AlertType.ERROR);
                    }
                }
            }
        });
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
    }
}
