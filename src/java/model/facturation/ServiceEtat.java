/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.facturation;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import model.consultation.EtatDent;
import model.consultation.Service;

/**
 *
 * @author chalman
 */
@DBTable(name = "service_etat", sequenceName = "seq_service_etat")
public class ServiceEtat {
    @DBField(name="id_service_etat", isPrimaryKey = true)
    private int idServiceEtat;
    @DBField(name="id_etat_dent", isForeignKey = true)
    private EtatDent etatDent;
    @DBField(name="id_service", isForeignKey = true)
    private Service service;
    
///Getters et setters

    public int getIdServiceEtat() {
        return idServiceEtat;
    }

    public void setIdServiceEtat(int idServiceEtat) {
        this.idServiceEtat = idServiceEtat;
    }

    public EtatDent getEtatDent() {
        return etatDent;
    }

    public void setEtatDent(EtatDent etatdent) {
        this.etatDent = etatdent;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
 ///Constructors

    public ServiceEtat() {
    }

    public ServiceEtat(int idServiceEtat, EtatDent etatdent, Service service) {
        this.idServiceEtat = idServiceEtat;
        this.etatDent = etatdent;
        this.service = service;
    }

    public ServiceEtat(EtatDent etatdent, Service service) {
        this.etatDent = etatdent;
        this.service = service;
    }
    
///Fonctions
    
}
