package model;

/**
 * The Employee class represents a single employee.
 * 
 * @author John Phillips
 * @version 20151015
 *
 */
public class Inventory {

	private int id;
	private String itemName;
	private String lastName;
	private String firstName;
	private String homePhone;
	private int quantity;
	private double price;

	public Inventory() {
		id = 0;
		itemName = "";
		lastName = "";
		firstName = "";
		homePhone = "";
		quantity = 0;
		price = 0;
	}

	public Inventory(int id, String itemName, String lastName, String firstName, String homePhone, int quantity, double price) {
		this.id = id;
		this.itemName = itemName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.homePhone = homePhone;
		this.quantity = quantity;
		this.price = price;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getEmpId() {
		return id;
	}

	public void setEmpId(int empId) {
		this.id = empId;
	}

	public String getLastName() {
		return lastName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%5d : %s, %s, %s, %s, %d, %.2f", this.getEmpId(), this.getItemName(), this.getLastName(),
				this.getFirstName(), this.getHomePhone(), this.getQuantity(), this.getPrice());
	}
}