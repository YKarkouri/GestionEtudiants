module ma.est.gestionetudiants {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires org.apache.poi.poi;

    opens ma.est.gestionetudiants;
    exports ma.est.gestionetudiants;

    opens ma.est.gestionetudiants.model.bean;

    exports ma.est.gestionetudiants.controller;
    opens ma.est.gestionetudiants.controller to javafx.fxml;

}