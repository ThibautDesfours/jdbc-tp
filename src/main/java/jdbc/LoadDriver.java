package jdbc;

import java.sql.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.ResultSet;

public class LoadDriver {
    public static void main(String[] args) {
    	Connection conn = null;
    	PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        	selectPersonne(conn);
        	insertPersonne(conn);
        	updatePersonne(conn);
        	deletePersonne(conn);
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { }

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }

                stmt = null;
           }
        }
    }
    
    public static void selectPersonne(Connection conn) {
    	try {
	    	PreparedStatement stmt = conn.prepareStatement("SELECT concat(prenom,' ',nom) as nomcomplet FROM person");
	    	ResultSet rs = stmt.executeQuery();
	        if(rs.next())
	        	System.out.println(rs.getString("nomcomplet"));
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static void insertPersonne(Connection conn) {
    	try {
	    	String sql = "INSERT INTO person (id,nom,prenom,dateN) VALUES (?, ?, ?, ?)";
	    	PreparedStatement stmt = conn.prepareStatement("SELECT max(id)+1 as newid FROM person");
	    	ResultSet rs = stmt.executeQuery();
	    	String newid = "";
	        if(rs.next())
	        	newid = rs.getString("newid");
	        
	    	PreparedStatement statement = conn.prepareStatement(sql);
	    	statement.setString(1, newid);
	    	statement.setString(2, "nom"+newid);
	    	statement.setString(3, "prenom"+newid);
	    	statement.setString(4, "20200101");
	    	 
	    	int rowsInserted = statement.executeUpdate();
	    	if (rowsInserted > 0) {
	    	    System.out.println("A new user was inserted successfully!");
	    	}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static void updatePersonne(Connection conn) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT max(id) as maxid FROM person");
	    	ResultSet rs = stmt.executeQuery();
	    	String maxid = "";
	        if(rs.next())
	        	maxid = rs.getString("maxid");
	    	String sql = "UPDATE person set nom = ? where id = ?";
	        
	    	PreparedStatement statement = conn.prepareStatement(sql);
	    	statement.setString(1, "nombidon");
	    	statement.setString(2, maxid);
	    	 
	    	int rowsInserted = statement.executeUpdate();
	    	if (rowsInserted > 0) {
	    	    System.out.println("A new user was updated successfully!");
	    	}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public static void deletePersonne(Connection conn) {
    	try {
    		PreparedStatement stmt = conn.prepareStatement("SELECT min(id) as minid FROM person");
	    	ResultSet rs = stmt.executeQuery();
	    	String minid = "";
	        if(rs.next())
	        	minid = rs.getString("minid");
	    	String sql = "DELETE from person where id = ?";
	        
	    	PreparedStatement statement = conn.prepareStatement(sql);
	    	statement.setString(1, minid);
	    	 
	    	int rowsInserted = statement.executeUpdate();
	    	if (rowsInserted > 0) {
	    	    System.out.println("A new user was deleted successfully!");
	    	}
    	} catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
}