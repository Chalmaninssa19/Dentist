<%-- 
    Document   : listConsultation
    Created on : 5 janv. 2024, 13:44:45
    Author     : chalman
--%>
<%@page import="java.util.List"%>
<%@page import="model.consultation.Consultation"%>

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
            <% if(request.getAttribute("error") != null) { %>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <p class="text-danger"><%=request.getAttribute("error") %></p>
                    </div>
                </div>
            <% } %>
            <div class="row consult">
                <div class="col-md-12">
                    <h1>Liste des consultations</h1>
                    <a href="InsertConsultation1">
                        <button type="button" class="btn btn-primary">
                            <i class="glyphicon glyphicon-plus"></i>Nouvelle consultation</button>
                    </a>
                    <table class="table" style="margin-top: 30px">
                        <tr>
                            <th>N</th>
                            <th>Client</th>
                            <th>Priorite</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                        <% if(request.getAttribute("consultations") != null) { 
                            List<Consultation> consultations = (List<Consultation>)request.getAttribute("consultations");
                            for(int i = 0; i < consultations.size(); i++) {
                        %>
                        <tr>
                            <td><%=consultations.get(i).getIdConsultation() %></td>
                            <td><%=consultations.get(i).getNomClient() %></td>
                            <td><%=consultations.get(i).getPrioriteLetter(consultations.get(i).getPriorite()) %></td>
                            <td><%=consultations.get(i).getDate() %></td>
                            <td>
                                <a href="DetailsConsultation?idConsultation=<%=consultations.get(i).getIdConsultation() %>" type="button" class="btn btn-primary">Details</a>
                            </td>
                            <td>
                                <a href="DeleteConsultation?idConsultation=<%=consultations.get(i).getIdConsultation() %>" type="button" class="text-danger"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>
                        </tr>
                        <% } } %>
                    </table>
                </div>
            </div>
        </div>
        
        <script src="css/FOU/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
