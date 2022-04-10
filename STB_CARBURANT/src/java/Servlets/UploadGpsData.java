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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



@WebServlet(name = "UploadGpsData", urlPatterns = {"/UploadGpsData"})
@MultipartConfig
public class UploadGpsData extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                String jsonArray=request.getParameter("gps");
                String userId=request.getParameter("userId");
                JSONArray jsonArr=new JSONArray(jsonArray);                
                JSONArray array = new JSONArray();
              try {            
                Connection connection; 
                Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < jsonArr.length(); i++){
                    JSONObject jsonObj = jsonArr.getJSONObject(i);            
                    final String gps_id = jsonObj.getString("gps_id");            
                    final String gps_usr_id = jsonObj.getString("gps_usr_id");
                    final String gps_latitude = jsonObj.getString("gps_latitude");
                    final String gps_longitude = jsonObj.getString("gps_longitude");  
                    final String gps_date = jsonObj.getString("gps_date"); 
                    String value="("+gps_usr_id+","+gps_latitude+","+gps_longitude+",convert(datetime,'"+gps_date+"',121),"+gps_id+",convert(nvarchar,GETDATE(),20),"+userId+")";                           
                    
                    if(i==0)
                    sb.append(value);
                    else 
                     sb.append(","+value);         
                 
                }
                st.executeUpdate("INSERT INTO GPS \n" +
                    "           ([gps_usr_id] \n" +
                    "           ,[gps_latitude] \n" +
                    "           ,[gps_longitude] \n" +
                    "           ,[gps_date] \n" +
                    "           ,[gps_sync_id] \n" +
                    "           ,[gps_sync_date]"+
                    "           ,[gps_hash]) \n" +
                    "           VALUES \n" + sb.toString()   );

                    ResultSet rs=st.executeQuery("SELECT  [gps_id]      \n" +
                    "      ,[gps_sync_id] \n" +
                    "      ,[gps_sync_date] \n" +                  
                    "FROM GPS \n" +
                    "where [gps_hash] ='"+userId+"'");

                    
                    while(rs.next()){
                        JSONObject object= new JSONObject();
                        object.put("gps_id", rs.getInt("gps_id"));
                        object.put("gps_sync_id", rs.getInt("gps_sync_id"));
                        object.put("gps_sync_date", rs.getString("gps_sync_date"));
                     
                        array.put(object);
                    }
                    
                    out.println(array.toString());
                
             } catch (Exception e) {
                FileWriter fw = new FileWriter(new File("D:\\STB_CARBURANT\\log.txt"),true);
                fw.write(e.getMessage());
                fw.close();
            }
                    
            }catch (JSONException ex) {
                Logger.getLogger(UploadGpsData.class.getName()).log(Level.SEVERE, null, ex);
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
