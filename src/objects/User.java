package objects;

import java.util.ArrayList;

/*
The class User has a constructor that takes four parameters who's values are obtained from textboxes filled
by the user on the registration page. These four parameters are: userName, userPhone, userPassword and userEmail.
All of these parameters are strings and their values are used to initialize the similarly named variables
in the user class: name, phone, password and email.

There is a second constructor that exists for the user class but it takes a string argument: userInfo which is
a user object converted to a string as previously mentioned in the javadoc for the Users_List class. The 
constructor iterates through every character of the string, using the special character "|" to know which 
characters in the string hold which values (phone, email, etc.). From this a complete user class instance
is generated and the values for it's local variables set to those given by the userInfo string.

The class User has a setter and getter method for every local variable and array: getName, setName, getPhone, etc.
Each one of these methods either returns the value stored in the class instance's local variables/array or
updates that specific variable's value to that given by the parameter in the setter method.

The local variables used by this class are: name, phone, password, email and password (all of which are of type
string). userID which is of type integer and the two arrays: coursesToLearn and coursesToTeach. The arrays hold 
strings which represent the name of a given course.
*/

public class User {
	

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	private ArrayList<String> coursesToLearn = new ArrayList<String>();
	private ArrayList<String> coursesToTeach = new ArrayList<String>();

	private String phone;
	private String name;
	private String password;
	private String email;
	private int userID;

	public User(String userName, String userPhone, String userPassword, String userEmail) {

		setName(userName);
		setPhone(userPhone);
		setPassword(userPassword);
		setEmail(userEmail);
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	

	public User(String userInfo) {
		int valueIndex = 0;
		int arrayIndex = 0;
		String userName = "";
		String userPhone = "";
		String userPassword = "";
		String userEmail = "";

		ArrayList<String> txtCoursesToLearn = new ArrayList<String>();
		ArrayList<String> txtCoursesToTeach = new ArrayList<String>();
		txtCoursesToTeach.add("");
		txtCoursesToLearn.add("");

		for (int i = 0; i < userInfo.length(); i++) {

			if (userInfo.charAt(i) != '|' && valueIndex == 0) {
				userName = userName + userInfo.charAt(i);
			}

			if ((userInfo.charAt(i) == '|' && valueIndex == 0)) {
				valueIndex = 1;
				continue;
			}

			if (userInfo.charAt(i) != '|' && valueIndex == 1) {
				userEmail = userEmail + userInfo.charAt(i);
			}
			if ((userInfo.charAt(i) == '|' && valueIndex == 1)) {
				valueIndex = 2;
				continue;
			}

			if (userInfo.charAt(i) != '|' && valueIndex == 2) {

				userPhone = userPhone + userInfo.charAt(i);
			}
			if ((userInfo.charAt(i) == '|' && valueIndex == 2)) {
				valueIndex = 3;
				continue;
			}

			if (userInfo.charAt(i) != '|' && valueIndex == 3) {

				userPassword = userPassword + userInfo.charAt(i);
			}
			if ((userInfo.charAt(i) == '|' && valueIndex == 3)) {
				valueIndex = 4;
				continue;
			}

			if ((userInfo.charAt(i) == '|' && valueIndex == 4)) {
				valueIndex = 5;
				arrayIndex = 0;
				continue;
			}
			
			if (valueIndex == 4 && userInfo.charAt(i) != '|') {
				if (userInfo.charAt(i) != '+') {
					txtCoursesToLearn.set(arrayIndex, txtCoursesToLearn.get(arrayIndex) + userInfo.charAt(i));
				}
				if (userInfo.charAt(i) == '+') {
					txtCoursesToLearn.add("");
					arrayIndex++;
				}
			}

			if (valueIndex == 5 && userInfo.charAt(i) != '|') {
				if (userInfo.charAt(i) != '+') {

					txtCoursesToTeach.set(arrayIndex, txtCoursesToTeach.get(arrayIndex) + userInfo.charAt(i));
				}
				if (userInfo.charAt(i) == '+') {
					txtCoursesToTeach.add("");
					arrayIndex++;
				}
			}

		}

		if (txtCoursesToTeach.size() > 1)
			txtCoursesToTeach.remove(txtCoursesToTeach.size() - 1);

		if (txtCoursesToLearn.size() > 1)
			txtCoursesToLearn.remove(txtCoursesToLearn.size() - 1);
		setName(userName);
		setPhone(userPhone);
		setPassword(userPassword);
		setEmail(userEmail.toLowerCase());
		addCourses(txtCoursesToLearn, txtCoursesToTeach);

	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	
	public ArrayList<String> getcoursesToLearn() {
		return (coursesToLearn);
	}

	public ArrayList<String> getcoursesToTeach() {
		return (coursesToTeach);
	}



	public void addCourses(ArrayList<String> CoursesToLearnP, ArrayList<String> CoursesToTeachP) {

		ArrayList<String> CTL = new ArrayList<String>();
		ArrayList<String> CTP = new ArrayList<String>();
		
		
		if(CoursesToLearnP.size()>0) {
			for (String str: CoursesToLearnP) {
				if (str.length()>1) {
					CTL.add(str);
				}
			}
		}
		if(CoursesToTeachP.size()>0) {
			for (String str: CoursesToTeachP) {
				if (str.length()>1) {
					CTP.add(str);
				}
			}
		}
		
		coursesToLearn = CTL;
		coursesToTeach = CTP;

	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	public void printUserInfo() {
		System.out.println("name => "+name+"|||"+"email => "+email+"|||"+"phone => "+phone+"|||"+"password => "+password);
		System.out.println("courses to Learn:"+coursesToLearn);
		System.out.println("courses to Learn:"+coursesToTeach);
		System.out.println("//------------------------------------------------------------------------------"); 
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	


	public String getPhone() {
		try {
			return this.phone;
		}catch (NullPointerException npe) {
			return ("Error; No phone number");
		}
	}

	public void setPhone(String numPhone) {
		try {
			this.phone = numPhone;
		}catch (NullPointerException npe) {	
		}
	}

	public String getEmail() {
		try {
			return this.email;
		}catch (NullPointerException npe) {
			return ("Error; no email");
		}
	}

	public void setEmail(String emailAdress) {
		try {
			this.email = emailAdress;
		}catch (NullPointerException npe) {
		}
	}	
		
	public String getName() {
		try {
			return this.name;
		}catch (NullPointerException npe) {
			return ("Error; no name");
		}
	}

	public void setName(String userName) {
		try {
			this.name = userName;
		}catch (NullPointerException npe) {
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserID(int id) {
		this.userID = id;

	}

	public int getUserID() {

		return (userID);
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------   	
	
	
	
}
