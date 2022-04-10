<%@page import="model.User"%>
<%@page import="model.Assignment"%>
<%@page import="java.util.List"%>
<%@page import="model.Vehicle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> Demandes de chargement en attente de confirmation </title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>   
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />   
     <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
     <link rel="stylesheet" href="/STB_CARBURANT/sidebar-02/css/style.css">
</head>
<body>        
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
                    <li class="active">
                        <a href="dashboard"><i class="fa fa-tachometer "></i>  Tableau de bord</a>
                    </li>

                    <li >
                        <a href="vehicles?action=list"><i class="fa fa-car"></i> Véhicules</a>
                    </li>

                    <li>
                        <a href="drivers?action=list"><i class="fa fa-male"></i> Conducteurs</a>
                    </li>

                    <li >
                        <a href="assignments?action=list"><i class="fa fa-id-card"></i> Affectations</a>
                    </li> 
                    <br>
                    <li>
                      <a href="logout"><i class="fa fa-sign-out"></i> Déconnexion</a>
                    </li>

                </ul>

          </div>
    </nav>

    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5 pt-5">
        <div class="row">
            <div class="col-sm-8">
                <h1 class="text-logo"> <i class="fa fa-list"></i> Demandes de chargement en attente de confirmation</h1>
            </div>
            <div class="col-sm-4" >
                    <a  style="float:right;background-color: #09ad32;color:white" onclick="ExportToExcel('xlsx')" class="add-button"  title="Exporter en excel"><i class="fa fa-file"></i></a>
            </div>
        </div>
        <%  
                        User user=(User) request.getSession().getAttribute("user");
                        List<String> roles=user.getRoles();
                        %>

        <input type="text" id="searchInput" onkeyup="myFunction()" placeholder="Tapez pour chercher/filtrer ...">            
        <table class="table" id="table">
            <thead style="text-align: center">
                <tr style="cursor: pointer">
                <th scope="col" onclick="sortTable(0)">Conducteur  <i class="fa fa-sort"></i></th>
                <th scope="col" onclick="sortTable(1)">Code véhicule  <i class="fa fa-sort"></i></th>
                <th scope="col" onclick="sortTable(2)">Km initial  <i class="fa fa-sort"></i></th>
                  <th scope="col" onclick="sortTable(3)">Dernier Km<i class="fa fa-sort"></i></th>
                  <th scope="col" onclick="sortTable(3)">Conso moy<i class="fa fa-sort"></i></th>
                <th scope="col" onclick="sortTable(4)">État  <i class="fa fa-sort"></i></th>
                <th scope="col" >Actions</th>
              </tr>
            </thead>
            <tbody>
                <% 
                    List<Assignment> assignments=(List<Assignment>)request.getAttribute("assignments");
                    for(int i=0;i<assignments.size();i++){
                    %>
                    <tr style="text-align: center" title="cliquez pour voir détail" onclick="document.location='assignments?action=detail&id=<%= assignments.get(i).getId() %>';">
                        <td><%= assignments.get(i).getUser().getEmployee().getRaisonSociale() %></td>
                        <td><%= assignments.get(i).getVehicle().getCode() %></td>
                        <td><%= assignments.get(i).getFirstKm() %></td>
                        <td><%= assignments.get(i).getLastKm() %></td>
                        <td><%= assignments.get(i).getAverageConsumption() %></td>
                        <% if(assignments.get(i).isActive()){ %>

                        <td style="text-align: center"> <i class="fa fa-check fa-lg" style="color: green"></i></td>

                        <% }else{ %>

                        <td style="text-align: center"><i class="fa fa-times fa-lg" style="color: red"></i> </td>
                        <% } %>

                       <td style="text-align: center"> 
<!--                            <a href="assignments?action=detail&id=<%= assignments.get(i).getId() %>" title="Consulter détail"  ><i style="color: green" class="fa fa-eye fa-lg"></i></a> 
                            &nbsp;&nbsp;&nbsp;-->
                                   <a  href="assignments?action=print&id=<%= assignments.get(i).getId() %>" title="Imprimer la fiche d'affectation & l'engagement"><i style="color: #fc6d00" class="fa fa-print fa-lg"></i></a>                             
                            &nbsp;&nbsp;&nbsp; 
                                <%  if(roles.contains("administrateur")) { %>
                            <a href="assignments?action=update&id=<%= assignments.get(i).getId() %>" title="Modifier"  ><i style="color: blue" class="fa fa-edit fa-lg"></i></a> 
                             &nbsp;&nbsp;&nbsp;
                             <% if (!assignments.get(i).isActive()){  %>
                                 <a href="assignments?action=delete&id=<%= assignments.get(i).getId() %>" title="Supprimer"  onclick="return confirm('Êtes-vous sûr de vouloir supprimer?')"><i style="color: red" class="fa fa-trash fa-lg"></i></a>                            
                             <% }  %>
                            <% }  %>   
                        </td>
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
        <script>
            function assignmentSelected(x) {
                alert("info: " + x);
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
                      XLSX.writeFile(wb, fn || ('affectations_list.' + (type || 'xlsx')));
                 }
        </script>
            
    </div>
    </div>
    
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>      
        
</body>
</html>