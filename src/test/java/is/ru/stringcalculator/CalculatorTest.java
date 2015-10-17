package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.calculator.CalculatorTest");
    }

    // Tests for add function
	@Test
	public void testEmptyString() throws Exception {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() throws Exception {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() throws Exception {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers() throws Exception {
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test
    public void testNewLine() throws Exception {
    	assertEquals(6, Calculator.add("1\n2,3"));
    }
    
    @Test 
    public void testDelimiter() throws Exception {
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumbers(){
    	String result = "";

		try {
		  Calculator.add("-1,2");
		} catch (Exception e) {
		  result = e.getMessage();
		}

  		assertEquals("Negatives are not allowed: -1", result);
    }

    @Test
    public void testTwoNegativeNumbers(){
    	String result = "";

		try {
		  Calculator.add("2,-4,3,-5");
		} catch (Exception e) {
		  result = e.getMessage();
		}

  		assertEquals("Negatives are not allowed: -4,-5", result);
    }

    @Test
    public void testBigNumber() throws Exception{
    	assertEquals(2,Calculator.add("1001,2"));
    }

    @Test 
    public void testLongDelimiter() throws Exception{
    	assertEquals(6,Calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void testMultipleDelimiters() throws Exception{
    	assertEquals(6,Calculator.add("//[*][%]\n1*2%3"));
    }
}