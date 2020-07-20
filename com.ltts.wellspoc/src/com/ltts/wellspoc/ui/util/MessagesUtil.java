package com.ltts.wellspoc.ui.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class with methods to display error, warnings and logging.
 * 
 * @author Avanthi Madivada
 *
 */
public class MessagesUtil {
	// Gets the active shell.
	private static Shell defaultShell = Display.getDefault().getActiveShell();
	public static boolean isValid = true;

	/**
	 * Logs the error message with provided class name.
	 * 
	 * @param className
	 * @param errorMessage
	 */
	public static void logError(String className, String errorMessage) {
		Logger logger = Logger.getLogger(className);
		logger.log(Level.ALL, errorMessage);
	}

	/**
	 * Opens an error dialog with provided error message.
	 * 
	 * @param errMsg
	 */
	public static void displayErrorDialog(String errMsg) {
		MessageDialog.openError(defaultShell, "Error", errMsg);
	}

	/**
	 * Opens a warning dialog with provided warning message.
	 * 
	 * @param warning
	 */
	public static void displayWarningDialog(String warning) {
		MessageDialog.openWarning(defaultShell, "Warning", warning);
	}

	/**
	 * Opens a Message dialog with given information
	 * 
	 * @param info
	 */
	public static void displayInformationDialog(String info) {
		MessageDialog.openInformation(defaultShell, "Message", info);
	}

	/**
	 * Opens a question dialog with provided question.
	 * 
	 * @param question
	 * @return true if selected ok or else false.
	 */
	public static boolean askQuestionDialog(String question) {
		return MessageDialog.openQuestion(defaultShell, "Question", question);
	}

	/**
	 * Restricts the entry of invalid characters.
	 * 
	 * @param textItem
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean restrictEnteredChars(String text, Double minValue, Double maxValue) {
		String allowedCharacters = "0123456789.";
		for (int i = 0; i < text.length(); i++) {
			char charBefore = '\t';
			if (i >= 2) {
				charBefore = text.charAt(i - 2);
			}
			char character = text.charAt(i);
			boolean isAllowed = allowedCharacters.indexOf(character) > -1;

			if (!isAllowed) {
				isValid = false;
				return isValid;
			} else if (charBefore == '.') {
				isValid = false;
				return isValid;
			}
		}
		checkInLimit(text, minValue, maxValue);
		return isValid;
	}

	/**
	 * Checks if the entered chars is number or not.
	 * 
	 * @param textItem
	 * @return
	 */
	public static boolean checkIfNumber(String text) {
		try {
			Double.valueOf(text);
			isValid = true;
		} catch (NumberFormatException exception) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Checks if the given number is within the provided min and max range.
	 * 
	 * @param textItem
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static boolean checkInLimit(String text, Double minValue, Double maxValue) {
		checkIfNumber(text);
		try {
			Double enteredDouble = Double.valueOf(text);
			if (enteredDouble < minValue || enteredDouble > maxValue) {
				isValid = false;
			} else {
				isValid = true;
			}
		} catch (NumberFormatException exception) {
			isValid = false;
		}
		return isValid;
	}
}
