package Servlets;

import Connexion.DatabaseConnection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author akhaldi
 */
@WebServlet(name = "UploadTrajetData", urlPatterns = {"/UploadTrajetData"})
@MultipartConfig
public class UploadTrajetData extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
             try {
               
                final String tr_id = request.getParameter("tr_id");
                final String tr_usr_id = request.getParameter("tr_usr_id");
                final String tr_motif = request.getParameter("tr_motif");
                final String tr_date_depart = request.getParameter("tr_date_depart");
                final String tr_kilometrage_depart = request.getParameter("tr_kilometrage_depart");
                final String tr_lieu_depart = request.getParameter("tr_lieu_depart");
                final String tr_date_arrivee = request.getParameter("tr_date_arrivee");
                final String tr_kilometrage_arrivee = request.getParameter("tr_kilometrage_arrivee");
                final String tr_lieu_arrivee = request.getParameter("tr_lieu_arrivee");
                final String tr_note = request.getParameter("tr_note");  
                final String tr_sync_id = request.getParameter("tr_sync_id");
                final String tr_sync_date = request.getParameter("tr_sync_date");
                final String tr_type = request.getParameter("tr_type");
    
                Connection connection; 
                Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
               
                if(tr_sync_id.equals("0")){ // on insère car première synchronsation
                    
                     if(!tr_kilometrage_arrivee.equals("0.0")){
                   
                    st.executeUpdate("INSERT INTO TRAJET \n" +
                "           ([tr_usr_id] \n" +
                "           ,[tr_motif] \n" +
                "           ,[tr_date_depart] \n" +
                "           ,[tr_kilometrage_depart] \n" +
                "           ,[tr_lieu_depart] \n" +
                "           ,[tr_date_arrivee] \n" +
                "           ,[tr_kilometrage_arrivee] \n" +
                "           ,[tr_lieu_arrivee] \n" +
                "           ,[tr_note] \n" +
                "           ,[tr_sync_id] \n" +
                "           ,[tr_sync_date] \n" +
                "           ,[tr_type]) \n" +
                        
                "     VALUES \n" +
                "           ("+tr_usr_id+" \n" +
                "           ,'"+tr_motif+"' \n" +
                             "           ,convert(datetime,'"+tr_date_depart+"',121) \n" +
                "           ,"+tr_kilometrage_depart+" \n" +
                "           ,'"+tr_lieu_depart+"' \n" +
                             "           ,convert(datetime,'"+tr_date_arrivee+"',121) \n" +
                "           ,"+tr_kilometrage_arrivee+" \n" +
                "           ,'"+tr_lieu_arrivee+"' \n" +
                "           ,'"+tr_note+"' \n" +
                "           ,"+tr_id+" \n" +
        "           , convert(nvarchar,GETDATE(),20) \n"+
                     "           ,"+tr_type+" ) " 
                    
                    ) ;
                    }else{              
                          System.out.println("b");             
                         st.executeUpdate("INSERT INTO TRAJET \n" +
                "           ([tr_usr_id] \n" +
                "           ,[tr_motif] \n" +
                "           ,[tr_date_depart] \n" +
                "           ,[tr_kilometrage_depart] \n" +
                "           ,[tr_lieu_depart] \n" +
                "           ,[tr_date_arrivee] \n" +
                "           ,[tr_kilometrage_arrivee] \n" +
                "           ,[tr_lieu_arrivee] \n" +
                "           ,[tr_note] \n" +
                "           ,[tr_sync_id] \n" +
                "           ,[tr_sync_date] \n" +
                                          "           ,[tr_type]) \n" +
                        
                "     VALUES \n" +
                "           ("+tr_usr_id+" \n" +
                "           ,'"+tr_motif+"' \n" +
                             "           ,convert(datetime,'"+tr_date_depart+"',121) \n" +
                "           ,"+tr_kilometrage_depart+" \n" +
                "           ,'"+tr_lieu_depart+"' \n" +
                          "           , ' ' \n" +
                "           ,"+tr_kilometrage_arrivee+" \n" +
                "           ,'"+tr_lieu_arrivee+"' \n" +
                "           ,'"+tr_note+"' \n" +
                "           ,"+tr_id+" \n" +
        "           , convert(nvarchar,GETDATE(),20) \n"+
                                              "           ,"+tr_type+" ) " 
                         
                         );
                    }
                }else{
                     System.out.println("c");
                     st.executeUpdate("UPDATE TRAJET SET \n" +
        "           [tr_usr_id] = " + tr_usr_id+" \n" +
        "           ,[tr_motif] = '" + tr_motif+"' \n" +
        "           ,[tr_date_depart]= convert(datetime,'"+tr_date_depart+"',121) \n"+
        "           ,[tr_kilometrage_depart]= " + tr_kilometrage_depart+" \n" +
        "           ,[tr_lieu_depart]= '" + tr_lieu_depart+"' \n" +
        "           ,[tr_date_arrivee]= convert(datetime,'"+tr_date_arrivee+"',121) \n"+
        "           ,[tr_kilometrage_arrivee]= " + tr_kilometrage_arrivee+" \n" +
        "           ,[tr_lieu_arrivee]= '" + tr_lieu_arrivee+"' \n" +
        "           ,[tr_note]= '" + tr_note+"' \n" +
        "           ,[tr_sync_id]= " + tr_id+" \n" +
        "           ,[tr_sync_date]= convert(nvarchar,GETDATE(),20) \n"+
                
                 "           ,[tr_type]= " + tr_type+" \n" +
                
        "           WHERE tr_id=  " +tr_sync_id)
                     ;
                }
                
               
                
                
                 ResultSet rs=st.executeQuery("SELECT  [tr_id]      \n" +
        "      ,[tr_sync_id] \n" +
        "      ,[tr_sync_date] \n" +                  
        "FROM TRAJET \n" +
        "where [tr_sync_id] ='"+tr_id+"'");
                
                JSONObject object= new JSONObject();
                while(rs.next()){            
                    object.put("tr_id", rs.getInt("tr_id"));
                    object.put("tr_sync_id", rs.getInt("tr_sync_id"));
                    object.put("tr_sync_date", rs.getString("tr_sync_date"));
                }
                    out.println(object.toString());
                
          
            } catch (Exception e) {
               Logger.getLogger(UploadGpsData.class.getName()).log(Level.SEVERE, null, e);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
