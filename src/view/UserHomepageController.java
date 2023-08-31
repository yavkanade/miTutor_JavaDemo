package view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import java.io.FileInputStream;
import java.io.IOException;
import objects.User;
import objects.Users_List;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * This controller dictates the behavior of the application regarding the homepage.
 * It has several local variables which use the User class getter and setter methods to both fetch and display
 * the current user's information as well as update said information according to the user's inputs.
 * The homepage contains several buttons which are used to open the user data textfield to edit the string values
 * of that User instance (name, phone, email .ect) as well as buttons that bring to other scenes such as the 
 * CoursesPage or FindTutorPage as well as a log out button which closes the application.
*/

public class UserHomepageController {
	
	private Stage primaryStage;
	private Scene myScene;
	
	private LoginController2 controllerLogin;
	private FindStudensController controllerFindStudents;
	private FindTutorsController controllerFindTutors;
	private CoursesPageController controllerCourses;

    public void setPrimaryStage(Stage aStage) {
		primaryStage = aStage;
	}
	
	public void setMyScene(Scene aScene) {
		myScene = aScene;
	}
	
//	public void setNextController(LoginController2 aController) {
//		controllerLogin = aController;
//	}
	
	public void takeFocus() {
		primaryStage.setScene(myScene);
	}

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

	public void setUpController(User usr,Users_List uList, Stage aStage,Scene aScene) {
		userList= uList;
		user=usr;
		primaryStage.setScene(myScene);
		myScene = aScene;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private MenuItem editProfileButton;

    @FXML
    private Button checkButton;

    @FXML
    private MenuItem logoutButton;

    @FXML
    private MenuButton nameLabel;
    
    @FXML
    private Label idUser;

    @FXML
    private Label userPhone;

    @FXML
    private Label UserPassword;

    @FXML
    private Label userEmail;
    
    @FXML
    private HBox saveHbox;

    @FXML
    private VBox editVbox;

    @FXML
    private VBox editVbox2;

    @FXML
    private VBox editVbox1;
    
    @FXML
    private Label userNameLabel;

    @FXML
    private TextField passwordTXT;
    
    @FXML
    private TextField emailTXT;
    
    @FXML
    private TextField phoneTXT;
    
    @FXML
    private Button editbutton;
    
    @FXML
    private Button saveChangesbutton;
    
  //-------------------------------------------------------------------------------------------------------------------------------------------------   	
  
    public void loginUserSetup(User usr) {
    	userNameLabel.setText(usr.getName());
    	userPhone.setText(usr.getPhone());
    	userEmail.setText(usr.getEmail());
    	
    	passwordTXT.setText(usr.getPassword()); 
		phoneTXT.setText(usr.getPhone());
		emailTXT.setText(usr.getEmail());
		
    	passwordTXT.setVisible(false);
		emailTXT.setVisible(false);
		phoneTXT.setVisible(false);
		saveChangesbutton.setVisible(false);
	}

    //-------------------------------------------------------------------------------------------------------------------------------------------------   	
    
    @FXML
    void saveChanges() throws IOException {
    	editbutton.setVisible(true);
    	saveChangesbutton.setVisible(false);
    	
    	try {
    		if (!passwordTXT.getText().equals("")) {
    			String pasSTR=passwordTXT.getText();
    			user.setPassword(pasSTR);
    			}
    	}
    	catch(Exception e) {
    		System.out.println("password empty");
    		e.printStackTrace();
    	}
		
		try {
			if (!emailTXT.getText().equals("")) {
			String emailSTR=emailTXT.getText();
			user.setEmail(emailSTR);
			}
    	}
    	catch(Exception e) {
    		e.printStackTrace();

    		System.out.println("email empty");
    	}
		
		try {
			if (!phoneTXT.getText().equals("")) {
			String phoneSTR=phoneTXT.getText();
			user.setPhone(phoneSTR);
			}
		}
    	catch(Exception e) {
    		e.printStackTrace();
    		System.out.println("phone empty");
    	}

    	userNameLabel.setText(user.getName()+"");
    	
    	userPhone.setText(user.getPhone());
    	userEmail.setText(user.getEmail());
    	//UserPassword.setText(user.getPassword());
    	
		passwordTXT.setVisible(false);
		emailTXT.setVisible(false);
		phoneTXT.setVisible(false);

    	userList.saveUserListAsTxt("src\\\\objects\\\\AllUsersTXTFILE");
    }

    @FXML
	void editInfoAction(ActionEvent event) {
    	saveChangesbutton.setVisible(true);
    	editbutton.setVisible(false);
		passwordTXT.setVisible(true);
		emailTXT.setVisible(true); 
		phoneTXT.setVisible(true);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------   	
    
    @FXML
    void logoutAction(ActionEvent event) {

		try {
	    	FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/Login Page.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root); scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE); 
			
			controllerLogin = loader.getController();
			controllerLogin.setPrimaryStage(primaryStage);
			controllerLogin.setMyScene(scene);
			controllerLogin.setUserList(userList); 
			controllerLogin.wrongLoginLabel.setTextFill(Color.LIME);
			controllerLogin.wrongLoginLabel.setText("Logout succesful");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		controllerLogin.takeFocus();
    }

  //-------------------------------------------------------------------------------------------------------------------------------------------------   	

	@FXML
	void coursepageaction(ActionEvent event) {		
		try {
	    	userList.saveUserListAsTxt("src\\\\objects\\\\AllUsersTXTFILE");
	    	FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/CoursesPage.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root); scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE); 
			
			controllerCourses = loader.getController();
			controllerCourses.setPrimaryStage(primaryStage);
			controllerCourses.setMyScene(scene);
			controllerCourses.setUser(user); 
			controllerCourses.setUserList(userList); 
			controllerCourses.addUserCoursesLearn();  
			controllerCourses.addUserCoursesTeach();
		}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		controllerCourses.takeFocus();
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	

    @FXML
    void findTutor(ActionEvent event) {
    	try {
        	userList.saveUserListAsTxt("src\\\\objects\\\\AllUsersTXTFILE");
    		
	    	FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/FindTutorPage.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root); scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE);  scene.setFill(Color.BLUE); 
			
			controllerFindTutors = loader.getController();
			controllerFindTutors.setPrimaryStage(primaryStage);
			controllerFindTutors.setMyScene(scene);
			controllerFindTutors.setUser(user); 
			controllerFindTutors.setUserList(userList); 
			controllerFindTutors.takeFocus();
			controllerFindTutors.setChoicebox(user.getcoursesToLearn());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
  //-------------------------------------------------------------------------------------------------------------------------------------------------   	

    @FXML
    void findStudentAction(ActionEvent event) {
    	try {
        	userList.saveUserListAsTxt("src\\\\objects\\\\AllUsersTXTFILE");
	    	FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/view/FindStudentPage.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(root); scene.setFill(Color.BLUE); 
			
			controllerFindStudents = loader.getController();
			controllerFindStudents.setPrimaryStage(primaryStage);
			controllerFindStudents.setMyScene(scene);
			controllerFindStudents.setUser(user); 
			controllerFindStudents.setUserList(userList);  
			controllerFindStudents.takeFocus();
			controllerFindStudents.setChoicebox(user.getcoursesToTeach());
			
			} 
    	catch(Exception e) {
    		e.printStackTrace(); 
    	}
    }

}
