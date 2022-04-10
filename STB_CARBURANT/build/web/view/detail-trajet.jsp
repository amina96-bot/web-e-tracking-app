<%@page import="model.Trajet"%>
<%@page import="model.Payment"%>
<%@page import="java.util.List"%>
<%@page import="model.Gps"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Détail d'un trajet</title>
    
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
        Trajet trajet=(Trajet)request.getAttribute("trajet");
    %>

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
            
             <div class="row">
                <div class="col-sm-6">
                     <h1 class="text-logo"> <i class="fa fa-info-circle "></i> Détail du trajet</h1>

                </div>
                <div class="col-sm-6" >

                        <a  style="float:right;color: white" onclick="history.back()" class="add-button"  title="Retour"><i  class="fa fa-backward"></i></a>

                </div>
            </div>
            
           
             <div class="row">                              
                <div class="col-sm-12">
                    <h5 class="text-detail"> <i class="fa fa-question-circle "></i> Motif de déplacement :</h5> 
                    <p class="textInput card"><%= trajet.getMotif() %> </p>
                </div>                  
            </div> 
                
            <div class="row">                              
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-calendar "></i> Date de départ:</h5> 
                    <p class="textInput card"><%= trajet.getDateDepart() %> </p>
                </div>
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-calculator "></i> Kilométrage de départ:</h5> 
                    <p class="textInput card"><%= trajet.getKilometrageDepart() %> </p>
                </div>                    
            </div>  
                
            <div class="row">                              
                <div class="col-sm-12">
                    <h5 class="text-detail"> <i class="fa fa-question-circle "></i> Lieu de départ :</h5> 
                    <p class="textInput card"><%= trajet.getLieuDepart() %> </p>
                </div>                  
            </div> 
                
                <div class="row">                              
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-calendar "></i> Date d'arrivée :</h5> 
                    <p class="textInput card"><%= trajet.getDateArrivee() %> </p>
                </div>
                <div class="col-sm-6">
                    <h5 class="text-detail"> <i class="fa fa-calculator "></i> Kilométrage à l'arrivée :</h5> 
                    <p class="textInput card"><%= trajet.getKilometrageArrivee() %> </p>
                </div>                    
            </div>  
                
            <div class="row">                              
                <div class="col-sm-12" >
                    <h5 class="text-detail"> <i class="fa fa-question-circle "></i> Lieu d'arrivée :</h5> 
                    <p class="textInput card"><%= trajet.getLieuArrivee() %> </p>
                </div>                  
            </div> 
                
            <div class="row">                              
                <div class="col-sm-12">
                  <h5 class="text-detail"> <i class="fa fa-commenting-o   "></i> Commentaire :</h5> 
                   <p class="textInput card"><%= trajet.getNote() %> </p>
                </div>                  
            </div>
              
            
           <div class="row">
                 <div class="col-sm-6">
                    <h5 class="text-detail "> <i class="fa fa-map-marker"></i> Itinéraire GPS:</h5>  
                </div>                               
                <div id="map"></div>
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
        <%    
            List<Gps> gps=(List<Gps>)request.getAttribute("gps");
            if(!gps.isEmpty()){
        %>
       var map = L.map('map').setView([ <%= gps.get(0).getLatitude() %>, <%= gps.get(0).getLongitude() %>], 13);

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Itinéraire suivi'
        }).addTo(map);      
       
        let coordinates =[];
        
        <%    
            for(int i=0;i<gps.size();i++){
        %>
            coordinates.push( { lat: <%= gps.get(i).getLatitude() %> , lng:  <%= gps.get(i).getLongitude()%> } );  
        <% 
            }           
        %>  
            console.log(coordinates);
        var polyline = L.polyline(coordinates, { weight: 3, color: '#f73434'}).addTo(map);

        // zoom the map to the polyline
        map.fitBounds(polyline.getBounds());
        
        <% 
            }           
        %>  

 </script>
</body>
</html>


