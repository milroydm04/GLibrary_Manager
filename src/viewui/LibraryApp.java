package viewui;

import java.util.Scanner;

import model.Inventory;
import model.IEmployeeDAO;
import model.datastore.mysql.InventoryDAO;

/**
 * @author Dylan Milroy
 * @version 20151015
 * 
 */
public class LibraryApp {

	IEmployeeDAO invenList = new InventoryDAO();
	Scanner sc = new Scanner(System.in);

	public LibraryApp() {
		menuLoop();
	}

	private void menuLoop() {
		int id, quantity;
		String gameName, developer, publisher, releaseYear;
		double value;
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nGame Library App");
			System.out.println("0 = Quit");
			System.out.println("1 = Show All Games");
			System.out.println("2 = Add New Game");
			System.out.println("3 = Game Lookup");
			System.out.println("4 = Update Game");
			System.out.println("5 = Remove Game");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-5]$");

			switch (choice) {
			case "1":
				System.out.println(invenList.toString());
				break;
			case "2":
				id = Validator.getInt(sc, "New Game ID: ");
				gameName = Validator.getLine(sc, "Game name: ");
				developer = Validator.getLine(sc, "Developer name: ");
				publisher = Validator.getLine(sc, "Publisher name: ");
				releaseYear = Validator.getLine(sc, "Release Year: ");
				quantity = Validator.getInt(sc, "Quantity: ");
				value = Validator.getDouble(sc, "Value of Game: ");
				invenList.createRecord(new Inventory(id, gameName, developer, publisher, releaseYear, quantity, value));
				break;
			case "3":
				id = Validator.getInt(sc, "Employee id to retrieve: ");
				System.out.println(invenList.retrieveRecordById(id));
				break;
			case "4":
				id = Validator.getInt(sc, "Game ID to update: ");
				gameName = Validator.getLine(sc, "Game Name : ");
				developer = Validator.getLine(sc, "Developer name: ");
				publisher = Validator.getLine(sc, "Publisher Name: ");
				releaseYear = Validator.getLine(sc, "Release Year: ");
				quantity = Validator.getInt(sc, "Quantity: ");
				value = Validator.getDouble(sc, "Value of Game: ");
				invenList.updateRecord(new Inventory(id, gameName, developer, publisher, releaseYear, quantity, value));
				break;
			case "5":
				id = Validator.getInt(sc, "Game ID to delete: ");
				System.out.println(invenList.retrieveRecordById(id));
				String ok = Validator.getLine(sc, "Delete this Game? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					invenList.deleteRecord(id);
				}
				break;
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new LibraryApp();
	}
}
