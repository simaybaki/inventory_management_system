import java.util.ArrayList;

public class Market implements Building {
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private static int id = 1;
	private String name;

	public Market() {
		name = "Market " + id;
		id++;
	}

	@Override
	public ArrayList<Item> getItemList() {
		return itemList;
	}

	@Override
	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public void addEmployee(Employee employee) {
		employeeList.add(employee);
	}

	public String getName() {
		return name;
	}
}
