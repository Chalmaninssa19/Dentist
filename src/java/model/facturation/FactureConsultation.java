/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.facturation;

import generalisation.GenericDAO.GenericDAO;
import java.util.ArrayList;
import java.util.List;
import model.consultation.ConfigTraitement;
import model.consultation.Consultation;
import model.consultation.ConsultationDent;
import model.consultation.Dent;
import model.consultation.EtatDent;
import model.consultation.Service;

/**
 *
 * @author chalman
 */
public class FactureConsultation {
    List<Facture> factureList;
    Consultation consultation;
    Double total;
    
///Getters et setters 

    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public Double getRestBudget() {
        return getConsultation().getBudget() - getTotal();
    }
///Constructors

    public FactureConsultation() {
    }

    public FactureConsultation(List<Facture> factureList, Consultation consultation, Double total) {
        this.factureList = factureList;
        this.consultation = consultation;
        this.total = total;
    }
    
///Fonctions
    //Initialisation de l'etat dent final
    public void initializeEtatDentFinal(List<ConsultationDent> consultationDentList) {
        for(ConsultationDent consultationDent : consultationDentList) {
            consultationDent.setEtatDentFinal(consultationDent.getEtatDent());
        }
    }
    
    //Avoir la facture de la consultation
    public List<Facture> getFacture(Consultation consultation) throws Exception {
        List<Facture> factureList = new ArrayList<>();
        List<ServiceEtat> serviceEtatList = (List<ServiceEtat>) GenericDAO.getAll(ServiceEtat.class, null, null);
        List<ConfigTraitement> configList = (List<ConfigTraitement>)GenericDAO.getAll(ConfigTraitement.class, null, null);
        
        //Initialization de l'etat dent final par normal
        initializeEtatDentFinal(consultation.getConsultationDents());
        
        if(consultation.getPriorite() == 1) {   //Beaute
            orderByBeauty(consultation.getConsultationDents());
        } else {
            orderBySante(consultation.getConsultationDents());
        }
        
        for(ConsultationDent item : consultation.getConsultationDents()) {
            boolean budgetSupported = true;
            Integer etatDentInitial = item.getEtatDent();
            
            while(item.getEtatDent() != 10 && budgetSupported) {
                //Rechercher la configuration correspondant a l'etat dent
                ConfigTraitement configTraitement = getConfig(configList, item.getEtatDent());
                
                //Cout correspondant au service
                Double price = getPriceService(configTraitement.getService(), item.getDent());
                
                //Prix du facture
                Double facturePrice  = getFacturePrice(price, configTraitement, item);
                               
                if(consultation.getBudget() - facturePrice >= 0) {
                    factureList.add(new Facture(item.getDent(), configTraitement.getService(), facturePrice));
                    consultation.setBudget(consultation.getBudget() - facturePrice);
                    item.setEtatDent(configTraitement.getEtatUpdate());
                    item.setEtatDentFinal(configTraitement.getEtatUpdate());
                } else {
                    budgetSupported = false;
                }
            }
            item.setEtatDent(etatDentInitial);
        }
        
        return factureList;
    }
    
    //Prix du facture
    public Double getFacturePrice(Double price, ConfigTraitement configTraitement, ConsultationDent consultationDent) {
        if(configTraitement.getValueEtatMin() > 0) {    //Si le servcie n'est pas le remplacement
            return (configTraitement.getValueEtatMax() - consultationDent.getEtatDent()) * price;
        } 
        
        return price;
    }
    
    
    //Retourner la configuration correspondant a l'etat
    public ConfigTraitement getConfig(List<ConfigTraitement> configList, Integer etat) {
        for(ConfigTraitement item : configList) {
            if((etat >= item.getValueEtatMin() && etat < item.getValueEtatMax()) || (etat == 0 && item.getValueEtatMin() == 0)) {
                 return item;
            }
        }
        return null;
    }
    //Trier selon beaute desc
    public void orderByBeauty(List<ConsultationDent> consultationDents) throws Exception {
        for(int i = 0; i < consultationDents.size(); i++) {
            for(int j = i+1; j < consultationDents.size(); j++) {
                if(consultationDents.get(j).getDent().getPrioriteBeaute() > consultationDents.get(i).getDent().getPrioriteBeaute()) {
                    ConsultationDent dentSupposed = consultationDents.get(i);
                    consultationDents.set(i, consultationDents.get(j));
                    consultationDents.set(j, dentSupposed);
                }
            }
        }
    }
    
    //Trier selon sante desc
    public void orderBySante(List<ConsultationDent> consultationDents) throws Exception {
        for(int i = 0; i < consultationDents.size(); i++) {
            for(int j = i+1; j < consultationDents.size(); j++) {
                if(consultationDents.get(j).getDent().getPrioriteSante() > consultationDents.get(i).getDent().getPrioriteSante()) {
                    ConsultationDent dentSupposed = consultationDents.get(i);
                    consultationDents.set(i, consultationDents.get(j));
                    consultationDents.set(j, dentSupposed);
                }
            }
        }
    }
    
    //Recuperer le service etat correspondant a l'etat du dent
    public static ServiceEtat getServiceEtat(EtatDent etatDent, List<ServiceEtat> serviceEtatList) {
        for(ServiceEtat item : serviceEtatList) {
            if(item.getEtatDent().getIdEtatDent() == etatDent.getIdEtatDent()) {
                return item;
            }
        }
        
        return  null;
    }
    
    //Avoir le prix du service
    public Double getPriceService(Service service, Dent dent) {
        if(service.getIdService() == 1) {   //Nettoyage
            return dent.getCoutNettoyage();
        } else if(service.getIdService() == 2) {  //Remplacement
            return dent.getCoutRemplacement();
        }  else if(service.getIdService() == 2) {  //Reparation
            return dent.getCoutReparation();
        }
        
        return dent.getCoutEnlevement();
    }
    
    //Total du facture
    public static Double getTotal(List<Facture> factureList) {
        Double sum = 0.0;
        for(Facture item : factureList) {
            sum += item.getPrix();
        }
        
        return sum;
    }
    
    //Visualisation du facture
    public void getVisualisation(List<Facture> facture) {
        for(Facture item : facture) {
            System.out.println("Dent = "+item.getDent().getIdDent()+", Service = "+item.getService().getNom()+", Prix = "+item.getPrix()+" Ar");
        }
        System.out.println("Total = "+getTotal(facture)+" Ar");
    }
    
    //Sauvegarder la facture
    public void save() throws Exception {
        try {
            this.getConsultation().save();
            System.out.println("MAndeha tsara");
            for(Facture facture : this.getFactureList()) {
                facture.setConsultation(this.getConsultation());
                GenericDAO.save( facture, null);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
