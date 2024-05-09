package ma.est.gestionetudiants.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.est.gestionetudiants.model.bean.Enseignant;
import ma.est.gestionetudiants.model.dao.EnseignantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.util.List;

public class EnseignantsController {

    @FXML
    private TableView<Enseignant> enseignantsTable;

    @FXML
    private TableColumn<Enseignant, String> nomColumn;

    @FXML
    private TableColumn<Enseignant, String> prenomColumn;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TableColumn<Enseignant, String> cinColumn;

    @FXML
    private TableColumn<Enseignant, String> telephoneColumn;

    @FXML
    private TableColumn<Enseignant, String> adresseColumn;

    private ObservableList<Enseignant> enseignantsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        List<Enseignant> enseignants = EnseignantDAO.getAll();
        enseignantsList.addAll(enseignants);
        enseignantsTable.setItems(enseignantsList);
    }

    @FXML
    private void returnAccueilAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
    }

    @FXML
    private void createNewEnseignantAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Nouvel Enseignant", "nouvel-enseignant-view.fxml", stage);
    }
}
