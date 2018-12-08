/**
 * 
 */
package controllers;
import application.Main;
import dao.DaoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Instructor;


/**
 * @author Admin
 *
 */
public class AdminInstructorDetailsController {

	@FXML
	private TextField fNameTxtFld,mNameTxtFld,lNameTxtFld,cwidTxtFld,emailTxtFld,courseIDTxtFld;
	@FXML
	private Button okBTN , enterBTN;
	@FXML
	private ChoiceBox<String> crseDrpDwn;
	@FXML
	private Label deptLbl, errLbl;
	
	private DaoModel db;
	/**
	 * 
	 */
	public AdminInstructorDetailsController() 
	{
		db = new DaoModel();
		
	}
	
	public void initialize() 
	{
		System.out.println("Inside View initialize : dept : " + Main.adminObject.dept);
		deptLbl.setText(Main.adminObject.dept);
		ObservableList<String> courseList = FXCollections.observableArrayList(db.getCourseListPerDept(Main.adminObject.dept));
		crseDrpDwn.setItems(courseList);
		okBTN.setVisible(false);
	}
	
	public void enterInstructorDetails()
	{
		errLbl.setText("");
		Instructor i = new Instructor();
		i.fname = fNameTxtFld.getText().trim();
		i.mname = mNameTxtFld.getText().trim();
		i.lname = lNameTxtFld.getText().trim();
		i.cwid = cwidTxtFld.getText().trim();
		i.dept = Main.adminObject.dept;
		i.email = emailTxtFld.getText().trim();
		i.ccode = crseDrpDwn.getValue();
		
		System.out.print( i.fname + i.lname + i.ccode + i.cwid + i.dept + i.email );
		
		if( i.fname == "" || i.fname == null || i.lname == "" || i.lname == null || i.cwid == "" || i.cwid == null 
				|| i.email == "" || i.email == null || i.ccode == null || i.ccode == "" )
		{
				errLbl.setText("Fields cannot be empty");
				return;
		}
		
		if( db.enterInstructorDetailsByAdmin( i ) )
		 {
			 errLbl.setText("Entered details succesfully!");
			 enterBTN.setVisible(false);
			 okBTN.setVisible(true);			 
		 }
		 else
		 {
			 errLbl.setText("Entered details unsuccesful!");
			 enterBTN.setVisible(false);
			 okBTN.setVisible(true);
		 }
	}
	
	public void goback()
	{
		new Main().updateScene(Main.ADMINVIEW);
	}

}
