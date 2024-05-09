package ma.est.gestionetudiants.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Absence;
import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.dao.CoursDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

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
        // TODO: ControllerUtils.changeScene("Export Notes", "export-notes-view.fxml", stage);
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
    }
}
