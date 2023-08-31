package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import objects.Users_List;
import view.LoginController2;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class System extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/Login Page.fxml"));
			root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root);
			LoginController2 controller = (LoginController2)loader.getController();

			Users_List userList= new Users_List();
			controller.setUserList(userList);
			
			controller.setPrimaryStage(primaryStage); 
			controller.setMyScene(scene);
			
			  
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Find your Tutor");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
