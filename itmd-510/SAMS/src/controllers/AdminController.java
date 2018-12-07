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
	private ObservableList<String> cwidList = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<String> actionDrpDwn;
	@FXML
	private ComboBox<String> targetDrpDwn;
	@FXML
	private ComboBox<String> functionDrpDwn;
	@FXML
	private Button ftchDtls;
	@FXML
	private Label errLbl;
	
	@FXML
	private AnchorPane adminGoView;
	@FXML
	private ComboBox<String> deptDrpDwn;
	@FXML 
	private ChoiceBox<String> cwidDrpDwn;
	@FXML 
	private Button enblAction;
	
	private AdminModel admin;
	
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
		cwidDrpDwn.setItems(emptyList);
		
		adminGoView.setVisible(false);
	}
	
	public void fetchDetails()
	{	
		errLbl.setText("");
		String action = actionDrpDwn.getValue();
		String target = targetDrpDwn.getValue();
		String function = functionDrpDwn.getValue();
		
		
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
		
		cwidDrpDwn.setVisible(false);
		adminGoView.setVisible(true);
		
		String dept = deptDrpDwn.getValue();
		if( dept == null || dept == "" )
		{
			errLbl.setText("Please choose a dept");
			return;
		}
		
		if( target.equals("Course"))
		{
			//get course ids from the course table
		}
		
		else
		{
			//if( target.equals(arg0))
			cwidList = FXCollections.observableArrayList(admin.getCWIDs(dept, target));
			cwidDrpDwn.setItems(cwidList);;
			cwidDrpDwn.setVisible(true);
		}
	}
	
	public void setDeptDrpDwn()
	{
		
	}
	
	public void setCwidDrpdwnValues()
	{
		cwidDrpDwn.setItems(cwidList);
	}
	
	public void fetchFurtherDetails()
	{
		
	}

}
