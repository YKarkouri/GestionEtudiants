package ma.est.gestionetudiants.model.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
    private static final String URL = "jdbc:mysql://localhost:8889/gestion_etudiants";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "root";

    private static Connection connexion;

    private ConnexionBDD() {
    }

    public static synchronized Connection getInstance() {
        if (connexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
                System.out.println("Connexion réussie !");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            }
        }
        return connexion;
    }

    public static void closeConnexion() {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion fermée !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

}
