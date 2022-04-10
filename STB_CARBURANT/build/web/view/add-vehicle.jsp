<%@page import="model.Model"%>
<%@page import="model.Fuel"%>
<%@page import="model.Brand"%>
<%@page import="java.util.List"%>
<%@page import="model.Vehicle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <style>
.textInput,select{
  width: 100%;
  padding: 12px 20px;
  margin: 15px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.formInput{
  width: 30%;
  color: white;
  padding: 14px 20px;
  margin: auto;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  
}

input[type=submit]:hover {
  background-color: #45a049;
}

.formFiv {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<head>
    <meta charset="utf-8">
    <title>Ajouter un véhicule</title>
       
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/styles/styles.css" />
    
     <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
     
     
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <link rel="stylesheet" type="text/css" href="/STB_CARBURANT/sidebar-02/css/style.css">

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
                            <a href="vehicles?action=list"><i class="fa fa-car"></i> Véhicules</a>
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

        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
            <div class="row">
                <div class="col-sm-6">
                    <h1 class="text-logo"> <i class="fa fa-plus-square-o"></i> Ajouter un véhicule</h1>
                </div>
            </div>
    
            <form id="add-vehicle-form" action="add_vehicle"   method="post" >
                
                <div class="row">
                    <div class="col-sm-6">

                         <label for="code">Code:</label>       
                         <input type="text" class="textInput" id="code" name="code" placeholder="Code véhicule" required>
                    </div>
                    <div class="col-sm-6">
                         <label for="immatriculation">N° immatriculation :</label>
                         <input required type="text" class="textInput" id="immatriculation" name="immatriculation" placeholder="N° immatriculation">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                       
                        <label for="marque" >Marque :</label>
                        <select id="marque" name="marque" class="textInput"  required>   
                            <option />
                        </select>
                    </div>
                        
                    <div class="col-sm-6">
                        <label for="modele">Modèle :</label>
                        <select id="modele" name="modele" class="textInput" required> 
                        </select>
                        
                    </div>
                </div>
                <div class="row">
                     <div class="col-sm-6">
                          <label for="carburant">Type carburant :</label>
                            <select id="carburant" name="carburant" class="textInput" required>
                                <option />
                                 <% 
                                      List<Fuel> fuels=(List<Fuel>)request.getAttribute("fuels");
                                      for(int i=0;i<fuels.size();i++){
                                  %>
                                      <option value=<%= fuels.get(i).getId() %> > <%= fuels.get(i).getDescription() %></option>
                                 <% }  %>
                            </select>
                    </div>
                    <div class="col-sm-6">                      
                        <label for="marque" >Région :</label>
                        <select id="region" name="region" class="textInput"  required>   
                            <option value="Centre" >Centre</option>                                                  
                            <option value="Est">Est</option>
                            <option value="Ouest" >Ouest</option>
                        </select>
                    </div>
                </div>
                            
                <div class="row">
                    <div class="col-sm-6">
                         <label for="dateCreation">Date création :</label>
                         <input required type="datetime-local" class="textInput" id="dateCreation" name="dateCreation" placeholder="Date création véhicule">
                    </div>
                    <div class="col-sm-6">
                        <label for="consommation">Consommation moyenne :</label>       
                         <input type="number" step='0.01' class="textInput" id="consommation" name="consommation" placeholder="Consommation moyenne" required>

                    </div>
                </div>
                   
                        <br><br>
                <div  class="row">
                    <div class="col-sm-6" style="text-align: center;">      
   
                        <a style="background-color: #db2121;float:left;color: white" class="formInput"  href="vehicles?action=list">Annuler</a>
                    </div>
                    <div class="col-sm-6" >
                        
                        <input style="background-color: #4CAF50;float: right"  type="submit"  value="Valider" class="formInput">
                    </div>                  
                </div>
        </form>
        </div>
        </div>
          
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: "GetBrandModelServlet",
                    method: "GET",
                    data: {operation: 'brand'},
                    success: function (data, textStatus, jqXHR) {
                        let obj = $.parseJSON(data);
                        $.each(obj, function (key, value) {
                            $('#marque').append('<option value="' + value.id + '">' + value.description + '</option>');
                        });         
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
              
                    },
                    cache: false
                });
                
                 $('#marque').change(function () {
                    $('#modele').find('option').remove();

                    let mid = $('#marque').val();
                    let data = {
                        operation: "model",
                        id: mid
                    };

                    $.ajax({
                        url: "GetBrandModelServlet",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            let obj = $.parseJSON(data);
                            $.each(obj, function (key, value) {
                                $('#modele').append('<option value="' + value.id + '">' + value.description + '</option>')
                            });
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#modele').append('<option value="' + value.id + '">' + 'Model Unvjcd' + '</option>');
                        },
                        cache: false
                    });
                });

            });
        </script>
        
        <script type="text/javascript">

                function openPage(pageURL) {
                    window.location = pageURL;
                }

        </script>
        
        <script type="text/javascript">           
            function validateForm(){
                var code=document.forms["add-vehicle-form"]["code"].value;
                if(code==""){
                    alert("Le code ne peut pas être vide");
                }
                return false;
            }
        </script>
        
        
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>
  
    <footer>
    </footer>
</body>
</html>