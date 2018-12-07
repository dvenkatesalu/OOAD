package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.AdminModel;

/**
 * AdminController.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 01, 2018
 */
public class AdminController 
{
	private ObservableList<String> actionList = FXCollections.observableArrayList("View","Modify","Delete","Enter");
	private ObservableList<String> targetList = FXCollections.observableArrayList("Student", "Instructor", "Course");
	private ObservableList<String> functionList = FXCollections.observableArrayList("Details","Schedule");
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
	
	
	private AdminModel admin;
	private String target, dept;
	/**
	 * 
	 */
	public AdminController() 
	{
		admin = new AdminModel();
	}
	
	public void initialize()
	{		
		actionDrpDwn.setItems(actionList);
		targetDrpDwn.setItems(targetList);
		functionDrpDwn.setItems(functionList);
		deptDrpDwn.setItems(deptList);
		idDrpDwn.setItems(emptyList);
		
		idViewPane.setVisible(false);
		
		target = "";
		dept = "";
	}
	
	public void setDeptDrpDwn()
	{	
		errLbl.setText("");
		String action = actionDrpDwn.getValue();
		target = targetDrpDwn.getValue();
		String function = functionDrpDwn.getValue();
		dept = deptDrpDwn.getValue();
		
		if( action == null || action == "" )
		{
			errLbl.setText("Please choose an action");
			return;
		}
		else if( target == null || target == "" )
		{
			errLbl.setText("Please choose a target");
			return;
		}
		else if( function == null || function == "" )
		{
			errLbl.setText("Please choose a function");
			return;
		}
		else if( dept == null || dept == "" )
		{
			errLbl.setText("Please choose a dept");
			return;
		}
		System.out.println("In fetch details ");
		System.out.println(dept + " : " + target );
		setIdDrpdwnValues();
	}
	
	public void setIdDrpdwnValues()
	{
		errLbl.setText("");
		System.out.println("In drpDwn details ");
		System.out.println(dept + " : " + target );
		idViewPane.setVisible(true);
				
		if( target.equals("Course"))
		{
			idLbl.setText("Course ID");
			idList = FXCollections.observableArrayList(admin.getCourseIDs(dept));
		}
		
		else
		{
			idLbl.setText("CWID");
			idList = FXCollections.observableArrayList(admin.getCWIDs(dept, target));
		}
		
		if( idList.size() > 0 )
			idDrpDwn.setItems(idList);
		else
			errLbl.setText("No ids exist please try another option.");
	}
	
	public void fetchFurtherDetails()
	{
		//call update view to set next view
	}

}
