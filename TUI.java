/**
 * 
 */


import java.util.Scanner;

/**
 * A simple text-based user interface for displaying concordance.
 * 
 * @author S H S Wong
 * @version 26/10/2016
 */
public class TUI {

	private Controller controller;  // Concordance viewer controller 
	private Scanner stdIn;
	
	public TUI(Controller controller) {
		
		this.controller = controller;
		
		// Creates a Scanner object for obtaining user input
		stdIn = new Scanner(System.in);
		
		while (true) {
			displayMenu();
			getAndProcessUserOption();
		}
	}

	/**
	 * Displays the header of this application and a summary of menu options.
	 */
	private void displayMenu() {
		display(header());
		display(menu());
	}
	
	/**
	 * Obtains an user option and processes it.
	 */
	private void getAndProcessUserOption() {
		String[] command = stdIn.nextLine().trim().split(" ");
		switch (command[0]) {
		case "exit" : // Exits the application
			display("Thank you for using Concordance Viewer. Goodbye!");
			System.exit(0);
			break;
		case "kwic" : // KWIC view
			if (command.length == 1) {
				displayError("No query word given.");
			}
			else {
				String word = command[1];
				if (command.length > 2) {
					int contextSize = 0;
					try {
						contextSize = Integer.parseInt(command[2]);
					}
					catch (NumberFormatException numberFormatEx) {
						display('"' + command[2] + '"' + " is not a recognisable number.\nAssuming default context size...\n\n");
					}
					if (contextSize > 0) {
						display(controller.getKWIC(word, contextSize));
						break;
					}
					else {
						display('"' + command[2] + '"' + " is not a positive integer.\nAssuming default context size...\n\n");
					}	
				}
				display(controller.getKWIC(word));  // no valid context size given, assuming default context size...
			}
			break;
		case "detail" : // Wider context view
			if (command.length == 1) {
				displayError("No ID for the required detailed view given.");
			}
			else {
				if (command.length == 2) {
					display(controller.getWiderContext(command[1]));
				}
				else {
					display("Unrecognised command."); 
				}
			}
			break;
		default : // Not a known command option
			display(unrecogniseCommandErrorMsg(command[0]));
		}
	}
	
	/*
	 * Returns a string representation of a brief title for this application as the header.
	 * @return	a header
	 */
	private static String header() {
		return "\nConcordance Viewer\n";
	}
	
	/*
	 * Returns a string representation of the user menu.
	 * @return	the user menu
	 */
	private static String menu() {
		return "PLEASE NOTE THAT ALL COMMANDS ARE CASE-SENSITIVE.\n" + 
		       "To display the concordance of a word: \n Type 'kwic ' followed by the English word and then followed by the context size, e.g.\n" +
		       "\t kwic sister-in-law 6\n" + 
		       "If no window size is specified, a default window size of 10 is assumed, eg\n" + 
		       "\t kwic King's-Weston\n" +		       
		       "To display the wider context of a KWIC result line: \n Type 'detail ' followed by the ID as shown in the last KWIC lookup results, " +
		       " e.g. to show the KWIC line with ID '3':\n" +
		       "\t detail 3\n" +
			   "To exit this application: Type 'exit'\n";
	}
	
	/*
	 * Displays an error message for the user to view.
	 * @param error	the error message to be displayed on the screen
	 */
	private void displayError(String error) {
		System.out.println("ERROR: " + error);
	}
	
	/*
	 * Displays the specified info for the user to view.
	 * @param info	info to be displayed on the screen
	 */
	private void display(String info) {
		System.out.println(info);
	}
	
    /*
     * Returns an error message for an unrecognised command.
     * 
     * @param error the unrecognised command
     * @return      an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: %s.%n", error);
    }

}
