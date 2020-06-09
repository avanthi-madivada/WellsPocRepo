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
	//Gets the active shell.
	private static Shell defaultShell = Display.getDefault().getActiveShell();

	/**
	 * Logs the error message with provided class name.
	 * 
	 * @param className
	 * @param errorMessage
	 */
	public static void logError(String className, String errorMessage) {
		Logger logger = Logger.getLogger(className);
		logger.log(Level.ALL, "Error msg");
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
}
