/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.consultation;

import generalisation.GenericDAO.GenericDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.consultation.Consultation;
import model.consultation.Dent;
import model.consultation.EtatDent;
import model.facturation.Facture;
import model.facturation.FactureConsultation;

/**
 *
 * @author chalman
 */
@WebServlet(name = "InsertConsultationServlet", urlPatterns = {"/InsertConsultation"})
public class InsertConsultationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertConsultationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertConsultationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sqlDentList = "SELECT * FROM dent ORDER BY id_dent ASC";
            String sqlEtatDentList = "SELECT * FROM etat_dent ORDER BY id_etat_dent ASC";
            List<Dent> dents = (List<Dent>)GenericDAO.directQuery(Dent.class, sqlDentList, null);
            List<EtatDent> etatDents = (List<EtatDent>)GenericDAO.directQuery(EtatDent.class, sqlEtatDentList, null);

            //Lancement de la session consultation
            HttpSession session = request.getSession();
            session.setAttribute("consultation", new Consultation());
            
            request.setAttribute("dents", dents);
            request.setAttribute("etatDents", etatDents);
            request.getRequestDispatcher("./pages/consultation/insertConsultation.jsp").forward(request, response);
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String client = request.getParameter("client");
            String budget = request.getParameter("budget");
            String priorite = request.getParameter("priorite");
            String date = request.getParameter("date");
            HttpSession session = request.getSession();
            Consultation consultation = (Consultation)session.getAttribute("consultation");
            consultation.setNomClient(client);
            consultation.setBudget(budget);
            consultation.setPriorite(priorite);
            consultation.setDate(date);
            consultation.setEtat(1);
            
            FactureConsultation factureTemp = new FactureConsultation();
            List<Facture> factureList = factureTemp.getFacture(consultation);
            Double total = FactureConsultation.getTotal(factureList);
            consultation.setBudget(budget);
            factureTemp.setConsultation(consultation);
            factureTemp.setFactureList(factureList);
            factureTemp.setTotal(total);
            
            session.setAttribute("facture", factureTemp);
            
            response.sendRedirect("./Facture");
            //factureTemp.getVisualisation(facture);
            
            //consultation.save();
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
            doGet(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
