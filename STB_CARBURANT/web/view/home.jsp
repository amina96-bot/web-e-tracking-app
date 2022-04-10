<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home page</title>
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

                    <li>
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

    <h2 class="mb-4">Sidebar #02</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
  </div>
     <div id="content2" class="p-4 p-md-5 pt-5">

    <h2 class="mb-4">Sidebar #03</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
  </div>
            </div>

    <script src="sidebar-02/js/jquery.min.js"></script>
    <script src="sidebar-02/js/popper.js"></script>
    <script src="sidebar-02/js/bootstrap.min.js"></script>
    <script src="sidebar-02/js/main.js"></script>
      
    <footer>

    </footer>
</body>
</html>