package ma.est.gestionetudiants.model.dao;

import ma.est.gestionetudiants.model.bean.Cours;
import ma.est.gestionetudiants.model.bean.Enseignant;
import ma.est.gestionetudiants.model.bean.Etudiant;
import ma.est.gestionetudiants.model.config.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO {

    private static final String INSERT_COURS_SQL = "INSERT INTO cours (nom, description, enseignant_id) VALUES (?, ?, ?)";
    private static final String INSERT_COURS_ETUDIANTS_SQL = "INSERT INTO cours_etudiants (cours_id, etudiant_id) VALUES (?, ?)";

    public static boolean create(Cours cours) {
        Connection connection = ConnexionBDD.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURS_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cours.getNom());
            preparedStatement.setString(2, cours.getDescription());
            preparedStatement.setLong(3, cours.getEnseignant().getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }
            try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cours.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }

            PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_COURS_ETUDIANTS_SQL);

            for (Etudiant etudiant : cours.getEtudiants()) {
                preparedStatement2.setLong(1, cours.getId());
                preparedStatement2.setLong(2, etudiant.getId());
                preparedStatement2.addBatch();
            }
            preparedStatement2.executeBatch();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du cours : " + e.getMessage());
            return false;
        }
    }

    public static Cours findById(Long id) {
        Connection connexion = ConnexionBDD.getInstance();
        Cours cours = null;
        try {
            String sql = "SELECT * FROM cours WHERE id = ?";
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                cours = new Cours();
                cours.setId(result.getLong("id"));
                cours.setNom(result.getString("nom"));
                cours.setDescription(result.getString("description"));
                Enseignant enseignant = EnseignantDAO.findById(result.getLong("enseignant_id"));
                List<Etudiant> etudiants = EtudiantDAO.findByCoursId(id);
                cours.setEtudiants(etudiants);
                cours.setEnseignant(enseignant);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du cours : " + e.getMessage());
        }
        return cours;
    }

    public static List<Cours> getAll() {
        Connection connexion = ConnexionBDD.getInstance();
        List<Cours> coursList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Cours";
            PreparedStatement statement = connexion.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Cours cours = new Cours();
                Long id = result.getLong("id");
                cours.setId(id);
                cours.setNom(result.getString("nom"));
                cours.setDescription(result.getString("description"));
                Enseignant enseignant = EnseignantDAO.findById(result.getLong("enseignant_id"));
                List<Etudiant> etudiants = EtudiantDAO.findByCoursId(id);
                cours.setEtudiants(etudiants);
                cours.setEnseignant(enseignant);
                coursList.add(cours);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des cours : " + e.getMessage());
        }
        return coursList;
    }
}
