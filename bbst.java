import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class bbst {

	public static void main(String[] args) throws IOException {
		
		//The Main class
		
		//Code to read the sorted array from a file and initialize the tree.
		Scanner sc = new Scanner(new File(args[0]));
	    sc.useDelimiter("\n");
	    int size = sc.nextInt();
	    int i = 0;
	    int[][] initial_array = new int[size][2];
	    while (sc.hasNext()) {
	      String s = sc.next();
	      String[] ss = s.split(" ");
	      initial_array[i][0] = Integer.parseInt(ss[0]);
	      initial_array[i][1] = Integer.parseInt(ss[1]);
	      i++;
	    }
	    
	    sc.close();
	    
	    RBTree tree = new RBTree();
	    tree.rbTreeCreate(initial_array, size);

	    
		//Code to read user input and to operate on the tree.
	    Scanner scanner = new Scanner(System.in);

	    while (scanner.hasNext()) {
	    	String line = scanner.nextLine();
		    String command = line.split(" ")[0];
		   try{
		    switch (command) {
		    	case "increase": {
		    			int id = Integer.parseInt(line.split(" ")[1]);
		    			int count = Integer.parseInt(line.split(" ")[2]);
		    			tree.increase(id, count);
		    			break;
		    		}
		    case "reduce": {
		    			int id = Integer.parseInt(line.split(" ")[1]);
		    			int count = Integer.parseInt(line.split(" ")[2]);
		    			tree.reduce(id, count);
		    			break;
		    		}

		    case "count": {
		    			int id = Integer.parseInt(line.split(" ")[1]);
		    			tree.countNode(id);
		    			break;
		    		}

		    case "inrange": {
		    			int id1 = Integer.parseInt(line.split(" ")[1]);
		    			int id2 = Integer.parseInt(line.split(" ")[2]);
		    			tree.inRange(id1, id2);
		    			break;
		    		}

		    case "next": {
		    			int id = Integer.parseInt(line.split(" ")[1]);
		    			tree.nextNode(id);
		    			break;
		    		}

		    case "previous": {
		    			int id = Integer.parseInt(line.split(" ")[1]);
		    		    tree.previousNode(id);
		    			break;
		    		}

		    case "quit": {
		    			System.exit(0);
		    			break;
		    		}
		    }
	    }
		    
	    	catch(Exception ex){
		    	System.out.println(ex.getCause());
		    	}
		    
	    } scanner.close();
	    
	}

}
