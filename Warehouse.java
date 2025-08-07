import java.util.ArrayList;

public class Warehouse implements Building {
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private static int id = 1;
	private String name;
	
	public Warehouse() {
		name = "Warehouse " + id;
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
