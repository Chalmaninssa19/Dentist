/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.consultation;

import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.util.List;
import model.facturation.Facture;

/**
 *
 * @author chalman
 */
@DBTable(name = "consultation_dent", sequenceName = "seq_consultation_dent")
public class ConsultationDent {
    @DBField(name="id_consultation_dent", isPrimaryKey = true)
    private int idConsultation;
    
    @DBField(name="id_consultation", isForeignKey = true)
    private Consultation consultation;
    
    @DBField(name="id_dent", isForeignKey = true)
    private Dent dent;
    
    @DBField(name="etat_dent")
    private Integer etatDent;
    
    @DBField(name="etat_dent_final")
    private Integer etatDentFinal;
    
///Getters et setters
    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Dent getDent() {
        return dent;
    }

    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public Integer getEtatDent() {
        return etatDent;
    }

    public void setEtatDent(Integer etatDent) {
        this.etatDent = etatDent;
    }

    public Integer getEtatDentFinal() {
        return etatDentFinal;
    }

    public void setEtatDentFinal(Integer etatDentFinal) {
        this.etatDentFinal = etatDentFinal;
    }

///Constructors

    public ConsultationDent() {
    }
    
    public ConsultationDent(int idConsultation, Consultation consultation, Dent dent, Integer etatDent, Integer etatDentFinal) {
        this.idConsultation = idConsultation;
        this.consultation = consultation;
        this.dent = dent;
        this.etatDent = etatDent;
        this.etatDentFinal = etatDentFinal;
    }
    
    
    public ConsultationDent(Consultation consultation, Dent dent, Integer etatDent, Integer etatDentFinal) {
        this.consultation = consultation;
        this.dent = dent;
        this.etatDent = etatDent;
        this.etatDentFinal = etatDentFinal;
    }

    public ConsultationDent(Dent dent, Integer etatDent) {
        this.dent = dent;
        this.etatDent = etatDent;
    }

    
 ///Fonctions

}
