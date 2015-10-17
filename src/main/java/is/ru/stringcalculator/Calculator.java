package is.ru.stringcalculator;

import java.util.ArrayList;
import java.util.regex.Pattern;

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

        	number = checkBigNumbers(number);
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

    	if(delimiter.equals("[")) {
    		return longDelimiter(text);
    	}
    	else {
    		// cut off the //
			text = text.substring(4);
    	}

		return sum(text.split(delimiter));
    }

    private static int longDelimiter(String text) throws Exception{
    	ArrayList<String> delimiters  = findAllDelimiters(text);
    	
    	int newline = text.indexOf("\n");
    	// get all the string after \n
		text = text.substring(newline + 1);
		
		for(String del : delimiters){
			// replays the delimiter whith ,
			text = text.replaceAll(Pattern.quote(del),",");
		}

		return sum(splitNumbers(text));
    }

    private static ArrayList<String> findAllDelimiters(String text) throws Exception{
    	ArrayList<String> delimiters  = new ArrayList<String>();

    	int indexStart =  text.indexOf("[", 2); 
    	String i = "[";
    	
    	while(i.equals("[")){
        	int indexEnd =  text.indexOf("]", indexStart); 
        	
        	// find the delimiter and add it to the ArrayList
        	delimiters.add(text.substring(indexStart + 1,indexEnd));
        	
        	indexStart = text.indexOf("[", indexEnd);
        	
        	// if we found ]
        	if(indexStart != -1){
        		i = text.substring(indexStart,indexStart + 1);
        	}
        	else {
        		i = "[ was not found";
        	}
    	}

    	return delimiters;
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

    private static int checkBigNumbers(int number) {
    	if(number > 1000) {
    		return 0;
    	}
    	return number;
    }
}