/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.consultation;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;

/**
 *
 * @author chalman
 */
@DBTable(name = "etat_dent", sequenceName = "")
public class EtatDent {
    @DBField(name="id_etat_dent", isPrimaryKey = true)
    int idEtatDent;
    @DBField(name="nom")
    String nom;
    @DBField(name="couleur")
    String couleur;
    
///Getters et setters

    public int getIdEtatDent() {
        return idEtatDent;
    }

    public void setIdEtatDent(int idEtatDent) {
        this.idEtatDent = idEtatDent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
///Constructors

    public EtatDent() {
    }

    public EtatDent(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }

    public EtatDent(int idEtatDent, String nom, String couleur) {
        this.idEtatDent = idEtatDent;
        this.nom = nom;
        this.couleur = couleur;
    }
    
///Fonctions

    
}
