package com.example.spring_maven_try.useragent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UAControllerTests {

	@Test
	public void testFindbrowser() {
		UAController controller = new UAController();
		
		String line = "";
		int count=1;
        String splitBy = ",,";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("src/test.txt"));
            while ((line = br.readLine()) != null) // returns a Boolean value
			{
				//if(count==900) break;
				String[] n = line.split(splitBy);
				assertEquals(n[1], controller.findbrowser(n[0]), count+" Browser ");
				assertEquals(n[2],controller.findBrowserVer(n[0],controller.findbrowser(n[0])),count+" Browser Version ");
				assertEquals(n[3],controller.findos(n[0]),count+" OS ");
				assertEquals(n[4],controller.findOSVer(n[0],controller.findos(n[0])),count+" Operating Version ");

				count++;
			}
			br.close();
		}     
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
	}

}
