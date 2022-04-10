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
@Path("Get_Gps")
public class Get_GpsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_GpsResource
     */
    public Get_GpsResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_GpsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/query")
    @Produces("application/json")
    public String getJson(@QueryParam("usr_id") int usr_id) throws Exception{
         Connection connection; Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                ResultSet rs=st.executeQuery("select [gps_id]\n" +
"      ,[gps_usr_id]\n" +
"      ,[gps_latitude]\n" +
"      ,[gps_longitude]\n" +
"      ,[gps_date]"+
                        "      ,[gps_sync_id]\n" +
"      ,[gps_sync_date]\n" 
                        + " from USERS \n" +
"inner join AFFECTATION on usr_id=af_usr_id \n" +
"inner join GPS on gps_usr_id=usr_id \n" +
"where af_active=1 and  gps_date >=af_date   \n"
                        + " and usr_id="+usr_id);
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
object.put("gps_id", rs.getInt(1));
object.put("gps_usr_id", rs.getInt(2));
object.put("gps_latitude", rs.getDouble(3));
object.put("gps_longitude", rs.getDouble(4));
object.put("gps_date", rs.getString(5));    
object.put("gps_sync_id", rs.getInt(6)); 
object.put("gps_sync_date", rs.getString(7)); 
array.put(object);
        }
        return array.toString();
    }

    /**
     * PUT method for updating or creating an instance of Get_GpsResource
     * @param content representation for the resource
     */
    @PUT
    @Produces("application/json")
    public void putJson(String content) {
    }
}
