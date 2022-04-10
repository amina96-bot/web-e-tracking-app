package Servlets;

import Connexion.DatabaseConnection;
import dao.Dao;
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
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.EmailUtility;
import model.Employee;
import model.User;
import org.json.JSONObject;

/**
 *
 * @author akhaldi
 */
@WebServlet(name = "UploadKilometrageData", urlPatterns = {"/UploadKilometrageData"})
@MultipartConfig
public class UploadKilometrageData extends HttpServlet {
    
     Dao dao = new Dao(DatabaseConnection.getConnection());
    Dao dao2 = new Dao(DatabaseConnection.getConnection2()); 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
             try {
               
final String km_id = request.getParameter("km_id");
final String km_usr_id = request.getParameter("km_usr_id");
final String km_date = request.getParameter("km_date");
final String km_kilometrage = request.getParameter("km_kilometrage");
final String km_latitude = request.getParameter("km_latitude");
final String km_longitude = request.getParameter("km_longitude"); 
final String km_sync_id = request.getParameter("km_sync_id");
final String km_sync_date = request.getParameter("km_sync_date");
final String km_file_path = request.getParameter("km_file_path");
final String km_file_name = request.getParameter("km_file_name");  

final Part filePart = request.getPart("file");
OutputStream output = null;
InputStream filecontent = null;
try {
        output = new FileOutputStream(new File("D:\\STB_CARBURANT\\image\\"+km_file_name));
        filecontent = filePart.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) {
            output.write(bytes, 0, read);
        }                                             
        output.close();
        filecontent.close();
    } catch (Exception fne) {
        FileWriter fw = new FileWriter(new File("D:\\STB_CARBURANT\\log.txt"),true);
        fw.write(fne.getMessage());
        fw.close();
    } 

Connection connection; 
Statement st = null;
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
st = connection.createStatement();

if(km_sync_id.equals("0")){ // on insère car première synchronsation
    st.executeUpdate("INSERT INTO KILOMETRAGE \n" +
"           ([km_usr_id] \n" +
"           ,[km_date] \n" +
"           ,[km_kilometrage] \n" +
"           ,[km_latitude] \n" +
"           ,[km_longitude] \n" +
"           ,[km_sync_id] \n" +
"           ,[km_sync_date]" +
"          ,[km_file_path] \n" +
"           ,[km_file_name]) \n" +
        
"     VALUES \n" +
"           ("+km_usr_id+" \n" +
"           ,convert(datetime,'"+km_date+"',121) \n" +
"           ,"+km_kilometrage+" \n" +
"           ,"+km_latitude+" \n" +
"           ,"+km_longitude+" \n" +
"           ,"+km_id+" \n" +
"           , convert(nvarchar,GETDATE(),20) "+
"           ,'"+km_file_path+"' \n" +
"           ,'"+km_file_name+"' )")       
        ;
  
    User user=dao.getUserById(Integer.valueOf(km_usr_id));

    
    String to=null;
    switch (user.getEmployee().getRegion()) {
        case "Centre":
            to = "akhaldi@starbrandsspa.com";
        break; 
         case "Est":
             to = "akhaldi@starbrandsspa.com";
        break; 
         case "Ouest":
             to = "akhaldi@starbrandsspa.com";
        break;
    }
      String from = "notification@starbrandsspa.com";
      String host = "mail.starbrandsspa.com";
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
     message.setSubject("Nouvelle demande en attente d'approbation");
         message.setText("Bonjour, Vous avez une nouvelle demande de chargement en attente de validation lancée par l'utilisateur "+user.getEmployee().getRaisonSociale());
         message.setSentDate(new Date());
         Transport.send(message);
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }       
}else{
    
     st.executeUpdate("UPDATE [dbo].[KILOMETRAGE] SET \n" +
        "           [km_usr_id] ="+ km_usr_id+" \n" +
        "           ,[km_date]= convert(datetime,'"+km_date+"',121) \n"+
        "           ,[km_kilometrage]=" + km_kilometrage+" \n" +
        "           ,[km_latitude]= " + km_latitude+" \n" +
        "           ,[km_longitude]= " + km_longitude+" \n" +
        "           ,[km_sync_id]= " + km_id+" \n" +
        "           ,[km_sync_date]= convert(nvarchar,GETDATE(),20) \n"+
        "           ,[km_file_path]= '" + km_file_path+"' \n" +
        "           ,[km_file_name]= '" + km_file_name+"' \n" +
        "           WHERE [km_id]=  " +km_sync_id)
                     ;
}


 ResultSet rs=st.executeQuery("SELECT  [km_id]      \n" +
        "      ,[km_sync_id] \n" +
        "      ,[km_sync_date] \n" +                  
        "FROM KILOMETRAGE \n" +
        "where [km_sync_id] ='"+km_id+"'");
                
                JSONObject object= new JSONObject();
                while(rs.next()){            
                    object.put("km_id", rs.getInt("km_id"));
                    object.put("km_sync_id", rs.getInt("km_sync_id"));
                    object.put("km_sync_date", rs.getString("km_sync_date"));
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
