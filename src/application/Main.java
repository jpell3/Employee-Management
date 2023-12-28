package application;
	
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// create payroll instance
			Payroll pr = new Payroll();
			
			// create variables
			String title = "UNH Employee Portal - ";
			
			// configure root
			BorderPane loginRoot = new BorderPane();
			primaryStage.setTitle(title + "LOADING...");
			
			// configure login scene
			primaryStage.setTitle(title + "LOG IN");
			Scene loginScene = new Scene(loginRoot, 800, 500);
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
				// create logo
				Image logo = new Image("https://upload.wikimedia.org/wikipedia/en/5/55/University_of_New_Haven_seal.png");
				ImageView iv = new ImageView(logo);
				iv.setFitWidth(200);
				iv.setFitHeight(200);
			
				// create username fields
				Text username = new Text("Employee Username: ");
				TextField usernameInput = new TextField();
				usernameInput.setPromptText("Username");
				
				// create password fields
				Text password = new Text("Employee Password: ");
				PasswordField passwordInput = new PasswordField();
				passwordInput.setPromptText("Password");
				
				// create login error label
				Label errorMessage = new Label("");
				errorMessage.setId("login-error");
				
				// create submit button
				Button submitBtn = new Button("Log In");
				
				// create left box to contain labels
				VBox loginLeft = new VBox(20.0);
				loginLeft.getChildren().addAll(username, password);
				loginLeft.setAlignment(Pos.CENTER_RIGHT);
				
				// create right box to contain inputs
				VBox loginRight = new VBox(10.0);
				loginRight.setAlignment(Pos.CENTER);
				loginRight.getChildren().addAll(usernameInput, passwordInput);
				
				// create box to house left and right box
				HBox loginCenter = new HBox(20.0);
				loginCenter.setAlignment(Pos.CENTER);
				loginCenter.getChildren().addAll(loginLeft, loginRight);
				
				// create box to house all controls
				VBox loginControls = new VBox();
				VBox.setMargin(loginCenter, new Insets(40,0,25,0));
				loginControls.setId("scene");
				loginControls.getChildren().addAll(iv, loginCenter, errorMessage, submitBtn);
				loginControls.setAlignment(Pos.CENTER);
				loginRoot.setCenter(loginControls);
		
			// configure employee scene
			BorderPane managerRoot = new BorderPane();
			Scene managerScene = new Scene(managerRoot, 800, 500);
			managerScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				// create action bar
				HBox actionBar = new HBox(20);
				
				// create label
				Label actionLabel = new Label("ACTION BAR:");
				actionLabel.setId("employee-section-label");
				
				// create buttons
				Button addEmployeeBtn = new Button("Create New Employee");
				Button updateEmployeeBtn = new Button("Update Employee");
				Button terminateEmployeeBtn = new Button("Terminate Employee");
				Button payEmployeesBtn = new Button("Pay Employees");
				Button logoffBtn = new Button("Log Off");
				
				// create box to house label and action bar
				VBox actionControls = new VBox();
				actionControls.setId("employee-section");
				actionControls.getChildren().addAll(actionLabel, actionBar);
				
				// create label
				Label employeeLabel = new Label("EMPLOYEE LIST:");
				employeeLabel.setId("employee-section-label");
				
				// create and configure table				
				TableView<Employee> employeeTable = new TableView<Employee>();
				employeeTable.setId("employee-tableview");
				
				// create columns
				TableColumn<Employee, String> column1 = new TableColumn<>("ID");
				TableColumn<Employee, String> column2 = new TableColumn<>("Username");
				TableColumn<Employee, String> column3 = new TableColumn<>("Name");
				TableColumn<Employee, String> column4 = new TableColumn<>("Type");
				TableColumn<Employee, Double> column5 = new TableColumn<>("Salary");
				TableColumn<Employee, Date> column6 = new TableColumn<>("Date Hired");
				TableColumn<Employee, Date> column7 = new TableColumn<>("Date Terminated");
				
			    // create cells
				column1.setCellValueFactory(new PropertyValueFactory<Employee, String>("strID"));
			    column2.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
			    column3.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
			    column4.setCellValueFactory(new PropertyValueFactory<Employee, String>("type"));
			    column5.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
			    column6.setCellValueFactory(new PropertyValueFactory<Employee, Date>("startDate"));
			    column7.setCellValueFactory(new PropertyValueFactory<Employee, Date>("endDate"));
			    
			    // configure table and columns
			    employeeTable.setMaxHeight(200);
			    employeeTable.setStyle("-fx-alignment: CENTER");
			    employeeTable.getColumns().add(column1);
			    employeeTable.getColumns().add(column2);
			    employeeTable.getColumns().add(column3);
			    employeeTable.getColumns().add(column4);
			    employeeTable.getColumns().add(column5);
			    employeeTable.getColumns().add(column6);
			    employeeTable.getColumns().add(column7);
			    column1.setResizable(false);
			    column2.setResizable(false);
			    column3.setResizable(false);
			    column4.setResizable(false);
			    column5.setResizable(false);
			    column6.setResizable(false);
			    column7.setResizable(false);
			    column1.setReorderable(false);
			    column2.setReorderable(false);
			    column3.setReorderable(false);
			    column4.setReorderable(false);
			    column5.setReorderable(false);
			    column6.setReorderable(false);
			    column7.setReorderable(false);
			    column1.setPrefWidth(50);
			    column2.setPrefWidth(80);
			    column3.setPrefWidth(123);
			    column4.setPrefWidth(75);
			    column5.setPrefWidth(70);
			    column6.setPrefWidth(180);
			    column7.setPrefWidth(180);
			    
			// create terminatedError
			Label terminatedMessage = new Label("");
			terminatedMessage.setId("terminated-error");   
	
			// create box for employee list view
			VBox employeeSection = new VBox();
			employeeSection.setId("employee-section");
			employeeSection.getChildren().addAll(employeeLabel, employeeTable);
			
			VBox managerView = new VBox();
			managerView.setId("employee-section");
			managerView.getChildren().addAll(employeeSection, terminatedMessage, actionControls);
			managerRoot.setCenter(managerView);
				
			// configure pay scene
			BorderPane payRoot = new BorderPane();
			Scene payScene = new Scene(payRoot, 800, 500);
			payScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
				Label payLabel = new Label("PAY REPORT:");
				payLabel.setId("employee-section-label");
				
				Button goBackBtn = new Button("Go Back");
				
				// create and configure table				
				TableView<Employee> payTable = new TableView<Employee>();
				payTable.setId("employee-tableview");
				
				// create columns
				TableColumn<Employee, String> column1a = new TableColumn<>("ID");
				TableColumn<Employee, String> column2a = new TableColumn<>("Username");
				TableColumn<Employee, String> column3a = new TableColumn<>("Name");
				TableColumn<Employee, String> column4a = new TableColumn<>("Type");
				TableColumn<Employee, Double> column5a = new TableColumn<>("Base Salary");
				TableColumn<Employee, Double> column6a = new TableColumn<>("Weekly Salary");
				
			    // create cells
				column1a.setCellValueFactory(new PropertyValueFactory<Employee, String>("strID"));
			    column2a.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
			    column3a.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
			    column4a.setCellValueFactory(new PropertyValueFactory<Employee, String>("type"));
			    column5a.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
			    column6a.setCellValueFactory(new PropertyValueFactory<Employee, Double>("paySalary"));
			    
			    // configure table and columns
			    payTable.setMaxHeight(400);
			    payTable.setStyle("-fx-alignment: CENTER");
			    payTable.getColumns().add(column1a);
			    payTable.getColumns().add(column2a);
			    payTable.getColumns().add(column3a);
			    payTable.getColumns().add(column4a);
			    payTable.getColumns().add(column5a);
			    payTable.getColumns().add(column6a);
			    column1a.setResizable(false);
			    column2a.setResizable(false);
			    column3a.setResizable(false);
			    column4a.setResizable(false);
			    column5a.setResizable(false);
			    column6a.setResizable(false);
			    column1a.setReorderable(false);
			    column2a.setReorderable(false);
			    column3a.setReorderable(false);
			    column4a.setReorderable(false);
			    column5a.setReorderable(false);
			    column6a.setReorderable(false);
			    column1a.setPrefWidth(50);
			    column2a.setPrefWidth(80);
			    column3a.setPrefWidth(125);
			    column4a.setPrefWidth(75);
			    column5a.setPrefWidth(225);
			    column6a.setPrefWidth(225);
			    
			    VBox paySection = new VBox(20.0);
			    paySection.setId("employee-section");
			    paySection.getChildren().addAll(payLabel, payTable, goBackBtn);
			    
			    payRoot.setCenter(paySection);
			    
			    payEmployeesBtn.setOnAction(e-> {
			    	primaryStage.setScene(payScene);
			    	primaryStage.setTitle(title + "PAY REPORT");
			    	
			    	ObservableList<Employee> employeeListAll = FXCollections.observableArrayList(pr.employees);
			    	ObservableList<Employee> payList = FXCollections.observableArrayList();
			    	TextInputDialog td = new TextInputDialog("Enter number of hours");
			    	td.setHeaderText(null);

					   try {
				    	for (Employee emp : employeeListAll) {
				    		
				    		if(emp.getClass().getName() == "application.Salaried" && emp.getEndDate() == null) {
				    			emp.setPaySalary(emp.getSalary());
				    			payList.add(emp);
				    		} else if(emp.getClass().getName() == "application.Hourly" && emp.getEndDate() == null) {
			    				td.getEditor().setText("");
				    			td.setContentText("How many hours did " + emp.getUsername() + " work this week?");
				    			td.showAndWait();
				    			String hours = td.getEditor().getText();
				    			Double hoursDouble = Double.parseDouble(hours);
				    			emp.setPaySalary(emp.getPay() * hoursDouble);
				    			payList.add(emp);
				    		}
			    		}
				    } catch (NumberFormatException error) {
				    	Alert payAlert = new Alert(AlertType.ERROR);
				    	payAlert.setHeaderText(null);
				    	payAlert.setContentText("Invalid value entered. Please enter a different value and try again.");
				    	payAlert.showAndWait();
				    	primaryStage.setScene(managerScene);
				    }

					payTable.setItems(payList);
					payTable.refresh();
			    	
			    });
			    
			   // event listeners
			    goBackBtn.setOnAction(e-> {
			    	primaryStage.setScene(managerScene);
			    	primaryStage.setTitle(title + "MANAGER VIEW");
			    });
			    
			submitBtn.setOnAction(e-> {
				System.out.println("\nLOG:\tAttempting login for user " + usernameInput.getText());
				Payroll.username = usernameInput.getText();
				Payroll.password = passwordInput.getText();
				if(Payroll.doLogin(pr.employees, Payroll.username, Payroll.password) == 1) {
					
					// configure stage
					primaryStage.setScene(managerScene);
					usernameInput.clear();
					passwordInput.clear();
					
					// add buttons
					if (Payroll.currentUser.getStrID().equals(Payroll.managerID)) {
						terminateEmployeeBtn.setText("Terminate Employee");
						primaryStage.setTitle(title + "MANAGER VIEW");
						actionBar.getChildren().add(addEmployeeBtn);
						actionBar.getChildren().add(updateEmployeeBtn);
						actionBar.getChildren().add(payEmployeesBtn);
					} else {
						terminateEmployeeBtn.setText("Resign");
						primaryStage.setTitle(title + "EMPLOYEE VIEW");
					}
					if(Payroll.currentUser.getEndDate() == null) {
						actionBar.getChildren().add(terminateEmployeeBtn);
					} else {
						terminatedMessage.setText("You are not currently an employee of this company. "
								+ "If you feel this is a mistake, please contact your manager.");
					}
					actionBar.getChildren().add(logoffBtn);
					
					if(Payroll.currentUser.getStrID().equals(Payroll.managerID)) {
						ObservableList<Employee> employeeListAll = FXCollections.observableArrayList(pr.employees);
						employeeTable.setItems(employeeListAll);
					} else {
						ObservableList<Employee> currentEmployee = FXCollections.observableArrayList();
						currentEmployee.add(Payroll.currentUser);
						ObservableList<Employee> employeeListCurrent = FXCollections.observableArrayList(currentEmployee);
						employeeTable.setItems(employeeListCurrent);
					}
					
				} else {
					passwordInput.clear();
					errorMessage.setText("Incorrect username and/or password. Please try again");
				}
				
			});
			
			addEmployeeBtn.setOnAction(e-> {
				
				Stage newEmployeePopup = new Stage();
				newEmployeePopup.setTitle(title + "CREATE EMPLOYEE");
				newEmployeePopup.setResizable(false);
				Image addIcon = new Image("https://cdn-icons-png.flaticon.com/512/544/544548.png");
				newEmployeePopup.getIcons().add(addIcon);
				
				// create pane
				BorderPane createRoot = new BorderPane();
				
				// create scene
				Scene createScene = new Scene(createRoot, 400, 300);
				createScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				
				Label name = new Label("Full Name:");
				Label user = new Label("Username:");
				Label password1 = new Label("Password:");
				Label password2 = new Label("Re-type Password:");
				Label type = new Label("Employee Type:");
				Label salary = new Label("Salary");
				
				TextField nameValue = new TextField();
				nameValue.setPromptText("Name");
				
				TextField userValue = new TextField();
				userValue.setPromptText("Username");
				
				PasswordField password1Value = new PasswordField();
				password1Value.setPromptText("Password");
				
				PasswordField password2Value = new PasswordField();
				password2Value.setPromptText("Re-type Password");
				
				final ToggleGroup group = new ToggleGroup();
				RadioButton type1Value = new RadioButton("Salaried");
				RadioButton type2Value = new RadioButton("Hourly");
				type1Value.setToggleGroup(group);
				type2Value.setToggleGroup(group);
				type1Value.setSelected(true);
				
				HBox typeButtons = new HBox(10.0);
				typeButtons.setAlignment(Pos.CENTER);
				typeButtons.getChildren().addAll(type1Value, type2Value);
				
				TextField salaryValue = new TextField();
				salaryValue.setPromptText("Salary");
				
				VBox labels = new VBox(20.0);
				labels.setAlignment(Pos.CENTER_RIGHT);
				labels.getChildren().addAll(name, user, password1, password2, type, salary);
				
				VBox fields = new VBox(14.0);
				fields.setAlignment(Pos.CENTER);
				fields.getChildren().addAll(nameValue, userValue, password1Value, password2Value, typeButtons, salaryValue);
				
				HBox inputSection = new HBox(20.0);
				inputSection.setAlignment(Pos.CENTER);
				inputSection.getChildren().addAll(labels, fields);
				
				Button createBtn = new Button("Create Employee");
				
				VBox createControls = new VBox(20.0);
				createControls.setId("scene");
				VBox.setMargin(createControls, new Insets(20,0,25,0));
				createControls.setAlignment(Pos.CENTER);
				createControls.getChildren().addAll(inputSection, createBtn);
				
				createRoot.setCenter(createControls);
				
				newEmployeePopup.setScene(createScene);
				newEmployeePopup.show();
				
				
			createBtn.setOnAction(event-> {
				
				// define conditions
				Boolean allFields = nameValue.getText() != "" && userValue.getText() != "" && salaryValue.getText() != "";
				Boolean passwordValid = password1Value.getText() != "" && (Payroll.encryptPassword(password1Value.getText())
						.equals(Payroll.encryptPassword(password2Value.getText())));
				int results = 1;
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				
				if(!allFields) {
					alert.setContentText("Please ensure all fields are filled in and try again.");
					alert.showAndWait();
				} else if(!passwordValid) {
					alert.setContentText("Password fields are either empty or do not match. Please try again.");
					alert.showAndWait();
				} else {
					if(type2Value.isSelected()) {
						Payroll.employeeType = 1;
					} else {
						Payroll.employeeType = 0;
					}
					
				results = Payroll.addEmployee(pr.employeeFile, pr.employees, nameValue.getText(), 
						userValue.getText(), salaryValue.getText());
				
				
				if(results == -1) {
					alert.setContentText("Data format error. Please ensure that all data is correctly formatted and try again.");
				} else if (results == 0) {
					alert.setContentText("Username already taken. Please enter another one and try again.");
				} else {
					alert.setAlertType(AlertType.INFORMATION);
					alert.setContentText("User successfully created.");
					newEmployeePopup.close();
				}
				employeeTable.refresh();
				alert.showAndWait();
				
				}
				
			});
				
				employeeTable.refresh();
			});
			
			updateEmployeeBtn.setOnAction(e-> {
				System.out.println("\nLOG:\tUpdating employee...");
				
				// create popup stage
				Stage updatePopup = new Stage();
				updatePopup.setTitle(title + "UPDATE EMPLOYEE");
				updatePopup.setResizable(false);
				Image updateIcon = new Image("https://cdn0.iconfinder.com/data/icons/people-126/100/people-11-512.png");
				updatePopup.getIcons().add(updateIcon);
				
				// create pane
				BorderPane updateRoot = new BorderPane();
				
			// create update screen
			Scene updateScene = new Scene(updateRoot, 400, 300);
			updateScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Alert updateAlert = new Alert(AlertType.ERROR);
			updateAlert.setHeaderText(null);
			updateAlert.setContentText("No employee selected. Please select an employee and try again.");
				try {
					Label instructions = new Label("To update an employee, enter a value. Blank values will not be updated.");
				
					VBox labels = new VBox(20.0);
					labels.setAlignment(Pos.CENTER_RIGHT);
					Label name = new Label("Full Name:");
					Label salary = new Label("Salary:");
					labels.getChildren().addAll(name, salary);
					
					VBox values = new VBox(10.0);
					values.setAlignment(Pos.CENTER);
					TextField nameValue = new TextField();
					nameValue.setPromptText(Payroll.selectedEmployee.getName());
					TextField salaryValue = new TextField();
					salaryValue.setPromptText(Payroll.selectedEmployee.getSalary().toString());
					values.getChildren().addAll(nameValue, salaryValue);
					
					HBox fields = new HBox(20.0);
					fields.setAlignment(Pos.CENTER);
					fields.getChildren().addAll(labels, values);
					
					Button updateBtn = new Button("Update Employee");
					
					VBox updateControls = new VBox();
					updateControls.setId("scene");
					VBox.setMargin(fields, new Insets(20,0,25,0));
					updateControls.setAlignment(Pos.CENTER);
					updateControls.getChildren().addAll(instructions, fields, updateBtn);
					
					
					updateRoot.setCenter(updateControls);
					
					updatePopup.setScene(updateScene);
					updatePopup.show();

				
				updateBtn.setOnAction(event-> {
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("");
					
					
					int results = Payroll.updateEmployee(pr.employees, nameValue.getText(), salaryValue.getText());
					
					if(results == -1) {
						alert.setContentText("Invalid salary value. Please enter a different value and try again.");
						alert.showAndWait();
					} else if(results == 0) {
						alert.setContentText("No updates detected. Please enter a value and try again.");
						alert.showAndWait();
					} else {
						updatePopup.close();
						alert.setAlertType(AlertType.INFORMATION);
						alert.setContentText("User successfully updated.");
						alert.showAndWait();
					}
					
					employeeTable.refresh();
					Payroll.printToFile(pr.employees, pr.employeeFile);
					
				});

				} catch (NullPointerException exc) {
					updateAlert.showAndWait();
				}
				System.out.println(Payroll.currentUser.toString());
				employeeTable.refresh();
			});
			
			terminateEmployeeBtn.setOnAction(e-> {
				Payroll.terminateEmployee(pr.employees, pr.exEmployees, pr.employeeFile);
				employeeTable.refresh();
			});
			
			employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
				if(newSelection != null) {
					Payroll.selectedEmployee = newSelection;
				} else {
					Payroll.selectedEmployee = Payroll.currentUser;
				}
				
				System.out.println("\nLOG:\tUser " + Payroll.selectedEmployee.getUsername() + " selected.");
			});
			
			logoffBtn.setOnAction( e-> {
				System.out.println("\nLOG:\tUser " + Payroll.currentUser.getUsername() + " logged out.");
				primaryStage.setTitle(title + "LOG IN");
				errorMessage.setText("");
				terminatedMessage.setText("");
				actionBar.getChildren().clear();
				Payroll.selectedEmployee = null;
				Payroll.currentUser = null;
				primaryStage.setScene(loginScene);
			});
			
					
			// configure primary stage, launch
			Image icon = new Image("https://cdn-icons-png.flaticon.com/512/4974/4974985.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(loginScene);
			primaryStage.setResizable(false);
			primaryStage.show();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		// declare project control class, print banner
		ProjectControl pc = new ProjectControl("P6_Payroll_GUI", "May 11th, 2023");
		pc.banner();
		
		// create new payroll
		//Payroll payroll = new Payroll();
		
		// launch GUI
		launch(args);
		
		// close scanner, terminate program
		pc.bye();
		
	}
}
