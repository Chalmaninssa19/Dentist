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
@DBTable(name = "config_traitement", sequenceName = "seq_config_traitement")
public class ConfigTraitement {
    @DBField(name="id_config_traitement", isPrimaryKey = true)
    private Integer idConfigTraitement;
    
    @DBField(name="value_etat_min")
    private Integer valueEtatMin;
    
    @DBField(name="value_etat_max")
    private Integer valueEtatMax;
    
    @DBField(name="id_service", isForeignKey = true)
    private Service service;
    
    @DBField(name="etat_after")
    private Integer etatUpdate;
    
    @DBField(name="id_etat_dent", isForeignKey = true)
    private EtatDent etatDent;
    
///Getters et setters

    public Integer getIdConfigTraitement() {
        return idConfigTraitement;
    }

    public void setIdConfigTraitement(Integer idConfigTraitement) {
        this.idConfigTraitement = idConfigTraitement;
    }

    public Integer getValueEtatMin() {
        return valueEtatMin;
    }

    public void setValueEtatMin(Integer valueEtatMin) {
        this.valueEtatMin = valueEtatMin;
    }

    public Integer getValueEtatMax() {
        return valueEtatMax;
    }

    public void setValueEtatMax(Integer valueEtatMax) {
        this.valueEtatMax = valueEtatMax;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getEtatUpdate() {
        return etatUpdate;
    }

    public void setEtatUpdate(Integer etatUpdate) {
        this.etatUpdate = etatUpdate;
    }

    public EtatDent getEtatDent() {
        return etatDent;
    }

    public void setEtatDent(EtatDent etatDent) {
        this.etatDent = etatDent;
    }
    
///Constructors

    public ConfigTraitement() {
    }

    public ConfigTraitement(Integer idConfigTraitement, Integer valueEtatMin, Integer valueEtatMax, Service service, Integer etatUpdate, EtatDent etatDent) {
        this.idConfigTraitement = idConfigTraitement;
        this.valueEtatMin = valueEtatMin;
        this.valueEtatMax = valueEtatMax;
        this.service = service;
        this.etatUpdate = etatUpdate;
        this.etatDent = etatDent;
    }

    public ConfigTraitement(Integer valueEtatMin, Integer valueEtatMax, Service service, Integer etatUpdate, EtatDent etatDent) {
        this.valueEtatMin = valueEtatMin;
        this.valueEtatMax = valueEtatMax;
        this.service = service;
        this.etatUpdate = etatUpdate;
        this.etatDent = etatDent;
    }
    
///Fonctions
    
}
