package console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.jboss.logging.Logger;

public class Console {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(Console.class);

	public static void printMenu(String title, String[] options) {
		System.out.println("==========================================");
		System.out.println(title);
		System.out.println("==========================================");

		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ". " + options[i]);
		}
		System.out.println((options.length + 1) + ". Quit");
		System.out.println("==========================================");
		System.out.print("Choose an option: ");
	}

	public static int getChoice() {
		return getValidIntInput("", "Please enter a valid number: ");
	}

	public static boolean confirmAction(String message) {
		while (true) {
			String input = getInput(message + " (y/n): ").toLowerCase();
			if (input.equals("y")) {
				return true;
			} else if (input.equals("n")) {
				return false;
			}
			System.out.println("Please enter 'y' or 'n'");
		}
	}

	public static void displayMessage(String message) {
		System.out.println("==========================================");
		System.out.println(message);
		System.out.println("==========================================");
	}

	public static String getInput(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}

	public static LocalDateTime parseToLocalDateTime(String startDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(startDate, formatter);
		return localDate.atStartOfDay();
	}

	public static LocalDateTime getValidatedLocalDateTime(String prompt) {
		while (true) {
			String dateInput = getInput(prompt + "(yyyy-MM-dd): ");
			try {
				return parseToLocalDateTime(dateInput);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in 'yyyy-MM-dd' format.");
			}
		}
	}

	public static LocalDateTime getOptionalLocalDateTime(String prompt) {
		while (true) {
			String dateInput = getOptionalInput(prompt + "(yyyy-MM-dd)");
			if (dateInput == null) {
				return null;
			}
			try {
				return parseToLocalDateTime(dateInput);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in 'yyyy-MM-dd' format.");
			}
		}
	}

	public static String getOptionalInput(String prompt) {
		System.out.print(prompt + " (press Enter to skip): ");
		String input = scanner.nextLine().trim();
		return input.isEmpty() ? null : input;
	}

	public static int getOptionalIntInput(String prompt) {
		while (true) {
			System.out.print(prompt + " (press Enter to skip): ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty()) {
				return 0;
			}

			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
	}

	public static int getValidIntInput(String prompt, String errorMessage) {
		while (true) {
			try {
				String input = getInput(prompt);
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
		}
	}

	public static double getValidDoubleInput(String prompt) {
		while (true) {
			try {
				String input = getInput(prompt + ": ");
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid double.");
			}
		}
	}

	public static double getOptionalDoubleInput(String prompt) {
		while (true) {
			System.out.print(prompt + " (press Enter to skip): ");
			String input = scanner.nextLine().trim();

			if (input.isEmpty()) {
				return 0.0;
			}

			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid double.");
			}
		}
	}

	public static void close() {
		scanner.close();
	}
}