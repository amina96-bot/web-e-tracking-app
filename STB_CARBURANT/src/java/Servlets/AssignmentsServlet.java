/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Assignment;
import model.Employee;
import model.Gps;
import model.Kilometrage;
import model.Payment;
import model.Trajet;
import model.User;
import model.Vehicle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author akhaldi
 */
@WebServlet("/assignments")
public class AssignmentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      String action = request.getParameter("action");
      
        if(action==null){
            action="list";
        }
        String id;
        
        Dao dao = new Dao(DatabaseConnection.getConnection());
        Dao dao2 = new Dao(DatabaseConnection.getConnection2());  
        
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        User user=(User) session.getAttribute("user");     
   
        String destPage = "";
        
        switch (action) {
    case "list" :
            List<Assignment> assignments =new ArrayList();
            if(user.getRoles().contains("administrateur")){
                assignments = dao.getAllAssignments(); 
            }else  if(user.getRoles().contains("validator")){
                assignments = dao.getAllAssignmentsByRegion(user.getEmployee().getRegion()); 
            }
            request.setAttribute("assignments", assignments);                         
            destPage = "view/assignments.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;             
        case "detail" :                       
            id = request.getParameter("id"); 
            
            List<Kilometrage> kilometrages = dao.getAllKilometrage(Integer.valueOf(id));
            request.setAttribute("kilometrages", kilometrages);                   
                                
            List<Payment> payments = dao.getAllPayment(Integer.valueOf(id));
            request.setAttribute("payments", payments);
            
            List<Trajet> trajets = dao.getAllTrajet(Integer.valueOf(id));        
            request.setAttribute("trajets", trajets); 
            
            request.setAttribute("assignmentId", id);           
            Assignment assignment =dao.getAssignmentById(Integer.valueOf(id));            
            request.setAttribute("assignment", assignment);
                
            destPage = "view/detail-assignment.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            
        break;
        case "detail-trajet":
            String trajetId = request.getParameter("trajetId");
            Trajet trajet=dao.getTrajetById(Integer.valueOf(trajetId));                      
            String assignmentId = request.getParameter("assignmentId");
            List<Gps> gps=null;
            if(trajet.getType().equals("0") && trajet.getKilometrageArrivee().equals("0.0")){
                
                 gps= dao.getAllGps2(Integer.valueOf(assignmentId),  Integer.valueOf(trajetId) ); 
                 System.out.println("1111111111111111111   "+gps.size());
            }else{
                
                gps=dao.getAllGps(Integer.valueOf(assignmentId),  Integer.valueOf(trajetId) ); 
                System.out.println("2222222222222222222   "+gps.size());
            }
            System.out.println("********************   "+gps.size());
             request.setAttribute("gps", gps);
             request.setAttribute("trajet", trajet);
             request.setAttribute("assignmentId", assignmentId);
            
            destPage = "view/detail-trajet.jsp"; 
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;          
        
        case "detail-kilometrage":
            String kilometrageId = request.getParameter("kilometrageId");           
            Kilometrage kilometrage=dao.getKilometrageById(Integer.valueOf(kilometrageId));                     
            assignmentId = request.getParameter("assignmentId");
            request.setAttribute("kilometrage", kilometrage);
            
            destPage = "view/detail-kilometrage.jsp"; 
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;
            
        case "traiter-demande":
            String kmId = request.getParameter("kilometrageId");           
            Kilometrage km=dao.getKilometrageById(Integer.valueOf(kmId));                     
            assignmentId = request.getParameter("assignmentId");
        
            request.setAttribute("kilometrage", km);
            request.setAttribute("assignmentId", assignmentId);
            
            destPage = "view/traiter_demande.jsp"; 
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        break;  
        case "charger-demande":
            kmId = request.getParameter("kilometrageId");           
            km=dao.getKilometrageById(Integer.valueOf(kmId));                     
            assignmentId = request.getParameter("assignmentId");
            request.setAttribute("kilometrage", km);
            request.setAttribute("assignmentId", assignmentId);
            
            destPage = "view/charger_demande.jsp"; 
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        break;          
        case "detail-payment":
            String paymentId = request.getParameter("paymentId");
            Payment payment=dao.getPaymentById(Integer.valueOf(paymentId));                   
            assignmentId = request.getParameter("assignmentId");    
     
            request.setAttribute("payment", payment); 
            destPage = "view/detail-payment.jsp"; 
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;           
        case "add":
            List<User> users = dao.getAllUsers();       
            for(int i=0;i<users.size();i++){
                Employee employee=dao2.getEmployeeById(Integer.valueOf( users.get(i).getEmployeeId()));
                users.get(i).setEmployee(employee);
            }
            request.setAttribute("users", users);
            
            List<Vehicle> vehicles = dao.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
                       
            destPage = "view/add-assignment.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;
        case "update":
            id = request.getParameter("id");
             assignment=dao.getAssignmentById(Integer.valueOf(id));
            assignment.setAffectationDate(assignment.getAffectationDate().substring(0, 10)+"T"+assignment.getAffectationDate().substring(11, 16));
            if(!assignment.isActive()){
                assignment.setAffectationEndDate(assignment.getAffectationEndDate().substring(0, 10)+"T"+assignment.getAffectationEndDate().substring(11, 16));
            }
            
            users = dao.getAllUsers();
             for(int i=0;i<users.size();i++){
                Employee employee=dao2.getEmployeeById(Integer.valueOf( users.get(i).getEmployeeId()));
                users.get(i).setEmployee(employee);
            }
            request.setAttribute("users", users);
            
            vehicles = dao.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
            
            request.setAttribute("assignment", assignment);
            
            destPage = "view/update-assignment.jsp";
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;
        case "delete":
            id = request.getParameter("id");
            
            assignment=dao.getAssignmentById(Integer.valueOf(id));
            
            if(assignment.isActive()){              
                response.setContentType("text/html");
                PrintWriter pw=response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Impossible de supprimer une affectation qui est active! ');");  
                pw.println("history.back();");  
                pw.println("</script>");               
            }else{
               dao.deleteAssignment(id);    
                assignments = dao.getAllAssignments();     
                for(int i=0;i<assignments.size();i++){
                     Employee employee=dao2.getEmployeeById(Integer.valueOf( assignments.get(i).getUser().getEmployeeId()));
                     assignments.get(i).getUser().setEmployee(employee);
                 }

                request.setAttribute("assignments", assignments);                         
                destPage = "view/assignments.jsp";  
                dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);  
            }        
            break;
            
        case "print" :            
            id = request.getParameter("id");
            assignment=dao.getAssignmentById(Integer.valueOf(id));  
            OutputStream output, output2;
            try {
                //generate assignment pdf 
                JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("D:\\jasper\\assignment.jasper");
                Map<String, Object> params = new HashMap<String, Object>();         
                params.put("vehicleId", assignment.getVehicle().getId());
                params.put("userId", assignment.getUser().getId());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,DatabaseConnection.getConnection());
                File file = new File("D:\\STB_CARBURANT\\reports");
                if (!file.exists()) {
                    file.mkdir();
                    file.setWritable(true);
                } 
                 output = new FileOutputStream(new File("D:\\STB_CARBURANT\\reports\\"+"assignment"+id+".pdf")); 
                JasperExportManager.exportReportToPdfStream(jasperPrint, output) ;
                
                // generate engagement pdf 
                JasperReport jasperReport2 = (JasperReport) JRLoader.loadObjectFromFile("D:\\jasper\\engagement.jasper");
                Map<String, Object> params2 = new HashMap<String, Object>();         
                params2.put("assignmentId", assignment.getId());
                JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, params2,DatabaseConnection.getConnection());
                File file2 = new File("D:\\STB_CARBURANT\\reports");
                if (!file2.exists()) {
                    file2.mkdir();
                    file2.setWritable(true);
                }
                 output2 = new FileOutputStream(new File("D:\\STB_CARBURANT\\reports\\"+"engagement"+id+".pdf")); 
                JasperExportManager.exportReportToPdfStream(jasperPrint2, output2); 
                            
            } catch (JRException ex) {
                Logger.getLogger(AssignmentsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
            assignments = dao.getAllAssignments(); 
            for(int i=0;i<assignments.size();i++){
                Employee employee=dao2.getEmployeeById(Integer.valueOf( assignments.get(i).getUser().getEmployeeId()));
                assignments.get(i).getUser().setEmployee(employee);
            }
            
            long bytes=Files.size(Paths.get("D:\\STB_CARBURANT\\reports\\"+"assignment"+id+".pdf"));
            long bytes2=Files.size(Paths.get("D:\\STB_CARBURANT\\reports\\"+"engagement"+id+".pdf"));
            
            while(bytes==0 || bytes2==0){               
            }
           
            request.setAttribute("id", id);          
            request.setAttribute("assignments", assignments);                         
            destPage = "view/documents.jsp";  
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;
      
        default:
            assignments = dao.getAllAssignments();            
            request.setAttribute("assignments", assignments);                         
            destPage = "view/assignments.jsp";   
            dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response); 
            break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
