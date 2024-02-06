<%-- 
    Document   : listConsultation
    Created on : 5 janv. 2024, 13:44:45
    Author     : chalman
--%>
<%@page import="java.util.List"%>
<%@page import="model.facturation.*"%>
<%@page import="model.consultation.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>Dentiste</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                      <div class="navbar-header">
                        <a class="navbar-brand" href="#">
                          <img alt="Dentisterie" src="...">
                        </a>
                      </div>
                    </div>
                  </nav>
            </div>

            <div class="row consult">
                <h1>Details consultation</h1>
                <hr>
                <% if(request.getAttribute("factureConsultation") != null) { 
                    FactureConsultation factureConsultation = (FactureConsultation)request.getAttribute("factureConsultation");
                %>
                <div class="col-md-10">
                    <div class="col-md-3">
                        <p><strong>Date : </strong> <%=factureConsultation.getConsultation().getDate() %></p>
                        <p><strong>Nom :</strong> <%=factureConsultation.getConsultation().getNomClient() %></p>
                        <p><strong>Priorite :</strong> <%=factureConsultation.getConsultation().getPrioriteLetter(factureConsultation.getConsultation().getPriorite()) %></p>
                        <p><strong>Budget :</strong> <%=factureConsultation.getConsultation().getBudget() %></p>
                    </div>
                    
                    <% if(request.getAttribute("etatDentList") != null) { 
                        List<EtatDent> etatDentList = (List<EtatDent>)request.getAttribute("etatDentList");
                    %>
                    <div class="col-md-9" style="border: 1px solid black; width:500px; height: 150px;">
                        <% for(int i = 0; i < etatDentList.size(); i++) { %>
                                <button type="button" style="width: 50px; height: 50px; background-color: <%=etatDentList.get(i).getCouleur() %>; margin-top: 20px" class="btn btn-default"></button>
                                <%=etatDentList.get(i).getNom() %>
                        <% } %>
                    </div>
                    <% } %>

                </div>
               
                <div class="col-md-10">
                    <h3>Etat dent avant traitement</h3>
                    <hr>
                    <div class="btn-group" role="group" aria-label="...">
                        <% for(int i = 1; i <= 16; i++) { %>
                        <button type="button" style="width: 50px; height: 50px; background-color: <%=factureConsultation.getConsultation().getEtatDentColor(i) %>" class="btn btn-default"><%=i %></button>
                        <% } %>
                    </div>
                    <div class="btn-group" role="group" aria-label="...">
                        <% for(int i = 21; i <= 36; i++) { %>
                        <button type="button" style="width: 50px; height: 50px; background-color: <%=factureConsultation.getConsultation().getEtatDentColor(i) %>" class="btn btn-default"><%=i %></button>
                        <% } %>
                    </div>
                </div>
                    
                <div class="col-md-10">
                    <h3>Etat dent apres traitement</h3>
                    <hr>
                    <div class="btn-group" role="group" aria-label="...">
                        <% for(int i = 1; i <= 16; i++) { %>
                        <button type="button" style="width: 50px; height: 50px; background-color: <%=factureConsultation.getConsultation().getEtatDentFinalColor(i) %>" class="btn btn-default"><%=i %></button>
                        <% } %>
                    </div>
                    <div class="btn-group" role="group" aria-label="...">
                        <% for(int i = 21; i <= 36; i++) { %>
                        <button type="button" style="width: 50px; height: 50px; background-color: <%=factureConsultation.getConsultation().getEtatDentFinalColor(i) %>" class="btn btn-default"><%=i %></button>
                        <% } %>
                    </div>
                </div>
                
                <div class="col-md-10">
                    <h3>Facture</h3>
                    <table class="table">
                        <tr>
                            <th>Dent</th>
                            <th>Service</th>
                            <th>Cout</th>
                        </tr>
                        <% 
                            for(Facture facture : factureConsultation.getFactureList()) {
                        %>
                        <tr>
                            <td><%=facture.getDent().getIdDent() %></td>
                            <td><%=facture.getService().getNom() %></td>
                            <td><%=facture.getPrix() %></td>
                        </tr>
                       <% } %>
                        <tr>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td><strong><%=factureConsultation.getTotal() %></strong></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><strong>Reste budget</strong></td>
                            <td><strong><%=factureConsultation.getRestBudget() %></strong></td>
                        </tr>
                    </table>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="./ListConsultation" type="button" class="btn btn-default">Retour</a>
                    </div>
                </div>
                <% } %>
            </div>
            
        </div>
        
        <script src="css/FOU/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
