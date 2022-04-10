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
    <title>Tableau de bord</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />
    
     <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
       
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <link rel="stylesheet" href="/STB_CARBURANT/sidebar-02/css/style.css">
    
    <style>
        
        .card {
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        transition: 0.3s;
        border-radius: 5px; /* 5px rounded corners */
      }

      /* Add rounded corners to the top left and the top right corner of the image */
      img {
        border-radius: 5px 5px 0 0;
      }
    </style>
</head>
<body>
   <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary"><i class="fa fa-bars"></i><span class="sr-only">Toggle Menu</span></button>
                </div>
		<div class="p-4 pt-5">       
                    <h6 style="color: #D0D0D0"><i style="color: #D0D0D0" class="fa fa-user-circle-o"></i> ${user.lastName} ${user.firstName} </h6>
                    <h7 style="color: #D0D0D0"><i style="color: #D0D0D0" class="fa fa-map-marker"></i> ${user.employee.region }</h7>
                    <br><br>
                    <ul class="list-unstyled components mb-5">
                        <li class="active">
                            <a href="dashboard"><i class="fa fa-tachometer fa-lg"></i>  Tableau de bord</a>
                        </li>
                        
                        <li >
                            <a href="vehicles?action=list"><i class="fa fa-car"></i> Véhicules</a>
                        </li>
                        
                        <li >
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
                    
    <%  int countUnprocessedRequests=(Integer) request.getAttribute("countUnprocessedRequests");
        int countUnConfirmedRequests=(Integer) request.getAttribute("countUnConfirmedRequests");
        int countTotalUnprocessedRequests=(Integer) request.getAttribute("countTotalUnprocessedRequests");
        User user=(User) request.getSession().getAttribute("user"); %>

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
            <h1 class="text-logo"> <i class="fa fa-tachometer fa-lg"></i> Tableau de bord & statistiques</h1>
            <div class="row">                           
                <div class="col-sm-4" style="color: orange;">                                     
                    <div onclick="location.href='dashboard?action=new_requests_assignments_list';"  class="card"  style="cursor: pointer">
                        <img src="notification.png" alt="Avatar" style="width:100%;height: 230px;">
                        <div class="container" style="text-align: center">
                            <h4 ><b>Alertes</b></h4>
                        </div>
                        <div class="container" style="text-align: center">                            
                            <%if( user.getRoles().contains("administrateur")) { %>
                            <p style="font-weight: bold; font-size: 15px"><%= countTotalUnprocessedRequests %> Demandes en attente d'aprobation</p>
                            <p style="font-weight: bold; font-size: 15px"><%= countUnConfirmedRequests %> Demandes approuvées en attente de chargement</p>
                            <% }else{ %>
                            <p style="font-weight: bold; font-size: 15px"><%= countUnprocessedRequests %> Demandes en attente d'aprobation</p>
                           <% } %>
                        </div>
                  </div>
                </div>  
                  
            </div>            
        </div>        
   </div>
                    
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>

</body>
</html>