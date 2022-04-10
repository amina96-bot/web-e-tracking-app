/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Assignment;
import model.Kilometrage;
import model.Payment;
import model.Trajet;
import model.User;

/**
 *
 * @author akhaldi
 */
@WebServlet("/update_demande_chargement")
public class UpdateDemandeChargement extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Dao dao = new Dao(DatabaseConnection.getConnection());
        String kmId = request.getParameter("kmId");
        String traitement = request.getParameter("traitement");
        switch (traitement){
                case "accepter" : 
                dao.accepterDemandeChargement(kmId);
                
                Kilometrage km=dao.getKilometrageById(Integer.valueOf(kmId));
                User user=dao.getUserById(km.getUsr_id());
                
                String to="akhaldi@starbrandsspa.com";
                String from = "notification@starbrandsspa.com";
                String host = "mail.starbrandsspa.com";
                Properties properties = System.getProperties();
                properties.setProperty("mail.smtp.host", host);
                Session session = Session.getDefaultInstance(properties);
                try {
                   MimeMessage message = new MimeMessage(session);
                   message.setFrom(new InternetAddress(from));
                   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                   message.setSubject("Nouvelle demande approuvée en attente de chargement");
                   message.setText("Bonjour, Une nouvelle demande vient d'être approuvée par le chef du parc, veuillez procéder au chargement; l'utilisateur: "+user.getEmployee().getRaisonSociale());
                   message.setSentDate(new Date());
                   Transport.send(message);
                } catch (MessagingException mex) {
                   mex.printStackTrace();
                } 
                
                break;
        }
        AssignmentsServlet assignmentsServlet=new AssignmentsServlet();
        assignmentsServlet.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Dao dao = new Dao(DatabaseConnection.getConnection());
        String kmId = request.getParameter("kmId");
        String traitement = request.getParameter("traitement");
        switch (traitement){         
            case "refuser":
                String motif = request.getParameter("motif");
                dao.refuserDemandeChargement(kmId,motif);
            break; 
            case "charger" :
                String montant = request.getParameter("montant");   
                dao.chargerDemandeChargement(kmId, Double.valueOf(montant));
            break; 
        }
        AssignmentsServlet assignmentsServlet=new AssignmentsServlet();
        assignmentsServlet.doGet(request, response);
    }

  
}
