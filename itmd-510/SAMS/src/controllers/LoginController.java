package controllers;

import application.Main;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.LoginModel;
import models.Person;
import javafx.fxml.FXML;

/**
 * LoginController.java
 * @author Dharanip Priya
 * @since Nov 29, 2018
 */
public class LoginController
{
	@FXML
	private TextField unameTxtFld;
	@FXML
	private PasswordField pwdFld;
	@FXML 
	private Label errLbl;
	
	private LoginModel login;

	/**
	 * 
	 */
	public LoginController() 
	{
		login = new LoginModel();
		Main.personObject = new Person();
	} //End of constructor
	
	public void login()
	{
		this.errLbl.setText("");
		String mailId = this.unameTxtFld.getText();
		String pwd = this.pwdFld.getText();
		
		mailId.trim();
		pwd.trim();
		
		if( ( mailId == null || mailId.equals("") ) &&
				( pwd == null || pwd.equals("") ) )
		{
			this.errLbl.setText("EMail ID and Password cannot be empty");
			return;
		}
		
		else if( mailId == null || mailId.equals("") )
		{
			this.errLbl.setText("EMail ID cannot be empty");
			return;
		}
		
		else if( pwd == null || pwd.equals("") )
		{
			this.errLbl.setText("Password cannot be empty");
			return;
		}
		
		checkCredentialsAndChangeView( mailId, pwd );		
		
	}
	
	private void checkCredentialsAndChangeView( String email, String pwd )
	{		if( !( login.chckCredentials( email, pwd, Main.personObject ) ) )
		{
			this.errLbl.setText("EMail ID or Password is Incorrect. Please try again."); //Implement 3 tries
			return;
		}
		
		if( Main.personObject.isAdmin )
		{
			new Main().updateScene(Main.ADMINVIEW);
		}
		
		else if( Main.personObject.isInstructor )
		{
			new Main().updateScene(Main.INSTRUCTORVIEW);
		}
		
		else if( Main.personObject.isStudent )
		{
			new Main().updateScene(Main.STUDENTVIEW);
		}
		
	}
	
	public void register()
	{
		new Main().updateScene(Main.REGISTERVIEW);
	}

} //End of class
