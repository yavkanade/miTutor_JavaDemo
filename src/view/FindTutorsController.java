package view;

import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.User;
import objects.Users_List;

/*
 * The FindTutorsController controller is nearly identically to the FindStudensController controller.
 * They function almost identically save for what methods are called, most importantly getCourseTeachers 
 * instead of getCourseStudents from the Users_List class which returns a list of strings containing the 
 * contents of a user object associated with a found match for a tutor as a string which is then displayed 
 * to the user by the controller once the method has run through the entire array of users.
 */

public class FindTutorsController {
	
	
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

// -------------------------------------------------------------------------------------------------------------------------------------------------

	@FXML
	private ChoiceBox<String> courseLearnChoiceBox;// learn because these are the courses student need to learn

	public void setChoicebox(ArrayList<String> courses) {
		ObservableList<String> setupList = FXCollections.observableArrayList(courses);
		courseLearnChoiceBox.setItems(setupList);
	}

// -------------------------------------------------------------------------------------------------------------------------------------------------    
//Find Tutor 
	
    @FXML
    private VBox findTutorPageVbox;

	@FXML
    void actionFindTeachers(ActionEvent event) {
		String courseToFind="";
		try {
			courseToFind=courseLearnChoiceBox.getValue();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ArrayList <User> Teachers = new ArrayList<User>();
		
		Teachers = userList.getCourseTeachers(courseToFind);
		VBox teachersContainer= new VBox();
		teachersContainer.setAlignment(Pos.CENTER);
		findTutorPageVbox.getChildren().clear(); 
		findTutorPageVbox.setAlignment(Pos.CENTER); 
		HBox space=new HBox();
		space.setPrefHeight(30);
		
		Label lbl=new Label("People who can teach: "+courseToFind);
		findTutorPageVbox.getChildren().addAll(lbl,space);
		for (User indexUser: Teachers) {  
			
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
		findTutorPageVbox.getChildren().addAll(teachersContainer,line);
		
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
