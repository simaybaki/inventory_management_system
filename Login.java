import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private String username;
	private String password;
	private int posX;
	private int posY;
	private final Action action = new SwingAction();

	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(new Color(30, 132, 162));
		setResizable(false);
        setUndecorated(true);
		setTitle("Login Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		usernameField = new JTextField();
		usernameField.setBounds(53, 179, 138, 32);
		usernameField.setColumns(10);
		JPanel contentPane_2 = new JPanel();
		contentPane_2.setBounds(0, 0, 577, 32);
		contentPane.add(contentPane_2);
		contentPane_2.setLayout(null);
		contentPane_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_2.setBackground(new Color(215, 230, 232));
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(215, 230, 232));
		btnNewButton.setIcon(new ImageIcon(".\\img\\icon.png"));
		
		btnNewButton.setVisible(true);
		btnNewButton.setBounds(555, 11, 15, 15);
		contentPane_2.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
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
        
		JButton loginButton = new JButton("Login");
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(37, 81, 68));
		loginButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!usernameField.getText().isBlank() && passwordField.getPassword().length != 0) {
					username = usernameField.getText();
					password = new String(passwordField.getPassword());
					
					if (username.equals("admin") && password.equals("admin")) {
						ExecutiveMenu menu = new ExecutiveMenu(getObject());
						menu.setVisible(true);
						disposeObject();
					} else {
						String info[] = Information.getInstance().checkCreditentials(username, password);
						
						if (info[0].equals("true")) {
							Building b = Information.getInstance().getBuilding(info[2]);
							if (info[1].equals("true")) {
								ManagerMenu menu = new ManagerMenu(getObject(), b);
								menu.setVisible(true);
								disposeObject();
							} else {
								WorkerMenu menu = new WorkerMenu(getObject(), b);
								menu.setVisible(true);
								disposeObject();
							}
						} else {
							JOptionPane.showMessageDialog(new JFrame(),"Invalid username or password.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),"Both fields must be filled.");
				}
			}
		});
		loginButton.setBounds(72, 350, 86, 23);

		passwordField = new JPasswordField();
		passwordField.setBounds(53, 253, 138, 32);
		
		JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");
		showPasswordCheckbox.setBounds(63, 288, 120, 23);
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
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBounds(53, 159, 63, 20);
		txtpnUsername.setBackground(UIManager.getColor("240, 240, 240"));
		txtpnUsername.setText("Username");
		txtpnUsername.setEditable(false);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnPassword.setBounds(53, 232, 63, 20);
		txtpnPassword.setText("Password");
		txtpnPassword.setBackground(UIManager.getColor("240, 240, 240"));
		txtpnPassword.setEditable(false);
		contentPane.setLayout(null);
		contentPane.add(usernameField);
		contentPane.add(loginButton);
		contentPane.add(passwordField);
		contentPane.add(showPasswordCheckbox);
		contentPane.add(txtpnUsername);
		contentPane.add(txtpnPassword);
		
		JButton createUserButton = new JButton("Create User");
		createUserButton.setBackground(new Color(223, 94, 60));
		createUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser createUser = new CreateUser(getObject(), null, null, null);
				createUser.getReturnToLoginBtn().setVisible(true);
				createUser.getReturnToMenuBtn().setVisible(false);
				createUser.setVisible(true);
				dispose();
			}
		});
		createUserButton.setBounds(63, 384, 113, 23);
		contentPane.add(createUserButton);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setForeground(new Color(37, 81, 68));
		txtpnLogin.setFont(new Font("Tahoma", Font.BOLD, 34));
		txtpnLogin.setText("Login");
		txtpnLogin.setEditable(false);
		txtpnLogin.setBackground((Color) null);
		txtpnLogin.setBounds(71, 89, 102, 47);
		contentPane.add(txtpnLogin);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(137, 179, 185));
		contentPane_1.setBounds(243, 30, 334, 450);
		contentPane.add(contentPane_1);
		
		JTextPane txtpnIms = new JTextPane();
		txtpnIms.setSelectedTextColor(new Color(223, 94, 60));
		txtpnIms.setBounds(43, 103, 250, 211);
		contentPane_1.add(txtpnIms);
		txtpnIms.setText("Department Store Inventory Management System");
		txtpnIms.setForeground(new Color(255, 255, 255));
		txtpnIms.setFont(new Font("Tahoma", Font.BOLD, 34));
		txtpnIms.setEditable(false);
		txtpnIms.setBackground((Color) null);
		
	
		
		
		
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		});
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		});
	}
	
	private void disposeObject() {
		this.dispose();
	}
	
	private Login getObject() {
		return this;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
