<%-- 
    Document   : listConsultation
    Created on : 5 janv. 2024, 13:44:45
    Author     : chalman
--%>
<%@page import="java.util.List"%>
<%@page import="model.facturation.*"%>

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
                <div class="col-md-12">
                    <h1>Facture</h1>
                    <% if(request.getAttribute("facture") != null) { 
                        FactureConsultation factureConsultation = (FactureConsultation)request.getAttribute("facture");
                    %>
                    <p>Date : <%=factureConsultation.getConsultation().getDate() %></p>
                    <p>Client : <%=factureConsultation.getConsultation().getNomClient() %></p>
                    <p>Priorite : <%=factureConsultation.getConsultation().getPrioriteLetter(factureConsultation.getConsultation().getPriorite()) %></p>
                    <p>Budget : <%=factureConsultation.getConsultation().getBudget() %></p>

                    <table class="table">
                        <tr>
                            <th>Dent</th>
                            <th>Service</th>
                            <th>Prix</th>
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
                    <% } %>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a href="./ValiderFacture" type="button" class="btn btn-primary">Valider</a>
                        <a href="./InsertConsultation1" type="button" class="btn btn-default">Annuler</a>
                    </div>
                </div>
                <% if(request.getAttribute("error") != null) { %>
                 <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <p class="text-danger"><%=request.getAttribute("error") %></p>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
        
        <script src="css/FOU/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
