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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akhaldi
 */

@WebServlet("/add_vehicle")
public class AddVehicleServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{      
            String code = request.getParameter("code");
            String immatriculation = request.getParameter("immatriculation");
            String modele = request.getParameter("modele");
            String dateCreation = request.getParameter("dateCreation");
            String carburant = request.getParameter("carburant");
            String consommation = request.getParameter("consommation");
            String region = request.getParameter("region");
            Dao dao = new Dao(DatabaseConnection.getConnection());
            
             if(dao.checkIfVehicleCodeExists(code) || dao.checkIfVehicleImmatriculationExists(immatriculation)){                
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Véhicule existe! ');");  
                pw.println("history.back();");  
                pw.println("</script>");
             }else{
                dao.addNewvehicle(code, immatriculation, modele, dateCreation, carburant, consommation,region);
                VehiclesServlet vehiclesServlet=new VehiclesServlet();
                vehiclesServlet.doGet(request, response);
             }        
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
