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
import models.Person;
import models.RegisterModel;

/**
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30,2018
 */
public class RegisterController 
{

	private ObservableList<String> deptList = FXCollections.observableArrayList("ITM", "MMAE", "ECE", "CSE");;
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
	
	private Person p;
	private RegisterModel r;
	/**
	 * Constructor to initialize default values
	 */
	public RegisterController() 
	{
		p = new Person();
		r = new RegisterModel();
	}
	
	public void initialize()
	{
		deptChoice.setItems(deptList);
	}
	
	public void updateDetails()
	{
		p.fName = fName.getText();
		p.mName = this.mName.getText();
		p.lName = this.lName.getText();
		p.emailid = this.emailId.getText();
		p.cwid = this.cwid.getText();
		p.dept = this.deptChoice.getValue();
		p.password = this.pwdFld.getText();
		String pwd2 = this.pwdReEnterFld.getText();
		
		if( p.fName == null || p.fName.trim().equals("") )
		{
			this.errorLbl.setText("*First Name should not be empty");
			return;
		}
		
		if( p.lName == null || p.lName.trim().equals("") )
		{
			this.errorLbl.setText("*Last Name should not be empty");
			return;
		}
		
		if( p.emailid == null || p.emailid.trim().equals("") )
		{
			this.errorLbl.setText("*Email should not be empty");
			return;
		}
		
		if( p.cwid == null || p.cwid.trim().equals("") )
		{
			this.errorLbl.setText("*cwid should not be empty");
			return;
		}
		
		if( p.dept == null || p.dept.trim().equals("") )
		{
			this.errorLbl.setText("*Please choose a department");
			return;
		}
		
		if( p.password == null || p.password.trim().equals(""))
		{
			this.errorLbl.setText("*Password cannot be empty");
			return;
		}
		
		if( pwd2== null || pwd2.trim().equals("") || !(pwd2.equals(p.password)))
		{
			this.errorLbl.setText("*Passwords dont match");
			return;
		}
		
		
		int idx = p.emailid.indexOf("@");
		String mailDomain = p.emailid.substring(idx+1);
		
		if( mailDomain.equals("hawk.iit.edu"))
		{
			p.isStudent = true;
		}
		else if(mailDomain.equals("iit.edu"))
		{
			p.isInstructor = true;
		}
		else if(mailDomain.equals("a.iit.edu"))
		{
			p.isAdmin = true;
		}
		else
		{
			this.errorLbl.setText("*Please enter a valid mail ID associated with the university");
			return;
		}
		
		r.enterDetails(p);
		System.out.println("Details entered successfully!");
		new Main().updateScene(Main.LOGINVIEW);
		
	}

	public void prevLoginScreen()
	{
		new Main().updateScene(Main.LOGINVIEW);
	}
}
