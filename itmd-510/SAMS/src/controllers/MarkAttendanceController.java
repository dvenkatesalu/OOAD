/**
 * 
 */
package controllers;

import dao.DaoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Dharanip Priya
 *
 */
public class MarkAttendanceController {

	@FXML
	private TextField pcTxtFld;
	@FXML
	private Button okBtn;
	@FXML
	private Label errLbl;
	@FXML
	private TextField ccodeTxtFld;
	@FXML
	private TextField cwidTxtFld;
	
	private DaoModel db;
	/**
	 * 
	 */
	public MarkAttendanceController() {
		db = new DaoModel();
	}
	
	public void initialize()
	{
		
	}
	
	public void enterStudentPasscode()
	{
		errLbl.setText("");
		String pcode = pcTxtFld.getText().trim();
		String cwid = cwidTxtFld.getText().trim();
		String ccode = ccodeTxtFld.getText().trim();
		
		if( pcode == null || pcode == "" )
		{
			errLbl.setText("Please enter passcode");
		}
		if( ccode == null || ccode == "" )
		{
			errLbl.setText("Please enter course code");
		}
		if( cwid == null || cwid == "" )
		{
			errLbl.setText("Please enter cwid");
		}
		errLbl.setText(db.enterStudentAttendance(pcode,ccode,cwid));
		okBtn.setVisible(false);
	}

}
