package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleProgramforExecuteUpdateTest {

	public static void main(String[] args) throws SQLException {
		Connection connection=null;
		Driver driver = new Driver();
     DriverManager.registerDriver(driver);
   try {
	   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
     Statement statement = connection.createStatement();
     int result = statement.executeUpdate("insert into student values(102,'rohan',870998840);");
       if(result==1) {
    	   System.out.println("the table is updated");
       }
       else {
    	   System.out.println("the table has not been created");
	}
   }
   catch(SQLException s) {
	   
   }
   finally {
	   connection.close();
   }
	}
}
