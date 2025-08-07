import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddItem extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField itemTypeField;
	private JTextField brandField;
	private JTextField stockField;
	private int posX;
	private int posY;

	/**
	 * Create the frame.
	 */
	public AddItem(ManagerMenu menu, Building b) {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menu.setEnabled(true);
				menu.setFocusable(true);
				menu.requestFocus();
			}
		});
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setEditable(false);
		txtpnName.setBackground(new Color(255, 255, 255));
		txtpnName.setForeground(new Color(37, 81, 68));
		txtpnName.setText("Product Name:");
		txtpnName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnName.setBounds(31, 45, 111, 20);
		contentPane.add(txtpnName);
		
		JTextPane txtpnType = new JTextPane();
		txtpnType.setForeground(new Color(37, 81, 68));
		txtpnType.setEditable(false);
		txtpnType.setBackground(new Color(255, 255, 255));
		txtpnType.setText("Product Type:");
		txtpnType.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnType.setBounds(31, 76, 111, 20);
		contentPane.add(txtpnType);
		
		JTextPane txtpnProductionDate = new JTextPane();
		txtpnProductionDate.setForeground(new Color(37, 81, 68));
		txtpnProductionDate.setEditable(false);
		txtpnProductionDate.setBackground(new Color(255, 255, 255));
		txtpnProductionDate.setText("Production Date:");
		txtpnProductionDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductionDate.setBounds(31, 169, 129, 32);
		contentPane.add(txtpnProductionDate);
		
		JTextPane txtpnExpreationDate = new JTextPane();
		txtpnExpreationDate.setForeground(new Color(37, 81, 68));
		txtpnExpreationDate.setEditable(false);
		txtpnExpreationDate.setBackground(new Color(255, 255, 255));
		txtpnExpreationDate.setText("Expiration Date:");
		txtpnExpreationDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnExpreationDate.setBounds(31, 212, 129, 32);
		contentPane.add(txtpnExpreationDate);
		
		JDateChooser productionDate = new JDateChooser();
		productionDate.setBounds(198, 169, 167, 32);
		contentPane.add(productionDate);
		
		JDateChooser expirationDate = new JDateChooser();
		expirationDate.setBounds(198, 212, 167, 32);
		contentPane.add(expirationDate);
		
		JButton returnToMenuBtn = new JButton("Return to Menu");
		returnToMenuBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		returnToMenuBtn.setBackground(new Color(37, 81, 68));
		returnToMenuBtn.setBounds(31, 296, 135, 46);
		contentPane.add(returnToMenuBtn);
		
		returnToMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.setEnabled(true);
				menu.setFocusable(true);
				menu.requestFocus();
			}
		});
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBackground(new Color(223, 94, 60));
		btnAddItem.setForeground(new Color(255, 255, 255));
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nameField.getText().isBlank() && !itemTypeField.getText().isBlank() && !brandField.getText().isBlank() && 
						productionDate.getDate() != null && expirationDate.getDate() != null && !stockField.getText().isBlank()) {
					boolean isInteger = isInteger(stockField.getText());
					
					if (!isInteger) {
						JOptionPane.showMessageDialog(new JFrame(), "Stock number field must be an integer.");
						return;
					}
					String branch = b.getName();
					String name = nameField.getText();
					String itemType = itemTypeField.getText();
					String brand = brandField.getText();
					String production = formatDate(productionDate.getDate().toString());
					String expiration = formatDate(expirationDate.getDate().toString());
					int stock = Integer.parseInt(stockField.getText());
					
					ArrayList<Item> items = b.getItemList();
					boolean flag = false;
					
					for (int j = 0; j < items.size(); j++) {
						if (items.get(j).getName().equals(name) && items.get(j).getBrand().equals(brand)) {
							items.get(j).setStock(items.get(j).getStock() + stock);
							flag = true;
							break;
						}
					}
					
					if (!flag) {
						Item item = new Item(name, itemType, brand, production, expiration, stock,branch);
						items.add(item);
					}
					JOptionPane.showMessageDialog(new JFrame(), "Item successfully added.");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "All fields must be filled.");
				}
			}
		});
		btnAddItem.setBounds(236, 296, 129, 46);
		contentPane.add(btnAddItem);
		
		JTextPane txtpnProductBrand = new JTextPane();
		txtpnProductBrand.setForeground(new Color(37, 81, 68));
		txtpnProductBrand.setEditable(false);
		txtpnProductBrand.setText("Product Brand:");
		txtpnProductBrand.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductBrand.setBackground(new Color(255, 255, 255));
		txtpnProductBrand.setBounds(31, 138, 111, 20);
		contentPane.add(txtpnProductBrand);
		
		nameField = new JTextField();
		nameField.setBounds(198, 45, 167, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		itemTypeField = new JTextField();
		itemTypeField.setColumns(10);
		itemTypeField.setBounds(198, 76, 167, 20);
		contentPane.add(itemTypeField);
		
		brandField = new JTextField();
		brandField.setColumns(10);
		brandField.setBounds(198, 138, 167, 20);
		contentPane.add(brandField);
		
		stockField = new JTextField();
		stockField.setBounds(198, 107, 167, 20);
		contentPane.add(stockField);
		stockField.setColumns(10);
		
		JTextPane txtpnStockNumber = new JTextPane();
		txtpnStockNumber.setForeground(new Color(37, 81, 68));
		txtpnStockNumber.setText("Stock Number:");
		txtpnStockNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnStockNumber.setEditable(false);
		txtpnStockNumber.setBackground(new Color(255, 255, 255));
		txtpnStockNumber.setBounds(31, 107, 111, 20);
		contentPane.add(txtpnStockNumber);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(215, 230, 232));
		contentPane_1.setBounds(0, 0, 394, 32);
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
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAddItem.doClick();
				}
			}
		});
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
	
	private boolean isInteger(String str) {
        boolean flag = true;
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
