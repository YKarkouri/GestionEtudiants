package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Absence;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbsenceDAO {

    public static boolean create(Absence absence) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            boolean exist = anyMatchByEtudiantAndDateTime(absence.getEtudiant().getId(), absence.getDateTime());
            if (!exist) {
                String sql = "INSERT INTO absences (dateTime, etudiant_id) VALUES (?, ?)";
                PreparedStatement statement = connexion.prepareStatement(sql);
                statement.setObject(1, absence.getDateTime());
                statement.setLong(2, absence.getEtudiant().getId());
                statement.executeUpdate();
                System.out.println("Absence ajoutée avec succès !");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'absence : " + e.getMessage());
            return false;
        }
    }

    public static boolean anyMatchByEtudiantAndDateTime(Long etudiantId, LocalDateTime dateTime) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "SELECT * FROM absences WHERE etudiant_id = ? AND dateTime = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, etudiantId);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            statement.setTimestamp(2, timestamp);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'absence : " + e.getMessage());
            return false;
        }
    }

    public static void deleteById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "DELETE FROM absences WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Absence supprimée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'absence : " + e.getMessage());
        }
    }

    public static List<Absence> getAll() {
        Connection connexion = ConnexionBDD.getInstance();
        List<Absence> absences = new ArrayList<>();
        try {
            String sql = "SELECT * FROM absences";
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Absence absence = new Absence();
                absence.setId(result.getLong("id"));
                absence.setDateTime(result.getObject("dateTime", LocalDateTime.class));
                Etudiant etudiant = EtudiantDAO.findById(result.getLong("etudiant_id"));
                absence.setEtudiant(etudiant);
                absences.add(absence);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des absences : " + e.getMessage());
        }
        return absences;
    }

    public static List<Absence> findByEtudiant(Long idEtudiant) {
        Connection connexion = ConnexionBDD.getInstance();
        List<Absence> absences = new ArrayList<>();
        try {
            String sql = "SELECT * FROM absences WHERE etudiant_id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, idEtudiant);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Absence absence = new Absence();
                absence.setId(result.getLong("id"));
                absence.setDateTime(result.getObject("dateTime", LocalDateTime.class));
                Etudiant etudiant = EtudiantDAO.findById(result.getLong("etudiant_id"));
                absence.setEtudiant(etudiant);
                absences.add(absence);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des absences de l'étudiant : " + e.getMessage());
        }
        return absences;
    }
}
