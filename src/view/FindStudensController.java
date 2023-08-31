package view;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.util.ArrayList;
import objects.User;
import objects.Users_List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.stage.Stage;
/*
 * 
 * The FindStudentsController is charged with handling the search for potential students at the interface level.
 * The controller sets the scene and fetches the information it requires to construct the found students page, 
 * this is done by mainly calling on the getter methods defined in the User class.
 * The controller uses these methods to create a choice box for the user to select which of the user specified 
 * courses they would like to find a student to tutor. The controller then makes use of the getCourseStudents method
 * defined in the users list class to get an array of strings representing users of the application found to be 
 * potential students which the controller stores and then displays to the user. 
 *
*/

public class FindStudensController {

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	
    private User user;
    public Users_List userList = new Users_List();

	public Users_List getUserList() { 
		return(userList);
	}
    
	public void setUserList(Users_List uList) {
		userList= uList;
	}

    public void setUser(User usr) {
    	user=usr;
    }
    
    public User getUser() {
    	return(user);
    }
	
//-------------------------------------------------------------------------------------------------------------------------------------------------    
    
	private Stage primaryStage;
	private Scene myScene;
	private UserHomepageController controllerTwo;
	
	public void setPrimaryStage(Stage aStage) {
		primaryStage = aStage;
	}
	
	public void setMyScene(Scene aScene) {
		myScene = aScene;
	}
	
	public void takeFocus() {
		primaryStage.setScene(myScene);  
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------  

	@FXML
    private ChoiceBox<String> courseTeachChoiceBox;

	public void setChoicebox(ArrayList <String> courses) {
		System.out.println(courses);
		ObservableList<String> setupList = FXCollections.observableArrayList(courses);
		courseTeachChoiceBox.setItems(setupList);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	//Find Students

    @FXML
    private VBox findStudentPageVbox;

	@FXML
    void actionFindStudents(ActionEvent event) {
	
		String courseToFind="";
		try {
			courseToFind=courseTeachChoiceBox.getValue();
			System.out.println(courseToFind);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ArrayList <User> Students = new ArrayList<User>();
		Students = userList.getCourseStudents(courseToFind);
		VBox teachersContainer= new VBox();
		teachersContainer.setAlignment(Pos.CENTER);
		findStudentPageVbox.getChildren().clear(); 
		findStudentPageVbox.setAlignment(Pos.CENTER);
		Label lbl=new Label("Students who need help with: "+courseToFind);
		HBox space=new HBox();
		space.setPrefHeight(30);  
		findStudentPageVbox.getChildren().addAll(lbl, space);
		
		for (User indexUser: Students) {
			HBox container=new HBox(100);
			container.setAlignment(Pos.CENTER);
			Label fullName= new Label("Name: "+indexUser.getName());
			Label email= new Label("Email:"+indexUser.getEmail());
			Label phone= new Label("Phone number: "+indexUser.getPhone());
			
			container.getChildren().addAll(fullName,email,phone);

			Label lineShort=new Label("-------------------------------");
			teachersContainer.getChildren().addAll(container,lineShort);
		
		}
		Label line=new Label("-------------------------------------------------------------------");
		
		findStudentPageVbox.getChildren().addAll(teachersContainer,line);
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	
	@FXML
    void goHomePage(ActionEvent event) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/UserHomepage.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root); scene.setFill(Color.BLUE); 
 
			controllerTwo = loader.getController();
			controllerTwo.setPrimaryStage(primaryStage);
			controllerTwo.setMyScene(scene);
			controllerTwo.setUser(user);
			controllerTwo.setUserList(userList);

			controllerTwo.loginUserSetup(user);
			controllerTwo.takeFocus();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	

}
