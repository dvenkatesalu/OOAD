/**
 * 
 */
package controllers;

import application.Main;
import dao.DaoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.NewCourseDetails;

/**
 * @author Dharanip Priya
 *
 */
public class AdminCourseDetailsController 
{

	@FXML
	private TextField ccodeTextFld;
	@FXML
	private TextField cNametxtFLd;
	@FXML
	private TextField schedTextFld;
	@FXML
	private Label deptLbl;
	@FXML
	private Label errLbl;
	@FXML
	private Button backBtn;
	@FXML
	private Button entrCrseDtlsBtn;
	
	DaoModel db;
	private ObservableList<String> deptList = FXCollections.observableArrayList("ITM", "MMAE", "ECE", "CSE");
	/**
	 * 
	 */
	public AdminCourseDetailsController() 
	{
		db = new DaoModel();
	}
	
	public void initialize()
	{
		System.out.println("Main object action : " + Main.adminObject.function.equals("Enter"));
		if( Main.adminObject.action.equals("Enter"))
		{
			deptLbl.setText(Main.adminObject.dept);
			backBtn.setVisible(false);
		}
		
		else if( Main.adminObject.action.equals("View"))
		{
			backBtn.setVisible(true);
			entrCrseDtlsBtn.setVisible(false);
			disableFields();
		}
	}
	
	private void disableFields()
	{
		ccodeTextFld.setEditable(false);
		cNametxtFLd.setEditable(false);
		deptLbl.setVisible(true);
		schedTextFld.setEditable(false);
		NewCourseDetails c = new NewCourseDetails();
		c = db.getCourseDetails(Main.adminObject.ccode);
		ccodeTextFld.setText(c.ccode);
		cNametxtFLd.setText(c.cName);
		deptLbl.setText(c.dept);
		schedTextFld.setText(c.schedule);
	}
	
	public void enterDetailsIntoDb()
	{
		NewCourseDetails c = new NewCourseDetails();
		
		c.ccode = ccodeTextFld.getText().trim();
		c.cName = cNametxtFLd.getText().trim();
		c.schedule = schedTextFld.getText().trim();
		
		if( c.ccode == null || c.ccode == "" )
		{
			errLbl.setText("Course code cannot be empty!");
		}
		else if( c.cName == null || c.cName == "" )
		{
			errLbl.setText("Course Name cannot be empty!");
		}
		else if( c.schedule == null || c.schedule == "" )
		{
			errLbl.setText("Course schedule cannot be empty!");
		}
		if( !db.enterCourseDetails(c) )
		{
			errLbl.setText("Error while entering course details. Please try again.");
			return;
		}
		else
		{
			errLbl.setText("Course details entered successfully!");
			backBtn.setVisible(true);
		}
	}
	
	public void goBack()
	{
		new Main().updateScene(Main.ADMINVIEW);
	}

}
