package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.SocksProxySocketFactory;

public class SampleProgramForExecuteQuery {

	public static void main(String[] args) throws SQLException {
     //1.registering the database
		Driver driver=new Driver();
     DriverManager.registerDriver(driver);
     
     //2.for establishing connection
     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
     
     //3.create SQL statement
     Statement statement = connection.createStatement();
     
     //4.execute SQL query
     ResultSet result = statement.executeQuery("select * from student;");
     
     while(result.next()) {
    	 System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
     }
     
    connection.close();
     
	}

}
