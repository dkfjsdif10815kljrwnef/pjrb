package pjrb.cms.temp.excel.web;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonFileRead {

	public void jsonRead() throws IOException, ParseException {
		
		 Reader reader = new FileReader("sampleData");
	        
	        // reader를 Object로 parse
	        JSONParser parser = new JSONParser();
	        Object obj = parser.parse(reader); 
	
	}
}
