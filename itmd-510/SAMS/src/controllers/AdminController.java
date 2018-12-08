package controllers;

import java.util.Optional;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Admin;
import models.AdminModel;

/**
 * AdminController.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 01, 2018
 */
public class AdminController 
{
	private ObservableList<String> actionList = FXCollections.observableArrayList("View","Delete","Enter");
	private ObservableList<String> targetList = FXCollections.observableArrayList("Student", "Instructor", "Course");
	private ObservableList<String> functionList = FXCollections.observableArrayList("Details");
	private ObservableList<String> deptList = FXCollections.observableArrayList("ITM", "MMAE", "ECE", "CSE");
	private ObservableList<String> emptyList = FXCollections.observableArrayList();
	private ObservableList<String> idList = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> actionDrpDwn;
	@FXML
	private ComboBox<String> targetDrpDwn;
	@FXML
	private ComboBox<String> functionDrpDwn;
	@FXML
	private Label errLbl;
	@FXML
	private ComboBox<String> deptDrpDwn;
	@FXML 
	private Button fetchidBtn;
	
	@FXML
	private AnchorPane idViewPane;
	@FXML
	private Label idLbl;
	@FXML 
	private ChoiceBox<String> idDrpDwn;
	@FXML 
	private Button finalGoBtn;
	@FXML
	private DialogPane delPane;	
	
	private AdminModel admin;
	//private String target, dept;
	/**
	 * 
	 */
	public AdminController() 
	{
		admin = new AdminModel();
	}
	
	public void initialize()
	{		
		Main.adminObject = new Admin();
		actionDrpDwn.setItems(actionList);
		targetDrpDwn.setItems(targetList);
		functionDrpDwn.setItems(functionList);
		deptDrpDwn.setItems(deptList);
		idDrpDwn.setItems(emptyList);
		
		idViewPane.setVisible(false);
		delPane.setVisible(false);
	}
	
	public void setDeptDrpDwn()
	{	
		errLbl.setText("");
		Main.adminObject.action = this.actionDrpDwn.getValue();
		System.out.println("Action value : " + this.actionDrpDwn.getValue());
		Main.adminObject.target = targetDrpDwn.getValue();
		Main.adminObject.function = functionDrpDwn.getValue();
		Main.adminObject.dept = deptDrpDwn.getValue();
		
		if( Main.adminObject.action == null || Main.adminObject.action == "" )
		{
			errLbl.setText("Please choose an action");
			return;
		}
		else if( Main.adminObject.target == null || Main.adminObject.target == "" )
		{
			errLbl.setText("Please choose a target");
			return;
		}
		else if( Main.adminObject.function == null || Main.adminObject.function == "" )
		{
			errLbl.setText("Please choose a function");
			return;
		}
		else if( Main.adminObject.dept == null || Main.adminObject.dept == "" )
		{
			errLbl.setText("Please choose a dept");
			return;
		}
		System.out.println(Main.adminObject.dept + " : " + Main.adminObject.target );
		System.out.println("Main object action : " + Main.adminObject.action);
		if( Main.adminObject.action.equals("Enter") )
		{
			fetchFurtherDetails();
			return;
		}
		setIdDrpdwnValues();
	}
	
	public void setIdDrpdwnValues()
	{
		errLbl.setText("");
		System.out.println("In drpDwn details ");
		System.out.println(Main.adminObject.dept + " : " + Main.adminObject.target + actionDrpDwn.getValue());
		idViewPane.setVisible(true);
				
		if( Main.adminObject.target.equals("Course"))
		{
			idLbl.setText("Course ID");
			idList = FXCollections.observableArrayList(admin.getCourseIDs(Main.adminObject.dept));
		}
		
		else
		{
			idLbl.setText("CWID");
			idList = FXCollections.observableArrayList(admin.getCWIDs(Main.adminObject.dept, Main.adminObject.target));
		}
		
		if( idList.size() > 0 )
			idDrpDwn.setItems(idList);
		else
			errLbl.setText("No ids exist please try another option.");
	}
	
	public void fetchFurtherDetails()
	{
		if( Main.adminObject.action.equals("Delete") )
		{
			delPane.setVisible(true);
		}
		
		if( Main.adminObject.target.equals("Student"))
		{
			Main.adminObject.cwid = idDrpDwn.getValue();
			new Main().updateScene(Main.ADMINSTUDENTVIEW);
		}
		else if( Main.adminObject.target.equals("Instructor"))
		{
			Main.adminObject.cwid = idDrpDwn.getValue();
			new Main().updateScene(Main.ADMININSTRUCTORVIEW);
		}
		else if( Main.adminObject.target.equals("Course"))
		{
			Main.adminObject.ccode = idDrpDwn.getValue();
			new Main().updateScene(Main.ADMINCOURSEVIEW);
		}
	}

}
