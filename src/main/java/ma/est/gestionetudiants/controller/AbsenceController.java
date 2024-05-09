package ma.est.gestionetudiants.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import ma.est.gestionetudiants.model.bean.Absence;
import ma.est.gestionetudiants.model.dao.AbsenceDAO;
import ma.est.gestionetudiants.utils.Common;
import ma.est.gestionetudiants.utils.ControllerUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AbsenceController {

    @FXML
    private TableView<Absence> absencesTable;

    @FXML
    private TableColumn<Absence, String> nomColumn;

    @FXML
    private TableColumn<Absence, String> prenomColumn;

    @FXML
    private TableColumn<Absence, String> cneColumn;

    @FXML
    private TableColumn<Absence, String> statutColumn;

    @FXML
    private TableColumn<Absence, LocalDateTime> dateTimeColumn;

    private ObservableList<Absence> absencesList = FXCollections.observableArrayList();

    public void initialize() {
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtudiant().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtudiant().getPrenom()));
        cneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtudiant().getCne()));
        statutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtudiant().getStatut().name()));

        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        dateTimeColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Absence, LocalDateTime> call(TableColumn<Absence, LocalDateTime> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(formatter.format(item));
                        }
                    }
                };
            }
        });

        List<Absence> absences = AbsenceDAO.getAll();
        absencesList.addAll(absences);
        absencesTable.setItems(absencesList);
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        ControllerUtils.changeScene("Nouvelle Absence", "nouvelle-absence-view.fxml", stage);
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ControllerUtils.changeScene("Accueil", "accueil-view.fxml", stage);
    }
}
