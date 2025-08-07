

public class Item {
	private String name;
	private String itemType;
	private String brand;
	private String productionDate;
	private String expirationDate;
	private int stock;
	private String branch;

	public Item(String name, String itemType, String brand, String productionDate, String expirationDate,int stock, String branch) {
		this.name = name;
		this.itemType = itemType;
		this.brand = brand;
		this.productionDate = productionDate;
		this.expirationDate = expirationDate;
		this.stock = stock;
		this.branch = branch;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
