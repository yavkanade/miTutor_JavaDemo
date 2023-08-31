package objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Users_List has two array fields: userArray and userArrayStrings. The former is used to store an array of User objects,
while the latter stores an array of strings each of which contains the data for a user object but as a string.

An iterative method is used to keep track of the number of user objects being stored, using a variable named numberOfUsers.

The addUser method takes a user object as a parameter and appends it to the current userArray, updates the variable numberOfUsers
and assigns the given user a unique ID using a method for the user class.

The deleteUser method also takes a user object as a parameter, and searches the userArray for the first occurrence
of that user object in order to remove it from the array.

There are also two getter methods: getArray and getUsersList both of which return the values for userArray currently
stored by the class.

The method setUserList takes an array of user objects and uses it to replace the current userArray array used by
the class.

There are two getter/search methods that take the name of a course as a string as a parameter and iterate through the entire userArray array, 
looking at the lists of strings containing course names for a match regardless of capitalization, if a match is found
the user object is added to a list which is returned to the program to display the user with their potential tutors/students.

*/
public class Users_List {

	private ArrayList<User> userArray = new ArrayList<User>();
	private ArrayList<String> userArrayStrings = new ArrayList<String>();
	private int numberOfUsers = 0;

	public void getUserListFromTxt(String filename) throws IOException, IOException {
		numberOfUsers = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			this.userArray=new ArrayList<User>();
			while ((line = br.readLine()) != null) {
				if (userArrayStrings.contains(line))
				this.userArrayStrings.add(line);
				User usr=new User(line);
				
				usr.setUserID(numberOfUsers);
				numberOfUsers++;
				this.userArray.add(usr);
			}

		}
	}
 
	
	
	
	public Users_List() {

	}


	public void addUser(User newUser) {
		try {
			this.userArray.add(newUser);
		}catch (NullPointerException npe) {
		}
		this.numberOfUsers++;
		newUser.setUserID(numberOfUsers);
	}

	public void deleteUser(User U1) {
		try {
			userArray.remove(U1);
		}catch (NullPointerException npe) {
		}
		this.numberOfUsers = this.numberOfUsers - 1;
	}

	public ArrayList<User> getArray() {
		return (userArray);
	}



	public ArrayList<User> getUserList() {
		return (userArray);
	}

	public void setUserList(ArrayList<User> users) {
		try {
			userArray = users;
		}catch (NullPointerException npe0 ) {
		}
	}

	public User getUser(int id) {
		return (userArray.get(id));
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	public ArrayList<User> getCourseStudents(String coursename) {
		ArrayList<User> usersHavingCourse = new ArrayList<User>();

		System.out.println("to find  "+coursename);
		for (User usr : userArray) {
			
			ArrayList<String> userCoursesToCheck = usr.getcoursesToLearn();
//			System.out.print("Name: " + usr.getName());
//			System.out.println("\\\\courses user teacher=>" + userCoursesToCheck);

			System.out.println("-----------------------------");

			System.out.println("-----------------------------");
			for (String userCourse : userCoursesToCheck) {

				if (userCourse.equalsIgnoreCase(coursename)) {

					usersHavingCourse.add(usr);

					System.out.println("user found|| name:"+ usr.getName());



				}
 
			} 

			System.out.println("-----------------------------");
			System.out.println("-----------------------------");
		}
		System.out.println("search done students course:  "+coursename);
		return (usersHavingCourse);
	}

	public ArrayList<User> getCourseTeachers(String coursename) {
		ArrayList<User> usersHavingCourse = new ArrayList<User>();
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println("to find  "+coursename);
		for (User usr : userArray) {
			ArrayList<String> userCoursesToCheck = usr.getcoursesToTeach();
//			System.out.print("Name: " + usr.getName());
//			System.out.println("\\\\courses user teacher=>" + userCoursesToCheck);

			
			for (String userCourse : userCoursesToCheck) {
				if (userCourse.equalsIgnoreCase(coursename)) {
					
					usersHavingCourse.add(usr);
					System.out.println("user found|| name:"+ usr.getName());

				}
			}
			
		}
		
		System.out.println("search done tutors for:  "+coursename);
		System.out.println("-------------------------------------------------");
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		return (usersHavingCourse);
	}


	
	//------------------------------------------------------------------------------------------------------------------------------------------------- 	
	public User isValid(String email, String password) {
		User answer = null;
		for (User usr : userArray) {
//			System.out.println(email+"|||"+usr.getEmail());
//
//			System.out.println(password+"|||"+usr.getPassword());
//			System.out.println("----------------------------");
			if ((usr.getEmail().equalsIgnoreCase(email) && usr.getPassword().equals(password))) {
				answer = usr;
				return(answer);
			}
		}
		return (answer);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------- 	
	public boolean newUserGood(String email) {
		boolean userGood=true;
		for (User user : userArray) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				userGood = false;
				return(userGood);
			}
		}
		return (userGood);
	}

	//------------------------------------------------------------------------------------------------------------------------------------------------- 	
	public void printUserList() {
		for (User usr: userArray) {
			usr.printUserInfo();
			
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------- 	
	public void cleanUp(String filename) throws IOException, IOException {
		FileWriter cleaner = new FileWriter(filename);
		cleaner.close();
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------- 	
	public void saveUserListAsTxt(String filename) throws IOException, IOException {
//
		Boolean firstline=true;
		Path path = Paths.get(filename);
		Files.delete(path);
		File usersTxt= new File(filename);
		FileWriter writer = new FileWriter(filename);
		 
		//System.out.println("number of users: " +numberOfUsers);
		for (User usr: userArray) {
 
    		if (firstline==false) writer.write(System.getProperty( "line.separator" ));
			String userline= (usr.getName()+"|"+usr.getEmail()+"|"+usr.getPhone()+"|"+usr.getPassword()+"|");
			firstline=false;
    		for (String str:usr.getcoursesToLearn()) {
    			userline=userline+str+"+";
    		}
    		userline=userline+"|";
     		for (String str:usr.getcoursesToTeach()) {
    			userline=userline+str+"+"; 
    		} 
    		userline=userline+"|";
    		
    		writer.write(userline);
		}
		writer.close();
	}
	

	
}
