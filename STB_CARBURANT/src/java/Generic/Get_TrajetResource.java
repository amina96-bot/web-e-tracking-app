/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

import Connexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author akhaldi
 */
@Path("Get_Trajet")
public class Get_TrajetResource {

   @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_TrajetResource
     */
    public Get_TrajetResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_TrajetResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/query")
    @Consumes("application/json")
    public String getJson(@QueryParam("usr_id") int usr_id) throws Exception{
       //TODO return proper representation object
         Connection connection; Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                ResultSet rs=st.executeQuery("select [tr_id]\n" +
"      ,[tr_usr_id]\n" +
"      ,[tr_motif]\n" +
"      ,[tr_date_depart]\n" +
"      ,[tr_kilometrage_depart]\n"+
"      ,[tr_lieu_depart]\n"+
"      ,[tr_date_arrivee]\n"+
"      ,[tr_kilometrage_arrivee]\n"+
"      ,[tr_lieu_arrivee]\n"+
"      ,[tr_note]\n"   + 
"      ,[tr_sync_id]\n" +
"      ,[tr_sync_date]\n" +
"      ,[tr_type]\n" +
"  from USERS \n" +
"inner join AFFECTATION on usr_id=af_usr_id \n" +
"inner join TRAJET on tr_usr_id=usr_id \n" +
"where af_active=1 and  tr_date_depart >=af_date   \n"
                        + " and usr_id="+usr_id);
                JSONArray array= new JSONArray();
                
                while (rs.next()) {            
            JSONObject object = new JSONObject();
object.put("tr_id", rs.getInt(1));
object.put("tr_usr_id", rs.getInt(2));
object.put("tr_motif", rs.getString(3));
object.put("tr_date_depart", rs.getString(4));
object.put("tr_kilometrage_depart", rs.getDouble(5));
object.put("tr_lieu_depart", rs.getString(6));  
object.put("tr_date_arrivee", rs.getString(7)); 
object.put("tr_kilometrage_arrivee", rs.getDouble(8)); 
object.put("tr_lieu_arrivee", rs.getString(9)); 
object.put("tr_note", rs.getString(10)); 
object.put("tr_sync_id", rs.getInt(11)); 
object.put("tr_sync_date", rs.getString(12));
object.put("tr_type", rs.getInt(13)); 
array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_TrajetResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
