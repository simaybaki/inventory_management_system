import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Information {
	private static Information information = new Information();
	private static ArrayList<Building> buildingList;
	private static ArrayList<Item> itemList = new ArrayList<Item>();
	private static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static int id = 0;
	//getinstance.getbuildinglist.getname == market1
	private Information() {
       
		buildingList = new ArrayList<Building>();
		addBuildings();

		
	}
	
	public static Information getInstance() {
		return information;
	}
	


	public static ArrayList<Building> getBuildingList() {
		return buildingList;
	}

    public ArrayList<Item> getItemList(String buildingName){
        for (int i = 0; i < buildingList.size(); i++) {
            Building b = buildingList.get(i);
            if (b.getName().equals(buildingName)) {
                return b.getItemList();
            }
        }
        return null;
    }

	public ArrayList<Employee> getEmployeeList(String buildingName){
        for (int i = 0; i < buildingList.size(); i++) {
            Building b = buildingList.get(i);
            if (b.getName().equals(buildingName)) {
                return b.getEmployeeList();
            }
        }
        return null;
    }
	
	public static void addItem(Item item) {
		itemList.add(item);
	}
	
	public static void addEmployee(Employee employee) {
		employeeList.add(employee);
	}
	
	public static Building getBuilding(String buildingName) {
		for (int i = 0; i < Information.getBuildingList().size(); i++) {
			if (Information.getBuildingList().get(i).getName().equals(buildingName)) {
				return Information.getBuildingList().get(i);
			}
		}
		return null;
	}
	
	public Employee getEmployee(String employeeName) {
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getName().equals(employeeName)) {
				return employeeList.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Employee> getEmployees(int id, String name, String surname) {
		boolean nameBool = false;
		boolean surnameBool = false;
		boolean idBool = false;
		
		if (!name.equals("")) {
			nameBool = true;
		}
		
		if (!surname.equals("")) {
			surnameBool = true;
		}
		
		if (id != 0) {
			idBool = true;
		}
		
		ArrayList<Employee> empList = new ArrayList<Employee>();
		
		for (int i = 0; i < buildingList.size(); i++) {
			ArrayList<Employee> list = buildingList.get(i).getEmployeeList();
			
			for (int j = 0; j < list.size(); j++) {
				if (nameBool && surnameBool && idBool) {
					if (name.equals(list.get(j).getName()) && surname.equals(list.get(j).getSurname()) && id == list.get(j).getId()) {
						empList.add(list.get(j));
					}
				} else if (nameBool && surnameBool) {
					if (name.equals(list.get(j).getName()) && surname.equals(list.get(j).getSurname())) {
						empList.add(list.get(j));
					}
				} else if (nameBool && idBool) {
					if (name.equals(list.get(j).getName()) && id == list.get(j).getId()) {
						empList.add(list.get(j));
					}
				} else if (surnameBool && idBool) {
					if (surname.equals(list.get(j).getSurname()) && id == list.get(j).getId()) {
						empList.add(list.get(j));
					}
				} else if (nameBool) {
					if (name.equals(list.get(j).getName())) {
						empList.add(list.get(j));
					}
				} else if (surnameBool) {
					if (surname.equals(list.get(j).getSurname())) {
						empList.add(list.get(j));
					}
				} else if (idBool) {
					if (id == list.get(j).getId()) {
						empList.add(list.get(j));
					}
				}
			}
		}
		
		return empList;
	}
	
	public Item getItem(String itemName) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getName().equals(itemName)) {
				return itemList.get(i);
			}
		}
		return null;
	}

	private void addBuildings() {
		BuildingFactory bf = new BuildingFactory();
		
		for (int i = 0; i < 3; i++) {
			buildingList.add(bf.createBuilding("market"));
			buildingList.add(bf.createBuilding("warehouse"));
		}
	}
	
	
	
	private void addItems() {
		
	}
	
	private void addItemsToBuildings() {
		for (int i = 0; i < buildingList.size(); i++) {
			for (int j = i * itemList.size() / 6; j < itemList.size() / 6 * (i + 1); j++) {
				buildingList.get(i).addItem(itemList.get(j));
			}
		}
	}
	
	public void addEmployeeToBuilding(String branch, Employee e) {
		
		for (int i = 0; i < buildingList.size(); i++) {
			int size = 0;
			Building b = buildingList.get(i);
			
			if (b.getEmployeeList().size() != 0) {
				size = b.getEmployeeList().size() - 1;
			}

			if (b.getName().equals(branch)) {
				if (size != 0) {
					Information.id ++;
					int id = Information.id;
					e.setId(id);
				} else {
					e.setId(1);
				}
				
				buildingList.get(i).addEmployee(e);
				addEmployee(e);
				
			}
		}
		
	}
	
	public boolean deleteEmployee(String name, String worksAt, int id) throws SQLException {
        ArrayList<Employee> empArr = Information.getInstance().getEmployeeList(worksAt);
        boolean found = false;

        for (int i = 0; i < empArr.size(); i++) {
            Employee e = empArr.get(i);

            if (e.getName().equals(name) && e.getId() == id) {
                found = true;
                empArr.remove(i);
                DatabaseConnection.deleteValue(String.format("delete from \"employee\" where name = '%s' and id = %d", name,id));
                Information.id --;
                break;
            }
        }
       
        return found;
    }
	
	
	
	
	public String[] checkCreditentials(String username, String password) {
        String matched = "false";
        String isManager = "false";
        String worksAt = "";

        for (int i = 0; i < buildingList.size(); i++) {
            ArrayList<Employee> employeeList = buildingList.get(i).getEmployeeList();

            for (int j = 0; j < employeeList.size(); j++) {
                if (employeeList.get(j).getUsername().equals(username) && employeeList.get(j).getPassword().equals(password)) {

                    if (employeeList.get(j).getType().equalsIgnoreCase("manager")) {
                        isManager = "true";
                    }
                    worksAt = employeeList.get(j).getWorksAt();
                    matched = "true";
                    break;
                }
            }
        }
        String[] info = {matched, isManager, worksAt};
        return info;
    }
	
	
	
	public boolean deleteItem(String itemBrand, String itemName, Building b) throws SQLException {
		String tableName = b.getName();
		tableName = tableName.replace(" ", "");
		tableName = tableName.toLowerCase();
		
        ArrayList<Item> itemList = b.getItemList();
        boolean found = false;

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            if (item.getName().equals(itemName) && item.getBrand().equals(itemBrand)) {
                found = true;
                itemList.remove(i);
                DatabaseConnection.deleteValue(String.format("delete from \"%s\" where name = '%s' and brand = '%s'", tableName,itemName,itemBrand));
                break;
            }
        }
        return found;
    }
	
	
	
	
	
	
	
}
