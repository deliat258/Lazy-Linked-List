/*
  	Class: CE3345
 	Section: 003
  	Semester: Fall 2019
  	Name: Delia V. Trejo
  	Description: Main class takes in 2 arguments from the command prompt: an input file then an 
  				output file. This main will take each line, split it are the colon(:) and put it
  				store it into a string array. Next, the it will store the first index of the 
  				string array into operation(Insert, Delete, etc.) and the second index into key, an
  				integer. If the length of the string array is 1 then accept is false. If accept is
  				false then Insert, Delete, and Contains will have an error message displayed since
  				these functions REQUIRE an integer value.
  				If 2 files are defined during runtime then this program will output an error message
  				and will terminate.			
 */
import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;
public class Main {
    public static void main(String[] args) throws Exception{
    	if(args.length != 2) {
    		out.println("Give Command line arguement filenames");
    		System.exit(0);
    	}
    	PrintWriter output = null;
    	Scanner input = null;
    	String line;
    	
    	try {
    		boolean accept = false;
    		output = new PrintWriter(new File(args[1]));
    		input = new Scanner(new File(args[0]));
    		int key = 0;
    		String str[];
    		
	    	String operation = "";
	    	
	    	LazyBinarySearchTree tree = new LazyBinarySearchTree();
	    	
	    	while(input.hasNextLine()) {
	    		line = input.nextLine();
	    		str = line.split(":");
	    		if(str.length > 1 && line.contains(":")) {
		    		operation = str[0];
		    		key= Integer.parseInt(str[1]);
		    		accept = true;
	    		}
	    		if(str.length == 1) {
	    			operation = line;
	    			accept = false;
	    		}
	    		
	    		
	    		switch(operation) {
	    		case "Insert":
	    			if (accept == true) {
	    				try {
			    			output.println(tree.insert(key));
			    		}catch (Exception e) {
			    			output.println("Error in Insert:" + e.getMessage());
			    		}
	    			}
	    			else
	    				output.println("Error in Line: " + operation);
	    			break;
	    			
	    		case "Delete":
	    			if(accept == true) {
			    		try {
			    			output.println(tree.delete(key));
			    		}catch(Exception e) {
			    			output.println("Error in Delete: " + e.getMessage());
			    		}
	    			}
	    			else
	    				output.println("Error in Line: " + operation);
	    			break;
	    			
	    		case "Contains":
	    			if(accept == true) {
		    			try {
		    				output.println(tree.contains(key));
		    			}catch(Exception e) {
		    				output.println("Error in Contains: " + e.getMessage());
		    			}
	    			}
	    			else
	    				output.println("Error in Line: " + operation);
	    			break;
	    			
	    		case "FindMin":
	    			output.println(tree.findMin());
	    			break;
	    			
	    		case "FindMax":
	    			output.println(tree.findMax());
	    			break;
	    			
	    		case "PrintTree":
	    			output.println(tree);
	    			break;
	    			
	    		case "Height":
	    			output.println(tree.height());
	    			break;
	    		
	    		case "Size":
	    			output.println(tree.size());
	    			break;
	    			
	    		default:
	    			output.println("Error in Line: " + operation);
	    		}
	    	}
    	}
    	catch(Exception e){
    		e.getMessage();
    	}
    	input.close();
    	output.close();
    }
}