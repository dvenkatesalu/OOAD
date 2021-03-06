package controllers;

import dao.DaoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Instructor;

/**
 * InstructorController.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 1. 2018
 */
public class InstructorController 
{

	@FXML
	private ChoiceBox<String> Studentlist;
	@FXML
	private TextField passcode;
	@FXML 
	private Label errLbl;
	@FXML
	private Button genPasscodeBtn;
	@FXML
	private TextField grdeTxtFld;
	
	private DaoModel db;
	private Instructor i;
	
	ObservableList<String> studentIdList;
	/**
	 * 
	 */
	public InstructorController() 
	{
		db = new DaoModel();
		i = new Instructor();
	}
	
	public void initialize()
	{
		studentIdList = FXCollections.observableArrayList(db.getStudentListForACourse(i));
		Studentlist.setItems(studentIdList);
	}
	
	public void generatePassCode()
	{
		errLbl.setText("");
		String passCode = passcode.getText().trim();
		if( passCode == null || passCode == "" )
		{
			errLbl.setText("Passcode cannot be empty!");
			return;
		}
		if( !db.generatePasscodeForAttendance(passCode, i) )
		{
			errLbl.setText("Error in generating passcode. Please try again");
		}
		else
		{
			errLbl.setText("Passcode generated succesfully");
		}
	}
	
	public void enterGrade()
	{
		errLbl.setText("");
		String grade = grdeTxtFld.getText().trim();
		if( grade == null || grade == "" || grade.length() > 1)
		{
			errLbl.setText("Please enter proper grades");
			return;
		}
		String cwid = Studentlist.getValue();
		if( cwid == null || cwid == "" )
		{
			errLbl.setText("Please select a student id.");
			return;
		}
		
		if( !db.enterGrades(grade.charAt(0), cwid, i.ccode) )
		{
			errLbl.setText("Error while entering grades. Please try again");
		}
		else
		{
			errLbl.setText("Grade entered succesfully");
		}
	}

}
