<%@page import="model.Kilometrage"%>
<%@page import="java.util.List"%>
<%@page import="model.Gps"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Détail de la demande de chargement</title>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />    
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">        
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">    
    <link rel="stylesheet" href="/STB_CARBURANT/sidebar-02/css/style.css">    
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    
    <!-- fichiers css -->
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
   integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
   crossorigin=""/>
   <link rel="stylesheet" href="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.css" />    
     
   <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css" />
   
    <style>
        #map {
            height: 450px;
            width: 100%;
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
    <%    
        Kilometrage kilometrage=(Kilometrage)request.getAttribute("kilometrage");
    %>

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
            
            <div class="row">
                <div class="col-sm-6">
                     <h1 class="text-logo"> <i class="fa fa-info-circle "></i> Détail de la demande de chargement</h1>

                </div>
                <div class="col-sm-6" >

                        <a  style="float:right;color: white" onclick="history.back()" class="add-button"  title="Retour"><i  class="fa fa-backward"></i></a>

                </div>
            </div>
            
            <div class="row">                              
                <div class="col-sm-12">
                    <h5 class="text-detail"> <i class="fa fa-lightbulb-o "></i> Etat de traitement :</h5> 
                    
                     <% if(!kilometrage.isProcessed()) { %>

                     <p class="textInput card" style="color:gray;font-weight: bold;text-align: center">Demande en attente d'approbation  </p>

                       <% }else { %>
                        <% if(kilometrage.isAccepted()) { %>
                            <% if(kilometrage.isAccepted() && kilometrage.isConfirmed() ){ %>
                         <p class="textInput card" style="color:greenyellow;font-weight: bold;;text-align: center">Demande acceptée et chargement effectué</p>
                         <% }else{ %> 
                           <p class="textInput card" style="color:green;font-weight: bold;;text-align: center">Demande acceptée et en attente de chargement </p>
                         <% }}else { %>
                      
                         <p class="textInput card" style="color:red;font-weight: bold;;text-align: center">Demande de refusée </p>
                        <% } %>
                  
                         <% } %>
                </div>                    
            </div>
              
        <% if(kilometrage.isProcessed() && !kilometrage.isAccepted() ){ %>
            <div class="row">                              
                <div class="col-sm-12">
                    <h5 class="text-detail"> <i class="fa fa-commenting-o "></i> Motif de refus :</h5> 
                     <p class="textInput card" style="color:gray;font-weight: bold;text-align: center"><%= kilometrage.getMotif_refus() %> </p>
                </div>                    
            </div>
        <% } %>
        <% if(kilometrage.isProcessed() && kilometrage.isAccepted() && kilometrage.isConfirmed()){ %>
            <div class="row">                              
                <div class="col-sm-12">
                    <h5 class="text-detail"> <i class="fa fa-money "></i> Montant rechargé :</h5> 
                     <p class="textInput card" style="color:gray;font-weight: bold;text-align: center"><%= kilometrage.getMontant_recharge() %>   DA </p>
                </div>                    
            </div>
        <% } %>
            <div class="row">                              
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-calendar "></i> Date :</h5> 
                    <p class="textInput card"><%= kilometrage.getDate() %> </p>
                </div>
                <div class="col-sm-6">
                    
                    <h5 class="text-detail"> <i class="fa fa-calculator "></i> Kilométrage :</h5> 
                    <p class="textInput card"><%= kilometrage.getKilometrage() %> </p>
                </div>                    
            </div>  
                
            <div class="row">                              
                <div class="col-sm-6">
                    <h5 class="text-detail "><i class="fa fa-map-marker"></i> Compteur de kilométrage:</h5>
                    <img width="500" height="450" src="http://192.168.0.59:7020/image/<%= kilometrage.getCompteurKilométrage() %>">
                </div>
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-map-marker"></i> Position GPS:</h5> 
                    <div id="map"></div>
                </div>
            </div>
                
                         
        </div>
        
        <!-- fichiers js -->
       <!-- Make sure you put this AFTER Leaflet's CSS -->
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
      integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
      crossorigin=""></script>
    <script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>
    <script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>  
    
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>
    
    <script>
       var map = L.map('map').setView([<%= kilometrage.getLatitude() %>, <%= kilometrage.getLongitude() %>], 13);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Position GPS'
        }).addTo(map);  
        
        L.marker([<%= kilometrage.getLatitude() %>, <%= kilometrage.getLongitude() %>]).addTo(map);
    </script>
</body>
</html>


