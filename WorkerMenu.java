import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class WorkerMenu extends JFrame {

	private JPanel contentPane;
	private JTextField Product_Name_Text;
	private JTextField Product_Type_Text;
	private JScrollPane stockPane;
	private JButton btnSellitem;
	
	private JTable itemTable;

	private String tablecontentitemname = "";
	private String tablecontentitembrand = "";
	private boolean sellFlag = false;
	private boolean showAllClickedItem = false;
	private JPanel contentPane_1;
	private int posX;
	private int posY;


	/**
	 * Create the frame.
	 */
	public WorkerMenu(Login login, Building b) {
		setResizable(false);
		setUndecorated(true);
		setTitle("Worker Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JTextPane txtpnProductName = new JTextPane();
		txtpnProductName.setForeground(new Color(37, 81, 68));
		txtpnProductName.setBounds(178, 52, 107, 25);
		txtpnProductName.setEditable(false);
		txtpnProductName.setText("Product Name:");
		txtpnProductName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductName.setBackground(new Color(255, 255, 255));
		contentPane.add(txtpnProductName);

		Product_Name_Text = new JTextField();
		Product_Name_Text.setBounds(295, 52, 153, 25);
		Product_Name_Text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Product_Name_Text.setColumns(10);
		contentPane.add(Product_Name_Text);

		JTextPane txtpnProductType = new JTextPane();
		txtpnProductType.setForeground(new Color(37, 81, 68));
		txtpnProductType.setBounds(474, 52, 101, 25);
		txtpnProductType.setEditable(false);
		txtpnProductType.setText("Product Type:");
		txtpnProductType.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnProductType.setBackground(new Color(255, 255, 255));
		contentPane.add(txtpnProductType);

		Product_Type_Text = new JTextField();
		Product_Type_Text.setBounds(585, 52, 171, 25);
		Product_Type_Text.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Product_Type_Text.setColumns(10);
		contentPane.add(Product_Type_Text);

		stockPane = new JScrollPane();
		stockPane.setBounds(193, 101, 565, 348);
		contentPane.add(stockPane);

		itemTable = new JTable();
		stockPane.setViewportView(itemTable);
		itemTable.setVisible(false);
		stockPane.setVisible(true);

		JButton btnListItem = new JButton("List Item");
		btnListItem.setForeground(new Color(37, 81, 68));
		btnListItem.setBackground(new Color(215, 230, 232));
		btnListItem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListItem.setBounds(33, 195, 117, 36);
		btnListItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Product_Name_Text.getText().isBlank() && Product_Type_Text.getText().isBlank()) {
					JOptionPane.showMessageDialog(new JFrame(), "At least one of the fields must be filled.");
				} else {
					ArrayList<Item> itemList = b.getItemList();

					if (itemList.size() == 0) {
						if (sellFlag) {
							itemTable.setModel(new DefaultTableModel());
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "There are no items in this building.");
						}
					} else {
						DefaultTableModel model = new DefaultTableModel();
						Object[] columns = { "Name", "Item Type", "Brand", "Production Date", "Expiry Date", "Stock" };
						model.setColumnIdentifiers(columns);
						boolean atLeastOneMatch = false;

						for (int i = 0; i < itemList.size(); i++) {
							if (!Product_Name_Text.getText().isBlank() && !Product_Type_Text.getText().isBlank()) {
								if (itemList.get(i).getName().equals(Product_Name_Text.getText()) 
										&& itemList.get(i).getItemType().equals(Product_Type_Text.getText())) {
									String name = itemList.get(i).getName();
									String itemType = itemList.get(i).getItemType();
									String brand = itemList.get(i).getBrand();
									String productionDate = itemList.get(i).getProductionDate().toString();
									String expirationDate = itemList.get(i).getExpirationDate().toString();
									int Stock = itemList.get(i).getStock();
									Object[] data = { name, itemType, brand, productionDate, expirationDate, Stock };
									model.addRow(data);
									atLeastOneMatch = true;
								}
							} else {
								if (itemList.get(i).getName().equals(Product_Name_Text.getText()) 
										|| itemList.get(i).getItemType().equals(Product_Type_Text.getText())) {
									String name = itemList.get(i).getName();
									String itemType = itemList.get(i).getItemType();
									String brand = itemList.get(i).getBrand();
									String productionDate = itemList.get(i).getProductionDate().toString();
									String expirationDate = itemList.get(i).getExpirationDate().toString();
									int Stock = itemList.get(i).getStock();
									Object[] data = { name, itemType, brand, productionDate, expirationDate, Stock };
									model.addRow(data);
									atLeastOneMatch = true;
								}
							}
						}
						if (atLeastOneMatch) {
							itemTable.setModel(model);

							// String query = "select * from \"employee\"";
							// DefaultTableModel temp = DatabaseConnection.getTable(query);
							itemTable.setEnabled(true);
							itemTable.setVisible(true);
							stockPane.setVisible(true);
							showAllClickedItem = false;
						} else {
							if (!sellFlag) {
								JOptionPane.showMessageDialog(new JFrame(), "No items according to the given information was found.");
							} else {
								itemTable.setModel(new DefaultTableModel());
							}
						}
					}
				}
			}
		});
		contentPane.add(btnListItem);
		

		JButton listAllItemsBtn = new JButton("List All Items");
		listAllItemsBtn.setForeground(new Color(37, 81, 68));
		listAllItemsBtn.setBackground(new Color(215, 230, 232));
		listAllItemsBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		listAllItemsBtn.setBounds(33, 359, 117, 30);
		listAllItemsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> itemList = b.getItemList();
				if (itemList.size() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "There are no items in the selected building.");
				} else {
					DefaultTableModel model = new DefaultTableModel() {
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false; // Make all cells non-editable
			            }
			        };
					
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
		listAllItemsBtn.setToolTipText("");
		contentPane.add(listAllItemsBtn);


		btnSellitem = new JButton("Sell Item");
		btnSellitem.setForeground(new Color(37, 81, 68));
		btnSellitem.setBackground(new Color(215, 230, 232));
		btnSellitem.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSellitem.setBounds(33, 275, 117, 36);
		btnSellitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tablecontentitembrand.equals("") && !tablecontentitemname.equals("")) {
					boolean found = false;
					try {
						found = Information.getInstance().deleteItem(tablecontentitembrand, tablecontentitemname, b);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (found) {
						JOptionPane.showMessageDialog(new JFrame(), "Selected item is successfully sold.");

						tablecontentitembrand = "";
						tablecontentitemname = "";
						sellFlag = true;
						if (showAllClickedItem) {
							listAllItemsBtn.doClick();
						} else {
							btnListItem.doClick();
						}
						sellFlag = false;
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You need to make a selection from the table first.");
				}
			}
		});
		contentPane.add(btnSellitem);
		
		JTextPane txtpnCurrentlyLoggedIn = new JTextPane();
		txtpnCurrentlyLoggedIn.setForeground(new Color(37, 81, 68));
		txtpnCurrentlyLoggedIn.setBounds(42, 483, 279, 25);
		txtpnCurrentlyLoggedIn.setText("Currently Logged In User: " + login.getUsername());
		txtpnCurrentlyLoggedIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnCurrentlyLoggedIn.setEditable(false);
		txtpnCurrentlyLoggedIn.setBackground(new Color(255, 255, 255));
		contentPane.add(txtpnCurrentlyLoggedIn);
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		logOutButton.setForeground(new Color(255, 255, 255));
		logOutButton.setBackground(new Color(223, 94, 60));
		logOutButton.setBounds(409, 478, 107, 30);
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login.setVisible(true);
			}
		});
		contentPane.add(logOutButton);
		
		contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(215, 230, 232));
		contentPane_1.setBounds(-2, 0, 818, 32);
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
		ListSelectionModel modelitem = itemTable.getSelectionModel();
		modelitem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!modelitem.isSelectionEmpty()) {
					int index = modelitem.getMinSelectionIndex();
					tablecontentitemname = (String) itemTable.getValueAt(index, 0);
					tablecontentitembrand = (String) itemTable.getValueAt(index, 2);
				}
			}
		});
	}
}
