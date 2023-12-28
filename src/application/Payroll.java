package application;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Payroll {
	
	// 3.1 define class data members
	ObservableList<Employee> employees = FXCollections.observableArrayList();
	ObservableList<Employee> exEmployees = FXCollections.observableArrayList();
	String employeeFile = "employees.obj";
	String payrollFile = "payroll.txt";
	static Scanner sc = new Scanner(System.in);
	static Integer employeeType;
	static Employee currentUser;
	static Employee selectedEmployee;
	static String managerID = "00000";
	static String username;
	static String password;

	// 3.1 constructor
	Payroll() {
		
		Integer employeeCount = 0;
		
		try {
			Object newEmployee = new Object();
			FileInputStream fis = new FileInputStream(employeeFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while (true) {
				try {
					newEmployee	= ois.readObject();
					String className = newEmployee.getClass().getName();
					if (className.equals("application.Hourly")) {
						employees.add((Hourly) newEmployee);
					} else if (className.equals("application.Salaried")) {
						employees.add((Salaried) newEmployee);
					}
					employeeCount++;
					
				} catch (EOFException e) {
					// end of file reached
					if(employeeCount >= 1) {
						Employee.determineNextID(employees);
						System.out.println("\nOperation successfully completed. Imported " 
								+ employeeCount + " employees from file.");
						break;
					} else {
						e.printStackTrace();
						System.exit(1);
					}
				}
			}
			
			ois.close();
			fis.close();
			
			// throw exception if database is empty
			if(employeeCount == 0)
				throw new FileNotFoundException();
			
			} catch (FileNotFoundException e) {
				// no database file exists
				System.out.println("\nEmployee Database empty or not found. A new database is being created.");
				System.out.println("Information about the boss is required to initialize the database...");
				//doMenu(2);
				addEmployeeBypass(employeeFile, employees);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
	}
	
	public static String encryptPassword(String password) {
		
		try {
			String hash = new String(computeHash(password));
			return hash;
		}
		catch (NoSuchAlgorithmException e){
			System.err.println("\nLOG:\t Encryption failed.");
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}

	public static byte[] computeHash(String password ) throws NoSuchAlgorithmException {
		MessageDigest message = MessageDigest.getInstance("SHA-256");
		message.reset();
		return message.digest(password.getBytes());
	}
	
	public static int doLogin(ObservableList<Employee> employees, String username, String password) {
		String role;
		for(Employee emp : employees) {
			if(emp.getUsername().equals(username) && emp.getPasswordHash().equals(encryptPassword(password))) {
				currentUser = emp;
				if(emp.getStrID().equals(managerID)) {
					role = "MANAGER";
				} else if(emp.getEndDate() != null) {
					role = "TERMINATED";
				} else {
					role = "EMPLOYEE";
				}
				System.out.println("\nLOG:\tUser " + currentUser.getUsername() + " logged in with role " + role + ".");
				return 1;
			}
		} 
		
		System.out.println("\nLOG:\tUser " + username + " failed to log in.");
		return 0;
	}
	
	public static int addEmployee(String employeeFile, ObservableList<Employee> employees, String name, String username, String salary) {

			Employee newEmployee = null;
			
			// check if user exists
			for(Employee i : employees) {
				if(i.getUsername().equals(username)) {
					System.out.println("\nLOG:\tCreate user operation failed. User " + username + " already exists.");
					return 0;
				}
			}
			
			// check salary for type error
			try {
				Double.parseDouble(salary);
				
			} catch (NumberFormatException e) {
				return -1;
			}
	
			// create new object, add to collection
			if (employeeType == 1) {
				newEmployee = new Hourly(username, Double.parseDouble(salary), name, encryptPassword(password));
			} else if(employeeType == 0) {
				newEmployee = new Salaried(username, Double.parseDouble(salary), name, encryptPassword(password));
			}
			
			employees.add(newEmployee);
			printToFile(employees, employeeFile);
			System.out.println("\nLOG:\tUser " + newEmployee.getUsername() + " successfully added.");
		
		return 1;
	}
	
	private static int addEmployeeBypass(String employeeFile, ObservableList<Employee> employees) {
		// this is ONLY used to create the first user when a database is created. It is
		// computer-called and cannot be accessed from the menu.
		Employee newEmployee = null;
		String username;
		Double salary = 0.0;
		String name;
		
		// read username
		System.out.print("\nEnter employee username:  ");
		username = sc.nextLine();
		
		// read password
		System.out.print("\nEnter employee password:  ");
		password = encryptPassword(sc.nextLine());

		// read salary, handle exception
		System.out.print("Enter employee salary:  ");
		while (true) {
			try {
				salary = sc.nextDouble();
				sc.nextLine();
				if (salary >= 0)
					break;
				throw new InputMismatchException();
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input. Expected positive double. Enter employee salary:  ");
				sc.nextLine();
			}
		}
		
		// read employee type, handle exception
		System.out.print("Enter employee type (0 = Salaried / 1 = Hourly):  ");
		while (true) {
			try {
				employeeType = sc.nextInt();
				sc.nextLine();
				if (employeeType <= 0 || employeeType >= 1)
					break;
				throw new InputMismatchException();
			} catch (InputMismatchException e) {
				System.out.print("Invalid Input. Expected 0 or 1. "
						+ "Enter employee type (0 = Salaried / 1 = Hourly):  ");
				sc.nextLine();
			}
		}

		// read name
		System.out.print("Enter employee name:  ");
		name = sc.nextLine();
		
		// check if user exists
		for(Employee i : employees) {
			if(i.getUsername().equals(username)) {
				System.out.println("\nOperation Failed. User " + username + " already exists.");
				return 1;
			}
		}

		// create new object, add to collection
		if (employeeType == 1) {
			newEmployee = new Hourly(username, salary, name, password);
		} else if(employeeType == 0) {
			newEmployee = new Salaried(username, salary, name, password);
		}
		
		employees.add(newEmployee);
		printToFile(employees, employeeFile);
		System.out.println("\nUser " + newEmployee.getUsername() + " successfully added.");
		
		return 1;
	}
	
	public static int terminateEmployee(ObservableList<Employee> employees, ObservableList<Employee> exEmployees, String employeeFile) {
		
		// print to log
		System.out.println("\nLOG:\tUser " + currentUser.getUsername() + " attempting termination/resignation operation.");
		
		
		// initialize and configure alert
		try {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			
			// no employee selected
			if(selectedEmployee == null) {
				alert.setContentText("No employee selected. Please make a selection and try again.");
				alert.showAndWait();
				return 0;
			// manager terminates themselves
			} else if(currentUser.getStrID().equals(managerID) && currentUser.equals(selectedEmployee)) {
				alert.setContentText("The manager cannot terminate themselves. Please make a different selection.");
				alert.showAndWait();
				return 0;
			} else if(currentUser.getStrID().equals(managerID) && selectedEmployee.getEndDate() != null) {
				alert.setContentText("This employee has already been terminated. Please make a different selection.");
				alert.showAndWait();
				return 0;				
			// manager terminates active employee
			} else if(currentUser.getStrID().equals(managerID) && !currentUser.equals(selectedEmployee)) {
				alert.setAlertType(AlertType.CONFIRMATION);
				alert.setContentText("Are you sure you want to terminate " + selectedEmployee.getUsername() + "?");
			// employee resigns
			} else if(!currentUser.getStrID().equals(managerID) && currentUser.equals(selectedEmployee)) {
				alert.setAlertType(AlertType.CONFIRMATION);
				alert.setContentText("Are you sure you want to resign?");
			}
			
			// show confirmation
			Optional<ButtonType> confirmationResult = alert.showAndWait();
			
			// terminate employee
			if (confirmationResult.isPresent() && confirmationResult.get() == ButtonType.OK) {
				selectedEmployee.setEndDate();
				exEmployees.add(selectedEmployee);
				//employees.remove(selectedEmployee);
				System.out.println("\nLOG:\tUser " + selectedEmployee.getUsername() + " terminated.");
			}

			// update database
			printToFile(employees, employeeFile);
			
		} catch (NullPointerException e) {
			System.out.println("\nLOG:\tTermination/Resignation operation failed due to NullPointerException.");
		}
			
		return 1;	
	}
	
	public static int updateEmployee(ObservableList<Employee> employees, String name, String salary) {
		
		if (name == "" && salary == "") {
			return 0;
		}
		else if(name != "" && salary == "") {
			selectedEmployee.setName(name);
			return 1;
		} else if(name == "" && salary != "") {
			try {
				Double.parseDouble(salary);
				selectedEmployee.setSalary(Double.parseDouble(salary));
			} catch (NumberFormatException e) {
				return -1;				
			}
		} else {
			try {
				Double.parseDouble(salary);
				selectedEmployee.setSalary(Double.parseDouble(salary));
				selectedEmployee.setName(name);
				return 1;
			} catch (NumberFormatException e) {
				return -1;				
			}
		}
		return 1;
	}
	
	public static void printToFile(ObservableList<Employee> employees, String employeeFile) {
		try {
			FileOutputStream fos = new FileOutputStream(employeeFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (Employee i : employees) {
				oos.writeObject(i);
			}
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
		
	@Override
	public String toString() {
		return "currentUsername=" + currentUser.toString() + "\temployeeType=" + employeeType
				+ "\tmanagerID=" + managerID;
	}

}