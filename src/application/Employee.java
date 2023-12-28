package application;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javafx.collections.ObservableList;

public abstract class Employee implements Serializable {
	
	// declare class variables
	protected static final long serialVersionUID = -2617809307657829342L;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	protected String username;
	protected String passwordHash;
	protected Double salary;
	protected Double paySalary;
	protected String name;
	protected Date startDate;
	protected Date endDate = null;
	protected Integer ID;
	protected String strID;
	protected String type;
	protected static int nextID = 0;
	
	// CONSTRUCTOR: new employee from console
	Employee(String username, Double salary, String name, String type, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.salary = salary;
		this.name = name;
		this.startDate = new Date();
		this.ID = nextID();
		this.strID = String.format("%05d", this.ID);
		this.type = type;
	}
	
	// GETTER: salary
	public Double getSalary() {
		return salary;
	}
	
	// GETTER: paySalary
	public Double getPaySalary() {
		return paySalary;
	}
	
	// SETTER: salary
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	// SETTER: paySalary
	public void setPaySalary(Double paySalary) {
		this.paySalary = Double.parseDouble(df.format(paySalary));
	}
	
	// GETTER: name
	public String getName() {
		return name;
	}
	
	// SETTER: name
	public void setName(String name) {
		this.name = name.strip();
	}
	
	// GETTER: username
	public String getUsername() {
		return username.strip();
	}
	
	// GETTER: date
	public Date getStartDate() {
		return startDate;
	}
	
	// SETTER: date
	public void setEndDate() {
		this.endDate = new Date();
	}
	
	// GETTER: ID
	public String getStrID() {
		return strID;
	}
	
	// GETTER: endDate
	public Date getEndDate() {
		return endDate;
	}
	
	// GETTER: type
	public String getType() {
		return type;
	}
	
	// GETTER: password hash
	public String getPasswordHash() {
		return passwordHash;
	}
	
	// determine nextID after import
	public static void determineNextID (ObservableList<Employee> employees) {
		for (Employee i: employees) {
			if(i.ID >= nextID) {
				 nextID = i.ID + 1;
			}
		}
	}
	
	// generate nextID
	private static int nextID() {
		return nextID++;
	}
	
	// getPay method
	public abstract double getPay();
	
	// toString
	@Override
	public String toString() {
		return "id=" + strID + "\tusername=" + username + "\tpasswordHash=" + passwordHash + "\tsalary=" + salary 
				+ "\tstartDate=" + startDate + "\tendDate=" + endDate + "\tname=" + name;
	}

	public Integer getID() {
		return ID;
	}
	
	public Double calculatePay(Double hours) {
		return (salary / 52 / 40) * hours;
	}

}
