package main.java;

//imports
import main.java.dam.Database;
import java.util.HashMap;
import main.java.dam.DamEmployee;
import main.java.classes.Employee;
import java.util.ArrayList;
class Main{
    public static void main(String[] args){
        //Store the connection parameters
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("host", "192.154.227.17");
        params.put("instance", "CGCC");
        params.put("port", "1444");
        params.put("dbName", "AdventureWorks");
        params.put("integrated", "false");
        params.put("dbUser", "jdoe");
        params.put("dbPassword", "Password1234!");

        //init the database conenction
        Database.initDatabase(params);
        System.out.println(Database.getConnectionURL());
        ArrayList<Employee> employees = new DamEmployee().getEmployees(null);
    
        for(Employee e : employees){
            System.out.println(e.getFirstName() + " " + e.getLastName() + " " + e.getHireDate().toString());
        }
    
    
    }
}