package is.ru.stringcalculator;

import java.util.ArrayList;

public class Calculator {

	public static int add(String text) throws Exception {
		
		if(text.equals("")){
			return 0;
		}
		else if(text.startsWith("//")){
			return delimiter(text);
		}
		else if(text.contains(",")){
			return sum(splitNumbers(text));
		}
		else
			return toInt(text);
	}

	private static int toInt(String number) throws Exception {
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers) throws Exception {

		// change newline to ,
	    numbers = numbers.replaceAll("\n", ",");

	    return numbers.split(",");
	}
      
    private static int sum(String[] numbers) throws Exception{
 	    int total = 0;
		ArrayList<Integer> negative = new ArrayList<Integer>();

        for(String n : numbers){
        	int number = toInt(n);

		    total += number;

		    // if the number is negative
		    if(number < 0) {
		    	negative.add(number);
		    }
		}

		checkNegative(negative);

		return total;
    }

    private static int delimiter(String text) throws Exception{
    	// find the delimiter
    	String delimiter = text.substring(2,3);

		// cut off the //
		text = text.substring(4);

		text = text.replaceAll(delimiter, ",");
		return sum(splitNumbers(text));
    }

    private static void checkNegative(ArrayList<Integer> negative) throws Exception{
    	if(negative.isEmpty()) return;

    	String message = "Negatives are not allowed: ";

    	for(Integer number : negative){
    		message = message + number + ",";
    	}

    	// cut off the last ,
    	message = message.substring(0,message.length()-1);

		throw new Exception(message);
    }
}