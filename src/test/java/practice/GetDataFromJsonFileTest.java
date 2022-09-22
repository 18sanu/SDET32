package practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class GetDataFromJsonFileTest {

	public static void main(String[] args) throws IOException, ParseException {
		//create an object of JSONParser
		JSONParser parser=new JSONParser();
		//convert physical file to java object using FileReader 
		FileReader file=new FileReader("./src/test/resources/JsonFile.json");
		//Convert JSON file to java file
		Object obj = parser.parse(file);
		//downcast the object type of reference to JSONObject type to access the get method 
		//By the rule of JAVA we can not access sub class members from super class reference
		JSONObject jObj = (JSONObject)obj;
		System.out.println(jObj.get("browser"));
		System.out.println(jObj.get("url"));
		System.out.println(jObj.get("username"));
		System.out.println(jObj.get("password"));

	}

}
