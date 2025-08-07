import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;

public class ManagerMenu extends JFrame {

	private JPanel contentPane;
	private JTextField product_name;
	private JTextField product_type;
	private JTextField nameField;
	private JTextField surnameField;
	private JTable itemTable;
	private JTable empTable;
	private String tableContentName = "";
	private String tableContentWorksAt = "";

	private String tablecontentitemname = "";
	private String tablecontentitembrand = "";

	private int tableContentId = 0;
	private boolean fireFlag;
	
	private boolean showAllClickedEmp = false;
	private boolean showAllClickedItem = false;
	
	private int posX;
	private int posY;

	/**
	 * Create the frame.
	 */
	
	
	
	
	public ManagerMenu(Login login, Building b) {
		setUndecorated(true);
		setTitle("WAREHOUSE MANAGEMENT SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 592);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(215, 230, 232));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBounds(29, 45, 831, 488);
		contentPane.add(tabbedPane);

		JPanel manageStockPanel = new JPanel();
		manageStockPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("                                  Manage Stock                                  ", null, manageStockPanel, null);
		
		
		JTextPane currentlyLoggedIn = new JTextPane();
		currentlyLoggedIn.setForeground(new Color(37, 81, 68));
        currentlyLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 13));
        currentlyLoggedIn.setText("Currently Logged In User: " + login.getUsername());
        currentlyLoggedIn.setEditable(false);
        currentlyLoggedIn.setBackground(new Color(255, 255, 255));
        currentlyLoggedIn.setBounds(30, 545, 312, 25);
        contentPane.add(currentlyLoggedIn);
setResizable(false);
		
		

		JButton addItemBtn = new JButton("Add Item");
		addItemBtn.setBackground(new Color(215, 230, 232));
		addItemBtn.setForeground(new Color(37, 81, 68));
		addItemBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		addItemBtn.setBounds(30, 133, 131, 36);
		addItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItem addItem = new AddItem(getObject(), b);
				addItem.setVisible(true);
				setFocusable(false);
				setEnabled(false);
			}
		});
		manageStockPanel.setLayout(null);
		manageStockPanel.add(addItemBtn);

		JTextPane txtpnProductName = new JTextPane();
		txtpnProductName.setForeground(new Color(37, 81, 68));
		txtpnProductName.setEditable(false);
		txtpnProductName.setBounds(184, 45, 101, 25);
		txtpnProductName.setText("Product Name");
		txtpnProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductName.setBackground(new Color(255, 255, 255));
		manageStockPanel.add(txtpnProductName);

		product_name = new JTextField();
		product_name.setBounds(295, 45, 153, 25);
		product_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		product_name.setColumns(10);
		manageStockPanel.add(product_name);

		JTextPane txtpnProductType = new JTextPane();
		txtpnProductType.setForeground(new Color(37, 81, 68));
		txtpnProductType.setEditable(false);
		txtpnProductType.setBounds(503, 45, 101, 25);
		txtpnProductType.setText("Product Type");
		txtpnProductType.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductType.setBackground(new Color(255, 255, 255));
		manageStockPanel.add(txtpnProductType);

		product_type = new JTextField();
		product_type.setBounds(627, 45, 153, 25);
		product_type.setFont(new Font("Tahoma", Font.PLAIN, 12));
		product_type.setColumns(10);
		manageStockPanel.add(product_type);

		JScrollPane stockPane = new JScrollPane();

		stockPane.setBounds(184, 94, 596, 304);
		manageStockPanel.add(stockPane);

		itemTable = new JTable();
		stockPane.setViewportView(itemTable);
		itemTable.setVisible(false);
		stockPane.setVisible(false);

		JButton btnListItem = new JButton("List Item");
		btnListItem.setForeground(new Color(37, 81, 68));
		btnListItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListItem.setBackground(new Color(215, 230, 232));
		// itemlar� listeliyorum burada
		btnListItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (product_type.getText().isBlank() && product_name.getText().isBlank() && !fireFlag) {
					JOptionPane.showMessageDialog(new JFrame(), "At least one of the fields must be filled.");					
				} else {
					ArrayList<Item> itemList = b.getItemList();
					if (itemList.size() == 0) {
						if (!fireFlag) {
							JOptionPane.showMessageDialog(new JFrame(), "There are no items in the selected building.");
						} else {
							itemTable.setModel(new DefaultTableModel());
						}
						
					} else {
						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = { "Name", "Item Type", "Brand", "Production Date", "Expiry Date", "Stock" };
						model.setColumnIdentifiers(columns);
						boolean atLeastOneMatch = false;
						
						for (int i = 0; i < itemList.size(); i++) {
							if (!product_type.getText().isBlank() && !product_name.getText().isBlank()) {
								if (itemList.get(i).getName().equals(product_name.getText()) && itemList.get(i).getItemType().equals(product_type.getText())) {
									String name = itemList.get(i).getName();
									String itemType = itemList.get(i).getItemType();
									String brand = itemList.get(i).getBrand();
									String productionDate = itemList.get(i).getProductionDate();
									String expirationDate = itemList.get(i).getExpirationDate();
									int Stock = itemList.get(i).getStock();
									Object[] data = {name, itemType, brand, productionDate, expirationDate, Stock};
									model.addRow(data);
									atLeastOneMatch = true;
								}
							} else {
								if (itemList.get(i).getName().equals(product_name.getText()) || itemList.get(i).getItemType().equals(product_type.getText())) {
									String name = itemList.get(i).getName();
									String itemType = itemList.get(i).getItemType();
									String brand = itemList.get(i).getBrand();
									String productionDate = itemList.get(i).getProductionDate();
									String expirationDate = itemList.get(i).getExpirationDate();
									int Stock = itemList.get(i).getStock();
									Object[] data = {name, itemType, brand, productionDate, expirationDate, Stock};
									model.addRow(data);
									atLeastOneMatch = true;
								}
							}
						}
						if (atLeastOneMatch) {
							itemTable.setModel(model);

							// String query = "select * from \"employee\"";
							 //DefaultTableModel temp = DatabaseConnection.getTable(query);
							 //table_1.setModel(temp);
							itemTable.setEnabled(true);
							itemTable.setVisible(true);
							stockPane.setVisible(true);
							showAllClickedItem = false;
						} else {
							if (!fireFlag) {
								JOptionPane.showMessageDialog(new JFrame(), "No items according to the given information was found.");
							} else {
								itemTable.setModel(new DefaultTableModel());
							}
						}

					}
				}
			}
		});
		btnListItem.setBounds(30, 195, 131, 36);
		manageStockPanel.add(btnListItem);


		
		JButton listAllItemsBtn = new JButton("List All Items");
		listAllItemsBtn.setForeground(new Color(37, 81, 68));
		listAllItemsBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		listAllItemsBtn.setBackground(new Color(215, 230, 232));
		listAllItemsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> itemList = b.getItemList();
				if (itemList.size() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "There are no items in the selected building.");
				} else {
					DefaultTableModel model = new DefaultTableModel();
					Object[] columns = { "Name", "Item Type", "Brand", "Production Date", "Expiry Date", "Stock" };
					model.setColumnIdentifiers(columns);
					
					for (int i = 0; i < itemList.size(); i++) {
						String name = itemList.get(i).getName();
						String itemType = itemList.get(i).getItemType();
						String brand = itemList.get(i).getBrand();
						String productionDate = itemList.get(i).getProductionDate().toString();
						String expirationDate = itemList.get(i).getExpirationDate().toString();
						int Stock = itemList.get(i).getStock();
						Object[] data = {name, itemType, brand, productionDate, expirationDate, Stock};
						model.addRow(data);
					}
					
					itemTable.setModel(model);
					itemTable.setEnabled(true);
					itemTable.setVisible(true);
					stockPane.setVisible(true);
					showAllClickedItem = true;
				}
			}
		});
		listAllItemsBtn.setBounds(30, 314, 131, 36);
		manageStockPanel.add(listAllItemsBtn);

		JPanel manageEmployeePanel = new JPanel();
		manageEmployeePanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("                                    Manage Employee                                    ", null, manageEmployeePanel, null);
		manageEmployeePanel.setLayout(null);

		JTextPane txtpnEmployeeName = new JTextPane();
		txtpnEmployeeName.setForeground(new Color(37, 81, 68));
		txtpnEmployeeName.setEditable(false);
		txtpnEmployeeName.setBounds(20, 56, 74, 25);
		txtpnEmployeeName.setText("Name");
		txtpnEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnEmployeeName.setBackground(new Color(255, 255, 255));
		manageEmployeePanel.add(txtpnEmployeeName);

		nameField = new JTextField();
		nameField.setBounds(97, 56, 153, 25);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameField.setColumns(10);
		manageEmployeePanel.add(nameField);

		JTextPane txtpnSurname = new JTextPane();
		txtpnSurname.setForeground(new Color(37, 81, 68));
		txtpnSurname.setEditable(false);
		txtpnSurname.setBounds(20, 92, 74, 25);
		txtpnSurname.setText("Surname");
		txtpnSurname.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnSurname.setBackground(new Color(255, 255, 255));
		manageEmployeePanel.add(txtpnSurname);

		surnameField = new JTextField();
		surnameField.setBounds(97, 92, 153, 25);
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		surnameField.setColumns(10);
		manageEmployeePanel.add(surnameField);

		JButton btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.setBackground(new Color(215, 230, 232));
		btnAddEmployee.setForeground(new Color(37, 81, 68));
		btnAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser createUser = new CreateUser(login, null, getObject(), b);
				createUser.getReturnToLoginBtn().setVisible(false);
				createUser.getReturnToMenuBtn().setVisible(true);
				createUser.setVisible(true);
				setFocusable(false);
				setEnabled(false);
			}
		});
		btnAddEmployee.setBounds(62, 153, 188, 42);
		manageEmployeePanel.add(btnAddEmployee);
		
		JButton btnListEmployee = new JButton("List Employee");
		btnListEmployee.setBackground(new Color(215, 230, 232));
		btnListEmployee.setForeground(new Color(37, 81, 68));
		btnListEmployee.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListEmployee.setBounds(62, 259, 188, 42);
		manageEmployeePanel.add(btnListEmployee);



		JScrollPane employeePane = new JScrollPane();
		employeePane.setBounds(287, 56, 507, 312);
		manageEmployeePanel.add(employeePane);

		empTable = new JTable();
		employeePane.setViewportView(empTable);
		
		JButton listAllEmployeesBtn = new JButton("List All Employees");
		listAllEmployeesBtn.setBackground(new Color(215, 230, 232));
		listAllEmployeesBtn.setForeground(new Color(37, 81, 68));
		listAllEmployeesBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		listAllEmployeesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Employee> empList = b.getEmployeeList();
				
				if (empList.size() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "There are no employees in the selected building.");
				} else {
					DefaultTableModel model = new DefaultTableModel();
					Object[] columns = { "Name", "Surname", "Salary", "Start Date", "Phone", "Email", "ID", "Gender",
							"Age", "EmpId", "Branch" };
					model.setColumnIdentifiers(columns);

					for (int i = 0; i < empList.size(); i++) {
						String name = empList.get(i).getName();
						String surname = empList.get(i).getSurname();
						int salary = empList.get(i).getSalary();
						String startDate = "abcde";
						String phone = empList.get(i).getPhone();
						String email = empList.get(i).getEmail();
						String identification_number = empList.get(i).getIdentification_number();
						String gender = empList.get(i).getGender();
						int age = empList.get(i).getAge();
						String worksAt = empList.get(i).getWorksAt();
						int empId = empList.get(i).getId();
						Object[] data = { name, surname, salary, startDate, phone, email, identification_number, gender,
								age, empId, worksAt };
						model.addRow(data);
					}

					empTable.setModel(model);

					// String query = "select * from \"employee\"";
					 //DefaultTableModel temp = DatabaseConnection.getTable(query);
					empTable.setEnabled(true);
					empTable.setVisible(true);
					employeePane.setVisible(true);
					empTable.setVisible(true);
					showAllClickedEmp = true;
				}
			}
		});
		listAllEmployeesBtn.setBounds(62, 312, 188, 42);
		manageEmployeePanel.add(listAllEmployeesBtn);
		empTable.setVisible(false);
		employeePane.setVisible(false);

		btnListEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().isBlank() && surnameField.getText().isBlank()) {
					JOptionPane.showMessageDialog(new JFrame(), "At least one of the fields must be filled.");
				} else {
					ArrayList<Employee> empList = b.getEmployeeList();
					if (empList.size() == 0) {
						if (fireFlag) {
							empTable.setModel(new DefaultTableModel());
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "There are no employees in the selected building.");
						}
					} else {
						showAllClickedEmp = false;
						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = { "Name", "Surname", "Salary", "Start Date", "Phone", "Email", "ID", "Gender",
								"Age", "EmpId", "Branch" };
						model.setColumnIdentifiers(columns);

						for (int i = 0; i < empList.size(); i++) {
							if (!nameField.getText().isBlank() && !surnameField.getText().isBlank()) {
								if (empList.get(i).getName().equals(nameField.getText()) && empList.get(i).getSurname().equals(surnameField.getText())) {
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
									Object[] data = { name, surname, salary, startDate, phone, email, identification_number, gender,
											empId, age, worksAt };
									model.addRow(data);
								}
							} else {
								if (empList.get(i).getName().equals(nameField.getText()) || empList.get(i).getSurname().equals(surnameField.getText())) {
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
									Object[] data = { name, surname, salary, startDate, phone, email, identification_number, gender,
											empId, age, worksAt };
									model.addRow(data);
								}
							}
						}

						empTable.setModel(model);

						// String query = "select * from \"employee\"";
						 //DefaultTableModel temp = DatabaseConnection.getTable(query);
						empTable.setEnabled(true);
						empTable.setVisible(true);
						employeePane.setVisible(true);
						empTable.setVisible(true);
					}
				}
			}
		});

		ListSelectionModel modelemp = empTable.getSelectionModel();

		/// selection
		ListSelectionModel modelitem = itemTable.getSelectionModel();

		JButton btnRemoveEmployee = new JButton("Remove Employee");
		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// removc employee
				if (!tableContentName.equals("") && !tableContentWorksAt.equals("") && tableContentId != 0) {
					int answer = JOptionPane.showConfirmDialog(new JFrame(),
							"Do you really want to fire this employee?", "Fire Employee", 0);

					if (answer == 0 && !tableContentName.equals("") && !tableContentWorksAt.equals("")
							&& tableContentId != 0) {

						try {
							Information.getInstance().deleteEmployee(tableContentName, tableContentWorksAt, tableContentId);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(new JFrame(), "Selected employee is fired.");

						tableContentName = "";
						tableContentWorksAt = "";
						tableContentId = 0;
						fireFlag = true;
						
						if (showAllClickedEmp) {
							listAllEmployeesBtn.doClick();
							showAllClickedEmp = false;
						} else {
							btnListEmployee.doClick();
						}
						fireFlag = false;
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You need to make a selection from the table first.");
				}

			}
		});
		btnRemoveEmployee.setBounds(152, 363, 117, 42);

		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.setForeground(new Color(255, 255, 255));
		logOutBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		logOutBtn.setBackground(new Color(223, 94, 60));
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login.setVisible(true);
			}
		});
		logOutBtn.setBounds(417, 538, 94, 32);
		contentPane.add(logOutBtn);
		
		modelitem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!modelitem.isSelectionEmpty()) {
					int index = modelitem.getMinSelectionIndex();
					tablecontentitemname = (String) itemTable.getValueAt(index, 0);
					tablecontentitembrand = (String) itemTable.getValueAt(index, 2);
				}
			}
		});
		
		modelemp.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!modelemp.isSelectionEmpty()) {
					int index = modelemp.getMinSelectionIndex();
					tableContentName = (String) empTable.getValueAt(index, 0);
					tableContentWorksAt = (String) empTable.getValueAt(index, 10);
					tableContentId = (int) empTable.getValueAt(index, 9);
				}
			}
		});
		
		JButton btnDiscardItem = new JButton("Discard Item");
		btnDiscardItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDiscardItem.setForeground(new Color(37, 81, 68));
		btnDiscardItem.setBackground(new Color(215, 230, 232));
		btnDiscardItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tablecontentitembrand.equals("") && !tablecontentitemname.equals("")) {
					int answer = JOptionPane.showConfirmDialog(new JFrame(),
							"Do you really want to discard this item?", "Discard Item", 0);

					if (answer == 0 && !tablecontentitembrand.equals("") && !tablecontentitemname.equals("")) {

						boolean found = false;
						try {
							found = Information.getInstance().deleteItem(tablecontentitembrand, tablecontentitemname, b);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						if (found) {
							JOptionPane.showMessageDialog(new JFrame(), "Selected item is successfully discarded.");

							tablecontentitembrand = "";
							tablecontentitemname = "";
							fireFlag = true;
							if (showAllClickedItem) {
								listAllItemsBtn.doClick();
							} else {
								btnListItem.doClick();
							}
							
							fireFlag = false;
						}
					}
					
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You need to make a selection from the table first.");
				}

			}
		});
		btnDiscardItem.setBounds(30, 253, 131, 36);
		manageStockPanel.add(btnDiscardItem);
		
		JButton removeEmployeeBtn = new JButton("Remove Employee");
		removeEmployeeBtn.setBackground(new Color(215, 230, 232));
		removeEmployeeBtn.setForeground(new Color(37, 81, 68));
		removeEmployeeBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		removeEmployeeBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!tableContentName.equals("") && !tableContentWorksAt.equals("") && tableContentId != 0) {
					int answer = JOptionPane.showConfirmDialog(new JFrame(),
							"Do you really want to fire this employee?", "Fire Employee", 0);

					if (answer == 0 && !tableContentName.equals("") && !tableContentWorksAt.equals("")
							&& tableContentId != 0) {
						boolean found = false;
						 try {
							found = Information.getInstance().deleteEmployee(tableContentName, tableContentWorksAt, tableContentId);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						if (found) {
							JOptionPane.showMessageDialog(new JFrame(), "Selected employee is fired.");

							tableContentName = "";
							tableContentWorksAt = "";
							tableContentId = 0;
							fireFlag = true;
							
							if (showAllClickedEmp) {
								listAllEmployeesBtn.doClick();
								showAllClickedEmp = false;
							} else {
								btnListEmployee.doClick();	
							}

							fireFlag = false;
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You need to make a selection from the table first.");
				}
			}
		});
		removeEmployeeBtn.setBounds(62, 206, 188, 42);
		manageEmployeePanel.add(removeEmployeeBtn);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(215, 230, 232));
		contentPane_1.setBounds(0, 0, 891, 32);
		contentPane.add(contentPane_1);
		contentPane_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                 posX = e.getX();
                 posY = e.getY();
            }
        });

		contentPane_1.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = getLocation().x + e.getX() - posX;
                int newY = getLocation().y + e.getY() - posY;
                setLocation(newX, newY);
            }
        });
	}
	
	private ManagerMenu getObject() {
		return this;
	}
}
