package view;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import java.io.FileInputStream;
import java.util.ArrayList;
import objects.User;
import objects.Users_List;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* The controller; CoursesPageController handles the interactions between the user and GUI on the courses page.
 * The controller is charged with feeding the CoursesPage fxml fed with accurate data to show the user such as
 * the name of classes that are already being stored in the User object instance associated with the user of the
 * program. The controller also relays any inputed data from the user to the user class such as the name of a new
 * course.
*/
public class CoursesPageController {

    private User user;

    private ArrayList<TextField> coursesLearnAfter= new ArrayList<TextField>();
    private ArrayList<TextField> coursesTeachAfter= new ArrayList<TextField>();
    

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

    @FXML
    private VBox userCoursesTeach;
    
	public void addUserCoursesTeach() {

		ArrayList<String> coursesToTeach = user.getcoursesToTeach();

		for (int i = 0; i < 10; i++) {
			HBox container = new HBox();
			Label label = new Label("Course name:");
			String courseTeach = "";
			try {
				courseTeach = coursesToTeach.get(i);
			} catch (Exception e) {

			}
			TextField courseTeachTxt = new TextField(courseTeach);
			courseTeachTxt.setPromptText("PRMT101");
			coursesTeachAfter.add(courseTeachTxt);

			container.getChildren().addAll(label, courseTeachTxt);
			userCoursesTeach.getChildren().add(container);

		}
	}

	

	
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	


    @FXML
    private VBox userCoursesLearn;
    
    
	public void addUserCoursesLearn() { 

		ArrayList <String> coursesToLearn = user.getcoursesToLearn();
		for(int i=0; i<10; i++) {
			HBox container= new HBox();
			Label label= new Label("Course name:");
			String courseLearn="";
			try {
			courseLearn=coursesToLearn.get(i);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			TextField courseLearnTxt= new TextField(courseLearn);
			courseLearnTxt.setPromptText("PRMT101");
			coursesLearnAfter.add(courseLearnTxt);
			container.getChildren().addAll(label, courseLearnTxt);
			userCoursesLearn.getChildren().add(container);
		}
	}
  	
	//Method returns you to homepage and saves your added or deleted courses.
	@FXML
    void goHomePage(ActionEvent event) {

		ArrayList<String> coursesToLearnGoHome = new ArrayList<String>();
		ArrayList<String> coursesToTeachGoHome = new ArrayList<String>();
		
		for (TextField courseTeachTXT : coursesTeachAfter) {
			String str = courseTeachTXT.getText();
			if (!str.equals("")) {
				if ((str.length() == 7 || str.length() == 8)) {
					String str1="";
					str.toUpperCase();
					for (int i=0; i<str.length(); i++) {
						if (str.charAt(i)!=' ') str1=str1+str.charAt(i);
					}
					coursesToTeachGoHome.add(str1.toUpperCase());
				}
			}
		}	

		for (TextField courseLearnTXT : coursesLearnAfter) {
			String str = courseLearnTXT.getText();
			if (!str.equals("")) {
				if ((str.length() == 7 || str.length() == 8)) {
					String str1="";
					str.toUpperCase();
					for (int i=0; i<str.length(); i++) {
						if (str.charAt(i)!=' ') str1=str1+str.charAt(i);
					}
					coursesToLearnGoHome.add(str1.toUpperCase());
				}
			}
		}
		
		
		user.addCourses(coursesToLearnGoHome, coursesToTeachGoHome);
		
		
		try {

	    	userList.saveUserListAsTxt("src\\\\objects\\\\AllUsersTXTFILE");
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