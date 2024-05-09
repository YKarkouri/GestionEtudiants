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
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.StatutEtudiant;
import ma.est.gestionetudiants.model.dao.EtudiantDAO;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.util.List;

public class EtudiantsController {

    @FXML
    private TableView<Etudiant> etudiantsTable;

    @FXML
    private TableColumn<Etudiant, String> nomColumn;
    @FXML
    private TableColumn<Etudiant, String> prenomColumn;
    @FXML
    private TableColumn<Etudiant, String> cniColumn;
    @FXML
    private TableColumn<Etudiant, String> cneColumn;
    @FXML
    private TableColumn<Etudiant, String> emailColumn;
    @FXML
    private TableColumn<Etudiant, String> telephoneColumn;
    @FXML
    private TableColumn<Etudiant, String> adresseColumn;
    @FXML
    private TableColumn<Etudiant, StatutEtudiant> statutColumn;

    private ObservableList<Etudiant> etudianstData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cniColumn.setCellValueFactory(new PropertyValueFactory<>("cni"));
        cneColumn.setCellValueFactory(new PropertyValueFactory<>("cne"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        statutColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));

        List<Etudiant> etudiants = EtudiantDAO.getAll();
        etudianstData.addAll(etudiants);

        etudiantsTable.setItems(etudianstData);
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Nouvel Etudiant", "nouvel-etudiant-view.fxml", stage);
    }
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
    }

    @FXML
    private void handleRechercheButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // TODO: ControllerUtils.changeScene("Recherche Etudiants", "recherche-etudiants-view.fxml", stage);
    }
}
