package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import models.RegisterModel;

/**
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30,2018
 */
public class RegisterController 
{

	private ObservableList<String> deptList = FXCollections.observableArrayList("ITM", "MMAE", "ECE", "CSE");
	@FXML
	private TextField fName;
	@FXML
	private TextField mName;
	@FXML
	private TextField lName;
	@FXML
	private TextField emailId;
	@FXML 
	private TextField cwid;
	@FXML
	private Label errorLbl;
	@FXML 
	private Button submitBtn;
	@FXML
	private Button cancelBtn;
	@FXML
	private ChoiceBox<String> deptChoice;
	@FXML
	private PasswordField pwdFld;
	@FXML
	private PasswordField pwdReEnterFld;
	
	private RegisterModel r;
	/**
	 * Constructor to initialize default values
	 */
	public RegisterController() 
	{
		r = new RegisterModel();
	}
	
	public void initialize()
	{
		deptChoice.setItems(deptList);
	}
	
	public void updateDetails()
	{
		this.errorLbl.setText("");
		Main.personObject.fName = fName.getText();
		Main.personObject.mName = this.mName.getText();
		Main.personObject.lName = this.lName.getText();
		Main.personObject.emailid = this.emailId.getText();
		Main.personObject.cwid = this.cwid.getText();
		Main.personObject.dept = this.deptChoice.getValue();
		Main.personObject.password = this.pwdFld.getText();
		String pwd2 = this.pwdReEnterFld.getText();
		
		if( Main.personObject.fName == null || Main.personObject.fName.trim().equals("") )
		{
			this.errorLbl.setText("*First Name should not be empty");
			return;
		}
		
		if( Main.personObject.lName == null || Main.personObject.lName.trim().equals("") )
		{
			this.errorLbl.setText("*Last Name should not be empty");
			return;
		}
		
		if( Main.personObject.emailid == null || Main.personObject.emailid.trim().equals("") )
		{
			this.errorLbl.setText("*Email should not be empty");
			return;
		}
		
		if( Main.personObject.cwid == null || Main.personObject.cwid.trim().equals("") )
		{
			this.errorLbl.setText("*cwid should not be empty");
			return;
		}
		
		if( Main.personObject.dept == null || Main.personObject.dept.trim().equals("") )
		{
			this.errorLbl.setText("*Please choose a department");
			return;
		}
		
		if( Main.personObject.password == null || Main.personObject.password.trim().equals(""))
		{
			this.errorLbl.setText("*Password cannot be empty");
			return;
		}
		
		if( pwd2== null || pwd2.trim().equals("") || !(pwd2.equals(Main.personObject.password)))
		{
			this.errorLbl.setText("*Passwords dont match");
			return;
		}
		
		
		int idx = Main.personObject.emailid.indexOf("@");
		String mailDomain = Main.personObject.emailid.substring(idx+1);
		
		if( mailDomain.equals("hawk.iit.edu"))
		{
			Main.personObject.isStudent = true;
		}
		else if(mailDomain.equals("iit.edu"))
		{
			Main.personObject.isInstructor = true;
		}
		else if(mailDomain.equals("a.iit.edu"))
		{
			Main.personObject.isAdmin = true;
		}
		else
		{
			this.errorLbl.setText("*Please enter a valid mail ID associated with the university");
			return;
		}
		
		if( r.enterDetails(Main.personObject) )
		{
			System.out.println("Details entered successfully!");
			prevLoginScreen();
		}
		
		else
		{
			System.out.println("Problem with registration!");
			this.errorLbl.setText("There was an issue with registration. Please try again.");
		}
		prevLoginScreen();
	}

	public void prevLoginScreen()
	{
		new Main().updateScene(Main.LOGINVIEW);
	}
}
