import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JCheckBox;

public class CreateUser extends JFrame {
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_salary;
	private JTextField textField_phone;
	private JTextField textField_email;
	private JTextField textField_idNum;
	private JTextField textField_age;
	private JButton returnToLoginBtn;
	private JButton returnToMenuBtn;
	private JPasswordField passwordField;
	private JTextField textfield_username;
	private int posX;
	private int posY;
	
	/**
	 * Create the frame.
	 */
	public CreateUser(Login login, ExecutiveMenu execMenu, ManagerMenu managerMenu, Building b) {
		setResizable(false);
		setUndecorated(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (execMenu != null) {
					execMenu.setEnabled(true);
					execMenu.setFocusable(true);
					execMenu.requestFocus();
				} else if (managerMenu != null) {
					managerMenu.setEnabled(true);
					managerMenu.setFocusable(true);
					managerMenu.requestFocus();
				} else {
					dispose();
					login.setVisible(true);
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 394, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		returnToLoginBtn = new JButton("Return To Login");
		returnToLoginBtn.setForeground(new Color(0, 0, 0));
		returnToLoginBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		returnToLoginBtn.setBackground(new Color(37, 81, 68));
		returnToLoginBtn.setBounds(21, 486, 164, 39);
		returnToLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(returnToLoginBtn);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setForeground(new Color(37, 81, 68));
		txtpnName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnName.setEditable(false);
		txtpnName.setBackground(new Color(255, 255, 255));
		txtpnName.setBounds(36, 46, 71, 20);
		txtpnName.setText("Name");
		contentPane.add(txtpnName);
		
		JTextPane txtpnSurname = new JTextPane();
		txtpnSurname.setForeground(new Color(37, 81, 68));
		txtpnSurname.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnSurname.setEditable(false);
		txtpnSurname.setBackground(new Color(255, 255, 255));
		txtpnSurname.setBounds(36, 77, 71, 20);
		txtpnSurname.setText("Surname");
		contentPane.add(txtpnSurname);
		
		JTextPane txtpnSalary = new JTextPane();
		txtpnSalary.setForeground(new Color(37, 81, 68));
		txtpnSalary.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnSalary.setEditable(false);
		txtpnSalary.setBackground(new Color(255, 255, 255));
		txtpnSalary.setBounds(36, 296, 89, 20);
		txtpnSalary.setText("Salary");
		contentPane.add(txtpnSalary);
		
		JTextPane txtpnStartDate = new JTextPane();
		txtpnStartDate.setForeground(new Color(37, 81, 68));
		txtpnStartDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnStartDate.setEditable(false);
		txtpnStartDate.setBackground(new Color(255, 255, 255));
		txtpnStartDate.setBounds(36, 265, 89, 20);
		txtpnStartDate.setText("Start Date");
		contentPane.add(txtpnStartDate);
		
		JTextPane txtpnPhone = new JTextPane();
		txtpnPhone.setForeground(new Color(37, 81, 68));
		txtpnPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnPhone.setEditable(false);
		txtpnPhone.setBackground(new Color(255, 255, 255));
		txtpnPhone.setBounds(36, 170, 56, 20);
		txtpnPhone.setText("Phone");
		contentPane.add(txtpnPhone);
		
		JTextPane txtpnEmail = new JTextPane();
		txtpnEmail.setForeground(new Color(37, 81, 68));
		txtpnEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnEmail.setEditable(false);
		txtpnEmail.setBackground(new Color(255, 255, 255));
		txtpnEmail.setBounds(36, 327, 89, 20);
		txtpnEmail.setText("Email");
		contentPane.add(txtpnEmail);
		
		JTextPane txtpnIdNumber = new JTextPane();
		txtpnIdNumber.setForeground(new Color(37, 81, 68));
		txtpnIdNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnIdNumber.setEditable(false);
		txtpnIdNumber.setBackground(new Color(255, 255, 255));
		txtpnIdNumber.setBounds(36, 234, 89, 20);
		txtpnIdNumber.setText("ID Number");
		contentPane.add(txtpnIdNumber);
		
		JTextPane txtpnGender = new JTextPane();
		txtpnGender.setForeground(new Color(37, 81, 68));
		txtpnGender.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnGender.setEditable(false);
		txtpnGender.setBackground(new Color(255, 255, 255));
		txtpnGender.setBounds(36, 139, 56, 20);
		txtpnGender.setText("Gender");
		contentPane.add(txtpnGender);
		
		JTextPane txtpnAge = new JTextPane();
		txtpnAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnAge.setForeground(new Color(37, 81, 68));
		txtpnAge.setEditable(false);
		txtpnAge.setBackground(new Color(255, 255, 255));
		txtpnAge.setBounds(36, 108, 56, 20);
		txtpnAge.setText("Age");
		contentPane.add(txtpnAge);
		
		textField_name = new JTextField();
		textField_name.setBounds(189, 44, 164, 20);
		contentPane.add(textField_name);
		textField_name.setColumns(10);

		textField_surname = new JTextField();
		textField_surname.setBounds(189, 75, 164, 20);
		contentPane.add(textField_surname);
		textField_surname.setColumns(10);
		
		textField_salary = new JTextField();
		textField_salary.setBounds(189, 296, 164, 20);
		contentPane.add(textField_salary);
		textField_salary.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setBounds(189, 170, 164, 20);
		contentPane.add(textField_phone);
		textField_phone.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(189, 327, 164, 20);
		textField_email.setColumns(10);
		contentPane.add(textField_email);
		
		textField_idNum = new JTextField();
		textField_idNum.setBounds(189, 234, 164, 20);
		textField_idNum.setColumns(10);
		contentPane.add(textField_idNum);
		
		textField_age = new JTextField();
		textField_age.setBounds(189, 106, 164, 20);
		textField_age.setColumns(10);
		contentPane.add(textField_age);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(189, 265, 164, 20);
		contentPane.add(dateChooser);
		
		JComboBox comboBox_gender = new JComboBox();
		comboBox_gender.setBounds(189, 137, 164, 22);
		comboBox_gender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		comboBox_gender.setMaximumRowCount(3);
		contentPane.add(comboBox_gender);
		
		JTextPane txtpnWorkerType = new JTextPane();
		txtpnWorkerType.setForeground(new Color(37, 81, 68));
		txtpnWorkerType.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnWorkerType.setEditable(false);
		txtpnWorkerType.setBackground(new Color(255, 255, 255));
		txtpnWorkerType.setBounds(36, 203, 89, 20);
		txtpnWorkerType.setText("Employee Type");
		contentPane.add(txtpnWorkerType);
		
		JComboBox typeSelector = new JComboBox();
		typeSelector.setBounds(189, 201, 164, 22);
		typeSelector.setModel(new DefaultComboBoxModel(new String[] {"Worker", "Manager"}));
		typeSelector.setMaximumRowCount(2);
		contentPane.add(typeSelector);
		
		JButton addEmployeeBtn = new JButton("Add Employee");
		addEmployeeBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		addEmployeeBtn.setForeground(new Color(255, 255, 255));
		addEmployeeBtn.setBackground(new Color(223, 94, 60));

		JComboBox branchComboBox = new JComboBox();
		branchComboBox.setModel(new DefaultComboBoxModel(new String[] {"Market1", "Market2", "Market3", "Warehouse1", "Warehouse2", "Warehouse3"}));
		branchComboBox.setBounds(189, 358, 164, 23);
		branchComboBox.setMaximumRowCount(6);
		contentPane.add(branchComboBox);
		
		if (managerMenu != null) {
			typeSelector.setSelectedItem("Worker");
			typeSelector.setEnabled(false);
			branchComboBox.setSelectedItem(b.getName());
			branchComboBox.setEnabled(false);
		}
		
		addEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField_name.getText().isBlank() && !textField_surname.getText().isBlank() && 
                        !textField_salary.getText().isBlank() && !textField_age.getText().isBlank() && !textField_phone.getText().isBlank()
                        && !textField_email.getText().isBlank() && !textField_idNum.getText().isBlank() 
                        && !textfield_username.getText().isBlank() && !passwordField.getText().isBlank()) {
                    if (isInteger(textField_age.getText()) && isInteger(textField_salary.getText())) {
                        String name = textField_name.getText().trim();
                        String surname = textField_surname.getText().trim();
                        int age = Integer.parseInt(textField_age.getText().trim());
                        String gender = (String) comboBox_gender.getSelectedItem();
                        String phone = textField_phone.getText().trim();
                        String identification_num = textField_idNum.getText().trim();
                        String startDate =  formatDate(dateChooser.getDate().toString());
                        String email = textField_email.getText().trim();
                        int salary =  Integer.parseInt(textField_salary.getText().trim());
                        String type	= (String) typeSelector.getSelectedItem();
                        String branch = (String) branchComboBox.getSelectedItem();
                        
                        String username = textfield_username.getText();
                        String password = passwordField.getText();
                        Information.id ++;
                        int id = Information.id;
                        EmployeeFactory ef = new EmployeeFactory();
                        Employee emp = ef.createEmployee(type, name, surname, salary, startDate, phone, email, identification_num, gender, id, age, branch, username, password);
                        
                        Information.getInstance().addEmployeeToBuilding(branch, emp);
                        String query = String.format("INSERT INTO \"employee\" VALUES(%d,'%s','%s','%d','%s','%s','%s','%d','%s','%s','%s','%s','%s','%s')",id,name,surname,age,gender,phone,identification_num,salary,email,startDate,type,branch,username,password);
                        DatabaseConnection.insertValue(query);
                      
                        
                        JOptionPane.showMessageDialog(new JFrame(), "New user created successfully.");
                    } else {
                    	JOptionPane.showMessageDialog(new JFrame(), "Both age and salary fields must be an integer.");
                    }
                } else {
                	JOptionPane.showMessageDialog(new JFrame(), "All fields must be filled.");
                }
			}
		});
		addEmployeeBtn.setBounds(206, 486, 164, 39);
		contentPane.add(addEmployeeBtn);
		
		returnToMenuBtn = new JButton("Return to Menu");
		returnToMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (execMenu != null) {
					dispose();
					execMenu.setEnabled(true);
					execMenu.setFocusable(true);
					execMenu.requestFocus();
				} else {
					dispose();
					managerMenu.setEnabled(true);
					managerMenu.setFocusable(true);
					managerMenu.requestFocus();
				}
			}
		});
		returnToMenuBtn.setBounds(21, 487, 164, 39);
		contentPane.add(returnToMenuBtn);
		
		JTextPane txtpnBranch = new JTextPane();
		txtpnBranch.setForeground(new Color(37, 81, 68));
		txtpnBranch.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnBranch.setText("Branch");
		txtpnBranch.setBackground(new Color(255, 255, 255));
		txtpnBranch.setEditable(false);
		txtpnBranch.setBounds(36, 358, 71, 23);
		contentPane.add(txtpnBranch);
		
		passwordField = new JPasswordField();
		
		passwordField.setColumns(10);
		passwordField.setBounds(189, 423, 164, 20);
		contentPane.add(passwordField);
		
		
		passwordField.setColumns(10);
		passwordField.setBounds(189, 423, 164, 20);
		contentPane.add(passwordField);
		
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setForeground(new Color(37, 81, 68));
		txtpnUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnUsername.setText("Username");
		txtpnUsername.setEditable(false);
		txtpnUsername.setBackground(new Color(255, 255, 255));
		txtpnUsername.setBounds(36, 392, 71, 20);
		contentPane.add(txtpnUsername);
		
		JTextPane txtpnPassowrd = new JTextPane();
		txtpnPassowrd.setForeground(new Color(37, 81, 68));
		txtpnPassowrd.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnPassowrd.setText("Password");
		txtpnPassowrd.setEditable(false);
		txtpnPassowrd.setBackground(new Color(255, 255, 255));
		txtpnPassowrd.setBounds(36, 423, 89, 20);
		contentPane.add(txtpnPassowrd);
		
		textfield_username = new JTextField();
		textfield_username.setColumns(10);
		textfield_username.setBounds(189, 392, 164, 20);
		contentPane.add(textfield_username);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(215, 230, 232));
		contentPane_1.setBounds(0, 0, 394, 32);
		contentPane.add(contentPane_1);
		
		JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");
		contentPane.add(showPasswordCheckbox);
		showPasswordCheckbox.setBounds(199, 450, 120, 23);
		char defaultChar = passwordField.getEchoChar();
		showPasswordCheckbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (showPasswordCheckbox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar(defaultChar);
				}
			}		
		});
	
		
		
		
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
	
	public JButton getReturnToLoginBtn() {
		return returnToLoginBtn;
	}

	public JButton getReturnToMenuBtn() {
		return returnToMenuBtn;
	}
	
	private boolean isInteger(String str) {
        boolean flag = true;
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
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
