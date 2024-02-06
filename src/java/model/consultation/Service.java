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
@DBTable(name = "service", sequenceName = "")
public class Service {
    @DBField(name="id_service", isPrimaryKey = true)
    int idService;
    @DBField(name="nom")
    String nom;
    
///Getters et setters

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
///Constructors

    public Service() {
    }

    public Service(int idService, String nom) {
        this.idService = idService;
        this.nom = nom;
    }

    public Service(String nom) {
        this.nom = nom;
    }
    
}
