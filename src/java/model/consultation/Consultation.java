/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.consultation;

import generalisation.GenericDAO.GenericDAO;
import generalisation.annotations.DBField;
import generalisation.annotations.DBTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.facturation.Facture;

/**
 *
 * @author chalman
 */
@DBTable(name = "consultation", sequenceName = "seq_consultation")
public class Consultation {
    @DBField(name="id_consultation", isPrimaryKey = true)
    private int idConsultation;
    
    @DBField(name="nom_client")
    private String nomClient;
    
    @DBField(name="budget")
    private Double budget;
    
    @DBField(name="priorite")
    private Integer priorite;
    
    @DBField(name="date")
    private LocalDate date;
    
    @DBField(name="etat")
    private Integer etat;
    
    List<ConsultationDent> consultationDents = new ArrayList<>();

///Getters et setters
    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) throws Exception {
        if(nomClient.trim().equals("") || nomClient == null) {
            throw new Exception("Valeurs saisies ne doivent pas etre vides");
        }
        this.nomClient = nomClient;
    }

    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    public void setBudget(String budget) throws Exception {
        if(budget.trim().equals("") || budget == null) {
            throw new Exception("Valeurs saisies ne doivent pas etre vides");
        }
        Double budgetParsed;
        try {
            budgetParsed = Double.valueOf(budget);
        } catch(Exception e) {
            throw new Exception("La valeur de budget doit etre un nombre");
        }
        if(budgetParsed < 0) {
            throw new Exception("La valeur de budget doit etre positive");
        }
        this.setBudget(budgetParsed);
    }

    public Integer getPriorite() {
        return priorite;
    }
    public void setPriorite(Integer priorite) {
        this.priorite = priorite;
    }
    public void setPriorite(String priorite) throws Exception {
        try {
            this.setPriorite(Integer.valueOf(priorite));
        } catch(Exception e) {
            throw new Exception("Impossible de parser la valeur priorite");
        }
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDate(String date) throws Exception {
        try {
            this.setDate(LocalDate.parse(date));
        } catch(Exception e) {
            throw new Exception("Impossible de parser la date");
        }
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public List<ConsultationDent> getConsultationDents() {
        return consultationDents;
    }

    public void setConsultationDents(List<ConsultationDent> consultationDents) {
        this.consultationDents = consultationDents;
    }
    
///Constructors

    public Consultation() {
    }

    public Consultation(int idConsultation, String nomClient, Double budget, Integer priorite, LocalDate date, Integer etat) {
        this.idConsultation = idConsultation;
        this.nomClient = nomClient;
        this.budget = budget;
        this.priorite = priorite;
        this.date = date;
        this.etat = etat;
    }

    public Consultation(String nomClient, Double budget, Integer priorite, LocalDate date, Integer etat) {
        this.nomClient = nomClient;
        this.budget = budget;
        this.priorite = priorite;
        this.date = date;
        this.etat = etat;
    }

 ///Fonctions
      // ajouter un article dans une demande
    public ConsultationDent addEtatDent(String dent, String etatDent) throws Exception {
        Integer idDent = Integer.valueOf(dent);
        Integer etatDentParsed = Integer.valueOf(etatDent);
        
        Dent dentFinding = GenericDAO.findById(Dent.class, idDent, null);
        ConsultationDent consultationDent = new ConsultationDent(dentFinding, etatDentParsed);
        this.getConsultationDents().add(consultationDent);

        return consultationDent;
    }

    //Avoir le dent correspondant au idDent
    public Dent getDent(List<Dent> dents, int idDent) {
        for(Dent dent : dents) {
            if(dent.getIdDent() == idDent) {
                return dent;
            }
        }
        
        return null;
    }
    
    //Ajouter un etat dent
    public void addEtatDent2(String dent, String etatDent) throws Exception {
        List<Dent> dentList = (List<Dent>) GenericDAO.getAll(Dent.class, null, null);
        
        if(dent.contains("-")) {
            String [] dents = dent.split("-");
            Integer min = Integer.valueOf(dents[0]);
            Integer max = Integer.valueOf(dents[1]);
            for(int i = min; i <= max; i++) {
                Dent dentFinding = getDent(dentList, i);
                if(dentFinding != null) {
                    this.getConsultationDents().add(new ConsultationDent(dentFinding, Integer.valueOf(etatDent)));
                } else {
                    throw new Exception ("Dent saisie n'existe pas");
                }
            }
        } else if(dent.contains(";")) {
            String [] dents = dent.split(";");
            String [] etatDents = etatDent.split(",");

            for(int i = 0; i < dents.length; i++) {
                Dent dentFinding = getDent(dentList, Integer.valueOf(dents[i]));
                if(dentFinding != null) {
                    this.getConsultationDents().add(new ConsultationDent(dentFinding, Integer.valueOf(etatDents[i])));
                } else {
                    throw new Exception ("Dent saisie n'existe pas");
                }
            }
        }
    }
    
     // supprimer l'etat dent
    public void deleteEtatDent(String dent) throws Exception {
        int idDent = Integer.valueOf(dent);
        for (int i = 0; i < this.getConsultationDents().size(); i++) {
            if (this.getConsultationDents().get(i).getDent().getIdDent() == idDent) {
                this.getConsultationDents().remove(i);
            }
        }
    }

    // Pour avoir les informations a propos d'etat dent
    public void getInformation() {
        for (ConsultationDent consultationDent : this.getConsultationDents()) {
            System.out.println("Dent = " +consultationDent.getDent().getIdDent()+ ", etat dent = "+consultationDent.getEtatDent()+", etat dent final = "+consultationDent.getEtatDentFinal());
        }
    }
    
    //Sauvgarder la consultation
    public void save() throws Exception {
        try {
             GenericDAO.save(this, null);
            for(ConsultationDent consultationDent : this.getConsultationDents()) {
                consultationDent.setConsultation(this);
                GenericDAO.save(consultationDent, null);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //Avoir la lettre priorite
    public String getPrioriteLetter(Integer value) {
        if(value == 1) {
            return "Beaute";
        }
        
        return "Sante";
    }
    
    //Couleur de l'etat de dent
    public String getEtatDentColor(int idDent) throws Exception {
        for(ConsultationDent item : this.getConsultationDents()) {
            if(item.getDent().getIdDent() == idDent) {
                return getColor(item.getEtatDent());
            }
        }
        
        return "green";
    }
    
    //Avoir la couleur  d'un etat dent
    public String getColor(int etatDent) {
        if(etatDent == 0) {
            return "red";
        } 
        if(etatDent >= 1 && etatDent < 3) {
            return "gray";
        }
        if(etatDent >=3 && etatDent < 7) {
            return "blue";
        }
        if(etatDent >= 7 && etatDent < 10) {
            return "yellow";
        }
        
        return "green";
    }
    
    //Couleur de l'etat de dent final
    public String getEtatDentFinalColor(int idDent) throws Exception {
        for(ConsultationDent item : this.getConsultationDents()) {
            if(item.getDent().getIdDent() == idDent) {
                return getColor(item.getEtatDentFinal());
            }
        }
        
        return "green";
    }
}
