<%@page import="model.User"%>
<%@page import="model.Assignment"%>
<%@page import="model.Trajet"%>
<%@page import="model.Payment"%>
<%@page import="model.Kilometrage"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Détail d'une affectation conducteur-véhicule</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />
    
     <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
     
     
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <link rel="stylesheet" href="/STB_CARBURANT/sidebar-02/css/style.css">
    
    <style>
        .nouveau {
        margin: 0 auto;  
        background-color: #073763;
        color: white;
      }
      .ancien {
        margin: 0 auto;  
        background-color: #4040FF;
        color: white;
      }
        .gray1 {
        margin: 0 auto;  
        background-color: #837b7b;
      }
        .gray2 {
        margin: 0 auto;  
        background-color: #cdcbcb;
      }
        .gray3 {
        margin: 0 auto;  
        background-color: #e2e2e2;
      }
        .orange1 {
        margin: 0 auto;  
        background-color: #f5820b;
      }
        .orange2 {
        margin: 0 auto;  
        background-color: #f7a044;
      }
        .orange3 {
        margin: 0 auto;  
        background-color: #fff2cc;
      }
        .led-red {
        margin: 0 auto;
        width: 35px;
        height: 35px;
        background-color: #F00;
        border-radius: 50%;
        box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #441313 0 -1px 9px, rgba(255, 0, 0, 0.5) 0 2px 12px;
      }
      .led-stable-green {
        margin: 0 auto;
        width: 35px;
        height: 35px;
        background-color: #ABFF00;
        border-radius: 50%;
        box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #304701 0 -1px 9px, #89FF00 0 2px 12px;
      }
      .led-yellow {
  margin: 0 auto;
  width: 35px;
  height: 35px;
  background-color: #FF0;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 12px;
  -webkit-animation: blinkYellow 1s infinite;
  -moz-animation: blinkYellow 1s infinite;
  -ms-animation: blinkYellow 1s infinite;
  -o-animation: blinkYellow 1s infinite;
  animation: blinkYellow 1s infinite;
}

@-webkit-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-moz-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-ms-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@-o-keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}
@keyframes blinkYellow {
    from { background-color: #FF0; }
    50% { background-color: #AA0; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #808002 0 -1px 9px, #FF0 0 2px 0; }
    to { background-color: #FF0; }
}

 .led-green {
  margin: 0 auto;
  width: 35px;
  height: 35px;
  background-color: #24ff00;
  border-radius: 50%;
  box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 12px;
  -webkit-animation: blinkGreen 1s infinite;
  -moz-animation: blinkGreen 1s infinite;
  -ms-animation: blinkGreen 1s infinite;
  -o-animation: blinkGreen 1s infinite;
  animation: blinkGreen 1s infinite;
}

@-webkit-keyframes blinkGreen {
    from { background-color: #24ff00; }
    50% { background-color: #3aba03; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 0; }
    to { background-color: #24ff00; }
}
@-moz-keyframes blinkGreen {
    from { background-color: #24ff00; }
    50% { background-color: #3aba03; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 0; }
    to { background-color: #24ff00; }
}
@-ms-keyframes blinkGreen {
    from { background-color: #24ff00; }
    50% { background-color: #3aba03; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 0; }
    to { background-color: #24ff00; }
}
@-o-keyframes blinkGreen {
    from { background-color: #24ff00; }
    50% { background-color: #3aba03; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 0; }
    to { background-color: #24ff00; }
}
@keyframes blinkGreen {
    from { background-color: #24ff00; }
    50% { background-color: #3aba03; box-shadow: rgba(0, 0, 0, 0.2) 0 -1px 7px 1px, inset #3ac100 0 -1px 9px, #24ff00 0 2px 0; }
    to { background-color: #24ff00; }
}

.border{
    border: 2px solid red;
    border-radius: 25px;
}
.row{
    padding: 5px;
}
    </style>
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
                        
                        <li >
                            <a href="vehicles?action=list"><i class="fa fa-car"></i> Véhicules</a>
                        </li>
                        
                        <li>
                            <a href="drivers?action=list"><i class="fa fa-male"></i> Conducteurs</a>
                        </li>
                        
                        <li class="active">
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
            <div class="col-sm-6">
                <h1 class="text-logo"> <i class="fa fa-info-circle"></i> Détail affectation conducteur-véhicule</h1>
            </div>
            <div class="col-sm-6" >
                
                    <a class="add-button"  style="float:right;color: white"  onclick="history.back()"    title="Retour"><i  class="fa fa-arrow-left"></i></a>

            </div>
        </div>
            
              <% Assignment assignment=(Assignment)request.getAttribute("assignment");
                 User user=(User) request.getSession().getAttribute("user"); %>
              <div class="border" style="text-align:center">
            <div class="row">
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-user-circle-o"></i> Conducteur</h5> 
                    
                </div>
                <div class="col-sm-6" > 
                    <h5 class="text-detail"> <i class="fa fa-car"></i> Véhicule</h5> 
                           
               </div>
          
            </div>
              
            <div class="row">
                <div class="col-sm-6">
                    <%=  assignment.getUser().getLastName() %>  <%=  assignment.getUser().getFirstName() %>
                </div>
                <div class="col-sm-6" > 
                    <%=  assignment.getVehicle().getCode() %>  /  <%=  assignment.getVehicle().getImmatriculation() %>
               </div>
             
            </div>
            
               <br><br>
               
            <div class="row">             
                <div class="col-sm-6" > 
                   
                   <h5 class="text-detail"> <i class="fa fa-calendar"></i> Date d'affectation</h5>                   
                   
               </div>
                <div class="col-sm-6" > 
   
                   <h5 class="text-detail"> <i class="fa fa-calendar"></i> Date fin d'affectation</h5>
               </div>
            </div>
               
            <div class="row">
                <div class="col-sm-6">
                    <%=  assignment.getAffectationDate() %>  
                </div>
                <div class="col-sm-6" > 
                    <%=  assignment.getAffectationEndDate() %>  
               </div>
            </div>
               </div>
             <br><br>
             
            <div class="row">
                <div class="col-sm-6">
                     <h5 class="text-detail"> <i class="fa fa-list"></i> Liste des demandes de chargement</h5>  
                </div>
                <div class="col-sm-6" > 
                    <a  style="float:right;background-color: #09ad32;color:white" onclick="ExportToExcel('xlsx')" class="add-button"  title="Exporter en excel"><i class="fa fa-file"></i></a>
               </div>
            </div>          
                
            <input type="text" id="searchInput" onkeyup="myFunction('table','searchInput')" placeholder="Tapez pour chercher/filtrer ...">            
            <table class="table" id="table">
                <thead style="text-align: center">
                    <tr style="cursor: pointer">
                    <th scope="col" onclick="sortTable(0,'table')">Date  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(1,'table')">Kilométrage  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(2,'table')">Traitement demande  <i class="fa fa-sort"></i></th>
<!--                    <th scope="col" >Actions</th>-->
                 
                  </tr>
                </thead>
                <tbody>
                    <% 
                        String assignmentId=(String)request.getAttribute("assignmentId");
                        
                        List<Kilometrage> kilometrages=(List<Kilometrage>)request.getAttribute("kilometrages");
                        for(int i=0;i<kilometrages.size();i++){
                        %>
                        <tr style="text-align: center" onclick="document.location='assignments?action=detail-kilometrage&kilometrageId=<%= kilometrages.get(i).getId() %>&assignmentId=<%= assignmentId %>';"> 
                            <td><%= kilometrages.get(i).getDate().substring(0,16)  %></td>
                            <td><%= kilometrages.get(i).getKilometrage() %></td>
                            
                            <% if(!kilometrages.get(i).isProcessed()){ %>
                                <td style="text-align: center"><div class="led-yellow"></div></td>
                            <% }else { %>
                                <% if(kilometrages.get(i).isAccepted()) { %>
                                    <% if(kilometrages.get(i).isConfirmed()) { %>
                                        <td style="text-align: center"><div class="led-stable-green"></div></td>
                                    <%  }else{%>
                                        <td style="text-align: center"><div class="led-green"></div></td>
                                    <%  }%>                         
                                <%  }else{%>
                                    <td style="text-align: center"><div class="led-red"></div></td>
                                <%  }%>                          
                            <% }%>
                          
                            
                            <td  style="cursor: pointer"> 
                             <% if(kilometrages.get(i).isProcessed()){ %>
                                   <% if(kilometrages.get(i).isAccepted() &&  !kilometrages.get(i).isConfirmed()) { %>
                                    <%  if(user.getRoles().contains("administrateur")) { %>
                                        &nbsp;&nbsp;&nbsp;
                                        <a href="assignments?action=charger-demande&kilometrageId=<%= kilometrages.get(i).getId() %>&assignmentId=<%= assignmentId %>" title="Charger la demande"  ><i style="color: green" class="fa fa-upload fa-2x"></i></a>
                                    <% }  %>
                               <% }%>
                            
                            <%  }else{%>                       
                            <%  if(assignment.getUser().getEmployee().getRegion().equals(user.getEmployee().getRegion()) || user.getRoles().contains("administrateur") ) { %>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="assignments?action=traiter-demande&kilometrageId=<%= kilometrages.get(i).getId() %>&assignmentId=<%= assignmentId %>" title="Traiter la demande"  ><i style="color: blue" class="fa fa-edit fa-2x"></i></a>                           
                                <% }  %>
                            <%  }%>
                            </td>
                           
                       </tr>
                   <% }  %>
                </tbody>
            </table>
                
                <br><br>   
            <div class="row">
               <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-list"></i> Liste des paiements de carburant</h5> 
               </div>
               <div class="col-sm-6" >  
                    <a  style="float:right;background-color: #09ad32;color:white" onclick="ExportToExcel2('xlsx')" class="add-button"  title="Exporter en excel"><i class="fa fa-file"></i></a>
               </div>
            </div>          
                
            <input type="text" id="searchInput2" onkeyup="myFunction('table2','searchInput2')" placeholder="Tapez pour chercher/filtrer ...">            
            <table class="table" id="table2">
                <thead style="text-align: center">
                    <tr style="cursor: pointer">
                    <th scope="col" onclick="sortTable(0,'table2')">Date  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(1,'table2')">Kilométrage  <i class="fa fa-sort"></i></th>             
                    <th scope="col" onclick="sortTable(3,'table2')">Montant <i class="fa fa-sort"></i></th>                    
                    <th scope="col" onclick="sortTable(6,'table2')">Type paiement <i class="fa fa-sort"></i></th>             
<!--                    <th scope="col" >Actions</th>-->
                  </tr>
                </thead>
                <tbody>
                    <% 
                        List<Payment> payments=(List<Payment>)request.getAttribute("payments");
                        for(int i=0;i<payments.size();i++){
                        %>
                        <tr style="text-align: center" onclick="document.location='assignments?action=detail-payment&paymentId=<%= payments.get(i).getId() %>&assignmentId=<%= assignmentId %>';">
                            <td><%= payments.get(i).getDate().substring(0,16)  %></td>
                            <td><%= payments.get(i).getDistance() %></td>                        
                            <td><%= payments.get(i).getCost() %></td>                          
                            <td><%= payments.get(i).getPaymentType() %></td>
                            
                             <% if (payments.get(i).getPaymentType().equals("1000")){  %>
                            <td><div class="orange1">Carte Naftal</div></td>
                             <% }else if (payments.get(i).getPaymentType().equals("1001")) {  %>
                               <td><div class="orange2">Bon de carburant</div></td>
                              <% } else{ %>
                              <td><div class="orange3">Espèces</div></td>
                              <% }%>
                        
<!--                            <td  style="cursor: pointer;"> 
                                 <a href="assignments?action=detail-payment&paymentId=<%= payments.get(i).getId() %>&assignmentId=<%= assignmentId %>" title="Consulter détail"  ><i style="color: green" class="fa fa-eye fa-lg"></i></a> 

                             </td>-->
                       </tr>
                   <% }  %>
                </tbody>
            </table> 
                
                   <br><br>
                   
            <div class="row">
               <div class="col-sm-6">
                    
                    <h5 class="text-detail"> <i class="fa fa-list"></i> Liste des trajets</h5> 
                    
               </div>
               <div class="col-sm-6" >
                    <a  style="float:right;background-color: #09ad32;color:white" onclick="ExportToExcel3('xlsx')" class="add-button"  title="Exporter en excel"><i class="fa fa-file"></i></a>
               </div>
            </div>          
                
            <input type="text" id="searchInput3" onkeyup="myFunction('table3','searchInput3')" placeholder="Tapez pour chercher/filtrer ...">            
            <table class="table" id="table3">
                <thead style="text-align: center">
                    <tr style="cursor: pointer">
                    <th scope="col" onclick="sortTable(0,'table3')">Type trajet <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(1,'table3')">Motif  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(2,'table3')">Date départ  <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(3,'table3')">Kilométrage départ <i class="fa fa-sort"></i></th>                                    
                    <th scope="col" onclick="sortTable(5,'table3')">Date arrivée <i class="fa fa-sort"></i></th>
                    <th scope="col" onclick="sortTable(6,'table3')">Kilométrage arrivée <i class="fa fa-sort"></i></th>
                  </tr>
                </thead>
                <tbody>
                    <% 
                        
                        
                        List<Trajet> trajets=(List<Trajet>)request.getAttribute("trajets");
                        for(int i=0;i<trajets.size();i++){
                        %>
                        <tr style="text-align: center" onclick="document.location='assignments?action=detail-trajet&trajetId=<%= trajets.get(i).getId() %>&assignmentId=<%= assignmentId %>';">
                            <% if (trajets.get(i).getType().equals("0")){  %>
                            <td><div class="nouveau">Nouveau</div></td>
                             <% }else{  %>
                               <td><div class="ancien">Ancien</div></td>
                              <% }  %>
                               
                            <% if (trajets.get(i).getMotif().equals("TRAVAIL")){  %>
                            <td><div class="gray1">Travail</div></td>
                             <% }else if (trajets.get(i).getMotif().equals("PERSONNEL")) {  %>
                               <td><div class="gray2">Personnel</div></td>
                              <% } else{ %>
                              <td><div class="gray3">Mission</div></td>
                              <% }%>
                            <td><%= trajets.get(i).getDateDepart().substring(0,16)  %></td>
                            <td><%= trajets.get(i).getKilometrageDepart() %></td>
                        
                            <td><%= trajets.get(i).getDateArrivee().substring(0,16) %></td>                            
                            <td><%= trajets.get(i).getKilometrageArrivee() %></td>                  
                        </tr>
                   <% }  %>
                </tbody>
            </table>
                
                   <br><br>
                 
                
                
            <%-- search&filter script--%>
            <script>
                function myFunction(tableId,searchInputId) {
                  // Declare variables
                  var input, filter, table, tr, td, i, txtValue;
                  input = document.getElementById(searchInputId);
                  filter = input.value.toUpperCase();
                  table = document.getElementById(tableId);
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
                function sortTable(n,tableId) {
                  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                  table = document.getElementById(tableId);
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
            
            <script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>            
        <script type="text/javascript">                
                function ExportToExcel(type, fn, dl) {
                    var elt = document.getElementById('table');
                    var wb = XLSX.utils.table_to_book(elt, { sheet: "sheet1" });
                    return dl ?
                      XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }):
                      XLSX.writeFile(wb, fn || ('kilométrages_list.' + (type || 'xlsx')));
                 }
                 
                 function ExportToExcel2(type, fn, dl) {
                    var elt = document.getElementById('table2');
                    var wb = XLSX.utils.table_to_book(elt, { sheet: "sheet1" });
                    return dl ?
                      XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }):
                      XLSX.writeFile(wb, fn || ('paiements_list.' + (type || 'xlsx')));
                 }
                 
                 function ExportToExcel3(type, fn, dl) {
                    var elt = document.getElementById('table3');
                    var wb = XLSX.utils.table_to_book(elt, { sheet: "sheet1" });
                    return dl ?
                      XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }):
                      XLSX.writeFile(wb, fn || ('trajets_list.' + (type || 'xlsx')));
                 }
        </script>
       </div>
            
        <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
        <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
        <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>
</body>
</html>