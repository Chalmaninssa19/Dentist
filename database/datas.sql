CREATE DATABASE dentiste;
\c dentiste;

CREATE TABLE dent (
    id_dent INTEGER PRIMARY KEY,
    priorite_sante INTEGER,
    priorite_beaute INTEGER,
    cout_nettoyage DOUBLE PRECISION,
    cout_remplacement DOUBLE PRECISION,
    cout_enlevement DOUBLE PRECISION
);

CREATE SEQUENCE seq_consultation; 
CREATE TABLE consultation(
    id_consultation integer primary key default nextval('seq_consultation'),
    nom_client varchar(100),
    budget DOUBLE PRECISION,
    priorite INTEGER,
    date DATE,
    etat INTEGER
);

CREATE SEQUENCE seq_consultation_dent;
CREATE TABLE consultation_dent (
    id_consultation_dent integer primary key default nextval('seq_consultation_dent'),
    id_consultation INTEGER,
    id_dent INTEGER,
    etat_dent INTEGER,
    etat_dent_final INTEGER,
    FOREIGN KEY(id_consultation) REFERENCES consultation(id_consultation),
    FOREIGN KEY(id_dent) REFERENCES dent(id_dent)
);

CREATE TABLE etat_dent (
    id_etat_dent INTEGER PRIMARY KEY,
    nom VARCHAR(50)
);

CREATE TABLE service (
    id_service INTEGER PRIMARY KEY,
    nom VARCHAR(50)
);

CREATE SEQUENCE seq_service_etat;
CREATE TABLE service_etat (
    id_service_etat integer primary key default nextval('seq_service_etat'),
    id_etat_dent INTEGER,
    id_service INTEGER,
    FOREIGN KEY(id_etat_dent) REFERENCES etat_dent(id_etat_dent),
    FOREIGN KEY(id_service) REFERENCES service(id_service)
);

CREATE SEQUENCE seq_facture;
CREATE TABLE facture (
    id_facture integer primary key default nextval('seq_facture'),
    id_dent INTEGER,
    id_service INTEGER,
    prix DOUBLE PRECISION,
    id_consultation INTEGER,
    FOREIGN KEY (id_dent) REFERENCES dent(id_dent),
    FOREIGN KEY (id_service) REFERENCES service(id_service),
    FOREIGN KEY (id_consultation) REFERENCES consultation(id_consultation)
);

CREATE SEQUENCE seq_config_traitement;
CREATE TABLE config_traitement (
    id_config_traitement integer primary key default nextval('seq_config_traitement'),
    value_etat_min INTEGER,
    value_etat_max INTEGER,
    id_service INTEGER,
    etat_after INTEGER,
    id_etat_dent INTEGER,
    FOREIGN KEY (id_service) REFERENCES service(id_service),
    FOREIGN KEY (id_etat_dent) REFERENCES etat_dent(id_etat_dent)
);