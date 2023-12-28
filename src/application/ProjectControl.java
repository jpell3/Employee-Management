package application;

public class ProjectControl {
	String name = "Justin K. Pelletier";
	String course = "CSCI-6617, Java Programming";
	String term = "Spring 2023";
	String project;
	String date;
	double startTime;
	int exceptions = 0;

	// constructor
	public ProjectControl(String project, String date) {
		this.project = project;
		this.date = date;
	}

	public void banner() {
		startTime = System.currentTimeMillis();
		System.out.println("Welcome to " + project + " by " + name + ".");
		System.out.println(course + ", " + term);
		System.out.println("Developed on " + date + ".");
	}

	public void bye() {
		System.out.println("\nThank you for using " + project + ". " + "Enjoy the remainder of your day!\n");
		System.out.println("[Normal Project Termination after ~ " + computeExecutionTime(startTime) + "]");
		if (exceptions != 0)
			System.out.print("*" + exceptions + " exceptions thrown.");
	}

	// calculate the total execution time of the program and adjust unit
	private String computeExecutionTime(double startTime) {
		double elapsedTime = System.currentTimeMillis() - startTime;
		int minuteThreshold = 60000;
		int secondThreshold = 1000;
		String units = "ms";

		if (elapsedTime >= minuteThreshold) {
			elapsedTime /= 60000;
			units = "m";
		} else if (elapsedTime >= secondThreshold) {
			elapsedTime /= 1000;
			units = "s";
		} else {
			units = "ms";
		}
		return String.valueOf(elapsedTime) + " " + units;
	}

	public void addException() {
		++exceptions;
	}
}