package ma.est.gestionetudiants.model.bean;

import java.time.LocalDateTime;

public class Absence {
    private Long id;
    private LocalDateTime dateTime;
    private Etudiant etudiant;

    public Absence(Long id, LocalDateTime dateTime, Etudiant etudiant) {
        this.id = id;
        this.dateTime = dateTime;
        this.etudiant = etudiant;
    }

    public Absence(LocalDateTime dateTime, Etudiant etudiant) {
        this.dateTime = dateTime;
        this.etudiant = etudiant;
    }

    public Absence() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", etudiant=" + etudiant +
                '}';
    }
}
