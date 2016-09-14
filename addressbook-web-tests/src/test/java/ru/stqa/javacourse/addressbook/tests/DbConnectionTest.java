package ru.stqa.javacourse.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.javacourse.addressbook.model.GroupData;
import ru.stqa.javacourse.addressbook.model.Groups;

import java.io.IOException;
import java.sql.*;

@Test
public class DbConnectionTest {
	//final private Properties properties;
	//private String dbConn;

	/*public DbConnectionTest(String dbConn) {
		this.dbConn = dbConn;
		properties = new Properties();
	}*/

	public void testDbConnection() throws IOException {

		//String target2 = System.getProperty("target2", "local");
		//properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target2))));
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");//conn = DriverManager.getConnection("web.dbAddress");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
			Groups groups = new Groups();
			while (rs.next()){
				groups.add(new GroupData()
						.withId(rs.getInt("group_id"))
						.withName(rs.getString("group_name"))
						.withHeader(rs.getString("group_header"))
						.withFooter(rs.getString("group_footer")));
			}
			rs.close();
			st.close();
			conn.close();
			
			System.out.println(groups);

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
