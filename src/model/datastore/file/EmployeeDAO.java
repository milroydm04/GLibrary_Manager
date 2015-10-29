package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Inventory;
import model.IEmployeeDAO;

/**
 * @author John Phillips
 * @version 20151009
 * 
 */
public class EmployeeDAO implements IEmployeeDAO {

	protected String fileName = null;
	protected final List<Inventory> myList;

	public EmployeeDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Inventory inventory) {
		myList.add(inventory);
		writeList();
	}

	@Override
	public Inventory retrieveRecordById(int id) {
		for (Inventory inventory : myList) {
			if (inventory.getEmpId() == id) {
				return inventory;
			}
		}
		return null;
	}

	@Override
	public List<Inventory> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Inventory updatedEmployee) {
		for (Inventory inventory : myList) {
			if (inventory.getEmpId() == updatedEmployee.getEmpId()) {
				inventory.setItemName(updatedEmployee.getItemName());
				inventory.setLastName(updatedEmployee.getLastName());
				inventory.setFirstName(updatedEmployee.getFirstName());
				inventory.setHomePhone(updatedEmployee.getHomePhone());
				inventory.setQuantity(updatedEmployee.getQuantity());
				inventory.setPrice(updatedEmployee.getPrice());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Inventory inventory : myList) {
			if (inventory.getEmpId() == id) {
				myList.remove(inventory);
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(Inventory inventory) {
		myList.remove(inventory);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String item = data[1];
				String last = data[2];
				String first = data[3];
				String homePhone = data[4];
				int quantity = Integer.parseInt(data[5]);
				double salary = Double.parseDouble(data[6]);
				Inventory inventory = new Inventory(id, item, last, first, homePhone, quantity, salary);
				myList.add(inventory);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Inventory inventory : myList) {
				writer.write(String.format("%d,%s,%s,%s,%s,%d,%.2f\n", inventory.getEmpId(), inventory.getItemName(), inventory.getLastName(),
						inventory.getFirstName(), inventory.getHomePhone(), inventory.getQuantity(), inventory.getPrice()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Inventory inventory : myList) {
			sb.append(String.format("%5d : %s, %s, %s, %s, %d, %.2f\n", inventory.getEmpId(), inventory.getItemName(), inventory.getLastName(),
					inventory.getFirstName(), inventory.getHomePhone(), inventory.getQuantity(), inventory.getPrice()));
		}

		return sb.toString();
	}
}
