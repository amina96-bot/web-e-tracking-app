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
@Path("Get_Kilometrage")
public class Get_KilometrageResource{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_KilometrageResource
     */
    public Get_KilometrageResource() {
    }

    /**
     * Retrieves representation of an instance of Generic.Get_KilometrageResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/query")
    @Produces("application/json")
     public String getJson(@QueryParam("usr_id") int usr_id) throws Exception{
        //TODO return proper representation object
         Connection connection; Statement st = null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:sqlserver://"+new DatabaseConnection().getServer()+";database="+new DatabaseConnection().getDatabase()+";user="+new DatabaseConnection().getUser()+";password="+new DatabaseConnection().getPassword());
                st = connection.createStatement();
                ResultSet rs=st.executeQuery("select [km_id]\n" +
"      ,[km_usr_id]\n" +
"      ,[km_date]\n" +
"      ,[km_kilometrage]\n" +
"      ,[km_latitude]\n"+
"      ,[km_longitude]"+
"      ,[km_sync_id]\n" +
"      ,[km_sync_date]\n" +                   
"      ,[km_file_path]\n" +
"      ,[km_file_name]\n" +
"      ,[accepted]\n" +
"      ,[montant_recharge]\n" +
"      ,[motif_refus]\n" +
                         " from USERS \n" +
"inner join AFFECTATION on af_usr_id=usr_id \n" +
"inner join KILOMETRAGE on km_usr_id=usr_id \n" +
"where af_active=1 and  km_date >=af_date   \n"
                        + " and usr_id="+usr_id);
                JSONArray array= new JSONArray();
                while (rs.next()) {            
            JSONObject object = new JSONObject();
object.put("km_id", rs.getInt(1));
object.put("km_usr_id", rs.getInt(2));
object.put("km_date", rs.getString(3));
object.put("km_kilometrage", rs.getDouble(4));
object.put("km_latitude", rs.getDouble(5));
object.put("km_longitude", rs.getDouble(6));   
object.put("km_sync_id", rs.getInt(7)); 
object.put("km_sync_date", rs.getString(8)); 
object.put("km_file_path", rs.getString(9));
object.put("km_file_name", rs.getString(10));


object.put("accepted", "-1");
object.put("montant_recharge", "-1");
object.put("motif_refus", "-1");
    
if(rs.getString(11)!=null){ 
    object.put("accepted", rs.getString(11));
    if(rs.getString(11).equals("0")){ // demande refusée 
    object.put("motif_refus", rs.getString(13));
    }else{ // demande acceptée 
        if(rs.getString(12)!=null){ 
             object.put("montant_recharge", rs.getString(12));
        }
    }    
}

array.put(object);
}
return array.toString();
}

    /**
     * PUT method for updating or creating an instance of Get_KilometrageResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content){
    }
}
