/**
 * 
 */
package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.Person;

/**
 * @author Dharanip Priya
 *
 */
public class AdminStudentDetailsController {

	@FXML
	private TextField fNameTxtFLd;
	@FXML
	private TextField mNameTxtFLd;
	@FXML
	private TextField lNameTxtFLd;
	@FXML 
	private TextField cwidTxtFld;
	@FXML
	private Label deptLbl;
	@FXML 
	private ChoiceBox<String> deptDrpDwn;
	@FXML
	private TextField mailIDTxtFld;
	@FXML
	private TextField currentTermTxtFld;
	@FXML
	private TextField ugpgTxtFld;
	@FXML
	private RadioButton taYesRdoBtn;
	@FXML 
	private RadioButton taNoRdoBtn;
	@FXML
	private ChoiceBox<String> crse1DrpDwn;
	@FXML
	private ChoiceBox<String> crse2DrpDwn;
	@FXML
	private ChoiceBox<String> crse3DrpDwn;
	
	/**
	 * 
	 */
	public AdminStudentDetailsController() 
	{
		
	}
	
	public void intialize()
	{
		switch( Main.adminObject.function )
		{
		case "Enter" : enterDetails();
						break;
		}
	}
	
	public void enterDetails()
	{
		Person p = new Person();
		p.fName = fNameTxtFLd.getText().trim();
		p.lName = lNameTxtFLd.getText().trim();
		p.mName = mNameTxtFLd.getText().trim();
		p.cwid = cwidTxtFld.getText().trim();
		p.dept = deptDrpDwn.getValue();
		
	}
	
	public void viewDetails()
	{
		Person P;
		
	}
	
	public void setDisbaled()
	{
		
	}

}
