package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.bean.StatutEtudiant;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    public static boolean create(Etudiant etudiant) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "INSERT INTO etudiants (nom, prenom, cni, cne, email, telephone, adresse, statut) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getCni());
            statement.setString(4, etudiant.getCne());
            statement.setString(5, etudiant.getEmail());
            statement.setString(6, etudiant.getTelephone());
            statement.setString(7, etudiant.getAdresse());
            statement.setString(8, etudiant.getStatut().toString());
            statement.executeUpdate();
            System.out.println("Étudiant ajouté avec succès !");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
            return false;
        }
    }

    public static Etudiant findById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        Etudiant etudiant = null;
        try {
            String sql = "SELECT * FROM etudiants WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                etudiant = new Etudiant();
                etudiant.setId(result.getLong("id"));
                etudiant.setNom(result.getString("nom"));
                etudiant.setPrenom(result.getString("prenom"));
                etudiant.setCni(result.getString("cni"));
                etudiant.setCne(result.getString("cne"));
                etudiant.setEmail(result.getString("email"));
                etudiant.setTelephone(result.getString("telephone"));
                etudiant.setAdresse(result.getString("adresse"));
                etudiant.setStatut(StatutEtudiant.valueOf(result.getString("statut")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'étudiant : " + e.getMessage());
        }
        return etudiant;
    }

    public static void update(Etudiant etudiant) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "UPDATE etudiants SET nom = ?, prenom = ?, cni = ?, cne = ?, email = ?, telephone = ?, adresse = ?, statut = ? WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getCni());
            statement.setString(4, etudiant.getCne());
            statement.setString(5, etudiant.getEmail());
            statement.setString(6, etudiant.getTelephone());
            statement.setString(7, etudiant.getAdresse());
            statement.setString(8, etudiant.getStatut().toString());
            statement.setLong(9, etudiant.getId());
            statement.executeUpdate();
            System.out.println("Étudiant mis à jour avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
        }
    }

    public static void deleteById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "DELETE FROM etudiants WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Étudiant supprimé avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
        }
    }

    public static List<Etudiant> getAll() {
        Connection connexion = ConnexionBDD.getInstance();
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            String sql = "SELECT * FROM etudiants";
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(result.getLong("id"));
                etudiant.setNom(result.getString("nom"));
                etudiant.setPrenom(result.getString("prenom"));
                etudiant.setCni(result.getString("cni"));
                etudiant.setCne(result.getString("cne"));
                etudiant.setEmail(result.getString("email"));
                etudiant.setTelephone(result.getString("telephone"));
                etudiant.setAdresse(result.getString("adresse"));
                etudiant.setStatut(StatutEtudiant.valueOf(result.getString("statut")));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des étudiants : " + e.getMessage());
        }
        return etudiants;
    }

    public static List<Etudiant> findByCoursId(Long coursId) {
        Connection connexion = ConnexionBDD.getInstance();
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            String sql = "SELECT et.id, et.nom, et.prenom, et.cni, et.cne, et.email, et.telephone, et.adresse, et.statut" +
                    " FROM etudiants et" +
                    " JOIN cours_etudiants cet ON et.id = cet.etudiant_id" +
                    " WHERE cet.cours_id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, coursId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(result.getLong("id"));
                etudiant.setNom(result.getString("nom"));
                etudiant.setPrenom(result.getString("prenom"));
                etudiant.setCni(result.getString("cni"));
                etudiant.setCne(result.getString("cne"));
                etudiant.setEmail(result.getString("email"));
                etudiant.setTelephone(result.getString("telephone"));
                etudiant.setAdresse(result.getString("adresse"));
                etudiant.setStatut(StatutEtudiant.valueOf(result.getString("statut")));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des etudiants : " + e.getMessage());
        }
        return etudiants;
    }
}
