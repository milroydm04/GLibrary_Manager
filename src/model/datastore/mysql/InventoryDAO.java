package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Inventory;
import model.IEmployeeDAO;

/**
 * @author John Phillips
 * @version 20151015
 *
 */
public class InventoryDAO implements IEmployeeDAO {
	
	protected final static boolean DEBUG = false;

	@Override
	public void createRecord(Inventory inventory) {
		final String QUERY = "insert into inventory (empId, itemName, lastName, firstName, homePhone, quantity, price) VALUES (null, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, inventory.getItemName());
			stmt.setString(2, inventory.getLastName());
			stmt.setString(3, inventory.getFirstName());
			stmt.setString(4, inventory.getHomePhone());
			stmt.setInt(5, inventory.getQuantity());
			stmt.setDouble(6, inventory.getPrice());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Inventory retrieveRecordById(int id) {
		final String QUERY = "select empId, itemName, lastName, firstName, homePhone, quantity, price from inventory where empId = " + id;
		// final String QUERY = "select empId, lastName, firstName, homePhone,
		// salary from employee where empId = ?";
		Inventory emp = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				emp = new Inventory(rs.getInt("empId"), rs.getString("itemName"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getInt("quantity"), rs.getDouble("price"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return emp;
	}

	@Override
	public List<Inventory> retrieveAllRecords() {
		final List<Inventory> myList = new ArrayList<>();
		final String QUERY = "select empId, itemName, lastName, firstName, homePhone, quantity, price from inventory";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Inventory(rs.getInt("empId"), rs.getString("itemName"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getInt("quantity"), rs.getDouble("price")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord(Inventory updatedEmployee) {
		final String QUERY = "update inventory set itemName=?, lastName=?, firstName=?, homePhone=?, quantity=?, price=? where empId=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(2, updatedEmployee.getItemName());
			stmt.setString(3, updatedEmployee.getLastName());
			stmt.setString(4, updatedEmployee.getFirstName());
			stmt.setString(5, updatedEmployee.getHomePhone());
			stmt.setInt(6, updatedEmployee.getQuantity());
			stmt.setDouble(7, updatedEmployee.getPrice());
			stmt.setInt(1, updatedEmployee.getEmpId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from employee where empId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Inventory inventory) {
		final String QUERY = "delete from inventory where empId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, inventory.getEmpId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Inventory inventory : retrieveAllRecords()) {
			sb.append(inventory.toString() + "\n");
		}

		return sb.toString();
	}
}
