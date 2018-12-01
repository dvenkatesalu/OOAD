package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application 
{
	public static Stage stage;
	private AnchorPane root;
	private Scene scene;
	public static String LOGINVIEW = "/views/LoginView.fxml";
	public static String REGISTERVIEW = "/views/RegisterView.fxml";
	public static String INSTRUCTORVIEW = "";
	public static String STUDENTVIEW = "";
	public static String ADMINVIEW = "";
	
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			stage = primaryStage;
			root = (AnchorPane) FXMLLoader.load(getClass().getResource(LOGINVIEW));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.show();
		} 
		
		catch(Exception e) 
		{
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
	}
	
	public void updateScene( String viewName )
	{
		try
		{
			String title = "";
			if( viewName.equals(LOGINVIEW) )
				title = "Login";
			else if( viewName.equals(REGISTERVIEW) )
				title = "Register";
			root = (AnchorPane) FXMLLoader.load(getClass().getResource(viewName));
			stage.setTitle(title);
			scene = new Scene(root);
			stage.setScene(scene);
		}
		catch(Exception e) 
		{
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
