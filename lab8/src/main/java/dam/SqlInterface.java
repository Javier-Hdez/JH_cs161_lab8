package main.java.dam;

//imports
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

class SqlInterface{
    protected Connection getConnection(){
        return Database.connection();
    }

    protected Statement getStatement(){
        try{
            return getConnection().createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    //utility method to return java date
    protected java.util.Date getResultSetDate(ResultSet rs, String colName) throws SQLException {
        //capture sql date
        java.sql.Date sqlDate = rs.getDate(colName);

        //if it was null, return a null object
        if(rs.wasNull())
            return null;

        //otherwise create the new date
        java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
        
        //return a java Date object
        return javaDate;
    }
}