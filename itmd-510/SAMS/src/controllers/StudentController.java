package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import application.Main;
import dao.DaoModel;

/**
 * StudentController.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 1, 2018
 */
public class StudentController 
{
	@FXML 
	private Label nameTagLbl;
	
	//tab 1
	@FXML
	private Tab crse1Tab;
	@FXML
	private Label crse1ProfessorName;
	@FXML
	private Label crse1ProfMailId;
	@FXML
	private Label crse1TAName;
	@FXML
	private Label crse1TAMailId;
	@FXML
	private Label crse1Nme;
	
	//tab 2
	@FXML
	private Tab crs2Tab;
	@FXML
	private Label crse2ProfessorName;
	@FXML
	private Label crse2ProfMailId;
	@FXML
	private Label crse2TAName;
	@FXML
	private Label crse2TAMailId;
	@FXML
	private Label crse2Nme;
	
	//tab 3
	@FXML
	private Tab crs3Tab;
	@FXML
	private Label crse3ProfessorName;
	@FXML
	private Label crse3ProfMailId;
	@FXML
	private Label crse3TAName;
	@FXML
	private Label crse3TAMailId;
	@FXML
	private Label crse2Nme1;
	
	private DaoModel db;

	/**
	 * 
	 */
	public StudentController() 
	{
		db = new DaoModel();
	}

	public void initialize()
	{
		db.fetchStudentDetails();
		nameTagLbl.setText(Main.personObject.fName + " " + Main.personObject.mName + " " + Main.personObject.lName);
		//Tab1
		crse1Tab.setText(Main.personObject.courses.get(0).ccode);
		crse1Nme.setText(Main.personObject.courses.get(0).cname);
		System.out.println("IName in Controller : " + Main.personObject.courses.get(0).instructorName);
		crse1ProfessorName.setText(Main.personObject.courses.get(0).instructorName);
		crse1ProfMailId.setText(Main.personObject.courses.get(0).iemail);
		crse1TAName.setText(Main.personObject.courses.get(0).taName);
		crse1TAMailId.setText(Main.personObject.courses.get(0).temail);
		
		//Tab2
		System.out.println("IName2 in Controller : " + Main.personObject.courses.get(1).instructorName);
		crs2Tab.setText(Main.personObject.courses.get(1).ccode);
		crse2Nme.setText(Main.personObject.courses.get(1).cname);
		crse2ProfessorName.setText(Main.personObject.courses.get(1).instructorName);
		crse2ProfMailId.setText(Main.personObject.courses.get(1).iemail);
		crse2TAName.setText(Main.personObject.courses.get(1).taName);
		crse2TAMailId.setText(Main.personObject.courses.get(1).temail);
		
		//Tab3
		crs3Tab.setText(Main.personObject.courses.get(2).ccode);
		crse2Nme1.setText(Main.personObject.courses.get(2).cname);
		crse3ProfessorName.setText(Main.personObject.courses.get(2).instructorName);
		crse3ProfMailId.setText(Main.personObject.courses.get(2).iemail);
		crse3TAName.setText(Main.personObject.courses.get(2).taName);
		crse3TAMailId.setText(Main.personObject.courses.get(2).temail);
		
	}
}
