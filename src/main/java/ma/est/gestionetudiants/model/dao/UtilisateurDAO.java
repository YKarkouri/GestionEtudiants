package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Utilisateur;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

    public static Utilisateur findByNomUtilisateur(String nomUtilisateur) {
        Connection connexion = ConnexionBDD.getInstance();
        Utilisateur utilisateur = null;
        try {
            String sql = "SELECT * FROM utilisateurs WHERE nom_utilisateur = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1, nomUtilisateur);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                utilisateur = new Utilisateur(
                        result.getLong("id"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("nom_utilisateur"),
                        result.getString("mot_de_passe"),
                        result.getString("email"),
                        result.getBoolean("admin")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
        }
        return utilisateur;
    }

}
