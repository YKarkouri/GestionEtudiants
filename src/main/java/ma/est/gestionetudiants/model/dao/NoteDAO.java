package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.Note;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    public static boolean create(Note note) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            boolean exist = anyMatchByEtudiantAndCours(note.getEtudiant().getId(), note.getCours().getId());
            if (exist) {
                return update(note);
            }
            String sql = "INSERT INTO notes (etudiant_id, cours_id, note) VALUES (?, ?, ?)";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, note.getEtudiant().getId());
            statement.setLong(2, note.getCours().getId());
            statement.setDouble(3, note.getNote());
            statement.executeUpdate();
            System.out.println("Note ajoutée avec succès !");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la note : " + e.getMessage());
            return false;
        }
    }

    public static boolean anyMatchByEtudiantAndCours(Long etudiantId, Long coursId) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "SELECT * FROM notes WHERE etudiant_id = ? AND cours_id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, etudiantId);
            statement.setLong(2, coursId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la note : " + e.getMessage());
            return false;
        }
    }

    public static boolean update(Note note) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "UPDATE notes SET note = ? WHERE etudiant_id = ? AND cours_id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setDouble(1, note.getNote());
            statement.setLong(2, note.getEtudiant().getId());
            statement.setLong(3, note.getCours().getId());
            statement.executeUpdate();
            System.out.println("Note mise à jour avec succès !");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de la note : " + e.getMessage());
            return false;
        }
    }

    public static List<Note> getAll() {
        Connection connexion = ConnexionBDD.getInstance();
        List<Note> notes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM notes";
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Note note = new Note();
                Etudiant etudiant = new EtudiantDAO().findById(result.getLong("etudiant_id"));
                Cours cours = new CoursDAO().findById(result.getLong("cours_id"));
                note.setEtudiant(etudiant);
                note.setCours(cours);
                note.setNote(result.getDouble("note"));
                notes.add(note);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des notes : " + e.getMessage());
        }
        return notes;
    }

    public static List<Note> findByCours(Cours cours) {
        Connection connexion = ConnexionBDD.getInstance();
        List<Note> notes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM notes where cours_id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, cours.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Note note = new Note();
                Etudiant etudiant = EtudiantDAO.findById(result.getLong("etudiant_id"));
                note.setEtudiant(etudiant);
                note.setCours(cours);
                note.setNote(result.getDouble("note"));
                notes.add(note);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des notes : " + e.getMessage());
        }
        return notes;
    }
}
