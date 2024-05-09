package ma.est.gestionetudiants.model.bean;

import java.util.Objects;

public class Etudiant {
    private Long id;
    private String nom;
    private String prenom;
    private String cni;
    private String cne;
    private String email;
    private String telephone;
    private String adresse;
    private StatutEtudiant statut;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String cni, String cne, StatutEtudiant statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.cni = cni;
        this.cne = cne;
        this.statut = statut;
    }

    public Etudiant(String nom, String prenom, String cni, String cne, String email, String telephone, String adresse, StatutEtudiant statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.cni = cni;
        this.cne = cne;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.statut = statut;
    }

    public Etudiant(Long id, String nom, String prenom, String cni, String cne, StatutEtudiant statut) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cni = cni;
        this.cne = cne;
        this.statut = statut;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public StatutEtudiant getStatut() {
        return statut;
    }

    public void setStatut(StatutEtudiant statut) {
        this.statut = statut;
    }

    public String getFullname() {
        return prenom + " " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Etudiant etudiant = (Etudiant) o;
        return Objects.equals(id, etudiant.id) && cni.equals(etudiant.cni) && cne.equals(etudiant.cne);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + cni.hashCode();
        result = 31 * result + cne.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return cne + " - " + nom + " " + prenom + " ";
    }
}
