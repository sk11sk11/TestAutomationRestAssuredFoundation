package files;

public class payload {
	
	public static String Addbook(String isbn, String aisle) {
		
		String payload = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"WisdomBook\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Grassian\"\r\n"
				+ "}\r\n"
				+ "";
		return payload;
	}
	
	
	public static String Deletebook(String isbn, String aisle) {
		
		String payload = "{\r\n"
				+ "    \"ID\": \""+isbn+aisle+"\"\r\n"
				+ "}";
		return payload;
		
	}

}
