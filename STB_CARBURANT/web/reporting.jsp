<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>



<%   
//String id = request.getParameter("userId");

String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
String connectionUrl = "jdbc:sqlserver://192.168.0.106;database=NAFTAL;user=sa;password=_Admin@SBG$";
String dbName = "jsptutorials";
String userId = "root";
String password = "root";

try {
Class.forName(driverName);
}catch(ClassNotFoundException e){
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>


<h2 align="center"><font><strong>Tableau de bord 'Gestion du parc automobile'</strong></font></h2>

<h3>Marques & modèles des véhicules:</h3>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>marque</b></td>
<td><b>modèle</b></td>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl);
statement=connection.createStatement();
String sql ="SELECT * FROM BRAND INNER JOIN MODEL on br_id=md_br_id";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">
<td><%=resultSet.getString("br_description") %></td>
<td><%=resultSet.getString("md_description") %></td>
</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>

<h3>Prix actuel du carburant:</h3>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#249e65">
<td><b>carburant</b></td>
<td><b>prix actuel</b></td>
<td><b>date d'actualisation</b></td>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl);
statement=connection.createStatement();
String sql ="SELECT * FROM FUEL INNER JOIN COST on fl_id=ct_fl_id WHERE ct_active=1";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#94ffcd">
<td><%=resultSet.getString("fl_description") %></td>
<td><%=resultSet.getString("ct_price") %></td>
<td><%=resultSet.getString("ct_date") %></td>
</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>

<h3>Utilisateur:</h3>

<form>
  <label for="fname">Prénom:</label>
  <input type="text" id="fname" name="fname"><br><br>
  <label for="lname">Nom:</label>
  <input type="text" id="lname" name="lname"><br><br>
  <input type="submit" value="Submit">
</form>
