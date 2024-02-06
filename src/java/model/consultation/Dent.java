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
@DBTable(name = "dent", sequenceName = "")
public class Dent {
    @DBField(name="id_dent", isPrimaryKey = true)
    private int idDent;
    
    @DBField(name="priorite_sante")
    private Integer prioriteSante;
    
    @DBField(name="priorite_beaute")
    private Integer prioriteBeaute;
    
    @DBField(name="cout_nettoyage")
    private Double coutNettoyage;
    
    @DBField(name="cout_remplacement")
    private Double coutRemplacement;
    
    @DBField(name="cout_enlevement")
    private Double coutEnlevement;
    
    @DBField(name="cout_reparation")
    private Double coutReparation;
///Getters et setters 
    public int getIdDent() {
        return idDent;
    }

    public void setIdDent(int idDent) {
        this.idDent = idDent;
    }

    public Integer getPrioriteSante() {
        return prioriteSante;
    }

    public void setPrioriteSante(Integer prioriteSante) {
        this.prioriteSante = prioriteSante;
    }

    public Integer getPrioriteBeaute() {
        return prioriteBeaute;
    }

    public void setPrioriteBeaute(Integer prioriteBeaute) {
        this.prioriteBeaute = prioriteBeaute;
    }

    public Double getCoutNettoyage() {
        return coutNettoyage;
    }

    public void setCoutNettoyage(Double coutNettoyage) {
        this.coutNettoyage = coutNettoyage;
    }

    public Double getCoutRemplacement() {
        return coutRemplacement;
    }

    public void setCoutRemplacement(Double coutRemplacement) {
        this.coutRemplacement = coutRemplacement;
    }

    public Double getCoutEnlevement() {
        return coutEnlevement;
    }

    public void setCoutEnlevement(Double coutEnlevement) {
        this.coutEnlevement = coutEnlevement;
    }

    public Double getCoutReparation() {
        return coutReparation;
    }

    public void setCoutReparation(Double coutReparation) {
        this.coutReparation = coutReparation;
    }
    
    
///Constructors

    public Dent() {
    }

    public Dent(Integer prioriteSante, Integer prioriteBeaute, Double coutNettoyage, Double coutRemplacement, Double coutEnlevement, Double coutReparation) {
        this.prioriteSante = prioriteSante;
        this.prioriteBeaute = prioriteBeaute;
        this.coutNettoyage = coutNettoyage;
        this.coutRemplacement = coutRemplacement;
        this.coutEnlevement = coutEnlevement;
        this.coutReparation = coutReparation;
    }
    
    public Dent(int idDent, Integer prioriteSante, Integer prioriteBeaute, Double coutNettoyage, Double coutRemplacement, Double coutEnlevement, Double coutReparation) {
        this.idDent = idDent;
        this.prioriteSante = prioriteSante;
        this.prioriteBeaute = prioriteBeaute;
        this.coutNettoyage = coutNettoyage;
        this.coutRemplacement = coutRemplacement;
        this.coutEnlevement = coutEnlevement;
        this.coutReparation = coutReparation;
    }
    
///Fonctions

    
}
