package application;

public class Hourly extends Employee {
	
	// declare class variables
	private static final long serialVersionUID = 3299674633241846752L;

	// CONSTRUCTOR: new employee from console
	public Hourly(String username, Double salary, String name, String passwordHash) {
		super(username, salary, name, "Hourly", passwordHash);
	}
	
	// calculate pay
	@Override
	public double getPay() {
		return (salary / 52.0 / 40.0);
	}
	
	// instance data dump
	public String toString() {
		return super.toString();
	}
}
