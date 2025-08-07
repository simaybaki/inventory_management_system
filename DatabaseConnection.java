import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnection {
	static String url = "database-url";
	static Connection connection = null;
	static Statement st = null;

	public static void connect() {
		try {
			connection = DriverManager.getConnection(url, "sbadmin", "admin123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getEmployeeFromDatabase() {
		String query = "select * from \"employee\"";
		int id;
		String name;
		String surname;
		int age;
		String gender;
		String phone;
		String identification_number;
		int salary;
		String email;
		String start_date;
		String type;
		String branch;
		String username;
		String password;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				id = rs.getInt("id");
				name = rs.getString("name");
				surname = rs.getString("surname");
				age = Integer.parseInt(rs.getString("age"));
				gender = rs.getString("gender");
				phone = rs.getString("phone");
				identification_number = rs.getString("identification_number");
				salary = Integer.parseInt(rs.getString("salary"));
				email = rs.getString("email");
				start_date = rs.getString("start_date");
				type = rs.getString("type");
				branch = rs.getString("branch");
				branch = branch.substring(0,branch.length() -1) + " " + branch.substring(branch.length() -1, branch.length());
				username = rs.getString("username");
				password = rs.getString("password");

				Information.id++;
				EmployeeFactory employeeFactory = new EmployeeFactory();
				Employee employee = employeeFactory.createEmployee(type, name, surname, salary, start_date, phone, email,
						identification_number, gender, id, age, branch, username, password);

				for (int i = 0; i < Information.getBuildingList().size(); i++) {
					if (Information.getBuildingList().get(i).getName().equals(branch)) {

						Information.getBuildingList().get(i).getEmployeeList().add(employee);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getItemFromDatabase() {
		String name;
		String type;
		String brand;
		String production_date;
		String expiration_date;
		String query = "";
		int stock;
		int count = 1;
		for (int k = 1; k <= 6; k++) {
			String branch;
			if (count == 4) {
				count = 1;
			}
			if (k < 4) {
				branch = "Market " + count;
			} else {
				branch = "Warehouse " + count;
			}
			String tableName = branch;
			tableName = tableName.replace(" ", "");
			
			query = "select * from " + tableName;
			query = query.toLowerCase();
			try {
				st = connection.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					name = rs.getString("name");
					type = rs.getString("type");
					brand = rs.getString("brand");
					production_date = rs.getString("production_date");
					expiration_date = rs.getString("expiration_date");
					stock = rs.getInt("stock");
					Item item = new Item(name, type, brand, production_date, expiration_date, stock, branch);
					for (int i = 0; i < Information.getBuildingList().size(); i++) {
						if (Information.getBuildingList().get(i).getName().equals(branch)) {
							Information.getBuildingList().get(i).getItemList().add(item);

						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			count++;
		}

	}

	static void insertValue(String query) {
		try {
			st = connection.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	static void deleteValue(String query) throws SQLException {
		st = connection.createStatement();
		st.executeUpdate(query);

	}

}
