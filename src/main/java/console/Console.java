package console;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.jboss.logging.Logger;

public class Console {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(Console.class);

	public static void printMenu(String title, String[] options) {
		System.out.println("==========================================");
		System.out.println(title);
		System.out.println("==========================================");

		int i;
		for (i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ". " + options[i]);
		}
		System.out.println(++i + ". Quit");
		System.out.println("==========================================");
		System.out.print("Choose an option: ");
	}

	public static int getChoice() {
		return getValidIntInput("", "Please enter a valid number: ");
	}

	public static boolean confirmAction(String message) {
		String input;
		while (true) {
			input = getInput(message + " (y/n): ");

			if (input.equals("y")) {
				return true;
			} else if (input.equals("n")) {
				return false;
			}
		}
	}

	public static void displayMessage(String message) {
		System.out.println("==========================================");
		System.out.println(message);
		System.out.println("==========================================");
	}

	public static String getInput(String prompt) {
		System.out.print(prompt);
		if (scanner.hasNextLine()) {
			scanner.nextLine(); // Consume the leftover newline
		}
		return scanner.next();
	}

	public static String getOptionalInput(String prompt) {
		System.out.print(prompt + " (press Enter to skip): ");
		String input = scanner.nextLine(); // Directly read input without checking for leftovers
		return input.isEmpty() ? null : input; // Returns null if the input is empty
	}

	public static int getOptionalIntInput(String prompt) {
		// Consume any leftover newline from previous input
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}

		System.out.print(prompt + " (press Enter to skip): ");
		String input = scanner.nextLine();

		if (input.isEmpty()) {
			return 0; // Returns 0 if the input is empty
		}

		try {
			return Integer.parseInt(input); // Returns the integer if input is valid
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid integer.");
			return getOptionalIntInput(prompt); // Recursively prompts again
		}
	}

	public static int getValidIntInput(String prompt, String errorMessage) {
		System.out.print(prompt);
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine(); // Clear the invalid input
				System.out.println(errorMessage);
			}
		}
	}

	public static double getValidDoubleInput(String prompt) {
		// Consume any leftover newline from previous input
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}

		System.out.print(prompt + ": ");
		String input = scanner.nextLine();

		try {
			return Double.parseDouble(input); // Returns the double if input is valid
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid double.");
			return getValidDoubleInput(prompt); // Recursively prompts again
		}
	}

	public static double getOptionalDoubleInput(String prompt) {
		// Consume any leftover newline from previous input
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}

		System.out.print(prompt + " (press Enter to skip): ");
		String input = scanner.nextLine();

		if (input.isEmpty()) {
			return 0.0; // Returns 0.0 if the input is empty
		}

		try {
			return Double.parseDouble(input); // Returns the double if input is valid
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter a valid double.");
			return getOptionalDoubleInput(prompt); // Recursively prompts again
		}
	}

	public static void close() {
		scanner.close();
	}
}
