package main.java.dam;
import java.util.ArrayList;
import main.java.classes.Employee;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DamEmployee extends SqlInterface{

    public ArrayList<Employee> getEmployees(String id){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" P.BusinessEntityID, P.FirstName, P.LastName");
        sb.append(" ,E.HireDate, E.JobTitle");
        sb.append(" FROM");
        sb.append(" HumanResources.Employee AS E");
        sb.append(" JOIN Person.Person AS P");
        sb.append(" ON P.BusinessEntityID = E.BusinessEntityID");
        if(id != null){
            sb.append(" WHERE");
            sb.append(" E.BusinessEntityID=" + id);

        }

        try(Connection con = getConnection(); Statement stmt = getStatement()){
            ResultSet rs = stmt.executeQuery(sb.toString());

            while(rs.next()){
                Employee e = new Employee(
                    rs.getInt("BusinessEntityID"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    getResultSetDate(rs, "HireDate"),
                    rs.getString("JobTitle")
                );
                
                employees.add(e);


            }

            return employees;



        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }


    }


    






}