package objects;

import java.util.Random;
import java.io.IOException;

public class UserGenerator {
	
	public UserGenerator() {	
	}
	
	public  void generateUsers(int userNumber) throws IOException {
		
        String[] abbreviations = {"ACWR","ACCT","ACSC","ENAE","AFST","ASL","ANTH","ALMC","ARKY","ARCH","APLA",
        		"ART","ARHI","ARTS","ASHA","ASTR","ASPH","BCEM","BIOL","BMEN","BIST","BOTA","BTMA","CNST",
        		"CMMB","CEST","ENCH","CHEM","CHIN","ENCI","CMCL","COMS","MDCH","CORE","CMDA","ENCM","CPSC",
        		"COOP","DNCE","DATA","ENDG","DRAM","EASC","EALS","EAST","ECOL","ECON","EDUC","EDBT","EDPS",
        		"EDER","ENEL","ENEE","EESS","ENER","ENMG","ENGG","ENFD","ENGL","ENTI","ENEN","ENSC","FILM",
        		"FNCE","FINA","FREN","GSXS","GEOG","GLGY","ENGO","GOPH","GERM","DEST","GREK","GRST","HSOC",
        		"HTST","INDL","INDG","ISEC","INNO","INTR","INTE","IPHE","ITAL","JPNS","KNES","LAND","LANG",
        		"LLAC","LATI","LAST","LAW","LWSO","LEAD","LING","MGST","ENMF","MRSC","MKTG","MATH","ENME",
        		"MDGE","MDPH","MDPR","MDSC","MDCN","MHST","MUSI","MUED","MUPF","NANS","NEUR","NURS","OPMA",
        		"OBHR","ENPE","PHIL","PHYS","PLAN","PLBI","PLUR","POLI","PLMA","PSYC","PPOL","REAL","RELS",
        		"RMIN","ROST","RUSS","SCPA","SCIE","SLAV","SOWK","SOCI","SENG","ENSF","SAST","SPPH","SPAN",
        		"STAT","STST","SGMA","SCMA","SUST","SEDV","TAP","TOUR","UNIV","UNEX","UBST","VETM","WELL","ZOOL"};
		
        String[] names = {"Emma", "Olivia", "Ava", "Isabella", "Sophia",
                "Charlotte", "Mia", "Amelia", "Harper", "Evelyn",
                "Abigail", "Emily", "Elizabeth", "Mila", "Ella",
                "Avery", "Sofia", "Camila", "Aria", "Scarlett"};
        
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown",
                "Miller", "Davis", "Garcia", "Rodriguez", "Martinez",
                "Hassan", "Ali", "Mohammed", "Khan", "Saleh",
                "Kim", "Park", "Lee", "Wang", "Lin",
                "Ivanov", "Petrov", "Sokolov", "Popov", "Volkov",
                "Kowalski", "Nowak", "Jablonski", "Wisniewski", "Kowalczyk"};
        
        String[] emails = {"@gmail.com","@gmail.ca", "@ucalgary.ca","@yahoo.com","@bing.com",
                "@outlook.com", "@aol.com", "@hotmail.com", "@mail.com", "@zoho.com",
                "@icloud.com", "@protonmail.com", "@me.com", "@yandex.com", "@gmx.com"};
        
        String [] phoneAreaCodes = { "403", "587", "780", "204"};
        
        Random rand = new Random();
        //\src\objects\Randomly Generated User

		//FileWriter writer = new FileWriter("\\src\\objects\\Randomly Generated User");
        for (int j=0; j<userNumber; j++) {
        	String name= names[rand.nextInt(names.length)];
        	String lastName=lastNames[rand.nextInt(lastNames.length)];
        	String email=(name+lastName).toLowerCase()+j+emails[rand.nextInt(emails.length)];
        	
        	
        	String phone = phoneAreaCodes[rand.nextInt(phoneAreaCodes.length)]+(rand.nextInt(899)+100)+(rand.nextInt(8999)+1000)+"|";
        
        	
        	String userStr=name+" "+ lastName+"|"+email+"|"+phone+"|"+("pass"+j)+"|";
        	
        	int l=(rand.nextInt(4)+3);
        	for (int i = 0; i <l; i++) {//courses
                String abbreviation = abbreviations[rand.nextInt(abbreviations.length)];
                int number = rand.nextInt(200) + 200;
                userStr=userStr+abbreviation+number+"+";
            }
        	userStr=userStr+"|";
        	l=(rand.nextInt(4)+3);
        	
            for (int i = 0; i < l; i++) {//courses
                String abbreviation = abbreviations[rand.nextInt(abbreviations.length)];
                int number = rand.nextInt(200) + 300; 
                userStr=userStr+abbreviation+number+"+";
            }

        	userStr=userStr+"|";
        	System.out.println(userStr);
        	//writer.write(userStr);
        	//writer.write(System.getProperty( "line.separator" ));
        }
       // writer.close();
	}
	

	

	
	
	
	
}
