package ma.est.gestionetudiants.model.bean;

public class Note {
    private Etudiant etudiant;
    private Cours cours;
    private double note;

    public Note(Etudiant etudiant, Cours cours, double note) {
        this.etudiant = etudiant;
        this.cours = cours;
        this.note = note;
    }

    public Note() {
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "etudiant=" + etudiant +
                ", cours=" + cours +
                ", note=" + note +
                '}';
    }
}
