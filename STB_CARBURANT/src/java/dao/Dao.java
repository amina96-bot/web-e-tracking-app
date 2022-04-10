package dao;

import Connexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Assignment;
import model.Brand;
import model.Departement;
import model.Employee;
import model.Fuel;
import model.Gps;
import model.Kilometrage;
import model.Model;
import model.Payment;
import model.Trajet;
import model.User;
import model.Vehicle;

/**
 *
 * @author akhaldi
 */
public class Dao {

    Connection connection;
    String sql;
    Dao dao2;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public List<String> getRoles(int userId) {
        List<String> roles = new ArrayList();
        try {
            sql = "SELECT role_designation from users inner join user_role on usr_id=ur_usr_id inner join role on role_id=ur_role_id  where usr_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                roles.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public List<String> getRegion(int userId) {
        List<String> roles = new ArrayList();
        try {
            sql = "SELECT role_designation from users inner join user_role on usr_id=ur_usr_id inner join role on role_id=ur_role_id  where usr_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                roles.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList();
        try {
            sql = "SELECT e.ID, e.NomEmployeSB, e.DateNSB, e.Matricule,e.Mobile,e.NomEmploye,e.PrenomEmploye, f.FonctionEmployeSB, s.NomService,d.ID, u.NomUtilisateur  from EmployeSBs e inner join FonctionSBs f on e.FonctionId=f.ID inner join Services s on e.ServiceId=s.ID inner join Departements d on s.DepartementId=d.ID inner join Utilisateurs u on e.SuperviseurId=u.ID  order by e.NomEmployeSB asc";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Employee employee = new Employee();
                employee.setId(result.getInt(1));
                employee.setRaisonSociale(result.getString(2));
                employee.setDateNaissance(result.getString(3));
                employee.setMatricule(result.getString(4));
                employee.setMobile(result.getString(5));
                employee.setLastName(result.getString(6));
                employee.setFirstName(result.getString(7));
                employee.setFonction(result.getString(8));
                employee.setService(result.getString(9));
                employee.setDepartement(result.getString(10));
                employee.setSuperviseur(result.getString(11));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        try {
            sql = "select e.ID, e.NomEmployeSB, e.DateNSB, e.Matricule,e.Mobile,e.NomEmploye,e.PrenomEmploye, f.FonctionEmployeSB, s.NomService,d.CodeDepartement, u.NomUtilisateur, r.region  from EmployeSBs e inner join FonctionSBs f on e.FonctionId=f.ID inner join Services s on e.ServiceId=s.ID inner join Departements d on s.DepartementId=d.ID inner join Utilisateurs u on e.SuperviseurId=u.ID inner join Regions r on e.RegionId=r.ID where e.ID=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                employee = new Employee();
                employee.setId(result.getInt(1));
                employee.setRaisonSociale(result.getString(2));
                employee.setDateNaissance(result.getString(3));
                employee.setMatricule(result.getString(4));
                employee.setMobile(result.getString(5));
                employee.setLastName(result.getString(6));
                employee.setFirstName(result.getString(7));
                employee.setFonction(result.getString(8));
                employee.setService(result.getString(9));
                employee.setDepartement(result.getString(10));
                employee.setSuperviseur(result.getString(11));
                employee.setRegion(result.getString(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList();
        try {
            sql = "SELECT distinct vh_id,vh_code,vh_immatriculaton,vh_creation_date,vh_active,average_consumption,md_id,md_description,br_id,br_description,fl_id,fl_description,region FROM vehicle inner join model on vh_md_id=md_id inner join brand on md_br_id=br_id inner join fuel on vh_fl_id=fl_id order by vh_code asc";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(result.getInt(1));
                vehicle.setCode(result.getString(2));
                vehicle.setImmatriculation(result.getString(3));
                vehicle.setCreationDate(result.getString(4));
                vehicle.setActive(result.getByte(5) != 0);
                vehicle.setAverageConsumption(String.valueOf(result.getDouble(6)));

                Brand brand = new Brand();
                brand.setId(result.getInt(9));
                brand.setDescription(result.getString(10));

                Model model = new Model();
                model.setId(result.getInt(7));
                model.setDescription(result.getString(8));
                model.setBrand(brand);

                Fuel fuel = new Fuel();
                fuel.setId(result.getInt(11));
                fuel.setDescription(result.getString(12));

                vehicle.setModel(model);
                vehicle.setFuel(fuel);
                vehicle.setRegion(result.getString(13));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
    
     public List<Vehicle> getAllVehiclesByRegion(String region) {
        List<Vehicle> vehicles = new ArrayList();
        try {
            sql = "SELECT distinct vh_id,vh_code,vh_immatriculaton,vh_creation_date,vh_active,average_consumption,md_id,md_description,br_id,br_description,fl_id,fl_description,region FROM vehicle inner join model on vh_md_id=md_id inner join brand on md_br_id=br_id inner join fuel on vh_fl_id=fl_id where region=? order by vh_code asc";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, region);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(result.getInt(1));
                vehicle.setCode(result.getString(2));
                vehicle.setImmatriculation(result.getString(3));
                vehicle.setCreationDate(result.getString(4));
                vehicle.setActive(result.getByte(5) != 0);
                vehicle.setAverageConsumption(String.valueOf(result.getDouble(6)));

                Brand brand = new Brand();
                brand.setId(result.getInt(9));
                brand.setDescription(result.getString(10));

                Model model = new Model();
                model.setId(result.getInt(7));
                model.setDescription(result.getString(8));
                model.setBrand(brand);

                Fuel fuel = new Fuel();
                fuel.setId(result.getInt(11));
                fuel.setDescription(result.getString(12));

                vehicle.setModel(model);
                vehicle.setFuel(fuel);
                vehicle.setRegion(result.getString(13));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void addNewvehicle(String code, String immatriculation, String modele, String dateCreation, String carburant, String consommation,String region) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateCreation);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            sql = "insert into vehicle(vh_md_id,vh_fl_id,vh_code,vh_immatriculaton,vh_creation_date,vh_active,average_consumption,region) values(?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(modele));
            statement.setInt(2, Integer.valueOf(carburant));
            statement.setString(3, code);
            statement.setString(4, immatriculation);
            statement.setTimestamp(5, sqlDate);
            statement.setByte(6, Byte.valueOf("1"));
            statement.setDouble(7, Double.valueOf(consommation));
            statement.setString(8, region);
            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVehicle(String id, String code, String immatriculation, String modele, String dateCreation, String carburant, String active, String consommation, String region) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateCreation);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            sql = "update vehicle set vh_md_id=? ,vh_fl_id=?,vh_code=?,vh_immatriculaton=?,vh_creation_date=?,vh_active=?,average_consumption=?, region=? where vh_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(modele));
            statement.setInt(2, Integer.valueOf(carburant));
            statement.setString(3, code);
            statement.setString(4, immatriculation);
            statement.setTimestamp(5, sqlDate);
            statement.setByte(6, Byte.valueOf(active));
            statement.setDouble(7, Double.valueOf(consommation));
            statement.setString(8, region);
            statement.setInt(9, Integer.valueOf(id));
            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateVehicleAverageCons(int id, Double consommation) {
        try {
            sql = "update vehicle set average_consumption=? where vh_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setDouble(1, consommation);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refuserDemandeChargement(String id, String motif) {
        try {
            sql = "update kilometrage set accepted=?,motif_refus=?  where km_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Byte.valueOf("0"));
            statement.setString(2, motif);
            statement.setInt(3, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accepterDemandeChargement(String id) {
        try {
            sql = "update kilometrage set accepted=? where km_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Byte.valueOf("1"));
            statement.setInt(2, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chargerDemandeChargement(String id, Double montant) {
        try {
            sql = "update kilometrage set accepted=?, montant_recharge=? where km_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Byte.valueOf("1"));
            statement.setDouble(2, montant);
            statement.setInt(3, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteVehicle(String id) {
        try {
            sql = "delete from vehicle where vh_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vehicle getVehicleById(int vehicleId) {
        Vehicle vehicle = null;
        try {
            sql = "select vh_id,vh_code,vh_immatriculaton,vh_creation_date,vh_active,average_consumption,md_id,md_description,br_id,br_description,fl_id,fl_description, region FROM vehicle inner join model on vh_md_id=md_id inner join brand on md_br_id=br_id inner join fuel on vh_fl_id=fl_id where vh_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, vehicleId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                vehicle = new Vehicle();
                vehicle.setId(result.getInt(1));
                vehicle.setCode(result.getString(2));
                vehicle.setImmatriculation(result.getString(3));
                vehicle.setCreationDate(result.getString(4));
                vehicle.setActive(result.getByte(5) != 0);
                vehicle.setAverageConsumption(String.valueOf(result.getDouble(6)));

                Brand brand = new Brand();
                brand.setId(result.getInt(9));
                brand.setDescription(result.getString(10));

                Model model = new Model();
                model.setId(result.getInt(7));
                model.setDescription(result.getString(8));
                model.setBrand(brand);

                Fuel fuel = new Fuel();
                fuel.setId(result.getInt(11));
                fuel.setDescription(result.getString(12));

                vehicle.setRegion(result.getString(13));
                vehicle.setModel(model);
                vehicle.setFuel(fuel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public Departement getDepartementById(int id) {
        Departement departement = null;
        try {
            sql = "select * from DEPARTEMENT where dep_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                departement = new Departement();
                departement.setId(result.getInt(1));
                departement.setDescription(result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }
        
    public Departement getDepartementByDescription(String description) {
        Departement departement = null;
        try {
            sql = "select * from DEPARTEMENT where dep_description = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, description);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                departement = new Departement();
                departement.setId(result.getInt(1));
                departement.setDescription(result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departement;
    }

    public List<Brand> getAllBrand() {
        List<Brand> brands = new ArrayList<>();
        try {
            sql = "select * from brand";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Brand brand = new Brand();
                brand.setId(result.getInt(1));
                brand.setDescription(result.getString(2));
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    public List<Fuel> getAllFuel() {
        List<Fuel> fuels = new ArrayList<>();
        try {
            sql = "select * from fuel";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Fuel fuel = new Fuel();
                fuel.setId(result.getInt(1));
                fuel.setDescription(result.getString(2));
                fuels.add(fuel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuels;
    }

    public List<Model> getModelByBrandId(int brandId) {
        List<Model> models = new ArrayList<>();
        try {
            sql = "select md_id,md_description,br_id,br_description from model inner join brand on md_br_id=br_id where md_br_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, brandId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Brand brand = new Brand();
                brand.setId(result.getInt(3));
                brand.setDescription(result.getString(4));

                Model model = new Model();
                model.setId(result.getInt(1));
                model.setDescription(result.getString(2));
                model.setBrand(brand);
                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList();
        try {
            sql = "SELECT  usr_id,usr_login,usr_password,usr_active,employee_id, usr_name,usr_first_name,usr_dep_id  FROM users inner join user_role on usr_id=ur_usr_id inner join role on role_id=ur_role_id inner join DEPARTEMENT on usr_dep_id=dep_id where role_designation='conducteur' order by usr_name asc";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setActive(result.getByte(4) != 0);
                user.setEmployeeId(String.valueOf(result.getInt(5)));
                user.setLastName(result.getString(6));
                user.setFirstName(result.getString(7));

                int dep_id=result.getInt(8);
                dao2 = new Dao(DatabaseConnection.getConnection2());
                user.setEmployee(dao2.getEmployeeById(Integer.valueOf(user.getEmployeeId())));
                user.setRoles(getRoles(user.getId()));
                user.setDepartement(this.getDepartementById(dep_id));
                
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
     public List<User> getAllUsersByRegion(String region) {
        List<User> users = new ArrayList();
        try {
            sql = "SELECT  usr_id,usr_login,usr_password,usr_active,employee_id, usr_name,usr_first_name,usr_dep_id  FROM users inner join user_role on usr_id=ur_usr_id inner join role on role_id=ur_role_id inner join DEPARTEMENT on usr_dep_id=dep_id where role_designation='conducteur' and region=? order by usr_name asc";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, region);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setActive(result.getByte(4) != 0);
                user.setEmployeeId(String.valueOf(result.getInt(5)));
                user.setLastName(result.getString(6));
                user.setFirstName(result.getString(7));

                int dep_id=result.getInt(8);
                dao2 = new Dao(DatabaseConnection.getConnection2());
                user.setEmployee(dao2.getEmployeeById(Integer.valueOf(user.getEmployeeId())));
                user.setRoles(getRoles(user.getId()));
                user.setDepartement(this.getDepartementById(dep_id));
                
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Departement> getAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        try {
            sql = "select * from departement";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Departement departement = new Departement();
                departement.setId(result.getInt(1));
                departement.setDescription(result.getString(2));
                departements.add(departement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departements;
    }

    public void addNewUser(Employee employee, String login, String password) {
        Departement dep = this.getDepartementByDescription(employee.getDepartement());
        try {
            sql = "insert into users(usr_name, usr_first_name,usr_dep_id, usr_login,usr_password,usr_active,employee_id,superviseur, region) values(?, ?, ?,?,?,?,?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getFirstName());
            statement.setInt(3, dep.getId());

            statement.setString(4, login);
            statement.setString(5, password);

            statement.setByte(6, Byte.valueOf("1"));
            statement.setInt(7, employee.getId());
            statement.setString(8, employee.getSuperviseur());
            statement.setString(9, employee.getRegion());
            int result = statement.executeUpdate();

            if (result > 0) {

                ResultSet rsVal = statement.getGeneratedKeys();
                rsVal.next();

                int lastInsertedUserId = rsVal.getInt(1);
                System.out.println(lastInsertedUserId);

                sql = "insert into user_role(ur_usr_id,ur_role_id) values(?, ?)";
                statement = this.connection.prepareStatement(sql);
                statement.setInt(1, lastInsertedUserId);
                statement.setInt(2, 1001);
                statement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String id) {
        try {
            if (!checkIfUserHasAffectation(Integer.valueOf(id))) {
                sql = "delete from user_role where ur_role_id=1001 and ur_usr_id= ? ";
                PreparedStatement statement = this.connection.prepareStatement(sql);
                statement.setInt(1, Integer.valueOf(id));
                statement.executeUpdate();

                sql = "delete from users where usr_id= ? ";
                statement = this.connection.prepareStatement(sql);
                statement.setInt(1, Integer.valueOf(id));
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUserHasAffectation(int userId) {
        boolean bool = false;
        try {
            sql = "select * from affectation where af_usr_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public User getUserById(int userId) {
        User user = null;
        try {
            sql = "select usr_id,usr_login,usr_password,usr_active,employee_id, usr_name,usr_first_name,usr_dep_id FROM users where usr_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                user = new User();
                user.setId(result.getInt(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setActive(result.getByte(4) != 0);
                user.setEmployeeId(String.valueOf(result.getInt(5)));
                user.setLastName(result.getString(6));
                user.setFirstName(result.getString(7));
                
                int dep_id=result.getInt(8);
                dao2 = new Dao(DatabaseConnection.getConnection2());
                user.setEmployee(dao2.getEmployeeById(Integer.valueOf(user.getEmployeeId())));
                user.setRoles(getRoles(user.getId()));
                user.setDepartement(this.getDepartementById(dep_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public int getUserByUsernameAndPassword(String username, String password) {
        int  userId=0;
        try {
            sql = "SELECT * FROM users where usr_login = ? and usr_password = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
             statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                userId= result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public void updateUser(String id, String login, String password, String active) {
        try {
            sql = "update users set usr_login=?,usr_password=?,usr_active=? where usr_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setByte(3, Byte.valueOf(active));
            statement.setInt(4, Integer.valueOf(id));
            statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = res.getInt(3);
                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getAllAssignmentsByRegion(String region) {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation inner join users on af_usr_id=usr_id  where region=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, region);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = res.getInt(3);
                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getDriverAssignments(String id) {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT af_id, af_vh_id,af_date,af_active,af_end_date from affectation where af_usr_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = Integer.valueOf(id);
                int vehicleId = res.getInt(2);
                assignment.setAffectationDate(res.getString(3));
                assignment.setActive(res.getByte(4) != 0);
                assignment.setAffectationEndDate(res.getString(5));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getVehicleAssignments(String id) {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT af_id, af_usr_id,af_date,af_active,af_end_date from affectation where af_vh_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = Integer.valueOf(id);
                assignment.setAffectationDate(res.getString(3));
                assignment.setActive(res.getByte(4) != 0);
                assignment.setAffectationEndDate(res.getString(5));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getAllNewRequestsAssignmentsByRegion(String region) {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT distinct af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation inner join kilometrage on af_usr_id=km_usr_id  inner join users on af_usr_id=usr_id    where af_active=1 and km_date > af_date and accepted is null and region=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, region);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = res.getInt(3);
                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
    
    public List<Assignment> getAllNewRequestsAssignments() {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT distinct af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation inner join kilometrage on af_usr_id=km_usr_id  inner join users on af_usr_id=usr_id    where af_active=1 and km_date > af_date and (accepted is null  or (accepted=1 and montant_recharge is null))";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = res.getInt(3);
                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));
                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public List<Assignment> getAllUnconfirmedRequestsAssignments() {
        List<Assignment> assignments = new ArrayList();
        try {
            sql = "SELECT distinct af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation inner join kilometrage on af_usr_id=km_usr_id  where af_active=1 and km_date > af_date and accepted=1 and montant_recharge is null";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(res.getInt(1));
                int userId = res.getInt(2);
                int vehicleId = res.getInt(3);

                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));

                User user = this.getUserById(userId);
                Vehicle vehicle = this.getVehicleById(vehicleId);

                assignment.setUser(user);
                assignment.setVehicle(vehicle);

                assignments.add(assignment);
            }
            initialiseAssignmentsList(assignments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    public int countUnprocessedRequests(String region) {
        int count = 0;
        try {
            sql = "SELECT distinct count(*) from affectation inner join kilometrage on af_usr_id=km_usr_id inner join users on af_usr_id=usr_id   where af_active=1 and km_date > af_date and accepted is null and region=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, region);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
     public int countTotalUnprocessedRequests() {
        int count = 0;
        try {
            sql = "SELECT distinct count(*) from affectation inner join kilometrage on af_usr_id=km_usr_id inner join users on af_usr_id=usr_id   where af_active=1 and km_date > af_date and accepted is null";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int countUnConfirmedRequests() {
        int count = 0;
        try {
            sql = "SELECT distinct count(*) from affectation inner join kilometrage on af_usr_id=km_usr_id  where af_active=1 and km_date > af_date and accepted=1 and montant_recharge is null";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void addNewAssignment(String user, String vehicle, String affectationDate) {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(affectationDate);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

            sql = "insert into affectation(af_usr_id,af_vh_id,af_date,af_active) values(?, ?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(user));
            statement.setInt(2, Integer.valueOf(vehicle));
            statement.setTimestamp(3, sqlDate);
            statement.setByte(4, Byte.valueOf("1"));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfUserHasActiveAffectation(int userId) {
        boolean bool = false;
        try {
            sql = "select * from affectation where af_active= 1 and af_usr_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkIfVehicleIsAffected(int vehicleId) {
        boolean bool = false;
        try {
            sql = "select * from affectation where af_active= 1 and af_vh_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, vehicleId);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkAffectationDate(int userId, int vehicleId, String affectationDate) {
        boolean bool = false;
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(affectationDate);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());

            sql = "select * from affectation where af_active= 0 and af_usr_id = ? and af_vh_id = ? and af_end_date> ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, vehicleId);
            statement.setTimestamp(3, sqlDate);

            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }

    public void deleteAssignment(String id) {
        try {
            sql = "delete from affectation where af_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(id));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Assignment getAssignmentById(int assignmentId) {
        Assignment assignment = null;
        try {
            sql = "select af_id,af_usr_id, af_vh_id,af_date,af_active,af_end_date  from affectation  where af_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, assignmentId);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                assignment = new Assignment();
                assignment.setId(res.getInt(1));
                assignment.setAffectationDate(res.getString(4));
                assignment.setActive(res.getByte(5) != 0);
                assignment.setAffectationEndDate(res.getString(6));
                User user = this.getUserById(res.getInt(2));
                Vehicle vehicle = this.getVehicleById(res.getInt(3));
                assignment.setUser(user);
                assignment.setVehicle(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignment;
    }

    public void updateAssignment(String id, String affectationDate, String affectationEndDate, String active) {
        try {
            PreparedStatement statement;
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(affectationDate);
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
            if ("0".equals(active)) { // affectation inactive (terminée)
                java.util.Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(affectationEndDate);
                java.sql.Timestamp sqlEndDate = new java.sql.Timestamp(endDate.getTime());
                sql = "update affectation set af_date=?,af_active=?,af_end_date=? where af_id=?";
                statement = this.connection.prepareStatement(sql);
                statement.setTimestamp(3, sqlEndDate);
                statement.setInt(4, Integer.valueOf(id));
            } else { // affectation active 
                sql = "update affectation set af_date=?,af_active=? where af_id=?";
                statement = this.connection.prepareStatement(sql);
                statement.setInt(3, Integer.valueOf(id));
            }

            statement.setTimestamp(1, sqlDate);
            statement.setByte(2, Byte.valueOf(active));

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean activeAffectationExist(int affectationId) {
        try {
            sql = "select * from AFFECTATION where af_active=1 and af_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                sql = "select km_id,km_date,km_kilometrage,km_latitude,km_longitude from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date > af_date and af_id=?";
                return true;
            } else {
                sql = "select km_id,km_date,km_kilometrage,km_latitude,km_longitude from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date between af_date and af_end_date and af_id=?";
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ************************************** Get All data ********************************************
     */
    public List<Kilometrage> getAllKilometrage(int affectationId) {
        List<Kilometrage> kilometrages = new ArrayList();
        try {
            if (activeAffectationExist(affectationId)) {
                sql = "select km_id,km_date,km_kilometrage,km_latitude,km_longitude,accepted, montant_recharge,motif_refus  from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date > af_date and af_id=?  order by km_date desc";
            } else {
                sql = "select km_id,km_date,km_kilometrage,km_latitude,km_longitude,accepted, montant_recharge,motif_refus  from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date between af_date and af_end_date and af_id=?  order by km_date desc";
            }
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Kilometrage kilometrage = new Kilometrage();
                kilometrage.setId(result.getInt(1));
                kilometrage.setDate(result.getString(2));
                kilometrage.setKilometrage(String.valueOf(result.getDouble(3)));
                kilometrage.setLatitude(String.valueOf(result.getDouble(4)));
                kilometrage.setLongitude(String.valueOf(result.getDouble(5)));

                result.getString(6);
                if (result.wasNull()) {
                    kilometrage.setProcessed(false);
                } else {
                    kilometrage.setProcessed(true);
                    kilometrage.setAccepted(result.getByte(6) != 0);
                }
                result.getString(7);
                if (result.wasNull()) {
                    kilometrage.setConfirmed(false);
                    kilometrage.setMontant_recharge(null);
                } else {
                    kilometrage.setConfirmed(true);
                    kilometrage.setMontant_recharge(String.valueOf(result.getDouble(7)));
                }
                kilometrage.setMotif_refus(result.getString(8));

                kilometrages.add(kilometrage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kilometrages;
    }

    public List<Payment> getAllPayment(int affectationId) {
        List<Payment> payments = new ArrayList();

        try {
            if (activeAffectationExist(affectationId)) {
                sql = "select pa_id,pa_date,pa_distance,pa_unit_price,pa_cost,pa_latitude,pa_longitude,pa_pt_id,pa_comment from PAYMENT  inner join affectation on pa_usr_id=af_usr_id where pa_date > af_date and af_id=?  order by pa_date desc";
            } else {
                sql = "select pa_id,pa_date,pa_distance,pa_unit_price,pa_cost,pa_latitude,pa_longitude,pa_pt_id,pa_comment from PAYMENT inner join affectation on pa_usr_id=af_usr_id where pa_date between af_date and af_end_date and af_id=?   order by pa_date desc";
            }
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Payment payment = new Payment();
                payment.setId(result.getInt(1));
                payment.setDate(result.getString(2));
                payment.setDistance(String.valueOf(result.getDouble(3)));
                payment.setUnitPrice(String.valueOf(result.getDouble(4)));
                payment.setCost(String.valueOf(result.getDouble(5)));

                payment.setLatitude(String.valueOf(result.getDouble(6)));
                payment.setLongitude(String.valueOf(result.getDouble(7)));
                payment.setPaymentType(result.getString(8));
                payment.setComment(result.getString(9));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public List<Trajet> getAllTrajet(int affectationId) {
        List<Trajet> trajets = new ArrayList();
        try {
            if (activeAffectationExist(affectationId)) {
                sql = "select tr_id,tr_motif,tr_date_depart,tr_kilometrage_depart,tr_lieu_depart,tr_date_arrivee,tr_kilometrage_arrivee,tr_lieu_arrivee,tr_note,tr_type from TRAJET inner join affectation on tr_usr_id=af_usr_id where tr_date_depart > af_date and af_id=?  order by tr_date_depart desc";
            } else {
                sql = "select tr_id,tr_motif,tr_date_depart,tr_kilometrage_depart,tr_lieu_depart,tr_date_arrivee,tr_kilometrage_arrivee,tr_lieu_arrivee,tr_note,tr_type from TRAJET inner join affectation on tr_usr_id=af_usr_id where tr_date_depart between af_date and af_end_date and af_id=?  order by tr_date_depart desc";
            }
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Trajet trajet = new Trajet();
                trajet.setId(result.getInt(1));
                trajet.setMotif(result.getString(2));
                trajet.setDateDepart(result.getString(3));
                trajet.setKilometrageDepart(String.valueOf(result.getDouble(4)));
                trajet.setLieuDepart(result.getString(5));

                trajet.setDateArrivee(result.getString(6));
                trajet.setKilometrageArrivee(String.valueOf(result.getDouble(7)));
                trajet.setLieuArrivee(result.getString(8));

                trajet.setNote(result.getString(9));
                trajet.setType(result.getString(10));
                trajets.add(trajet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajets;
    }

    public List<Gps> getAllGps(int affectationId, int trajetId) {
        List<Gps> gpsList = new ArrayList();

        try {
            sql = "select gps_id,gps_latitude,gps_longitude,gps_date from GPS inner join affectation on gps_usr_id=af_usr_id inner join trajet on tr_usr_id=af_usr_id  where  gps_date between tr_date_depart and tr_date_arrivee and af_id= ? and tr_id= ? order by gps_date asc";
            System.out.println(sql);
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            statement.setInt(2, trajetId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getString(2) + "  " + result.getString(3) + "  " + result.getString(4));
                Gps gps = new Gps();
                gps.setId(result.getInt(1));
                gps.setLatitude(result.getString(2));
                gps.setLongitude(result.getString(3));
                gps.setDate(result.getString(4));

                gpsList.add(gps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gpsList;
    }

    public List<Gps> getAllGps2(int affectationId, int trajetId) {
        List<Gps> gpsList = new ArrayList();

        try {
            sql = "select gps_id,gps_latitude,gps_longitude,gps_date from GPS inner join affectation on gps_usr_id=af_usr_id inner join trajet on tr_usr_id=af_usr_id  where  gps_date > tr_date_depart  and af_id= ? and tr_id= ?  order by gps_date asc";
            System.out.println(sql);
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, affectationId);
            statement.setInt(2, trajetId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Gps gps = new Gps();
                gps.setId(result.getInt(1));
                gps.setLatitude(result.getString(2));
                gps.setLongitude(result.getString(3));
                gps.setDate(result.getString(4));

                gpsList.add(gps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gpsList;
    }

    public ArrayList<HashMap<String, String>> getGlobalKmValues(int affectationId) {
        ArrayList<HashMap<String, String>> kilometrages = new ArrayList<>();
        try {
            if (activeAffectationExist(affectationId)) {
                System.out.print("11111111111111111111");
                sql = "select a.date, a.kilometrage from ( select km_date as date,km_kilometrage as kilometrage from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date > af_date  and af_id=" + affectationId + " UNION select pa_date as date,pa_distance as kilometrage from PAYMENT inner join affectation on pa_usr_id=af_usr_id where pa_date > af_date  and af_id=" + affectationId + " UNION select tr_date_depart as date,tr_kilometrage_depart as kilometrage from TRAJET inner join affectation on tr_usr_id=af_usr_id where tr_date_depart > af_date  and af_id=" + affectationId + " ) a";
            } else {
                System.out.print("22222222222222222222");
                sql = "select b.date, b.kilometrage from ( select km_date as date,km_kilometrage as kilometrage from KILOMETRAGE inner join affectation on km_usr_id=af_usr_id where km_date between af_date and af_end_date and af_id=" + affectationId + " UNION select pa_date as date,pa_distance as kilometrage from PAYMENT inner join affectation on pa_usr_id=af_usr_id where pa_date between af_date and af_end_date  and af_id=" + affectationId + " UNION select tr_date_depart as date,tr_kilometrage_depart as kilometrage from TRAJET inner join affectation on tr_usr_id=af_usr_id where tr_date_depart between af_date and af_end_date  and af_id=" + affectationId + ") b";
            }
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                HashMap<String, String> element = new HashMap<>();
                element.put("date", result.getString(1));
                element.put("kilometrage", String.valueOf(result.getDouble(2)));
                kilometrages.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kilometrages;
    }

    /**
     * ******************************************* Get By Id ************************************
     */
    public Trajet getTrajetById(int trajetId) {
        Trajet trajet = null;
        try {
            sql = "select tr_id,tr_motif,tr_date_depart,tr_kilometrage_depart,tr_lieu_depart,tr_date_arrivee,tr_kilometrage_arrivee,tr_lieu_arrivee,tr_note,tr_type from trajet where tr_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, trajetId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                trajet = new Trajet();
                trajet.setId(result.getInt(1));
                trajet.setMotif(result.getString(2));
                trajet.setDateDepart(result.getString(3));
                trajet.setKilometrageDepart(String.valueOf(result.getDouble(4)));
                trajet.setLieuDepart(result.getString(5));

                trajet.setDateArrivee(result.getString(6));
                trajet.setKilometrageArrivee(String.valueOf(result.getDouble(7)));
                trajet.setLieuArrivee(result.getString(8));

                trajet.setNote(result.getString(9));
                trajet.setType(result.getString(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajet;
    }

    public Kilometrage getKilometrageById(int kilometrageId) {
        Kilometrage kilometrage = null;
        try {
            sql = "select km_id,km_date,km_kilometrage,km_latitude,km_longitude, km_file_name, accepted, montant_recharge,motif_refus,km_usr_id from KILOMETRAGE where km_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, kilometrageId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                kilometrage = new Kilometrage();
                kilometrage.setId(result.getInt(1));
                kilometrage.setDate(result.getString(2));
                kilometrage.setKilometrage(String.valueOf(result.getDouble(3)));
                kilometrage.setLatitude(String.valueOf(result.getDouble(4)));
                kilometrage.setLongitude(String.valueOf(result.getDouble(5)));
                kilometrage.setCompteurKilométrage(result.getString(6));

                kilometrage.setMotif_refus(result.getString(9));

                result.getString(7);
                if (result.wasNull()) {
                    kilometrage.setProcessed(false);
                } else {
                    kilometrage.setProcessed(true);
                    kilometrage.setAccepted(result.getByte(7) != 0);
                }
                result.getString(8);
                if (result.wasNull()) {
                    kilometrage.setConfirmed(false);
                    kilometrage.setMontant_recharge(null);
                } else {
                    kilometrage.setConfirmed(true);
                    kilometrage.setMontant_recharge(String.valueOf(result.getDouble(8)));
                }
             kilometrage.setUsr_id(result.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kilometrage;
    }

    public Payment getPaymentById(int paymentId) {
        Payment payment = null;
        try {
            sql = "select pa_id,pa_date,pa_distance,pa_unit_price,pa_cost,pa_latitude,pa_longitude,pt_description,pa_comment,pa_file_name,pa_km_counter_file_name from PAYMENT inner join PAYMENT_TYPE on pa_pt_id=pt_id where pa_id=?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, paymentId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                payment = new Payment();
                payment.setId(result.getInt(1));
                payment.setDate(result.getString(2));
                payment.setDistance(String.valueOf(result.getDouble(3)));
                payment.setUnitPrice(String.valueOf(result.getDouble(4)));
                payment.setCost(String.valueOf(result.getDouble(5)));

                payment.setLatitude(String.valueOf(result.getDouble(6)));
                payment.setLongitude(String.valueOf(result.getDouble(7)));
                payment.setPaymentType(result.getString(8));
                payment.setComment(result.getString(9));

                payment.setBonPaiement(result.getString(10));
                payment.setCompteurKilométrage(result.getString(11));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }

    public boolean checkIfDriverExists(int id) {
        boolean bool = false;
        try {
            sql = "select * from users where employee_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkIfVehicleImmatriculationExists(String immatriculation) {
        boolean bool = false;
        try {
            sql = "select * from vehicle where vh_immatriculaton = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, immatriculation);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkIfVehicleCodeExists(String code) {
        boolean bool = false;
        try {
            sql = "select * from vehicle where vh_code = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkIfVehcileIsAffected(String id) {
        boolean bool = false;
        try {
            sql = "select * from affectation where af_vh_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public boolean checkIfUserHasAffectation(String id) {
        boolean bool = false;
        try {
            sql = "select * from affectation where af_usr_id = ?";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public void initialiseAssignmentsList(List<Assignment> assignments) {
        for (int i = 0; i < assignments.size(); i++) {
            ArrayList<HashMap<String, String>> kilometrages = getGlobalKmValues(assignments.get(i).getId());           
             if (kilometrages.size() > 0) {
                assignments.get(i).setFirstKm(kilometrages.get(0).get("kilometrage"));
                assignments.get(i).setLastKm(kilometrages.get(kilometrages.size()-1).get("kilometrage"));
            } else {
                assignments.get(i).setFirstKm("--");
                assignments.get(i).setLastKm("--");
            }

            List<Payment> paList = getAllPayment(assignments.get(i).getId());         
            if (paList.size() > 2) {
                Double consoMoy = (Double.valueOf(paList.get(1).getCost()) / Double.valueOf(paList.get(1).getUnitPrice())) / (Double.valueOf(paList.get(0).getDistance()) - Double.valueOf(paList.get(1).getDistance()));
                consoMoy = consoMoy * 100 * Math.pow(10, 4);
                consoMoy = Math.floor(consoMoy);
                consoMoy = consoMoy / Math.pow(10, 4);
                assignments.get(i).setAverageConsumption(String.valueOf(consoMoy));
                updateVehicleAverageCons(assignments.get(i).getVehicle().getId(), consoMoy);
            } else {
                assignments.get(i).setAverageConsumption("--");
            }
            dao2 = new Dao(DatabaseConnection.getConnection2());
            Employee employee = dao2.getEmployeeById(Integer.valueOf(assignments.get(i).getUser().getEmployeeId()));
            assignments.get(i).getUser().setEmployee(employee);
        }
    }
}
