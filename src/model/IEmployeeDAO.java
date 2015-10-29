package model;

import java.util.List;

/**
 * @author John Phillips
 * @version 20151009
 *
 */
public interface IEmployeeDAO {

	void createRecord(Inventory inventory);

	Inventory retrieveRecordById(int id);

	List<Inventory> retrieveAllRecords();

	void updateRecord(Inventory updatedEmployee);

	void deleteRecord(int id);

	void deleteRecord(Inventory inventory);

	String toString();

}