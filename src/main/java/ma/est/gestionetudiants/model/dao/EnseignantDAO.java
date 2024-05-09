package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Enseignant;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAO {

    public static boolean create(Enseignant enseignant) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "INSERT INTO enseignants (nom, prenom, email, cin, telephone, adresse) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, enseignant.getNom());
            statement.setString(2, enseignant.getPrenom());
            statement.setString(3, enseignant.getEmail());
            statement.setString(4, enseignant.getCin());
            statement.setString(5, enseignant.getTelephone());
            statement.setString(6, enseignant.getAdresse());
            statement.executeUpdate();
            System.out.println("Enseignant ajouté avec succès !");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'enseignant : " + e.getMessage());
            return false;
        }
    }

    public static Enseignant findById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        Enseignant enseignant = null;
        try {
            String sql = "SELECT * FROM enseignants WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                enseignant = new Enseignant();
                enseignant.setId(result.getLong("id"));
                enseignant.setNom(result.getString("nom"));
                enseignant.setPrenom(result.getString("prenom"));
                enseignant.setEmail(result.getString("email"));
                enseignant.setCin(result.getString("cin"));
                enseignant.setTelephone(result.getString("telephone"));
                enseignant.setAdresse(result.getString("adresse"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'enseignant : " + e.getMessage());
        }
        return enseignant;
    }

    public static void update(Enseignant enseignant) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "UPDATE enseignants SET nom = ?, prenom = ?, email = ?, cin = ?, telephone = ?, adresse = ? WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, enseignant.getNom());
            statement.setString(2, enseignant.getPrenom());
            statement.setString(3, enseignant.getEmail());
            statement.setString(4, enseignant.getCin());
            statement.setString(5, enseignant.getTelephone());
            statement.setString(6, enseignant.getAdresse());
            statement.setLong(7, enseignant.getId());
            statement.executeUpdate();
            System.out.println("Enseignant mis à jour avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'enseignant : " + e.getMessage());
        }
    }

    public static void deleteById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        try {
            String sql = "DELETE FROM enseignants WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Enseignant supprimé avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'enseignant : " + e.getMessage());
        }
    }

    public static List<Enseignant> getAll() {
        Connection connexion = ConnexionBDD.getInstance();
        List<Enseignant> enseignants = new ArrayList<>();
        try {
            String sql = "SELECT * FROM enseignants";
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(result.getLong("id"));
                enseignant.setNom(result.getString("nom"));
                enseignant.setPrenom(result.getString("prenom"));
                enseignant.setEmail(result.getString("email"));
                enseignant.setCin(result.getString("cin"));
                enseignant.setTelephone(result.getString("telephone"));
                enseignant.setAdresse(result.getString("adresse"));
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des enseignants : " + e.getMessage());
        }
        return enseignants;
    }
}
