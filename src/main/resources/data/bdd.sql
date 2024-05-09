CREATE DATABASE gestion_etudiant
    CHARACTER SET utf8
    COLLATE utf8_general_ci;

-- Table utilisateurs
CREATE TABLE utilisateurs
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nom             VARCHAR(255) NOT NULL,
    prenom          VARCHAR(255) NOT NULL,
    nom_utilisateur VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe    VARCHAR(255) NOT NULL,
    email           VARCHAR(255),
    admin           BOOLEAN
);

-- Table enseignants
CREATE TABLE enseignants
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(255) NOT NULL,
    prenom    VARCHAR(255) NOT NULL,
    cin       VARCHAR(255) NOT NULL UNIQUE,
    email     VARCHAR(255),
    telephone VARCHAR(255),
    adresse   VARCHAR(255)
);

-- Table etudiants
CREATE TABLE etudiants
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(255) NOT NULL,
    prenom    VARCHAR(255) NOT NULL,
    cni       VARCHAR(255) NOT NULL UNIQUE,
    cne       VARCHAR(255) NOT NULL UNIQUE,
    email     VARCHAR(255),
    telephone VARCHAR(255),
    adresse   VARCHAR(255),
    statut    VARCHAR(255) NOT NULL
);

-- Table cours
CREATE TABLE cours
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nom           VARCHAR(255) NOT NULL,
    description   TEXT,
    enseignant_id INT,
    FOREIGN KEY (enseignant_id) REFERENCES enseignants (id)
);

-- Table cours_etudiants
CREATE TABLE cours_etudiants
(
    cours_id    INT NOT NULL,
    etudiant_id INT NOT NULL,
    PRIMARY KEY (cours_id, etudiant_id),
    FOREIGN KEY (cours_id) REFERENCES cours (id),
    FOREIGN KEY (etudiant_id) REFERENCES etudiants (id)
);

-- Table absences
CREATE TABLE absences
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    dateTime    DATETIME NOT NULL,
    etudiant_id INT      NOT NULL,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants (id)
);

-- Table notes
CREATE TABLE notes
(
    etudiant_id INT NOT NULL,
    cours_id    INT NOT NULL,
    note        DOUBLE DEFAULT 0,
    PRIMARY KEY (etudiant_id, cours_id),
    FOREIGN KEY (etudiant_id) REFERENCES etudiants (id),
    FOREIGN KEY (cours_id) REFERENCES Cours (id)
);

-- Insertion de l'utilisateur administrateur
INSERT INTO utilisateurs (nom, prenom, nom_utilisateur, mot_de_passe, email, admin)
VALUES ('AL Admin', 'Admin', 'admin', 'admin', 'admin@example.com', true);
