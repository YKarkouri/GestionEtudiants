package ma.est.gestionetudiants.model.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cours {
    private Long id;
    private String nom;
    private String description;
    private Enseignant enseignant;
    private List<Etudiant> etudiants;

    public Cours() {}
    public Cours(String nom, String description, Enseignant enseignant) {
        this.nom = nom;
        this.description = description;
        this.enseignant = enseignant;
    }
    public Cours(Long id, String nom, String description, Enseignant enseignant) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.enseignant = enseignant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<Etudiant> getEtudiants() {
        if (etudiants == null) etudiants = new ArrayList<>();
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void addEtudiant(Etudiant etudiant) {
        if (etudiants == null) etudiants = new ArrayList<>();
        etudiants.add(etudiant);
    }

    public void removeEtudiant(Etudiant etudiant) {
        if (etudiants == null) {
            etudiants = new ArrayList<>();
            return;
        }
        etudiants.remove(etudiant);
    }

    public String getEnseignantFullName() {
        return enseignant.getPrenom() + " " + enseignant.getNom();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(nom);
        result = 31 * result + Objects.hashCode(description);
        return result;
    }

    @Override
    public String toString() {
        return nom + " - " + description;
    }
}
