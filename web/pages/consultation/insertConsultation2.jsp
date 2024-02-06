<%-- 
    Document   : insertConsultation
    Created on : 5 janv. 2024, 13:45:03
    Author     : chalman
--%>
<%@page import="java.util.List"%>
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
                <div class="col-md-12">
                    <h1>Nouvelle consultation</h1>
                    <div class="col-md-12">
                        <div class="btn-group" role="group" aria-label="...">
                            <% for(int i = 1; i <= 16; i++) { %>
                            <button type="button" style="width: 50px; height: 50px" class="btn btn-default"><%=i %></button>
                            <% } %>
                        </div>
                        <div class="btn-group" role="group" aria-label="...">
                            <% for(int i = 21; i <= 36; i++) { %>
                            <button type="button" style="width: 50px; height: 50px" class="btn btn-default"><%=i %></button>
                            <% } %>
                        </div>
                    </div>
                        
                    <div class="col-md-12" style="margin-top: 30px">
                        <form class="form-horizontal" action="./VoirFacture" method="POST">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Dent</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="dent" name="dent" id="dentInput">
                                </div>
                                <label class="col-sm-2 control-label">Etat</label>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" placeholder="etat dent" name="etatDent" id="etatDentInput">
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" onclick="addNewEtatDent2()" class="btn btn-primary">Ajouter</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-3"></div>
                                <div class="col-sm-3">
                                    <table class="table table-bordered" style="text-align: center" id="etatDentList">
                                        <tr>
                                            <th>Dent</th>
                                            <th>Etat</th>
                                        </tr>
                                        <% if(request.getSession().getAttribute("consultation") != null) { 
                                            Consultation consultation = (Consultation)request.getSession().getAttribute("consultation");
                                            for(int i = 0; i < consultation.getConsultationDents().size(); i++) {
                                        %>
                                        <tr>
                                            <td><%=consultation.getConsultationDents().get(i).getDent().getIdDent() %></td>
                                            <td><%=consultation.getConsultationDents().get(i).getEtatDent() %></td>
                                        </tr>
                                        <% } } %>
                                    </table>                            
                                </div>
                            </div>
                            <div class="form-group">
                              <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-primary">Voir facture</button>
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
                        </form>        
                    </div>
                        
                        
                </div>
            </div>
        </div>
        
        <script src="./assets/js/consultation/consult.js"></script>
        <script src="./assets/js/jquery.min.js"></script>

    </body>
</html>
