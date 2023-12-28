package application;
public class Salaried extends Employee {
	
	// declare class variables
	private static final long serialVersionUID = 4780371367523242739L;

	// CONSTRUCTOR: new employee from console
	public Salaried(String username, Double salary, String name, String passwordHash) {
		super(username, salary, name, "Salaried", passwordHash);
	}
	
	// calculate pay
	@Override
	public double getPay() {
		return salary / 24;
	}
		
	// instance data dump
	public String toString() {
		return super.toString();
	}
}
