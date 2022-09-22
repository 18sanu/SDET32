package practice;



import java.util.HashSet;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsDemo {
	@Test
	public void demo1() {
		SoftAssert a=new SoftAssert();
		System.out.println("step 1 from demo1");
		HashSet h = new HashSet();
		h.add(1);
		h.add(2);
		HashSet h1 = new HashSet();
		h1.add(2);
		h1.add(1);
		
		a.assertEquals(h1, h);
		System.out.println("step 2 from demo1");
	
		System.out.println("step 3 from demo1");
		a.assertAll();
		
		System.out.println("step 4 from demo1");
		
	}

}
