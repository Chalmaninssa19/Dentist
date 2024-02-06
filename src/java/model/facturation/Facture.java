/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.facturation;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.consultation.Consultation;
import model.consultation.Dent;
import model.consultation.Service;

/**
 *
 * @author chalman
 */
@DBTable(name = "facture", sequenceName = "seq_facture")
public class Facture {
    @DBField(name="id_facture", isPrimaryKey = true)
    int idFacture;
    
    @DBField(name="id_dent", isForeignKey = true)
    Dent dent;
    
    @DBField(name="id_service", isForeignKey = true)
    Service service;
    
    @DBField(name="prix")
    Double prix;
    
    @DBField(name="id_consultation", isForeignKey = true)
    Consultation consultation;

///Getters et setters
    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int idFacture) {    
        this.idFacture = idFacture;
    }

    public Dent getDent() {
        return dent;
    }

    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
    
///Constructors

    public Facture() {
    }

    public Facture(int idFacture, Dent dent, Service service, Double prix, Consultation consultation) {
        this.idFacture = idFacture;
        this.dent = dent;
        this.service = service;
        this.prix = prix;
        this.consultation = consultation;
    }

    public Facture(Dent dent, Service service, Double prix, Consultation consultation) {
        this.dent = dent;
        this.service = service;
        this.prix = prix;
        this.consultation = consultation;
    }

    public Facture(Dent dent, Service service, Double prix) {
        this.dent = dent;
        this.service = service;
        this.prix = prix;
    }
    
///Fonctions
   
}
