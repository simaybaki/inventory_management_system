import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class ExecutiveMenu extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField surnameField;
	private JTextField nameField;
	private JTable firstTabTable;
	private JTable secondTabTable;
	private String tableContentName = "";
	private String tableContentWorksAt = "";
	private int tableContentId = 0;
	private  boolean fireFlag;
	private int posX;
	private int posY;

	/**
	 * Create the frame.
	 */
	
	
	
	public ExecutiveMenu(Login login) {
		setResizable(false);
		setTitle("Department Store Inventory Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 661);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		JPanel contentPane_2 = new JPanel();
		contentPane_2.setLayout(null);
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_2.setBackground(new Color(215, 230, 232));
		contentPane_2.setBounds(0, 0, 1147, 32);
		contentPane.add(contentPane_2);
		contentPane_2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                 posX = e.getX();
                 posY = e.getY();
            }
        });

		contentPane_2.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = getLocation().x + e.getX() - posX;
                int newY = getLocation().y + e.getY() - posY;
                setLocation(newX, newY);
            }
        });
		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.setForeground(new Color(255, 255, 255));
		logOutBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		logOutBtn.setBackground(new Color(223, 94, 60));
		logOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login.setVisible(true);
				disposeObject();
			}
		});
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disposeObject();
				login.setVisible(true);
			}
		});
		logOutBtn.setBounds(544, 612, 94, 31);
		contentPane.add(logOutBtn);
		
		JTextPane txtpnCurrenlyLoggedIn = new JTextPane();
		txtpnCurrenlyLoggedIn.setForeground(new Color(0, 0, 0));
		txtpnCurrenlyLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnCurrenlyLoggedIn.setEditable(false);
		txtpnCurrenlyLoggedIn.setBackground(new Color(255, 255, 255));
		txtpnCurrenlyLoggedIn.setText("Currenly Logged In User: " + login.getUsername());
		txtpnCurrenlyLoggedIn.setBounds(71, 623, 242, 20);
		contentPane.add(txtpnCurrenlyLoggedIn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(215, 230, 232));
		tabbedPane.setBounds(50, 55, 1053, 546);
		contentPane.add(tabbedPane);
		
		JPanel buildingActionsPanel = new JPanel();
		buildingActionsPanel.setBorder(null);
		buildingActionsPanel.setForeground(new Color(255, 255, 255));
		buildingActionsPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("                                                                  Building Actions                                                                        ", null, buildingActionsPanel, null);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setBackgroundAt(0, new Color(215, 230, 232));
		buildingActionsPanel.setLayout(null);
		
		
		
		JList BuildingList = new JList();
		BuildingList.setBackground(new Color(255, 255, 255));
		BuildingList.setForeground(new Color(37, 81, 68));
		BuildingList.setFont(new Font("Tahoma", Font.BOLD, 14));
		BuildingList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Market 1", "Market 2", "Market 3", "Warehouse 1", "Warehouse 2", "Warehouse 3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		BuildingList.setBounds(67, 146, 117, 114);
		buildingActionsPanel.add(BuildingList);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 38, 780, 451);
		scrollPane.setVisible(false);
		buildingActionsPanel.add(scrollPane);
		
		firstTabTable = new JTable();
		firstTabTable.setRowSelectionAllowed(true);
		scrollPane.setViewportView(firstTabTable);
		firstTabTable.setVisible(false);
		
		
		JTextPane txtpnBuildingName = new JTextPane();
		txtpnBuildingName.setEditable(false);
		txtpnBuildingName.setBackground(SystemColor.menu);
		txtpnBuildingName.setBounds(528, 7, 218, 20);
		txtpnBuildingName.setVisible(false);
		buildingActionsPanel.add(txtpnBuildingName);

		JButton getEmployeeListBtn = new JButton("Get Employee List");
		getEmployeeListBtn.setForeground(new Color(255, 255, 255));
		getEmployeeListBtn.setBackground(new Color(223, 94, 60));
		getEmployeeListBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		getEmployeeListBtn.setBounds(48, 297, 156, 35);
		buildingActionsPanel.add(getEmployeeListBtn);
		
		JButton getItemListBtn = new JButton("Get Item List");
		getItemListBtn.setForeground(new Color(255, 255, 255));
		getItemListBtn.setBackground(new Color(37, 81, 68));
		getItemListBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		getItemListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buildingName = (String) BuildingList.getSelectedValue();
				if (buildingName == null) {
					JOptionPane.showMessageDialog(new JFrame(),"You must make a building selection from the list first.");
				} else {
					ArrayList<Item> itemList = Information.getInstance().getItemList(buildingName);
					System.out.println();
					
					if (itemList.size() == 0) {
						JOptionPane.showMessageDialog(new JFrame(),"There are no items in the selected building.");	
					} else {
						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = {"Name", "Item Type", "Brand", "Production Date", "Expiry Date","Stock"};
						model.setColumnIdentifiers(columns);
						
						for (int i = 0; i < itemList.size(); i++) {
							String name = itemList.get(i).getName();
							String itemType = itemList.get(i).getItemType();
							String brand = itemList.get(i).getBrand();
							String productionDate = itemList.get(i).getProductionDate().toString();
							String expirationDate = itemList.get(i).getExpirationDate().toString();
							int stock = itemList.get(i).getStock();
							Object[] data = {name, itemType, brand, productionDate, expirationDate,stock};
							
							model.addRow(data);
						}
						
						firstTabTable.setModel(model);

						//String query = "select * from \"employee\"";
						//DefaultTableModel temp =  DatabaseConnection.getTable(query);
						//table_1.setModel(temp);
						firstTabTable.setEnabled(false);
						firstTabTable.setVisible(true);
						scrollPane.setVisible(true);
						txtpnBuildingName.setText(buildingName + " - Items");
						txtpnBuildingName.setVisible(true);
					}
				}
			}
		});
		getItemListBtn.setBounds(48, 365, 156, 35);
		buildingActionsPanel.add(getItemListBtn);
		
		JTextPane infoPn = new JTextPane();
		infoPn.setFont(new Font("Tahoma", Font.BOLD, 14));
		infoPn.setText("  Select a building from the list below.");
		infoPn.setEditable(false);
		infoPn.setBackground(new Color(255, 255, 255));
		infoPn.setBounds(58, 57, 156, 55);
		buildingActionsPanel.add(infoPn);
		
		getEmployeeListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buildingName = (String) BuildingList.getSelectedValue();
				
				if (buildingName == null) {
					if (!fireFlag ) {
						JOptionPane.showMessageDialog(new JFrame(),"You need to select a building from the list first.");
					}
					else {
						secondTabTable.setModel(new DefaultTableModel());
					}
				} else {
					ArrayList<Employee> empList = Information.getInstance().getEmployeeList(buildingName);
					
					if (empList.size() == 0) {
						JOptionPane.showMessageDialog(new JFrame(),"There are no employees in the selected building.");
					} else {
						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = {"Name", "Surname", "Salary", "Start Date","Type", "Phone", "Email", "ID", "Gender", "Age", "EmpId", "Works At"};
						model.setColumnIdentifiers(columns);
						
						for (int i = 0; i < empList.size(); i++) {
							String type = empList.get(i).getType();
							String name = empList.get(i).getName();
							String surname = empList.get(i).getSurname();
							int salary = empList.get(i).getSalary();
							String startDate = empList.get(i).getStartDate();
							String phone = empList.get(i).getPhone();
							String email = empList.get(i).getEmail();
							String identification_number = empList.get(i).getIdentification_number();
							String gender = empList.get(i).getGender();
							int age = empList.get(i).getAge();
							String worksAt = empList.get(i).getWorksAt();
							int empId = empList.get(i).getId();
							Object[] data = {name, surname, salary, startDate,type, phone, email, identification_number, gender, age, empId, worksAt};
							
							model.addRow(data);
						}
						
						firstTabTable.setModel(model);

						//String query = "select * from \"employee\"";
						//DefaultTableModel temp =  DatabaseConnection.getTable(query);
						//table_1.setModel(temp);
						firstTabTable.setEnabled(false);
						firstTabTable.setVisible(true);
						scrollPane.setVisible(true);
						txtpnBuildingName.setText(buildingName + " - Employees");
						txtpnBuildingName.setVisible(true);
					}
				}
			}
		});
		
		JPanel employeectionsPanel = new JPanel();
		employeectionsPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("                                                                  Employee Actions                                                                  ", null, employeectionsPanel, null);
		tabbedPane.setBackgroundAt(1, new Color(215, 230, 232));
		employeectionsPanel.setLayout(null);
		
		JButton hireEmployeeBtn = new JButton("Hire Employee");
		hireEmployeeBtn.setForeground(new Color(37, 81, 68));
		hireEmployeeBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		hireEmployeeBtn.setBackground(new Color(215, 230, 232));
		hireEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CreateUser createUser = new CreateUser(login, getObject(), null, null);
					createUser.setVisible(true);
					createUser.getReturnToLoginBtn().setVisible(false);
					createUser.getReturnToMenuBtn().setVisible(true);
					setFocusable(false);
					setEnabled(false);			
			}
		});
		hireEmployeeBtn.setBounds(628, 430, 128, 44);
		employeectionsPanel.add(hireEmployeeBtn);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idField.setBounds(147, 22, 153, 25);
		employeectionsPanel.add(idField);
		idField.setColumns(10);
		
		JTextPane txtpnEnterEmployeeId = new JTextPane();
		txtpnEnterEmployeeId.setForeground(new Color(37, 81, 68));
		txtpnEnterEmployeeId.setEditable(false);
		txtpnEnterEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnEnterEmployeeId.setBackground(new Color(255, 255, 255));
		txtpnEnterEmployeeId.setText("Id:");
		txtpnEnterEmployeeId.setBounds(111, 22, 26, 25);
		employeectionsPanel.add(txtpnEnterEmployeeId);		
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		surnameField.setColumns(10);
		surnameField.setBounds(628, 22, 153, 25);
		employeectionsPanel.add(surnameField);
		
		JScrollPane employeeInfoScrollPane = new JScrollPane();
		employeeInfoScrollPane.setBounds(111, 59, 833, 350);
		employeectionsPanel.add(employeeInfoScrollPane);
		
		secondTabTable = new JTable();
		employeeInfoScrollPane.setViewportView(secondTabTable);
		secondTabTable.setVisible(false);
		employeeInfoScrollPane.setVisible(false);
		
		JButton showEmployeeInfoBtn = new JButton("Show employee info\r\n");
		showEmployeeInfoBtn.setForeground(new Color(255, 255, 255));
		showEmployeeInfoBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		showEmployeeInfoBtn.setBackground(new Color(223, 94, 60));
		showEmployeeInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!idField.getText().isBlank() || !nameField.getText().isBlank() || !surnameField.getText().isBlank()) {
					int id = 0;
					String name = "";
					String surname = "";
					
					if (!idField.getText().isBlank()) {
						try {
							id = Integer.parseInt(idField.getText());
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(new JFrame(),"Id field must be filled with a number.");
							return;
						}
					}
					
					if (!nameField.getText().isBlank()) {
						name = nameField.getText();
					}
					
					if (!surnameField.getText().isBlank()) {
						surname = surnameField.getText();
					}
					
					ArrayList<Building> bList = Information.getInstance().getBuildingList();
					DefaultTableModel model = new DefaultTableModel();
					Object[] columns = {"Name", "Surname", "Salary", "Start Date", "Phone", "Email", "ID", "Gender", "Age", "EmpId", "Works At"};
					model.setColumnIdentifiers(columns);
					boolean atLeastOneMatch = false;
					
					for (int i = 0; i < bList.size(); i++) {
						ArrayList<Employee> empList = bList.get(i).getEmployeeList();
						
						for (int j = 0; j < empList.size(); j++) {
							Employee emp = empList.get(j);
							boolean match = false;
							
							if (!name.equals("") && !surname.equals("") && id != 0) {
								if (emp.getName().equalsIgnoreCase(name) && emp.getSurname().equalsIgnoreCase(surname) && emp.getId() == id) {
									match = true;
								}
							} else if (!name.equals("") && !surname.equals("")) {
								if (emp.getName().equalsIgnoreCase(name) && emp.getSurname().equalsIgnoreCase(surname)) {
									match = true;
								}
							} else if (!name.equals("") && id != 0) {
								if (emp.getName().equalsIgnoreCase(name) && emp.getId() == id) {
									match = true;
								}
							} else if (!surname.equals("") && id != 0) {
								if (emp.getSurname().equalsIgnoreCase(surname) && emp.getId() == id) {
									match = true;
								}
							} else if (!name.equals("")) {
								if (emp.getName().equalsIgnoreCase(name)) {
									match = true;
								}
							} else if (!surname.equals("")) {
								if (emp.getSurname().equalsIgnoreCase(surname)) {
									match = true;
								}
							} else if (id != 0) {
								if (emp.getId() == id) {
									match = true;
								}
							}
							
							if (match) {
								atLeastOneMatch = true;
								String empName = empList.get(j).getName();
								String empSurname = empList.get(j).getSurname();
								int salary = empList.get(j).getSalary();
								String startDate = empList.get(j).getStartDate();
								String phone = empList.get(j).getPhone();
								String email = empList.get(j).getEmail();
								String identification_number = empList.get(j).getIdentification_number();
								String gender = empList.get(j).getGender();
								int age = empList.get(j).getAge();
			
								int empId = empList.get(j).getId();
								
								String worksAt = empList.get(j).getWorksAt();
								Object[] data = {empName, empSurname, salary, startDate, phone, email, identification_number, gender, age, empId, worksAt};
								
								model.addRow(data);
							}
						}
					}
					
					if (atLeastOneMatch) {						
						secondTabTable.setModel(model);

						//String query = "select * from \"employee\"";
						//DefaultTableModel temp =  DatabaseConnection.getTable(query);
						secondTabTable.setEnabled(true);
						secondTabTable.setVisible(true);
						employeeInfoScrollPane.setVisible(true);
					} else {
						if (!fireFlag ) {
							JOptionPane.showMessageDialog(new JFrame(),"No employee according to the given information was found.");
						}
						else {
							secondTabTable.setModel(new DefaultTableModel());
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),"At least one of the fields must be filled.");
				}
			}
		});
		showEmployeeInfoBtn.setBounds(791, 22, 153, 27);
		employeectionsPanel.add(showEmployeeInfoBtn);
		
		JButton fireEmployeeBtn = new JButton("Fire Employee");
		fireEmployeeBtn.setForeground(new Color(255, 255, 255));
		fireEmployeeBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		fireEmployeeBtn.setBackground(new Color(37, 81, 68));
		fireEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tableContentName.equals("") && !tableContentWorksAt.equals("") && tableContentId != 0) {
					int answer = JOptionPane.showConfirmDialog(new JFrame(), "Do you really want to fire this employee?", "Fire Employee", 0);
					boolean found = false;
					if (answer == 0 && !tableContentName.equals("") && !tableContentWorksAt.equals("") && tableContentId != 0) {
						 try {
							found = Information.getInstance().deleteEmployee(tableContentName, tableContentWorksAt, tableContentId);
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						 
						if (found) {
							JOptionPane.showMessageDialog(new JFrame(), "Selected employee is fired.");
							
							tableContentName = "";
							tableContentWorksAt = "";
							tableContentId = 0;
							fireFlag = true;
							showEmployeeInfoBtn.doClick();
							fireFlag = false;
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You need to make a selection from the table first.");
				}
			}
		});
		
		fireEmployeeBtn.setBounds(292, 430, 128, 44);
		employeectionsPanel.add(fireEmployeeBtn);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setForeground(new Color(37, 81, 68));
		txtpnName.setBounds(322, 22, 40, 25);
		employeectionsPanel.add(txtpnName);
		txtpnName.setEditable(false);
		txtpnName.setText("Name:");
		txtpnName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnName.setBackground(new Color(255, 255, 255));
		
		JTextPane txtpnSurname = new JTextPane();
		txtpnSurname.setForeground(new Color(37, 81, 68));
		txtpnSurname.setBounds(556, 22, 62, 25);
		employeectionsPanel.add(txtpnSurname);
		txtpnSurname.setEditable(false);
		txtpnSurname.setText("Surname:");
		txtpnSurname.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnSurname.setBackground(new Color(255, 255, 255));
		
		nameField = new JTextField();
		nameField.setBounds(372, 22, 153, 25);
		employeectionsPanel.add(nameField);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameField.setColumns(10);
		
		
		
		ListSelectionModel model = secondTabTable.getSelectionModel();

		model.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            if (!model.isSelectionEmpty()) {
	            	int index = model.getMinSelectionIndex();
	            	tableContentName = (String) secondTabTable.getValueAt(index, 0);
	            	tableContentWorksAt = (String) secondTabTable.getValueAt(index, 10);
	            	tableContentId = (int) secondTabTable.getValueAt(index, 9);
	            }
	        }
	    });
	}
	
	private void disposeObject() {
		this.dispose();
	}
	
	private ExecutiveMenu getObject() {
		return this;
	}
	
	private String formatDate(String date) {
		String[] dates = date.split(" ");
		String month = "";
		String day = dates[2];
		String year = dates[5];
		
		if (dates[1].equals("Jan")) {
			month = "01";
		} else if (dates[1].equals("Feb")) {
			month = "02";
		} else if (dates[1].equals("Mar")) {
			month = "03";
		} else if (dates[1].equals("Apr")) {
			month = "04";
		} else if (dates[1].equals("May")) {
			month = "05";
		} else if (dates[1].equals("Jun")) {
			month = "06";
		} else if (dates[1].equals("Jul")) {
			month = "07";
		} else if (dates[1].equals("Aug")) {
			month = "08";
		} else if (dates[1].equals("Sep")) {
			month = "09";
		} else if (dates[1].equals("Oct")) {
			month = "10";
		} else if (dates[1].equals("Nov")) {
			month = "11";
		} else {
			month = "12";
		}
		
		String formattedDate = day + "/" +month +"/"+ year;
		return formattedDate;
	}
}
