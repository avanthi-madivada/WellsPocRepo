package com.ltts.wellspoc.ui.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Utility class with methods to display error, warnings and logging.
 * 
 * @author Avanthi Madivada
 *
 */
public class MessagesUtil {
	// Gets the active shell.
	private static Shell defaultShell = Display.getDefault().getActiveShell();

	private static final Device device = Display.getCurrent();;
	private static final Color red = new Color(device, 255, 0, 0);
	private final static Color black = new Color(device, 0, 0, 0);
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
	 */
	public static void restrictEnteredChars(Text textItem, Double minValue, Double maxValue) {
		String allowedCharacters = "0123456789.,-";
		String text = textItem.getText();
		textItem.setForeground(black);
		for (int i = 0; i < text.length(); i++) {
			char charBefore = '\t';
			if (i >= 2) {
				charBefore = text.charAt(i - 2);
			}
			char character = text.charAt(i);
			boolean isAllowed = allowedCharacters.indexOf(character) > -1;

			if (!isAllowed) {
				textItem.setForeground(red);
				isValid = false;
				return;
			} else if (charBefore == '.') {
				textItem.setForeground(red);
				isValid = false;
				return;
			}
		}
		checkInLimit(textItem, minValue, maxValue);
	}

	/**
	 * Checks if the entered chars is number or not.
	 * 
	 * @param textItem
	 */
	public static void checkIfNumber(Text textItem) {
		String textEntered = textItem.getText();
		try {
			textItem.setForeground(black);
			Double.valueOf(textEntered);
			return;
		} catch (NumberFormatException exception) {
			isValid = false;
			textItem.setForeground(red);
			return;
		}
	}

	/**
	 * Checks if the given number is within the provided min and max range.
	 * 
	 * @param textItem
	 * @param minValue
	 * @param maxValue
	 */
	public static void checkInLimit(Text textItem, Double minValue, Double maxValue) {
		checkIfNumber(textItem);
		String textEntered = textItem.getText();
		try {
			Double enteredDouble = Double.valueOf(textEntered);
			if (enteredDouble < minValue || enteredDouble > maxValue) {
				isValid = false;
				textItem.setForeground(red);
				return;
			} else {
				textItem.setForeground(black);
				isValid = true;
				return;
			}
		} catch (NumberFormatException exception) {
			isValid = false;
		}
	}
}
