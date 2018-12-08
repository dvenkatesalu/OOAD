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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import models.Course;
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
	private TextField mailIDTxtFld;
	@FXML
	private TextField currentTermTxtFld;
	@FXML
	private TextField ugpgTxtFld;
	
	@FXML
	private ChoiceBox<String> crse1DrpDwn, taDrpDwn;
	@FXML
	private ChoiceBox<String> crse2DrpDwn;
	@FXML
	private ChoiceBox<String> crse3DrpDwn;
	@FXML
	private Label errLbl;
	@FXML 
	private Button enterBtn, okBtn;
	
	private DaoModel db;
	/**
	 * 
	 */
	public AdminStudentDetailsController() 
	{
		db = new DaoModel();
	}
	
	public void initialize()
	{
		System.out.println("Dept in AdminStudentController : " + Main.adminObject.dept );
		System.out.println("Action in AdminStudentController : " + Main.adminObject.action );
		if( Main.adminObject.action.equals("Enter"))
		{
			System.out.println("Dept in AdminStudentController : " + Main.adminObject.dept );
						deptLbl.setText(Main.adminObject.dept);
						//cwidTxtFld.setText(Main.adminObject.cwid);
						ObservableList<String> courseList = FXCollections.observableArrayList(db.getCourseListPerDept(Main.adminObject.dept));
						crse1DrpDwn.setItems(courseList);
						crse2DrpDwn.setItems(courseList);
						crse3DrpDwn.setItems(courseList);
						ObservableList<String> taOptions = FXCollections.observableArrayList("Yes","No");
						taDrpDwn.setItems(taOptions);
		}
	}
	
	public void enterDetails()
	{
		
		errLbl.setText("");
		Person p = new Person();
		p.fName = fNameTxtFLd.getText().trim();
		p.lName = lNameTxtFLd.getText().trim();
		p.mName = mNameTxtFLd.getText().trim();
		p.cwid = cwidTxtFld.getText().trim();
		p.dept = Main.adminObject.dept;
		p.email = mailIDTxtFld.getText().trim();
		p.currTerm = currentTermTxtFld.getText().trim();
		p.level = ugpgTxtFld.getText().trim();
		/*if( taNoRdoBtn.isPressed() )
			p.isTa = false;
		else if( taYesRdoBtn.isPressed() )
			p.isTa = true;
		else
		{
			errLbl.setText("Please choose make TA option");
			return;
		}*/
		String crse1 = crse1DrpDwn.getValue();
		String crse2 = crse2DrpDwn.getValue();
		String crse3 = crse3DrpDwn.getValue();
			
		if( p.lName == "" || p.lName == null || p.fName == "" || p.fName == null || p.email == "" || p.email == null 
				|| p.currTerm == "" || p.currTerm == null || p.level == "" || p.level == null || crse1 == "" || crse1 == null
				|| crse2 == null || crse3 == null || crse1 == "" || crse2 == "" || crse3 == "" || taDrpDwn.getValue() == "" 
				|| taDrpDwn.getValue() == null )
		{
			errLbl.setText("Fields cannot be empty");
			return;
		}
		p.isTa = ( taDrpDwn.getValue().equals("Yes") )? true : false;
		Course c = new Course();
		c.ccode = crse1;
		p.courses.add(c);
		Course c2 = new Course();
		c2.ccode = crse2;
		p.courses.add(c2);
		Course c3 = new Course();
		c3.ccode = crse3;
		p.courses.add(c3);
		 if( db.enterStudentDetailsByAdmin( p ) )
		 {
			 errLbl.setText("Entered details succesfully!");
			 enterBtn.setVisible(false);
		 }
		 else
		 {
			 errLbl.setText("Entered details unsuccesful!");
			 enterBtn.setVisible(false);
		 }
		
	}
	
	public void viewDetails()
	{
		//Person P;
		
	}
	
	public void goBack()
	{
		new Main().updateScene(Main.ADMINVIEW);
	}

}
