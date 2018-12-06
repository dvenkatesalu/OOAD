package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	
	@FXML
	private ComboBox<String> actionDrpDwn;
	@FXML
	private ComboBox<String> targetDrpDwn;
	@FXML
	private ComboBox<String> functionDrpDwn;
	@FXML
	private Button ftchDtls;
	
	@FXML
	private AnchorPane adminGoView;
	@FXML
	private ComboBox<String> deptDrpDwn;
	@FXML 
	private ComboBox<String> cwidDrpDwn;
	
	private AdminModel admin;
	
	/**
	 * 
	 */
	public AdminController() 
	{
		admin = new admin();
	}
	
	public void initialize()
	{
		actionDrpDwn.setItems(actionList);
		targetDrpDwn.setItems(targetList);
		functionDrpDwn.setItems(functionList);
		deptDrpDwn.setItems(deptList);
		adminGoView.setVisible(false);
	}
	
	public void fetchDetails()
	{
		//Check for null or empty before proceeding
		adminGoView.setVisible(true);
		
		if( targetDrpDwn.getAccessibleText().equals("Course"))
		{
			
		}
		
		else
		{
			ObservableList<String> cwidList = FXCollections.observableArrayList(admin.getCWIDs(deptDrpDwn.getAccessibleText(), targetDrpDwn.getAccessibleText()));
			cwidDrpDwn.setItems(cwidList);
		}
	}

}
