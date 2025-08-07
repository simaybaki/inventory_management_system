import java.util.ArrayList;

public interface Building {
	public ArrayList<Item> getItemList();
	public ArrayList<Employee> getEmployeeList();
	public void addItem(Item item);
	public void addEmployee(Employee employee);
	public String getName();
}
