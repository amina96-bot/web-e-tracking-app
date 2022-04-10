<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="model.Vehicle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Liste des véhicules</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />
    
     <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
     
     
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <link rel="stylesheet" href="/STB_CARBURANT/sidebar-02/css/style.css">
</head>
<body>
    <header>
        
    </header>
        
        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary"><i class="fa fa-bars"></i><span class="sr-only">Toggle Menu</span></button>
                </div>
		<div class="p-4 pt-5">
                    <h6 style="color: #D0D0D0"><i style="color: #D0D0D0" class="fa fa-user-circle-o"></i> ${user.lastName} ${user.firstName} </h6>
                    <h7 style="color: #D0D0D0"><i style="color: #D0D0D0" class="fa fa-map-marker"></i> ${user.employee.region}</h7>
                    <br><br>
                    <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="dashboard"><i class="fa fa-tachometer "></i>  Tableau de bord</a>
                        </li>
                        
                        <li class="active">
                            <a href="vehicles?action=list"><i class="fa fa-car fa-lg"></i> Véhicules</a>
                        </li>
                        
                        <li>
                            <a href="drivers?action=list"><i class="fa fa-male"></i> Conducteurs</a>
                        </li>
                        
                        <li>
                            <a href="assignments?action=list"><i class="fa fa-id-card"></i> Affectations</a>
                        </li> 
                        <br>
                        <li>
                          <a href="logout"><i class="fa fa-sign-out"></i> Déconnexion</a>
                        </li>
                        
                    </ul>

	      </div>
    	</nav>

                     <% User user=(User) request.getSession().getAttribute("user"); %>
        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
            <div class="row">
                <div class="col-sm-6">
                    <h1 class="text-logo"> <i class="fa fa-list fa-lg"></i> Liste des véhicules</h1>
                </div>
                <div class="col-sm-6" >
                <%  if(user.getRoles().contains("administrateur")) { %>
                        <a  style="float:right" href="vehicles?action=add" class="add-button"  title="Ajouter un véhicule"><i  class="fa fa-plus"></i></a>
                <% }  %>
                        <a  style="float:right;background-color: #09ad32;color:white" onclick="ExportToExcel('xlsx')" class="add-button"  title="Exporter en excel"><i class="fa fa-file"></i></a>
                </div>
            </div>
           
                
            <input type="text" id="searchInput" onkeyup="myFunction()" placeholder="Tapez pour chercher/filtrer ...">            
            <table class="table" id="table">
                <thead style="text-align: center">
                    <tr style="cursor: pointer">
                    <th scope="col" onclick="sortTable(0)">Code  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(1)">N° immatriculation  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(2)">Région <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(3)">Modèle <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(4)">Marque <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(5)">Carburant <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(6)">Conso <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(7)">Date <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(8)">Etat <i class="fa fa-sort"></i></th>
                    
                     <%  if(user.getRoles().contains("administrateur")) { %>
                    <th scope="col" >Actions</th>
                    <% }  %>
                  </tr>
                </thead>
                <tbody>
                    <% 
                        List<Vehicle> vehicles=(List<Vehicle>)request.getAttribute("vehicles");
                        for(int i=0;i<vehicles.size();i++){
                        %>
                        <tr style="text-align: center" onclick="document.location='vehicles?action=vehicle_assignments&id=<%= vehicles.get(i).getId() %>';">
                            <td><%= vehicles.get(i).getCode()  %></td>
                            <td><%= vehicles.get(i).getImmatriculation() %></td>
                            <td><%= vehicles.get(i).getRegion() %></td>
                            <td><%= vehicles.get(i).getModel().getDescription() %></td>
                            <td><%= vehicles.get(i).getModel().getBrand().getDescription() %></td>
                            <td style="text-align: center"><%= vehicles.get(i).getFuel().getDescription() %></td>
                               <td><%= vehicles.get(i).getAverageConsumption() %></td>
                            <td><%= vehicles.get(i).getCreationDate().substring(0,16) %></td>
                            <% if(vehicles.get(i).isActive()){ %>                          
                            <td style="text-align: center"> <i class="fa fa-check fa-lg" style="color: green"></i></td>                          
                            <% }else{ %>                                      
                            <td style="text-align: center"><i class="fa fa-times fa-lg" style="color: red"></i> </td>
                            <% } %>
                            
                              
                              
                              <%  if(user.getRoles().contains("administrateur")) { %>
                            <td style="text-align: center"> 
                                <a href="vehicles?action=update&id=<%= vehicles.get(i).getId() %>"><i style="color: blue" class="fa fa-edit fa-lg"></i></a> 
                               &nbsp;&nbsp;&nbsp;
                               <a href="vehicles?action=delete&id=<%= vehicles.get(i).getId() %>" onclick="return confirm('Êtes-vous sûr de vouloir supprimer?')"><i style="color: red" class="fa fa-trash fa-lg"></i></a> 
                            </td>
                             <% }  %>
                        </tr>
                   <% }  %>
                </tbody>
            </table>
                   <%-- search&filter script--%>
            <script>
                function myFunction() {
                  // Declare variables
                  var input, filter, table, tr, td, i, txtValue;
                  input = document.getElementById("searchInput");
                  filter = input.value.toUpperCase();
                  table = document.getElementById("table");
                  tr = table.getElementsByTagName("tr");

                  // Loop through all table rows, and hide those who don't match the search query
                  for (i = 1; i < tr.length; i++) {                      
                    td=tr[i].getElementsByTagName("td");
                      // hide the row
                    tr[i].style.display = "none";
                    // loop through row cells
                    for (var j = 0; j < td.length; j++) {
                      // if there's a match
                      if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {

                        // show the row
                        tr[i].style.display = "";

                        // skip to the next row
                        continue;

                      }
                    }
                  }
                }
            </script>
              <%-- sort on header click script--%>
              
              <script>
                function sortTable(n) {
                  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                  table = document.getElementById("table");
                  switching = true;
                  // Set the sorting direction to ascending:
                  dir = "asc";
                  /* Make a loop that will continue until
                  no switching has been done: */
                  while (switching) {
                    // Start by saying: no switching is done:
                    switching = false;
                    rows = table.rows;
                    /* Loop through all table rows (except the
                    first, which contains table headers): */
                    for (i = 1; i < (rows.length - 1); i++) {
                      // Start by saying there should be no switching:
                      shouldSwitch = false;
                      /* Get the two elements you want to compare,
                      one from current row and one from the next: */
                      x = rows[i].getElementsByTagName("td")[n];
                      y = rows[i + 1].getElementsByTagName("td")[n];
                      /* Check if the two rows should switch place,
                      based on the direction, asc or desc: */
                      if (dir == "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                          // If so, mark as a switch and break the loop:
                          shouldSwitch = true;
                          break;
                        }
                      } else if (dir == "desc") {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                          // If so, mark as a switch and break the loop:
                          shouldSwitch = true;
                          break;
                        }
                      }
                    }
                    if (shouldSwitch) {
                      /* If a switch has been marked, make the switch
                      and mark that a switch has been done: */
                      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                      switching = true;
                      // Each time a switch is done, increase this count by 1:
                      switchcount ++;
                    } else {
                      /* If no switching has been done AND the direction is "asc",
                      set the direction to "desc" and run the while loop again. */
                      if (switchcount == 0 && dir == "asc") {
                        dir = "desc";
                        switching = true;
                      }
                    }
                  }
                }
            </script>
            
            
            <script type="text/javascript">

                function openPage(pageURL) {
                    window.location = pageURL;
                }

            </script>
            
            <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>            
            <script type="text/javascript">                
                function ExportToExcel(type, fn, dl) {
                    var elt = document.getElementById('table');
                    var wb = XLSX.utils.table_to_book(elt, { sheet: "sheet1" });
                    return dl ?
                      XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }):
                      XLSX.writeFile(wb, fn || ('vehicules_list.' + (type || 'xlsx')));
                 }
            </script>
            
        </div>

    
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>
      
        
    <footer>

    </footer>
</body>
</html>