package view;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.UserGenerator;
import objects.User;
import objects.Users_List;

/*
 * This controller is charged with controlling the login page which is what every user will se upon starting
 * the application. The page has two textfields used for logging in and an extra button to bring the user to
 * the registration page.
 * When a user attempts to log in by filling in both textboxes and clicking the login button the controller will
 * go through the textfile that stores all of the application's data. Using the isValid method from the Users_List
 * class, the controller will only allow the application to acces the homepage is a match is found for both the
 * email and password among the array of users in the txt file. If no match is found a textfield will appear
 * above the textboxes and inform the user that their email/password is incorrect.
 * 
*/
public class LoginController2 {

    @FXML
    private TextField loginUsername;

    @FXML
    private Button loginButton; 
    
    @FXML
    private PasswordField loginPassword;

    @FXML
    private Hyperlink createAccountHyperlink;
    
    @FXML
    public Label wrongLoginLabel;

	private Stage primaryStage;
	private Scene myScene;
	private SignUpController controllerOne;
	private UserHomepageController controllerTwo;

    public Users_List userList = new Users_List();
	
	public Users_List getUserList() {
		return(userList);
	}
    
	public void setUserList(Users_List uList) {
		userList= uList;
	}
	
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
	void loginAction(ActionEvent event) throws IOException {
		UserGenerator gen= new UserGenerator();
		gen.generateUsers(100);

	    userList.getUserListFromTxt("src\\\\objects\\\\AllUsersTXTFILE");
	    User userCheck=  userList.isValid(loginUsername.getText(), loginPassword.getText());

	    if  (userCheck!=null) { 
	        try {
	            userCheck.printUserInfo();
	            
	            FXMLLoader loader = new FXMLLoader();
	            VBox root = loader.load(new FileInputStream("src/view/UserHomepage.fxml")); root.setStyle("-fx-background-color: #ADD8E6;");
	            root.setStyle("-fx-background-color: #ADD8E6;");
	            
	            Scene scene = new Scene(root); scene.setFill(Color.WHEAT); 

	            controllerTwo = loader.getController();
	            controllerTwo.setPrimaryStage(primaryStage); 
	            controllerTwo.setMyScene(scene); 
	            //controllerTwo.setNextController(this);  
	            controllerTwo.setUser(userCheck);  
	            controllerTwo.setUserList(userList); 
	            controllerTwo.loginUserSetup(userCheck);
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	        controllerTwo.takeFocus();
	    }
	    else {
	    	wrongLoginLabel.setTextFill(Color.RED);
	    	wrongLoginLabel.setText("Sorry, you entered an invalid email or password. Please try again.");
	    }
	}

  //-------------------------------------------------------------------------------------------------------------------------------------------------   	

    @FXML
    void signUpAction(ActionEvent event) throws IOException {

    	userList.getUserListFromTxt("src\\\\objects\\\\AllUsersTXTFILE");
    	try { 
	    	FXMLLoader loader = new FXMLLoader();
			VBox homepageVbox = loader.load(new FileInputStream("src/view/SignUpPage.fxml")); homepageVbox.setStyle("-fx-background-color: #ADD8E6;");
			Scene scene = new Scene(homepageVbox);
			
			controllerOne = loader.getController();
			controllerOne.setPrimaryStage(primaryStage);
			controllerOne.setMyScene(scene); 
			controllerOne.setUserList(userList);

    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	controllerOne.takeFocus();
    }
    
    
}

