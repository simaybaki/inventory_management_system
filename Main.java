import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
static int count = 0;

	public static void main(String[] args) throws FileNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DatabaseConnection.connect();
					DatabaseConnection.getEmployeeFromDatabase();
					DatabaseConnection.getItemFromDatabase();
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
