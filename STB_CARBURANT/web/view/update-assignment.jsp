<%@page import="model.User"%>
<%@page import="model.Assignment"%>
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
    <title>Modifier une affectation</title>
       
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
                    <h1 class="text-logo"> <i class="fa fa-edit"></i> Modifier une affectation conducteur-véhicule</h1>
                </div>
            </div>
            
            <% 
                Assignment assignment=(Assignment) request.getAttribute("assignment");
            %>
    
            <form action="update_assignment?id=<%= assignment.getId() %>"   method="post">
                <div class="row">
                    <div class="col-sm-6">
                        
                        <label for="user" >Conducteur :</label>
                        
                        <input class="textInput" value="<%= assignment.getUser().getLastName() %> &nbsp; <%= assignment.getUser().getFirstName() %> " disabled="">
                      
                    </div>
                        
                    <div class="col-sm-6">
                        <label for="vehicle">Véhicule :</label>
                        <input class="textInput" value="<%= assignment.getVehicle().getCode() %> &nbsp; <%= assignment.getVehicle().getImmatriculation() %> " disabled="">     
                    </div>
                </div>
               
                <div class="row">
                    <div class="col-sm-12">
                         <label for="affectationDate">Date affectation : </label>
                         <input required type="datetime-local" class="textInput" id="affectationDate" name="affectationDate" placeholder="Date affectation" value="<%= assignment.getAffectationDate() %>">
                    </div>
                </div>
                        
                <div class="row">
                    <div class="col-sm-6">
                        <label for="active">État affectation (active/inactive) : </label> <br>
                         
                         <% if(assignment.isActive()){ %>
                         
                        <label class="switch" >
                            <input onload="showAssignmentEndDateInput(this)" onclick="showAssignmentEndDateInput(this)"  type="checkbox"  id="active" name="active" checked >
                            <span class="slider"></span>
                        </label>                     
                        
                        <% }else{%>
                       
                           <label class="switch">
                            <input  type="checkbox" id="active" name="active" >
                            <span class="slider"></span> 
                           </label>
                        <% }%>
                    </div>      
                </div>
                            
        
                <div class="row" id="affectationEndDateDiv">
                    <div class="col-sm-12">
                        <label for="affectationEndDate">Date fin d'affectation :</label>
                        <input  type="datetime-local" min="<%= assignment.getAffectationDate() %>"  class="textInput" id="affectationEndDate" name="affectationEndDate" placeholder="Date fin d'affectation" value="<%= assignment.getAffectationEndDate() %>">
                    </div>
                </div>
                        
                    <br><br>
                            
                <div  class="row">
                    <div class="col-sm-6" style="text-align: center;">      

                        <a style="background-color: #db2121;float:left;color: white" class="formInput"  href="assignments?action=list">Annuler</a>
                    </div>
                    <div class="col-sm-6" >

                        <input style="background-color: #4CAF50;float: right"  type="submit"  value="Valider" class="formInput">
                    </div>                  
                </div>
        </form>
        </div>
                        
                        
        </div>
       
        <script type="text/javascript">
            
            var activeCheckbox= document.getElementById("active");
            var affectationEndDateDiv=document.getElementById("affectationEndDateDiv");
            var affectationEndDate=document.getElementById("affectationEndDate");
            
            $(document).ready(function(){
         
                
                if(activeCheckbox.checked){
                    affectationEndDateDiv.style.display="none";
                    affectationEndDate.required=false;
                    
                }else{
                    activeCheckbox.disabled=true;
                    affectationEndDate.required=true;
                }
            });
            
            function showAssignmentEndDateInput(assignmentActiveCheckbox){
                
                affectationEndDateDiv.style.display=assignmentActiveCheckbox.checked ? "none" : "block";
                affectationEndDate.required=assignmentActiveCheckbox.checked ? "false" : "true";
            }
        </script>            
                      
    <script src="/STB_CARBURANT/sidebar-02/js/popper.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/bootstrap.min.js"></script>
    <script src="/STB_CARBURANT/sidebar-02/js/main.js"></script>
  
    <footer>
    </footer>
</body>
</html>