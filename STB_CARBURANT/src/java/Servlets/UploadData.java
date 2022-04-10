/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Connexion.DatabaseConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.json.JSONObject;

/**
 *
 * @author nbenadjimi
 */

@MultipartConfig
public class UploadData extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
               
            // Create path components to save the file
         final String pa_id = request.getParameter("pa_id");
         final String pa_usr_id = request.getParameter("pa_usr_id");
         final String pa_date = request.getParameter("pa_date");
         final String pa_distance = request.getParameter("pa_distance");
         final String pa_unit_price = request.getParameter("pa_unit_price");
         final String pa_cost = request.getParameter("pa_cost");
         final String pa_latitude = request.getParameter("pa_latitude");
         final String pa_longitude = request.getParameter("pa_longitude");
         final String pa_pt_id = request.getParameter("pa_pt_id");         
         final String pa_sync_id = request.getParameter("pa_sync_id");
         final String pa_sync_date = request.getParameter("pa_sync_date");         
         final String pa_comment = request.getParameter("pa_comment");
         final String pa_file_path = request.getParameter("pa_file_path");
         final String pa_file_name = request.getParameter("pa_file_name");
         final String pa_km_counter_file_path = request.getParameter("pa_km_counter_file_path");
         final String pa_km_counter_file_name = request.getParameter("pa_km_counter_file_name");
     

    final Part filePart = request.getPart("file");
    final Part km_counter_filePart = request.getPart("km_counter_file");
    OutputStream output = null;
    InputStream filecontent = null;
    try {
            output = new FileOutputStream(new File("D:\\STB_CARBURANT\\image\\"+pa_file_name));
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }                
            output = new FileOutputStream(new File("D:\\STB_CARBURANT\\image\\"+pa_km_counter_file_name));
            filecontent = km_counter_filePart.getInputStream();
            read = 0;        
            while ((read = filecontent.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }                              
            output.close();
            filecontent.close();
        } catch (Exception fne) {
            FileWriter fw = new FileWriter(new File("D:\\STB_CARBURANT\\image\\log.txt"),true);
            fw.write(fne.getMessage());
            fw.close();
        } 
    
        Connection connection; 
        Statement st = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
        st = connection.createStatement();
        
        
       if(pa_sync_id.equals("0")){ // on insère car première synchronsation
            st.executeUpdate("INSERT INTO PAYMENT \n" +
        "           ([pa_usr_id] \n" +
        "           ,[pa_date] \n" +
        "           ,[pa_distance] \n" +
        "           ,[pa_unit_price] \n" +
        "           ,[pa_cost] \n" +
        "           ,[pa_latitude] \n" +
        "           ,[pa_longitude] \n" +
        "           ,[pa_pt_id] \n" +
        "           ,[pa_sync_id] \n" +
        "           ,[pa_sync_date] \n" +
        "           ,[pa_comment] \n" +
        "           ,[pa_file_path] \n" +
        "           ,[pa_file_name] \n" +
        "           ,[pa_km_counter_file_path] \n" +
        "           ,[pa_km_counter_file_name]) \n" +
        "     VALUES \n" +
        "           ("+pa_usr_id+" \n" +
        "           ,convert(datetime,'"+pa_date+"',121) \n" +
        "           ,"+pa_distance+" \n" +
        "           ,"+pa_unit_price+" \n" +
        "           ,"+pa_cost+" \n" +
        "           ,"+pa_latitude+" \n" +
        "           ,"+pa_longitude+" \n" +
        "           ,"+pa_pt_id+" \n" +
        "           ,"+pa_id+" \n" +
        "           , convert(nvarchar,GETDATE(),20) \n" +
        "           ,'"+pa_comment+"' \n" +
        "           ,'"+pa_file_path+"' \n" +
        "           ,'"+pa_file_name+"' \n" +
        "           ,'"+pa_km_counter_file_path+"' \n" +
        "           ,'"+pa_km_counter_file_name+"' )");
       }else{ // on update 
             st.executeUpdate("UPDATE [dbo].[PAYMENT] SET \n" +
        "           [pa_usr_id] =" + pa_usr_id+" \n" +
        "           ,[pa_date]= convert(datetime,'"+pa_date+"',121) \n"+
        "           ,[pa_distance]=" + pa_distance+" \n" +
        "           ,[pa_unit_price]=" + pa_unit_price+" \n" +
        "           ,[pa_cost]=" + pa_cost+" \n" +
        "           ,[pa_latitude]=" + pa_latitude+" \n" +
        "           ,[pa_longitude]=" + pa_longitude+" \n" +
        "           ,[pa_pt_id]=" + pa_pt_id+" \n" +
        "           ,[pa_sync_id]=" + pa_id+" \n" +
        "           ,[pa_sync_date]= convert(nvarchar,GETDATE(),20) \n"+
        "           ,[pa_comment]= '" + pa_comment+"' \n" +
        "           ,[pa_file_path]= '" + pa_file_path+"' \n" +
        "           ,[pa_file_name]= '" + pa_file_name+"' \n" +
        "           ,[pa_km_counter_file_path]= '" + pa_km_counter_file_path+"' \n" +
        "           ,[pa_km_counter_file_name]= '" + pa_km_counter_file_name+"' \n" +
                
        "           WHERE [pa_id]=  " +pa_sync_id)
                     ;
       }
        
        ResultSet rs=st.executeQuery("SELECT  [pa_id]      \n" +
        "      ,[pa_sync_id] \n" +
        "      ,[pa_sync_date] \n" +
        "      ,[pa_file_name] \n" +                    
        "FROM PAYMENT \n" +
        "where [pa_file_name] ='"+pa_file_name+"'");
                    JSONObject object= new JSONObject();
                    while(rs.next())
                    {            
                    object.put("pa_id", rs.getInt("pa_id"));
                    object.put("pa_sync_id", rs.getInt("pa_sync_id"));
                    object.put("pa_sync_date", rs.getString("pa_sync_date"));
                    object.put("pa_file_name", rs.getString("pa_file_name"));
                    }
                    out.println(object.toString());
            
            
            } catch (Exception e) {
                FileWriter fw = new FileWriter(new File("D:\\STB_CARBURANT\\log.txt"),true);
                fw.write(e.getMessage());
                fw.close();
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
